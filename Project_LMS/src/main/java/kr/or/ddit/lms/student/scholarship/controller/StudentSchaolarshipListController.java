package kr.or.ddit.lms.student.scholarship.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.lms.student.scholarship.service.StudentSchaolarshipService;
import kr.or.ddit.lms.student.scholarship.vo.StuScholarshipVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
/**
 * @author 조예슬
 * @since 2021. 1. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 28.     조예슬       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class StudentSchaolarshipListController {
	@Inject
	private StudentSchaolarshipService service;
	/**학생장학금 수혜내역 조회**/
	@RequestMapping("/lms/student/schaolarship/list.do")
	public String SchaolarshipList(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
								@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
								@ModelAttribute("searchVO") SearchVO searchVO,
								Model model ) {
		List<StuScholarshipVO> smst = service.retrieveStuScholarshipSmstList(authMember);
		PagingVO<StuScholarshipVO> paging =new PagingVO<>(5,5);
		paging.setSearchVO(searchVO);
		paging.setSearchDetail((StuScholarshipVO.builder().sfundStudent(authMember.getMemId()).codeResult("PAYED").build()));
		
		int totalRecord = service.retrieveStuScholarshipCount(paging);
		paging.setTotalRecord(totalRecord);
		paging.setCurrentPage(currentPage);
		List<StuScholarshipVO> schList = service.retrieveStuScholarshipList(paging);
		paging.setDataList(schList);
		
		model.addAttribute("semeSchList", smst);
		model.addAttribute("paginationInfo", new CustomPaginationInfo<StuScholarshipVO>(paging));
		
		return "lms/schaolarship/stuSchaolarshipList";
	}
}
