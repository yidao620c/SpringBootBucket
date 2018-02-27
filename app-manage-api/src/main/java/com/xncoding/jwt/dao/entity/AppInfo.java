package com.xncoding.jwt.dao.entity;

import com.xncoding.jwt.common.constant.DictMap;
import com.xncoding.jwt.common.dao.entity.App;

import java.io.Serializable;

/**
 * Description: App信息
 */
public class AppInfo extends App implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 操作者用户名
     */
    private String operatorUsername;
    /**
     * 操作者姓名
     */
    private String operatorName;
    /**
     * 归属项目名称
     */
    private String projectName;

    /**
     * 发布范围显示
     */
    private String publishRangeStr;
    /**
     * APK下载链接
     */
    private String downloadUrl;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOperatorUsername() {
        return operatorUsername;
    }

    public void setOperatorUsername(String operatorUsername) {
        this.operatorUsername = operatorUsername;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getPublishRangeStr() {
        return publishRangeStr;
    }

    public void setPublishRangeStr(String publishRangeStr) {
        this.publishRangeStr = publishRangeStr;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public void buildTable() {
        publishRangeStr = DictMap.value(DictMap.KEY_APP_PUBLISH_RANGE, getPublishRange());
    }
}
