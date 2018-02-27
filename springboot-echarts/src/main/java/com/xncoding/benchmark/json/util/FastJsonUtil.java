package com.xncoding.benchmark.json.util;

import com.alibaba.fastjson.JSON;

/**
 * FastJsonUtil
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/24
 */
public class FastJsonUtil {
    public static String bean2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return JSON.parseObject(jsonStr, objClass);
    }
}
