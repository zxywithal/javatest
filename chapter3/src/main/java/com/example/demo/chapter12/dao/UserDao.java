package com.example.demo.chapter12.dao;

import com.example.demo.chapter12.pojo.DatabaseUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
	
	public DatabaseUser getUser(String userName);
}
