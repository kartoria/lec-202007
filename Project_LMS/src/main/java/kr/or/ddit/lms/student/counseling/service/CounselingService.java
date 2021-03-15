package kr.or.ddit.lms.student.counseling.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lms.student.counseling.dao.ICounselingDAO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CounselingVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class CounselingService {
	
	@Inject
	private ICounselingDAO dao;
	
	
	public ServiceResult createCounseling(CounselingVO coun) {
		int rowcnt = dao.insertCounseling(coun);
		ServiceResult result = ServiceResult.OK;
		if(rowcnt <= 0) {
			result = ServiceResult.FAILED;
		} 
		return result;
	}

	public int retrieveCounselingApplyCount(PagingVO<CounselingVO> paging) {
		return dao.selectCounselingApplyCount(paging);
	}
	
	public int selectCounselingDoneCount(PagingVO<CounselingVO> paging) {
		return dao.selectCounselingDoneCount(paging);
	}
	
	public List<CounselingVO> retrieveCounselingApplyList(PagingVO<CounselingVO> paging){
		return dao.selectCounselingApplyList(paging);
	}

	public List<CounselingVO> retrieveCounselingDoneList(PagingVO<CounselingVO> paging){
		return dao.selectCounselingDoneList(paging);
	}
	
	public CounselingVO selectDetailCounseling(String cstNo) {
		return dao.selectDetailCounseling(cstNo);
	}
	
	public ServiceResult counselingUpdate(CounselingVO coun) {
		int rowcnt = dao.counselingUpdate(coun);
		ServiceResult result = ServiceResult.OK;
		if(rowcnt <= 0) {
			result = ServiceResult.FAILED;
		} 
		return result;
	}
	
	public ServiceResult counselingDelete(CounselingVO coun) {
		int rowcnt =  dao.counselingDelete(coun);
		ServiceResult result = ServiceResult.OK;
		if(rowcnt <= 0) {
			result = ServiceResult.FAILED;
		} 
		return result;
	}
	
	public List<CounselingVO> chooseCounselingApply(CounselingVO cstStudent) {
		return dao.chooseCounselingApply(cstStudent);
	}
	
	public List<CounselingVO> selectChooseCounselingTime(CounselingVO counselingVO){
		return dao.selectChooseCounselingTime(counselingVO);
	}
}
