package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
@RequestMapping("/board/boardInsert.do")
public class BoardInsertController {
	@Inject
	private IBoardService service;
	
	@GetMapping
	public String form() {
		return "board/boardForm";
	}
	
	@PostMapping
	public String insert(@Validated(InsertGroup.class) @ModelAttribute("board") BoardVO board,
			Errors errors, Model model
	) {
		boolean valid = !errors.hasErrors();
		String goPage = null;
		
		if(valid) {
			ServiceResult result = service.createBoard(board);
			switch (result) {
				case OK:
					goPage =  "redirect:/board/"+board.getBo_no();
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
