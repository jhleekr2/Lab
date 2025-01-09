package socket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebsocketTest {

	@GetMapping("/websocket/main")
	public void main(Model model) {
		log.info("main() 호출");
		//로그가 정상동작함을 확인
		
		//이후 본격적으로 웹소켓 개발해보자
		//만일 웹소켓 기본채팅기능 성공하면 프로젝트에 합병해보는 것 시도
	}
	
}
