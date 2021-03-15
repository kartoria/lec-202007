package kr.or.ddit.admin.student.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.admin.student.service.AdminStudentService;
import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.commons.vo.IssuedVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class AdminStudentManagement extends BaseController{
	
	@Inject
	AdminStudentService service;
	
//	@RequestMapping("/admin/studentManagement.do")
//	public String studentManagement(@RequestParam(value="base", required=false) String manager_id, 
//			HttpServletRequest req, HttpServletResponse resp,Model model) throws ServletException, IOException {
//
//		
//		MemberVO memberVO = new MemberVO();
//		memberVO.setMemId(manager_id);
//		List<MemberVO> list = service.selectMemberHierarchy(memberVO);
//		LOGGER.info("--------------------------{}",list);
//		String accept = req.getHeader("Accept");
//		if(StringUtils.containsIgnoreCase(accept, "json")) {
//			resp.setContentType("application/json;charset=UTF-8");
//			try(
//				PrintWriter out = resp.getWriter();	
//			){
//				ObjectMapper mapper = new ObjectMapper();
//				mapper.writeValue(out, list);
//			}
//			return null;
//		}else {
//			model.addAttribute("children", list);
//			return "admin/adminStudent/studentManagement";
//		}
//		
//	}
	
	
	
	//증명서 발급 내역
	@RequestMapping("/admin/studentIssuedList.do")
	public String issuedList(
			@RequestParam(value="page", required=false,defaultValue="1") int currentPage
			,@ModelAttribute("searchVO") SearchVO searchVO 
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			Model model)  {
		PagingVO<IssuedVO> paging = new PagingVO<>(10,10);
//		paging.setSearchDetail();
		paging.setSearchVO(searchVO);
		String searchType = searchVO.getSearchType();
		int totalRecord =0;
		List<IssuedVO> issuedList =null;
		if(searchType==null|| searchType==""|| "".equals(searchType)) {
			totalRecord=service.IssuedCount(paging);
			paging.setTotalRecord(totalRecord);
			paging.setCurrentPage(currentPage);
			issuedList = service.issuedList(paging);
		}else {
			totalRecord=service.IssuedSearchCount(paging);
			paging.setTotalRecord(totalRecord);
			paging.setCurrentPage(currentPage);
			issuedList = service.issuedSearchList(paging);
			
		}
		paging.setDataList(issuedList);
		model.addAttribute("paginationInfo", new CustomPaginationInfo<IssuedVO>(paging));		
		model.addAttribute("pageTitle", "문서발급내역");
		
		return "admin/adminStudent/studentIssuedList";
		
	}
	
}
