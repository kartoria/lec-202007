package kr.or.ddit.member.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.commons.service.BaseService;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.vo.FirstLoginVO;
import kr.or.ddit.member.vo.MemberVO;

@Service
public class MemberService extends BaseService{
	@Inject
	IMemberDAO dao;
	@Inject
	PasswordEncoder passwordEncoder;
	
	
	public List<MemberVO> inquiryId(MemberVO paramVO) {
		LOGGER.info("{} 입력값 : {}", this.getClass().getName(), paramVO.toString());
		List<MemberVO> memberList = dao.inquiryId(paramVO);
		LOGGER.info("{} 학번 찾기 결과 : {}",this.getClass().getName(), memberList);
		if(memberList.size() == 0)
			throw new UsernameNotFoundException(paramVO.getMemName()+"에 해당하는 유저가 없음.");
		return memberList;
	}
	
	//최초로그인
		public int firstLoginInsert(FirstLoginVO firstLoginVO) {
			return dao.firstLoginInsert(firstLoginVO);
		}
		
	
}
