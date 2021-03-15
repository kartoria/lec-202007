package kr.or.ddit.lms.student.academic.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.lms.student.tuition.vo.VirtualAccountTuitionVO;
import kr.or.ddit.member.vo.MemberVO;

@Repository
public interface IAcademicDAO {
	public MemberVO selectLeaveAcademic(MemberVO authMember);
	
	public VirtualAccountTuitionVO selectTuidionAcademic(MemberVO authMember);
	
	List<VirtualAccountTuitionVO> selectScholarship(MemberVO authMember);
}
