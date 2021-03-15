package kr.or.ddit.lms.student.counseling.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.lms.student.counseling.service.CounselingService;
import kr.or.ddit.vo.CounselingVO;

@Controller
public class CounselingDetailController {
	
	@Inject
	private CounselingService service;	
	
	@RequestMapping("/lms/student/counseling/detail.do")
	public String detail(
			@RequestParam("what") String cstNo,
			Model model
			) {
		CounselingVO coun = service.selectDetailCounseling(cstNo);
		model.addAttribute("coun", coun);
		model.addAttribute("pageLink", "/lms/student/counseling/list.do");
		model.addAttribute("pageTitle", "상담 상세 조회");
		return "lms/counseling/counselingDetail";
	}
}
