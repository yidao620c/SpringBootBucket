package com.xncoding.echarts.api.model.jmh;

/**
 * Normal
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/9
 */
public class Normal {
    private boolean show;
    private Integer textBorderWidth;

    public Normal() {
    }

    public Normal(boolean show, Integer textBorderWidth) {
        this.show = show;
        this.textBorderWidth = textBorderWidth;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public Integer getTextBorderWidth() {
        return textBorderWidth;
    }

    public void setTextBorderWidth(Integer textBorderWidth) {
        this.textBorderWidth = textBorderWidth;
    }
}
