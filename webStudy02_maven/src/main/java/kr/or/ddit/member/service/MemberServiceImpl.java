package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.dao.MemberDaoImpl_JDBC;
import kr.or.ddit.utils.SecurityUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberServiceImpl implements IMemberService{
	private IMemberDao memDao;
	private static IMemberService memService;
	private MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}
	public static IMemberService getInstance() {
		if(memService == null) memService = new MemberServiceImpl();
		return memService;
	}
	
	private IAuthenticateService authService = AuthenticateServiceImpl.getInstance();
	
	private void encodePassword(MemberVO member) {
		String encoded = SecurityUtils.encryptSha512(member.getMem_pass());
		member.setMem_pass(encoded);
	}
	
	@Override
	public ServiceResult registMember(MemberVO member) {
		if(memDao.selectMember(member.getMem_id()) != null)
			return ServiceResult.PKDUPLICATED;
		if(memDao.insertMember(member) == 0) 
			return ServiceResult.FAILED;
		encodePassword(member);
		return ServiceResult.OK;
	}
	
	@Override
	public List<MemberVO> retrieveMemberList(PagingVO pagingVO) {
		return memDao.selectMemberList(pagingVO);
	}

	@Override
	public MemberVO retrieveMember(String mem_id){
		MemberVO member = memDao.selectMember(mem_id);
		if(member == null) 
			throw new UserNotFoundException(member);
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member){
		Object authResult = authService.authenticate(member);
		ServiceResult result = ServiceResult.INVALIDPASSWORD;
		if(authResult instanceof MemberVO) {
			int rowcnt = memDao.updateMember(member);
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
			int rowcnt = memDao.deleteMember(member.getMem_id());
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
	
	@Override
	public int retrieveMemberCount(PagingVO pagingVO) {
		return memDao.selectMemberCount(pagingVO);
	}
}
