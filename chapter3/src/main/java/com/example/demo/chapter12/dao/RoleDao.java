package com.example.demo.chapter12.dao;

import com.example.demo.chapter12.pojo.DatabaseRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {
	
	public List<DatabaseRole> findRolesByUserName(String userName);
}
