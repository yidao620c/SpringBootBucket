package com.xncoding.jwt.model;

/**
 * WsParam
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/3/22
 */
public class WsParam {
    private String method;
    private Object param;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }
}
