package com.example.demo.chapter13.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**** imports ****/
@Service
public class ScheduleServiceImpl {
    public static Log log = LogFactory.getLog(ScheduleServiceImpl.class);
    // 计数器
    int count1 = 1;
    int count2 = 1;
    
    // 每隔1秒执行一次
//    @Scheduled(fixedRate = 1000)
    // 使用异步执行
    @Async
    public void job1() {
        log.info("【" +Thread.currentThread().getName()+"】"
                + "【job1】每秒钟执行一次，执行第【" + count1 + "】次");
        count1 ++;
    }
    
    // 每隔1秒执行一次
//    @Scheduled(fixedRate = 1000)
    // 使用异步执行
    @Async
    public void job2() {
        log.info("【" +Thread.currentThread().getName()+"】"
                + "【job2】每秒钟执行一次，执行第【" + count2 + "】次");
        count2 ++;
    }
    
    int count3 = 1;
    int count4 = 1;
    // Spring IoC容器初始化后，第一次延迟3秒，每隔1秒执行一次
//    @Scheduled(initialDelay = 3000, fixedRate = 1000)
    @Async
    public void job3() {
        log.info("【" + Thread.currentThread().getName() + "】"
            + "【job3】每秒钟执行一次，执行第【" + count3 + "】次");
        count3++;
    }

    // 没分钟执行一次
//    @Scheduled(cron = "0/3 * * * * ?")
    @Async
    public void job4() {
        log.info("【" + Thread.currentThread().getName()
            + "】【job4】每分钟执行一次，执行第【" + count4 + "】次");
        count4 ++;
    }
}