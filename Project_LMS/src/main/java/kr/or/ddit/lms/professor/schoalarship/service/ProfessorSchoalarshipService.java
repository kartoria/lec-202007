package kr.or.ddit.lms.professor.schoalarship.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.lms.professor.schoalarship.dao.IProfessorSchoalarshipDAO;
import kr.or.ddit.lms.professor.schoalarship.vo.StudentFormVO;
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
public class ProfessorSchoalarshipService {
	@Inject
	private IProfessorSchoalarshipDAO proschDAO;

	public List<MemberVO> retrieveStudentByDep(StudentFormVO stuVO) {
		return proschDAO.selectStudentByDep(stuVO);
	}

	public List<ScholarshipVO> retrieveRecommendScholarList() {
		return proschDAO.selectRecommendScholarList();
	}

	public int createRecommendScholarship(ScholarshipFundVO schVO) {
		return proschDAO.insertRecommendScholarship(schVO);
	}

	public List<ScholarshipFundVO> retrieveRecomScholarshipSmstList(MemberVO authMember) {
		return proschDAO.selectRecommScholarshipSmstList(authMember);
	}

	public int retrieveRecommendStudentCount(PagingVO<ScholarshipFundVO> paging) {
		return proschDAO.selectRecommendStudentCount(paging);
	}

	public List<ScholarshipFundVO> retrieveRecommendStudentList(PagingVO<ScholarshipFundVO> paging) {
		return proschDAO.selectRecommendStudentList(paging);
	}

	public ScholarshipFundVO checkRecommendRecord(ScholarshipFundVO schVO) {
		return proschDAO.checkRecommendRecord(schVO);
	}

	public int removeRecommendScholarship(ScholarshipFundVO schVO) {
		return proschDAO.deleteRecommendScholarship(schVO);
	}

	public ScholarshipFundVO retrieveRecommendDetail(ScholarshipFundVO schVO) {
		return proschDAO.selectRecommendDetail(schVO);
	}
	
}
