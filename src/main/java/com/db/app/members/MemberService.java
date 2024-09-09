package com.db.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
	
	public boolean memberValidate(MemberVO memberVO,BindingResult bindingResult) throws Exception {
		boolean check =false;
		//check == false : 검증성공(error없음)
		//check == true : 검증실패(error있음)
		
		//0. 기본검증값(Annotation 검증의 결과값)
		check = bindingResult.hasErrors();
		
		//1. password 일치하는지 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			//비밀번호랑 비밀번호 체크가 일치하지 않으면 check = true
			check= true;
			bindingResult.rejectValue("passwordCheck","memberVO.pw.notEqual");
			
		}
		
		//2. ID 중복 검사
		MemberVO result =memberMapper.detail(memberVO);
		//아이디가 있다면
		if(result != null) {
			check=true;
			bindingResult.rejectValue("username","memberVO.username.duplication");
		}
		
		return check;
		
	}
	
	public int add(MemberVO memberVO) throws Exception{
		int result= memberMapper.add(memberVO);
		
		Map<String, Object> map = new HashMap<>();
		map.put("username",memberVO.getUsername());
		map.put("roleNum",1);
		result = memberMapper.addRole(map);
		
		return result;
	}
	
	public MemberVO detail(MemberVO memberVO) throws Exception{
		MemberVO result = memberMapper.detail(memberVO);
		//아이디가 널이거나 비번이 틀리면 다 널
		if(result != null) {
			if(result.getPassword().equals(memberVO.getPassword())) {
				return result;
			}
		}
		return null;
	}
	
//	public int addRole(Map<String, Object> map) throws Exception{
//		
//	};
}
