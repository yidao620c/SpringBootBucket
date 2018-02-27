package com.xncoding.echarts.config.properties;

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
     * 图片保存路径
     */
    private String imageDir;
    /**
     * Phantomjs加载文件
     */
    private String loadJs;
    /**
     * 打开的HTMl文件
     */
    private String indexHtml;

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

    public String getImageDir() {
        return imageDir;
    }

    public void setImageDir(String imageDir) {
        this.imageDir = imageDir;
    }

    public String getLoadJs() {
        return loadJs;
    }

    public void setLoadJs(String loadJs) {
        this.loadJs = loadJs;
    }

    public String getIndexHtml() {
        return indexHtml;
    }

    public void setIndexHtml(String indexHtml) {
        this.indexHtml = indexHtml;
    }
}
