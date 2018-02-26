package com.enzhico.jwt.common.dao.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * 项目表
 *
 * @author 熊能
 * @version 1.0
 * @since 2018/01/02
 */
@TableName(value = "t_project")
public class Project extends Model<Project> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 应用编号
     */
    private String applicationId;
    /**
     * 项目图片
     */
    private String icon;
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
     * 获取 项目名称.
     *
     * @return 项目名称.
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 项目名称.
     *
     * @param name 项目名称.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 应用编号.
     *
     * @return 应用编号.
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * 设置 应用编号.
     *
     * @param applicationId 应用编号.
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * 获取 项目图片.
     *
     * @return 项目图片.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置 项目图片.
     *
     * @param icon 项目图片.
     */
    public void setIcon(String icon) {
        this.icon = icon;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
