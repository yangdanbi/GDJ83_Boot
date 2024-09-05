package com.db.app.aops.pays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect //공통로직에 주는 어노테이션
public class Card {
	//전에 실행 공통로직 advice
	// take 이게 있으면 핵심로직/핵심로직 실행되기 전에 Card Check가 실행됨 메서드 명이 take 이게 있으면 ..
	//@After("execution(* com.db.app.aops.transfers.Transfer.take*())")
	
	//Throwable exception중에 최고 조상
	//전후로 출력하기
	@Around("execution(* com.db.app.aops.transfers.Transfer.take*(..))") //매개변수 갯수에 따라 take*(..)  조절 -> ..은 매개변수가 0개 이상이면 다 됨
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable{ //전후로 출력하기 위해서 핵심로직의 대한 정보를 객체로 만들어서
		log.info("===== Before Card Check =====");
		
		//getArgs 매개변수
		log.info("==== ARGS : {} ", joinPoint.getArgs());
		
		//핵심로직이진행되는 구간
		Object obj =	joinPoint.proceed();// point cut
		log.info("===== Return : {}",obj);
		log.info("===== After Card Check =====");
		
		return obj;
		
	}

}
