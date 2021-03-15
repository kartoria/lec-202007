package kr.or.ddit.lms.student.grade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LectureScheduleController {

	@RequestMapping("/lms/student/grade/classSchedule.do")
	public String classSchedule(Model model){
		model.addAttribute("pageTitle", "시간표");

		return "lms/lmsGrade/classSchedule";
	}
}
