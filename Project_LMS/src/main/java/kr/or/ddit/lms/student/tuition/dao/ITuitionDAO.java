package kr.or.ddit.lms.student.tuition.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.commons.vo.SPlanVO;
import kr.or.ddit.lms.student.tuition.vo.VirtualAccountTuitionVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.TuitionVO;

@Repository
public interface ITuitionDAO {

	TuitionVO selectTuitionList(MemberVO authMember);

	int tuitionUpdate(MemberVO authMember);

	List<TuitionVO> selectTuitionPayList(MemberVO authMember);

	TuitionVO selectBank(MemberVO authMember);

	DepartmentVO selectDepName(MemberVO authMember);
	
	int virtualAccounUpdate(VirtualAccountTuitionVO virtualAccountVO);
	
	VirtualAccountTuitionVO selectPayAcn(MemberVO authMember);

	List<TuitionVO> selectSfundValue(MemberVO authMember);
	
	VirtualAccountTuitionVO selectPayImp(MemberVO authMember);
	
	VirtualAccountTuitionVO selectDepFee(MemberVO authMember);

	int selectSfundCount(MemberVO authMember);
	
	int selectPayCountDivisionCheck(MemberVO authMember);
	
	VirtualAccountTuitionVO selectPayStartDate();
	
	int piadVirtualAccountUpdate(VirtualAccountTuitionVO virtualAccountTuitionVO);
	
	int tuitionDivisionUpdate(VirtualAccountTuitionVO virtualAccountTuitionVO);
	
	VirtualAccountTuitionVO tuitionDivisionSelectPayExpect(MemberVO authMember);
	
	VirtualAccountTuitionVO selectDivisionPayImp(MemberVO authMember);
	
	int paidDivisionUpdate(VirtualAccountTuitionVO virtualAccountTuitionVO);
	
	int selectCountSchedule();
	
	List<VirtualAccountTuitionVO> tuitionDivisionDateCheck(MemberVO authMember);
	
	List<VirtualAccountTuitionVO> selectVirtualAcoountTuitionDoneList(PagingVO<VirtualAccountTuitionVO> paging);
	
	int selectVirtualAcoountTuitionCount(PagingVO<VirtualAccountTuitionVO> paging);
	
	VirtualAccountTuitionVO selectFirstPay(MemberVO authMember);
	
	int selectTuitionBillDateCheckCount(MemberVO authMember);
	
	VirtualAccountTuitionVO selectEmail(MemberVO authMember);
	
	VirtualAccountTuitionVO selectEmailDivision(MemberVO authMember);
	
	List<VirtualAccountTuitionVO> selectEmailDivisionList(MemberVO authMember);
	
	VirtualAccountTuitionVO selectDivisionAmount(MemberVO authMember);
	
	VirtualAccountTuitionVO selectDivisionPDF(VirtualAccountTuitionVO virtualAccountTuitionVO);
}
