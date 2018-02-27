package com.xncoding.trans.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.JdbcConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 自定义配置文件
 *
 * @author xiongneng
 * @since 2017-05-21 11:18
 */
@Component
@ConfigurationProperties(prefix = "common")
public class CommonProperties {
    /**
     * csv文件路径，文件名格式为“表名.csv”
     */
    private String csvDir;
    private String csvVtoll;
    private String csvCanton;
    private String csvExeOffice;
    private String csvApp;
    private String csvLog;
    /**
     * csv文件在哪 1:文件系统 2:类路径下面
     */
    private Integer location;

    public String getCsvDir() {
        return csvDir;
    }

    public void setCsvDir(String csvDir) {
        this.csvDir = csvDir;
    }

    public String getCsvExeOffice() {
        return csvExeOffice;
    }

    public void setCsvExeOffice(String csvExeOffice) {
        this.csvExeOffice = csvExeOffice;
    }

    public String getCsvVtoll() {
        return csvVtoll;
    }

    public void setCsvVtoll(String csvVtoll) {
        this.csvVtoll = csvVtoll;
    }

    public String getCsvApp() {
        return csvApp;
    }

    public void setCsvApp(String csvApp) {
        this.csvApp = csvApp;
    }

    public String getCsvLog() {
        return csvLog;
    }

    public void setCsvLog(String csvLog) {
        this.csvLog = csvLog;
    }

    public String getCsvCanton() {
        return csvCanton;
    }

    public void setCsvCanton(String csvCanton) {
        this.csvCanton = csvCanton;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }
}