package com.xncoding.jwt.common.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * POS机监控表
 *
 * @author 熊能
 * @version 1.0
 * @since 2018/01/02
 */
public class PosMonitor {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Integer id;
    /**
     * POS机ID
     */
    private Integer posId;
    /**
     * Socket会话ID
     */
    private String sessionId;
    /**
     * 最近一次报告时间
     */
    private Date reportTime;
    /**
     * 最近一次报告地址
     */
    private String reportLocation;
    /**
     * 在线状态: 1:在线 2:离线
     */
    private Integer onlineState;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 获取 主键ID.
     *
     * @return 主键ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 主键ID.
     *
     * @param id 主键ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 POS机ID.
     *
     * @return POS机ID.
     */
    public Integer getPosId() {
        return posId;
    }

    /**
     * 设置 POS机ID.
     *
     * @param posId POS机ID.
     */
    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    /**
     * 获取 Socket会话ID.
     *
     * @return Socket会话ID.
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * 设置 Socket会话ID.
     *
     * @param sessionId Socket会话ID.
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * 获取 最近一次报告时间.
     *
     * @return 最近一次报告时间.
     */
    public Date getReportTime() {
        return reportTime;
    }

    /**
     * 设置 最近一次报告时间.
     *
     * @param reportTime 最近一次报告时间.
     */
    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * 获取 最近一次报告地址.
     *
     * @return 最近一次报告地址.
     */
    public String getReportLocation() {
        return reportLocation;
    }

    /**
     * 设置 最近一次报告地址.
     *
     * @param reportLocation 最近一次报告地址.
     */
    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    /**
     * 获取 在线状态: 1:在线 2:离线.
     *
     * @return 在线状态: 1:在线 2:离线.
     */
    public Integer getOnlineState() {
        return onlineState;
    }

    /**
     * 设置 在线状态: 1:在线 2:离线.
     *
     * @param onlineState 在线状态: 1:在线 2:离线.
     */
    public void setOnlineState(Integer onlineState) {
        this.onlineState = onlineState;
    }

    /**
     * 获取 创建时间.
     *
     * @return 创建时间.
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置 创建时间.
     *
     * @param createdTime 创建时间.
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取 更新时间.
     *
     * @return 更新时间.
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置 更新时间.
     *
     * @param updatedTime 更新时间.
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    protected Serializable pkVal() {
        return this.id;
    }

}
