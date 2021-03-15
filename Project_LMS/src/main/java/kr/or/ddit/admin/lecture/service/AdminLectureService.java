package kr.or.ddit.admin.lecture.service;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.lecture.dao.AdminLectureDAO;
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
@Service
public class AdminLectureService {
	@Inject
	private AdminLectureDAO lectureDAO;

	public CodeVO retrieveCurrentSmst(CodeVO currentSmst){
		return lectureDAO.selectCurrentSmst(currentSmst);
	}
	public List<CodeVO> retrieveCollegeList(){
		return lectureDAO.selectCollegeList();
	}
	public List<LectureVO> retriveLecSmstList() {
		return lectureDAO.selectLecSmstList();
	}
	public List<DepartmentVO> retrieveDepartmentList(String college){
		return lectureDAO.selectDepartmentList(college);
	}
	public List<SubjectVO> retrieveSubjectList(String depNo){
		return lectureDAO.selectSubjectList(depNo);
	}
	public List<CodeVO> retrieveRoomList(String college){
		return lectureDAO.selectRoomList(college);
	}
	public List<LectureVO> retrieveAvaliableDays(AdminLectureVO lecVO){
		return lectureDAO.selectAvaliableDays(lecVO);
	}
	public List<LectureVO> retrieveProfessorTimeTable(AdminLectureVO adminlecVO) {
		return lectureDAO.selectProfessorTimeTable(adminlecVO);
	}
	@Transactional
	public String createLecture(LectureVO lecVO, List<LectureTimeVO> lecTimeList){
		int cnt = lectureDAO.insertLecture(lecVO);
		String lecCode = null;
		if(lecVO.getLecCode()!=null) {
		for(int i=0;i<lecTimeList.size();i++) {
			lecTimeList.get(i).setLecCode(lecVO.getLecCode());
		}
			cnt += getLecCode(lecTimeList);
			
		}
		if(cnt>0) {
			lecCode = lecVO.getLecCode();
		}
		return lecCode;
	}
	
	private int getLecCode(List<LectureTimeVO> lecTimeList){
		String lecCode = null;
//		lecCode = lectureDAO.selectLecCode(lecVO);
		
		int cnt =  lectureDAO.insertLectureRoom(lecTimeList);
		
		return cnt;
	}
	public int retrieveLectureCount(PagingVO<AdminLectureVO> pagingVO){
		return lectureDAO.selectLectureCount(pagingVO);
	}
	public List<AdminLectureVO> retrieveLectureList(PagingVO<AdminLectureVO> pagingVO){
		return lectureDAO.selectLectureList(pagingVO);
	}
	public LectureVO retrieveLecture(LectureVO lecVO){
		return lectureDAO.selectLecture(lecVO);
	}
	public List<LectureTimeVO> retrieveLectureTimeList(LectureVO lecVO){
		return lectureDAO.selectLectureTimeList(lecVO);
	}
	public List<MemberVO> retrieveProfessorList(String depNo){
		return lectureDAO.selectProfessorList(depNo);
	}
	public int modifyLecture(LectureVO lecVO){
		return lectureDAO.updateLecture(lecVO);
	}
	@Transactional
	public int deleteLecture(LectureVO lecVO){
		int cnt = lectureDAO.deleteLecture(lecVO);
		if(cnt>0) {
			cnt += deleteLecRoom(lecVO);
			
		}
		return cnt;
	}
	
	private int deleteLecRoom(LectureVO lecVO) {
		int cnt =  lectureDAO.deleteLectureRoom(lecVO);
		return cnt;
		
	}

	
}
