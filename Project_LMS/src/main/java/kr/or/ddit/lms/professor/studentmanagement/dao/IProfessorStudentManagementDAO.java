package kr.or.ddit.lms.professor.studentmanagement.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

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
public interface IProfessorStudentManagementDAO {

	int selectMemberListCount(PagingVO<MemberVO> paging);
	List<MemberVO> selectMemberList(PagingVO<MemberVO> paging);
	List<MemberVO> selectAllMemberList(PagingVO<MemberVO> paging);


}
