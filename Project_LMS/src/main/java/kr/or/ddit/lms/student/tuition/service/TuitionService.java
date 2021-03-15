package kr.or.ddit.lms.student.tuition.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.vo.SPlanVO;
import kr.or.ddit.lms.student.tuition.dao.ITuitionDAO;
import kr.or.ddit.lms.student.tuition.vo.VirtualAccountTuitionVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.TuitionVO;

@Service
public class TuitionService {
	@Inject
	ITuitionDAO tuitionDao;
	//분할 납부 조회
	public TuitionVO selectTuitionList(MemberVO authMember) {
		return tuitionDao.selectTuitionList(authMember);
	}
	//분할 납부 신청
	public int tuitionUpdate(MemberVO authMember) {
		return tuitionDao.tuitionUpdate(authMember);
	}
	//1차2차3차 납부 확인
	public List<TuitionVO> selectTuitionPayList(MemberVO authMember) {
		return tuitionDao.selectTuitionPayList(authMember);
	}
	
	public TuitionVO selectBank(MemberVO authMember) {
		return tuitionDao.selectBank(authMember);
	}
	public DepartmentVO selectDepName(MemberVO authMember) {
		return tuitionDao.selectDepName(authMember);
	}
	// 가상계좌 등록
	public int virtualAccounUpdate(VirtualAccountTuitionVO virtualAccountVO) {
		return tuitionDao.virtualAccounUpdate(virtualAccountVO);
	}
	public VirtualAccountTuitionVO selectPayAcn(MemberVO authMember) {
		return tuitionDao.selectPayAcn(authMember);
	}
	public List<TuitionVO> selectSfundValue(MemberVO authMember) {
		return tuitionDao.selectSfundValue(authMember);
	}
	public VirtualAccountTuitionVO selectPayImp(MemberVO authMember) {
		return tuitionDao.selectPayImp(authMember);
	}
	public VirtualAccountTuitionVO selectDepFee(MemberVO authMember) {
		return tuitionDao.selectDepFee(authMember);
	}
	public int selectSfundCount(MemberVO authMember) {
		return tuitionDao.selectSfundCount(authMember);
	}
	public int selectPayCountDivisionCheck(MemberVO authMember) {
		return tuitionDao.selectPayCountDivisionCheck(authMember);
	}
	public VirtualAccountTuitionVO selectPayStartDate() {
		return tuitionDao.selectPayStartDate();
	}
	public int piadVirtualAccountUpdate(VirtualAccountTuitionVO virtualAccountTuitionVO) {
		return tuitionDao.piadVirtualAccountUpdate(virtualAccountTuitionVO);
	}
	public int tuitionDivisionUpdate(VirtualAccountTuitionVO virtualAccountTuitionVO) {
		return tuitionDao.tuitionDivisionUpdate(virtualAccountTuitionVO);
	}
	public VirtualAccountTuitionVO tuitionDivisionSelectPayExpect(MemberVO authMember) {
		return tuitionDao.tuitionDivisionSelectPayExpect(authMember);
	}
	public VirtualAccountTuitionVO selectDivisionPayImp(MemberVO authMember) {
		return tuitionDao.selectDivisionPayImp(authMember);
	}
	public int paidDivisionUpdate(VirtualAccountTuitionVO virtualAccountTuitionVO) {
		return tuitionDao.paidDivisionUpdate(virtualAccountTuitionVO);
	}
	public int selectCountSchedule() {
		return tuitionDao.selectCountSchedule();
	}
	public List<VirtualAccountTuitionVO> tuitionDivisionDateCheck(MemberVO authMember) {
		return tuitionDao.tuitionDivisionDateCheck(authMember);
	}
	public List<VirtualAccountTuitionVO> selectVirtualAcoountTuitionDoneList(PagingVO<VirtualAccountTuitionVO> paging){
		return tuitionDao.selectVirtualAcoountTuitionDoneList(paging);
	}
	
	public int selectVirtualAcoountTuitionCount(PagingVO<VirtualAccountTuitionVO> paging){
		return tuitionDao.selectVirtualAcoountTuitionCount(paging);
	}
	public VirtualAccountTuitionVO selectFirstPay(MemberVO authMember) {
		return tuitionDao.selectFirstPay(authMember);
	}
	public int selectTuitionBillDateCheckCount(MemberVO authMember) {
		return tuitionDao.selectTuitionBillDateCheckCount(authMember);
	}
	public VirtualAccountTuitionVO selectEmail(MemberVO authMember) {
		return tuitionDao.selectEmail(authMember);
	}
	public VirtualAccountTuitionVO selectEmailDivision(MemberVO authMember){
		return tuitionDao.selectEmailDivision(authMember);
	}
	public List<VirtualAccountTuitionVO> selectEmailDivisionList(MemberVO authMember){
		return tuitionDao.selectEmailDivisionList(authMember);
	}
	public VirtualAccountTuitionVO selectDivisionAmount(MemberVO authMember) {
		return tuitionDao.selectDivisionAmount(authMember);
	}
	public VirtualAccountTuitionVO selectDivisionPDF(VirtualAccountTuitionVO virtualAccountTuitionVO) {
		return tuitionDao.selectDivisionPDF(virtualAccountTuitionVO);
	}
}
