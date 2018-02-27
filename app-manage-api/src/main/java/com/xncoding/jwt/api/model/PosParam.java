package com.xncoding.jwt.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * POS入网参数
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/7
 */
@ApiModel(value = "POS入网参数", description = "POS入网请求时候需要传入的参数")
public class PosParam {
    /**
     * 机具IMEI码
     */
    @ApiModelProperty(value = "IMEI码（长度1-32位）", name = "imei", dataType = "String", example = "2324DEEFAXX122", required = true)
    private String imei;
    /**
     * 序列号(SN)
     */
    @ApiModelProperty(value = "序列号SN（长度1-64位）", name = "sn", dataType = "String", example = "ssssssnnnn", required = true)
    private String sn;
    /**
     * 机具型号
     */
    @ApiModelProperty(value = "机具型号（长度1-32位）", name = "series", dataType = "String", example = "APOS A8", required = true)
    private String series;
    /**
     * Android版本
     */
    @ApiModelProperty(value = "Android版本（长度1-32位）", name = "androidVersion", dataType = "String", example = "6.2.0", required = true)
    private String androidVersion;
    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号（长度1-32位）", name = "version", dataType = "String", example = "B0182_C2BOM_V1.1.4_20171213/4.0.28", required = true)
    private String version;
    /**
     * 归属网点
     */
    @ApiModelProperty(value = "归属网点（长度1-64位）", name = "location", dataType = "String", example = "昆明市公安局交通警察支队车辆管理所", required = true)
    private String location;
    /**
     * 产权方
     */
    @ApiModelProperty(value = "机具产权方（长度1-64位）", name = "owner", dataType = "String", example = "招商银行昆明分行", required = true)
    private String owner;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注说明（长度0-255位）", name = "tips", dataType = "String", example = "备注说明")
    private String tips;
    /**
     * 应用ID
     */
    @ApiModelProperty(value = "APP应用ID（长度1-64位），每个APP都有唯一的Application Id，并且这个应用ID必须已经存在于系统之中",
            name = "applicationId", dataType = "String", example = "com.xncoding.xzpay", required = true)
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
