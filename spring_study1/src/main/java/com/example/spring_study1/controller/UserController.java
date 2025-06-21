package com.example.spring_study1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring_study1.entity.Role;
import com.example.spring_study1.entity.User;
import com.example.spring_study1.service.UserService;

@Controller	
public class UserController {

	@Autowired
	private UserService userService;
	
	//Bean으로 등록한 비밀번호 암호화 모듈을 Autowired로 연관성 만드는 과정
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user) {
		String userPassword = user.getPassword();
		System.out.println("userPassword:"+userPassword);
		user.setRole(Role.MEMBER);
		//비밀번호를 DB에 암호화하여 저장
		String passwordEncoded = passwordEncoder.encode(userPassword);
		user.setPassword(passwordEncoded);
		userService.insertUser(user);
		return "redirect:/loginPage";
	}
	
	
}
