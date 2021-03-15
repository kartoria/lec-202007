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
package kr.or.ddit.lms.student.scholarship.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
@Repository
public interface StudentSchaolarshipMapper {

	List<StuScholarshipVO> selectStuScholarshipSmstList(MemberVO authMember);

	int selectStuScholarshipCount(PagingVO<StuScholarshipVO> paging);

	List<StuScholarshipVO> selectStuScholarshipList(PagingVO<StuScholarshipVO> paging);

	List<ScholarshipVO> selectScholarshipType(ScholarshipFundVO schFundVO);

	int insertScholarshipApply(ScholarshipFundVO schFundVO);

	List<ScholarshipFundVO> selectStuScholarshipApplySmst(MemberVO authMember);

	int selectStuScholarshipApplyCount(PagingVO<ScholarshipFundVO> paging);

	List<ScholarshipFundVO> selectStuScholarshipApplyList(PagingVO<ScholarshipFundVO> paging);

	StuScholarshipVO selectStuScholarshipApply(int sfundNo);

}
