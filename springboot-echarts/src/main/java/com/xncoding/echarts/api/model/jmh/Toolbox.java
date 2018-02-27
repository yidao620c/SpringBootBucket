package com.xncoding.echarts.api.model.jmh;

/**
 * Toolbox
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/9
 */
public class Toolbox {
    private boolean show;
    private Feature feature;

    public Toolbox() {
    }

    public Toolbox(boolean show, Feature feature) {
        this.show = show;
        this.feature = feature;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
