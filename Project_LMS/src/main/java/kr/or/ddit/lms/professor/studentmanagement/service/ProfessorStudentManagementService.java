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
package kr.or.ddit.lms.professor.studentmanagement.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.lms.professor.studentmanagement.dao.IProfessorStudentManagementDAO;
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
@Service
public class ProfessorStudentManagementService {

	@Inject
	IProfessorStudentManagementDAO dao ;

	//멤버리스트 카운ㅌ트
	public int selectMemberListCount(PagingVO<MemberVO> paging) {
		return dao.selectMemberListCount(paging);
	}
	
	//멤버 리스트 
	public List<MemberVO> selectMemberList(PagingVO<MemberVO> paging) {
		return dao.selectMemberList(paging);
	}
	//엑셀 다운용 학생 전체 리스트
	public List<MemberVO> selectAllMemberList(PagingVO<MemberVO> paging) {
		return dao.selectAllMemberList(paging);
	}

	
	
}
