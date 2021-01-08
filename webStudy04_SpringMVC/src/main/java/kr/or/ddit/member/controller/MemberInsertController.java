package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
@RequestMapping("/member/registMember.do")
public class MemberInsertController{
	@Inject
	private IMemberService service;
	
	@GetMapping
	public String doGet(){
		return "member/memberForm";
	}
	
	@PostMapping
	public String doPost(
			@Validated(InsertGroup.class) @ModelAttribute("member")MemberVO member,
			Errors errors, Model model) {
//		Map<String, List<String>> errors = new LinkedHashMap<>();
//		req.setAttribute("errors", errors);
//		CommonValidator<MemberVO> validator = new CommonValidator<>();
//		boolean valid = validator.validate(member, errors, InsertGroup.class);
		
		String goPage = null;
		boolean vaild = !errors.hasErrors();
		if(vaild) {
			ServiceResult result = service.registMember(member);
			switch (result) {
			case PKDUPLICATED:
				goPage = "member/memberForm";
				model.addAttribute("message", NotyMessageVO.builder("아이디 중복").build());
				break;
			case FAILED:
				goPage = "member/memberForm";
				model.addAttribute("message", NotyMessageVO.builder("서버 오류").build());
				break;
			case OK:
				goPage =  "redirect:/login/loginForm.do";
				break;
			}
		}else {
			goPage = "member/memberForm";
		}
		return goPage;
	}
}












