package com.example.demo.chapter3;

import com.example.demo.chapter3.pojo.DataBaseProperties;
import com.example.demo.chapter3.pojo.User;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by zhangxiaoyun3 on 2018/9/12.
 */
@SpringBootApplication
@Configuration
//@ComponentScan(value = "com.example.demo.chapter3.pojo",basePackageClasses ={User.class} )
@ComponentScan(value = "com.example.demo.chapter3.*",excludeFilters =@ComponentScan.Filter(type=FilterType.ANNOTATION,value = Service.class) )
@PropertySource(value = {"classpath:jdbc.properties"},ignoreResourceNotFound = false)
public class AppConfig {

    //    private User user;
//    @Bean(name = "user")
//    public User initUser(){
//        user = new User();
//        user.setId(1L);
//        user.setNote("note_1");
//        user.setUserNmae("user_name_1");
//        return user;
//    }
    @Bean(name = "dataSource",destroyMethod = "close")
    @Conditional(DataBaseProperties.class)
//    @Profile("dev")
    public DataSource getDataSource(
            @Value("database.driverName") String driver,
            @Value("database.url") String url,
            @Value("database.username") String username,
            @Value("database.password") String password) {
        Properties p = new Properties();
        p.setProperty("driver", driver);
        p.setProperty("url", url);
        p.setProperty("username", username);
        p.setProperty("password", password);
        DataSource dataSource = null;
        try{
            dataSource = BasicDataSourceFactory.createDataSource(p);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataSource;
    }
}
