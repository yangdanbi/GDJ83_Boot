package com.db.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.db.app.util.FileManager;
import com.db.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {
	//서비스는 매퍼가(dao) 필요함
	@Autowired
	private QnaMapper qnaMapper;
//	@Value("${board.qna}")
//	private String board;
	
	@Value("${app.upload}")
	private String upload;

	@Value("${board.qna}")
	private String name;
	
	@Autowired
	private FileManager fileManager;

	
	
	public List<QnaVO> getList(Pager pager)throws Exception{
		pager.makeRow();
		//getList했을떄 upload 패스를 {} 안에 넣어서 출력
		//log.info("UploadPath : {}" , upload);
		
		return qnaMapper.getList(pager);
	}
	public int add(QnaVO qnaVO,MultipartFile [] attaches) throws Exception{
		log.info("=============Insert Before BoardNum: {}",qnaVO.getBoardNum());
		int result = qnaMapper.add(qnaVO);
		log.info("=============Insert After BoardNum: {}",qnaVO.getBoardNum());
		//ref 값을 가져와서 다시 수정
		result = qnaMapper.refUpdate(qnaVO);
		
		//파일을 하드디스크에 저장하고 db에 정보를 추가
		for(MultipartFile mf:attaches) {
			//파일을 안올릴수도 있기 때문에 비었으면 continue
			if(mf==null|| mf.isEmpty()) {
				continue;
			}
			String fileName= fileManager.fileSave(upload+name,mf); //D:upload/qna
			//log.info("저장된 파일명 : {}",fileName);
			
			QnaFileVO qnaFileVO = new QnaFileVO();
			qnaFileVO.setFileName(fileName);
			qnaFileVO.setOriName(mf.getOriginalFilename());
			qnaFileVO.setBoardNum(qnaVO.getBoardNum());
			
			result = qnaMapper.addFile(qnaFileVO);
			}
//		return result;
		return 0;
	}
	public QnaVO getDetail(QnaVO qnaVO)throws Exception{
		return qnaMapper.getDetail(qnaVO);
	}
}
