package com.example.demo.chapter7.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * Created by zhangxiaoyun3 on 2018/10/29.
 */
@SpringBootApplication
@Configuration
@ComponentScan(value = "com.example.demo.chapter7.*",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = SpringBootApplication.class)})
@PropertySource(value = {"classpath:jdbc.properties", "classpath:application.properties","classpath:cache.properties"},
        ignoreResourceNotFound = false)
@MapperScan(basePackages = "com.example.demo.chapter7.dao",
        annotationClass = Repository.class,
        sqlSessionFactoryRef = "sqlSessionFactory",
        sqlSessionTemplateRef = "sqlSessionTemplate")
//开启cache
@EnableCaching
public class Chapter7Application {
    private Log log = LogFactory.getLog(Chapter7Application.class);
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisConnectionFactory connectionFactory;
    @Autowired
    private MessageListener messageListener;

    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Bean
    public ThreadPoolTaskScheduler initThreadPool(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(20);
        return threadPoolTaskScheduler;
    }
    @Bean
    public RedisMessageListenerContainer initListenerContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setTaskExecutor(threadPoolTaskScheduler);
        Topic topic = new ChannelTopic("topic1");
        container.addMessageListener(messageListener,topic);
        return container;
    }

    @PostConstruct
    public void initRedisTemplate() {
        redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
        redisTemplate.setHashKeySerializer(redisTemplate.getStringSerializer());
    }

        public static void main(String[] args) {
        SpringApplication.run(Chapter7Application.class, args);
    }
}
