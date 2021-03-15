package kr.or.ddit.cyber.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.commons.service.BoardService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

/**
 * 사이버 캠퍼스 공지사항 Controller
 */
@Controller
public class NoticeController extends BaseController{
	
	@Inject
	private BoardService service;
	
	private void addCommandAttribute(Model model) {
		model.addAttribute("command", "MODIFY");
	}
	
	/** 사이버 캠퍼스 공지사항 조회 **/
	@GetMapping("/cyber/notice/list.do")
	public String cyberNotiselect(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
					@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
					SearchVO searchVO, Model model) {
		PagingVO<BoardVO> pagingVO = new PagingVO<>(10,5);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setBoGroupCode("CYNTC");
		
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<BoardVO> boardList = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		
		model.addAttribute("pageTitle","공지사항");
		model.addAttribute("pageLink","/cyber/notice/list.do");
		model.addAttribute("paginationInfo", new CustomPaginationInfo<BoardVO>(pagingVO));
		return "cyber/notice/cyberNoticeList";
	}
	
	/** 사이버 캠퍼스 공지사항 상세보기 **/
	@GetMapping("/cyber/notice/{what}/view.do")
	public String cyberView(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
						@PathVariable(value="what", required=true) int boNo, Model model, RedirectAttributes redirectAttributes) {
		BoardVO board = service.retrieveBoard(BoardVO.builder().boNo(boNo).build());	
		// 삭제된 게시글인 경우
		if("Y".equals(board.getBoDelete())) {
			redirectAttributes.addFlashAttribute("message", notyErrorMessage());
			return "redirect:/cyber/notice/list.do";
		} else {
			model.addAttribute("board",board);
			return "cyber/notice/cyberNoticeView";
		}
	}
	
	@ModelAttribute("board")
	public BoardVO board() {
		return new BoardVO();
	}
	
	/** 사이버 캠퍼스 공지사항 등록 get**/
	@RequestMapping("/cyber/notice/insertForm.do")
	public String formInsert(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,Model model) {
		if(!"ROLE_ADMIN".equals(authMember.getMemType())) {
			throw new AccessDeniedException("관리자가 아님");
		}
		model.addAttribute("pageTitle","공지사항");
		model.addAttribute("pageLink","/cyber/notice/insertForm.do");
		return "cyber/notice/cyberNoticeForm";
	}
	
	/** 사이버 캠퍼스 공지사항 등록**/
	@PostMapping("/cyber/notice/insert.do")
	public String cyberNotiInsert(
							@Validated(InsertGroup.class) @ModelAttribute("board") BoardVO board, BindingResult errors, Model model
							,@AuthenticationPrincipal(expression="realMember") MemberVO authMember, RedirectAttributes redirectAttributes) {
		if(errors.hasErrors()) {
			model.addAttribute("message", notyErrorMessage());
			return "cyber/notice/cyberNoticeForm";
		}
		try {
			service.createBoard(board);
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());
			return "cyber/notice/cyberNoticeForm";
		}
		redirectAttributes.addFlashAttribute("message", notyInsertSuccessMessage());
		return "redirect:/cyber/notice/"+board.getBoNo()+"/view.do";
	}
	
	/** 사이버 캠퍼스 공지사항 수정 폼**/
	@GetMapping("/cyber/notice/updateForm.do")
	public String form(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
					@RequestParam("boNo") int boNo, Model model) {
		if(!"ROLE_ADMIN".equals(authMember.getMemType())) {
			throw new AccessDeniedException("관리자가 아님");
		}
		addCommandAttribute(model);
		BoardVO board = service.retrieveBoard(BoardVO.builder().boNo(boNo).build());
		model.addAttribute("board", board);
		model.addAttribute("pageTitle","공지사항");
		model.addAttribute("pageLink","/cyber/notice/updateForm.do");
		return "cyber/notice/cyberNoticeForm";
	}
	
	/** 사이버 캠퍼스 공지사항 수정 **/
	@PostMapping("/cyber/notice/update.do")
	public String cyberUpdate(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
							@Validated(UpdateGroup.class) BoardVO board, BindingResult errors, Model model, RedirectAttributes redirectAttributes) {
		addCommandAttribute(model);
		if(errors.hasErrors()) {
			model.addAttribute("message", notyErrorMessage());
			return "cyber/notice/cyberNoticeForm";
		}
		try {
			service.modifiyBoard(board);
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());
			return "cyber/notice/cyberNoticeForm";
		}
		model.addAttribute("pageTitle","공지사항");
		model.addAttribute("pageLink","/cyber/notice/update.do");
		redirectAttributes.addFlashAttribute("message", notyUpdateSuccessMessage());
		return "redirect:/cyber/notice/list.do";
	}
	
	/** 공지사항 삭제 **/
	@RequestMapping("/cyber/notice/delete.do")
	public String cyberNotiDel(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
					@Validated(DeleteGroup.class) BoardVO board,  BindingResult errors,
					RedirectAttributes redirectAttributes, Model model) {
		if(!"ROLE_ADMIN".equals(authMember.getMemType())) {
			throw new AccessDeniedException("관리자가 아님");
		}
		try {
			service.removeBoard(board);
			model.addAttribute("message", notyDeleteSuccessMessage());
		} catch (Exception e) {
			model.addAttribute("board", board);
			model.addAttribute("message", notyErrorMessage());
		}
		model.addAttribute("pageTitle","공지사항");
		model.addAttribute("pageLink","/cyber/notice/delete.do");
		return "redirect:/cyber/notice/list.do";
	}
}
