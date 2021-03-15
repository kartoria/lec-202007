package kr.or.ddit.commons.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.commons.service.ReplyService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

@RestController
@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReplyController extends BaseController{
	@Inject
	ReplyService replyService;
	
	
	@PostMapping("/reply/list.do")
	public PagingVO<ReplyVO> list(@RequestParam(value="boNo", required=true) int boNo, 
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember) throws Exception{
		ReplyVO searchDetail = new ReplyVO();
		searchDetail.setBoNo(boNo);
		
		PagingVO<ReplyVO> pagingVO = new PagingVO<>(5, 5);
		pagingVO.setSearchDetail(searchDetail);
		
		int totalRecord = replyService.selectReplyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord); // totalPage
		pagingVO.setCurrentPage(currentPage); // startRow, endRow, startPage, endPage
		
		List<ReplyVO> replyList = replyService.selectReplyList(pagingVO);
		pagingVO.setDataList(replyList);
		pagingVO.setMemId(authMember.getMemId());
		return pagingVO;
	}
	
	@PostMapping("/reply/insert.do")
	public Map<String, Object> insert(@ModelAttribute("reply") ReplyVO reply
			, @AuthenticationPrincipal(expression="realMember") MemberVO authMember){
		reply.setMemId(authMember.getMemId());
		try {
			replyService.createReply(reply);
			return Collections.singletonMap("result", "OK");
		} catch (Exception e) {
			return Collections.singletonMap("msg", "오류로 인해 댓글 등록에 실패했습니다.");
		}
	}
	
	@PostMapping("/reply/delete.do")
	public Map<String, Object> delete(@ModelAttribute("reply") ReplyVO replyVO,
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember){
		if(!replyVO.getMemId().equals(authMember.getMemId()))
			return Collections.singletonMap("msg", "해당 댓글의 작성자가 아닙니다.");
		try {
			replyService.removeReply(replyVO);
			return Collections.singletonMap("result", "OK");
		} catch (Exception e) {
			return Collections.singletonMap("msg", "오류로 인해 댓글 등록에 실패했습니다.");
		}
	}
}
