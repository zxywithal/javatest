package com.example.demo.chapter7.dao;

import com.example.demo.chapter7.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangxiaoyun3 on 2018/11/2.
 */
@Repository
public interface UserDao {
    public User getUser(Long id);
    public int insertUser(User user);
    public int updateUser(User user);
    List<User> findUsers(@Param("userName") String userName, @Param("note") String note);
    int deleteUser(Long id);
}
