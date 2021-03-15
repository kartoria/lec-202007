package kr.or.ddit.admin.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.main.service.AdminMainService;
import kr.or.ddit.admin.student.vo.AdminMemVO;
import kr.or.ddit.commons.vo.ScheduleVO;
import kr.or.ddit.vo.BoardVO;
/**
 * @author 조예슬
 * @since 2021. 3. 02.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 3. 02.   조예슬       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class AdminMainController {
	@Inject
	private AdminMainService service;
	
	@RequestMapping("/admin/dashboard.do")
	public String adminDashBoardHome(Model model) {
		//1. 재학생수
		int stuCnt = service.retrieveAllStudentCount();
		//2. 개설강의
		int lecCnt = service.retrieveAllLectureCount();
		//3. 전체학과
		int depCnt = service.retrieveAllDepartmentCount();
		//4. 재직교수
		int proCnt = service.retrieveAllProfessorCount();
		//5. 최근공지글
		List<BoardVO> notyList = service.retrieveRecentNotice();
		//6. 연간학사일정
		List<ScheduleVO> schList =service.retrieveYearScheduleList();
		
		model.addAttribute("pageTitle", "관리자페이지");
		
		model.addAttribute("stuCnt", stuCnt);
		model.addAttribute("lecCnt", lecCnt);
		model.addAttribute("depCnt", depCnt);
		model.addAttribute("proCnt", proCnt);
		model.addAttribute("notyList", notyList);
		model.addAttribute("schList", schList);
		
		return "admin/dashboard";
	}
	
	@RequestMapping("/admin/cyber")
	public String adminCyber() {
		return "redirect:/cyber";
	}
	
	@RequestMapping(value="/admin/dashboardAjax.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, List<?>> adminDashBoardHomeForAjax() {
		//1. 연도별입학생
		List<AdminMemVO> stuList =service.retrieveAdmissionYearList();
		//2. 학과별 학생수
		List<AdminMemVO> depStuList = service.retrieveDepartmentStudentCount();
		Map<String, List<?>> map = new HashMap<>();
		map.put("stuList", stuList);
		map.put("depStuList", depStuList);
		return map;
	}
}
