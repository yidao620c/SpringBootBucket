package com.xncoding.echarts.api.model.jmh;

/**
 * XAxis
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/9
 */
public class XAxis {
    private String type;
    private String name;

    public XAxis() {
    }

    public XAxis(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
