package com.xncoding.jwt.common.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * APP发布表
 *
 * @author 熊能
 * @version 1.0
 * @since 2018/01/02
 */
public class AppPublish {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Integer id;
    /**
     * APP主键
     */
    private Integer appId;
    /**
     * POS主键
     */
    private Integer posId;
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
     * 获取 APP主键.
     *
     * @return APP主键.
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * 设置 APP主键.
     *
     * @param appId APP主键.
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * 获取 POS主键.
     *
     * @return POS主键.
     */
    public Integer getPosId() {
        return posId;
    }

    /**
     * 设置 POS主键.
     *
     * @param posId POS主键.
     */
    public void setPosId(Integer posId) {
        this.posId = posId;
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
