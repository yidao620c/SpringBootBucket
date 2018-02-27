package com.xncoding.trans.modules.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * DateUtil
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/5
 */
public class DateUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-M月-y hh.mm.ss.S a", Locale.CHINA);
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M月 -y hh.mm.ss.S a", Locale.CHINA);

    public static synchronized Date parseDatetime(String dateStr) {
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static synchronized Timestamp parseTimestamp(String dateStr) {
        try {
            return new Timestamp(sdf.parse(dateStr).getTime());
        } catch (ParseException e) {
            try {
                return new Timestamp(sdf2.parse(dateStr).getTime());
            } catch (ParseException ee) {
                return new Timestamp(System.currentTimeMillis());
            }
        }
    }

    public static synchronized String formatTimestamp(Timestamp date) {
        return sdf.format(date);
    }

    public static void main(String[] args) {
        Timestamp t = parseTimestamp("08-12月-17 05.38.07.859000 下午");
        System.out.println(t);
        System.out.println(formatTimestamp(t));
    }
}
