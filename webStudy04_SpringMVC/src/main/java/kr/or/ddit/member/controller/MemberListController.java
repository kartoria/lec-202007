package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@RequestMapping("/member/memberList.do")
@Controller
public class MemberListController {
	private static final Logger logger = LoggerFactory.getLogger(MemberListController.class);
	
	@Resource(type=MemberServiceImpl.class)
	private IMemberService service;
	
	@RequestMapping
	public String memberList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("searchVO") SearchVO searchVO
			, Model model
	) throws ServletException, IOException {
		
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 2);
		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);
		
		int totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "member/memberList";
	}
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String memberListForAjax(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("searchVO") SearchVO searchVO
			, Model model
	) throws ServletException, IOException {
		memberList(currentPage,searchVO, model);
		
		return "jsonView";
	}
}
