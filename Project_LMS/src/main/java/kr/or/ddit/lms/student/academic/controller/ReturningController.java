package kr.or.ddit.lms.student.academic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ReturningController {
	@RequestMapping("/lms/student/academic/returning.do")
	public String returning(Model model) {
		model.addAttribute("pageTitle", "복학 신청서 발급");
		return "lms/academic/returningview";
	}
}
