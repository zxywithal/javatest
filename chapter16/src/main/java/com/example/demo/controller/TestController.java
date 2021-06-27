package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangxiaoyun3 on 2019/2/20.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/package")
    @ResponseBody
    public String firstMethod(){
        return "打包测试，哈哈哈";
    }
}
