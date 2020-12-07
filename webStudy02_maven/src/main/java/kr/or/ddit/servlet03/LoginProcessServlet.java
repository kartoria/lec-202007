package kr.or.ddit.servlet03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/loginProcess.do")
public class LoginProcessServlet extends HttpServlet {

	private boolean authenticate(String mem_id, String mem_pass) {
		
		
		return mem_id.equals(mem_pass);
	}

	private boolean vaildate(String mem_id, String mem_pass) {
		//서버 사이드 정규표현식 아이디 비번 체크 완성
		return true;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		
		if(vaildate(mem_id,mem_pass)) {
			
		}
		
		HttpSession session = req.getSession();

		if (authenticate(mem_id, mem_pass)) {
			session.setAttribute("authMember", mem_id);
			resp.sendRedirect(req.getContextPath()+"/login/loginSuccess.jsp");
			
		} else {
			session.setAttribute("mem_id", mem_id);
		/*	RequestDispatcher dispatcher = req.getRequestDispatcher("/login/loginForm.jsp");
			dispatcher.forward(req, resp);*/
			resp.sendRedirect(req.getContextPath()+"/login/loginForm.jsp");
		}

	}
}