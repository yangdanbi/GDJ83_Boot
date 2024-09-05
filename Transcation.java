package com.db.app.aops.pays;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Transcation {

	//예외가 발생했을때
	@AfterThrowing("execution(* com.db.app.*.*.set*(..))")
	public void rollBack() {
		
	}
}
