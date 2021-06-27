package com.example.demo.chapter13.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangxiaoyun3 on 2018/11/30.
 */
@Configuration
@EnableAsync
@EnableScheduling
public class AsynConfiger implements AsyncConfigurer {

    @Nullable
    @Override
    public Executor getAsyncExecutor() {
        return Executors.newFixedThreadPool(100, new ThreadFactory() {
            private String gpName = "my-thread-pool-zxy";
            private AtomicInteger atomicInteger = new AtomicInteger();
            @Override
            public Thread newThread(Runnable r) {
                ThreadGroup threadGroup = new ThreadGroup(gpName);
                return new Thread(threadGroup, r, gpName+"-"+atomicInteger.getAndIncrement());
            }
        });
    }

    @Nullable
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
                System.out.println(throwable + "-" + method + "-" + objects);
            }
        };
    }
}
