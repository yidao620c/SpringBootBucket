package com.xncoding.jwt.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * APP版本检查接口参数
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/9
 */
@ApiModel(value = "APP版本检查参数", description = "APP版本检查参数")
public class VersionParam {
    /**
     * 机具IMEI码
     */
    @ApiModelProperty(value = "IMEI码", name = "imei", example = "2324DEEFAXX122", required = true)
    private String imei;
    /**
     * 应用ID
     */
    @ApiModelProperty(value = "APP应用ID，每个APP都有唯一的Application Id", name = "applicationId", example = "com.xncoding.xzpay", required = true)
    private String applicationId;
    /**
     * 当前版本号
     */
    @ApiModelProperty(value = "APP的当前版本号", name = "version", example = "1.2.0", required = true)
    private String version;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
