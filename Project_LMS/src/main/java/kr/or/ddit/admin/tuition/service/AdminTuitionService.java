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
package kr.or.ddit.admin.tuition.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.tuition.dao.IAdminTuitionDAO;
import kr.or.ddit.lms.student.tuition.vo.VirtualAccountTuitionVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.LectureVO;
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
public class AdminTuitionService {
	@Inject
	private IAdminTuitionDAO dao;
	
	//단과대 셀렉트
	public List<CodeVO> selectCodeName() {
		
		return dao.selectCodeName();
	}
	//학생 리스트 갖고오기
	public List<MemberVO> selectMember(PagingVO<MemberVO> paging) {
		return dao.selectMember(paging);
	}
	public int memberCount(PagingVO<MemberVO> paging) {
		return dao.memberCount(paging);
	}
	//학과 리스트 받아오는것
	public List<DepartmentVO> selectDepName(CodeVO code) {
		return dao.selectDepName(code);
	}
	
	public int memberDepCount(PagingVO<MemberVO> paging) {
		
		return dao.memberDepCount(paging);
	}
	//학과별 학생 리스트
	public List<MemberVO> selectDepstuList(PagingVO<MemberVO> paging) {
		return dao.selectDepStuList(paging);
	}
	public int adminTuitionUpdateSucess(MemberVO member) {
		return dao.adminTuitionUpdateSucess(member);
		
	}
	public int adminTuitionUpdateFail(MemberVO member) {
		return dao.adminTuitionUpdateFail(member);
		
	}
	//현재 년도 조회
	public CodeVO selectSmst() {
		return dao.selectSmst();
	}
	public int insertPayDiv(MemberVO member) {
		int result3 =0;
		//1차
		int result1 =  dao.insertPayDiv1(member);
		if(result1>0) {
			//2차
			int result2 =  dao.insertPayDiv2(member);
			if(result2>0) {
				//3차
				result3 =  dao.insertPayDiv3(member);
			}
		}
		
		return result3;
	}
	
	public List<VirtualAccountTuitionVO> selectVirtualAccountTuition(PagingVO<VirtualAccountTuitionVO> paging){
		return dao.selectVirtualAccountTuition(paging);
	}
	
	public int selectVirttualCount() {
		return dao.selectVirttualCount();
	}

}
