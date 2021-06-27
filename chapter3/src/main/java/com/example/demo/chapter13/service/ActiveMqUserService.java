package com.example.demo.chapter13.service;


import com.example.demo.chapter13.pojo.User;

public interface ActiveMqUserService {

	public void sendUser(User user);

	public void receiveUser(User user);
}
