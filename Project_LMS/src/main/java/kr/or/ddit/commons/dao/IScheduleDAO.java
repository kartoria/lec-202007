package kr.or.ddit.commons.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.datasource.DataSourceException;
import org.springframework.stereotype.Repository;

import kr.or.ddit.commons.vo.SPlanVO;
import kr.or.ddit.commons.vo.ScheduleVO;
import kr.or.ddit.vo.TuitionVO;

@Repository
public interface IScheduleDAO {

	List<ScheduleVO> selectScheduleList();

	int insertSchedule(ScheduleVO scheduleVO) ;
	
	ScheduleVO selectSchedule(ScheduleVO scheVO);

	int deleteSchedule(ScheduleVO scheVO);

	int updateSchedule(ScheduleVO scheVO);

	List<SPlanVO> getSchedule() throws DataSourceException;
	
}
