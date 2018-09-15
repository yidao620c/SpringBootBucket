package com.xncoding.jwt.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限表
 *
 * @author 熊能
 * @version 1.0
 * @since 2018/01/02
 */
public class Permission {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 权限名称
     */
    private String permission;
    /**
     * 权限说明
     */
    private String description;
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
     * 获取 权限名称.
     *
     * @return 权限名称.
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 设置 权限名称.
     *
     * @param permission 权限名称.
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * 获取 权限说明.
     *
     * @return 权限说明.
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置 权限说明.
     *
     * @param description 权限说明.
     */
    public void setDescription(String description) {
        this.description = description;
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
