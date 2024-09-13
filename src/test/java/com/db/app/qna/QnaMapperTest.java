package com.db.app.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.db.app.util.Pager;

@SpringBootTest
@Transactional //모든 테스트 메서드 실행 후 전부 RollBack 처리 함
class QnaMapperTest {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	//@Test
	@Rollback(false) //메서드 실행 후 RollBack하지 않음 전체 클래스에 @Transactional해놓고 내가 필요하지않을땐 false로 하면 작동 x
	void getDetailTest() throws Exception {
		
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardNum(100L);
		qnaVO = qnaMapper.getDetail(qnaVO);
		
		assertNotNull(qnaVO);
		
	}

	//@Test
	void addTest() throws Exception {
		for (int i = 4; i < 110; i++) {
			QnaVO qnaVO = new QnaVO();
			qnaVO.setBoardContents("c" + i);
			qnaVO.setBoardTitle("t" + i);
			qnaVO.setBoardWriter("w" + i);
			qnaVO.setRef((long) i);
			qnaVO.setStep(0L);
			qnaVO.setDepth(0L);

			int result = qnaMapper.add(qnaVO);
			if (i % 10 == 0) {
				Thread.sleep(500);
			}
		}
	}

	//@Test
	void getListTest() throws Exception {
		Pager pager = new Pager();
		pager.setPage(1L);
		pager.setKind("k2");
		pager.setSearch("2");
		pager.makeRow();
		List<QnaVO>ar = qnaMapper.getList(pager);
		
//		for(QnaVO qnaVO:ar) {
//			System.out.println(qnaVO.toString());
//		}
		assertNotEquals(0, ar.size());

	}

}
