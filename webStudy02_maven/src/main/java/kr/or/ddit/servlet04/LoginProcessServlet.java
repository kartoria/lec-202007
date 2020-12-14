package kr.or.ddit.servlet04;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.servlet04.dao.LoginTestDao;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginProcess.do")
public class LoginProcessServlet extends HttpServlet {
	IAuthenticateService service = new AuthenticateServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");

		HttpSession session = req.getSession();
		Object result = service.authenticate(MemberVO.builder().mem_id(mem_id).mem_pass(mem_pass).build());
		if (result instanceof MemberVO) {
			MemberVO authMember = (MemberVO) result;
			session.setAttribute("authMember", authMember);
			resp.sendRedirect(req.getContextPath());
		} else {
			String message = null;
			if (ServiceResult.NOTEXIST.equals(result)) {
				message = "아이디 오류, 그런사람 없음";
			} else {
				message = "비번 오류, 다시작성 요망";
				session.setAttribute("mem_id", mem_id);
			}
			session.setAttribute("message", message);
			resp.sendRedirect(req.getContextPath() + "/login/loginForm.do");
		}
	}
}