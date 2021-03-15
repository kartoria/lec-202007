package kr.or.ddit.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.member.vo.FirstLoginVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원관리(CRUD)를 위한 Persistence Layer
 *
 */
@Repository
public interface IMemberDAO {
	
	/**
	 * 회원 등록
	 * @param MemberVO member
	 * @return &gt; 0 : 성공 , &lt;= 0 : 실패
	 */
	public int insertMember(MemberVO member);
	
	/**
	 * 회원 목록수 조회(totalRecord)
	 * @return
	 */
	public int selectMemberCount(PagingVO pagingVO);
	
	/**
	 * 회원목록 조회
	 * @param pagingVO TODO
	 * @return 존재하지 않을때, size == 0
	 */
	public List<MemberVO> selectMemberList(PagingVO pagingVO);
	
	/**
	 * 회원 상세 조회
	 * @param MemberVO member
	 * @return 존재하지 않을때 null 반환
	 */
	public MemberVO selectMember(MemberVO member);
	
	/**
	 * 회원 정보 수정(자기 정보 수정)
	 * @param MemberVO member
	 * @return &gt; 0 : 성공 , &lt;= 0 : 실패
	 */
	public int updateMember(MemberVO member);
	
	/**
	 * 회원 탈퇴 처리(???)
	 * @param MemberVO member
	 * @return &gt; 0 : 성공 , &lt;= 0 : 실패
	 */
	public int deleteMember(MemberVO member);
	
	/**
	 * 학번 찾기
	 * @author 김선준
	 * @since 2021. 1. 27.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 1. 27.      작성자명       최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	public List<MemberVO> inquiryId(MemberVO member);

	public int firstLoginInsert(FirstLoginVO firstLoginVO);
}
















