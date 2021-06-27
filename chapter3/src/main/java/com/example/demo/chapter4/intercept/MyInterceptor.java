package com.example.demo.chapter4.intercept;

import com.example.demo.chapter4.invoke.Invocation;
import com.example.demo.chapter4.service.impl.SayHelloImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by zhangxiaoyun3 on 2018/10/26.
 */
public class MyInterceptor implements Inteceptor {
    public static final Log log = LogFactory.getLog(MyInterceptor.class);
    @Override
    public boolean before() {
        log.info("before method");
        return true;
    }

    @Override
    public void after() {
        log.info("after method");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        log.info("around before");
        return invocation.proceed();
    }

    @Override
    public void afterReturning() {
        log.info("afterReturning method");
    }

    @Override
    public void afterThrowing() {
        log.info("afterThrowing method");
    }

    @Override
    public boolean useAround() {
        return true;
    }
}
