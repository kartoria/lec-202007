package kr.or.ddit.lms.student.grade.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import kr.or.ddit.lms.student.grade.dao.IGradeDAO;
import kr.or.ddit.lms.student.grade.vo.AttendanceVO;
import kr.or.ddit.lms.student.grade.vo.GradeVO;

/**
 * 학생lms의 학점 및 성적 카테고리
 * @author 김선준
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
public class GradeService {
	
	@Inject
	IGradeDAO gradeDAO;
	
	// 전체학기 성적 정보
	public GradeVO getTotalGrade(GradeVO gradeVO) throws DataAccessException{
		List<GradeVO> gradeList = gradeDAO.getTotalGrade(gradeVO);
		
		int totalCredit         = 0;  // 총 신청학점                                         
		int totalGetCredit      = 0;  // 총 획득 학점                                         
		float totalGradeMulCredit = 0;  // 평점 계 (학점 * 평점 ex 3학점 과목을 A+받으면 3*4.5 = 13.5)    
		float avgGradeMulCredit   = 0;  // 평점 평균 (평점 계 / 총 신청학점)                           
		int totalGEGrade        = 0;  // general elective 교양 선택                          
		int totalGRGrade        = 0;  // general required 교양 필수                          
		int totalMEGrade        = 0;  // major elective 전공 선택                            
		int totalMRGrade		= 0;  // major required 전공필수                             
		int totalFEGrade		= 0;  // 자유선택                             
		
		for(GradeVO tmp : gradeList) {
			float codeName = Float.parseFloat(tmp.getCodeName());
			totalCredit += tmp.getSubCredit();
			
			if(codeName != 0) totalGetCredit += tmp.getSubCredit();
			totalGradeMulCredit += tmp.getSubCredit() * codeName;
			
			switch(tmp.getSubDetail()){
			case "교선": totalGEGrade += tmp.getSubCredit();
			case "교필": totalGRGrade += tmp.getSubCredit();
			case "전선": totalMEGrade += tmp.getSubCredit();
			case "전필": totalMRGrade += tmp.getSubCredit();
			case "자선": totalFEGrade += tmp.getSubCredit();
			}
		}
		avgGradeMulCredit = Math.round(totalGradeMulCredit / totalCredit*100f)/100f;
		
		return GradeVO.builder().totalCredit(totalCredit)
				.totalGetCredit(totalGetCredit)
				.totalGradeMulCredit(totalGradeMulCredit)
				.avgGradeMulCredit(avgGradeMulCredit)
				.totalGEGrade(totalGEGrade)
				.totalGRGrade(totalGRGrade)
				.totalMEGrade(totalMEGrade)
				.totalMRGrade(totalMRGrade)
				.totalFEGrade(totalFEGrade).build();
	}

	// 내 학기 목록
	public List<GradeVO> getSMSTList(GradeVO gradeVO) throws DataAccessException {
		return gradeDAO.getSMSTList(gradeVO);
	}

	// 선택한 학기의 정보
	public List<GradeVO> getSMSTGradeList(GradeVO gradeVO) throws DataAccessException {
		return gradeDAO.getSMSTGradeList(gradeVO);
	}
	
	// 선택한 학기의 석차
	public String getRank(GradeVO gradeVO) throws DataAccessException {
		return gradeDAO.getRank(gradeVO);
	}

	
	
	// 금학기 평점정보
	public List<GradeVO> getNowSMSTGrade(GradeVO gradeVO) throws DataAccessException {
		return gradeDAO.getNowSMSTGrade(gradeVO);
	}

	public void evaluationLecture(GradeVO gradeVO) throws DataAccessException {
		gradeDAO.updateTlecScore(gradeVO);
	}

	public List<GradeVO> getNowSMSTLectureList(GradeVO gradeVO) throws DataAccessException {
		return gradeDAO.getNowSMSTLectureList(gradeVO);
	}
	
	public List<AttendanceVO> getAttendance(GradeVO gradeVO) throws DataAccessException{
		return gradeDAO.getAttendance(gradeVO);
	}

	public AttendanceVO getTotalAttendance(GradeVO gradeVO) throws DataAccessException {
		return gradeDAO.getTotalAttendance(gradeVO);
	}
	


	
	
}
