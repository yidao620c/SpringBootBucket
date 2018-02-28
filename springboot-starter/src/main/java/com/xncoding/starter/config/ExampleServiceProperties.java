package com.xncoding.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ExampleServiceProperties
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/28
 */
@ConfigurationProperties("example.service")
public class ExampleServiceProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
