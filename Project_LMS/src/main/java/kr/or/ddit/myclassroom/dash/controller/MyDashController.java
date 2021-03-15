package kr.or.ddit.myclassroom.dash.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.commons.service.BoardService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.dash.vo.AttChartVO;
import kr.or.ddit.myclassroom.task.service.ITaskService;
import kr.or.ddit.myclassroom.task.vo.TaskVO;
import kr.or.ddit.myclassroom.test.service.TestService;
import kr.or.ddit.myclassroom.test.vo.TestVO;
import kr.or.ddit.vo.BoardVO;
import oracle.net.aso.e;

/** 마클 대시보드 **/
@Controller
public class MyDashController extends BaseController{

	@Inject
	private BoardService boService;
	@Inject
	private ITaskService taskService;
	@Inject
	private TestService testService;
	
	/** 대시보드 **/
	@RequestMapping({"/myclass/{lecCode}/dashList.do", "/myclass/{lecCode}"})
	public String dashList(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@PathVariable(value ="lecCode", required=true) String lecCode
			, @RequestAttribute("subName") String subName
			, Model model) {
		BoardVO board = BoardVO.builder().lecCode(lecCode).boGroupCode("NTC").build();
		// 1. 최근 공지글(board)
		List<BoardVO> notyList = boService.retrieveRecentNoty(board);
		// 2. 최근 토론글(board)
		board = BoardVO.builder().lecCode(lecCode).boGroupCode("DISC").build();
		List<BoardVO> disList = boService.retrieveRecentDis(board);
		// 3. 최근 과제글(task)
		TaskVO task = TaskVO.builder().lecCode(lecCode).memId(authMember.getMemId()).build();
		List<TaskVO> taskList = taskService.retrieveRecentTask(task);
		// 4. 최근 시험글(test)
		List<TestVO> testList = testService.retrieveProTestList(lecCode);
		Map<String, TestVO> testMap = new HashMap<>();
		for(int i=0; i<testList.size();i++) {
			testMap.put(testList.get(i).getTestNo()+"", testList.get(i));
		}
		// 5. 최근 과제/시험 중 제출하지 않은 글(task/taskSubmit-totalScore)
		if("ROLE_STUDENT".equals(authMember.getMemType())) {
			List<TaskVO> noSbList = taskService.retrieveNoSbList(task);
			TestVO test = TestVO.builder().lecCode(lecCode).memId(authMember.getMemId()).build();
			List<TestVO> noTestList = testService.retrieveNoTestList(test);
			Map<Integer, TestVO> noTestMap= new HashMap<>();
			if(testList.size()>0 && noTestList.size() >0) {
				for(int i=0; i<noTestList.size();i++) {
					if(noTestList.get(i)!=null) {
						noTestMap.put(noTestList.get(i).getTestNo(), noTestList.get(i));
					}	
				}
			}
			model.addAttribute("noTestMap",noTestMap);
			model.addAttribute("noSbList",noSbList);
		} else {
			// 6. 최근 답변해야하는 질문 목록
			board = BoardVO.builder().lecCode(lecCode).boGroupCode("QNA").build();
			List<BoardVO> proQnaList = boService.retrieveQnaList(board);
			model.addAttribute("proQnaList", proQnaList);
		}
		model.addAttribute("notyList",notyList);
		model.addAttribute("disList",disList);
		model.addAttribute("taskList",taskList);
		model.addAttribute("testMap",testMap);
		model.addAttribute("pageTitle", subName);
		return "myClassRoom/dash/myDash";
	}
	
	/** 대시보드 평가 **/
	@RequestMapping(value="/myclass/attDashChart.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public AttChartVO attDashChart(Model model) {
		AttChartVO attChart = boService.retrieveAttChart();
		return attChart;
	}
}
