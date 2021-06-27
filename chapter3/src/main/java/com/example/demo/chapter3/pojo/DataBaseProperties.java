package com.example.demo.chapter3.pojo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by zhangxiaoyun3 on 2018/10/25.
 */
@Component
@ConfigurationProperties("database")
public class DataBaseProperties implements Condition{
    public static final Log log = LogFactory.getLog(DataBaseProperties.class);
//    @Value("${database.driverName}")
    private String driverName;
//    @Value("${database.url}")
    private String url;
//    @Value("${database.username}")
    private String username;
//    @Value("${database.password}")
    private String password;

    public void setDriverName(String driverName) {
        log.info("setDriverName");
        this.driverName = driverName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @PostConstruct
    public void init(){
        log.info("driverName:"+driverName);
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        return environment.containsProperty("database.driverName");
    }
}
