package com.example.demo.chapter6.service;

import com.example.demo.chapter6.pojo.User;

import java.util.List;

/**
 * Created by zhangxiaoyun3 on 2018/11/5.
 */
public interface UserService {
    public User getUser(Long id);
    public int insertUser(User user);
    public int batchInsertUser(List<User> list);
}
