package kr.or.ddit.lms.student.grade.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.lms.student.grade.service.GradeService;
import kr.or.ddit.lms.student.grade.vo.GradeVO;
import kr.or.ddit.member.vo.MemberVO;

@Controller
public class SemesterGradeController {
	@Inject
	GradeService gradeService;
	
	@ModelAttribute
	public GradeVO board(@AuthenticationPrincipal(expression="realMember") MemberVO authMember) {
		return new GradeVO();
	}
	
	@RequestMapping("/lms/student/grade/semesterGrade.do")
	public String semester(@AuthenticationPrincipal(expression="realMember") MemberVO authMember, Model model) {
		try {
			List<GradeVO> gradeList = gradeService.getNowSMSTGrade(GradeVO.builder().memId(authMember.getMemId()).build());
			model.addAttribute("gradeList", gradeList);
		} catch (DataAccessException e) {
		}
		return "lms/lmsGrade/semesterGrade";
	}
	
	@ResponseBody
	@PostMapping(value="/lms/student/grade/lectureEvaluation.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> evaluationLecture(@AuthenticationPrincipal(expression="realMember") MemberVO authMember, GradeVO gradeVO){
		try {
			gradeService.evaluationLecture(gradeVO);
			return Collections.singletonMap("result", "OK");
		} catch (DataAccessException e) {
			return Collections.singletonMap("msg", "오류로 인해 별점 등록에 실패하였습니다.");
		}
	}
	
}
