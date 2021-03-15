package kr.or.ddit.lms.professor.counseling.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.commons.service.MessageService;
import kr.or.ddit.lms.professor.counseling.service.ProfessorCounselingService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CounselingVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.MessageVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class ProfessorCounselingApplyController extends BaseController{
	
	@Inject
	private ProfessorCounselingService service;
	
	@Inject
	private MessageService msgService;
	
	@RequestMapping("lms/professor/counseling/apply.do")
	public String proCounselingapply(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO,
			Model model
			) {
		
		PagingVO<CounselingVO> paging = new PagingVO<>(10, 5);
		paging.setSearchVO(searchVO);
		paging.setSearchDetail(CounselingVO.builder().cstProfessor(authMember.getMemId()).build());

		int totalRecord = service.retrieveCounselingApplyCount(paging);
		
		paging.setTotalRecord(totalRecord);
		paging.setCurrentPage(currentPage);
		List<CounselingVO> counList = service.retrieveCounselingApplyList(paging);
		
		paging.setDataList(counList);
		model.addAttribute("paginationInfo", new CustomPaginationInfo<CounselingVO>(paging));
		model.addAttribute("pageTitle", "상담 신청 현황");
		return "lmsProfessor/counseling/lmsProCounselingApply";
	}
	
	
	
	
	
	@RequestMapping(value="/lms/professor/counseling/updateApply.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> updateCounselingCstNote(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			CounselingVO coun,
			Model model
			) {
		coun.setCstProfessor(authMember.getMemId());
		
		
		MessageVO messageVO = new MessageVO();
		messageVO.setRecipient(coun.getCstStudent());
		messageVO.setMsgContent("상담 신청이 처리되었습니다.");
		
		int msgResult = msgService.insertMessage(messageVO);
		
		int updateCounseling = service.updateCounselingCstNote(coun);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("updateCounseling", updateCounseling);
		return resultMap;
	}
}
