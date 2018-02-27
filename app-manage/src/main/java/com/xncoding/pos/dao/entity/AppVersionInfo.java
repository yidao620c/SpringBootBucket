package com.xncoding.pos.dao.entity;

import java.util.List;

/**
 * App名字和版本对应类
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/17
 */
public class AppVersionInfo {
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 版本号列表
     */
    private List<String> versions;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<String> getVersions() {
        return versions;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }
}
