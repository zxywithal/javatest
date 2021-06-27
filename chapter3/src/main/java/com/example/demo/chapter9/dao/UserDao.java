package com.example.demo.chapter9.dao;

import java.util.List;

import com.example.demo.chapter9.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserDao {
	
	User getUser(Long id);

	List<User> findUsers(@Param("userName") String userName, @Param("note") String note);
}
