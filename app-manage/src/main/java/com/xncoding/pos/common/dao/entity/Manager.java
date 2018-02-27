package com.xncoding.pos.common.dao.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * 后台管理用户表
 *
 * @author 熊能
 * @version 1.0
 * @since 2018/01/02
 */
@TableName(value = "t_manager")
public class Manager extends Model<Manager> {

private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    /**
     * 账号
     */
    private String username;
    /**
     * 名字
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * md5密码盐
     */
    private String salt;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 备注
     */
    private String tips;
    /**
     * 状态 1:正常 2:禁用
     */
    private Integer state;
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
     * 获取 账号.
     *
     * @return 账号.
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置 账号.
     *
     * @param username 账号.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取 名字.
     *
     * @return 名字.
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 名字.
     *
     * @param name 名字.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 密码.
     *
     * @return 密码.
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 密码.
     *
     * @param password 密码.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取 md5密码盐.
     *
     * @return md5密码盐.
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置 md5密码盐.
     *
     * @param salt md5密码盐.
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取 联系电话.
     *
     * @return 联系电话.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置 联系电话.
     *
     * @param phone 联系电话.
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * 获取 状态 1:正常 2:禁用.
     *
     * @return 状态 1:正常 2:禁用.
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置 状态 1:正常 2:禁用.
     *
     * @param state 状态 1:正常 2:禁用.
     */
    public void setState(Integer state) {
        this.state = state;
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
