package com.example.demo.chapter4.aspect.service.imple;

import com.example.demo.chapter3.pojo.User;
import com.example.demo.chapter4.aspect.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by zhangxiaoyun3 on 2018/10/29.
 */
@Service
public class UserServiceImpl {
    private static Log log = LogFactory.getLog(UserService.class);

    public void printUser(User user) {
        if(user == null){
            throw new RuntimeException();
        }
        log.info(user.getId());
        log.info(user.getNote());
        log.info(user.getUserNmae());
    }
    public void manyAspect(User user){
        log.info("测试多个切面");
    }
}
