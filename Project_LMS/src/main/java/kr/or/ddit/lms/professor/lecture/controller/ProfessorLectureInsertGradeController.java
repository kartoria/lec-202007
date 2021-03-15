
package kr.or.ddit.lms.professor.lecture.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.enumpkg.GradeSort;
import kr.or.ddit.lms.professor.lecture.service.ProfessorLectureService;
import kr.or.ddit.lms.professor.lecture.vo.InsertGradeVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.NotyMessageVO;

/**
 * 교수가 학생들 성적등록
 * @author 김선준
 * @since 2021. 2. 14
 */
@Controller
public class ProfessorLectureInsertGradeController extends BaseController{
	
	@Inject
	ProfessorLectureService lectureService;	
	
	@RequestMapping("/lms/professor/class/gradeList.do")
	public String studentGradeList(@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			, InsertGradeVO insertGradeVO
			, Model model){
		
		// 교수의 학번을 VO에 세팅
		insertGradeVO.setMemId(authMember.getMemId());
		
		// 처음은 석차순으로 정렬함
		if(StringUtils.isBlank(insertGradeVO.getHowToSort()))
			insertGradeVO.setHowToSort(GradeSort.ORDER_BY_RANK.getGradeSort());
		
		printInfo("howToSort", insertGradeVO.getHowToSort());
		
		// 교수의 학번을 들고가서 그 교수가 강의중인 강의목록을 가져옴
		List<InsertGradeVO> lectureList = lectureService.selectLectureList(insertGradeVO);
		
		// 처음 들어갈땐 강의목록의 첫번째 강의를 선택해줌
		String lecCode = insertGradeVO.getLecCode();
		if(lectureList.size() > 0) {
			if(lecCode == null) 
				lecCode = lectureList.get(0).getLecCode();
			
			insertGradeVO.setLecCode(lecCode);
		
			// 강의 하나를 선택해서 그 강의를 수강중인 학생의 점수목록를 가져옴
			List<InsertGradeVO> studentScoreList = lectureService.selectStudentScoreList(insertGradeVO);
			
			// 성적 AP ~ N 까지 셀렉트박스에 넣어줄 리스트
			List<InsertGradeVO> gradeCodeList = lectureService.seleteGradeCodeList();
			
			model.addAttribute("lectureList", lectureList);
			model.addAttribute("studentScoreList", studentScoreList);
			model.addAttribute("gradeCodeList", gradeCodeList);
		}
		return "lmsProfessor/class/lmsProClassInsertGrade";
	}
	
	@ResponseBody
	@PostMapping(value="/lms/professor/class/insertGrade.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public NotyMessageVO insertGrade(InsertGradeVO insertGradeVO){
		try {
			lectureService.updateStudentScore(insertGradeVO);
			return notyInsertSuccessMessage();
		}catch (RuntimeException e) {
			return notyErrorMessage();
		}
	}
}
