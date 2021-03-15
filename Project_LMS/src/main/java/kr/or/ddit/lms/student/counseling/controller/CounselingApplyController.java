package kr.or.ddit.lms.student.counseling.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.commons.service.MessageService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lms.student.counseling.service.CounselingService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CounselingVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.MessageVO;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;

/**
 * @author PC-NEW05
 * @since 2021. 2. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 1.     PC-NEW05      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller

public class CounselingApplyController extends BaseController {
	@Inject
	private CounselingService service;

	@Inject
	private MessageService msgService;
	@RequestMapping("/lms/student/counseling/apply.do")
	public String apply(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model 
			) {
		
		PagingVO<CounselingVO> paging = new PagingVO<>(10, 5);
		paging.setSearchVO(searchVO);
		paging.setSearchDetail(CounselingVO.builder().cstStudent(authMember.getMemId()).build());

		int totalRecord = service.retrieveCounselingApplyCount(paging);
		
		paging.setTotalRecord(totalRecord);
		paging.setCurrentPage(currentPage);
		
		List<CounselingVO> counList = service.retrieveCounselingApplyList(paging);
		
		paging.setDataList(counList);
		model.addAttribute("paginationInfo", new CustomPaginationInfo<CounselingVO>(paging));
		model.addAttribute("pageTitle", "상담 신청");
		return "lms/counseling/counselingApply";
	}
	
	/**
	 * 학생이 상담 신청할 시, 같은 과에 소속된 교수들의 이름을 나열
	 * @author 김성준
	 * @since 2021. 2. 1.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 2. 1.    김성준       최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	@RequestMapping(value="/lms/student/counseling/chooseList.do", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> getListChoose(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			Model model
			) {
		List<CounselingVO> chooseList = service.chooseCounselingApply(CounselingVO.builder().cstStudent(authMember.getMemId()).build());
		Map<String, Object> resultMap = Collections.singletonMap("chooseList", chooseList);
		return resultMap;
	}
	
	
	/**
	 * 학생 상담 신청 form
	 * @author 김성준
	 * @since 2021. 2. 1.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 2. 1.    김성준    		   최초작성
	 * 2021. 3. 8	   전진원		  상담 신청 메세지 전송 기능
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	@RequestMapping(value="/lms/student/counseling/applyInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> applyInsert(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			CounselingVO counselingVO, BindingResult errors,
			Model model
			) {
		
		counselingVO.setCstStudent(authMember.getMemId());
		//상담 신청 메세지 보내기
		MessageVO messageVO = new MessageVO();
		messageVO.setRecipient(counselingVO.getCstProfessor());
		messageVO.setMsgContent(authMember.getMemName()+" 학생이 상담을 신청했습니다.");
		msgService.insertMessage(messageVO);
		
		ServiceResult successResult = service.createCounseling(counselingVO);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap = Collections.singletonMap("successResult", successResult);
		
		return resultMap;
	}
	
	@RequestMapping(value="/lms/student/counseling/selectChooseTime.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> selectChooseTime(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			CounselingVO counselingVO, BindingResult errors,
			Model model
			) {
		counselingVO.setCstStudent(authMember.getMemId());
		List<CounselingVO> chooseTimeList = service.selectChooseCounselingTime(counselingVO);
		Map<String, Object> resultMap = Collections.singletonMap("chooseTimeList", chooseTimeList);
		return resultMap;
	}
	
	
	/**
	 * 학생 상담 수정
	 * @author 김성준
	 * @since 2021. 2. 1.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 2. 1.    김성준       최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	@RequestMapping(value="/lms/student/counseling/applyupdate.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> counselingupdate(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			CounselingVO coun
			,Model model 
			) {
		
		coun.setCstStudent(authMember.getMemId());
		
		ServiceResult result = service.counselingUpdate(coun);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
	/**
	 * 학생 상담 삭제
	 * @author 김성준
	 * @since 2021. 2. 1.
	 * @version 1.0
	 * @see javax.servlet.http.HttpServlet
	 * [[개정이력(Modification Information)]]
	 * 수정일                          수정자               수정내용
	 * --------     --------    ----------------------
	 * 2021. 2. 1.    김성준       최초작성
	 * Copyright (c) 2021 by DDIT All right reserved
	 */
	@RequestMapping(value="/lms/student/counseling/applydelete.do")
	public String counselingApplydelete(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			Model model , CounselingVO coun
			, RedirectAttributes redirectAttribute
//			,@RequestParam("cstNo") Integer cstNo
			) {
		
//		CounselingVO coun = new CounselingVO();
//		coun.setCstNo(cstNo);
		ServiceResult result = service.counselingDelete(coun);
		if(result == ServiceResult.OK) {
			redirectAttribute.addFlashAttribute("msg", NotyMessageVO.builder("삭제 완료").layout(NotyLayout.topCenter).type(NotyType.success).timeout(3000).build());
		} else {
			redirectAttribute.addFlashAttribute("msg",  NotyMessageVO.builder("서버 오류").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
		}
		
		return "redirect:/lms/student/counseling/apply.do";
	}
	
}
