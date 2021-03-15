package kr.or.ddit.admin.main.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.main.dao.IAdminMainDAO;
import kr.or.ddit.admin.student.vo.AdminMemVO;
import kr.or.ddit.commons.vo.ScheduleVO;
import kr.or.ddit.vo.BoardVO;

@Service
public class AdminMainService {
	@Inject
	private IAdminMainDAO dao;
	
	public List<AdminMemVO> retrieveAdmissionYearList() {
		return dao.selectAdmissionYearList();
	}

	public int retrieveAllStudentCount() {
		return dao.selectAllStudentCount();
	}

	public int retrieveAllLectureCount() {
		return dao.selectAllLectureCount();
	}

	public int retrieveAllDepartmentCount() {
		return dao.selectAllDepartmentCount();
	}

	public int retrieveAllProfessorCount() {
		return dao.selectAllProfessorCount();
	}

	public List<BoardVO> retrieveRecentNotice() {
		return dao.selectRecentNotice();
	}

	public List<ScheduleVO> retrieveYearScheduleList() {
		return dao.selectYearScheduleList();
	}

	public List<AdminMemVO> retrieveDepartmentStudentCount() {
		return dao.selectDepartmentStudentCount();
	}

}
