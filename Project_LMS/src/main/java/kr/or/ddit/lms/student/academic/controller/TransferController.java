package kr.or.ddit.lms.student.academic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class TransferController {
	@RequestMapping("/lms/student/academic/transfer.do")
	public String previous(Model model) {
		model.addAttribute("pageTitle", "전과 신청서 발급");
		return "lms/academic/transferview";
	}
}
