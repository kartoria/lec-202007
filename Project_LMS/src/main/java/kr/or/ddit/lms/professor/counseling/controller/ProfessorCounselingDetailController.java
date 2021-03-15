package kr.or.ddit.lms.professor.counseling.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.lms.professor.counseling.service.ProfessorCounselingService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.CounselingVO;

@Controller
public class ProfessorCounselingDetailController extends BaseController{
	
	@Inject
	private ProfessorCounselingService service;
	
	@RequestMapping("/lms/professor/counseling/detail.do")
	public String proCounselingcounselingDetail(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@RequestParam(value="what", required=true) String cstStudent,
			Model model
			) {
		CounselingVO coun = new CounselingVO();
		coun.setCstProfessor(authMember.getMemId());
		coun.setCstStudent(cstStudent);
		CounselingVO counDetail = service.selectDetailCounseling(coun);
		List<CounselingVO> selectDate = service.selectChooseCounselingDate(coun);
		model.addAttribute("counDetail", counDetail);
		model.addAttribute("selectDate", selectDate);
		model.addAttribute("pageLink", "/lms/professor/counseling/list.do");
		model.addAttribute("pageTitle", "상담 등록 및 조회");
		return "lmsProfessor/counseling/lmsProCounselingDetail";
	}
	
	@RequestMapping(value="/lms/professor/counseling/proInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> proCounselingInsertForm(
		CounselingVO counselingVO, @AuthenticationPrincipal(expression="realMember") MemberVO authMember,
		 Model model) {
		counselingVO.setCstProfessor(authMember.getMemId());
		int updateForm = service.updateCounseling(counselingVO);
		Map<String, Object> resultMap = new HashMap<>();
		if(updateForm > 0) {
			resultMap = Collections.singletonMap("updateForm", updateForm);
		}
		return resultMap;
	}
	
	@RequestMapping(value="/lms/professor/counseling/chooseDate.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> getChooseDate(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			CounselingVO counselingVO,
			Model model
			){
		
		counselingVO.setCstProfessor(authMember.getMemId());
		CounselingVO counVO = service.chooseDateCounseling(counselingVO);
		Map<String, Object> resultMap = Collections.singletonMap("counVO", counVO);
		return resultMap;
	}
	
}
