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
package kr.or.ddit.admin.student.service;

import java.util.List;

import javax.inject.Inject;
import javax.swing.tree.TreeModel;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.student.dao.IAdminStudentDAO;
import kr.or.ddit.admin.student.vo.AdminDepartmentInfoVO;
import kr.or.ddit.admin.student.vo.AdminMemVO;
import kr.or.ddit.admin.student.vo.SearchMemFormVO;
import kr.or.ddit.admin.student.vo.StudentListVO;
import kr.or.ddit.admin.student.vo.TreeModelVO;
import kr.or.ddit.commons.vo.IssuedVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.PagingVO;

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
public class AdminStudentService {
	@Inject
	IAdminStudentDAO dao;
	
//	//팬시트리 리스트
//	public List<MemberVO> selectMemberHierarchy(MemberVO member) {
//		return dao.selectMemberHierarchy(member);
//	}
//	//팬시트리 학부리스트
//	public List<CodeVO> selectCollegeHierarchy(String collegeId) {
//		return dao.selectCollegeHierarchy(collegeId);
//	}
//	//팬시트리 학과리스트
//	public List<DepartmentVO> selectDepartMentHierarchy() {
//		return dao.selectDepartmentHierarchy();
//	}
	//학부리스트 조회
	public List<CodeVO> retrieveCollegeList() {
		return dao.selectCollegeList();
	}
	//학과리스트 조회
	public List<DepartmentVO> retrieveDepartmentList(CodeVO colVO) {
		return dao.selectDepartmentList(colVO);
	}
	//학적 상태 리스트 조회
	public List<CodeVO> retrieveStateList() {
		return dao.selectStateList();
	}
	//학생 조회 및 검색
	public List<AdminMemVO> selectMemberList(SearchMemFormVO searchStuVO) {
		return dao.selectMemberList(searchStuVO);
	}

	//학과 코드 조회
	public DepartmentVO selectDepNo(DepartmentVO departmentVO) {
		return dao.selectDepNo(departmentVO);
	}
	//학생 일괄 등록 처리
	public int insertStudent(List<MemberVO> memberList) {
		return dao.insertStudent(memberList);
	}
	//마지막 학번 받아오기
	public MemberVO selectMaxMemId(MemberVO member) {
		return dao.selectMaxMemId(member);
	}
	//학적 정보 수정하기
	public int modifyStudentState(StudentListVO stuList) {
		return dao.updateStudentState(stuList);
	}
	//학생 상세정보
	public AdminMemVO retrieveStudent(AdminMemVO stuVO) {
		return dao.selectStudent(stuVO);
	}
	//학생 정보 수정
	public int modifyStudent(AdminMemVO stuVO) {
		return dao.updateStudent(stuVO);
	}
	//출력 대장 카운트
	public int IssuedCount(PagingVO<IssuedVO> paging) {
		return dao.IssuedCount(paging);
	}
	public List<IssuedVO> issuedList(PagingVO<IssuedVO> paging) {
		return dao.issuedList(paging);
	}
	public int IssuedSearchCount(PagingVO<IssuedVO> paging) {
		return dao.IssuedSearchCount(paging);
	}
	public List<IssuedVO> issuedSearchList(PagingVO<IssuedVO> paging) {
		return dao.issuedSearchList(paging);
	}
	//학부 tree구조 조회
	public List<TreeModelVO> retreveCollegeHierarchy() {
		return dao.selectCollegeHierarchy();
	}
	//학과 tree구조 조회
	public List<TreeModelVO> retrieveDepartmentHierarchy() {
		return dao.selectDepartmentHierarchy();
	}
	//교수 tree구조 조회
	public List<TreeModelVO> retrieveProfessorHierarchy() {
		return dao.selectProfessorHierarchy();
	}
	// 학부정보 페이지에서 학과정보 가져오기
	public AdminDepartmentInfoVO retrieveDepartmentInfo(DepartmentVO depVO) {
		return dao.selectDepartmentInfo(depVO);
	}
	// 학부정보 페이지에서 강의실정보 가져오기
	public List<CodeVO> retrieveLectureRoomInfo(DepartmentVO depVO) {
		return dao.selectLectureRoomInfo(depVO);
	}
	
	
	
}