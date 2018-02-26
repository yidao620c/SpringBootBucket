package com.enzhico.pos.dao.entity;

import java.util.Date;
import java.util.List;

/**
 * SearchUser
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/8
 */
public class SearchPos {
    private Integer pageNumber;
    private Integer pageSize;
    private String imei;
    private String location;
    private Integer projectId;

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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public List<Integer> getPidList() {
        return pidList;
    }

    public void setPidList(List<Integer> pidList) {
        this.pidList = pidList;
    }
}
