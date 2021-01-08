package kr.or.ddit.member.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

public interface IAuthenticateService {
	/**
	 * 인증 로직
	 * @param paramVO 아이디와 비번 기반의 인증 데이터
	 * @return 인증 성공시 : MemberVO, 실패시: ServiceResult(NOTEXIST, INVALIDPASSWORD)
	 */
	public Object authenticate(MemberVO paramVO);
}
