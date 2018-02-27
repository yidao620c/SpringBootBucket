package com.xncoding.trans.modules;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.xncoding.trans.modules.common.DateUtil;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.validation.DataBinder;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * MyBeanWrapperFieldSetMapper
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/5
 */
public class MyBeanWrapperFieldSetMapper<T> extends BeanWrapperFieldSetMapper<T> {
    @Override
    protected void initBinder(DataBinder binder) {
        binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (StringUtils.isNotEmpty(text)) {
                    setValue(DateUtil.parseTimestamp(text));
                } else {
                    setValue(null);
                }
            }

            @Override
            public String getAsText() throws IllegalArgumentException {
                Object date = getValue();
                if (date != null) {
                    return DateUtil.formatTimestamp((Timestamp) date);
                } else {
                    return "";
                }
            }
        });
    }
}
