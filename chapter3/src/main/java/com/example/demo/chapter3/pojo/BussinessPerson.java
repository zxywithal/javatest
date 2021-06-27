package com.example.demo.chapter3.pojo;

import com.example.demo.IoCTest;
import com.example.demo.chapter3.pojo.definition.Animal;
import com.example.demo.chapter3.pojo.definition.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by zhangxiaoyun3 on 2018/10/25.
 */
@Component
public class BussinessPerson implements Person,
        BeanNameAware,BeanFactoryAware,ApplicationContextAware,InitializingBean,DisposableBean {
    public static final Log log = LogFactory.getLog(BussinessPerson.class);
    @Autowired
    @Qualifier("dog")
    private Animal animal;
    private String name;
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;

    @Override
    public void service() {
        animal.use();
    }

    @Override
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }


    @Override
    public void setBeanName(String name) {
        this.name = name;
        log.info("开始调用BeanNameAware的setBeanName方法 name"+name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        log.info("开始调用BeanFactoryAware的setBeanFactory方法 beanFactory"+beanFactory);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        log.info("开始调用 ApplicationContextAware 的 setApplicationContext 方法 applicationContext"+applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("开始调用 InitializingBean 的 afterPropertiesSet 方法");

    }

    @Override
    public void destroy() throws Exception {
        log.info("开始调用 DisposableBean 的 destroy 方法");
    }
    @PostConstruct
    public void init(){
        log.info("开始调用 PostConstruct 的 init 方法");
    }
    @PreDestroy
    public void preDestory(){
        log.info("开始调用 PreDestroy 的 preDestory 方法");

    }
}
