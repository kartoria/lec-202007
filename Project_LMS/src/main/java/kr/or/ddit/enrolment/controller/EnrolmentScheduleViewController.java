/**
 * @author PC-17
 * @since 2021. 1. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 23.     PC-17      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
package kr.or.ddit.enrolment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author PC-17
 * @since 2021. 1. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 23.     PC-17      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class EnrolmentScheduleViewController {
	
	@RequestMapping("/enrolment/Schedule.do")
	public void ScheduleView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/lms/lmsGrade/classSchedule.jsp").forward(req, resp);
	}
}
