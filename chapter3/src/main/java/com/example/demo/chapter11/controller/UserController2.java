package com.example.demo.chapter11.controller;

import com.example.demo.chapter11.pojo.User;
import com.example.demo.chapter11.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zhangxiaoyun3 on 2018/11/22.
 */
//使每个方法默认都是用json数据格式返回
@RestController
public class UserController2 {
    private static Log log = LogFactory.getLog(UserController2.class);
    @Autowired
    private UserService userService;
    @GetMapping("/restful2")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("restful");

        return modelAndView;
    }

    //获取对象
    @GetMapping("/user2/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    //post新增对象
    @PostMapping("/user2/entity")
    public ResponseEntity<User> insertUserEntiry(@RequestBody User user) {
        User user1 = userService.insertUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("success","true");
        return new ResponseEntity<User>(user1, headers, HttpStatus.CREATED);
    }

    @PostMapping("/user2/annotation")
    @ResponseStatus(HttpStatus.CREATED)
    public User insertUserAnnotation(@RequestBody User user) {
        User user1 = userService.insertUser(user);
        return user1;
    }
}
