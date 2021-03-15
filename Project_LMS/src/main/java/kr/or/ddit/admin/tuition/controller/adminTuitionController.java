package kr.or.ddit.admin.tuition.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.admin.tuition.service.AdminTuitionService;
import kr.or.ddit.lms.student.tuition.vo.VirtualAccountTuitionVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class adminTuitionController {
	@Inject
	private AdminTuitionService service;
	
	@RequestMapping("/admin/tuition.do")
	public String adminTuition(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model
			) {
		
		PagingVO<VirtualAccountTuitionVO> paging = new PagingVO<>(10,5);
		paging.setSearchVO(searchVO);
//		paging.setSearchDetail(VirtualAccountTuitionVO.builder().memId(authMember.getMemId()).build());
		
		int totalRecord = service.selectVirttualCount();
		paging.setTotalRecord(totalRecord);
		paging.setCurrentPage(currentPage);
		
		List<VirtualAccountTuitionVO> vacountList = service.selectVirtualAccountTuition(paging);
		paging.setDataList(vacountList);
		
		model.addAttribute("paginationInfo", new CustomPaginationInfo<VirtualAccountTuitionVO>(paging));
		model.addAttribute("pageTitle", "등록금 납부 내역");
		return "admin/adminTuition/adminTuitionList";
	}
}
