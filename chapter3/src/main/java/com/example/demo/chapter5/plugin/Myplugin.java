package com.example.demo.chapter5.plugin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by zhangxiaoyun3 on 2018/11/5.
 */
@Intercepts({@Signature(type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class})})
public class Myplugin implements Interceptor {
    private static Log log = LogFactory.getLog(Myplugin.class);
    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("插件链接方法........");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
