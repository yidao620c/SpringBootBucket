package com.xncoding.echarts.api.model.jmh;

import java.util.List;

/**
 * YAxis
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/9
 */
public class YAxis {
    private String type;
    private boolean inverse;
    private List<String> data;
    private YAxisLabel axisLabel;

    public YAxis() {
    }

    public YAxis(String type, boolean inverse, List<String> data, YAxisLabel axisLabel) {
        this.type = type;
        this.inverse = inverse;
        this.data = data;
        this.axisLabel = axisLabel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isInverse() {
        return inverse;
    }

    public void setInverse(boolean inverse) {
        this.inverse = inverse;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public YAxisLabel getAxisLabel() {
        return axisLabel;
    }

    public void setAxisLabel(YAxisLabel axisLabel) {
        this.axisLabel = axisLabel;
    }
}
