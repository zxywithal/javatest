package com.example.demo.chapter8.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.chapter8.pojo.User;
import com.example.demo.chapter8.service.UserService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController  {
    private Log log = LogFactory.getLog(UserController.class);
    // 后面会给出其操作的方法
    @Autowired
    private UserService userService = null;

    // 跳转到测试页面
    @RequestMapping("/page")
    public String page() {
        return "/user/user";
    }


    @RequestMapping("/save")
    @ResponseBody
    public User saveUser(@RequestBody User user) {
//        log.info("userJson "+userJson);
//        User user = JSON.parseObject(userJson, User.class);
        userService.saveUser(user);
        return user;
    }

    /***
     * 获取用户
     * @param id -- 用户主键
     * @return 用户信息
     */
    @RequestMapping("/get")
    @ResponseBody
    public User getUser(Long id) {
        User user = userService.getUser(id);
        return user;
    }


    /**
     * 查询用户
     * @param userName --用户名称
     * @param note -- 备注
     * @param skip -- 跳过用户个数
     * @param limit -- 限制返回用户个数
     * @return
     */
    @RequestMapping("/find")
    @ResponseBody
    public List<User> addUser(String userName, String note, Integer skip, Integer limit) {
        List<User> userList = userService.findUser(userName, note, skip, limit);
        return userList;
    }

    /**
     * 更新用户部分属性
     * @param id —— 用户编号
     * @param userName —— 用户名称
     * @param note —— 备注
     * @return 更新结果
     */
    @RequestMapping("/update")
    @ResponseBody
    public UpdateResult updateUser(Long id, String userName, String note) {
        return userService.updateUser(id, userName, note);
    }

    /**
     * 删除用户
     * @param id -- 用户主键
     * @return 删除结果
     */
    @RequestMapping("/delete")
    @ResponseBody
    public DeleteResult deleteUser(Long id) {
        return userService.deleteUser(id);
    }
}