package com.xncoding.echarts.api.model.jmh;

/**
 * Feature
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/9
 */
public class Feature {
    private SaveAsImage saveAsImage;

    public Feature() {
    }

    public Feature(SaveAsImage saveAsImage) {
        this.saveAsImage = saveAsImage;
    }

    public SaveAsImage getSaveAsImage() {
        return saveAsImage;
    }

    public void setSaveAsImage(SaveAsImage saveAsImage) {
        this.saveAsImage = saveAsImage;
    }
}
