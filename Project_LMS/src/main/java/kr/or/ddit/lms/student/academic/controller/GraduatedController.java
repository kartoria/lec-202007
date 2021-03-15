package kr.or.ddit.lms.student.academic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class GraduatedController {

	@RequestMapping("/lms/student/academic/graduated.do")
	public String graduated(Model model) {
		model.addAttribute("pageTitle", "졸업 증명서 발급");
		return "lms/academic/graduatedview";
	}
}
