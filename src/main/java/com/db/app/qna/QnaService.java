package com.db.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {
	//서비스는 매퍼가(dao) 필요함
	@Autowired
	private QnaMapper qnaMapper;
	
	public List<QnaVO> getList(Pager pager)throws Exception{
		
		pager.makeRow();
		
		return qnaMapper.getList(pager);
	}
	public int add(QnaVO qnaVO) throws Exception{
		log.info("=============Insert Before BoardNum: {}",qnaVO.getBoardNum());
		int result = qnaMapper.add(qnaVO);
		log.info("=============Insert After BoardNum: {}",qnaVO.getBoardNum());
		//ref 값을 가져와서 다시 수정
		result = qnaMapper.refUpdate(qnaVO);
		
		
		return result;
	}
	public QnaVO getDetail(QnaVO qnaVO)throws Exception{
		return qnaMapper.getDetail(qnaVO);
	}
}
