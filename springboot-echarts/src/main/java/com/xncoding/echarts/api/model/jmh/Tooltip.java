package com.xncoding.echarts.api.model.jmh;

/**
 * Tooltip
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/9
 */
public class Tooltip {
    private String trigger;
    private AxisPointer axisPointer;

    public Tooltip() {
    }

    public Tooltip(String trigger, AxisPointer axisPointer) {
        this.trigger = trigger;
        this.axisPointer = axisPointer;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public AxisPointer getAxisPointer() {
        return axisPointer;
    }

    public void setAxisPointer(AxisPointer axisPointer) {
        this.axisPointer = axisPointer;
    }
}
