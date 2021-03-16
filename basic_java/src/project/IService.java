package project;

import java.util.Map;

public interface IService {

	/**
	 * 로그인을 위한 메서드
	 * @param params mem_id 사람의 아이디, mem_pass 비밀번호
	 * @return id와 pass가 일치하는 한명의 아이디, 일치하는 사람이 없으면 null
	 * @author 선준
	 * @since 2020.09.04
	 * @see 
	 */
	String logIn(Map<String, String> params);

	/**
	 * 회원가입했을때 테이블 업데이트
	 * @param mv 회원객체
	 * @author 선준
	 * @since 2020.09.04
	 * @see
	 */
	boolean insertMember(MemberVO mv);
	
	/**
	 * 회원가입했을때 테이블 업데이트
	 * @author 선준
	 * @since 2020.09.04
	 * @see
	 */
	Map<Integer, String> showMemList();

	/**
	 * 상세정보 print
	 * @param mem_id 아이디
	 * @author 선준
	 * @since 2020.09.04
	 * @see
	 */
	boolean infoMember(String mem_id);

	/**
	 * 비밀번호 변경
	 * @param params mem_id 아이디, mem_pass 변경할 비밀번호
	 * @author 선준
	 * @since 2020.09.04
	 * @see
	 */
	boolean changePass(Map<String, String> params);

	/**
	 * 이름 변경
	 * @param params mem_id 아이디, mem_name 변경할 이름
	 * @author 선준
	 * @since 2020.09.04
	 * @see
	 */
	boolean changeName(Map<String, String> params);

	/**
	 * 주민번호 변경
	 * @param params mem_id 아이디, mem_name 변경할 이름
	 * @author 선준
	 * @since 2020.09.04
	 * @see
	 */
	boolean changeRegno(Map<String, String> params);

	
	/**
	 * 회원탈퇴
	 * @param mem_id 아이디
	 * @author 선준
	 * @since 2020.09.04
	 * @see
	 */
	boolean deleteMember(String mem_id);
	
}
