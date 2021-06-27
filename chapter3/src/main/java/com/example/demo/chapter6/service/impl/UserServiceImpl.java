package com.example.demo.chapter6.service.impl;

import com.example.demo.chapter6.pojo.User;
import com.example.demo.chapter6.dao.MyBatisUserDao;
import com.example.demo.chapter6.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangxiaoyun3 on 2018/11/5.
 */
@Service
public class UserServiceImpl implements UserService ,ApplicationContextAware{
    private static Log log = LogFactory.getLog(UserServiceImpl.class);
    @Autowired
    private MyBatisUserDao myBatisUserDao;
    //解决自调失效得问题
    private ApplicationContext applicationContext;

    @Override
//    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,timeout = 1)
    public User getUser(Long id) {
        User user = myBatisUserDao.getUser(id);
        return user;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW,timeout = 1)
    public int insertUser(User user) {
//        if (user.getUserName().equals("rongyao1")) {
//            log.info("模拟异常");
//            throw new RuntimeException("hh");
//        }
        return myBatisUserDao.insertUser(user);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int batchInsertUser(List<User> list) {
        int i = 0;
        for (User user : list) {
            UserService bean = applicationContext.getBean(UserService.class);
            i+=bean.insertUser(user);
        }
        return i;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
