package com.db.app.robot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//xml 대신에 java를 이용해서 객체 생성

@Configuration  //xml역할을 하는 어노테이션
public class RobotConfig {
	
	//<bean class="">
	@Bean("ra")
	RobotArm makeArm() {
		return new RobotArm();
	}
	//객체를 만들면 스프링풀이라는 곳에 객체를 보관해놈
	@Bean
	Robot makeRobot() {
		Robot robot = new Robot();
		robot.setRobotArm(makeArm());
		
		return robot;
	}
	
	

}
