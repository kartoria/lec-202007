package kr.or.ddit.admin.tuition.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.admin.tuition.service.AdminTuitionService;
import kr.or.ddit.commons.service.MessageService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.DepartmentVO;
import kr.or.ddit.vo.MessageVO;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;

@Controller
public class adminTuitionDivisionController {
	@Inject
	private AdminTuitionService service;
	
	@Inject
	private MessageService msgService;
	
	//관리자 분할신청 내역 
	@RequestMapping("/admin/tuitionDivision.do")
	public String adminTuitionDivision(Model model,
			@RequestParam(value="page", required=false,defaultValue="1") int currentPage
			,@ModelAttribute("searchVO") SearchVO searchVO
			) {
		PagingVO<MemberVO> paging = new PagingVO<>(10,5);
		paging.setSearchVO(searchVO);
		String searchType = searchVO.getSearchType();
		int totalRecord =0;
		//단과대 명 받아오기
		List<CodeVO> codeList = service.selectCodeName();  
		//검색조건 없을 때 전체 조회
		if(searchType ==null || searchType==""|| "".equals(searchType)) {
			totalRecord = service.memberCount(paging);
			paging.setTotalRecord(totalRecord);
			paging.setCurrentPage(currentPage);
			//학생 리스트 받아오기
			List<MemberVO> memberList = service.selectMember(paging);
			paging.setDataList(memberList);
		//검색 조건 있을 때		
		}else {
			totalRecord = service.memberDepCount(paging);
			paging.setTotalRecord(totalRecord);
			paging.setCurrentPage(currentPage);
			List<MemberVO> memberList = service.selectDepstuList(paging);
			paging.setDataList(memberList);
		}
		
		model.addAttribute("codeList",codeList);
		model.addAttribute("paginationInfo", new CustomPaginationInfo<MemberVO>(paging));		
		model.addAttribute("pageTitle", "분할 납부 신청");
		
		return "admin/adminTuition/adminTuitionDivisionList";
	}
	
	//학과 명 조회
	@RequestMapping(value = "/lms/student/admin/adminDepNameList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> selectDepName(
			@RequestParam("codeName") String codeName
			) {
		CodeVO code = new CodeVO();
		code.setCodeName(codeName);
		List<DepartmentVO> departmentList =service.selectDepName(code);
		
		Map<String, Object> resultMap = Collections.singletonMap("departmentList", departmentList);
		return resultMap;
	}
	
	//승인 반려 처리
	@RequestMapping(value = "/admin/adminTuitionUpdate.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> successUpdate(
			@RequestParam("resultFlag") String flag,
			@RequestParam("memId") String memId			
			){
		MemberVO member = new MemberVO();
		member.setMemId(memId);
		String resultText="";
		int result = 0;
		
		MessageVO messageVO = new MessageVO();
		messageVO.setRecipient(memId);
//		
//		//메세지 자동 작성
		
		if("success".equals(flag)) {
			result = service.adminTuitionUpdateSucess(member);
			if(result>0) {
				int divResult = service.insertPayDiv(member);
				if(divResult>0) {
					resultText="OK";
					messageVO.setMsgContent("분할납부 신청이 승인되었습니다.");
					msgService.insertMessage(messageVO);
				}
			}else {
				resultText="NotOK";
			}
		}
		if("faile".equals(flag)) {
			result = service.adminTuitionUpdateFail(member);
			if(result>0) {
				resultText="Fail";
				messageVO.setMsgContent("분할납부 신청이 반려되었습니다.");
				msgService.insertMessage(messageVO);
			}else {
				resultText="NotFail";
			}
		}
		
		Map<String, Object> resultMap = Collections.singletonMap("resultText", resultText);
		return resultMap;
	}
	
	
}
