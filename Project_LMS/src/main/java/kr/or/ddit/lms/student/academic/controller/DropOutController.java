package kr.or.ddit.lms.student.academic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DropOutController {

	
	@RequestMapping("/lms/student/academic/dropout.do")
	public String droutOut(Model model) {
		model.addAttribute("pageTitle", "자퇴 신청서 발급");
		return "lms/academic/dropOutview";
	}
}
