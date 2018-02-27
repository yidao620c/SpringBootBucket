package com.xncoding.pos.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 本项目自定义配置
 *
 * @author xiongneng
 * @since 2018/01/06 21:09
 */
@Component
@ConfigurationProperties(prefix = "xncoding")
public class MyProperties {
    /**
     * excel模板文件路径
     */
    private String excelPath = "";
    /**
     * 文件保存路径
     */
    private String filesPath = "";
    /**
     * 图片保存路径
     */
    private String picsPath = "";
    /**
     * 图片访问URL前缀
     */
    private String picsUrlPrefix = "";
    /**
     * 文件访问URL前缀
     */
    private String filesUrlPrefix = "";
    /**
     * POS API接口前缀
     */
    private String posapiUrlPrefix = "";
    /**
     * 是否验证码
     */
    private Boolean kaptchaOpen = false;
    /**
     * 是否开启Swaggr
     */
    private Boolean swaggerOpen = false;
    /**
     * session 失效时间（默认为30分钟 单位：秒）
     */
    private Integer sessionInvalidateTime = 30 * 60;
    /**
     * session 验证失效时间（默认为15分钟 单位：秒）
     */
    private Integer sessionValidationInterval = 15 * 60;
    /**
     * 机具心跳报告超时时间 单位：分钟
     */
    private Integer heartbeatTimeout;

    public String getExcelPath() {
        return excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
    }

    public String getPicsUrlPrefix() {
        return picsUrlPrefix;
    }

    public void setPicsUrlPrefix(String picsUrlPrefix) {
        this.picsUrlPrefix = picsUrlPrefix;
    }

    public Boolean getKaptchaOpen() {
        return kaptchaOpen;
    }

    public void setKaptchaOpen(Boolean kaptchaOpen) {
        this.kaptchaOpen = kaptchaOpen;
    }

    public Boolean getSwaggerOpen() {
        return swaggerOpen;
    }

    public void setSwaggerOpen(Boolean swaggerOpen) {
        this.swaggerOpen = swaggerOpen;
    }

    public Integer getSessionInvalidateTime() {
        return sessionInvalidateTime;
    }

    public void setSessionInvalidateTime(Integer sessionInvalidateTime) {
        this.sessionInvalidateTime = sessionInvalidateTime;
    }

    public Integer getSessionValidationInterval() {
        return sessionValidationInterval;
    }

    public void setSessionValidationInterval(Integer sessionValidationInterval) {
        this.sessionValidationInterval = sessionValidationInterval;
    }

    public String getFilesUrlPrefix() {
        return filesUrlPrefix;
    }

    public void setFilesUrlPrefix(String filesUrlPrefix) {
        this.filesUrlPrefix = filesUrlPrefix;
    }

    public String getFilesPath() {
        return filesPath;
    }

    public void setFilesPath(String filesPath) {
        this.filesPath = filesPath;
    }

    public Integer getHeartbeatTimeout() {
        return heartbeatTimeout;
    }

    public void setHeartbeatTimeout(Integer heartbeatTimeout) {
        this.heartbeatTimeout = heartbeatTimeout;
    }

    public String getPicsPath() {
        return picsPath;
    }

    public void setPicsPath(String picsPath) {
        this.picsPath = picsPath;
    }

    public String getPosapiUrlPrefix() {
        return posapiUrlPrefix;
    }

    public void setPosapiUrlPrefix(String posapiUrlPrefix) {
        this.posapiUrlPrefix = posapiUrlPrefix;
    }
}
