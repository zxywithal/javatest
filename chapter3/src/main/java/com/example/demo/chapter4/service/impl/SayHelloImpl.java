package com.example.demo.chapter4.service.impl;

import com.example.demo.chapter3.pojo.DataBaseProperties;
import com.example.demo.chapter4.service.HelloService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Created by zhangxiaoyun3 on 2018/10/26.
 */

public class SayHelloImpl implements HelloService {
    public static final Log log = LogFactory.getLog(SayHelloImpl.class);
    @Override
    public void sayHello(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("name is null");
        }
        log.info("hello " + name);
    }
}
