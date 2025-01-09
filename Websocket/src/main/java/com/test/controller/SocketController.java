package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SocketController {

	//WebSocket으로 클라이언트와 서버 통신할 때는 컨트롤러의 역할은
	//View를 띄워주는 정도의 역할만 존재
	@GetMapping("/test")
	public void test(Model model) {		
	}
}
