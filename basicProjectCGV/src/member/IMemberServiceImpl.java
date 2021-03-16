package member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class IMemberServiceImpl implements IMemberService {
	
	private static IMemberService memSv;
	private IMemberDao memdao;
	
	private IMemberServiceImpl(){
		memdao = IMemberDaoImpl.getInstance();
	}

	public static IMemberService getInstance() {
		if(memSv == null){
			memSv = new IMemberServiceImpl();
		}
		return memSv;
	}
	
	@Override
	public String logIn(Map<String, String> params) {
		return memdao.logIn(params);
	}

	@Override
	public List<MemberVO> readAllMember() {
		return memdao.readAllMember();
	}

	@Override
	public MemberVO readMemInfo(String login_id) {
		return memdao.readMemInfo(login_id);
	}

	@Override
	public int createMember(MemberVO newMember) {
		return memdao.createMember(newMember);
	}

	@Override
	public int updateMemHp(Map<String, String> info) {
		return memdao.updateMemHp(info);
	}

	@Override
	public String checkDupId(String mem_id) {
		return memdao.checkDupId(mem_id);
	}

	@Override
	public int updateMemAdd(Map<String, String> info) {
		return memdao.updateMemAdd(info);
	}

	@Override
	public int updateMemMail(Map<String, String> info) {
		return memdao.updateMemMail(info);
	}

	@Override
	public int deleteMember(String mem_id) {
		return memdao.deleteMember(mem_id);
	}

	@Override
	public int updateMemName(Map<String, String> info) {
		return memdao.updateMemName(info);
	}

	@Override
	public int updateMemPw(Map<String, String> info) {
		return memdao.updateMemPw(info);
	}
}
