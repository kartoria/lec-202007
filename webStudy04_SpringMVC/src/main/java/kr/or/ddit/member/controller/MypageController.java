package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
@RequestMapping("/mypage.do")
public class MypageController{
	@Inject
	private IAuthenticateService service;
	
	@GetMapping
	public String doGet(){
		return "member/passwordForm";
	}
	
	@PostMapping
	public String doPost(
			@RequestParam("mem_pass") String mem_pass
			, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session == null || session.isNew()) {
			resp.sendError(400);
			return null;
		}
		
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String mem_id = authMember.getMem_id();
		
		Object result = service.authenticate(MemberVO.builder()
											.mem_id(mem_id)
											.mem_pass(mem_pass)
											.build());
		String goPage = null;
		if(result instanceof MemberVO) {
			req.setAttribute("member", result);
			goPage = "member/mypage";
		}else {
			session.setAttribute("message", NotyMessageVO.builder("비밀번호 오류, 다시 해보셈").build());
			goPage = "redirct:/mypage.do";
		}
		return goPage;
	}
	
}
















