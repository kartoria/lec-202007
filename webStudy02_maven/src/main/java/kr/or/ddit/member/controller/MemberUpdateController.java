package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/member/modifyMember.do")
public class MemberUpdateController extends HttpServlet{
	private void addCommandAttribute(HttpServletRequest req) {
		req.setAttribute("commend", "MODIFY");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addCommandAttribute(req);
		String url = "member/memberForm";
		req.getRequestDispatcher("/"+url+".tiles").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addCommandAttribute(req);
	}
}
