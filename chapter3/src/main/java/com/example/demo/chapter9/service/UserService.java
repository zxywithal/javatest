package com.example.demo.chapter9.service;

import com.example.demo.chapter9.pojo.User;

import java.util.List;


public interface UserService {
	
	User getUser(Long id);

	List<User> findUsers(String userName, String note);

}
