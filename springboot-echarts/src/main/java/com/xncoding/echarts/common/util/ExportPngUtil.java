package com.xncoding.echarts.common.util;

import com.xncoding.echarts.api.model.jmh.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ExportPngUtil
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/24
 */
public class ExportPngUtil {

    public static void postOption(String optionStr, String url) throws Exception {
        final MediaType TEXT = MediaType.parse("application/text; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(TEXT, optionStr);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public static String generateOption(String titleStr, List<String> objects, List<String> dimensions,
                                  List<List<Double>> allData, String xunit) {
        Option option = new Option();
        // "title"
        Title title = new Title();
        title.setText(titleStr);
        // "tooltip"
        Tooltip tooltip = new Tooltip("axis", new AxisPointer("shadow"));
        // "legend"
        Legend legend = new Legend(objects);
        // "grid"
        Grid grid = new Grid(100);
        // "toolbox"
        Toolbox toolbox = new Toolbox(false, new Feature(new SaveAsImage("png")));
        // "xAxis"
        XAxis xAxis = new XAxis("value", xunit);
        // "yAxis"
        YAxis yAxis = new YAxis("category", false, dimensions, new YAxisLabel(20));
        // "series"
        List<Serie> series = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            Serie serie = new Serie();
            serie.setName(objects.get(i));
            serie.setType("bar");
            serie.setLabel(new Label(new Normal(true, 2)));
            serie.setData(allData.get(i));
            series.add(serie);
        }

        // 开始设置option
        option.setTitle(title);
        option.setTooltip(tooltip);
        option.setLegend(legend);
        option.setGrid(grid);
        option.setToolbox(toolbox);
        option.setxAxis(xAxis);
        option.setyAxis(yAxis);
        option.setSeries(series);

        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(option);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
