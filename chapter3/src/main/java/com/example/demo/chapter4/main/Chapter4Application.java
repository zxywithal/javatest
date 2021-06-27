package com.example.demo.chapter4.main;

import com.example.demo.chapter4.aspect.MyAspect;
import com.example.demo.chapter4.aspect.MyAspect1;
import com.example.demo.chapter4.aspect.MyAspect2;
import com.example.demo.chapter4.aspect.MyAspect3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

/**
 * Created by zhangxiaoyun3 on 2018/10/29.
 */
@SpringBootApplication
@Configuration
@ComponentScan(value = "com.example.demo.*",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = SpringBootApplication.class)})
@PropertySource(value = {"classpath:jdbc.properties","classpath:application.properties"},ignoreResourceNotFound = false)
public class Chapter4Application {
    @Bean("myAspect")
    public MyAspect initAspect(){
        return new MyAspect();
    }

    /**
     * 多切面是前面执行的顺序和切面对象初始化的顺序一致
     * @return
     */
    @Bean("myAspect3")
    public MyAspect3 initAspect3(){
        return new MyAspect3();
    }
    @Bean("myAspect1")
    public MyAspect1 initAspect1(){
        return new MyAspect1();
    }
    @Bean("myAspect2")
    public MyAspect2 initAspect2(){
        return new MyAspect2();
    }

    public static void main(String[] args) {
        SpringApplication.run(Chapter4Application.class, args);
    }
}
