package com.xncoding.echarts.api.model.jmh;

import java.util.List;

/**
 * Legend
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/9
 */
public class Legend {
    private List<String> data;

    public Legend() {
    }

    public Legend(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
