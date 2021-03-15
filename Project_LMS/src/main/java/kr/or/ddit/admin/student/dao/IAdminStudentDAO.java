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
package kr.or.ddit.admin.student.dao;

import java.util.List;

import javax.swing.tree.TreeModel;

import org.springframework.stereotype.Repository;

import kr.or.ddit.admin.student.vo.SearchMemFormVO;
import kr.or.ddit.admin.student.vo.StudentListVO;
import kr.or.ddit.admin.student.vo.TreeModelVO;
import kr.or.ddit.commons.vo.IssuedVO;
import kr.or.ddit.admin.student.vo.AdminDepartmentInfoVO;
import kr.or.ddit.admin.student.vo.AdminMemVO;
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
@Repository
public interface IAdminStudentDAO {

	List<MemberVO> selectMemberHierarchy(MemberVO member);

	List<CodeVO> selectCollegeHierarchy(String collegeId);

	List<TreeModelVO> selectDepartmentHierarchy();

	List<CodeVO> selectCollegeList();

	List<DepartmentVO> selectDepartmentList(CodeVO colVO);

	List<CodeVO> selectStateList();

	List<AdminMemVO> selectMemberList(SearchMemFormVO searchStuVO);


	DepartmentVO selectDepNo(DepartmentVO departmentVO);

	int insertStudent(List<MemberVO> memberList);

	MemberVO selectMaxMemId(MemberVO member);

	int updateStudentState(StudentListVO stuList);

	AdminMemVO selectStudent(AdminMemVO stuVO);

	int updateStudent(AdminMemVO stuVO);


	int IssuedCount(PagingVO<IssuedVO> paging);

	List<IssuedVO> issuedList(PagingVO<IssuedVO> paging);

	int IssuedSearchCount(PagingVO<IssuedVO> paging);

	List<IssuedVO> issuedSearchList(PagingVO<IssuedVO> paging);

	List<TreeModelVO> selectCollegeHierarchy();

	List<TreeModelVO> selectProfessorHierarchy();

	AdminDepartmentInfoVO selectDepartmentInfo(DepartmentVO depVO);

	List<CodeVO> selectLectureRoomInfo(DepartmentVO depVO);


}
