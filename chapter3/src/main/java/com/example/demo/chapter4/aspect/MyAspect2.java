package com.example.demo.chapter4.aspect;

import com.example.demo.chapter3.pojo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;

/**
 * Created by zhangxiaoyun3 on 2018/10/29.
 */
@Aspect
public class MyAspect2 implements Ordered{
    public static final Log log = LogFactory.getLog(MyAspect2.class);

    @Pointcut("execution(* com.example.demo.chapter4.aspect.service.imple.UserServiceImpl.manyAspect(..))")
    public void pointCut() {

    }

    @Before("pointCut() && args(user)")
    public void before(JoinPoint joinPoint, User user) {
        log.info("before...");
    }

    @After("pointCut()")
    public void after() {
        log.info("after...");
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
