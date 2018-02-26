package com.enzhico.pos.common.dao.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * POS机历史归属表
 *
 * @author 熊能
 * @version 1.0
 * @since 2018/01/02
 */
@TableName(value = "t_pos_history")
public class PosHistory extends Model<PosHistory> {

private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**
     * POS机ID
     */
    private Integer posId;
    /**
     * 归属网点
     */
    private String location;
    /**
     * 绑定时间
     */
    private Date bindtime;
    /**
     * 解绑时间
     */
    private Date unbindtime;
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
     * 获取 解绑时间.
     *
     * @return 解绑时间.
     */
    public Date getUnbindtime() {
        return unbindtime;
    }

    /**
     * 设置 解绑时间.
     *
     * @param unbindtime 解绑时间.
     */
    public void setUnbindtime(Date unbindtime) {
        this.unbindtime = unbindtime;
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
