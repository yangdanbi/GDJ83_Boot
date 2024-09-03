package com.db.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.app.util.Pager;

@Service
public class QnaService {
	//서비스는 매퍼가(dao) 필요함
	@Autowired
	private QnaMapper qnaMapper;
	
	public List<QnaVO> getList(Pager pager)throws Exception{
		
		pager.makeRow();
		
		return qnaMapper.getList(pager);
	}
}
