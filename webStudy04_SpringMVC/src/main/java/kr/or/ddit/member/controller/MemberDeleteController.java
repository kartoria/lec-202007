package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
public class MemberDeleteController{
	@Inject
	private IMemberService service;
			
	@RequestMapping(value="/member/removeMember.do", method=RequestMethod.POST)
	public String doPost(@RequestParam("mem_pass") String mem_pass, HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession(false);
		if(session == null || session.isNew()) {
			resp.sendError(400);
			return null;
		}
		
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String mem_id = authMember.getMem_id();
		ServiceResult result = service.removeMember(MemberVO.builder()
															.mem_id(mem_id)
															.mem_pass(mem_pass)
															.build());
		
		String goPage = null;
		switch (result) {
		case INVALIDPASSWORD:
			session.setAttribute("message", NotyMessageVO.builder("비번 오류:").build());
			goPage = "redirect:/mypage.do";
			break;
		case FAILED:
			session.setAttribute("message", NotyMessageVO.builder("서버 오류:").build());
			goPage = "redirect:/mypage.do";
			break;
		default:
			goPage = "forward:/login/logout.do";
			break;
		}
		
		
		return goPage;
		
	}
}





