package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.dao.IMemDao;
import kr.or.ddit.member.dao.MemDaoImpl;
import kr.or.ddit.utils.SecurityUtils;
import kr.or.ddit.vo.MemberVO;

public class MemServiceImpl implements IMemService{
	private IMemDao memDao;
	private static IMemService memService;
	private MemServiceImpl() {
		memDao = MemDaoImpl.getInstance();
	}
	public static IMemService getInstance() {
		if(memService == null) memService = new MemServiceImpl();
		return memService;
	}
	
	private IAuthenticateService authService = AuthenticateServiceImpl.getInstance();
	
	@Override
	public ServiceResult registMember(MemberVO member) {
		if(memDao.selectMember(member.getMem_id()) != null)
			return ServiceResult.PKDUPLICATED;
		if(memDao.insertMember(member) == 0) 
			return ServiceResult.FAILED;
		encodePassword(member);
		return ServiceResult.OK;
	}

	private void encodePassword(MemberVO member) {
		String encoded = SecurityUtils.encryptSha512(member.getMem_pass());
		member.setMem_pass(encoded);
	}

	@Override
	public List<MemberVO> retrieveMemberList() {
		return memDao.selectMemberList();
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
		MemberVO memberCheck = memDao.selectMember(member.getMem_id());
		if(memberCheck == null) 
			throw new UserNotFoundException(member);
		if (memDao.updateMember(member) == 0) 
			return ServiceResult.FAILED;
		return ServiceResult.OK;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		MemberVO memberCheck = memDao.selectMember(member.getMem_id());
		if(memberCheck == null) 
			throw new UserNotFoundException(member);
		
		if(authService.authenticate(member) instanceof MemberVO) {
			if(memDao.deleteMember(member.getMem_id()) > 0){
				return ServiceResult.OK;
			}else {
				return ServiceResult.FAILED;
			}
		}
		return ServiceResult.INVALIDPASSWORD;
	}
}
