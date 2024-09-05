package com.db.app.aops.transfers;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Transfer {
	//핵심로직은 별도의 설정을 안함
	//공통로직에서 함
	public String takeBus(int num) {
		log.info("====== 버스타기 ======");
		
		return "BUS";
	}
	public void takeSubway(Long m, String name) {
		log.info("====== 지하철타기 ======");
	}
	public void walk() {
		log.info("===== 걸어가기 =====");
	}
}
