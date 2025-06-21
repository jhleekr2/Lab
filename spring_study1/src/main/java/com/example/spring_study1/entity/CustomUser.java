package com.example.spring_study1.entity;

import org.springframework.security.core.authority.AuthorityUtils;

public class CustomUser extends org.springframework.security.core.userdetails.User{

	private User user;
	
	public CustomUser(User user) {
		//기존의 우리가 만들었던 user클래스와 시큐리티에 있는 user 클래스를 합침
		super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
	}
}
