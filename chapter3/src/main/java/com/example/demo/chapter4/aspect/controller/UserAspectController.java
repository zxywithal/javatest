package com.example.demo.chapter4.aspect.controller;

import com.example.demo.chapter3.pojo.User;
import com.example.demo.chapter4.aspect.service.imple.UserServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Proxy;

/**
 * Created by zhangxiaoyun3 on 2018/10/29.
 */
@RequestMapping("/user")
@Controller
public class UserAspectController {
    private static Log log = LogFactory.getLog(UserAspectController.class);
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/print")
    @ResponseBody
    private User printUser(Long id, String userName, String note) {
        User user = new User();
        user.setId(id);
        user.setUserNmae(userName);
        user.setNote(note);
        log.info(Proxy.isProxyClass(userService.getClass()));
        userService.printUser(user);
        return user;
    }

    @RequestMapping(value = "/manyAspect")
    @ResponseBody
    private User manyAspect(Long id, String userName, String note) {
        User user = new User();
        user.setId(id);
        user.setUserNmae(userName);
        user.setNote(note);
        log.info(Proxy.isProxyClass(userService.getClass()));
        userService.manyAspect(user);
        return user;
    }
}
