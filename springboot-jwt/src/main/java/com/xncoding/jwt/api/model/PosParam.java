package com.xncoding.jwt.api.model;

/**
 * POS入网参数
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/7
 */
public class PosParam {
    /**
     * 机具IMEI码
     */
    private String imei;
    /**
     * 序列号(SN)
     */
    private String sn;
    /**
     * 机具型号
     */
    private String series;
    /**
     * Android版本
     */
    private String androidVersion;
    /**
     * 版本号
     */
    private String version;
    /**
     * 归属网点
     */
    private String location;
    /**
     * 产权方
     */
    private String owner;
    /**
     * 备注
     */
    private String tips;
    /**
     * 应用ID
     */
    private String applicationId;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
