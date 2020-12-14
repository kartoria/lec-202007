package kr.or.ddit.member.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.IMemDao;
import kr.or.ddit.member.dao.memDaoImpl;
import kr.or.ddit.utils.SecurityUtils;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements IAuthenticateService{
	IMemDao dao = new memDaoImpl();
	public Object authenticate(MemberVO mv) {
		Object result = null;
		MemberVO member = dao.selectMember(mv.getMem_id());
		if(member != null) {
			String input = mv.getMem_pass();
			String encoded = SecurityUtils.encryptSha512(input);
			String saved = member.getMem_pass();
			if(saved.equals(encoded)) {
				result = member;
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}
}
