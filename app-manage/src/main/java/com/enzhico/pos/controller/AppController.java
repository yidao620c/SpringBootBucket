package com.enzhico.pos.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.enzhico.pos.common.dao.entity.Pos;
import com.enzhico.pos.common.util.AnalysisApk;
import com.enzhico.pos.common.util.DateUtil;
import com.enzhico.pos.config.properties.MyProperties;
import com.enzhico.pos.dao.entity.AppInfo;
import com.enzhico.pos.dao.entity.ManagerInfo;
import com.enzhico.pos.dao.entity.PublishParam;
import com.enzhico.pos.dao.entity.SearchApp;
import com.enzhico.pos.model.BaseResponse;
import com.enzhico.pos.service.AppService;
import com.enzhico.pos.shiro.ShiroKit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

/**
 * Description: APP管理
 */

@Controller
@RequestMapping("/app")
public class AppController {
    @Resource
    private AppService appService;
    @Resource
    private MyProperties myProperties;

    private static final Logger _logger = LoggerFactory.getLogger(AppController.class);

    /**
     * App管理首页
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    @RequiresUser
    public String index(HttpServletRequest request, Model model) throws JsonProcessingException {
        ManagerInfo managerInfo = ShiroKit.getUser();
        // 应用名称和版本号对应关系
        String json = new ObjectMapper().writeValueAsString(appService.selectAppVersionList(managerInfo.getPidsList()));
        model.addAttribute("appVersionMap", json);
        // 应用名称列表
        model.addAttribute("appNames", appService.selectAllAppNames(managerInfo.getPidsList()));
        // 版本号列表
        model.addAttribute("versions", appService.selectAllVersions(managerInfo.getPidsList()));
        // 项目名称列表
        model.addAttribute("projects", appService.selectAllProjects(managerInfo.getPidsList()));
        return "modules/app/appManagement";
    }

    /**
     * 分页查询列表
     *
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    @RequiresUser()
    public BaseResponse list(@ModelAttribute SearchApp searchApp) {
        // 日期范围分解
        if (StringUtils.isNotEmpty(searchApp.getPublishtimeRange())) {
            String[] ds = searchApp.getPublishtimeRange().split(" - ");
            searchApp.setPublishTimeStart(DateUtil.getDateStartTime(DateUtil.parseDate(ds[0])));
            searchApp.setPublishTimeEnd(DateUtil.getDateEndTime(DateUtil.parseDate(ds[1])));
        }
        // 设置项目ID
        ManagerInfo managerInfo = ShiroKit.getUser();
        searchApp.setPidList(managerInfo.getPidsList());
        Page page = new Page(searchApp.getPageNumber(), searchApp.getPageSize());
        List<AppInfo> list = appService.searchList(page, searchApp);
        return new BaseResponse(true, "查询列表", page.getTotal(), list);
    }

    /**
     * 灰度发布列表
     *
     * @return
     */
    @RequestMapping(value = "/graylist")
    @ResponseBody
    @RequiresUser
    public BaseResponse graylist(@RequestParam("id") Integer appId) {
        List<Pos> list = appService.grayList(appId);
        return new BaseResponse(true, "灰度发布列表", 0, list);
    }

    /**
     * 全网发布
     *
     * @return
     */
    @RequestMapping(value = "/publishall/{appId}", method = RequestMethod.POST)
    @ResponseBody
    @RequiresUser
    public BaseResponse publishAll(@PathVariable("appId") Integer appId) {
        appService.publishAll(appId);
        return new BaseResponse(true, "全网发布成功", 0, null);
    }


    /**
     * 发布新版本页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/publish")
    @RequiresUser
    public String publish(HttpServletRequest request, Model model) {
        // 项目名称列表
        ManagerInfo managerInfo = ShiroKit.getUser();
        // 应用名称列表
        model.addAttribute("appNames", appService.selectAllAppNames(managerInfo.getPidsList()));
        return "modules/app/addVersion";
    }

    /**
     * 发布新版本
     *
     * @return
     */
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    @ResponseBody
    @RequiresUser
    public BaseResponse doPublish(@ModelAttribute PublishParam param) throws IOException {
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        multipartRequest.getParameter("name");
        _logger.info("app name=" + param.getName());
        MultipartFile file = param.getFile();
        String tempFile = param.getName() + "_" + System.currentTimeMillis() + ".apk";
        if (!file.isEmpty()) {
            File destFile = new File(myProperties.getFilesPath(), tempFile);
            Path destFilePath = Paths.get(destFile.getAbsolutePath());
            saveFile(file, destFile);
            // 解析APK文件，提取版本号和Application ID
            String[] packageInfo;
            try {
                packageInfo = AnalysisApk.unZip(
                        new File(myProperties.getFilesPath(), tempFile).getAbsolutePath(), myProperties.getFilesPath());
            } catch (IOException e) {
                return new BaseResponse(false, "文件类型错误，请上传应用文件。", 0, null);
            }
            String appVersion = packageInfo[0];
            if (appVersion == null) {
                return new BaseResponse(false, "文件类型错误，请上传应用文件。", 0, null);
            }
//            String appVersionCode = packageInfo[1];
            String applicationId = packageInfo[2];

            // 先获取登录用户
            ManagerInfo manager = ShiroKit.getUser();
            // 做用户发布权限的检查
            if (!appService.checkUserPublishPermission(manager.getId(), applicationId)) {
                return new BaseResponse(false, "上传失败，你没有权限发布此应用", 0, null);
            }

            // 接下来做APK文件和版本号的合法性检查
            boolean checkVersion;
            try {
                checkVersion = appService.checkAppVersion(applicationId, appVersion);
            } catch (NullPointerException e) {
                return new BaseResponse(false, "上传失败，请先配置好Application Id = " + applicationId + "的项目", 0, null);
            }
            if (!checkVersion) {
                Files.delete(destFilePath);
                return new BaseResponse(false, "版本号较低，请重新上传", 0, null);
            }
            // 重命名文件
            Files.move(destFilePath, destFilePath.resolveSibling(param.getName() + "_" + appVersion + ".apk"), StandardCopyOption.REPLACE_EXISTING);
            // 更新参数
            param.setVersion(appVersion);
            param.setApplicationId(applicationId);

            // 然后保存这条版本记录
            appService.addVersion(param, manager);
            return new BaseResponse(true, "上传成功", 0, null);
        }
        return new BaseResponse(false, "请上传应用文件", 0, null);
    }

    /**
     * 灰度发布页面
     *
     * @return
     */
    @RequestMapping(value = "/gray")
    @RequiresUser
    public String gray(HttpServletRequest request, Model model) {
        // 项目名称列表
        ManagerInfo managerInfo = ShiroKit.getUser();
        model.addAttribute("locations", appService.selectAllLocations(managerInfo.getPidsList()));
        return "modules/app/grayPublish";
    }

    /**
     * 按网点查询POS列表
     *
     * @return
     */
    @RequestMapping(value = "/listlo")
    @ResponseBody
    @RequiresUser
    public BaseResponse listlo(@RequestParam("locations") String locations) {
        if ("0".equals(locations)) {
            return new BaseResponse(true, "网点查询POS列表", 0, null);
        }
        List<String> locationList = null;
        if (StringUtils.isNotEmpty(locations)) {
            locationList = Arrays.asList(locations.split(","));
        }
        List<Pos> posList = appService.selectPosListByLocations(locationList);
        return new BaseResponse(true, "网点查询POS列表", posList != null ? posList.size() : 0, posList);
    }

    /**
     * 按imei查询POS列表
     *
     * @return POS列表
     */
    @RequestMapping(value = "/imei")
    @ResponseBody
    @RequiresUser
    public BaseResponse imei(@RequestParam("imei") String imei) {
        Pos pos = appService.selectByImei(imei);
        return new BaseResponse(pos != null, "按imei查询POS列表", 0, pos);
    }

    /**
     * 保存文件
     * @param file 上传文件
     * @param destFile 目标文件
     */
    private void saveFile(MultipartFile file, File destFile) {
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
        } catch (IOException ie) {
            _logger.error("保存文件出错", ie);
        }
    }


}
