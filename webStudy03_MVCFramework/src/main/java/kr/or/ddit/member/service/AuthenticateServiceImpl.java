package kr.or.ddit.member.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.utils.SecurityUtils;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements IAuthenticateService{
	IMemberDao dao = MemberDaoImpl.getInstance();
	private static IAuthenticateService authService;
	private AuthenticateServiceImpl() {
	}
	public static IAuthenticateService getInstance() {
		if(authService == null) authService = new AuthenticateServiceImpl();
		return authService;
	}
	
	public Object authenticate(MemberVO mv) {
		Object result = null;
		MemberVO member = dao.selectMember(mv.getMem_id());
		if(member != null && !"Y".equals(member.getMem_delete())) {
			String input = mv.getMem_pass();
			String encoded = SecurityUtils.encryptSha512(input);
			String saved = member.getMem_pass();
			if(saved.equals(encoded)) {
				result = member;
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else if(member != null && "Y".equals(member.getMem_delete())){
			result = ServiceResult.DISABLE;
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}
}
