package com.xncoding.jwt.service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.xncoding.jwt.api.model.ReportParam;
import com.xncoding.jwt.api.model.VersionParam;
import com.xncoding.jwt.api.model.VersionResult;
import com.xncoding.jwt.common.dao.entity.Pos;
import com.xncoding.jwt.common.dao.entity.PosHistory;
import com.xncoding.jwt.common.dao.entity.PosMonitor;
import com.xncoding.jwt.common.dao.entity.Project;
import com.xncoding.jwt.common.dao.repository.PosHistoryMapper;
import com.xncoding.jwt.common.dao.repository.PosMapper;
import com.xncoding.jwt.common.dao.repository.PosMonitorMapper;
import com.xncoding.jwt.common.dao.repository.ProjectMapper;
import com.xncoding.jwt.common.util.CommonUtil;
import com.xncoding.jwt.config.properties.MyProperties;
import com.xncoding.jwt.dao.repository.ApiDao;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 专门用来服务对外接口用Service
 */

@Service
public class ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

    @Resource
    private PosMapper posMapper;
    @Resource
    private PosHistoryMapper posHistoryMapper;
    @Resource
    private PosMonitorMapper posMonitorMapper;
    @Resource
    private ApiDao apiDao;
    @Resource
    private MyProperties myProperties;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private SocketIOServer server;

    /**
     * 根据IMEI码查询POS机是否已经入网
     *
     * @param imei IMEI码
     * @return 数量
     */
    public int selectCount(String imei) {
        return posMapper.selectCount(Condition.create().eq("imei", imei));
    }

    /**
     * 根据IMEI码查找POS
     *
     * @param imei
     * @return
     */
    public Pos selectByImei(String imei) {
        Pos p = new Pos();
        p.setImei(imei);
        return posMapper.selectOne(p);
    }

    /**
     * 根据IMEI码查找绑定网点的POS
     *
     * @param imei
     * @return
     */
    public Pos selectBindByImei(String imei) {
        Pos p = new Pos();
        p.setImei(imei);
        List<Pos> list = posMapper.selectList(Condition.create().eq("imei", imei).ne("location", "").isNotNull("location"));
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 根据IMEI码查询POS机是否绑定了网点
     *
     * @param imei IMEI码
     * @return 绑定数量
     */
    public int selectBindCount(String imei) {
        return posMapper.selectCount(Condition.create().eq("imei", imei).isNotNull("location").ne("location", ""));
    }

    /**
     * 更新机具信息
     *
     * @param pos
     * @return
     */
    public int bindLocation(Pos pos) {
        // 先更新POS网点信息
        posMapper.updateById(pos);

        // 然后插入新的POS绑定历史记录
        PosHistory posHistory = new PosHistory();
        posHistory.setPosId(pos.getId());
        posHistory.setLocation(pos.getLocation());
        posHistory.setBindtime(pos.getBindtime());
        return posHistoryMapper.insert(posHistory);
    }

    /**
     * 执行POS机入网
     *
     * @param param 参数
     * @return 结果
     */
    public int insertPos(Pos param) {
        param.setPosState(1);
        // 插入一条pos记录
        posMapper.insert(param);
        // 插入一条历史记录
        PosHistory posHistory = new PosHistory();
        posHistory.setPosId(param.getId());
        posHistory.setLocation(param.getLocation());
        posHistory.setBindtime(new Date());
        return posHistoryMapper.insert(posHistory);
    }

    /**
     * 根据Application Id查询项目
     *
     * @param applicationId Application Id
     * @return 项目
     */
    public Project selectProjectByApplicationId(String applicationId) {
        Project p = new Project();
        p.setApplicationId(applicationId);
        return projectMapper.selectOne(p);
    }

    /**
     * 更新报告
     *
     * @param param 报告参数
     * @return 结果
     */
    public int report(ReportParam param) {
        Pos p = new Pos();
        p.setImei(param.getImei());
        Pos pos = posMapper.selectOne(p);
        if (pos == null) {
            return 0;
        }
        Date now = new Date();
        PosMonitor pmParam = new PosMonitor();
        pmParam.setPosId(pos.getId());
        PosMonitor posMonitor = posMonitorMapper.selectOne(pmParam);
        if (posMonitor == null) {
            PosMonitor plast = new PosMonitor();
            plast.setPosId(pos.getId());
            plast.setOnlineState(1);
            plast.setReportTime(now);
            plast.setReportLocation(param.getLocation());
            return posMonitorMapper.insert(plast);
        } else {
            posMonitor.setOnlineState(1);
            posMonitor.setReportTime(now);
            posMonitor.setUpdatedTime(now);
            posMonitor.setReportLocation(param.getLocation());
            return posMonitorMapper.updateById(posMonitor);
        }
    }

    /**
     * Just Update monitor state
     *
     * @param param     report param
     * @param sessionId session id.
     * @param state     1:在线 2:离线.
     * @return result
     */
    public int updateJustState(ReportParam param, String sessionId, Integer state) {
        Pos p = new Pos();
        p.setImei(param.getImei());
        Pos pos = posMapper.selectOne(p);
        if (pos == null) {
            return 0;
        }
        Date now = new Date();
        PosMonitor pmParam = new PosMonitor();
        pmParam.setPosId(pos.getId());
        PosMonitor posMonitor = posMonitorMapper.selectOne(pmParam);
        if (posMonitor == null) {
            PosMonitor plast = new PosMonitor();
            plast.setPosId(pos.getId());
            plast.setSessionId(sessionId);
            plast.setOnlineState(state);
            return posMonitorMapper.insert(plast);
        } else {
            posMonitor.setSessionId(sessionId);
            posMonitor.setOnlineState(state);
            posMonitor.setUpdatedTime(now);
            return posMonitorMapper.updateById(posMonitor);
        }
    }

    /**
     * 根据imei码获取session id
     *
     * @param imei imei码
     * @return sessionId
     */
    public String selectSessionId(String imei) {
        return apiDao.selectSessionId(imei);
    }

    /**
     * 查询版本发布
     * @param param 查询版本参数
     * @return 结果
     */
    public VersionResult selectPublishCount(VersionParam param) {
        VersionResult r =  apiDao.selectPublishCount(param);
        if (r != null && CommonUtil.isNewer(r.getVersion(), param.getVersion())) {
            r.setFindNew(true);
            r.setDownloadUrl(myProperties.getApkUrlPrefix() + r.getAppName() + "_" + r.getVersion() + ".apk");
        } else {
            r = new VersionResult();
            r.setFindNew(false);
        }
        return r;
    }

    /**
     * 给某个POS机推送网点解除绑定消息
     * @param imei imei码
     * @param location 解除的网点
     * @return error msg
     */
    public String sendUnbind(String imei, String location) {
        logger.info("开始给POS机推送解绑消息");
        // 先获取session id
        String sessionId = selectSessionId(imei);
        if (StringUtils.isEmpty(sessionId)) {
            logger.error("找不到可用的sessionId");
            return "找不到可用的sessionId";
        }
        SocketIOClient targetClient = this.server.getClient(UUID.fromString(sessionId));
        if (targetClient == null) {
            logger.error("sessionId=" + sessionId + "在server中获取不到client");
            return "sessionId=" + sessionId + "在server中获取不到client";
        }
        targetClient.sendEvent("unbind", location);
        return null;
    }

}
