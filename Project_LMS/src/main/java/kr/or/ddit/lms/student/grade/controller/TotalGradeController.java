package kr.or.ddit.lms.student.grade.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.lms.student.grade.service.GradeService;
import kr.or.ddit.lms.student.grade.vo.GradeVO;
import kr.or.ddit.member.vo.MemberVO;

@Controller
public class TotalGradeController extends BaseController {
	@Inject
	GradeService gradeService;

	@RequestMapping("/lms/student/grade/totalGrade.do")
	public String totalgrade(Model model, @AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		GradeVO gradeVO = GradeVO.builder().memId(authMember.getMemId()).build();
		GradeVO totalGrade = gradeService.getTotalGrade(gradeVO);
		List<GradeVO> smstList = gradeService.getSMSTList(gradeVO);
		model.addAttribute("totalGrade", totalGrade);
		model.addAttribute("smstList", smstList);
		model.addAttribute("pageTitle", "전체성적");
		return "lms/lmsGrade/totalGrade";
	}

	@ResponseBody
	@PostMapping(value = "/lms/student/grade/getGradeList.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GradeVO getSMSTGradeList(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember,
			@RequestParam(value = "lecSmst", required = true) String lecSmst) {
		GradeVO gradeVO = GradeVO.builder().memId(authMember.getMemId()).lecSmst(lecSmst).build();
		
		try {
			String rank = gradeService.getRank(gradeVO);
			List<GradeVO> smstGradeList = gradeService.getSMSTGradeList(gradeVO);
			gradeVO.setRank(rank);
			gradeVO.setSmstGradeList(smstGradeList);
			return gradeVO;
		} catch (DataAccessException e) {
			return null;
		}
	}
}
