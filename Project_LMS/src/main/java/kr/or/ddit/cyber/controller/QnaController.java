package kr.or.ddit.cyber.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.commons.service.BoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;

/**
 * 사이버 캠퍼스 Qna 게시판 Controller 
 */
@Controller
public class QnaController extends BaseController{
	
	@Inject
	private BoardService service;
	
	@Inject
	PasswordEncoder passwordEncoder;
	

	@ModelAttribute("board")
	public BoardVO board() {
		return new BoardVO();
	}
	
	/** 사캠 이용 질문 게시판 리스트 **/
	@RequestMapping("/cyber/qna/list.do")
	public String doGet(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model, BoardVO boardVO
			) {
		PagingVO<BoardVO> pagingVO = new PagingVO<>(10, 5);
		pagingVO.setSearchVO(searchVO);
		int totalRecord = service.selectQnaCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<BoardVO> qnaList = service.selectQnaList(pagingVO);
		pagingVO.setDataList(qnaList);
		model.addAttribute("paginationInfo", new CustomPaginationInfo<BoardVO>(pagingVO));
		model.addAttribute("pageTitle", "사이버 캠퍼스 질문 내역");
		return "cyber/qna/cyberQnaList";
	}
	
	/** 사캠 이용  질문 등록  **/
	@RequestMapping("/cyber/qna/insert.do")
	public String insert(
			@Validated(InsertGroup.class) @ModelAttribute("board") BoardVO board, BindingResult errors, Model model
			,RedirectAttributes redirectAttribute) {
		model.addAttribute("pageLink", "/cyber/qna/list.do");
		String goPage = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createQna(board);
			switch (result) {
			case OK:
				redirectAttribute.addFlashAttribute("msg", NotyMessageVO.builder("등록 완료").layout(NotyLayout.topCenter).type(NotyType.success).timeout(3000).build());
				goPage = "redirect:/cyber/qna/"+board.getBoNo()+"/view.do";
//				goPage = "redirect:/cyber/qna/list.do";
				break;
			default:
				redirectAttribute.addFlashAttribute("msg",  NotyMessageVO.builder("서버 오류").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
				goPage = "cyber/qna/cyberQnaForm";
				break;
			}
		} else {
			goPage = "cyber/qna/cyberQnaForm";
		}
		model.addAttribute("pageTitle", "사이버 캠퍼스 질문 등록");
		return goPage;
	}	
	
	/** 사캠 이용  질문 수정 form**/
	@RequestMapping("/cyber/qna/updateForm.do")
	public String update(
			@RequestParam("what") int boNo, Model model) {
		BoardVO board = service.retrieveQna(BoardVO.builder().boNo(boNo).build());
		model.addAttribute("board", board);
		model.addAttribute("pageLink", "/cyber/qna/list.do");
		model.addAttribute("pageTitle", "사이버 캠퍼스 질문 수정");
		return "cyber/qna/cyberQnaUpdateForm";
	}
	
	/** 사캠 이용  질문 수정 **/
	@RequestMapping("/cyber/qna/update.do")
	public String update(
			@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board, BindingResult errors, Model model
			,RedirectAttributes redirectAttribute) {
		model.addAttribute("pageLink", "/cyber/qna/list.do");
		String goPage = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyQna(board);
			switch (result) {
			case OK:
				redirectAttribute.addFlashAttribute("msg", NotyMessageVO.builder("수정 완료").layout(NotyLayout.topCenter).type(NotyType.success).timeout(3000).build());
				goPage = "redirect:/cyber/qna/"+board.getBoNo()+"/view.do";
//				goPage = "redirect:/cyber/qna/list.do";
				break;
			default:
				redirectAttribute.addFlashAttribute("msg",  NotyMessageVO.builder("서버 오류").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
				goPage = "cyber/qna/cyberQnaUpdateForm";
				break;
			}
		} else {
			goPage = "cyber/qna/cyberQnaUpdateForm";
		}
		return goPage;
	}	
	
	/** 사캠 이용 질문 자식글 삭제 **/
	@RequestMapping("/cyber/qna/delete.do")
	public String cyberdelete(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@Validated(DeleteGroup.class) BoardVO board, Errors errors,
			RedirectAttributes redirectAttribute
			) {
		String goPage = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.removeQna(board);
			switch (result) {
			case OK:
				redirectAttribute.addFlashAttribute("msg", NotyMessageVO.builder("삭제 완료").layout(NotyLayout.topCenter).type(NotyType.success).timeout(3000).build());
				goPage = "redirect:/cyber/qna/list.do";
				break;
			default:
				redirectAttribute.addFlashAttribute("msg",  NotyMessageVO.builder("서버오류").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
				goPage = "redirect:/cyber/qna/"+board.getBoNo()+"/view.do";
				break;
			}
		} else {
			goPage = "redirect:/cyber/qna/list.do";
		}
		return goPage;
	}
	
	
	/** 사캠 이용  질문 상세 보기 **/
	@RequestMapping("/cyber/qna/{what}/view.do")
	public String view(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@PathVariable(value="what", required=true) int boNo,
			Model model, RedirectAttributes redirectAttributes
			) {
		BoardVO realBoard = BoardVO.builder().boNo(boNo).build();
		BoardVO board = service.retrieveQna(realBoard);
		BoardVO previousBoard = service.previousQna(realBoard);
		BoardVO nextBoard = service.nextQna(realBoard);
		
		model.addAttribute("board", board);
		model.addAttribute("previousBoard", previousBoard);
		model.addAttribute("nextBoard", nextBoard);
		model.addAttribute("pageLink", "/cyber/qna/list.do");
		model.addAttribute("pageTitle", "사이버 캠퍼스 질문글 상세");
		return "cyber/qna/cyberQnaView";
	}
	
	@RequestMapping("/cyber/qna/secretIcon.do")
	public String secretIcon() {
		return "cyber/qna/cyberQnaList";
	}
	
	/** 상세 조회 시, 비밀번호 재확인 **/
	@RequestMapping(value="/cyber/qna/passCheck.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> qnaMemberPasswordCheck(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@RequestParam("memPass") String memPass,
			@RequestParam("boNo") Integer boNo,
			@RequestParam("memId") String memId,
			Model model
			) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMemId(memId);
		memberVO = service.passCheck(memberVO);
		
		String realPass = memberVO.getMemPass();
		String realId = authMember.getMemId();
		
		boolean matchedPass = passwordEncoder.matches(memPass, realPass);
		boolean mathcedId = realId.contains(memId);
		
		Map<String, Object> resultMap = new HashMap<>();
		if(matchedPass && mathcedId) {
			 //로그인한 아이디가 qna 게시판에 기재한 사람과의 아이디가 같고, 비밀번호가 일치한다면 상세조회.
			resultMap = Collections.singletonMap("result", "OK");
		} else {
			resultMap = Collections.singletonMap("result", "FAILED");
		}
		return resultMap;
	}
	
	/** qna 삭제시, 비밀번호 재확인 **/
	@RequestMapping(value="/cyber/qna/deletePassCheck.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> qnaDeletePassCheck(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@RequestParam(value = "memPass", required=false) String memPass,
			@RequestParam("boNo") Integer boNo,
			@RequestParam("memId") String memId,
			Model model
			) {
		Map<String, Object> resultMap = Collections.singletonMap("result", "FAILED");
		if("ROLE_ADMIN".equals(authMember.getMemType())) {
			resultMap = Collections.singletonMap("result", "OK");
		} else {
			MemberVO memberVO = new MemberVO();
			memberVO.setMemId(memId);
			memberVO = service.passCheck(memberVO);
			
			String realPass = memberVO.getMemPass();
			String realId = authMember.getMemId();
			
			boolean matchedPass = passwordEncoder.matches(memPass, realPass);
			boolean mathcedId = realId.contains(memId);
			
			if(matchedPass && mathcedId) {
				//로그인한 아이디가 qna 게시판에 기재한 사람과의 아이디가 같고, 비밀번호가 일치한다면 상세조회.
				resultMap = Collections.singletonMap("result", "OK");
			} 
		}
		return resultMap;
	}
}
