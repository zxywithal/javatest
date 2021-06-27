package com.example.demo.chapter3.pojo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * 每个bean初始化前和后都会执行
 */
//@Component
public class BeanPostProcessExample implements BeanPostProcessor {

    public static final Log log = LogFactory.getLog(BeanPostProcessExample.class);

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("开始调用 BeanPostProcessor 的 postProcessBeforeInitialization 方法 bean:"+bean+" beanName:"+beanName);
        return null;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("开始调用 BeanPostProcessor 的 postProcessAfterInitialization 方法 bean:"+bean+" beanName:"+beanName);
        return null;
    }
}
