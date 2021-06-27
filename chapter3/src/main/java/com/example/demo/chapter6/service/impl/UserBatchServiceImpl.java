package com.example.demo.chapter6.service.impl;

import com.example.demo.chapter6.pojo.User;
import com.example.demo.chapter6.service.UserBatchService;
import com.example.demo.chapter6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangxiaoyun3 on 2018/11/5.
 */
@Service
public class UserBatchServiceImpl implements UserBatchService {

    @Autowired
    private UserService userService;
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int batchInsertUser(List<User> list) {
        int i = 0;
        for (User user : list) {
            i+=userService.insertUser(user);
        }
        return i;
    }
}
