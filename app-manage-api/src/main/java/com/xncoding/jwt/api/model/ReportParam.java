package com.xncoding.jwt.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 报告参数
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/7
 */
@ApiModel(value = "报告参数", description = "报告参数")
public class ReportParam {
    /**
     * IMEI码
     */
    @ApiModelProperty(value = "IMEI码（长度1-32位）", name = "imei", example = "2324DEEFAXX122", required = true)
    private String imei;
    /**
     * 位置
     */
    @ApiModelProperty(value = "位置（长度1-255位）", name = "location", example = "广东省广州市天河区五山路321号", required = true)
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
