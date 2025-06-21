package com.example.spring_study1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_study1.entity.User;
import com.example.spring_study1.mapper.UserMapper;

@Service
public class UserService {
//의존성주입
	@Autowired
	private UserMapper userMapper;
	
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}
	
	public String findWriter(String username) {
		return userMapper.findWriter(username);
	}
}
