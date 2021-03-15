package kr.or.ddit.admin.scholarship.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.admin.scholarship.vo.ScholarshipFundListVO;
import kr.or.ddit.admin.scholarship.vo.ScholarshipPayedDetailVO;
import kr.or.ddit.admin.scholarship.vo.ScoreFormVO;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ScholarshipFundVO;
import kr.or.ddit.vo.ScholarshipVO;
import kr.or.ddit.vo.SemesterScoreVO;

/**
 * @author 조예슬
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
public interface AdminScholarshipDAO {
	/**장학유형조회**/
	List<ScholarshipVO> selectScholarTypeList();
	ScholarshipVO selectScholarType(String schCode);
	int updateScholarType(ScholarshipVO schVO);
	int insertScholarType(ScholarshipVO schVO);
	int deleteScholarType(String schCode);
	List<ScholarshipVO> selectScholarFundList(PagingVO<ScholarshipVO> pagingVO);
	int selectScholarFundCount(PagingVO<ScholarshipVO> pagingVO);
	List<DepartmentVO> selectDepartmentList(int college);
	List<CodeVO> selectCollegeList();
	List<SemesterScoreVO> selectScoreSemester();
	List<SemesterScoreVO> selectSemesterScoreList(ScoreFormVO scoreFormVO);
	int insertScholarFund(ScholarshipFundListVO schFundList);
	List<ScholarshipVO> selectScholarApplyType();
	List<ScholarshipFundVO> selectScholarSmstList();
	List<ScholarshipFundVO> selectScholarApplyList(ScholarshipFundVO searchForm);
	int updateScholarFund(ScholarshipFundListVO  applyList);
	int deleteScholarApplyRecord(ScholarshipFundListVO applyList);
	ScholarshipFundVO selectScholarApply(int sfundNo);
	List<ScholarshipVO> selectScoreScholarshipList();
	/**성적장학생 선발여부확인**/
	List<SemesterScoreVO> selectScoreScholarCheck(ScoreFormVO scoreFormVO);
	int deleteScholarScoreRecord(ScholarshipFundListVO applyList);
	int updateScholarApply(ScholarshipFundVO schVO);
	List<ScholarshipFundVO> selectAllScholarSemester();
	ScholarshipPayedDetailVO selectScholarPayedDetail(ScholarshipFundVO schVO);

	
	


}
