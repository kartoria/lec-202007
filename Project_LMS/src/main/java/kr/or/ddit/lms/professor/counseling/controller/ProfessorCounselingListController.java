package kr.or.ddit.lms.professor.counseling.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.lms.professor.counseling.service.ProfessorCounselingService;
import kr.or.ddit.lms.student.profile.VO.ProfileVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CounselingVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class ProfessorCounselingListController extends BaseController{

	@Inject
	private ProfessorCounselingService service;
	
	@RequestMapping("/lms/professor/counseling/list.do")
	public String proCounselinglist(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model
			) {
		PagingVO<CounselingVO> paging = new PagingVO<>(4, 5);
		paging.setSearchVO(searchVO);
		paging.setSearchDetail(CounselingVO.builder().cstProfessor(authMember.getMemId()).build());
		int totalRecord = service.retrieveCounselingDoneCount(paging);
		paging.setTotalRecord(totalRecord);
		paging.setCurrentPage(currentPage);
		List<CounselingVO> counList = service.selectCounselingDoneList(paging);
		
		paging.setDataList(counList);
		model.addAttribute("paginationInfo", new CustomPaginationInfo<CounselingVO>(paging));
		model.addAttribute("pageTitle", "상담 승인 내역");
		return "lmsProfessor/counseling/lmsProCounselingList";
	}
}
