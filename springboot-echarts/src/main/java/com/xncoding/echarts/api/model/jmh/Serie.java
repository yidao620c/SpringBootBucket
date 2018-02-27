package com.xncoding.echarts.api.model.jmh;

import java.util.List;

/**
 * Serie
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/9
 */
public class Serie {
    private String name;
    private String type;
    private List<Double> data;
    private Label label;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
