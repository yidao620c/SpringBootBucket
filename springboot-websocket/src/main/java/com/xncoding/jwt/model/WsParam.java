package com.xncoding.jwt.model;

/**
 * WsParam
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/3/22
 */
public class WsParam<T> {
    private String method;
    private T param;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
