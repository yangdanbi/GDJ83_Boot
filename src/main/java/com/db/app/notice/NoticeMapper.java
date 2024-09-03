package com.db.app.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
	
	//long 타입 num을 보냄
	public List<NoticeVO> getList(Long num) throws Exception;
	public int add() throws Exception;

}
