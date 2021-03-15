package kr.or.ddit.commons.controller;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.commons.service.ScheduleService;
import kr.or.ddit.commons.vo.ScheduleVO;
import kr.or.ddit.member.vo.MemberVO;

@Controller
public class MainSchedulerController {
	@Inject
	private ScheduleService service;
	
	/**Cyber풀캘린더 조회
	 * @throws JsonProcessingException **/
	@RequestMapping(value= {"/cyber/main.do", "/cyber"})
	public String cybermain(Model model) throws JsonProcessingException {
		List<ScheduleVO> dataList;
		try {
			dataList = service.retreiveScheduleList();
			ObjectMapper mapper = new ObjectMapper();
			String scheduleList = mapper.writeValueAsString(dataList);
			model.addAttribute("scheduleList", scheduleList);
		} catch (DataAccessException e) {
			model.addAttribute("msg", "서버오류");
		}
		return "cyber/cyberMain";
	}
	/**lms풀캘린더 조회
	 * @throws JsonProcessingException **/
	@RequestMapping(value= {"/lms/lmsmain.do", "/lms"})
	public String lmsmain(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,Model model) throws JsonProcessingException {
		try {
			List<ScheduleVO> dataList;
			dataList = service.retreiveScheduleList();
			ObjectMapper mapper = new ObjectMapper();
			String scheduleList = mapper.writeValueAsString(dataList);
			model.addAttribute("scheduleList", scheduleList);
		} catch (DataAccessException e) {
			model.addAttribute("msg", "서버오류");
		}
		String role = authMember.getMemType();
		String goPage = null;
		if(role.equals("ROLE_STUDENT")) goPage = "lms/lmsMain";
		else if(role.equals("ROLE_PROFESSOR")) goPage = "lmsProfessor/lmsProfessorMain";
		else goPage = "redirect:/";
		return goPage;
	}
	
	/**공통 상세조회 modal**/
	@RequestMapping("/common/{scheNo}/scheduleView.do")
	public String scheduleView(@PathVariable(value="scheNo", required=true) int scheNo,
								Model model) {
		ScheduleVO scheVO = new ScheduleVO();
		scheVO.setScheNo(scheNo);
		scheVO = service.retrieveSchedule(scheVO);
		model.addAttribute("scheVO", scheVO);
		return "cmmn/main/commonScheduleView";
	}
	
}
