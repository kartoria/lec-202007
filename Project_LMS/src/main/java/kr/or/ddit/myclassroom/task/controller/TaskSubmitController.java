package kr.or.ddit.myclassroom.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.enumpkg.Browser;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.task.service.ITaskSubmitService;
import kr.or.ddit.myclassroom.task.vo.TaskSubmitVO;
import kr.or.ddit.vo.AttachVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 과제 제출  
 */
@Controller
public class TaskSubmitController extends BaseController{
	@Inject
	ITaskSubmitService service;
	
	@ModelAttribute("taskSb")
	public TaskSubmitVO taskSb() {
		return new TaskSubmitVO();
	}
	
	/** 과제 제출 댓글 리스트 조회 **/
	@PostMapping(value="/taskSb/list.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String taskSbList(@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
							@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
							@RequestParam(value="taskNo", required=true) int taskNo,
							Model model){
		TaskSubmitVO searchDetail = new TaskSubmitVO();
		searchDetail.setTaskNo(taskNo);
		
		PagingVO<TaskSubmitVO> pagingVO = new PagingVO<>(10,10);
		pagingVO.setSearchDetail(searchDetail);
		
		int totalRecord = 0;
		try {
			totalRecord = service.retrieveTaskSbCount(pagingVO);
		} catch (SQLException e) {
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<TaskSubmitVO> dataList = new ArrayList<>();
		try {
			dataList = service.retrieveTaskSbList(pagingVO);
		} catch (SQLException e) {
			LOGGER.error(this.getClass().getName() + " " + e.getMessage());
		}
		pagingVO.setDataList(dataList);
		pagingVO.setMemId(authMember.getMemId());
		model.addAttribute("pagingVO", pagingVO);
		return "jsonView";
	}
	
	
	/** 과제 제출 등록 **/
	@PostMapping(value="/taskSb/insert.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String , Object> taskSbInsert(TaskSubmitVO taskSb, @AuthenticationPrincipal(expression="realMember") MemberVO authMember){
		taskSb.setMemId(authMember.getMemId());
		try {
			service.createTask(taskSb);
			return Collections.singletonMap("result","OK");
		} catch (Exception e) {
			return Collections.singletonMap("message", NotyMessageVO.builder("댓글 등록 실패").build());
		}
	}
	
	/** 제출 과제 삭제 **/
	@PostMapping(value="/taskSb/delete.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> taskSbDel(TaskSubmitVO taskSb, @AuthenticationPrincipal(expression="realMember") MemberVO authMember){
		try {
			service.deleteTaskSb(taskSb);
			return Collections.singletonMap("result", "OK");
		} catch (Exception e) {
			return Collections.singletonMap("message", NotyMessageVO.builder("댓글 등록 오류").build());
		}
	}
	
	/** 제출 과제 다운로드 **/
	@RequestMapping("/taskSb/download.do")
	public String download(TaskSubmitVO taskSb,
				@RequestHeader(value="User-Agent", required=false) String agent, Model model) {
		Browser browser = Browser.getBrowserConstant(agent);
		taskSb= service.download(taskSb);
		model.addAttribute("taskSb", taskSb);
		return "downloadTaskSb";
	}
	
	/** 제출 배점 등록 **/
	@RequestMapping(value="/taskSb/taskScrInsert.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String , Object> taskScrInsert(TaskSubmitVO taskSb, @AuthenticationPrincipal(expression="realMember") MemberVO authMember) {
//		taskSb.setMemId(authMember.getMemId());
		try {
			service.taskScrInsert(taskSb);
			return Collections.singletonMap("result","OK");
		} catch (Exception e) {
			return Collections.singletonMap("message", NotyMessageVO.builder("배점 등록 실패").build());
		}
	}
	
	/** 제출 확인 **/
	@RequestMapping(value="/taskSb/taskSelect.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String , Object> taskSelect(TaskSubmitVO taskSb, @AuthenticationPrincipal(expression="realMember") MemberVO authMember) {
		taskSb.setMemId(authMember.getMemId());
		try {
			service.taskSelect(taskSb);
			return Collections.singletonMap("result","OK");
		} catch (Exception e) {
			return Collections.singletonMap("message", NotyMessageVO.builder("배점 등록 실패").build());
		}
	}
}
