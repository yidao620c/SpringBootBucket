package com.xncoding.jwt.common.util;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CommonUtilTest {

    @Test
    public void isNewer() {
        assertThat(CommonUtil.isNewer("1.2.1", "1.2.0"), is(true));
        assertThat(CommonUtil.isNewer("1.2", "1.2.0"), is(false));
        assertThat(CommonUtil.isNewer("2.1.9", "1.2.0"), is(true));
        assertThat(CommonUtil.isNewer("adfa.1.3", "1.2.0"), is(false));
    }

    @Test
    public void testTimestamp() {
        // 1516072088813
        // 1441594722
        System.out.println(System.currentTimeMillis());
    }
}