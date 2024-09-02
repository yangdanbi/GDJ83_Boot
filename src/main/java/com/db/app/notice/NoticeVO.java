package com.db.app.notice;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//dto = data transfer object
//vo = value object(읽기전용)
//dto -> vo

//어노테이션으로 게터세터 만들어줌
//@Getter
//@Setter
//@AllArgsConstructor //매개변수가 있는 생성자를 만들어줌
//@NoArgsConstructor// 매개변수가 없는 생성자 기본생성자를 만들어줌
//@ToString
@Data //위에 어노테이션이 다 들어가있는것
public class NoticeVO {
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContents;
	private Date createDate;
}
