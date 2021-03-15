package kr.or.ddit.lms.student.counseling.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.lms.student.counseling.service.CounselingService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CounselingVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class CounselingListController extends BaseController{

	@Inject
	private CounselingService service;
	
	@GetMapping("/lms/student/counseling/list.do")
	public String lmsCounselingList(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model){
		
		PagingVO<CounselingVO> paging = new PagingVO<>(10,5);
		paging.setSearchVO(searchVO);
		paging.setSearchDetail(CounselingVO.builder().cstStudent(authMember.getMemId()).build());
		
		int totalRecord = service.selectCounselingDoneCount(paging);
		paging.setTotalRecord(totalRecord);
		paging.setCurrentPage(currentPage);
		List<CounselingVO> counList = service.retrieveCounselingDoneList(paging);
		paging.setDataList(counList);
		
		model.addAttribute("paginationInfo", new CustomPaginationInfo<CounselingVO>(paging));
		model.addAttribute("pageTitle", "상담 내역");
		return "lms/counseling/counselingList";
	}
}
