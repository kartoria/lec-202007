package kr.or.ddit.myclassroom.notice.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

/**
 * 사이버 캠퍼스 공지사항 Controller
 */
@Controller
public class MyNotiController extends BaseController{
	
	@Inject
	private BoardService service;
	
	private void addCommandAttribute(Model model) {
		model.addAttribute("command", "MODIFY");
	}
	
	/** 마클 공지사항 조회 **/
	@GetMapping("/myclass/{lecCode}/noticeList.do")
	public String cyberNotiselect(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
					@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
					@ModelAttribute("searchVO") SearchVO searchVO, Model model,
					@RequestAttribute("subName") String subName,
					@PathVariable(value ="lecCode", required=true) String lecCode) {
		PagingVO<BoardVO> pagingVO = new PagingVO<>(10,5);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setBoGroupCode("NTC");
		pagingVO.setLecCode(lecCode);
		
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<BoardVO> boardList = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(boardList);
		
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "학습 공지"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/noticeList.do");
		model.addAttribute("paginationInfo", new CustomPaginationInfo<BoardVO>(pagingVO));
		return "myClassRoom/notice/myNoticeList";
	}
	
	/** 마클 공지사항 상세보기 **/
	@RequestMapping("/myclass/{lecCode}/{boNo}/notiView.do")
	public String cyberView(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
						@PathVariable(value="boNo", required=true) int boNo, Model model, 
						@RequestAttribute("subName") String subName, RedirectAttributes redirectAttributes,
						@PathVariable(value ="lecCode", required=true) String lecCode) {
		BoardVO board = service.retrieveBoard(BoardVO.builder().boNo(boNo).build());	
		// 삭제된 게시글인 경우
		if("Y".equals(board.getBoDelete())) {
			redirectAttributes.addFlashAttribute("message", notyErrorMessage());
			return "redirect:/myclass/"+lecCode+"/noticeList.do";
		} else {
			model.addAttribute("board",board);
			model.addAttribute("pageTitle", pageTitleWithSubName(subName, "학습 공지"));
			model.addAttribute("pageLink","/myclass/"+lecCode+board.getBoNo()+"/noticeList.do");
			return "myClassRoom/notice/myNoticeView";
		}
	}
	
	@ModelAttribute("board")
	public BoardVO board() {
		return new BoardVO();
	}
	
	/** 마클 공지사항 등록 get**/
	@RequestMapping("/myclass/{lecCode}/notiInsertForm.do")
	public String notiInsertForm(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@RequestAttribute("subName") String subName, Model model,@RequestAttribute("professorId") String professorId,@PathVariable(value ="lecCode", required=true) String lecCode) {
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "학습 공지"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/notiInsertForm.do");
		return "myClassRoom/notice/myNoticeForm";
	}
	
	/** 마클 공지사항 등록**/
	@PostMapping("/myclass/{lecCode}/notiInsert.do")
	public String notiInsert(@Validated(InsertGroup.class) @ModelAttribute("board") BoardVO board, BindingResult errors, Model model
							,@AuthenticationPrincipal(expression="realMember") MemberVO authMember, 
							@RequestAttribute("subName") String subName, RedirectAttributes redirectAttributes,@PathVariable(value ="lecCode", required=true) String lecCode) {
		if(errors.hasErrors()) {
			model.addAttribute("message", notyErrorMessage());
			return "myClassRoom/notice/myNoticeForm";
		}
		try {
			service.createBoard(board);
		} catch (Exception e) {
			model.addAttribute("message",  notyErrorMessage());
			return "myClassRoom/notice/myNoticeForm";
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "학습 공지"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/notiInsert.do");
		redirectAttributes.addFlashAttribute("message", notyInsertSuccessMessage());
		return "redirect:/myclass/"+lecCode+"/notiView.do";
	}
	
	/** 마클 공지사항 수정 폼**/
	@GetMapping("/myclass/{lecCode}/notiUpdateForm.do")
	public String notiUpdateForm(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,@RequestAttribute("professorId") String professorId,
					@PathVariable(value ="lecCode", required=true) String lecCode, @RequestAttribute("subName") String subName,@RequestParam("boNo") int boNo, Model model) {
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
		addCommandAttribute(model);
		BoardVO board = service.retrieveBoard(BoardVO.builder().boNo(boNo).build());
		model.addAttribute("board", board);
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "학습 공지"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/notiUpdateForm.do");
		return "myClassRoom/notice/myNoticeForm";
	}
	
	/** 마클 공지사항 수정 **/
	@PostMapping("/myclass/{lecCode}/notiUpdate.do")
	public String cyberUpdate(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
					@PathVariable(value ="lecCode", required=true) String lecCode, @RequestAttribute("subName") String subName,@Validated(UpdateGroup.class) BoardVO board, BindingResult errors, Model model, RedirectAttributes redirectAttributes) {
		addCommandAttribute(model);
		if(errors.hasErrors()) {
			model.addAttribute("message", notyErrorMessage());
			return "myClassRoom/notice/myNoticeForm";
		}
		try {
			service.modifiyBoard(board);
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());
			return "myClassRoom/notice/myNoticeForm";
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "학습 공지"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/notiUpdate.do");
		redirectAttributes.addFlashAttribute("message", notyUpdateSuccessMessage());
		return "redirect:/myclass/"+lecCode+"/noticeList.do";
	}
	
	/** 마클 공지사항 삭제 **/
	@RequestMapping("/myclass/{lecCode}/notiDelete.do")
	public String cyberNotiDel(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,@RequestAttribute("professorId") String professorId,
					@PathVariable(value ="lecCode", required=true) String lecCode, @RequestAttribute("subName") String subName, @Validated(DeleteGroup.class) BoardVO board, Errors errors,
					RedirectAttributes redirectAttributes, Model model) {
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
		if(errors.hasErrors()) {
			model.addAttribute("message", notyErrorMessage());
		}
		try {
			service.removeBoard(board);
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "학습 공지"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/notiDelete.do");
		redirectAttributes.addFlashAttribute("message", notyDeleteSuccessMessage());
		return "redirect:/myclass/"+lecCode+"/noticeList.do";
	}
}
