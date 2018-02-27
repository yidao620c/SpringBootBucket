package com.xncoding.echarts.api.model;

/**
 * EchartsData
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/8
 */
public class EchartsData {
    /**
     * 图表类型
     */
    private String msgType;
    /**
     * 图表数据
     */
    private String option;

    public EchartsData() {
    }

    public EchartsData(String msgType, String option) {
        this.msgType = msgType;
        this.option = option;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
