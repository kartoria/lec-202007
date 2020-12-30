package kr.or.ddit.board.controller;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;

@Controller
public class BoardInsertController {
	@RequestMapping("/board/boardInsert.do")
	public String form() {
		return "board/boardForm";
	}
	
	@RequestMapping(value="/board/boardInsert.do", method=RequestMethod.POST)
	public String insert() {
		
	}
}
