package com.xncoding.pos.dao.entity;

import java.util.Date;
import java.util.List;

/**
 * 查询APP列表参数
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/8
 */
public class SearchApp {
    private Integer pageNumber;
    private Integer pageSize;

    private String appName;
    private String appVersion;
    private Integer projectId;
    private Integer publishRange;
    private String publishtimeRange;
    private Date publishTimeStart;
    private Date publishTimeEnd;

    private List<Integer> pidList;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getPublishRange() {
        return publishRange;
    }

    public void setPublishRange(Integer publishRange) {
        this.publishRange = publishRange;
    }

    public Date getPublishTimeStart() {
        return publishTimeStart;
    }

    public void setPublishTimeStart(Date publishTimeStart) {
        this.publishTimeStart = publishTimeStart;
    }

    public Date getPublishTimeEnd() {
        return publishTimeEnd;
    }

    public void setPublishTimeEnd(Date publishTimeEnd) {
        this.publishTimeEnd = publishTimeEnd;
    }

    public String getPublishtimeRange() {
        return publishtimeRange;
    }

    public void setPublishtimeRange(String publishtimeRange) {
        this.publishtimeRange = publishtimeRange;
    }

    public List<Integer> getPidList() {
        return pidList;
    }

    public void setPidList(List<Integer> pidList) {
        this.pidList = pidList;
    }
}
