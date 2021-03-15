package kr.or.ddit.myclassroom.att.controller;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.commons.service.MessageService;
import kr.or.ddit.lms.student.grade.service.GradeService;
import kr.or.ddit.lms.student.grade.vo.AttendanceVO;
import kr.or.ddit.lms.student.grade.vo.GradeVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.att.AttVO;
import kr.or.ddit.myclassroom.att.service.AttService;
import kr.or.ddit.myclassroom.test.vo.StudentScoreVO;
import kr.or.ddit.myclassroom.video.vo.ViewRecordVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.MessageVO;
import kr.or.ddit.vo.SubjectVO;
import kr.or.ddit.vo.TakeLecVO;

/**
 * 싸캠 출결 
 */
@Controller
@RequestMapping("/myclass")
public class MyClassAttController extends BaseController {
	
	@Inject
	private AttService service;
	@Inject
	private MessageService msgService;
	@Inject 
	GradeService gradeService;
	/** 교수 출석 페이지 **/
	@RequestMapping("{lecCode}/proAttList.do")
	public String proAttList(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@PathVariable(value ="lecCode", required=true) String lecCode,
			Model model
			) {
		LectureVO lectureVO =new LectureVO();
		lectureVO.setLecCode(lecCode);
		
		//수강 학생 조회
		List<AttVO> attList = service.selectLecMember(lectureVO);
		model.addAttribute("lecCode", lecCode);
		model.addAttribute("attList", attList);
		
		return "myClassRoom/att/proAttList";
	}
	

	//개설강의 페이징 비동기 메소드
	@RequestMapping(value="{lecCode}/proAttSelect.do" , method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> attList(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@PathVariable(value ="lecCode", required=true) String lecCode,
			@RequestParam("memId") String memId
			) throws ServletException, IOException {
		AttVO attvo = new AttVO();
		attvo.setLecCode(lecCode);
		attvo.setMemId(memId);
		GradeVO gradeVO = new GradeVO();
		gradeVO.setLecCode(lecCode);
		gradeVO.setMemId(memId);
		// 학생 출석 조회
		List<AttVO> attList = service.selectAttendCode(attvo);
		// 영상 재생시간 조회
		List<ViewRecordVO> viewRecordList = service.selectViewRecordList(attvo);
		// 수강 번호 조회
		TakeLecVO takeLecVO = service.selectTakeLecNo(attvo);
		// 학생성적테이블에서 출석 등록 여부확인
		StudentScoreVO studentScoreVO = new StudentScoreVO();
		// 출석률 조회
		AttendanceVO totalAttendance = null;
		try {
			totalAttendance = gradeService.getTotalAttendance(gradeVO);
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
		
		if(takeLecVO.getTlecNo()!=null) {
			studentScoreVO = service.selectScrAttend(takeLecVO);
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("attList", attList);
		resultMap.put("viewRecordList", viewRecordList);
		resultMap.put("takeLecVO", takeLecVO);
		resultMap.put("studentScoreVO", studentScoreVO);
		resultMap.put("totalAttendance", totalAttendance);
		return resultMap;
		}
			
	// 학생 출결 개별 수정 
	@RequestMapping(value="{lecCode}/proAttUpdate.do",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public  Map<String, Object> proAttUpdate(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@PathVariable(value ="lecCode", required=true) String lecCode,
			AttVO attVO
			) {
		
		attVO.setLecCode(lecCode);
		int result = service.updateAtt(attVO);
		
		//과목명 조회
		SubjectVO subjectVO =service.selectSubName(attVO);
		MessageVO messageVO = new MessageVO();
		messageVO.setRecipient(attVO.getMemId());
		messageVO.setMsgContent(subjectVO.getSubName()+" 과목의 "+attVO.getWeek()+"주차 출결이 변경되었습니다.");
		
		//메세지 자동 작성
		int msgResult = msgService.insertMessage(messageVO);
		
		
		Map<String, Object> resultMap = Collections.singletonMap("result",result);
		
		return resultMap;
	}
	
	// 학생 출결 점수 등록
	@RequestMapping(value="{lecCode}/insertAttScore.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String insertAttScore(@PathVariable(value ="lecCode", required=true) String lecCode,StudentScoreVO studentScoreVO,Model model) {
		try {
			int cnt = service.insertAttScore(studentScoreVO);
			model.addAttribute("message", "등록 성공");
		} catch (Exception e) {
			model.addAttribute("message", "서버 오류");
		}
		
		return "jsonView";
	}
	
	
}
