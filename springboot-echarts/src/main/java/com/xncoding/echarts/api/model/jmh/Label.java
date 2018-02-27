package com.xncoding.echarts.api.model.jmh;

/**
 * Label
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/9
 */
public class Label {
    private Normal normal;

    public Label() {
    }

    public Label(Normal normal) {
        this.normal = normal;
    }

    public Normal getNormal() {
        return normal;
    }

    public void setNormal(Normal normal) {
        this.normal = normal;
    }
}
