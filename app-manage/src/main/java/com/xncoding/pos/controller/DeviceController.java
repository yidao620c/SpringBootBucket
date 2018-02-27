package com.xncoding.pos.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.xncoding.pos.common.dao.entity.PosHistory;
import com.xncoding.pos.dao.entity.ManagerInfo;
import com.xncoding.pos.dao.entity.PosInfo;
import com.xncoding.pos.dao.entity.SearchPos;
import com.xncoding.pos.model.BaseResponse;
import com.xncoding.pos.service.DeviceService;
import com.xncoding.pos.shiro.ShiroKit;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description: 机具入网管理
 */

@Controller
@RequestMapping("/device")
public class DeviceController {

    @Resource
    private DeviceService deviceService;

    /**
     * 机具入网管理首页
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    @RequiresUser()
    public String index(HttpServletRequest request, Model model) {
        // 查询所有项目列表
        ManagerInfo managerInfo = ShiroKit.getUser();
        model.addAttribute("projects", deviceService.allProjects(managerInfo.getPidsList()));
        return "modules/device/deviceManagement";
    }

    /**
     * 机具详情
     *
     * @return
     */
    @RequestMapping(value = "/detail")
    @RequiresUser()
    public String detail(Model model, @RequestParam("id") Integer id) {
        PosInfo posInfo = deviceService.searchDetail(id);
        model.addAttribute("pos", posInfo);
        return "modules/device/deviceDetail";
    }

    /**
     * 查询列表
     *
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    @RequiresUser()
    public BaseResponse list(@ModelAttribute SearchPos searchPos) {
        Page page = new Page(searchPos.getPageNumber(), searchPos.getPageSize());
        ManagerInfo managerInfo = ShiroKit.getUser();
        searchPos.setPidList(managerInfo.getPidsList());
        List<PosInfo> list = deviceService.searchList(page, searchPos);
        return new BaseResponse(true, "设备列表", page.getTotal(), list);
    }

    /**
     * 修改机具状态
     *
     * @return
     */
    @RequestMapping(value = "/state/{id}/{state}", method = RequestMethod.POST)
    @ResponseBody
    @RequiresUser()
    public BaseResponse state(@PathVariable("id") Integer id,
                              @PathVariable("state") Integer state) {
        deviceService.updateState(id, state);
        return new BaseResponse(true, "修改状态成功", 0, null);
    }

    /**
     * 解除网点绑定
     *
     * @return
     */
    @RequestMapping(value = "/unbind/{id}", method = RequestMethod.POST)
    @ResponseBody
    @RequiresUser()
    public BaseResponse unbind(@PathVariable("id") Integer id) {
        ManagerInfo managerInfo = ShiroKit.getUser();
        deviceService.unbind(id, managerInfo);
        return new BaseResponse(true, "解除网点绑定成功", 0, null);
    }

    /**
     * 查询绑定历史
     *
     * @return
     */
    @RequestMapping(value = "/history/{posId}", method = RequestMethod.GET)
    @ResponseBody
    @RequiresUser()
    public BaseResponse bindHistory(@PathVariable("posId") Integer id) {
        List<PosHistory> historyList = deviceService.selectHisotryList(id);
        return new BaseResponse(true, "查询绑定历史", 0, historyList);
    }

}
