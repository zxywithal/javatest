package com.example.demo.chapter7.controller;

import com.example.demo.chapter7.pojo.User;
import com.example.demo.chapter7.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangxiaoyun3 on 2018/11/1.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private Log log = LogFactory.getLog(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/insert")
    @ResponseBody
    public User insert(String userName,String sex,String note){
        User user = new User();
        user.setSex(sex);
        user.setNote(note);
        user.setUserName(userName);
        user = userService.insertUser(user);
        return user;
    }
    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUser(Long id){
        User user = userService.getUser(id);
        return user;
    }

    @RequestMapping("/findUsers")
    @ResponseBody
    public List<User> findUsers(String userName,String note){

        List<User> users = userService.findUsers(userName, note);
        return users;
    }

    @RequestMapping("/updateUserName")
    @ResponseBody
    public String updateUserName(Long id, String userName){

        User user = userService.updateUserName(id, userName);
        boolean res = user != null;
        return res?"成功":"失败";
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(Long id){

        int i = userService.deleteUser(id);
        boolean res = i==1;
        return res?"成功":"失败";
    }
}
