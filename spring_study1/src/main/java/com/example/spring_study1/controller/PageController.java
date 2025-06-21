package com.example.spring_study1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring_study1.entity.Menu;
import com.example.spring_study1.service.MenuRestService;
import com.example.spring_study1.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
//@Component 한마디로 스프링 빈으로 등록하기 위한 라벨링작업
public class PageController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MenuRestService menuRestService;
//@PostMapping()
//@PutMapping()
//@DeleteMapping()
	@GetMapping("/")
	// / => localhost:8080
	public String Home() {
		return "index";
	}
	//페이지를 조회및 이동할 때 위와같이 @GetMapping을 써서 이동
	// / => localhost:8080/register
	@GetMapping("/registerPage")
	public String registerPage(HttpServletRequest request, Model model) {
		//request는 view와 controller 사이의 통로역할
		
		CsrfToken csrfToken = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
		model.addAttribute("_csrf", csrfToken);
		return "register/index";
	}

	// / => localhost:8080/loginPage
	@GetMapping("/loginPage")
	public String loginPage(HttpServletRequest request, Model model) {
		CsrfToken csrfToken = (CsrfToken)request.getAttribute(CsrfToken.class.getName());
		model.addAttribute("_csrf", csrfToken);
		return "login/index";
	}
	
    @GetMapping("/noticeAddPage")
    public String noticeAddPage(Model model, Authentication authentication) {
    	//Model은 데이터를 한번 전달하면 사라지고 세션은 그렇지 않음
    	String writer = userService.findWriter(authentication.getName());
    	model.addAttribute("writer",writer);
    	return "noticeAdd/index";
    }
    
    @GetMapping("/noticeCheckPage")
    public String showNoticeCheckPage(@RequestParam("idx") int idx, Model model) {
    	Menu menu = menuRestService.boardContent(idx);
		model.addAttribute("menu", menu);
    	return "noticeCheck/index";
    }
    
    @GetMapping("/noticeModifyPage")
    public String showNoticeModifyPage(@RequestParam("idx") int idx, Model model) {
    	Menu menu = menuRestService.boardContent(idx);
		model.addAttribute("menu", menu);
    	return "noticeModify/index";
    }
}
