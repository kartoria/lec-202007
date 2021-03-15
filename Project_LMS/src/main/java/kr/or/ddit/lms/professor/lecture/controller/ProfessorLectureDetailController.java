package kr.or.ddit.lms.professor.lecture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfessorLectureDetailController {
	@RequestMapping("/lms/professor/class/detail.do")
	public String proClassDetail() {
		return "lmsProfessor/class/lmsProClassDetail";
	}
}
