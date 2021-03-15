package kr.or.ddit.enrolment.controller;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enrolment.service.EnrolmentService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.InterestLecVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.TakeLecVO;

/**
 * @author 전진원
 * @since 2021. 1. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 26.   전진원      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class EnrolmentViewController {

	@Inject
	private EnrolmentService service;
	
	@RequestMapping(value="/enrolment/list.do")
	public String enrolmentView2(
			@RequestParam(value="page", required=false,defaultValue="1") int currentPage
			,@ModelAttribute("searchVO") SearchVO searchVO, Model model
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			) throws ServletException, IOException {
		
		
		PagingVO<LectureVO> paging = new PagingVO<>(5,5);
		paging.setSearchDetail(LectureVO.builder().memId(authMember.getMemId()).build());
		paging.setSearchVO(searchVO);
		String searchType = searchVO.getSearchType();
		int totalRecord =0;
		if("subName".equals(searchType) || "lecGrd".equals(searchType)) {
			switch (searchType) {
			case "subName":
				totalRecord = service.retrieveLectureSubNameCount(paging);
				break;
			case "lecGrd":
				totalRecord = service.retrieveLectureLecGrdCount(paging);
				break;
			}
		}else {
			totalRecord = service.retrieveLectureCount(paging);
		}
		paging.setTotalRecord(totalRecord);
		paging.setCurrentPage(currentPage);
		
		
		//개설 강의 내용
		List<LectureVO> LectureList = service.retrieveLectureList(paging);

		paging.setDataList(LectureList);
		
		//수강 신청 내역
		List<LectureVO> detailList = service.detailEnrolment(authMember);
		paging.setDataList2(detailList);
				
		
		model.addAttribute("paginationInfo", new CustomPaginationInfo<LectureVO>(paging));
		return "enrolment/enrolmentList";
	}
	
	//개설강의 페이징 비동기 메소드
	@RequestMapping(value="/enrolment/listPaging.do" , method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> enrolmentViewPaging(
			@RequestParam(value="page", required=false,defaultValue="1") int currentPage
			,@ModelAttribute("searchVO") SearchVO searchVO, Model model
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			) throws ServletException, IOException {
		
		
		PagingVO<LectureVO> paging = new PagingVO<>(5,3);
		paging.setSearchVO(searchVO);
		
		String searchType = searchVO.getSearchType();
		int totalRecord =0;
		if(searchType ==null) {
			totalRecord = service.retrieveLectureCount(paging);
		}else {
			switch (searchType) {
			case "subName":
				totalRecord = service.retrieveLectureSubNameCount(paging);
				break;
			case "lecGrd":
				totalRecord = service.retrieveLectureLecGrdCount(paging);
				break;
			}
		}
		
		
		paging.setTotalRecord(totalRecord);
		paging.setCurrentPage(currentPage);
		
		
		//개설 강의 내용
		paging.setSearchDetail(LectureVO.builder().memId(authMember.getMemId()).build());
		List<LectureVO> LectureList = service.retrieveLectureList(paging);

		
				
		Map<String, Object> resultMap = Collections.singletonMap("LectureList", LectureList);
		return resultMap;
	}
	
	
	
	//수강신청
	@RequestMapping(value= "/enrolment/insert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> enrolmentInsert(Model model,
			@RequestParam(value="enrolmentMemId") String enrolmentMemId,
			@RequestParam(value="enrolmentLecCode") String enrolmentLecCode
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			) {
		
		TakeLecVO takeLec = new TakeLecVO();
		takeLec.setLecCode(enrolmentLecCode);
		takeLec.setMemId(enrolmentMemId);
		takeLec.setTlecGrd(authMember.getMemGrd());
		service.insertCount(takeLec);
		//수강 신청
		int result = service.insertEnrolment(takeLec);
		
		Map<String, Object> resultMap = Collections.singletonMap("result", result);
		return resultMap;
		
		
	}
	
	//수강취소
	@RequestMapping(value= "/enrolment/delete.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> enrolmentDelete(Model model,
			@RequestParam(value="dleenrolmentMemId") String dleenrolmentMemId,
			@RequestParam(value="delenrolmentLecCode") String delenrolmentLecCode
			) {
		
		TakeLecVO takeLec = new TakeLecVO();
		takeLec.setLecCode(delenrolmentLecCode);
		takeLec.setMemId(dleenrolmentMemId);
		
		//수강 신청
		int deleteResult = service.deleteEnrolment(takeLec);
		 
		service.deleteCount(takeLec);
		
		Map<String, Object> resultMap = Collections.singletonMap("deleteResult", deleteResult);
		return resultMap;
	}
	
	
	//관심 등록
	@RequestMapping(value= "/enrolment/interest.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> enrolmentInterest(Model model,
			@RequestParam(value="enrolmentMemId") String enrolmentMemId,
			@RequestParam(value="enrolmentLecCode") String enrolmentLecCode
			
			) {
		
		InterestLecVO interest = new InterestLecVO();
		interest.setLecCode(enrolmentLecCode);
		interest.setMemId(enrolmentMemId);
		
		//수강 신청
		int result = service.interestEnrolment(interest);
		
		Map<String, Object> resultMap = Collections.singletonMap("interestResult", result);
		return resultMap;
		
		
	}
	
	//관심 과목 리스트 출력
	@RequestMapping(value="/enrolment/interestList.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> enrolmentInterestList(
				Model model
			,@RequestParam(value="enrolmentMemId") String enrolmentMemId
			) throws ServletException, IOException {
		
		InterestLecVO interest = new InterestLecVO();
		interest.setMemId(enrolmentMemId);
		
		
		//개설 강의 내용
		List<LectureVO> LectureList = service.retrieveLectureInterestList(interest);
		
				
		Map<String, Object> resultMap = Collections.singletonMap("interestResult", LectureList);
		
		return resultMap;
	}
	
	
	//관심 취소
	@RequestMapping(value= "/enrolment/deleteInterest.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> enrolmentDeleteInterest(Model model,
			@RequestParam(value="dleenrolmentMemId") String dleenrolmentMemId,
			@RequestParam(value="delenrolmentLecCode") String delenrolmentLecCode
			
			) {
		
		InterestLecVO interest = new InterestLecVO();
		interest.setLecCode(delenrolmentLecCode);
		interest.setMemId(dleenrolmentMemId);
		
		//수강 신청
		int deleteResult = service.deleteInterest(interest);
		
		
		Map<String, Object> resultMap = Collections.singletonMap("deleteInterestResult", deleteResult);
		return resultMap;
	}
	
	
}