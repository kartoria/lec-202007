package kr.or.ddit.admin.scholarship.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.admin.scholarship.dao.AdminScholarshipDAO;
import kr.or.ddit.admin.scholarship.vo.ScholarshipFundListVO;
import kr.or.ddit.admin.scholarship.vo.ScholarshipPayedDetailVO;
import kr.or.ddit.admin.scholarship.vo.ScoreFormVO;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ScholarshipFundVO;
import kr.or.ddit.vo.ScholarshipVO;
import kr.or.ddit.vo.SemesterScoreVO;

@Service
public class AdminScholarshipService{
	@Inject
	private AdminScholarshipDAO scholarDAO;
	
	public List<ScholarshipVO> retrieveScholarTypeList() {
		return scholarDAO.selectScholarTypeList();
	}
	public ScholarshipVO retrieveScholarType(String schCode) {
		return scholarDAO.selectScholarType(schCode);
	}
	public int modifyScholarType(ScholarshipVO schVO){
		return scholarDAO.updateScholarType(schVO);
	}
	public int createScholarType(ScholarshipVO schVO) {
		return scholarDAO.insertScholarType(schVO);
	}
	public int removeScholarType(String schCode) {
		return scholarDAO.deleteScholarType(schCode);
	}
	public int retrieveScholarFundCount(PagingVO<ScholarshipVO> pagingVO){
		return scholarDAO.selectScholarFundCount(pagingVO);
	}
	public List<CodeVO> retrieveCollegeList() {
		return scholarDAO.selectCollegeList();
	}
	public List<SemesterScoreVO> retrieveScoreSemseter() {
		return scholarDAO.selectScoreSemester();
	}
	public List<ScholarshipVO> retrieveScholarFundList(PagingVO<ScholarshipVO> pagingVO) {
		return scholarDAO.selectScholarFundList(pagingVO);
	}
	public List<DepartmentVO> retrieveDepartmentList(int college) {
		return scholarDAO.selectDepartmentList(college);
	}
	public List<SemesterScoreVO> selectScoreScholarCheck(ScoreFormVO scoreFormVO) {
		 return scholarDAO.selectScoreScholarCheck(scoreFormVO);
	}
	public List<ScholarshipVO> retrieveScoreScholarshipList() {
		return scholarDAO.selectScoreScholarshipList();
	}
	public List<SemesterScoreVO> retrieveSemesterScoreList(ScoreFormVO scoreFormVO) {
		return scholarDAO.selectSemesterScoreList(scoreFormVO);
	}
	public int createScholarFund(ScholarshipFundListVO schFundList){
		return scholarDAO.insertScholarFund(schFundList);
	}
	public List<ScholarshipVO> retrieveScholarApplyType() {
		return scholarDAO.selectScholarApplyType();
	}
	public List<ScholarshipFundVO> retrieveScholarSmstList() {
		return scholarDAO.selectScholarSmstList();
	}
	public List<ScholarshipFundVO> retrieveScholarApplyList(ScholarshipFundVO searchForm) {
		return scholarDAO.selectScholarApplyList(searchForm);
	}
	public int modifyScholarFund(ScholarshipFundListVO  applyList){
		return scholarDAO.updateScholarFund(applyList);
	}
	public int removeScholarApplyRecord(ScholarshipFundListVO applyList){
		return scholarDAO.deleteScholarApplyRecord(applyList);
	}
	public ScholarshipFundVO retrieveScholarApply(int sfundNo) {
		return scholarDAO.selectScholarApply(sfundNo);
	}
	public int removeScholarScoreRecord(ScholarshipFundListVO applyList) {
		return scholarDAO.deleteScholarScoreRecord(applyList);
	}
	public int updateScholarApply(ScholarshipFundVO schVO){
		return scholarDAO.updateScholarApply(schVO);
	}
	public List<ScholarshipFundVO> retrieveAllScholarSemester() {
		return scholarDAO.selectAllScholarSemester();
	}
	public ScholarshipPayedDetailVO retreveScholarPayedDetail(ScholarshipFundVO schVO) {
		return scholarDAO.selectScholarPayedDetail(schVO);
	}
	
	
	

	

}
