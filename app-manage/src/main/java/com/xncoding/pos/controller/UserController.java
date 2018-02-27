package com.xncoding.pos.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.xncoding.pos.common.util.DateUtil;
import com.xncoding.pos.dao.entity.ManagerInfo;
import com.xncoding.pos.dao.entity.SearchUser;
import com.xncoding.pos.model.BaseResponse;
import com.xncoding.pos.service.ManagerInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description: 用户管理
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private ManagerInfoService managerInfoService;

    /**
     * 用户管理首页
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    @RequiresRoles(value = {"admin"})
    public String index(HttpServletRequest request, Model model) {
        // 查询所有项目列表
        model.addAttribute("projects", managerInfoService.allProjects());
        return "modules/user/userManagement";
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    @RequiresRoles(value = {"admin"})
    public BaseResponse list(@ModelAttribute SearchUser searchUser) {
        // 创建时间分解
        if (StringUtils.isNotEmpty(searchUser.getCreatedTime())) {
            String[] ds = searchUser.getCreatedTime().split(" - ");
            searchUser.setCreatedTimeStart(DateUtil.getDateStartTime(DateUtil.parseDate(ds[0])));
            searchUser.setCreatedTimeEnd(DateUtil.getDateEndTime(DateUtil.parseDate(ds[1])));
        }
        Page page = new Page(searchUser.getPageNumber(), searchUser.getPageSize());
        List<ManagerInfo> list = managerInfoService.searchUsers(page, searchUser);
        return new BaseResponse(true, "查询用户列表", page.getTotal(), list);
    }

    /**
     * 用户名重复检查
     *
     * @return
     */
    @RequestMapping(value = "/usernamechk", method = RequestMethod.GET)
    @ResponseBody
    @RequiresRoles(value = {"admin"})
    public BaseResponse doAdd(@RequestParam("username") String username) {
        // 用户名重复性检查
        int usercount = managerInfoService.checkUsername(username);
        if (usercount > 0) {
            return new BaseResponse(false, "此用户名已存在", 0, null);
        }
        return new BaseResponse(true, "此用户名不存在", 0, null);
    }

    /**
     * 执行添加用户
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @RequiresRoles(value = {"admin"})
    public String doAdd(@ModelAttribute ManagerInfo manager) {
        managerInfoService.addUser(manager);
        return "redirect:/user/index";
    }

    /**
     * 编辑用户
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit")
    @RequiresRoles(value = {"admin"})
    public String edit(HttpServletRequest request, Model model, @RequestParam("id") Integer id) {
        // 查询所有项目列表
        model.addAttribute("projects", managerInfoService.allProjects());
        // 查询用户
        model.addAttribute("user", managerInfoService.selectUser(id));
        return "modules/user/userEdit :: content";
    }

    /**
     * 执行编辑用户
     *
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @RequiresRoles(value = {"admin"})
    public String doEdit(@ModelAttribute ManagerInfo manager) {
        managerInfoService.editUser(manager);
        return "redirect:/user/index";
    }

    /**
     * 执行重置密码
     *
     * @return
     */
    @RequestMapping(value = "/reset/{id}/{username}", method = RequestMethod.POST)
    @RequiresRoles(value = {"admin"})
    @ResponseBody
    public BaseResponse doReset(@PathVariable("id") Integer id,
                                @PathVariable("username") String username) {
        managerInfoService.resetPassword(id, username);
        return new BaseResponse(true, "重置密码成功", 0, null);
    }

    /**
     * 执行删除用户
     *
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @RequiresRoles(value = {"admin"})
    @ResponseBody
    public BaseResponse doDelete(@PathVariable("id") Integer id) {
        managerInfoService.deleteUer(id);
        return new BaseResponse(true, "删除用户成功", 0, null);
    }

}
