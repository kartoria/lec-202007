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
package kr.or.ddit.lms.student.scholarship.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.lms.student.scholarship.dao.StudentSchaolarshipMapper;
import kr.or.ddit.lms.student.scholarship.vo.StuScholarshipVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ScholarshipFundVO;
import kr.or.ddit.vo.ScholarshipVO;

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
public class StudentSchaolarshipService {
	@Inject
	private StudentSchaolarshipMapper schDAO;

	public List<StuScholarshipVO> retrieveStuScholarshipSmstList(MemberVO authMember) {
		return schDAO.selectStuScholarshipSmstList(authMember);
	}

	public int retrieveStuScholarshipCount(PagingVO<StuScholarshipVO> paging) {
		return schDAO.selectStuScholarshipCount(paging);
	}

	public List<StuScholarshipVO> retrieveStuScholarshipList(PagingVO<StuScholarshipVO> paging) {
		return schDAO.selectStuScholarshipList(paging);
	}

	public List<ScholarshipVO> retrieveSholarshipType(ScholarshipFundVO schFundVO) {
		return schDAO.selectScholarshipType(schFundVO);
	}

	public int createScholarshipApply(ScholarshipFundVO schFundVO) {
		return schDAO.insertScholarshipApply(schFundVO);
	}

	public List<ScholarshipFundVO> retrieveStuScholarshipApplySmst(MemberVO authMember) {
		return schDAO.selectStuScholarshipApplySmst(authMember);
	}

	public int retrieveStuScholarshipApplyCount(PagingVO<ScholarshipFundVO> paging) {
		return schDAO.selectStuScholarshipApplyCount(paging);
	}

	public List<ScholarshipFundVO> retrieveStuScholarshipApplyList(PagingVO<ScholarshipFundVO> paging) {
		return schDAO.selectStuScholarshipApplyList(paging);
	}

	public StuScholarshipVO retrieveStuScholarshipApply(int sfundNo) {
		return schDAO.selectStuScholarshipApply(sfundNo);
	}


}
