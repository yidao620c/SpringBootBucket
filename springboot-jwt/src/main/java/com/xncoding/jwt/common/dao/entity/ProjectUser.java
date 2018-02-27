package com.xncoding.jwt.common.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目用户关联表
 *
 * @author 熊能
 * @version 1.0
 * @since 2018/01/02
 */
public class ProjectUser {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 项目ID
     */
    private Integer projectId;
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
     * 获取 用户ID.
     *
     * @return 用户ID.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置 用户ID.
     *
     * @param userId 用户ID.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取 项目ID.
     *
     * @return 项目ID.
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置 项目ID.
     *
     * @param projectId 项目ID.
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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
