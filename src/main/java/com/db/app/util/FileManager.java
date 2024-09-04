package com.db.app.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	public String fileSave(String path, MultipartFile multipartFile) throws Exception{
		//어디에 저장??
		File file = new File(path);
		//파일이 있을수도 있고, 없을 수도 있으니까
		//존재하지않으면 파일을 만들어라
		if(!file.exists()) {
			file.mkdir();
		}
		//저장할 파일명 생성 중복이 되면 안됨
		//확장자를 포함해서 파일이름을 만들기 위해 파일정보가있는 multipartFile안에 원래 이름을 통째로 가져와서 이용
		String fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		
		//파일을 HDD에 저장
		file = new File(file,fileName);
		multipartFile.transferTo(file);
		
		//저장된 파일명 리턴
		return fileName;
		
	}
}
