package com.example.demo.chapter6.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by zhangxiaoyun3 on 2018/10/29.
 */
@SpringBootApplication
@Configuration
@ComponentScan(value = "com.example.demo.chapter6.*",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = SpringBootApplication.class)})
@PropertySource(value = {"classpath:jdbc.properties","classpath:application.properties"},ignoreResourceNotFound = false)
@MapperScan(basePackages ="com.example.demo.chapter6.dao",
        annotationClass = Repository.class,
        sqlSessionFactoryRef = "sqlSessionFactory",
        sqlSessionTemplateRef = "sqlSessionTemplate")
public class Chapter6Application {
    private Log log = LogFactory.getLog(Chapter6Application.class);
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @PostConstruct
    public void viewTransaction() {
        log.info(platformTransactionManager.getClass().getName());
    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter6Application.class, args);
    }
}
