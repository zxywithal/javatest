package com.example.demo.chapter6.controller;

import com.example.demo.chapter5.enumeration.SexEnum;
import com.example.demo.chapter6.pojo.User;
import com.example.demo.chapter6.service.UserBatchService;
import com.example.demo.chapter6.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiaoyun3 on 2018/11/1.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private Log log = LogFactory.getLog(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private UserBatchService userBatchService;

    @RequestMapping("/add")
    @ResponseBody
    public String insert(String userName,String sex,String note){
        User user = new User();
        user.setSex(sex);
        user.setNote(note);
        user.setUserName(userName);

        int i = userService.insertUser(user);
        log.info("res " + i);
        return "succ";
    }
    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUser(Long id){
        User user = userService.getUser(id);
        return user;
    }

    @RequestMapping("/batchInsertUser")
    @ResponseBody
    public List<User> getUser(String userName, String sex,String note){
        ArrayList<User> list = new ArrayList<>();
        for(int i = 0 ;i<2;i++){
            User user = new User();
            user.setUserName(userName+i);
            user.setSex(sex);
            user.setNote(note+i);
            list.add(user);
        }
        int res = userService.batchInsertUser(list);
        return list;
    }
}
