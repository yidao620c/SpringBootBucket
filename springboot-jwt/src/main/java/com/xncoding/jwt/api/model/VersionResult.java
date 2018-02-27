package com.xncoding.jwt.api.model;

import java.util.Date;

/**
 * 版本检查结果
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/9
 */
public class VersionResult {
    /**
     * 是否发现新版本
     */
    private boolean findNew;
    /**
     * APP名称
     */
    private String appName;
    /**
     * 新版本号
     */
    private String version;
    /**
     * 新版本说明
     */
    private String tips;
    /**
     * 新版本发布时间
     */
    private Date publishtime;
    /**
     * 新版本下载地址
     */
    private String downloadUrl;

    public boolean isFindNew() {
        return findNew;
    }

    public void setFindNew(boolean findNew) {
        this.findNew = findNew;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }
}
