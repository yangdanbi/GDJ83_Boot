package com.db.app.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//  web/views/index.jsp를 찾으러 감
// 처음에 project 를 만들때 war 를 선택하면 webapp이 생성
// 스프링부트는 xml을 쓰지않아서 모든설정을  application.properties에서 함
@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() throws Exception {
		return "index";
		
	}
}
