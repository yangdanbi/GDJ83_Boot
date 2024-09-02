package com.db.app.notice;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeVO {
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContents;
	private Date createDate;

}
