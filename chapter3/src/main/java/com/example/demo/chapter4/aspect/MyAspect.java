package com.example.demo.chapter4.aspect;

import com.example.demo.chapter3.pojo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by zhangxiaoyun3 on 2018/10/29.
 */
@Aspect
public class MyAspect {
    public static final Log log = LogFactory.getLog(MyAspect.class);

    @Pointcut("execution(* com.example.demo.chapter4.aspect.service.imple.UserServiceImpl.printUser(..))")
    public void pointCut() {

    }

//    @Before("execution(* com.example.demo.chapter4.aspect.service.imple.UserServiceImpl.printUser(..))")
    @Before("pointCut() && args(user)")
    public void before(JoinPoint joinPoint, User user) {
        log.info("before user "+user);
        log.info("before joinpoint "+joinPoint.getArgs());
        log.info("before...");
    }

//    @After("execution(* com.example.demo.chapter4.aspect.service.imple.UserServiceImpl.printUser(..))")
    @After("pointCut()")
    public void after() {
        log.info("after...");
    }

//    @AfterReturning("execution(* com.example.demo.chapter4.aspect.service.imple.UserServiceImpl.printUser(..))")
    @AfterReturning("pointCut()")
    public void afterReturning() {
        log.info("afterReturning...");
    }


    @AfterThrowing("pointCut()")
//@AfterThrowing("execution(* com.example.demo.chapter4.aspect.service.imple.UserServiceImpl.printUser(..))")
    public void afterThrow() {
        log.info("afterThrow...");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) {
        log.info("around before");
        try {
            joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        log.info("around after");

    }
}
