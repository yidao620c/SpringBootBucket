package com.xncoding.jwt.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

/**
 * API接口的基础返回类
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/7
 */
@ApiModel(value = "BaseResponse", description = "API接口的返回对象")
public class BaseResponse<T> {
    /**
     * 是否成功
     */
    @ApiModelProperty(value = "是否成功", name = "success", example = "true", required = true)
    private boolean success;

    /**
     * 说明
     */
    @ApiModelProperty(value = "返回的详细说明", name = "msg", example = "成功")
    private String msg;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回的数据", name = "data")
    private T data;

    public BaseResponse() {

    }

    public BaseResponse(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
