package com.xncoding.jwt.service;

import com.xncoding.jwt.api.model.ReportParam;
import com.xncoding.jwt.api.model.VersionParam;
import com.xncoding.jwt.api.model.VersionResult;
import com.xncoding.jwt.common.dao.entity.Pos;
import com.xncoding.jwt.common.dao.entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 专门用来服务对外接口用Service
 */

@Service
public class ApiService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

    /**
     * 根据IMEI码查询POS机是否已经入网
     *
     * @param imei IMEI码
     * @return 数量
     */
    public int selectCount(String imei) {
        return 1;
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
        return p;
    }

    /**
     * 根据IMEI码查找绑定网点的POS
     *
     * @param imei
     * @return
     */
    public Pos selectBindByImei(String imei) {
        return null;
    }

    /**
     * 根据IMEI码查询POS机是否绑定了网点
     *
     * @param imei IMEI码
     * @return 绑定数量
     */
    public int selectBindCount(String imei) {
        return 1;
    }

    /**
     * 更新机具信息
     *
     * @param pos
     * @return
     */
    public int bindLocation(Pos pos) {
        return 1;
    }

    /**
     * 执行POS机入网
     *
     * @param param 参数
     * @return 结果
     */
    public int insertPos(Pos param) {
        return 1;
    }

    /**
     * 根据Application Id查询项目
     *
     * @param applicationId Application Id
     * @return 项目
     */
    public Project selectProjectByApplicationId(String applicationId) {
        Project p = new Project();
        return p;
    }

    /**
     * 更新报告
     *
     * @param param 报告参数
     * @return 结果
     */
    public int report(ReportParam param) {
        return 1;
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
        return 1;
    }

    /**
     * 根据imei码获取session id
     *
     * @param imei imei码
     * @return sessionId
     */
    public String selectSessionId(String imei) {
        return "11";
    }

    /**
     * 查询版本发布
     * @param param 查询版本参数
     * @return 结果
     */
    public VersionResult selectPublishCount(VersionParam param) {
        return new VersionResult();
    }

    /**
     * 给某个POS机推送网点解除绑定消息
     * @param imei imei码
     * @param location 解除的网点
     * @return error msg
     */
    public String sendUnbind(String imei, String location) {
        logger.info("开始给POS机推送解绑消息");
        return null;
    }

}
