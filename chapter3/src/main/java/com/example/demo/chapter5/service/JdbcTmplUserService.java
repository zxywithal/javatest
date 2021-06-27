package com.example.demo.chapter5.service;

import com.example.demo.chapter5.pojo.User;

import java.util.List;

/**
 * Created by zhangxiaoyun3 on 2018/10/31.
 */
public interface JdbcTmplUserService {
    public User getUser(Long id);
    public List<User> findUsers(String userNmae, String note);
    public int insertUser(User user);
    public int updateUser(User user);
    public int delteUser(User user);
}
