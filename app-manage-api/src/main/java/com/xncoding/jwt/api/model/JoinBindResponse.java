package com.xncoding.jwt.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 入网绑定返回
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/16
 */
@ApiModel(value = "入网绑定返回信息", description = "入网绑定的返回对象")
public class JoinBindResponse<T> extends BaseResponse<T> {
    /**
     * 返回码 1:已入网并绑定了网点 2:已入网但尚未绑定网点 3:未入网
     */
    @ApiModelProperty(value = "返回码 1:已入网并绑定了网点 2:已入网但尚未绑定网点 3:未入网", name = "code", example = "1", required = true)
    private int code;

    /**
     * 机具状态: 1:正常 2:故障 3:维修中(返厂) 4:已禁用(丢失) 5:已停用(回收)
     */
    @ApiModelProperty(value = "机具状态 1:正常 2:故障 3:维修中(返厂) 4:已禁用(丢失) 5:已停用(回收)", name = "posState", example = "1", required = true)
    private Integer posState;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Integer getPosState() {
        return posState;
    }

    public void setPosState(Integer posState) {
        this.posState = posState;
    }
}
