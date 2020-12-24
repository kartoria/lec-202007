package kr.or.ddit.servlet04;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionTimer.do")
public class SessionTimerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String timer = req.getParameter("timer");
		HttpSession session = req.getSession(true);
		session.setMaxInactiveInterval(120);
		req.getRequestDispatcher("/others/sessionTimer.tiles").forward(req, resp);
	}
}
