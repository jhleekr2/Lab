package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

	// DB작업 없이 서버와 클라이언트가 소통하면 컨트롤러가 할일은 없음.
	@GetMapping("/index")
	public void index(Model model) {
	}
	
	@GetMapping("/socket/chat")
	public void chat(Model model) {
	}
}
