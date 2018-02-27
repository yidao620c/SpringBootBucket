package com.xncoding.echarts.api.model.jmh;

/**
 * AxisPointer
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/9
 */
public class AxisPointer {
    public AxisPointer() {
    }

    public AxisPointer(String type) {
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
