package kr.or.ddit.lms.professor.lecture.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.enrolment.controller.EnrolmentClassPlan;
import kr.or.ddit.enrolment.service.EnrolmentService;
import kr.or.ddit.enrolment.vo.EnrolmentVO;
import kr.or.ddit.lms.professor.lecture.service.ProfessorLectureService;
import kr.or.ddit.lms.professor.lecture.vo.InsertGradeVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.video.vo.LecPlanVO;
import kr.or.ddit.vo.LecPlanListVO;
import kr.or.ddit.vo.LectureTimeVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.SubjectVO;

@Controller
public class ProfessorLectureApplyController extends BaseController {
	@Inject
	ProfessorLectureService service;

	@Inject
	private EnrolmentService enrolmentService;

	@Autowired
	ServletContext context;

	@Autowired
	EnrolmentClassPlan planPDF;

	// 강의계획서 등록 페이지 이동
	@RequestMapping("/lms/professor/class/apply.do")
	public String proClassapply(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember, Model model
	) {
		List<InsertGradeVO> subjectList = service.selectSubNameList(authMember);
		
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("pageTitle", "강의 계획서 등록");
		return "lmsProfessor/class/lmsProClassApply";
	}

	// 강의계획서 등록
	@RequestMapping(value = "/lms/professor/class/apply.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> proClassPlanInsert(
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember, Model model, SubjectVO subjectVO,
			LectureVO lectureVO, @RequestParam(value = "applyList") String[] applyList, RedirectAttributes redirect

	) {

		Map<String, Object> resultMap = new HashMap<>();
		if (!"no".equals(subjectVO.getSubName())) {
			SubjectVO subjectVo = subjectVO;
			lectureVO.setSubjectVo(subjectVo);
			lectureVO.setMemId(authMember.getMemId());
			EnrolmentVO enrolmentVO = service.selectLecCode(lectureVO);
			lectureVO.setLecCode(enrolmentVO.getLecCode());
			List<LecPlanVO> planList = new ArrayList<>();
			for (int i = 1; i < 16; i++) {
				LecPlanVO lecPlanVO = new LecPlanVO();
				lecPlanVO.setLecContent(applyList[i]);
				lecPlanVO.setLecWeek(i);
				lecPlanVO.setLecCode(enrolmentVO.getLecCode());
				planList.add(lecPlanVO);
			}

			try {
				int result = service.planUpdate(subjectVO, lectureVO, planList);
				if (result > 0) {
					resultMap = Collections.singletonMap("message", "OK");
				} else {
					resultMap = Collections.singletonMap("message", "강의 내용을 전부 입력하세요.");
				}
			} catch (Exception e) {
				resultMap = Collections.singletonMap("message", "강의 계획서 등록 실패");
			}
		} else {
			resultMap = Collections.singletonMap("message", "NotName");

		}

		return resultMap;
	}

	@RequestMapping(value = "/lms/professor/class/selectPlan.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> proClassPlanSelect(LecPlanVO lecPlanVO) {

		LectureVO lectureVO = service.selectBK(lecPlanVO);
		List<LecPlanVO> lecPlanList = service.selectPlanWeekContent(lecPlanVO);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("lectureVO", lectureVO);
		resultMap.put("lecPlanList", lecPlanList);

		return resultMap;
	}

	// 강의계획서 미리보기
	@RequestMapping(value = "/lms/professor/class/Plan.do", method = RequestMethod.POST)
	public void proClassPlan(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember,
			SubjectVO subjectVO, LectureVO lectureVO, HttpServletResponse response, Model model

	) throws Exception {
		SubjectVO subjectVo = subjectVO;
		lectureVO.setSubjectVo(subjectVo);

		LectureVO lectureList1 = null;
		List<LectureTimeVO> lectureList2 = null;
		SubjectVO lectureList3 = null;
		MemberVO lectureList4 = null;
		lectureVO.setMemId(authMember.getMemId());

		if (!"no".equals(subjectVo.getSubName())) {
			EnrolmentVO enrolmentVO = service.selectLecCode(lectureVO);
			lectureVO.setLecCode(enrolmentVO.getLecCode());
			// 강의계획 DB에서 받아오기
			lectureList1 = enrolmentService.selectLecturePlan1(enrolmentVO);
			lectureList2 = enrolmentService.selectLecturePlan2(enrolmentVO);
			lectureList3 = enrolmentService.selectLecturePlan3(enrolmentVO);
			lectureList4 = enrolmentService.selectLecturePlan4(enrolmentVO);
			lectureList1.setLecFull(lectureVO.getLecFull());
			lectureList1.setLecGrd(lectureVO.getLecGrd());
			lectureList1.setLecMbk(lectureVO.getLecMbk());
			lectureList1.setLecSbk(lectureVO.getLecSbk());
			lectureList3.setSubName(subjectVO.getSubName());
			lectureList3.setSubCredit(subjectVO.getSubCredit());
			lectureList3.setSubDetail(subjectVO.getSubDetail());
		}

		planPDF.drawPDF(lectureList1, lectureList2, lectureList3, lectureList4, response);

	}

}
