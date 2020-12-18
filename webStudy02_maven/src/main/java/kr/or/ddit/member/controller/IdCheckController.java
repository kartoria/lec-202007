package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.service.IMemService;
import kr.or.ddit.member.service.MemServiceImpl;

@WebServlet("/member/idCheck.do")
public class IdCheckController extends HttpServlet {
	private IMemService memService = MemServiceImpl.getInstance();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String inputId = (String) req.getParameter("inputId");
		if(StringUtils.isBlank(inputId)) {
			resp.sendError(400);
			return;
		}
		String check = "";
		resp.setContentType("text/plain");
		try {
			memService.retrieveMember(inputId);
			check = "false";
		} catch (UserNotFoundException e) {
			check = "true";
		}
		try(PrintWriter out = resp.getWriter()){
			out.print(check);
		}
	}
}
