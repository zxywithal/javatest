package com.example.controller;
;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhangxiaoyun3 on 2019/2/22.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/ribbon")
    public String testRibbon(){
        String user = null;
        for (int i = 0; i < 10; i++) {
            // 注意这里直接使用了USER这个服务ID，代表用户微服务系统
            // 该ID通过属性spring.application.name来指定
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            user = restTemplate.getForObject("http://USER/user/" + (i + 1), String.class);
            logger.info(user);
        }
        return user;
    }

    @GetMapping("/break1")
    @HystrixCommand(fallbackMethod = "error")
    public String break1(){
        return restTemplate.getForObject("http://USER/timeout/", String.class);
    }

    public String error(){
        return "超时熔断";
    }
}
