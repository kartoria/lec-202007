/**
 * @author PC-NEW08
 * @since 2021. 1. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 22.     PC-NEW08      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
package kr.or.ddit.admin.calendar.service;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.calendar.dao.ICalenderDAO;
import kr.or.ddit.commons.dao.IScheduleDAO;
import kr.or.ddit.commons.vo.ScheduleVO;

/**
 * @author PC-NEW08
 * @since 2021. 1. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 22.     PC-NEW08      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Service
public class CalendarService {
	@Inject
	private IScheduleDAO scheDAO;

	public List<ScheduleVO> retreiveScheduleList() {
		return scheDAO.selectScheduleList();
	}
	public int createSchedule(ScheduleVO scheduleVO){
		return scheDAO.insertSchedule(scheduleVO);
	}
	public ScheduleVO retrieveSchedule(ScheduleVO scheVO) {
		return scheDAO.selectSchedule(scheVO);
	}
	public int removeSchedule(ScheduleVO scheVO) {
		return scheDAO.deleteSchedule(scheVO);
	}
	public int modifySchedule(ScheduleVO scheVO) {
		return scheDAO.updateSchedule(scheVO);
	}


	
}
