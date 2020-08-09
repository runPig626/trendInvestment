package com.oyhp.trend.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * redis缓存配置
 * @author OuYangHaiPing<ouyanghaiping @ pvc123.com>
 * @date 2020-08-08
 */
@Configuration
@EnableCaching
public class RedisCacheConfig {
    /**
     * 创建redisConnectionFactory
     * @return
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        /*
            设置相关配置
         */
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setPort(6379);
        configuration.setHostName("localhost");
        configuration.setDatabase(0);
        JedisConnectionFactory jedisConnectionFactory =
                new JedisConnectionFactory(configuration);
        return jedisConnectionFactory;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return stringRedisTemplate;
    }


    @Bean
    public RedisCacheManager cacheManager() {
        /*
            配置json序列化方式
         */
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().
                serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(redisConnectionFactory()).
                transactionAware().cacheDefaults(cacheConfiguration).build();
    }

}
