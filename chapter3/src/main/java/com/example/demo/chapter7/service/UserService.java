package com.example.demo.chapter7.service;


import com.example.demo.chapter7.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangxiaoyun3 on 2018/11/5.
 */
public interface UserService {
    public User getUser(Long id);
    public User insertUser(User user);
    public User updateUserName(Long id,String userName);
    List<User> findUsers(String userName, String note);
    int deleteUser(Long id);
}
