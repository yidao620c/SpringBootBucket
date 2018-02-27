package com.xncoding.pos.controller;

import com.xncoding.pos.common.dao.entity.Project;
import com.xncoding.pos.config.properties.MyProperties;
import com.xncoding.pos.dao.entity.FileInfo;
import com.xncoding.pos.model.BaseResponse;
import com.xncoding.pos.service.ProjectService;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

/**
 * Description: 项目管理
 */

@Controller
@RequestMapping("/project")
public class ProjectController {
    private static final Logger _logger = LoggerFactory.getLogger(ProjectController.class);

    @Resource
    private ProjectService projectService;
    @Resource
    private MyProperties myProperties;

    /**
     * 项目管理首页
     *
     * @param request req
     * @param model model
     * @return 项目管理首页页面
     */
    @RequestMapping(value = "/index")
    @RequiresRoles("admin")
    public String index(HttpServletRequest request, Model model) {
        _logger.info("进入项目管理首页...");
        // 获取所有的项目
        List<Project> projects = projectService.selectAll();
        model.addAttribute("projects", projects);
        return "modules/project/projectManagement";
    }

    /**
     * 添加一个项目
     * @param project 项目
     * @return 项目首页
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresRoles("admin")
    public String add(HttpSession session, @ModelAttribute Project project){
        _logger.info("添加项目...");
        //Thread.sleep(3000L);
        projectService.add(project);
        // 更新session中的项目数量
        int pnum = projectService.selectProjectNum();
        session.setAttribute("projectNum", pnum);
        return "redirect:/project/index";
    }

    /**
     * 删除一个项目
     * @param projectId 项目ID
     * @return 项目首页
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    @RequiresRoles("admin")
    public BaseResponse delete(HttpSession session, @RequestParam("id") Integer projectId) {
        _logger.info("删除项目，id=" + projectId);
        // 项目下存在用户不能删除，该项目下存在用户，无法删除
        int userCount = projectService.selectUsersByPrjectId(projectId);
        if (userCount > 0) {
            return new BaseResponse(false, "该项目下存在用户，无法删除", 0, null);
        }
        projectService.delete(projectId);
        // 更新session中的项目数量
        int pnum = projectService.selectProjectNum();
        session.setAttribute("projectNum", pnum);

        return new BaseResponse(true, "删除项目成功", 0, null);
    }

    /**
     * 上传图片
     *
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    @RequiresUser
    public BaseResponse doPublish(@RequestParam("file") MultipartFile file) {
        String fileName0 = toUTF8(file.getOriginalFilename());
        String fileName = uuidStr() + fileName0.substring(fileName0.lastIndexOf("."));
        _logger.info("Sava fileName=" + fileName);
        if (!file.isEmpty()) {
            savePic(file, fileName);
            return new BaseResponse(true, "上传成功", 0, new FileInfo(fileName));
        }
        return new BaseResponse(false, "上传失败，因为文件是空的", 0, null);
    }
    /**
     * 获取随机值字符串
     * @return
     */
    public static String uuidStr() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private String toUTF8(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            _logger.info("文件名转码错误", e);
        }
        return null;
    }

    /**
     * 保存图片
     * @param picFile
     * @param fileName
     */
    private void savePic(MultipartFile picFile, String fileName) {
        try {
            FileUtils.copyInputStreamToFile(picFile.getInputStream(),
                    new File(myProperties.getPicsPath(), fileName));
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

}
