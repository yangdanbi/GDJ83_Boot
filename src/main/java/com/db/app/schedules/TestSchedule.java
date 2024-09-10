package com.db.app.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.db.app.qna.QnaMapper;
import com.db.app.qna.QnaVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	@Autowired
	private QnaMapper qnaMapper;
	
	//@Scheduled(fixedDelay = 1000, initialDelay = 2000) //1초마다 로그, 2초 후에 시작
	public void test1()throws Exception {
		//실행 후 종료까지 약 2초가 걸린다.
		log.error("Schedule Test1");
	}
	
	//@Scheduled(fixedRate = 2000) //2초마다 로그
	@Scheduled(cron = "0 50 * * * *") //초 분 시간 며칠 몇월 요일
	public void test2()throws Exception {
		log.error("============= Schedule Test2 ===============");
	}

	@Scheduled(cron = "*/5 * * * * *") //초 분 시간 며칠 몇월 요일
	public void test3()throws Exception {
		log.error("============= Schedule Test3 ===============");
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardWriter("test");
		qnaVO.setBoardTitle("Title");
		qnaVO.setBoardContents("Contents");
		qnaMapper.add(qnaVO);
		qnaMapper.refUpdate(qnaVO);
	}

}
