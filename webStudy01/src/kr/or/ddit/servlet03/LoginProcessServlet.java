package kr.or.ddit.servlet03;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;


@WebServlet("/login/loginProcess.do")
public class LoginProcessServlet extends HttpServlet{
	private static final long serialVersionUID = -8073879859863998844L;

	private boolean authenticate(String mem_id, String mem_pass) {
		return mem_id.equals(mem_pass);
	}
	private boolean validate(String mem_id, String mem_pass) {
		
		return false;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_id = (String) req.getParameter("mem_id");
		String mem_pass = (String) req.getParameter("mem_pass");
		
		if(validate(mem_id, mem_pass)) {
			
		}
		HttpSession session = req.getSession(true);
		if(authenticate(mem_id, mem_pass)) {
			session.setAttribute("authMember", mem_id);
			resp.sendRedirect(req.getContextPath()+"/login/loginSuccess.jsp");
		}else {
			String encoded = URLEncoder.encode("로그인실패", "UTF-8");
			session.setAttribute("mem_id", mem_id);
			resp.sendRedirect(req.getContextPath()+"/login/loginForm.jsp?msg="+encoded);
		}
	}

	
}
