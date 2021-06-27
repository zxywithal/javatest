package com.example.demo.chapter5.controller;

import com.example.demo.chapter5.pojo.User;
import com.example.demo.chapter5.service.MyBatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangxiaoyun3 on 2018/11/2.
 */
@Controller
@RequestMapping("/mybatis")
public class MyBatisController {

    @Autowired
    private MyBatisUserService myBatisUserService;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(long id) {
        return myBatisUserService.getuser(id);
    }
}
