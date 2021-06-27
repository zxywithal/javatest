package com.example.demo.chapter13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhangxiaoyun3 on 2018/12/4.
 */
@Controller
@RequestMapping("/websocket")
public class WebSocketController {

    @GetMapping("/index")
    public String websocket(){
        return "websocket";
    }
}
