package com.xncoding.benchmark.common;

import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.results.BenchmarkResult;
import org.openjdk.jmh.results.Result;
import org.openjdk.jmh.results.RunResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.xncoding.echarts.common.util.ExportPngUtil.generateOption;
import static com.xncoding.echarts.common.util.ExportPngUtil.postOption;

/**
 * 将基准测试结果导出图片
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/24
 */
public class ResultExporter {

    public static void exportResult(String titleStr, Collection<RunResult> results,
                                    String paramKey, String xunit) throws Exception {
        // 几个测试对象
        List<String> objects = new ArrayList<>();
        // 测试维度，输入值n
        List<String> dimensions = new ArrayList<>();
        // 有几个测试对象，就有几组测试数据，每组测试数据中对应几个维度的结果
        List<List<Double>> allData = new ArrayList<>();
        List<Double> temp = new ArrayList<>();
        for (RunResult runResult : results) {
            BenchmarkResult benchmarkResult = runResult.getAggregatedResult();
            Result r = benchmarkResult.getPrimaryResult();
            BenchmarkParams params = runResult.getParams();
            if (!objects.contains(r.getLabel())) {
                objects.add(r.getLabel());
                if (!temp.isEmpty()) {
                    allData.add(temp);
                    temp = new ArrayList<>();
                }
            }
            temp.add(Double.parseDouble(String.format("%.2f", r.getScore())));
            // 测试维度
            if (!dimensions.contains("n=" + params.getParam(paramKey))) {
                dimensions.add("n=" + params.getParam(paramKey));
            }
        }
        // 最后一组测试数据别忘记加进去了
        allData.add(temp);

        String optionStr = generateOption(titleStr, objects, dimensions, allData, xunit);
        // POST到接口上
        postOption(optionStr, "http://localhost:9075/api/v1/data");
    }
}
