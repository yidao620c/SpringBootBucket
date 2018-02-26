package com.enzhico.pos.common.dao.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * APP表
 *
 * @author 熊能
 * @version 1.0
 * @since 2018/01/02
 */
@TableName(value = "t_app")
public class App extends Model<App> {

private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**
     * 应用编号
     */
    private String applicationId;
    /**
     * 应用名称
     */
    private String name;
    /**
     * 版本号
     */
    private String version;
    /**
     * 版本说明
     */
    private String tips;
    /**
     * 归属项目ID
     */
    private Integer projectId;
    /**
     * 发布时间
     */
    private Date publishtime;
    /**
     * 发布范围 1:全网发布 2:灰度发布
     */
    private Integer publishRange;
    /**
     * 操作者ID
     */
    private Integer operatorId;
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
     * 获取 应用名称.
     *
     * @return 应用名称.
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 应用名称.
     *
     * @param name 应用名称.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 版本号.
     *
     * @return 版本号.
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置 版本号.
     *
     * @param version 版本号.
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取 版本说明.
     *
     * @return 版本说明.
     */
    public String getTips() {
        return tips;
    }

    /**
     * 设置 版本说明.
     *
     * @param tips 版本说明.
     */
    public void setTips(String tips) {
        this.tips = tips;
    }

    /**
     * 获取 归属项目ID.
     *
     * @return 归属项目ID.
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置 归属项目ID.
     *
     * @param projectId 归属项目ID.
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取 发布时间.
     *
     * @return 发布时间.
     */
    public Date getPublishtime() {
        return publishtime;
    }

    /**
     * 设置 发布时间.
     *
     * @param publishtime 发布时间.
     */
    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    /**
     * 获取 发布范围 1:全网发布 2:灰度发布.
     *
     * @return 发布范围 1:全网发布 2:灰度发布.
     */
    public Integer getPublishRange() {
        return publishRange;
    }

    /**
     * 设置 发布范围 1:全网发布 2:灰度发布.
     *
     * @param publishRange 发布范围 1:全网发布 2:灰度发布.
     */
    public void setPublishRange(Integer publishRange) {
        this.publishRange = publishRange;
    }

    /**
     * 获取 操作者ID.
     *
     * @return 操作者ID.
     */
    public Integer getOperatorId() {
        return operatorId;
    }

    /**
     * 设置 操作者ID.
     *
     * @param operatorId 操作者ID.
     */
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
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
