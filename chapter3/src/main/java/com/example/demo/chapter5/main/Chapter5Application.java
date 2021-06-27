package com.example.demo.chapter5.main;

import com.example.demo.chapter4.aspect.MyAspect;
import com.example.demo.chapter4.aspect.MyAspect1;
import com.example.demo.chapter4.aspect.MyAspect2;
import com.example.demo.chapter4.aspect.MyAspect3;
import com.example.demo.chapter5.dao.MyBatisUserDao;
import com.example.demo.chapter5.plugin.Myplugin;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * Created by zhangxiaoyun3 on 2018/10/29.
 */
@SpringBootApplication
@Configuration
@ComponentScan(value = "com.example.demo.chapter5.*",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = SpringBootApplication.class)})
@PropertySource(value = {"classpath:jdbc.properties","classpath:application.properties"},ignoreResourceNotFound = false)
//定义扫描mapper的方法3 后面两个参数在多数据源的情况下才有用
@MapperScan(basePackages ="com.example.demo.chapter5.dao",annotationClass = Repository.class,sqlSessionFactoryRef = "sqlSessionFactory",sqlSessionTemplateRef = "sqlSessionTemplate")
public class Chapter5Application {

    @Autowired
    SqlSessionFactory sqlSessionFactory = null;

    // 配置扫描的mapper方法1
//    @Bean
//    public MapperFactoryBean<MyBatisUserDao> initMyBatisUserDao(){
//        MapperFactoryBean<MyBatisUserDao> bean = new MapperFactoryBean<>();
//        bean.setMapperInterface(MyBatisUserDao.class);
//        bean.setSqlSessionFactory(sqlSessionFactory);
//        return bean;
//    }

    //扫描mapper的方法2
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer(){
//        MapperScannerConfigurer conf = new MapperScannerConfigurer();
//        conf.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        conf.setBasePackage("com.example.demo.chapter5.dao");
//        conf.setAnnotationClass(Repository.class);
//        return conf;
//    }

    /**
     * 这个没有测试成功
     */
    @PostConstruct
    public void initMyBatis(){
        Myplugin myplugin = new Myplugin();
        Properties properties = new Properties();
        properties.setProperty("key1", "v1");
        myplugin.setProperties(properties);
        sqlSessionFactory.getConfiguration().addInterceptor(myplugin);
    }
    public static void main(String[] args) {
        SpringApplication.run(Chapter5Application.class, args);
    }
}
