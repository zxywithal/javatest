package com.example.demo.chapter11.controller;

import com.example.demo.chapter11.exception.NotFundException;
import com.example.demo.chapter11.pojo.User;
import com.example.demo.chapter11.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiaoyun3 on 2018/11/22.
 */
@Controller
public class UserController {
    private static Log log = LogFactory.getLog(UserController.class);
    @Autowired
    private UserService userService;
    @GetMapping("/restful")
    public String index(){
        return "restful";
    }

    //post新增对象
    @PostMapping("/user")
    @ResponseBody
    public User insertUser(@RequestBody User user) {
        User user1 = userService.insertUser(user);
        return user1;
    }

    //获取对象
    @GetMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/user/exp/{id}")
    @ResponseBody
    public User getUserForExp(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        if(user==null){
            throw new NotFundException("111","not find user for id ["+id+"]");
        }
        return user;
    }

    @GetMapping("/user/{userName}/{note}/{start}/{limit}")
    @ResponseBody
    public List<User> findUsers(@PathVariable("userName") String userName,
                          @PathVariable("note") String note,
                          @PathVariable("start") int start,
                          @PathVariable("limit") int limit) {
        return userService.findUsers(userName, note, start, limit);
    }

    //删除对象
    @DeleteMapping("/user/{id}")
    @ResponseBody
    public int deleteUpser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    //put修改全部参数
    @PutMapping("/user/{id}")
    @ResponseBody
    public User updateUser(@PathVariable("id") int id, User user) {
        user.setId(id);
        userService.updateUser(user);
        return user;
    }

    //用patch修改部分参数
    @PatchMapping("/user/{id}/{userName}")
    @ResponseBody
    public User changeUserName(@PathVariable("id") long id,@PathVariable("userName") String userName) {
        userService.updateUserName(id, userName);
        return userService.getUser(id);
    }

}
