package com.example.demo.chapter6.dao;

import com.example.demo.chapter6.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * Created by zhangxiaoyun3 on 2018/11/2.
 */
@Repository
public interface MyBatisUserDao {
    public User getUser(Long id);
    public int insertUser(User user);
}
