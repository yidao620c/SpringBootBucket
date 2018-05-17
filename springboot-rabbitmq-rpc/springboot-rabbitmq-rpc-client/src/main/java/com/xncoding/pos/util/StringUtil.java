package com.xncoding.pos.util;

import java.util.UUID;

/**
 * StringUtil
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/5/17
 */
public class StringUtil {
    public static String generateUUId() {
        return UUID.randomUUID().toString();
    }
}
