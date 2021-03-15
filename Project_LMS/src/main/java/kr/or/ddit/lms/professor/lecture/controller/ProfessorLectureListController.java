package kr.or.ddit.lms.professor.lecture.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enrolment.vo.EnrolmentVO;
import kr.or.ddit.lms.professor.lecture.service.ProfessorLectureService;
import kr.or.ddit.member.vo.MemberVO;

@Controller
public class ProfessorLectureListController {
	@Inject
	ProfessorLectureService service;
	
	@RequestMapping("/lms/professor/class/list.do")
	public String proClasslist(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			Model model) {
		
		List<EnrolmentVO> proLectureList = service.selectproLectureList(authMember);
		
		model.addAttribute("proLectureList", proLectureList);
		model.addAttribute("pageTitle", "개설 강의 목록");

		return "lmsProfessor/class/lmsProClassList";
	}
}
