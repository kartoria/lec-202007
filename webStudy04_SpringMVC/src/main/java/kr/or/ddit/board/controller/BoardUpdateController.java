package kr.or.ddit.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
@RequestMapping("/board/boardUpdate.do")
public class BoardUpdateController {
	@Inject
	private IBoardService service;
	
	@GetMapping
	public String form(
		@RequestParam("what") int bo_no, Model model){
		BoardVO board = service.retrieveBoard(bo_no);
		model.addAttribute("board", board);
		return "board/boardForm";
	}
	
	@PostMapping
	public String update(@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board
			, Errors error, Model model) {
		String goPage = null;
		boolean valid = !error.hasErrors();
		if(valid) {
			ServiceResult result = service.modifyBoard(board);
			switch (result) {
				case OK:
					goPage =  "redirect:/board/"+board.getBo_no();
					break;
				case INVALIDPASSWORD:
					model.addAttribute("message", NotyMessageVO.builder("비밀번호 오류").build());
					goPage = "board/boardForm";
					break;
				default:	
					model.addAttribute("message", NotyMessageVO.builder("서버 오류").build());
					goPage = "board/boardForm";
				break;
			}
		}else {
			goPage = "board/boardForm";
		}
		return goPage;
	}
}
