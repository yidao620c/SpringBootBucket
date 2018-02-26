package com.enzhico.jwt.common.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 表常量字典
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/01/07
 */
public class DictMap {
    /**
     * 后台管理用户表 - 状态(1:正常 2:禁用)
     */
    public static final String KEY_USER_STATUS = "t_manager.status";
    /**
     * POS机表 - 机具状态(1:正常 2:故障 3:维修中(返厂) 4:已禁用(丢失) 5:已停用(回收))
     */
    public static final String KEY_POS_POS_STATUS = "t_pos.pos_state";
    /**
     * POS机监控表 - 在线状态(1:在线 2:离线)
     */
    public static final String KEY_POS_MONITOR_STATUS = "t_pos_monitor.online_state";
    /**
     * APP表 - 发布范围(1:全网发布 2:灰度发布)
     */
    public static final String KEY_APP_PUBLISH_RANGE = "t_app.publish_range";

    /**
     * 内部用，ClassName + FieldName为key
     */
    private static final Map<String, TreeMap<Integer, String>> _imap = new HashMap<>();

    static {
        _imap.put(KEY_USER_STATUS, new TreeMap<Integer, String>() {{
            put(1, "正常");
            put(2, "禁用");
        }});
        _imap.put(KEY_POS_POS_STATUS, new TreeMap<Integer, String>() {{
            put(1, "正常");
            put(2, "故障");
            put(3, "维修中(返厂)");
            put(4, "已禁用(丢失)");
            put(5, "已停用(回收)");
        }});
        _imap.put(KEY_POS_MONITOR_STATUS, new TreeMap<Integer, String>() {{
            put(1, "在线");
            put(2, "离线");
        }});
        _imap.put(KEY_APP_PUBLISH_RANGE, new TreeMap<Integer, String>() {{
            put(1, "全网发布");
            put(2, "灰度发布");
        }});
    }

    /**
     * 根据字典类型key获取某个字典Map
     *
     * @param type 常量类型
     * @return 字典Map
     */
    public static TreeMap<Integer, String> map(String type) {
        return _imap.get(type);
    }

    /**
     * 根据字典类型和数字获取对应的字符串
     *
     * @param type 字典类型
     * @param key  数字
     * @return 对应的字符串
     */
    public static String value(String type, Integer key) {
        return key != null ? map(type).get(key) : null;
    }

}
