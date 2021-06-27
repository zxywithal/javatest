package com.example.demo.chapter7.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhangxiaoyun3 on 2018/11/10.
 */
@Controller
@RequestMapping("/redis")
public class RedisController {
    private static Log log = LogFactory.getLog(RedisController.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("/testStringAndHash")
    @ResponseBody
    public Map testStringAndHash(){
        redisTemplate.opsForValue().set("key1","value1");
        redisTemplate.opsForValue().set("int_key","1");
        stringRedisTemplate.opsForValue().set("int", "1");
        //加1
        stringRedisTemplate.opsForValue().increment("int", 10);
        Jedis jedis = (Jedis)stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.decr("int");

        Map<String, String> map = new HashMap<>();
        map.put("field1", "field1");
        map.put("field2", "field2");
        stringRedisTemplate.opsForHash().putAll("hash", map);
        stringRedisTemplate.opsForHash().put("hash","field3","field3");
        BoundHashOperations<String, Object, Object> hashbound = stringRedisTemplate.boundHashOps("hash");
        hashbound.delete("field1");
        hashbound.delete("field2");
        hashbound.put("field4", "field4");
        return map;
    }
    @RequestMapping("/list")
    @ResponseBody
    private Map testList() {

        stringRedisTemplate.opsForList().leftPushAll("list1", "v2", "v4", "v6", "v8", "v10");
        stringRedisTemplate.opsForList().rightPushAll("list2", "v1","v2", "v3", "v4", "v5", "v6");

        BoundListOperations<String, String> list2 = stringRedisTemplate.boundListOps("list2");
        String s = list2.rightPop();
        log.info("list2.rightPop "+s);
        String index = list2.index(1);
        log.info("list2.index "+index);
        list2.leftPush("v0");
        log.info("list2 size "+list2.size());
        List<String> range = list2.range(0, list2.size() - 2);
        log.info("range "+range);

        Map<String, String> map = new HashMap<>();
        map.put("succ","true");
        return map;
    }
    @RequestMapping("/set")
    @ResponseBody
    private Map testSet(){
        stringRedisTemplate.opsForSet().add("set1","v1","v1","v2", "v3", "v6", "v8", "v10");
        stringRedisTemplate.opsForSet().add("set2","v1","v3","v5", "v7", "v9", "v10");

        BoundSetOperations<String, String> set2 = stringRedisTemplate.boundSetOps("set2");

        set2.add("v100");
        log.info("set2 members "+set2.members());
        set2.diffAndStore("set1","diff");
        set2.intersectAndStore("set1", "inter");
        set2.unionAndStore("set1","union");

        Map<String, String> map = new HashMap<>();
        map.put("succ","true");
        return map;
    }
    @ResponseBody
    @RequestMapping("/zset")
    public void testZset(){
        Set<TypedTuple<String>> sets = new HashSet<>();

        for(int i=0;i<10;i++){
            TypedTuple<String> typedTuple = new DefaultTypedTuple<String>("value"+i,i*0.1);
            sets.add(typedTuple);
        }
        stringRedisTemplate.opsForZSet().add("zset",sets);
        BoundZSetOperations<String, String> zset = stringRedisTemplate.boundZSetOps("zset");
        zset.add("value10",0.26);
        Set<String> range = zset.range(1, 6);
        log.info("range "+range);
        Set<String> rangeSource = zset.rangeByScore(0.2, 0.6);
        log.info("rangeSource "+rangeSource);
        Range range1 = new Range();
        range1.gt("value3");//大于等于value3
        range1.lte("value8");//小于等于value8
        Set<String> rangeByRange = zset.rangeByLex(range1);
        log.info("rangeByRange "+rangeByRange);
        Set<TypedTuple<String>> typedTuples = zset.rangeByScoreWithScores(1, 6);
        log.info("typedTuples "+typedTuples);
        Set<TypedTuple<String>> typedTuples1 = zset.rangeWithScores(1, 6);
        log.info("typedTuples1 "+typedTuples1);
    }

    /**
     * <pre>
     *     1.watch 监听某个键
     *     2.multi 开启事务
     * </pre>
     * redis 事务
     *
     * @return
     */
    @RequestMapping("/multi")
    @ResponseBody
    public Map testMulti(){
        stringRedisTemplate.opsForValue().set("watch","watch");
        stringRedisTemplate.execute(new SessionCallback<Object>() {

            @Nullable
            @Override
            public   Object execute(RedisOperations operations) throws DataAccessException {
                operations.watch("watch");
                operations.multi();
                operations.opsForValue().set("watch3","watch3");
                //这句执行的时候报错，但是对redis事务没有任何影响，所以redis事务中一定要注意数据类型
//                operations.opsForValue().increment("watch",1);
                operations.opsForValue().set("watch4", "watch4");
                Object watch3 = operations.opsForValue().get("watch3");
                log.info("watch3 "+watch3);
                //如果在执行之前手动的修改wathc键的值则整个事务会回退
                return operations.exec();
            }
        });
        Map<String, String> map = new HashMap<>();
        map.put("succ","true");
        return map;
    }


    /**
     * 测试流水线 redis的批量操作
     * 同样的10w次读写，pipelined需要1528 毫秒 非pipelined需要15770 流水线的效率是单次操作的10倍
     * @return
     */
    @RequestMapping("/pipeline")
    @ResponseBody
    public Map testPipeLine(){
        long start = System.currentTimeMillis();
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {

            @Nullable
            @Override
            public   Object execute(RedisOperations operations) throws DataAccessException {
                for(int i=0;i<100000;i++){
                    operations.opsForValue().set("noPipelined"+i,"value_"+i);
                    Object o = operations.opsForValue().get("noPipelined" + i);
                }
                return null;
            }
        });
        log.info("耗时 "+(System.currentTimeMillis()-start)+"");
        Map<String, String> map = new HashMap<>();
        map.put("succ","true");
        return map;
    }
}
