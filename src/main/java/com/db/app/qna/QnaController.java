package com.db.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.db.app.home.HomeController;
import com.db.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/qna/*")//qna로 시작하는 모든것은 여기로 와라
@Slf4j
public class QnaController {
	@Autowired
	private QnaService qnaService;
	
	@Value("${board.qna}")
	private String board;
	
	//board 속성명으로 qna가 감
	@ModelAttribute("board")
	public String getBoard() {
		return this.board;
	}
	
	//void면 요청url에 매핑된걸 합치는게 bean의 이름 qna/list 이렇게 생긴 이름이 없으면 jsp로 이동
	//foword
	@GetMapping("list")
	public void getList(Pager pager,Model model)throws Exception{
		List<QnaVO> ar = qnaService.getList(pager);
		model.addAttribute("list",ar);
		model.addAttribute("pager",pager);
	
		log.info("Pager : {} : {}"+ pager,pager.getKind());
		//return "redirect:../";
		
	}
	@GetMapping("add")
	public void add()throws Exception{
		
	}
	@PostMapping("add")
	public String add(QnaVO qnaVO,MultipartFile [] attaches)throws Exception{
		int result = qnaService.add(qnaVO,attaches);
		return "redirect:./list";
		
	}
	@GetMapping("detail")
	public void getDetail(QnaVO qnaVO,Model model) throws Exception{
		qnaVO = qnaService.getDetail(qnaVO);
		model.addAttribute("vo",qnaVO);
	}
	//fileDownView 와 같은 bean의 이름을 찾으러감 이름이 있어서 이동
	@GetMapping("fileDown")
	public String fileDown(QnaFileVO qnaFileVO,Model model) throws Exception {
		qnaFileVO = qnaService.getFileDetail(qnaFileVO);
		model.addAttribute("file",qnaFileVO);
		
		return "fileDownView";
	}
	

}
