package com.xncoding.jwt.api.model;

/**
 * 报告参数
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/7
 */
public class ReportParam {
    /**
     * IMEI码
     */
    private String imei;
    /**
     * 位置
     */
    private String location;

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
}
