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
package kr.or.ddit.lms.professor.lecture.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.enrolment.vo.EnrolmentVO;
import kr.or.ddit.lms.professor.lecture.vo.InsertGradeVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.video.vo.LecPlanVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.SubjectVO;

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
@Repository
public interface IProfessorLectureDAO {

	List<InsertGradeVO> selectSubNameList(MemberVO authMember);

	EnrolmentVO selectLecCode(LectureVO lectureVO);

	int subPlanUpdate(SubjectVO subjectVO) throws Exception;

	int lecPlanUpdate(LectureVO lectureVO) throws Exception;

	List<EnrolmentVO> proScheduleList(MemberVO authMember);

	List<EnrolmentVO> selectProLectureList(MemberVO authMember);
	
	List<InsertGradeVO> selectLectureList(InsertGradeVO insertGrade);
	
	List<InsertGradeVO> selectStudentScoreList(InsertGradeVO insertGrade);
	
	int updateStudentScore(InsertGradeVO insertGrade);

	List<InsertGradeVO> selectGradeCodeList();

	int lecWeekPlan(List<LecPlanVO> planList);

	int lecPlanCount(LecPlanVO lecPlanVO);

	int lecWeekPlanUpdate(List<LecPlanVO> planList);

	List<LecPlanVO> selectPlanWeekContent(LecPlanVO lecPlanVO);

	LectureVO selectBK(LecPlanVO lecPlanVO);

}
