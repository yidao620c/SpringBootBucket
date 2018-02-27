package com.xncoding.trans.modules.common.anno;

import java.lang.annotation.*;

/**
 * 表名注解
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/6
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface TableName {
    /**
     * 表名
     */
    String value();
}
