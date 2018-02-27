package com.xncoding.benchmark.json.util;

import net.sf.json.JSONObject;

/**
 * JsonLibUtil
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/24
 */

public class JsonLibUtil {

    public static String bean2Json(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return (T) JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
    }

}
