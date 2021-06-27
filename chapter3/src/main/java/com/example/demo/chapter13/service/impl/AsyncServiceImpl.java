package com.example.demo.chapter13.service.impl;

import com.example.demo.chapter13.service.AsyncService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by zhangxiaoyun3 on 2018/11/30.
 */
@Service
public class AsyncServiceImpl implements AsyncService {
    public static Log log = LogFactory.getLog(AsyncServiceImpl.class);

    @Async
    @Override
    public void servie() {
        log.info("start async service");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("end async service");

    }
}
