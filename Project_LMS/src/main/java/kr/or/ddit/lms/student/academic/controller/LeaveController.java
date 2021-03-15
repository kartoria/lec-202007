package kr.or.ddit.lms.student.academic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class LeaveController {
	@RequestMapping("/lms/student/academic/leave.do")
	public String leave(Model model) {
		model.addAttribute("pageTitle", "휴학 신청서 발급");
		return "lms/academic/leaveview";
	}
}
