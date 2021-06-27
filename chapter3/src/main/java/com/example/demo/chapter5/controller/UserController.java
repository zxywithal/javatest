package com.example.demo.chapter5.controller;

import com.example.demo.chapter5.enumeration.SexEnum;
import com.example.demo.chapter5.pojo.User;
import com.example.demo.chapter5.service.JdbcTmplUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhangxiaoyun3 on 2018/11/1.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private Log log = LogFactory.getLog(UserController.class);
    @Autowired
    private JdbcTmplUserService jdbcTmplUserService;

    @RequestMapping("/add")
    @ResponseBody
    public String insert(String userName,String sex,String note){
        User user = new User();
        user.setSexEnum(SexEnum.valueOf(sex));
        user.setNote(note);
        user.setUserName(userName);

        int i = jdbcTmplUserService.insertUser(user);
        log.info("res " + i);
        return "succ";
    }
    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUser(Long id){
        User user = jdbcTmplUserService.getUser(id);
        return user;
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public List<User> getUser(String userNmae, String note){
        List<User> users = jdbcTmplUserService.findUsers(userNmae, note);
        return users;
    }
}
