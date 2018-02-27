package com.xncoding.echarts.api.model;

/**
 * PicRequest
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/8
 */
public class PicRequest {
    /**
     * Base64格式的图片
     */
    private String picInfo;

    public String getPicInfo() {
        return picInfo;
    }

    public void setPicInfo(String picInfo) {
        this.picInfo = picInfo;
    }
}
