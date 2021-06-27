package com.example.demo.chapter5.service.impl;

import com.example.demo.chapter5.dao.MyBatisUserDao;
import com.example.demo.chapter5.pojo.User;
import com.example.demo.chapter5.service.MyBatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangxiaoyun3 on 2018/11/2.
 */
@Service
public class MyBatisUserServiceImpl implements MyBatisUserService {
    @Autowired
    MyBatisUserDao myBatisUserDao;
    @Override
    public User getuser(Long id) {
        return myBatisUserDao.getUser(id);
    }
}
