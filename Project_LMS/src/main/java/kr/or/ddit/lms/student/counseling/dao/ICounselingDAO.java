package kr.or.ddit.lms.student.counseling.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CounselingVO;
import kr.or.ddit.vo.PagingVO;

/**
 * @author 김성준
 * @since 2021. 1. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 26.    김성준             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Repository
public interface ICounselingDAO {
	/**
	 * @author 김성준
	 * @since 2021. 1. 26.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 1. 26.    김성준             최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	public int insertCounseling(CounselingVO coun);
	
	/**
	 * @author 김성준
	 * @since 2021. 1. 26.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 1. 26.    김성준             최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	public List<CounselingVO> selectCounselingApplyList(PagingVO<CounselingVO> paging);
	
	/**
	 * @author 김성준
	 * @since 2021. 1. 26.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 1. 26.    김성준             최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	public List<CounselingVO> selectCounselingDoneList(PagingVO<CounselingVO> paging);
	
	/**
	 * @author 김성준
	 * @since 2021. 1. 26.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 1. 26.    김성준             최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	public int selectCounselingApplyCount(PagingVO<CounselingVO> paging);
	
	
	public int selectCounselingDoneCount(PagingVO<CounselingVO> paging);
	
	
	/**
	 * @author 김성준
	 * @since 2021. 1. 26.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 1. 26.    김성준             최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	public CounselingVO selectDetailCounseling(String cstNo);
	
	/**
	 * 
	 * @author 김성준
	 * @since 2021. 1. 29.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 1. 29.     김성준          최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	public int counselingUpdate(CounselingVO coun);

	/**
	 * 
	 * @author 김성준
	 * @since 2021. 1. 29.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 1. 29.   김성준               최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	public int counselingDelete(CounselingVO coun);
	
	
	/**
	 * 
	 * @author 김성준
	 * @since 2021. 1. 27.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 1. 27.   김성준              최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	public List<CounselingVO> chooseCounselingApply(CounselingVO cstStudent);
	
	/**
	 * 상담 신청에서 교수를 선택하고 상담희망날짜를 선택할 때, 중복 방지
	 * @author 김성준
	 * @since 2021. 2. 10.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 2. 10.    김성준       최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	public List<CounselingVO> selectChooseCounselingTime(CounselingVO counselingVO);
}
