package com.example.demo.chapter13.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.chapter13.pojo.User;
import com.example.demo.chapter13.service.ActiveMqService;
import com.example.demo.chapter13.service.ActiveMqUserService;
import com.example.demo.chapter13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**** imports ****/
@Controller
@RequestMapping("/activemq")
public class ActiveMqController {
    
    // 注入服务对象
    @Autowired
    private ActiveMqService activeMqService = null;
    
    // 注入服务对象
    @Autowired
    private ActiveMqUserService activeMqUserService = null;
    @Autowired
    private UserService userService;
    
    // 测试普通消息的发送
    @ResponseBody
    @GetMapping("/msg")
    public Map<String, Object> msg(String message) {
        activeMqService.sendMsg(message);
        return result(true, message);
    }
    
    // 测试User对象的发送
    @ResponseBody
    @GetMapping("/user")
    public Map<String, Object> sendUser(Long id) {
        User user1 = userService.getUser(id);
        activeMqUserService.sendUser(user1);
        return result(true, user1);
        
    }
    
    private Map<String, Object> result(Boolean success, Object message) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("message", message);
        return result;
    }
}