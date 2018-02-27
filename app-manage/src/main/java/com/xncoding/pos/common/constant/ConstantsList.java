package com.xncoding.pos.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量列表
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/01/07
 */
public class ConstantsList {
    // 应收款项列表
    public static final List<String> INCOME_TYPE_LIST = new ArrayList<String>(){{
        add("租金");
        add("市场管理费");
        add("物业管理费");
        add( "其他");
    }};

    // 市场列表
    public static final List<String> MARKET_LIST = new ArrayList<String>(){{
        add("一期市场");
        add("二期市场");
        add("三期市场");
    }};

    // 业态
    public static final List<String> BUSINESS_LIST = new ArrayList<String>() {{
        add("男装");
        add("女装");
        add("玩具");
        add("餐饮");
        add("家具");
    }};
}
