package com.xncoding.echarts.common.util;

import com.xncoding.echarts.api.model.jmh.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.xncoding.echarts.common.util.ExportPngUtil.generateOption;
import static com.xncoding.echarts.common.util.ExportPngUtil.postOption;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ApplicationTests {

    @Test
    public void isNewer() {
        assertThat(CommonUtil.isNewer("1.2.1", "1.2.0"), is(true));
        assertThat(CommonUtil.isNewer("1.2", "1.2.0"), is(false));
        assertThat(CommonUtil.isNewer("2.1.9", "1.2.0"), is(true));
        assertThat(CommonUtil.isNewer("adfa.1.3", "1.2.0"), is(false));
    }

    @Test
    public void testTimestamp() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testOption() throws Exception {
        String titleStr = "对象序列化为JSON字符串";
        // 几个测试对象
        List<String> objects = Arrays.asList("FastJson", "Jackson", "Gson", "Json-lib");
        // 测试维度，输入值n
        List<String> dimensions = Arrays.asList("10000次", "100000次", "1000000次");
        // 有几个测试对象，就有几组测试数据，每组测试数据中对应几个维度的结果
        List<List<Double>> allData = new ArrayList<List<Double>>(){{
           add(Arrays.asList(2.17, 9.10, 21.70));
           add(Arrays.asList(1.94, 8.94, 19.43));
           add(Arrays.asList(4.88, 22.88, 48.89));
           add(Arrays.asList(9.11, 58.14, 108.44));
        }};
        String optionStr = generateOption(titleStr, objects, dimensions, allData, "秒");
        // POST到接口上
        postOption(optionStr, "http://localhost:9075/api/v1/data");
    }
}