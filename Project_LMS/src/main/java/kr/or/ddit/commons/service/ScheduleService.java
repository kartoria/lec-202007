package kr.or.ddit.commons.service;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.datasource.DataSourceException;
import org.springframework.stereotype.Service;

import kr.or.ddit.commons.dao.IScheduleDAO;
import kr.or.ddit.commons.vo.SPlanVO;
import kr.or.ddit.commons.vo.ScheduleVO;

@Service
public class ScheduleService {
	@Inject
	private IScheduleDAO scheduleDAO;
	
	public List<ScheduleVO> retreiveScheduleList(){
		return scheduleDAO.selectScheduleList();
	}
	
	public ScheduleVO retrieveSchedule(ScheduleVO scheVO) {
		return scheduleDAO.selectSchedule(scheVO);
	}
}
