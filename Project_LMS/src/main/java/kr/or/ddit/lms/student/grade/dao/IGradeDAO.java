package kr.or.ddit.lms.student.grade.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lms.student.grade.vo.AttendanceVO;
import kr.or.ddit.lms.student.grade.vo.GradeVO;

@Repository
public interface IGradeDAO {
	
	public List<GradeVO> getTotalGrade(GradeVO gradeVO) throws DataAccessException;
	
	public List<GradeVO> getSMSTList(GradeVO gradeVO) throws DataAccessException;
	
	public String getRank(GradeVO gradeVO) throws DataAccessException;
	
	public List<GradeVO> getSMSTGradeList(GradeVO gradeVO) throws DataAccessException;
	
	public List<GradeVO> getNowSMSTGrade(GradeVO gradeVO) throws DataAccessException;

	public void updateTlecScore(GradeVO gradeVO) throws DataAccessException;
	
	public List<GradeVO> getNowSMSTLectureList(GradeVO gradeVO) throws DataAccessException;
	
	public List<AttendanceVO> getAttendance(GradeVO gradeVO) throws DataAccessException;

	public AttendanceVO getTotalAttendance(GradeVO gradeVO) throws DataAccessException;
	
	
}
