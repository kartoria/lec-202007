package kr.or.ddit.admin.lecture.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.admin.lecture.vo.AdminLectureVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.LectureTimeVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.PagingVO;
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
public interface AdminLectureDAO {
	CodeVO selectCurrentSmst(CodeVO currentSmst);
	
	List<LectureVO> selectLecSmstList();
	
	List<CodeVO> selectCollegeList();

	List<DepartmentVO> selectDepartmentList(String college);

	List<SubjectVO> selectSubjectList(String depNo);

	List<CodeVO> selectRoomList(String college);

	List<LectureVO> selectAvaliableDays(AdminLectureVO lecVO);

	List<LectureVO> selectProfessorTimeTable(AdminLectureVO adminlecVO);
	
	int insertLecture(LectureVO lecVO);

	int insertLectureRoom(List<LectureTimeVO> lecTimeList);

	String selectLecCode(LectureVO lecVO);

	int selectLectureCount(PagingVO<AdminLectureVO> pagingVO);

	List<AdminLectureVO> selectLectureList(PagingVO<AdminLectureVO> pagingVO);

	LectureVO selectLecture(LectureVO lecVO);

	List<LectureTimeVO> selectLectureTimeList(LectureVO lecVO);

	List<MemberVO> selectProfessorList(String depNo);

	int updateLecture(LectureVO lecVO);

	int deleteLecture(LectureVO lecVO);

	int deleteLectureRoom(LectureVO lecVO);

	

	


	

}
