package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원 관리를 위한 Business Logic Layer
 *
 */
/**
 * @author SEM
 *
 */
public interface IMemberService {
	/**
	 * 회원등록
	 * @param member
	 * @return PKDUPLICATED, OK, FAILED
	 */
	public ServiceResult registMember(MemberVO member);
	
	/**
	 * 목록수 조회(totalRecord)
	 * @param pagingVO
	 * @return
	 */
	public int retrieveMemberCount(PagingVO pagingVO);
	/**
	 * 회원 목록 조회(추후 검색/페이징 기능 추가)
	 * @param pagingVO TODO
	 * @return
	 */
	public List<MemberVO> retrieveMemberList(PagingVO pagingVO);
	/**
	 * 회원 상세 조회
	 * @param mem_id
	 * @return 존재하지 않는다면, custom  exception 발생
	 */
	public MemberVO retrieveMember(String mem_id);
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return 존재하지 않는다면, custom  exception 발생, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult modifyMember(MemberVO member);
	/**
	 * 회원 탈퇴
	 * @param member
	 * @return  존재하지 않는다면, custom  exception 발생, INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult removeMember(MemberVO member);
}



























