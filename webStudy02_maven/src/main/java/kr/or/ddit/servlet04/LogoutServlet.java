package kr.or.ddit.servlet04;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout.do")
public class LogoutServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		super.init();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.invalidate();//세션값 다지우고 세션 만료까지 시킴
		
		String encoded = URLEncoder.encode("로그아웃성공","UTF-8");
		resp.sendRedirect(req.getContextPath()+"?message="+encoded);
	}
}
