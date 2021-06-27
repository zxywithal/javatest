package com.example.demo.chapter7.main;

import com.example.demo.chapter7.config.RedisConfig;
import org.aspectj.weaver.ast.Var;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.lang.Nullable;

/**
 * Created by zhangxiaoyun3 on 2018/11/6.
 */
public class Chaper7Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set("key2  ","value1");
        redisTemplate.opsForHash().put("hash","field","hvalue");
        BoundGeoOperations geo = redisTemplate.boundGeoOps("geo");
        redisTemplate.boundHashOps("hash");
        redisTemplate.boundListOps("list");
        redisTemplate.boundSetOps("set");
        redisTemplate.boundZSetOps("zset");
        redisTemplate.boundValueOps("key2");
        redisTemplate.execute(new SessionCallback() {
            @Nullable
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.opsForValue().set("SessionCallback","SessionCallback");
                operations.opsForHash().put("SessionCallback_hash","SessionCallback","SessionCallback");
                return null;
            }
        });
    }
}
