package com.example.controller;

import com.example.pojo.UserPo;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangxiaoyun3 on 2019/2/22.
 */
@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/user/{id}")
    private UserPo getUser(@PathVariable int id){
        ServiceInstance serviceInstance = discoveryClient.getInstances("USER").get(0);
        logger.info("[" + serviceInstance.getServiceId() + "] " + serviceInstance.getHost() + " " + serviceInstance.getPort());
        UserPo userPo = new UserPo();
        userPo.setId(id);
        userPo.setLevel(id%3+1);
        userPo.setUserName("userName-"+id);
        userPo.setNote("hhhhh");
        return userPo;
    }

    @GetMapping("/timeout")
    private String timeout(){
        long mil = (long) (3000*Math.random());
        try {
            Thread.sleep(mil);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "熔断测试";
    }

}
