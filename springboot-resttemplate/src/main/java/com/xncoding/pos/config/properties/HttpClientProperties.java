package com.xncoding.pos.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * HttpClient连接池配置
 *
 * @author xiongneng
 * @since 2018/7/09 22:31
 */
@Component
@ConfigurationProperties(prefix = "httpclient")
public class HttpClientProperties {
    /**
     * 建立连接的超时时间
     */
    private int connectTimeout = 20000;
    /**
     * 连接不够用的等待时间
     */
    private int requestTimeout = 20000;
    /**
     * 每次请求等待返回的超时时间
     */
    private int socketTimeout = 30000;
    /**
     * 每个主机最大连接数
     */
    private int defaultMaxPerRoute = 100;
    /**
     * 最大连接数
     */
    private int maxTotalConnections = 300;
    /**
     * 默认连接保持活跃的时间
     */
    private int defaultKeepAliveTimeMillis = 20000;
    /**
     * 空闲连接生的存时间
     */
    private int closeIdleConnectionWaitTimeSecs = 30;

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    public int getMaxTotalConnections() {
        return maxTotalConnections;
    }

    public void setMaxTotalConnections(int maxTotalConnections) {
        this.maxTotalConnections = maxTotalConnections;
    }

    public int getDefaultKeepAliveTimeMillis() {
        return defaultKeepAliveTimeMillis;
    }

    public void setDefaultKeepAliveTimeMillis(int defaultKeepAliveTimeMillis) {
        this.defaultKeepAliveTimeMillis = defaultKeepAliveTimeMillis;
    }

    public int getCloseIdleConnectionWaitTimeSecs() {
        return closeIdleConnectionWaitTimeSecs;
    }

    public void setCloseIdleConnectionWaitTimeSecs(int closeIdleConnectionWaitTimeSecs) {
        this.closeIdleConnectionWaitTimeSecs = closeIdleConnectionWaitTimeSecs;
    }
}
