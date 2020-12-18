package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

/**
 * 회원관리를 위한 Persistence Layer 
 */
public interface IMemDao {
	
	/**
	 * 회원 등록
	 * @param member
	 * @return &gt; 0 : 성공, %lt;= 0 : 실패
	 */
	public int insertMember(MemberVO member);
	
	/**
	 * 회원 목록 조회
	 * @return 존재하지 않을 때, size == 0
	 */
	public List<MemberVO> selectMemberList();
	
	/**
	 * 회원 상세 조회
	 * @param mem_id
	 * @return 존재하지 않을 때 null 반환
	 */
	public MemberVO selectMember(String mem_id);
	
	/**
	 * 회원 정보 수정 (자기 정보 수정)
	 * @param member
	 * @return &gt; 0 : 성공, &lt;= 0   :실패
	 */
	public int updateMember(MemberVO member);
	
	/**
	 * 회원 삭제
	 * @param mem_id
	 * @return
	 */
	public int deleteMember(String mem_id);
}
