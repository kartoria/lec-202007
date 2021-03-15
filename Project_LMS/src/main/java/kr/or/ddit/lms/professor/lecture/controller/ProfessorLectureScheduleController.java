package kr.or.ddit.lms.professor.lecture.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enrolment.controller.EnrolmentPDFController;
import kr.or.ddit.enrolment.vo.EnrolmentVO;
import kr.or.ddit.lms.professor.lecture.service.ProfessorLectureService;
import kr.or.ddit.lms.student.tuition.service.TuitionService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.DepartmentVO;

/**
 * @author 전진원
 * @since 2021. 2. 8.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 8.     전진원             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class ProfessorLectureScheduleController {

	@Inject
	ProfessorLectureService service;
	
	@Autowired
	EnrolmentPDFController enrolmentPDFController;
	
	
	@Inject
	TuitionService tuitionService;
	
	
	@RequestMapping("/lms/professor/class/schedule.do")
	public String proSchedyle(Model model) {
		 model.addAttribute("pageTitle", "시간표");

		return "lmsProfessor/class/lmsProClassShedule";
	}
	
	
	
	//교수 개설강의 시간표
	@RequestMapping("/lms/professor/class/SchedulePDF.do")
	public void proClassschedule(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			HttpServletResponse response) throws Exception {
	
		List<EnrolmentVO> scheduleList = service.proScheduleList(authMember);
		
		 DepartmentVO departmentVO = tuitionService.selectDepName(authMember);

		 enrolmentPDFController.schedulePDF(scheduleList, response, authMember,departmentVO);
		
		
		
	}
	
}
