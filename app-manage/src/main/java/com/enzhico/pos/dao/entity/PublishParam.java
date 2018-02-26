package com.enzhico.pos.dao.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * APP版本发布参数
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/10
 */
public class PublishParam {
    private String name;
    private String version;
    private String applicationId;
    private String tips;
    private Integer publishRange;
    private String grayIds;
    private MultipartFile file;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getPublishRange() {
        return publishRange;
    }

    public void setPublishRange(Integer publishRange) {
        this.publishRange = publishRange;
    }

    public String getGrayIds() {
        return grayIds;
    }

    public void setGrayIds(String grayIds) {
        this.grayIds = grayIds;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
