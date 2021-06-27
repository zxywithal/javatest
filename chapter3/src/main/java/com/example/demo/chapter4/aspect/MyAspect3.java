package com.example.demo.chapter4.aspect;

import com.example.demo.chapter3.pojo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

/**
 * Created by zhangxiaoyun3 on 2mj7  018/10/29.
 */
@Aspect
@Order(3)
public class MyAspect3 {
    public static final Log log = LogFactory.getLog(MyAspect3.class);

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

}
