package com.example.demo.chapter10.service.impl;

import com.example.demo.chapter10.pojo.User;
import com.example.demo.chapter10.service.UserService;
import com.example.demo.chapter10.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangxiaoyun3 on 2018/11/10.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    @Cacheable(value = "redisCache",key = "'redis_user_'+#id")
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    @CachePut(value = "redisCache",key = "'redis_user_'+#result.id")
    @Transactional
    public User insertUser(User user) {
         userDao.insertUser(user);
         return user;
    }

    @Override
    @Transactional
    @CachePut(value = "redisCache",condition = "#result!='null'",key = "'redis_user_'+#result.id")
    public User updateUserName(Long id, String userName) {
        User user = getUser(id);
        if(user==null)
            return null;
        user.setUserName(userName);
        userDao.updateUser(user);
        return user;
    }

    @Override
    public List<User> findUsers(String userName, String note) {
        return userDao.findUsers(userName,note);
    }

    @Override
    @CacheEvict(value = "redisCache",key = "'redis_user_'+#result.id",beforeInvocation = false)
    public int deleteUser(Long id) {
        return userDao.deleteUser(id);
    }
}
