package kr.or.ddit.board.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.board.sevice.BoardServiceImpl;
import kr.or.ddit.board.sevice.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
public class BoardInsertController {
	private IBoardService service = BoardServiceImpl.getInstance();
	
	@RequestMapping("/board/boardInsert.do")
	public String form() {
		return "board/boardForm";
	}
	
	@RequestMapping(value = "/board/boardInsert.do", method = RequestMethod.POST)
	public String insert(@ModelAttribute("board") BoardVO board
			, HttpServletRequest req) {
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		CommonValidator<BoardVO> validator = new CommonValidator<>();
		boolean valid = validator.validate(board, errors, InsertGroup.class);
		
		String goPage = null;
		
		if(valid) {
			ServiceResult result = service.createBoard(board);
			switch (result) {
				case OK:
					goPage =  "redirect:/board/boardView.do?what="+board.getBo_no();
					break;
				default:	
					req.setAttribute("message", NotyMessageVO.builder("서버 오류").build());
					goPage = "board/boardForm";
				break;
			}
		}else {
			goPage = "board/boardForm";
		}
		return goPage;
	}
}
