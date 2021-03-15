package kr.or.ddit.lms.student.scholarship.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.admin.lecture.service.AdminLectureService;
import kr.or.ddit.admin.student.service.AdminStudentService;
import kr.or.ddit.admin.student.vo.AdminMemVO;
import kr.or.ddit.lms.student.scholarship.service.StudentSchaolarshipService;
import kr.or.ddit.lms.student.scholarship.vo.StuScholarshipVO;
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
 * @since 2021. 1. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 28.     조예슬       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class StudentSchaolarshipApplyController {
	@Inject
	private StudentSchaolarshipService service;
	@Inject
	private AdminStudentService adminStuservice;
	@Inject
	private AdminLectureService adminLectureService;
	
	// 학생 정보 가져오는 메서드
	private void getStudentInformation(MemberVO authMember,Model model) {
		AdminMemVO stuVO = new AdminMemVO();
		stuVO.setMemId(authMember.getMemId());
		stuVO = adminStuservice.retrieveStudent(stuVO);
		model.addAttribute("stuVO", stuVO);
	}
	// 현재 학기 가져오는 메서드
	private CodeVO getCurrentSmst() {
		CodeVO currentSmst = new CodeVO();
		currentSmst.setGroupCode("A00");
		currentSmst = adminLectureService.retrieveCurrentSmst(currentSmst);
		return currentSmst;
	}
	
	/**
	 * 학생 장학금 신청/조회 페이지
	 * @author 조예슬
	 * @since 2021. 2. 1.
	 */
	@RequestMapping("/lms/student/schaolarship/apply.do")
	public String SchaolarshipApply(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
									@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
									@ModelAttribute("searchVO") SearchVO searchVO,
									Model model) {
		List<ScholarshipFundVO> smst = service.retrieveStuScholarshipApplySmst(authMember);
		PagingVO<ScholarshipFundVO> paging =new PagingVO<>(5,5);
		paging.setSearchVO(searchVO);
		paging.setSearchDetail((ScholarshipFundVO.builder().sfundStudent(authMember.getMemId()).build()));
		
		int totalRecord = service.retrieveStuScholarshipApplyCount(paging);
		paging.setTotalRecord(totalRecord);
		paging.setCurrentPage(currentPage);
		List<ScholarshipFundVO> schList = service.retrieveStuScholarshipApplyList(paging);
		paging.setDataList(schList);
		
		model.addAttribute("semeSchList", smst);
		model.addAttribute("paginationInfo", new CustomPaginationInfo<ScholarshipFundVO>(paging));
		
		return "lms/schaolarship/stuSchaolarshipApply";
	}
	/**
	 * 장학금 신청 폼
	 * @author 조예슬
	 * @since 2021. 2. 1.
	 */
	@RequestMapping("/lms/student/schaolarship/stuScholarshipApplyForm.do")
	public String stuScholarshipApplyForm(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
											Model model) {
		// 학생 정보 가져오기(학과명 포함)
		getStudentInformation(authMember,model);
		
		return "lms/schaolarship/stuScholarshipApplyForm";
	}
	

	/**
	 * 학생이 장학금 신청할 시, 신청 가능한 교내 장학금 나열
	 * @author 조예슬
	 * @since 2021. 2. 1.
	 */
	@RequestMapping(value="/lms/student/schaolarship/apply.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String selectScholarshipType(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
										Model model) {
		ScholarshipFundVO schFundVO = new ScholarshipFundVO();
		// 현재 학기 가져오기
		CodeVO currentSmst = getCurrentSmst();
		
		schFundVO.setSfundStudent(authMember.getMemId());
		schFundVO.setSfundSmst(currentSmst.getCodeName());
		// 학생이 이번학기에 신청한 장학금을 조회하여 신청할 수 있는 장학금만 가져옴
		List<ScholarshipVO> choseList = service.retrieveSholarshipType(schFundVO);
		model.addAttribute("chooseList", choseList);
		return "jsonView";
	}

	/**
	 * 학생이 장학금 신청
	 * @author 조예슬
	 * @since 2021. 2. 1.
	 */
	@RequestMapping(value="/lms/student/schaolarship/applyInsert.do",method=RequestMethod.POST)
	public String insertScholarshipApply(@AuthenticationPrincipal(expression="realMember") MemberVO authMember
										,@Validated(InsertGroup.class) @ModelAttribute("schFundVO")ScholarshipFundVO schFundVO,
										BindingResult errors,
										Model model,RedirectAttributes redirectModel) {
		boolean valid = !errors.hasErrors();
		if(valid) {
			// 현재 학기 가져오기
			CodeVO currentSmst = getCurrentSmst();
			schFundVO.setSfundStudent(authMember.getMemId());
			schFundVO.setSfundSmst(currentSmst.getCodeName());
			try {
				int cnt  = service.createScholarshipApply(schFundVO);
				redirectModel.addFlashAttribute("message", NotyMessageVO.builder("신청완료").type(NotyType.success).build());
				return "redirect:/lms/student/schaolarship/apply.do";
			} catch (DataAccessException e) {
				redirectModel.addFlashAttribute("message", NotyMessageVO.builder("서버 오류").build());
				return "lms/schaolarship/stuScholarshipApplyForm";
			}
			
		}else {
			// 학생정보 가져오기
			getStudentInformation(authMember,model);
			model.addAttribute("message", NotyMessageVO.builder("모든 항목을 입력해주세요.").build());
			return "lms/schaolarship/stuScholarshipApplyForm";
		}
		
	}
	/**장학금신청 상세조회**/
	@RequestMapping("/lms/student/schaolarship/applyView.do")
	public String scholarshipApplyView(@RequestParam("sfundNo")int sfundNo,Model model) {
		try {
			StuScholarshipVO schVO = service.retrieveStuScholarshipApply(sfundNo);
			model.addAttribute("schVO", schVO);
		} catch (DataAccessException e) {
			model.addAttribute("msg", NotyMessageVO.builder("서버오류").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
		}
		
		return "lms/schaolarship/modal/stuSholarshipApplyViewModal";
	}
	
}
