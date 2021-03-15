package kr.or.ddit.myclassroom.discuss.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.commons.service.ReplyService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.discuss.service.DiscussService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.NotyMessageVO.NotyType;

/**
 * 토론 게시판 
 */
@Controller
public class DiscussController extends BaseController{
	
	@Inject
	DiscussService discussService;
	@Inject
	ReplyService replyService;
	
	@ModelAttribute
	public BoardVO board( @AuthenticationPrincipal(expression="realMember") MemberVO authMember) {
		return new BoardVO();
	}
	
	/** 토론 게시판 리스트 **/
	@RequestMapping("/myclass/{lecCode}/disList.do")
	public String disList(@PathVariable(value="lecCode", required=true) String lecCode
						, @AuthenticationPrincipal(expression="realMember") MemberVO authMember
						, Model model) {
		List<BoardVO> discussList = discussService.selectDiscussList(BoardVO.builder().boGroupCode("DISC").lecCode(lecCode).build());
		
		model.addAttribute("authMember", authMember);
		model.addAttribute("discussList", discussList);
		return "myClassRoom/discuss/discussList";
	}
	
	/** 토론 게시판 조회 
	 * @throws Exception **/
	@RequestMapping("/myclass/{lecCode}/{boNo}/disView.do")
	public String disView(@PathVariable(value="lecCode", required=true) String lecCode
						, @PathVariable(value="boNo", required=true) Integer boNo
						, Model model
						, RedirectAttributes redirectAttributes){
		try {
			BoardVO boardVO = discussService.selectDiscusss(BoardVO.builder().boNo(boNo).boGroupCode("DISC").lecCode(lecCode).build());
			model.addAttribute("boardVO", boardVO);
		}catch(DataAccessException e) {
			printError("error", e.getMessage());
			redirectAttributes.addFlashAttribute("msg", notyErrorMessage());
			return "redirect:/myclass/{lecCode}/disList.do";
		}
		return "myClassRoom/discuss/discussView";
	}
	
	
	/** 토론 게시판 등록 
	 * @throws AccessDeniedException **/
	@RequestMapping("/myclass/{lecCode}/disInsertForm.do")
	public String disInsertForm(@PathVariable(value="lecCode", required=true) String lecCode
			, Model model
			, @RequestAttribute("professorId") String professorId
			, @AuthenticationPrincipal(expression="realMember") MemberVO authMember) {
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
		model.addAttribute("boardVO", BoardVO.builder().memId(authMember.getMemId()).boGroupCode("DISC").lecCode(lecCode).flag("create").build());
		return "myClassRoom/discuss/discussForm";
	}
	
	@PostMapping("/myclass/{lecCode}/disInsert.do")
	public String disInsert(@PathVariable(value="lecCode", required=true) String lecCode
			, @Validated(InsertGroup.class)@ModelAttribute BoardVO boardVO
			, BindingResult errors
			, Model model){
		if(errors.hasErrors()) {
			return "myClassRoom/discuss/discussForm";
		}
		try {
			discussService.insertDiscuss(boardVO);
		} catch (Exception e) {
			model.addAttribute("msg", notyErrorMessage());
			return "myClassRoom/discuss/discussForm";
		}
		return "redirect:/myclass/{lecCode}/disList.do";
	}
	
	@PostMapping("/myclass/{lecCode}/disUpdateForm.do")
	public String disUpdateForm(@PathVariable(value="lecCode", required=true) String lecCode,
			BoardVO boardVO
			, Model model) {
		boardVO = discussService.selectDiscusss(boardVO);
		boardVO.setFlag("modify");
		model.addAttribute("boardVO", boardVO);
		return "myClassRoom/discuss/discussForm";
	}
	
	@PostMapping("/myclass/{lecCode}/disUpdate.do")
	public String disUpdateForm(@PathVariable(value="lecCode", required=true) String lecCode
			, @AuthenticationPrincipal(expression="realMember") MemberVO authMember
			, @Validated(UpdateGroup.class) BoardVO boardVO
			, BindingResult errors
			, Model model) {
		if(!boardVO.getMemId().equals(authMember.getMemId())) {
			model.addAttribute("msg", NotyMessageVO.builder("해당 글의 작성자가 아닙니다.").type(NotyType.error).build());
			return "myClassRoom/discuss/discussView";
		}
		
		if(errors.hasErrors()) {
			model.addAttribute("boardVO", boardVO);
			return "myClassRoom/discuss/discussForm";
		}
		
		try {
			discussService.modifyDiscuss(boardVO);
		} catch (Exception e) {
			model.addAttribute("boardVO", boardVO);
			model.addAttribute("msg", notyErrorMessage());
			return "myClassRoom/discuss/discussForm";
		}
		// 포워딩하면 lecCode가 인코딩되서 깨짐
		return "redirect:/myclass/{lecCode}/disList.do";
	}
	
	@PostMapping("/myclass/{lecCode}/disDelete.do")
	public String disDelete(@PathVariable(value="lecCode", required=true) String lecCode
			, @AuthenticationPrincipal(expression="realMember") MemberVO authMember
			, BoardVO boardVO
			, Model model) {
		if(!boardVO.getMemId().equals(authMember.getMemId())) {
			model.addAttribute("msg", NotyMessageVO.builder("해당 글의 작성자가 아닙니다.").type(NotyType.error).build());
			return "myClassRoom/discuss/discussView";
		}
		try {
			discussService.disDelete(boardVO);
		} catch (Exception e) {
			model.addAttribute("boardVO", boardVO);
			model.addAttribute("msg", notyErrorMessage());
			return "myClassRoom/discuss/discussView";
		}
		return "redirect:/myclass/{lecCode}/disList.do";
	}
	
	
}
