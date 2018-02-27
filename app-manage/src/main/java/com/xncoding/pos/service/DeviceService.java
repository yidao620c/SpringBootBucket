package com.xncoding.pos.service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.xncoding.pos.async.AsyncException;
import com.xncoding.pos.async.AsyncTask;
import com.xncoding.pos.common.dao.entity.Pos;
import com.xncoding.pos.common.dao.entity.PosHistory;
import com.xncoding.pos.common.dao.entity.Project;
import com.xncoding.pos.common.dao.repository.PosHistoryMapper;
import com.xncoding.pos.common.dao.repository.ProjectMapper;
import com.xncoding.pos.config.properties.MyProperties;
import com.xncoding.pos.dao.entity.ManagerInfo;
import com.xncoding.pos.dao.entity.PosInfo;
import com.xncoding.pos.dao.entity.SearchPos;
import com.xncoding.pos.dao.repository.PosInfoDao;
import com.xncoding.pos.http.HttpBaseResponse;
import com.xncoding.pos.http.LoginParam;
import com.xncoding.pos.http.TokenRequestInterceptor;
import com.xncoding.pos.http.UnbindParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 机具管理Service
 */

@Service
public class DeviceService {

    private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);

    @Resource
    private PosInfoDao posInfoDao;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private PosHistoryMapper posHistoryMapper;
    @Resource
    private AsyncTask asyncTask;

    /**
     * 所有项目列表
     *
     * @return 查询到的所有项目列表
     */
    public List<Project> allProjects(List<Integer> pidList) {
        return projectMapper.selectList(Condition.create().in("id", pidList));
    }

    /**
     * 分页查询
     *
     * @param page      分页参数
     * @param searchPos 查询条件
     * @return 分页结果
     */
    public List<PosInfo> searchList(Page<PosInfo> page, SearchPos searchPos) {
        List<PosInfo> list = posInfoDao.searchList(page, searchPos);
        for (PosInfo posInfo : list) {
            posInfo.buildTable();
        }
        return list;
    }

    /**
     * 查询机具详情
     *
     * @param id 机具ID
     * @return 查询的POS详情
     */
    public PosInfo searchDetail(Integer id) {
        PosInfo posInfo = posInfoDao.searchDetail(id);
        posInfo.buildTable();
        return posInfo;
    }

    /**
     * 更新机具状态
     *
     * @param id    机具ID
     * @param state 状态值
     * @return 更新结果
     */
    public int updateState(Integer id, Integer state) {
        Pos pos = new Pos();
        pos.setId(id);
        pos.setPosState(state);
        pos.setUpdatedTime(new Date());
        return posInfoDao.updateById(pos);
    }

    /**
     * 解除绑定
     *
     * @param id pos机的ID
     * @return 解绑结果
     */
    public int unbind(Integer id, ManagerInfo managerInfo) {
        Date now = new Date();
        Pos pos = posInfoDao.selectById(id);
        // 先保存网点
        String location = pos.getLocation();
        pos.setLocation("");
        pos.setUpdatedTime(now);
        posInfoDao.updateById(pos);

        // 解绑之后更新绑定历史
        PosHistory posHistory = posInfoDao.selectLastHistory(id, location);
        if (posHistory != null) {
            posHistory.setUnbindtime(now);
            posHistory.setUpdatedTime(now);
            posHistoryMapper.updateById(posHistory);
        }

        // 开始异步推送消息
        asyncTask.pushUnbindMsg(managerInfo, pos, location);

        return 1;
    }



    /**
     * 查询pos绑定历史列表
     *
     * @param posId 机具ID
     * @return 绑定历史列表，按照绑定事件倒叙排列
     */
    public List<PosHistory> selectHisotryList(Integer posId) {
        return posHistoryMapper.selectList(Condition.create().eq("pos_id", posId).orderBy("bindtime", false));
    }


}
