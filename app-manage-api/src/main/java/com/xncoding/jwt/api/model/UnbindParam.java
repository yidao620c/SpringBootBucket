package com.xncoding.jwt.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 解绑通知参数
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/9
 */
@ApiModel(value = "解绑通知参数", description = "解绑通知参数")
public class UnbindParam {
    /**
     * IMEI码
     */
    @ApiModelProperty(value = "IMEI码", name = "imei", example = "TUYIUOIU234234YUII")
    private String imei;
    /**
     * 网点
     */
    @ApiModelProperty(value = "网点", name = "location", example = "广州市天河区交警7区")
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
