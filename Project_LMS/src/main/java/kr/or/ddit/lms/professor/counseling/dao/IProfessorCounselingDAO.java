/**
 * @author PC-NEW08
 * @since 2021. 1. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 22.     PC-NEW08      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
package kr.or.ddit.lms.professor.counseling.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.lms.student.profile.VO.ProfileVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CounselingVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author PC-NEW08
 * @since 2021. 1. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 22.     PC-NEW08      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Repository
public interface IProfessorCounselingDAO {

	public List<CounselingVO> selectCounselingApplyList(PagingVO<CounselingVO> paging);
	
	public int selectCounselingApplyCount(PagingVO<CounselingVO> paging);
	
	/**
	 * 
	 * @author 김성준
	 * @since 2021. 2. 11.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 2. 11.      김성준      최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	public int selectCounselingDoneCount(PagingVO<CounselingVO> paging);
	
	public int updateCounselingCstNote(CounselingVO coun);
	
	public List<CounselingVO> selectCounselingDoneList(PagingVO<CounselingVO> paging);
	
	public CounselingVO selectDetailCounseling(CounselingVO coun);
	
	public List<CounselingVO> selectChooseCounselingDate(CounselingVO coun);
	
	public int updateCounseling(CounselingVO coun);
	
	public CounselingVO chooseDateCounseling(CounselingVO coun);
}