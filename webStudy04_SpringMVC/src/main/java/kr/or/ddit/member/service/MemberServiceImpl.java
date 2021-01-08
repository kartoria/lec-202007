package kr.or.ddit.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.utils.SecurityUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class MemberServiceImpl implements IMemberService {
	@Inject
	private IMemberDAO dao;
	@Inject
	private IAuthenticateService authService;
	
	private void encodePassword(MemberVO member) {
		String encoded = SecurityUtils.encryptSha512(member.getMem_pass());
		member.setMem_pass(encoded);
	}
	
	@Override
	public ServiceResult registMember(MemberVO member) {
		ServiceResult result = null;
		if(dao.selectMember(member.getMem_id())==null) {
			encodePassword(member);
			int rowcnt = dao.insertMember(member);
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public int retrieveMemberCount(PagingVO pagingVO) {
		return dao.selectMemberCount(pagingVO);
	}
	
	@Override
	public List<MemberVO> retrieveMemberList(PagingVO pagingVO) {
		return dao.selectMemberList(pagingVO);
	}

	@Override
	public MemberVO retrieveMember(String mem_id) {
		MemberVO member = dao.selectMember(mem_id);
		if(member==null)
			throw new UserNotFoundException(mem_id+"에 해당하는 유저가 없음.");
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		Object authResult = authService.authenticate(member);
		ServiceResult result = ServiceResult.INVALIDPASSWORD;
		if(authResult instanceof MemberVO) {
			int rowcnt = dao.updateMember(member);
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}			
		}else if(ServiceResult.NOTEXIST.equals(authResult)) {
			throw new UserNotFoundException(member);
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		Object authResult = authService.authenticate(member);
		ServiceResult result = null;
		if(authResult instanceof MemberVO) {
			int rowcnt = dao.deleteMember(member.getMem_id());
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}			
		}else if(ServiceResult.NOTEXIST.equals(authResult)) {
			throw new UserNotFoundException(member);
		}else {
			result = (ServiceResult) authResult;
		}
		return result;
	}
}




