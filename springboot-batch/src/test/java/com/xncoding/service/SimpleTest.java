package com.xncoding.service;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * SimpleTest
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/5
 */
public class SimpleTest {
    @Test
    public void test() throws Exception {
        System.out.println(Arrays.toString(new String[]{"11", "22"}));
        System.out.println(String.join(",", new String[]{"11", "22"}));

        String d = "08-12月-17 05.38.07.812000 下午";
        Locale locale = Locale.CHINA;
        Date dd = new SimpleDateFormat("dd-M月-y hh.mm.ss.S a", locale).parse(d);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dd));
    }
}
