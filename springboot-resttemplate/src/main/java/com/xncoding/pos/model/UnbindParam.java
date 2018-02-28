package com.xncoding.pos.model;

/**
 * 解绑通知参数
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/9
 */
public class UnbindParam {
    /**
     * IMEI码
     */
    private String imei;
    /**
     * 网点
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
