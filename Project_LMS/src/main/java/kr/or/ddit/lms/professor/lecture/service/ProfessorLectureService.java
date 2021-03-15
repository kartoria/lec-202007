package kr.or.ddit.lms.professor.lecture.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.commons.service.BaseService;
import kr.or.ddit.enrolment.vo.EnrolmentVO;
import kr.or.ddit.lms.professor.lecture.dao.IProfessorLectureDAO;
import kr.or.ddit.lms.professor.lecture.vo.InsertGradeVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.video.vo.LecPlanVO;
import kr.or.ddit.vo.LecPlanListVO;
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
@Service
public class ProfessorLectureService extends BaseService {
	@Inject
	IProfessorLectureDAO dao;
	
	// 교수별 개설 과목 조회
	public List<InsertGradeVO> selectSubNameList(MemberVO authMember) {
		return dao.selectSubNameList(authMember);
	}
	
	public EnrolmentVO selectLecCode(LectureVO lectureVO) {
		return dao.selectLecCode(lectureVO);
	}
	
	@Transactional
	public int planUpdate(SubjectVO subjectVO, LectureVO lectureVO,List<LecPlanVO> planList) throws Exception{
		
		int result1 = dao.lecPlanUpdate(lectureVO);
		int result=0;
		if(result1>0) {
			//주차별 강의 내용이 있는지 체크
			int planCount = dao.lecPlanCount(planList.get(1));
			if(planCount>0) {
				//있으면 update
				result = dao.lecWeekPlanUpdate(planList);
				if(result==-1) {
					result=1;
				}
			}else {
				//없으면 insert
				result= dao.lecWeekPlan(planList);
			}
			
		}
		return result;
	}
	
	//교수 시간표 조회
	public List<EnrolmentVO> proScheduleList(MemberVO authMember) {
		return dao.proScheduleList(authMember);
	}
	
	//교수 본인 수강 과목 리스트
	public List<EnrolmentVO> selectproLectureList(MemberVO authMember) {
		return dao.selectProLectureList(authMember);
	}
	
	public List<InsertGradeVO> selectLectureList(InsertGradeVO insertGrade){
		return dao.selectLectureList(insertGrade);
	}
	
	public List<InsertGradeVO> selectStudentScoreList(InsertGradeVO insertGrade){
		return dao.selectStudentScoreList(insertGrade);
	}
	
	public void updateStudentScore(InsertGradeVO insertGrade) throws RuntimeException{
		int result = dao.updateStudentScore(insertGrade);
		if(result<=0) throw new RuntimeException();
	}

	public List<InsertGradeVO> seleteGradeCodeList() {
		return dao.selectGradeCodeList();
	}

	public List<LecPlanVO> selectPlanWeekContent(LecPlanVO lecPlanVO) {
		return dao.selectPlanWeekContent(lecPlanVO);
	}

	public LectureVO selectBK(LecPlanVO lecPlanVO) {
		return dao.selectBK(lecPlanVO);
	}
}
