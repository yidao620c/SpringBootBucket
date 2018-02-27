package com.xncoding.trans.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * RedisCacheConfig
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/2
 */
@Configuration
@EnableCaching
public class RedisCacheConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 重新配置RedisCacheManager
     */
    @Autowired
    public void configRedisCacheManger(RedisCacheManager rd) {
        rd.setDefaultExpiration(100L);
    }

    /**
     * 自定义缓存key的生成类实现
     */
    @Bean(name = "myKeyGenerator")
    public KeyGenerator myKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... params) {
                logger.info("自定义缓存，使用第一参数作为缓存key，params = " + Arrays.toString(params));
                // 仅仅用于测试，实际不可能这么写
                return params[0];
            }
        };
    }
}
