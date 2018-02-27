package com.xncoding.jwt.config.properties;

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
     * socket端口
     */
    private Integer socketPort;
    /**
     * Ping消息间隔（毫秒）
     */
    private Integer pingInterval;
    /**
     * Ping消息超时时间（毫秒）
     */
    private Integer pingTimeout;
    /**
     * APK文件访问URL前缀
     */
    private String apkUrlPrefix;

    public Integer getSocketPort() {
        return socketPort;
    }

    public void setSocketPort(Integer socketPort) {
        this.socketPort = socketPort;
    }

    public Integer getPingInterval() {
        return pingInterval;
    }

    public void setPingInterval(Integer pingInterval) {
        this.pingInterval = pingInterval;
    }

    public Integer getPingTimeout() {
        return pingTimeout;
    }

    public void setPingTimeout(Integer pingTimeout) {
        this.pingTimeout = pingTimeout;
    }

    public String getApkUrlPrefix() {
        return apkUrlPrefix;
    }

    public void setApkUrlPrefix(String apkUrlPrefix) {
        this.apkUrlPrefix = apkUrlPrefix;
    }
}
