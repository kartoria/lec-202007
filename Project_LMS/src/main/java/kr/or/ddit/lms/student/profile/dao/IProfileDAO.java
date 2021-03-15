package kr.or.ddit.lms.student.profile.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.lms.student.profile.VO.ProfileVO;
import kr.or.ddit.member.vo.MemberVO;

@Repository
public interface IProfileDAO {

	ProfileVO profileView(MemberVO authMember);

	int profileUpdate(ProfileVO profileVO);

	ProfileVO profileUpdateView(MemberVO authMember);


}
