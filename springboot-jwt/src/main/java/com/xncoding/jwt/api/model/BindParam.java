package com.xncoding.jwt.api.model;

/**
 * POS绑定网点参数
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/7
 */
public class BindParam {
    /**
     * 机具IMEI码
     */
    private String imei;
    /**
     * 归属网点
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
