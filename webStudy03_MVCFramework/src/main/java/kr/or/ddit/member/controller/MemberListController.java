package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.utils.JsonResponseUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class MemberListController{
	private static final Logger logger = LoggerFactory.getLogger(MemberListController.class);
	private IMemberService service = MemberServiceImpl.getInstance();
	
	@RequestMapping("/member/memberList.do")
	public String memberList(@RequestParam(value="page", requried=false, defaultValue="1") int currentPage
						   , @ModelAttribute("searchVO") SearchVO searchVO
						   , HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 2);
		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);
		
		int totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
		req.setAttribute("pagingVO", pagingVO);
		
		String accept = req.getHeader("Accept");
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			JsonResponseUtils.toJsonResponse(req, resp);
			return null;
		}else {
			return "member/memberList";
		}
	}
}
