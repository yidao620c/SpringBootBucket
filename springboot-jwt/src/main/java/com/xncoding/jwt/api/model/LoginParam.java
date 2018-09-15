package com.xncoding.jwt.api.model;

/**
 * 登录认证接口参数
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/9
 */
public class LoginParam {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

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
}
