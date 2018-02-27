package com.xncoding.jwt.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 版本检查结果
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/9
 */
@ApiModel(value = "APP版本检查结果", description = "APP版本检查结果")
public class VersionResult {
    /**
     * 是否发现新版本
     */
    @ApiModelProperty(value = "是否发现新版本(true:发现新版本，false:没有发现新版本)", name = "findNew", example = "true", required = true)
    private boolean findNew;
    /**
     * APP名称
     */
    @ApiModelProperty(value = "APP名称", name = "appName", example = "行政收费")
    private String appName;
    /**
     * 新版本号
     */
    @ApiModelProperty(value = "新版本号", name = "version", example = "v1.3.8")
    private String version;
    /**
     * 新版本说明
     */
    @ApiModelProperty(value = "新版本说明", name = "tips", example = "增加人脸识别功能")
    private String tips;
    /**
     * 新版本发布时间
     */
    @ApiModelProperty(value = "新版本发布时间", name = "publishtime", example = "2017-12-24 12:32:19")
    private Date publishtime;

    /**
     * 新版本下载地址
     */
    @ApiModelProperty(value = "新版本下载地址", name = "downloadUrl", example = "http://xncoding.net/files/行政收费_1.3.0.apk")
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
