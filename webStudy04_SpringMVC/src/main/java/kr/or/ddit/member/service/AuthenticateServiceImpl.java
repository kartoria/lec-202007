package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.utils.SecurityUtils;
import kr.or.ddit.vo.MemberVO;

@Service
public class AuthenticateServiceImpl implements IAuthenticateService {
	
	@Inject
	private IMemberDAO dao;

	@Override
	public Object authenticate(MemberVO paramVO) {
		Object result = null;
		MemberVO member = dao.selectMember(paramVO.getMem_id());
		if(member!=null && !"Y".equals(member.getMem_delete())) {
			String input = paramVO.getMem_pass();
			String encoded = SecurityUtils.encryptSha512(input);
			String saved = member.getMem_pass();
			if(saved.equals(encoded)) {
				result = member;
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else if(member!=null && "Y".equals(member.getMem_delete())) {
			result = ServiceResult.DISABLE;
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

}












