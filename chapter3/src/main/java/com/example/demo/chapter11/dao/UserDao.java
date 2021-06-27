package com.example.demo.chapter11.dao;

import com.example.demo.chapter11.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangxiaoyun3 on 2018/11/2.
 */
@Mapper
public interface UserDao {
    public User getUser(Long id);
    public int insertUser(User user);
    public int updateUser(User user);
    public int updateUserName(@Param("id") Long id, @Param("userName") String userName);
    public List<User> findUsers(@Param("userName") String userName, @Param("note") String note, @Param("start") int start,  @Param("limit") int limit);
    int deleteUser(Long id);
}
