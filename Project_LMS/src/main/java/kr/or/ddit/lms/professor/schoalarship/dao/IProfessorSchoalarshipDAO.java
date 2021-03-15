package kr.or.ddit.lms.professor.schoalarship.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
@Repository
public interface IProfessorSchoalarshipDAO {

	List<MemberVO> selectStudentByDep(StudentFormVO stuVO);

	List<ScholarshipVO> selectRecommendScholarList();

	int insertRecommendScholarship(ScholarshipFundVO schVO);

	List<ScholarshipFundVO> selectRecommScholarshipSmstList(MemberVO authMember);

	int selectRecommendStudentCount(PagingVO<ScholarshipFundVO> paging);

	List<ScholarshipFundVO> selectRecommendStudentList(PagingVO<ScholarshipFundVO> paging);

	ScholarshipFundVO checkRecommendRecord(ScholarshipFundVO schVO);

	int deleteRecommendScholarship(ScholarshipFundVO schVO);

	ScholarshipFundVO selectRecommendDetail(ScholarshipFundVO schVO);

}
