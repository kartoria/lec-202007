package project;

import java.util.HashMap;
import java.util.Map;

public class IServiceImpl implements IService {
	private IDao dao = new IDaoImpl(); 
	
	@Override
	public String logIn(Map<String, String> params) {
		return dao.logIn(params);
	}
	
	@Override
	public boolean insertMember(MemberVO mv) {
		return dao.insertMember(mv);
	}

	@Override
	public Map<Integer, String> showMemList() {
		return dao.showMemList();
	}

	@Override
	public boolean infoMember(String mem_id) {
		return dao.infoMember(mem_id);
	}

	@Override
	public boolean changePass(Map<String, String> params) {
		return dao.changePass(params);
	}

	@Override
	public boolean changeName(Map<String, String> params) {
		return dao.changeName(params);
	}

	@Override
	public boolean changeRegno(Map<String, String> params) {
		return dao.changeRegno(params);
	}

	@Override
	public boolean deleteMember(String mem_id) {
		return dao.deleteMember(mem_id);
	}

}
