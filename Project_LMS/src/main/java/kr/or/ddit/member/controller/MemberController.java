package kr.or.ddit.member.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.FirstLoginVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.validate.groups.UpdateGroup;

/**
 * 학번이랑 비밀번호 찾아주는 컨트롤러임
 * @author PC-NEW08
 * @since 2021. 1. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 22.     PC-NEW08      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class MemberController extends BaseController{
	@Inject
	MemberService memberService;
	
	@GetMapping("/login/inquiry.do")
	public String inquiryForm() {
		return "login/inquiry";
	}
	
	@PostMapping("/login/inquiryId.do")
	public String inquiryId(MemberVO member, Model model) {
		// 아이디랑 주민번호로 회원찾아옴
		try{
			List<MemberVO> memberList = memberService.inquiryId(member);
			model.addAttribute("memberList", memberList);
		}catch(UsernameNotFoundException e) {
			model.addAttribute("msg", "해당 정보와 일치하는 사람이 없습니다.");
			model.addAttribute("memName", member.getMemName());
			model.addAttribute("memReg1", member.getMemReg1());
			return "login/inquiry";
		}
		
		return "login/inquiryIdResult"; 
	}
	
	@PostMapping("/login/inquiryPass.do")
	public String inquiryPass(MemberVO member, Model model) {
		return "";
	}
	
	@RequestMapping(value="/first/login.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> firstLogin(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@Validated(UpdateGroup.class) FirstLoginVO firstLoginVO,
			BindingResult errors
			) {
		Map<String, Object> resultMap  = new  HashMap<>();
		firstLoginVO.setMemId(authMember.getMemId());
		
		if(errors.hasErrors()) {
			resultMap = Collections.singletonMap("result", "입력실패");
		}else {
			int result = memberService.firstLoginInsert(firstLoginVO);
			if(result>0) {
				resultMap = Collections.singletonMap("result", "성공");
			}else {
				resultMap = Collections.singletonMap("result", "등록실패");
			}
		}
		 
		return resultMap;
	}
}
