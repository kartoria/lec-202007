package kr.or.ddit.myclassroom.task.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.task.service.ITaskService;
import kr.or.ddit.myclassroom.task.vo.TaskVO;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.PagingVO;

@Controller
public class TaskController extends BaseController{
	@Inject
	private ITaskService service;
	
	private void addCommandAttribute(Model model) {
		model.addAttribute("command", "MODIFY");
	}
	
	/** 과제 리스트 **/
	@RequestMapping("/myclass/{lecCode}/taskList.do")
	public String taskList(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
						@PathVariable(value ="lecCode", required=true) String lecCode, @RequestParam(value="page", required=false, defaultValue="1") int currentPage,
						@RequestAttribute("subName") String subName
						,Model model) { 
		PagingVO<TaskVO> pagingVO = new PagingVO<>(7, 7);
		if(lecCode !=null) {
			pagingVO.setSearchDetail((TaskVO.builder().lecCode(lecCode).build()));
		}
		int totalRecord = service.retrieveTaskCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<TaskVO> taskList = service.retrieveTaskList(pagingVO);
		for(TaskVO task: taskList) {
			task.setMemId(authMember.getMemId());
		}
		pagingVO.setDataList(taskList);
		if(!taskList.isEmpty()) {
			if("ROLE_STUDENT".equals(authMember.getMemType())){
				// 제출 여부 확인
				List<TaskVO> submitList = service.retrieveTaskSb(taskList);
				model.addAttribute("submitList",submitList);
			}
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "과제"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/taskList.do");
		model.addAttribute("paginationInfo", new CustomPaginationInfo<TaskVO>(pagingVO));
		return "myClassRoom/task/myTaskList";
	}
	
	/** 과제 상세보기 **/
	@RequestMapping("/myclass/{lecCode}/{taskNo}/taskView.do")
	public String taskView(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
						@PathVariable(value="lecCode", required=true) String lecCode,@RequestAttribute("subName") String subName,
						@PathVariable(value="taskNo", required=true) int taskNo,
						Model model) {
		TaskVO task = new TaskVO();
		try {
			task = service.retrieveTask(TaskVO.builder().taskNo(taskNo).lecCode(lecCode).build());
			model.addAttribute("task",task);
			model.addAttribute("pageTitle","과제");
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());
			return "redirect:/myclass/{lecCode}/taskList.do";
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "과제"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/"+taskNo+"taskView.do");
		model.addAttribute("pageLink","/myclass/"+lecCode+"/"+task.getTaskNo()+"/taskView.do");
		return "myClassRoom/task/myTaskView";
	}
	
	@ModelAttribute("taskVO")
	public TaskVO taskVO() {
		return new TaskVO();
	}
	
	/** 과제 등록 폼 **/
	@RequestMapping("/myclass/{lecCode}/taskInsertForm.do")
	public String taskInsertForm(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
								@PathVariable(value="lecCode", required=true) String lecCode, @RequestAttribute("professorId") String professorId,
								@RequestAttribute("subName") String subName ,Model model) {
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
		model.addAttribute("pageTitle",pageTitleWithSubName(subName, "과제"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/taskInsertForm.do");
		return "myClassRoom/task/myTaskForm";
	}
	
	/** 과제 등록 **/
	@PostMapping("/myclass/{lecCode}/taskInsert.do")
	public String taskInsert(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
							@PathVariable(value="lecCode", required=true) String lecCode, @RequestAttribute("subName") String subName,
							@Validated(InsertGroup.class) TaskVO task
							,BindingResult errors
							,Model model
							, RedirectAttributes redirectAttributes) {
		if(authMember!=null) {
			task.setMemId(authMember.getMemId());
		}
		if(errors.hasErrors()) {
			model.addAttribute("message", notyErrorMessage());
			return "myClassRoom/task/myTaskForm";
		}
		try {
			service.createTask(task);
			model.addAttribute("message", notyInsertSuccessMessage());
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());
			return "myClassRoom/task/myTaskForm";
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "과제"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/taskInsert.do");
		return "redirect:/myclass/{lecCode}/"+task.getTaskNo()+"/taskView.do";
	}
	
	/** 과제 수정  폼**/
	@GetMapping("/myclass/{lecCode}/taskUpdateForm.do")
	public String taskUpdateForm(@AuthenticationPrincipal(expression="realMember") MemberVO authMember, @RequestAttribute("professorId") String professorId,
			@RequestAttribute("subName") String subName,@PathVariable(value="lecCode", required=true) String lecCode, TaskVO taskVO, Model model) {
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
		addCommandAttribute(model);
		taskVO = service.retrieveTask(taskVO);
		model.addAttribute("taskVO", taskVO);
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "과제"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/taskUpdateForm.do");
		return "myClassRoom/task/myTaskForm";
	}
	
	/** 과제 수정 **/
	@PostMapping("/myclass/{lecCode}/taskUpdate.do")
	public String taskUpdate(@AuthenticationPrincipal(expression="realMember") MemberVO authMember, 
			@RequestAttribute("subName") String subName, @PathVariable(value="lecCode", required=true) String lecCode, 
			@Validated(UpdateGroup.class) TaskVO taskVO, BindingResult errors, Model model, RedirectAttributes redirectAttributes) {
		addCommandAttribute(model);
		if(lecCode!=null) {
			taskVO.setLecCode(lecCode);
		}
		if(errors.hasErrors()) {
			model.addAttribute("message", notyErrorMessage());
			model.addAttribute("task", taskVO);
			return "myClassRoom/task/myTaskForm";
		}
		try {
			service.modifiyTask(taskVO);
			model.addAttribute("message", notyUpdateSuccessMessage());
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());	
			model.addAttribute("task", taskVO);
			return "myClassRoom/task/myTaskForm";
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "과제"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/taskUpdate.do");
		return "redirect:/myclass/"+lecCode+"/"+taskVO.getTaskNo()+"/taskView.do";
	}
	
	/** 과제 삭제 **/
	@PostMapping("/myclass/{lecCode}/taskDel.do")
	public String disDelete(@PathVariable(value="lecCode", required=true) String lecCode, @RequestAttribute("professorId") String professorId
			, @AuthenticationPrincipal(expression="realMember") MemberVO authMember, @RequestAttribute("subName") String subName
			, TaskVO taskVO
			, Model model,RedirectAttributes redirectAttributes) {
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
		try {
			service.removeTask(taskVO);
			model.addAttribute("message", notyDeleteSuccessMessage());
			model.addAttribute("pageTitle","과제");
		} catch (Exception e) {
			model.addAttribute("task", taskVO);
			model.addAttribute("message", notyErrorMessage());
			return "myclass/"+lecCode+"/"+taskVO.getTaskNo()+"/taskView.do";
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "과제"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/taskDel.do");
		return "redirect:/myclass/"+lecCode+"/taskList.do";
	}
	
}
