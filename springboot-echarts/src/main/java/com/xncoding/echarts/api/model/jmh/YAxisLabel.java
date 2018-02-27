package com.xncoding.echarts.api.model.jmh;

/**
 * YAxisLabel
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/9
 */
public class YAxisLabel {
    private Integer margin;

    public YAxisLabel() {
    }

    public YAxisLabel(Integer margin) {
        this.margin = margin;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }
}
