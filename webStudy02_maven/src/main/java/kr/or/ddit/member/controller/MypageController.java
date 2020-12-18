package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.utils.SecurityUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage.do")
public class MypageController extends HttpServlet{
	IAuthenticateService authService = AuthenticateServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String logicalView = "member/passwordForm";
		req.getRequestDispatcher("/" + logicalView + ".tiles").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session == null || session.isNew()) {
			resp.sendError(400);
			return;
		}
		
		MemberVO member = (MemberVO) session.getAttribute("authMember");
		String mem_pass = (String) req.getParameter("mem_pass");
		member.setMem_pass(mem_pass);
		
		String logicalView = "";
		Object result = authService.authenticate(member);
		if(result instanceof MemberVO)
			logicalView = "member/mypage";
		else {
			session.setAttribute("msg", "비밀번호가 틀렸습니다.");
			logicalView = "member/passwordForm";
		}
			
			
		req.getRequestDispatcher("/" + logicalView + ".tiles").forward(req, resp);
	}
}
