package com.example.demo.chapter7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by zhangxiaoyun3 on 2018/11/6.
 */
@Configuration
public class RedisConfig {
    private RedisConnectionFactory connectionFactory = null;

    @Bean("RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory(){
        if (connectionFactory != null) {
            return connectionFactory;
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(30);
        jedisPoolConfig.setMaxIdle(50);
        jedisPoolConfig.setMaxWaitMillis(2000);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        RedisStandaloneConfiguration standaloneConfiguration = jedisConnectionFactory.getStandaloneConfiguration();
        standaloneConfiguration.setHostName("127.0.0.1");
        standaloneConfiguration.setPort(6379);
        return jedisConnectionFactory;
    }
    @Bean("redisTemplate")
    public RedisTemplate<Object,Object> initRedisTemplate(){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
        redisTemplate.setHashKeySerializer(redisTemplate.getStringSerializer());
        redisTemplate.setHashValueSerializer(redisTemplate.getStringSerializer());
        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return redisTemplate;
    }
}
