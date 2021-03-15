package kr.or.ddit.lms.student.academic.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.lms.student.academic.dao.IAcademicDAO;
import kr.or.ddit.lms.student.tuition.vo.VirtualAccountTuitionVO;
import kr.or.ddit.member.vo.MemberVO;

@Service
public class AcademicService {
	
	@Inject
	private IAcademicDAO dao;
	
	public MemberVO selectLeaveAcademic(MemberVO authMember) {
		return dao.selectLeaveAcademic(authMember);
	}
	
	public VirtualAccountTuitionVO selectTuidionAcademic(MemberVO authMember) {
		return dao.selectTuidionAcademic(authMember);
	}
	
	public List<VirtualAccountTuitionVO> selectScholarship(MemberVO authMember){
		return dao.selectScholarship(authMember);
	}
}
