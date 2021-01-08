package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

@Controller
@RequestMapping(value="/member/idCheck.do", produces=MediaType.TEXT_PLAIN_VALUE)
public class IdCheckController{
	@Inject
	private IMemberService service;
	
	@GetMapping
	@ResponseBody
	public String doGet(@RequestParam("mem_id") String inputId) {
		boolean canUse = false;
		try {
			service.retrieveMember(inputId);
		}catch (UserNotFoundException e) {
			canUse = true;
		}
		return canUse ? "true" : "false";
	}
	
	@PostMapping
	@ResponseBody // 리턴값을 뷰네임이아니라 응답 데이터로 보냄
	public String doPost(@RequestParam("mem_id") String inputId) {
		boolean canUse = false;
		try {
			service.retrieveMember(inputId);
		}catch (UserNotFoundException e) {
			canUse = true;
		}
		return canUse?ServiceResult.OK.name():ServiceResult.FAILED.name();
	}
}











