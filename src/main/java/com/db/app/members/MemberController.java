package com.db.app.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.app.validate.MemberAddGroup;
import com.db.app.validate.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("update")
	public String update(HttpSession session,Model model) throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		model.addAttribute("memberVO",memberVO);
		
		return "member/update";
	}
	
//	@PostMapping("update")
//	public String update(@Validated(MemberAddGroup.class) MemberVO memberVO,BindingResult bindingResult) throws Exception{
//		if(bindingResult.hasErrors()) {
//			return "member/update";
//		}
//		return "redirect:./mypage";
//		
//	}
	
	@PostMapping("update")
	public String update(@Validated(MemberUpdateGroup.class) MemberVO memberVO, BindingResult bindingResult) throws Exception{
		if(bindingResult.hasErrors()) {
			return "member/update";
		}

		return "redirect:./mypage";
	}
	
	
	@GetMapping("mypage")
	public void mypage() throws Exception{
		
	}
	//add
	@GetMapping("add")
	public void add(@ModelAttribute MemberVO memberVO)throws Exception{
		//model.addAttribute("memberVO",new MemberVO());
		//모델로 보내는거보다 매개변수로 보내는게 편함
	}
	@PostMapping("add")
	public String add(@Validated(MemberAddGroup.class) MemberVO memberVO,BindingResult bindingResult)throws Exception{ //BindingResult bindingResult 검증의 결과를 받음
		
		boolean check = memberService.memberValidate(memberVO, bindingResult);
		
		if(check){
			//bindingResult.rejectValue("passwordCheck","memberVO.pw.notEqual");
			return "member/add";
		}
//		if(bindingResult.hasErrors()) {
//			return "member/add";
//		}
		//int result=memberService.add(memberVO);
		return "redirect:/";
	}
	
	//login
	@GetMapping("login")
	public void login()throws Exception{
		
	}
	@PostMapping("login")
	public String login(MemberVO memberVO,HttpSession session)throws Exception{
		memberVO = memberService.detail(memberVO);
		
		if(memberVO != null) {
			session.setAttribute("member",memberVO);
		}
		return "redirect:../";
	}
	
	//logout
	@GetMapping("logout")
	public String logout(HttpSession session)throws Exception {
		session.invalidate();
		return "redirect:../";
	}
}
