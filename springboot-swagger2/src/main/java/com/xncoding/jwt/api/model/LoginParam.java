package com.xncoding.jwt.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录认证接口参数
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/9
 */
@ApiModel(value = "登录认证接口参数", description = "登录认证接口参数")
public class LoginParam {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username", example = "admin", required = true)
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    private String password;
    /**
     * Application ID
     */
    @ApiModelProperty(value = "Application ID", name = "appid", example = "com.xncoding.xzpay", required = false)
    private String appid;
    /**
     * IMEI码
     */
    @ApiModelProperty(value = "IMEI码", name = "imei", example = "TUYIUOIU234234YUII", required = false)
    private String imei;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
