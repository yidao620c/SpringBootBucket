package com.xncoding.trans.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * RedisCacheConfig
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/2/2
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Bean
    public RedisStandaloneConfiguration getRedisClient() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, Integer.parseInt(port));
        return redisStandaloneConfiguration;
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory(RedisStandaloneConfiguration RedisStandaloneConfiguration) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(RedisStandaloneConfiguration);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(cf);
        return redisTemplate;
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(Duration.ofSeconds(600L));
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory cf) {
        //RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(cf);
        //RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, RedisCacheConfiguration.defaultCacheConfig());
        RedisCacheManager cm = RedisCacheManager.builder(cf).cacheDefaults(redisCacheConfiguration()).build();
        return cm;
    }

    // @Bean
    // public KeyGenerator keyGenerator() {
    //     return new KeyGenerator() {
    //         @Override
    //         public Object generate(Object o, Method method, Object... objects) {
    //             StringBuilder sb = new StringBuilder();
    //             sb.append(o.getClass().getName());
    //             sb.append(method.getName());
    //             for (Object obj : objects) {
    //                 sb.append(obj.toString());
    //             }
    //             return sb.toString();
    //         }
    //     };
    // }

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
