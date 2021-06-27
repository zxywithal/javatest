package com.example.demo.chapter9.service.impl;

import java.util.List;

import com.example.demo.chapter9.dao.UserDao;
import com.example.demo.chapter9.pojo.User;
import com.example.demo.chapter9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao = null;
	
	@Override
	public User getUser(Long id) {
		return userDao.getUser(id);
	}

	@Override
	public List<User> findUsers(String userName, String note) {
		return userDao.findUsers(userName, note);
	}
	
}
