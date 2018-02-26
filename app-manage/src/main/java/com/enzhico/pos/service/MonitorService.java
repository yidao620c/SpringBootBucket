package com.enzhico.pos.service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.enzhico.pos.common.dao.entity.Project;
import com.enzhico.pos.common.dao.repository.ProjectMapper;
import com.enzhico.pos.common.util.DateUtil;
import com.enzhico.pos.config.properties.MyProperties;
import com.enzhico.pos.dao.entity.*;
import com.enzhico.pos.dao.repository.PosInfoDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 监控管理Service
 */

@Service
public class MonitorService {

    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private PosInfoDao posInfoDao;
    @Resource
    private MyProperties myProperties;

    /**
     * 查询所有项目
     * @return
     */
    public List<Project> selectAllProjects(List<Integer> pidList) {
        return projectMapper.selectList(Condition.create().in("id", pidList));
    }

    /**
     * 入网机具总数
     * @return
     */
    public int posCount(Integer projectId, List<Integer> pidList) {
        if (projectId != 0) {
            return posInfoDao.selectCount(
                    Condition.create().eq("project_id", projectId).ne("location", ""));
        }
        return posInfoDao.selectCount(Condition.create().ne("location", "").in("project_id", pidList));
    }

    /**
     * 布放网点数
     * @param projectId
     * @return
     */
    public int locationCount(Integer projectId, List<Integer> pidList) {
        return posInfoDao.searchLocationCount(projectId, pidList);
    }

    /**
     * 分页查询，以网点分组
     * @param page
     * @param searchPos
     * @return
     */
    public List<PosGroupInfo> searchListGroupByLocation(Page<PosInfo> page, SearchPos searchPos) {
        List<PosGroupInfo> list = posInfoDao.searchListGroupByLocation(page, searchPos);
        return list;
    }

    /**
     * 网点详细页面分页查询
     * @param page
     * @param searchMonitor
     * @return
     */
    public List<MonitorInfo> searchLocationMonitorList(Page<PosInfo> page, SearchMonitor searchMonitor) {
        List<MonitorInfo> list = posInfoDao.selectPosMonitorList(page, searchMonitor);
        if (list != null) {
            for (MonitorInfo monitorInfo : list) {
                monitorInfo.buildTable();
            }
        }
        return list;
    }

    /**
     * 定期更新在线状态
     */
    public void updateOnlineState() {
        posInfoDao.updateOnlineState(DateUtil.offsiteDate(new Date(), Calendar.MINUTE, -10));
    }

}
