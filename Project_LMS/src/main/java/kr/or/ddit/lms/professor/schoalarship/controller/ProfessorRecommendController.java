package kr.or.ddit.lms.professor.schoalarship.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.admin.lecture.service.AdminLectureService;
import kr.or.ddit.lms.professor.schoalarship.service.ProfessorSchoalarshipService;
import kr.or.ddit.lms.professor.schoalarship.vo.StudentFormVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ScholarshipFundVO;
import kr.or.ddit.vo.ScholarshipVO;
import kr.or.ddit.vo.SearchVO;
/**
 * @author 조예슬
 * @since 2021. 2. 01.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 01.     조예슬       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class ProfessorRecommendController {
	@Inject
	private ProfessorSchoalarshipService service;
	@Inject
	private AdminLectureService adminLectureService;
	
	@ModelAttribute("schVO")
	public ScholarshipFundVO schVO() {
		return new ScholarshipFundVO();
	}
	
	// 현재 학기 가져오는 메서드
	private CodeVO getCurrentSmst() {
		CodeVO currentSmst = new CodeVO();
		currentSmst.setGroupCode("A00");
		currentSmst = adminLectureService.retrieveCurrentSmst(currentSmst);
		return currentSmst;
	}
	
	/**본인이 추천한 장학생 목록**/
	@RequestMapping("/lms/professor/scholarship/recommendList.do")
	public String recommendList(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
						@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
						@ModelAttribute("searchVO") SearchVO searchVO,
						Model model) {
		List<ScholarshipFundVO> smst = service.retrieveRecomScholarshipSmstList(authMember);
		PagingVO<ScholarshipFundVO> paging =new PagingVO<>(5,5);
		paging.setSearchVO(searchVO);
		paging.setSearchDetail((ScholarshipFundVO.builder().sfundProfessor(authMember.getMemId()).build()));
		
		int totalRecord = service.retrieveRecommendStudentCount(paging);
		paging.setTotalRecord(totalRecord);
		paging.setCurrentPage(currentPage);
		List<ScholarshipFundVO> schList = service.retrieveRecommendStudentList(paging);
		paging.setDataList(schList);
		
		model.addAttribute("semester", smst);
		model.addAttribute("paginationInfo", new CustomPaginationInfo<ScholarshipFundVO>(paging));
		
		return "lmsProfessor/professorScholarship/proRecommendList";
	}
	
	/**학과의 학생 목록 조회**/
	@RequestMapping("/lms/professor/scholarship/studentList.do")
	public String searchStudent(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
					@ModelAttribute("searchVO") SearchVO searchVO,
					Model model) {
		
		// 학과 학생들 명단
		StudentFormVO stuVO = new StudentFormVO();
		stuVO.setProId(authMember.getMemId());
		stuVO.setMemType("ROLE_STUDENT");
		stuVO.setSearchVO(searchVO);
		List<MemberVO> memList = service.retrieveStudentByDep(stuVO);
		
		model.addAttribute("memList", memList);
		return "jsonView";
	}
	
	/**장학생 추천페이지**/
	@RequestMapping("/lms/professor/scholarship/recommendForm.do")
	public String recommend(Model model) {
		return "lmsProfessor/professorScholarship/proRecommendForm";
	}
	/**장학생 추천 종류가져오기**/
	@RequestMapping("/lms/professor/scholarship/getScholarship.do")
	public String getscholarshiptype(Model model) {
		List<ScholarshipVO> schlist = service.retrieveRecommendScholarList();
		model.addAttribute("schlist", schlist);
		return "jsonView";
	}
	/**장학생추천**/
	@RequestMapping(value="/lms/professor/scholarship/recommend.do",method=RequestMethod.POST)
	public String insertRecommend(@Validated(InsertGroup.class)@ModelAttribute("schVO") ScholarshipFundVO schVO,BindingResult errors, Model model,RedirectAttributes redirectModel) {
		String goPage = null;
		
		// 현재 학기 가져오기
		CodeVO currentSmst = getCurrentSmst();
		schVO.setSfundSmst(currentSmst.getCodeName());
		
		boolean valid = !errors.hasErrors();
		
		if(valid) {
			try {
				ScholarshipFundVO schFundVO = service.checkRecommendRecord(schVO);
				if(schFundVO==null) {
					int cnt = service.createRecommendScholarship(schVO);
					redirectModel.addFlashAttribute("message", NotyMessageVO.builder("등록완료").layout(NotyLayout.topCenter).type(NotyType.success).timeout(3000).build());
					goPage = "redirect:/lms/professor/scholarship/recommendList.do";
				}else {
					model.addAttribute("message", NotyMessageVO.builder("이미 신청된 학생입니다.").layout(NotyLayout.topCenter).timeout(3000).build());
					goPage = "lmsProfessor/professorScholarship/proRecommendForm";
				}
			} catch (Exception e) {
				model.addAttribute("message", NotyMessageVO.builder("서버오류").layout(NotyLayout.topCenter).timeout(3000).build());
				goPage = "lmsProfessor/professorScholarship/proRecommendForm";
			}
			
		}else {
			model.addAttribute("message", NotyMessageVO.builder("입력 형식에 맞게 입력해주세요.").layout(NotyLayout.topCenter).timeout(3000).build());
			goPage = "lmsProfessor/professorScholarship/proRecommendForm";
		}
		
		return goPage;
	}
	/**신청철회**/
	@RequestMapping(value="/lms/professor/scholarship/cancelRecommend.do",method=RequestMethod.POST)
	public String cancelRecommend(@ModelAttribute("schVO")ScholarshipFundVO schVO,Model model) {
		try {
			int cnt = service.removeRecommendScholarship(schVO);
			//ajax
			model.addAttribute("message", "철회완료되었습니다.");
		} catch (DataAccessException e) {
			//ajax
			model.addAttribute("message", "서버오류");
		}
		return "jsonView";
	}
	/**교수 장학금상세조회 모달**/
	@RequestMapping("/lms/professor/scholarship/{sfundNo}/scholarshipDetail.do")
	public String scholarshipDetail(@PathVariable(value="sfundNo", required=true) int sfundNo,Model model) {
		try {
			ScholarshipFundVO schVO = new ScholarshipFundVO();
			schVO.setSfundNo(sfundNo);
			schVO = service.retrieveRecommendDetail(schVO);
			model.addAttribute("schVO", schVO);
		} catch (Exception e) {
			model.addAttribute("msg", NotyMessageVO.builder("서버오류").layout(NotyLayout.topCenter).timeout(3000).build());
		}
		
		return "lmsProfessor/professorScholarship/modal/proScholarshipView";
	}
	
	
}
