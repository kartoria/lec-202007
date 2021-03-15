package kr.or.ddit.admin.calendar.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.admin.calendar.service.CalendarService;
import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.commons.vo.ScheduleVO;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;

/**
 * @author 조예슬
 * @since 2021. 2. 09.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 09.    조예슬       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class CalendarController extends BaseController{
	@Inject
	private CalendarService service;
	/**관리자 학사일정 조회**/
	@RequestMapping("/admin/calendarView.do")
	public String adminCalendarMain(Model model) throws JsonProcessingException{
		List<ScheduleVO> dataList;
		try {
			dataList = service.retreiveScheduleList();
			ObjectMapper mapper = new ObjectMapper();
			String scheduleList = mapper.writeValueAsString(dataList);
			model.addAttribute("scheduleList", scheduleList);
			model.addAttribute("pageTitle", "학사 일정 관리");
		} catch (DataAccessException e) {
			model.addAttribute("msg", notyErrorMessage());
		}
		return "admin/addCalendar/CalendarIndex";
	}

	@ModelAttribute("scheduleVO")
	public ScheduleVO scheduleVO() {
		return new ScheduleVO();
	}
	/**관리자학사일정 등록폼**/
	@RequestMapping("/admin/insertscheduleForm.do")
	public String insertscheduleForm(Model model) {

		return "admin/addCalendar/modal/newschedule";
	}
	/**관리자 학사일정 등록**/
	@RequestMapping(value="/admin/insertschedule.do",method=RequestMethod.POST)
	public String insertschedule(@ModelAttribute("scheduleVO")ScheduleVO scheduleVO,RedirectAttributes redirectModel,Model model) {
		try {
			int cnt = service.createSchedule(scheduleVO);
			redirectModel.addFlashAttribute("msg", notyInsertSuccessMessage());
			return "redirect:/admin/calendarView.do";
		} catch (DataAccessException e) {
			model.addAttribute("msg", notyErrorMessage());
		}

		return "admin/addCalendar/modal/newschedule";
	}
	/**일정 상세보기**/
	@RequestMapping("/admin/{scheNo}/scheduleView.do")
	public String scheduleView(@PathVariable(value="scheNo", required=true) int scheNo,
								Model model) {
		ScheduleVO scheVO = new ScheduleVO();
		scheVO.setScheNo(scheNo);
		scheVO = service.retrieveSchedule(scheVO);
		model.addAttribute("scheVO", scheVO);
		return "admin/addCalendar/modal/scheduleView";
	}
	/**일정 수정**/
	@RequestMapping(value="/admin/updateSchedule.do",method=RequestMethod.POST)
	public String updateSchedule(@ModelAttribute("scheVO")ScheduleVO scheVO,Model model,RedirectAttributes redirectModel) {
		try {
			int cnt = service.modifySchedule(scheVO);
			redirectModel.addFlashAttribute("msg", notyErrorMessage());
			return "redirect:/admin/calendarView.do";
		} catch (DataAccessException e) {
			model.addAttribute("msg", notyErrorMessage());
			return "admin/addCalendar/modal/scheduleView";
		}
	}
	/**일정삭제**/
	@RequestMapping(value="/admin/deleteSchedule.do",method=RequestMethod.POST)
	public String deleteSchedule(@ModelAttribute("scheVO")ScheduleVO scheVO,Model model,RedirectAttributes redirectModel) {
		try {
			int cnt = service.removeSchedule(scheVO);
			redirectModel.addFlashAttribute("msg", notyDeleteSuccessMessage());
			return "redirect:/admin/calendarView.do";
		} catch (DataAccessException e) {
			model.addAttribute("msg", notyErrorMessage());
			return "admin/addCalendar/modal/scheduleView";
		}
	}
	

}
