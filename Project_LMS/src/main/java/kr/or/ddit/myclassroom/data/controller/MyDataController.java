package kr.or.ddit.myclassroom.data.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.commons.service.BoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;

/**
 * 사이버 캠퍼스 강의 자료 
 */
@Controller
public class MyDataController extends BaseController{
	@Inject
	BoardService service;
	
	private void addCommandAttribute(Model model) {
		model.addAttribute("command", "MODIFY");
	}
	
	@ModelAttribute("board")
	public BoardVO board() {
		return new BoardVO();
	}
	
	/** 강의 자료 리스트  **/
	@RequestMapping("/myclass/{lecCode}/dataList.do")
	public String doGet(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,@PathVariable(value="lecCode", required=true) String lecCode,
				@ModelAttribute("board") BoardVO board, Model model,@RequestAttribute("subName") String subName) {
		board.setBoGroupCode("REF");
		board.setLecCode(lecCode);
		List<BoardVO> boardList = service.retrieveDataList(board);
		
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "강의 자료"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/dataList.do");		
		model.addAttribute("boardList", boardList);
		return "myClassRoom/data/myDataList";
	}
	
	/** 강의 자료 등록 **/
	@PostMapping("/myclass/{lecCode}/dataInsert.do")
	public String dataInsert(@AuthenticationPrincipal(expression="realMember") MemberVO authMember, 
					@PathVariable(value="lecCode", required=true) String lecCode, 
					@Validated(InsertGroup.class) BoardVO board, BindingResult errors, Model model,@RequestAttribute("subName") String subName) {
		if(errors.hasErrors()) {
			model.addAttribute("message", notyErrorMessage());
			return "redirect:/myclass/"+lecCode+"/dataList.do";
		}
		try {
			service.createBoard(board);
			model.addAttribute("message", notyInsertSuccessMessage());
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());
			return "redirect:/myclass/"+lecCode+"/dataList.do";
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "강의 자료"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/dataInsert.do");			
		return "redirect:/myclass/"+board.getLecCode()+"/dataList.do";
	}
	/** 강의 자료 수정 from **/
	@RequestMapping("/myclass/{lecCode}/dataUpdateForm.do")
	public String dataUpdate(@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
						@PathVariable(value="lecCode", required=true) String lecCode,@RequestAttribute("subName") String subName,
						BoardVO board, Model model) {
		addCommandAttribute(model);
		board = service.retrieveBoard(board);
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "강의 자료"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/dataUpdateForm.do");			
		model.addAttribute("board", board);
		return "myClassRoom/data/others/mydataModal";
	}
	
	/** 강의 자료 수정  **/
	@PostMapping("/myclass/{lecCode}/dataUpdate.do")
	public String dataUpdate(@AuthenticationPrincipal(expression="realMember") MemberVO authMember, 
					@PathVariable(value="lecCode", required=true) String lecCode, @RequestAttribute("subName") String subName,
					@Validated(UpdateGroup.class) BoardVO board, BindingResult errors, Model model) {
		board.setBoGroupCode("REF");
		board.setLecCode(lecCode);
		if(errors.hasErrors()) {
			model.addAttribute("message", notyErrorMessage());
			return "myClassRoom/data/myDataList";
		}
		try {
			service.modifiyBoard(board);
			model.addAttribute("message", notyInsertSuccessMessage());
		} catch (Exception e) {
			model.addAttribute("message", notyErrorMessage());
			return "myClassRoom/data/myDataList";
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "강의 자료"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/dataUpdate.do");			
		return "redirect:/myclass/"+board.getLecCode()+"/dataList.do";
	}
	
	/** 강의 자료 삭제 **/
	@PostMapping("/myclass/{lecCode}/dataDel.do")
	public String dataDel(@AuthenticationPrincipal(expression="realMember") MemberVO authMember, 
							@PathVariable(value="lecCode", required=true) String lecCode,@RequestAttribute("subName") String subName,
							@Validated(DeleteGroup.class) BoardVO board, Model model,
							Errors errors, RedirectAttributes redirectAttributes) {
		board.setBoGroupCode("REF");
		board.setLecCode(lecCode);
		// 글번호, 비밀번호에 대한 검증(삭제 힌트 적용)
		String goPage = "myclass/{lecCode}/dataList.do";
		NotyMessageVO message = null;
		// 각 케이스별 메시지와 이동 위치 필요
		if(!errors.hasErrors()) { // 검증 통과
			ServiceResult result = service.removeBoard(board);
			switch (result) {
			case FAILED:
				message = NotyMessageVO.builder("서버 오류")
										.type(NotyType.error)
										.layout(NotyLayout.topCenter)
										.timeout(3000)
										.build();
				break;
			default: // OK , 삭제 성공시 게시글 목록으로 이동
				goPage = "redirect:/myclass/{lecCode}/dataList.do";
				break;
			}
			
		}else { // 불통
			message = NotyMessageVO.builder("글번호 누락")
									.type(NotyType.error)
									.layout(NotyLayout.topCenter)
									.timeout(3000)
									.build();
		}
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "강의 자료"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/dataDel.do");	
		if(message!=null) redirectAttributes.addFlashAttribute("message", message);
		return goPage;
	}
}
