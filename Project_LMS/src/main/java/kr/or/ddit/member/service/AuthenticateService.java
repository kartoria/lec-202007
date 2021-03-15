package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.ddit.commons.service.BaseService;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.MemberWrapper;


@Service("customUserService")
public class AuthenticateService extends BaseService implements UserDetailsService{
	@Inject
	private IMemberDAO dao;
	
	@Inject
	private MessageSourceAccessor accessor ;
	
	//db에 비밀번호 저장할때 쓸 용도
//	@Inject
//	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO member = dao.selectMember(MemberVO.builder().memId(username).build());
//		LOGGER.info("asd123암호화 : {}",passwordEncoder.encode("asd123"));
		if(member==null) {
			String message = accessor.getMessage("DigestAuthenticationFilter.usernameNotFound", new Object[] {username});
			throw new UsernameNotFoundException(message);
		}
		
		return new MemberWrapper(member);
	}
	
//	public Object authenticate(MemberVO paramVO) {
//		Object result = null;
//		MemberVO member = dao.selectMember(paramVO);
//		if(member!=null && member.getMemGraduation() == null) {
//			String input = paramVO.getMemPass();
//			String encoded = SecurityUtils.encryptSha512(input);
//			String saved = member.getMemPass();
//			if(saved.equals(encoded)) {
//				result = member;
//			}else {
//				result = ServiceResult.INVALIDPASSWORD;
//			}
//		}else if(member!=null && member.getMemGraduation() != null) {
//			result = ServiceResult.DISABLE;
//		}else {
//			result = ServiceResult.NOTEXIST;
//		}
//		return result;
//	}

}












