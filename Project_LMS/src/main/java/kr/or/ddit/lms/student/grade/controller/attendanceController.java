package kr.or.ddit.lms.student.grade.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.lms.student.grade.service.GradeService;
import kr.or.ddit.lms.student.grade.vo.AttendanceVO;
import kr.or.ddit.lms.student.grade.vo.GradeVO;
import kr.or.ddit.member.vo.MemberVO;

@Controller
public class attendanceController extends BaseController{
	@Inject 
	GradeService gradeService;
	
	@RequestMapping("/lms/student/grade/attendance.do")
	public String attendance(@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			, GradeVO gradeVO
			, Model model) {
		String memId = authMember.getMemId();
		String lecCode = gradeVO.getLecCode();
		gradeVO.setMemId(memId);
		
		List<GradeVO> lectureList = gradeService.getNowSMSTLectureList(gradeVO);
		
		AttendanceVO totalAttendance = null;
		if(lectureList.size() > 0) {
			model.addAttribute("lectureList", lectureList);
			if(lecCode == null) lecCode = lectureList.get(0).getLecCode();
			gradeVO.setLecCode(lecCode);
			
			try {
				totalAttendance = gradeService.getTotalAttendance(gradeVO);
				List<AttendanceVO> attendanceList = gradeService.getAttendance(gradeVO);
				model.addAttribute("attendanceList", attendanceList);
			}catch(DataAccessException e) {
				totalAttendance = AttendanceVO.builder()
						.totalTime(0)
						.countAttend(0)
						.countLate(0)
						.countEarly(0)
						.countAbsent(0)
						.avgAtten(0f)
						.build();
			}
		}else {
			totalAttendance = AttendanceVO.builder()
					.totalTime(0)
					.countAttend(0)
					.countLate(0)
					.countEarly(0)
					.countAbsent(0)
					.avgAtten(0f)
					.build();
		}
		
		model.addAttribute("totalAttendance", totalAttendance);
		return "lms/lmsGrade/attendance";
	}
}
