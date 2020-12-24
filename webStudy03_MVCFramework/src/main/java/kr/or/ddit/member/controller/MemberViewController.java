package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
public class MemberViewController extends HttpServlet{
	private IAuthenticateService service = AuthenticateServiceImpl.getInstance();
	
	@RequestMapping("/mypage.do")
	protected String doGet(){
		return "member/passwordForm";
	}
	
	@RequestMapping(value="/mypage.do", method=RequestMethod.POST)
	protected String doPost(@RequestParam("mem_pass") String mem_pass, 
			HttpServletRequest req) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		if(session == null || session.isNew()) {
			return null;
		}
		
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String mem_id = authMember.getMem_id();
		
		Object result = service.authenticate(MemberVO.builder()
											.mem_id(mem_id)
											.mem_pass(mem_pass)
											.build());
		if(result instanceof MemberVO) {
			req.setAttribute("member", result);
			return "member/mypage";
		}else {
			session.setAttribute("message", NotyMessageVO.builder("비밀번호 오류, 다시 해보셈").build());
			return "redirct:/mypage.do";
		}
	}
}
















