package kr.or.ddit.admin.main.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.admin.student.vo.AdminMemVO;
import kr.or.ddit.commons.vo.ScheduleVO;
import kr.or.ddit.vo.BoardVO;

@Repository
public interface IAdminMainDAO {

	List<AdminMemVO> selectAdmissionYearList();

	int selectAllStudentCount();

	int selectAllLectureCount();

	int selectAllDepartmentCount();

	int selectAllProfessorCount();

	List<BoardVO> selectRecentNotice();

	List<ScheduleVO> selectYearScheduleList();

	List<AdminMemVO> selectDepartmentStudentCount();


}
