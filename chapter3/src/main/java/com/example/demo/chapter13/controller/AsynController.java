package com.example.demo.chapter13.controller;

import com.example.demo.chapter13.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangxiaoyun3 on 2018/11/30.
 */
@Controller
public class AsynController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/testAsync")
    @ResponseBody
    public String testAsync(){
        asyncService.servie();
        return "succ";
    }
}
