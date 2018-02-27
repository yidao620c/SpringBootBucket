package com.xncoding.pos.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.xncoding.pos.model.BaseResponse;
import com.xncoding.pos.common.dao.entity.Project;
import com.xncoding.pos.dao.entity.*;
import com.xncoding.pos.service.MonitorService;
import com.xncoding.pos.shiro.ShiroKit;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 监控管理
 */

@Controller
@RequestMapping("/monitor")
public class MonitorController {

    @Resource
    private MonitorService monitorService;

    /**
     * 监控管理首页
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    @RequiresUser()
    public String index(HttpServletRequest request, Model model) {
        // 获取所有的项目
        ManagerInfo managerInfo = ShiroKit.getUser();
        List<Project> projects = monitorService.selectAllProjects(managerInfo.getPidsList());
        model.addAttribute("projects", projects);
        // 入网机具总数
        model.addAttribute("posCount", monitorService.posCount(0, managerInfo.getPidsList()));
        // 网点数量
        model.addAttribute("locationCount", monitorService.locationCount(0, managerInfo.getPidsList()));
        return "modules/monitor/deviceStatus";
    }

    /**
     * 切换项目重新加载
     *
     * @return
     */
    @RequestMapping(value = "/reload")
    @ResponseBody
    @RequiresUser()
    public BaseResponse reload(@RequestParam("projectId") Integer projectId) {
        Map<String, Integer> map = new HashMap<>();
        ManagerInfo managerInfo = ShiroKit.getUser();
        map.put("posCount", monitorService.posCount(projectId, managerInfo.getPidsList()));
        map.put("locationCount", monitorService.locationCount(projectId, managerInfo.getPidsList()));
        return new BaseResponse(true,"重新加载", 0,  map);
    }

    /**
     * 网点分组机具分页查询
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
        List<PosGroupInfo> list = monitorService.searchListGroupByLocation(page, searchPos);
        return new BaseResponse(true, "网点分组机具分页查询", page.getTotal(), list);
    }

    /**
     * 网点机具详情页面
     *
     * @return
     */
    @RequestMapping(value = "/detail")
    @RequiresUser()
    public String detail(Model model, @RequestParam("location") String location) {
        model.addAttribute("location", location);
        return "modules/monitor/deviceStatusDetail";
    }

    /**
     * 特定网点机具列表分页
     *
     * @return
     */
    @RequestMapping(value = "/detailList")
    @ResponseBody
    @RequiresUser()
    public BaseResponse detailList(@ModelAttribute SearchMonitor searchMonitor) {
        Page page = new Page(searchMonitor.getPageNumber(), searchMonitor.getPageSize());
        ManagerInfo managerInfo = ShiroKit.getUser();
        searchMonitor.setPidList(managerInfo.getPidsList());
        List<MonitorInfo> list = monitorService.searchLocationMonitorList(page, searchMonitor);
        return new BaseResponse(true, "特定网点机具列表分页", page.getTotal(), list);
    }

}
