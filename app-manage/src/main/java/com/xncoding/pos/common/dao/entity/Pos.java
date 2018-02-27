package com.xncoding.pos.common.dao.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * POS机表
 *
 * @author 熊能
 * @version 1.0
 * @since 2018/01/02
 */
@TableName(value = "t_pos")
public class Pos extends Model<Pos> {

private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**
     * 机具IMEI码
     */
    private String imei;
    /**
     * 序列号(SN)
     */
    private String sn;
    /**
     * 机具型号
     */
    private String series;
    /**
     * Android版本
     */
    private String androidVersion;
    /**
     * 版本号
     */
    private String version;
    /**
     * 归属网点
     */
    private String location;
    /**
     * 归属项目ID
     */
    private Integer projectId;
    /**
     * 入网时间
     */
    private Date jointime;
    /**
     * 绑定时间
     */
    private Date bindtime;
    /**
     * 产权方
     */
    private String owner;
    /**
     * 备注
     */
    private String tips;
    /**
     * 机具状态: 1:正常 2:故障 3:维修中(返厂) 4:已禁用(丢失) 5:已停用(回收)
     */
    private Integer posState;
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
     * 获取 机具IMEI码.
     *
     * @return 机具IMEI码.
     */
    public String getImei() {
        return imei;
    }

    /**
     * 设置 机具IMEI码.
     *
     * @param imei 机具IMEI码.
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * 获取 序列号(SN).
     *
     * @return 序列号(SN).
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置 序列号(SN).
     *
     * @param sn 序列号(SN).
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * 获取 机具型号.
     *
     * @return 机具型号.
     */
    public String getSeries() {
        return series;
    }

    /**
     * 设置 机具型号.
     *
     * @param series 机具型号.
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * 获取 Android版本.
     *
     * @return Android版本.
     */
    public String getAndroidVersion() {
        return androidVersion;
    }

    /**
     * 设置 Android版本.
     *
     * @param androidVersion Android版本.
     */
    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
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
     * 获取 归属网点.
     *
     * @return 归属网点.
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置 归属网点.
     *
     * @param location 归属网点.
     */
    public void setLocation(String location) {
        this.location = location;
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
     * 获取 入网时间.
     *
     * @return 入网时间.
     */
    public Date getJointime() {
        return jointime;
    }

    /**
     * 设置 入网时间.
     *
     * @param jointime 入网时间.
     */
    public void setJointime(Date jointime) {
        this.jointime = jointime;
    }

    /**
     * 获取 绑定时间.
     *
     * @return 绑定时间.
     */
    public Date getBindtime() {
        return bindtime;
    }

    /**
     * 设置 绑定时间.
     *
     * @param bindtime 绑定时间.
     */
    public void setBindtime(Date bindtime) {
        this.bindtime = bindtime;
    }

    /**
     * 获取 产权方.
     *
     * @return 产权方.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 设置 产权方.
     *
     * @param owner 产权方.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * 获取 备注.
     *
     * @return 备注.
     */
    public String getTips() {
        return tips;
    }

    /**
     * 设置 备注.
     *
     * @param tips 备注.
     */
    public void setTips(String tips) {
        this.tips = tips;
    }

    /**
     * 获取 机具状态: 1:正常 2:故障 3:维修中(返厂) 4:已禁用(丢失) 5:已停用(回收).
     *
     * @return 机具状态: 1:正常 2:故障 3:维修中(返厂) 4:已禁用(丢失) 5:已停用(回收).
     */
    public Integer getPosState() {
        return posState;
    }

    /**
     * 设置 机具状态: 1:正常 2:故障 3:维修中(返厂) 4:已禁用(丢失) 5:已停用(回收).
     *
     * @param posState 机具状态: 1:正常 2:故障 3:维修中(返厂) 4:已禁用(丢失) 5:已停用(回收).
     */
    public void setPosState(Integer posState) {
        this.posState = posState;
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
