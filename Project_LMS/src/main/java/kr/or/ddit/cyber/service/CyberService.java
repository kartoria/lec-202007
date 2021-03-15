package kr.or.ddit.cyber.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.cyber.dao.ICyberDAO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.LectureVO;

@Service
public class CyberService {
	@Inject
	ICyberDAO dao;
	
	/**
	 * 내  강의목록을 가져와서 myclass 강의실 입장할수있게
	 * @author 작성자명
	 * @since 2021. 1. 29.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 1. 29.      작성자명       최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	public List<LectureVO> getMyLectureList(MemberVO authMember){
		List<LectureVO> lectureList = new ArrayList<>();
		if("ROLE_STUDENT".equals(authMember.getMemType()))
			lectureList = dao.studentMyLectureList(authMember);
		else if("ROLE_PROFESSOR".equals(authMember.getMemType()))
			lectureList = dao.professorMyLectureList(authMember);
		else if("ROLE_ADMIN".equals(authMember.getMemType()))
			lectureList = dao.adminLectureList();
		return lectureList;
	}
	
	public List<LectureVO> getLectureTimeList(MemberVO authMember){
		return dao.lectureTimeList(authMember);
	}
}
