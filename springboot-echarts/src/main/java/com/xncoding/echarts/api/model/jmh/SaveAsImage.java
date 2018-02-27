package com.xncoding.echarts.api.model.jmh;

/**
 * SaveAsImage
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/10
 */
public class SaveAsImage {
    private String type;

    public SaveAsImage() {
    }

    public SaveAsImage(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
