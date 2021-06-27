package com.example.demo;

import com.example.demo.chapter3.AppConfig;
import com.example.demo.chapter3.pojo.BussinessPerson;
import com.example.demo.chapter3.pojo.ScopeBean;
import com.example.demo.chapter3.pojo.User;
import com.example.demo.chapter4.main.Chapter4Application;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import java.lang.reflect.Proxy;

/**
 * Created by zhangxiaoyun3 on 2018/9/12.
 */
public class IoCTest {
    public static final Log log = LogFactory.getLog(IoCTest.class);

    public static void main(String[] args) {
//        ConfigurableApplicationContext run = SpringApplication.run(Chapter4Application.class, args);
        ConfigurableApplicationContext run = SpringApplication.run(AppConfig.class, args);
//        BussinessPerson person = run.getBean(BussinessPerson.class);
//        person.service();
//        ScopeBean bean1 = run.getBean(ScopeBean.class);
//        ScopeBean bean2 = run.getBean(ScopeBean.class);
//        System.out.println(bean1==bean2);
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(Chapter4Application.class);
//        User user = (User)ctx.getBean("user");
//        log.info("id "+user.getId());
//        BussinessPerson person = ctx.getBean(BussinessPerson.class);
//        person.service();
//        ((AnnotationConfigApplicationContext)ctx).close();
    }
}
