package com.example.demo.chapter13.service;

import com.example.demo.chapter13.pojo.User;

import java.util.List;

/**
 * Created by zhangxiaoyun3 on 2018/11/5.
 */
public interface UserService {
    public User insertUser(User user);

    public User getUser(Long id);

    public List<User> findUsers(String userName, String note, int start, int limit);

    public int updateUser(User user);

    public int  updateUserName(Long id, String userName);

    public int deleteUser(Long id);
}
