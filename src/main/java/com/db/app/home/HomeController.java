package com.db.app.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.db.app.aops.main.Start;

import lombok.extern.slf4j.Slf4j;

//  web/views/index.jsp를 찾으러 감
// 처음에 project 를 만들때 war 를 선택하면 webapp이 생성
// 스프링부트는 xml을 쓰지않아서 모든설정을  application.properties에서 함
@Controller
@Slf4j
public class HomeController {
	@Autowired
	private Start start;
	
	@GetMapping("/")
	public String home() throws Exception {
		start.go();
//		log.trace("Trace");//추적
//		log.debug("Debug");//디버그찾는거
//		log.info("info"); //일반적인 정보
//		log.warn("warn");//경고
//		log.error("error");//에러가 났을때
//		
		return "index";
		
	}
}
