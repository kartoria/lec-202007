package kr.or.ddit.myclassroom.qna.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.commons.service.BoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.task.vo.TaskSubmitVO;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.CustomPaginationInfo;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;

/**
 * 질문 게시판
 */
@Controller
public class MyQnaController extends BaseController{

   @Inject
   private BoardService service;

   private void addCommandAttribute(Model model) {
      model.addAttribute("command", "MODIFY");
   }
   
   /** 조회 **/
   @RequestMapping(value="/myclass/{lecCode}/qnaList.do")
   public String qnaList(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
         @PathVariable(value ="lecCode", required=true) String lecCode, @RequestAttribute("subName") String subName,
         @RequestParam(value="page", required=false, defaultValue="1") int currentPage,
         @ModelAttribute("searchVO") SearchVO searchVO, Model model, BoardVO boardVO) {
      
      PagingVO<BoardVO> pagingVO = new PagingVO<>(10, 5);
      pagingVO.setSearchVO(searchVO);
      pagingVO.setLecCode(lecCode);
      pagingVO.setBoGroupCode("QNA");
      
      int totalRecord = service.retrieveBoardCount(pagingVO);
      pagingVO.setTotalRecord(totalRecord);
      pagingVO.setCurrentPage(currentPage);
      
	  List<BoardVO> boardList = service.retrieveBoardList(pagingVO);
	  pagingVO.setDataList(boardList);
	  if(boardList.size() > 0) {
		  List<BoardVO> repList = service.retrieveRepList(boardList);
		  model.addAttribute("repList",repList);
      }
	  model.addAttribute("pageTitle", pageTitleWithSubName(subName, "질문 게시판"));
	  model.addAttribute("pageLink","/myclass/"+lecCode+"/qnaList.do");
      model.addAttribute("paginationInfo", new CustomPaginationInfo<BoardVO>(pagingVO));
      return "myClassRoom/qna/myQnaList";
   }
   
   /** 답변 등록 **/
   @PostMapping(value="/myclass/{lecCode}/qnaInsert.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
   @ResponseBody
   public Map<String, Object> qnaInsert(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
            @PathVariable(value="lecCode", required=true) String lecCode, @RequestAttribute("subName") String subName,
            @Validated(InsertGroup.class)@ModelAttribute BoardVO board, Model model, BindingResult errors) {
      try {
         service.createBoard(board);
         return Collections.singletonMap("result", "OK");
      } catch (Exception e) {
         model.addAttribute("message", notyErrorMessage());
         return Collections.singletonMap("message", notyErrorMessage());
      }
   }
   
   /** 게시글 상세보기 **/
   @RequestMapping("/myclass/{lecCode}/{boNo}/qnaView.do")
   public String qnaView(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
         @PathVariable(value="boNo", required=true) int boNo, RedirectAttributes redirectAttributes, @RequestAttribute("subName") String subName,
         @PathVariable(value="lecCode", required=true) String lecCode,
         Model model) {
      
      BoardVO board = service.retrieveBoard(BoardVO.builder().boNo(boNo).build());
      // 삭제된 게시글인 경우
      if("Y".equals(board.getBoDelete())) {
         redirectAttributes.addFlashAttribute("message", notyDeleteSuccessMessage());
         return "redirect:myClassRoom/qna/myQnaList";
      } else {
         model.addAttribute("board",board);
 		 model.addAttribute("pageTitle", pageTitleWithSubName(subName, "질문 게시판"));
         model.addAttribute("pageLink","/myclass/"+lecCode+"/"+boNo+"/qnaView.do");
         return "myClassRoom/qna/myQnaView";
      }
   }
   
   /** 답변 수정 **/
   @RequestMapping("/myclass/{lecCode}/qnaUpdate.do")
   public String qnaUpdate(@AuthenticationPrincipal(expression="realMember") MemberVO authMember, @RequestAttribute("subName") String subName,
		   @PathVariable(value="lecCode", required=true) String lecCode,
		   @Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board, BindingResult errors, Model model) {
	   addCommandAttribute(model);
      if(lecCode!=null) {
         board.setLecCode(lecCode);
      }
      if(errors.hasErrors()) {
         model.addAttribute("message", notyUpdateSuccessMessage());
         model.addAttribute("board", board);
         return "myClassRoom/qna/myQnaForm";
      }
      try {
         service.modifiyBoard(board);
      } catch (Exception e) {
         model.addAttribute("message", notyErrorMessage());   
         model.addAttribute("task", board);
         return "myClassRoom/task/myTaskForm";
      }
      model.addAttribute("pageTitle", pageTitleWithSubName(subName, "질문 게시판"));
      model.addAttribute("pageLink","/myclass/"+lecCode+"/qnaUpdate.do");
      return "redirect:/myclass/"+lecCode+"/"+board.getBoNo()+"/qnaView.do";
   }
   
   /** 답변 조회 **/
   @PostMapping(value="/myclass/{lecCode}/qnaRepView.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
   public String qnaRepView(@AuthenticationPrincipal(expression="realMember") MemberVO authMember, 
         @RequestParam(value ="lecCode", required=true) String lecCode, @RequestParam(value="boParent", required=true) int boParent, Model model) {
      BoardVO repBoard = new BoardVO();
      try {
         repBoard = service.retrieveRep(BoardVO.builder().boParent(boParent).lecCode(lecCode).build());
      } catch (Exception e) {
         LOGGER.error(this.getClass().getName() + " " + e.getMessage());
      }
      model.addAttribute("pageLink","/myclass/"+lecCode+"/qnaRepView.do");      
      model.addAttribute("repBoard", repBoard);
      return "jsonView";
   }
   
   	/** 답변 삭제 **/
	@PostMapping(value="/myclass/{lecCode}/qnaRepDel.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> qnaRepDel(BoardVO board, @AuthenticationPrincipal(expression="realMember") MemberVO authMember){
		try {
			service.removeBoard(board);
			return Collections.singletonMap("result", "OK");
		} catch (Exception e) {
			return Collections.singletonMap("message", notyDeleteSuccessMessage());
		}
	}
	
	@ModelAttribute("board")
	public BoardVO board() {
		return new BoardVO();
	}
	
	/** 등록 form **/
	@RequestMapping("/myclass/{lecCode}/qnaBoInsertForm.do")
	public String qnaBoInsertForm(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,@RequestAttribute("subName") String subName,
								@PathVariable(value="lecCode", required=true) String lecCode, 
								@RequestAttribute("professorId") String professorId, Model model) {
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "질문 게시판"));
	    model.addAttribute("pageLink","/myclass/"+lecCode+"/qnaBoInsertForm.do");    
		return "myClassRoom/qna/myQnaForm";
	}
	
	/** 등록**/
	@PostMapping("/myclass/{lecCode}/qnaBoInsert.do")
	public String qnaBoInsert(@Validated(InsertGroup.class) @ModelAttribute("board") BoardVO board, BindingResult errors, Model model,
							@PathVariable(value="lecCode", required=true) String lecCode,@AuthenticationPrincipal(expression="realMember") MemberVO authMember, 
							RedirectAttributes redirectAttributes,@RequestAttribute("subName") String subName) {
		
		if(errors.hasErrors()) {
			model.addAttribute("message", notyErrorMessage());
			return "myClassRoom/qna/myQnaForm";
		} 
		try {
			service.createBoard(board);
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());
			return "myClassRoom/qna/myQnaForm";
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "질문 게시판"));
	    model.addAttribute("pageLink","/myclass/"+lecCode+"/qnaBoInsert.do");  
		redirectAttributes.addFlashAttribute("message", notyInsertSuccessMessage());
		return "redirect:/myclass/"+lecCode+"/"+board.getBoNo()+"/qnaView.do";
	}
	
	/** 수정 폼**/
	@GetMapping("/myclass/{lecCode}/qnaBoUpdateForm.do")
	public String qnaBoUpdateForm(@AuthenticationPrincipal(expression="realMember") MemberVO authMember, @RequestAttribute("subName") String subName,
								@PathVariable(value="lecCode", required=true) String lecCode, Model model, 
								@RequestAttribute("professorId") String professorId,BoardVO board) {
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
		addCommandAttribute(model);
		board = service.retrieveBoard(board);
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "질문 게시판"));
	    model.addAttribute("pageLink","/myclass/"+lecCode+"/qnaBoUpdateForm.do");  
		model.addAttribute("board", board);
		return "myClassRoom/qna/myQnaForm";
	}
	
	/** 수정 **/
	@PostMapping("/myclass/{lecCode}/qnaBoUpdate.do")
	public String qnaBoUpdate(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,@PathVariable(value="lecCode", required=true) String lecCode,
							@Validated(UpdateGroup.class) BoardVO board, BindingResult errors, Model model, 
							@RequestAttribute("subName") String subName, RedirectAttributes redirectAttributes) {
		addCommandAttribute(model);
		if(errors.hasErrors()) {
			model.addAttribute("message", notyErrorMessage());
			return "myClassRoom/qna/myQnaForm";
		}
		try {
			service.modifiyBoard(board);
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());
			return "redirect:/myclass/"+lecCode+"/qnaRepView.do";
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "질문 게시판"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/qnaBoUpdate.do");  
		redirectAttributes.addFlashAttribute("message", notyInsertSuccessMessage());
		return "redirect:/myclass/"+lecCode+"/"+board.getBoNo()+"/qnaView.do";
	}
	
	/** 삭제 **/
	@RequestMapping("/myclass/{lecCode}/qnaBoDelete.do")
	public String qnaBoDelete(@AuthenticationPrincipal(expression="realMember") MemberVO authMember, @PathVariable(value="lecCode", required=true) String lecCode,
					@Validated(DeleteGroup.class) BoardVO board, Errors errors, @RequestAttribute("professorId") String professorId, Model model, 
					@RequestAttribute("subName") String subName,
					RedirectAttributes redirectAttributes) {
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
		if(errors.hasErrors()) {
			model.addAttribute("message", notyErrorMessage());
		}
		try {
			service.removeBoard(board);
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "질문 게시판"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/qnaBoDelete.do"); 
		redirectAttributes.addFlashAttribute("message", notyDeleteSuccessMessage());
		return "redirect:/myclass/"+lecCode+"/qnaList.do";
	}
}   

