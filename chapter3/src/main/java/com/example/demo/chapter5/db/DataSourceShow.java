package com.example.demo.chapter5.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by zhangxiaoyun3 on 2018/10/31.
 */
@Component
public class DataSourceShow implements ApplicationContextAware {

    private static Log log = LogFactory.getLog(DataSourceShow.class);

    ApplicationContext applicationContext = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        DataSource bean = applicationContext.getBean(DataSource.class);
        log.info("================================");
        log.info(bean.getClass().getName());
        log.info("================================");
    }
}
