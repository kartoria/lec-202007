package kr.or.ddit.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;

@Controller
public class BoardDeleteController {
	@Inject
	private IBoardService service;
	
	@RequestMapping(value="/board/boardDelete.do", method=RequestMethod.POST)
	public String delete(@Validated(DeleteGroup.class) @ModelAttribute("board") BoardVO board,
			Errors errors, RedirectAttributes redirectAttributes){
		// 글번호, 비밀번호에 대한 검증(삭제 힌트 적용)
		String goPage = "redirect:/board/boardView.do?what="+board.getBo_no();
		NotyMessageVO message = null;
		boolean valid = !errors.hasErrors();
		// 각 케이스별 메시지와 이동 위치 필요
		if(valid) { // 검증 통과
			ServiceResult result = service.removeBoard(board);
			switch (result) {
			case INVALIDPASSWORD:
				message = NotyMessageVO.builder("비밀번호 오류")
										.type(NotyType.error)
										.layout(NotyLayout.topCenter)
										.timeout(3000)
										.build();
				break;
			case FAILED:
				message = NotyMessageVO.builder("서버 오류")
										.type(NotyType.error)
										.layout(NotyLayout.topCenter)
										.timeout(3000)
										.build();
				break;
			default: // OK , 삭제 성공시 게시글 목록으로 이동
				goPage = "redirect:/board/boardList.do";
				break;
			}
			
		}else { // 불통
			message = NotyMessageVO.builder("글번호나 비밀번호 누락")
									.type(NotyType.error)
									.layout(NotyLayout.topCenter)
									.timeout(3000)
									.build();
		}	
		if(message!=null) redirectAttributes.addFlashAttribute("message", message);
		return goPage;
	}
	
}












