package com.xncoding.pos.common.dao.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * 操作日志表
 *
 * @author 熊能
 * @version 1.0
 * @since 2018/01/02
 */
@TableName(value = "t_operation_log")
public class OperationLog extends Model<OperationLog> {

private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**
     * 操作者ID
     */
    private Integer operatorId;
    /**
     * 操作对象ID
     */
    private Integer targetId;
    /**
     * 操作对象名称
     */
    private String targetName;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 备注
     */
    private String tips;
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
     * 获取 操作对象ID.
     *
     * @return 操作对象ID.
     */
    public Integer getTargetId() {
        return targetId;
    }

    /**
     * 设置 操作对象ID.
     *
     * @param targetId 操作对象ID.
     */
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    /**
     * 获取 操作对象名称.
     *
     * @return 操作对象名称.
     */
    public String getTargetName() {
        return targetName;
    }

    /**
     * 设置 操作对象名称.
     *
     * @param targetName 操作对象名称.
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    /**
     * 获取 操作类型.
     *
     * @return 操作类型.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 操作类型.
     *
     * @param operateType 操作类型.
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
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
