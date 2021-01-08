package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jmx.snmp.Enumerated;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.enumpkg.OperateType;

@WebServlet("/03/calculate.do")
public class CalculateServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MimeType mime =  MimeType.parseAcceptToMimeType(req);
		resp.setContentType(mime.toString());
		
		String left = req.getParameter("leftOp");
		String right = req.getParameter("rightOp");
		String opParam = req.getParameter("operator");
		int status = HttpServletResponse.SC_OK;
		String message = null;
//		
//		if(left==null || !left.matches("[0-9]+")) {
//			status = HttpServletResponse.SC_BAD_REQUEST;
//			message = "좌측 피연산자 누락";
//		}
//		if(right==null || !right.matches("[0-9]+")) {
//			status = HttpServletResponse.SC_BAD_REQUEST;
//			message = "우측 피연산자 누락";
//		}
		OperateType operator = null;
		if(opParam==null || opParam.isEmpty()) {
			status = HttpServletResponse.SC_BAD_REQUEST;
			message = "연산자 누락";
		}else {
			try {
				operator = OperateType.valueOf(opParam);
			}catch (IllegalArgumentException e) {
				status = HttpServletResponse.SC_BAD_REQUEST;
			}
		}
		
		try {
			int leftOp = Integer.parseInt(left);
			int rightOp = Integer.parseInt(right);
			int result = operator.operator(leftOp, rightOp);
			
			String exprPtrn = "%d %s %d = %d";
			String responseData = String.format(exprPtrn, leftOp, operator.getSign(), rightOp, result);
			Date now = new Date();
			req.setAttribute("data", responseData);
			req.setAttribute("now", now);
			
			if(MimeType.HTML==mime) {
				String view = "/WEB-INF/views/calculate.jsp";
				req.getRequestDispatcher(view).forward(req, resp);
			}else if(MimeType.JSON==mime) {
			}
		}catch (NullPointerException | NumberFormatException e) {
			status = HttpServletResponse.SC_BAD_REQUEST;
			message = "잘못된 피연산자";
		}
		if(status!=HttpServletResponse.SC_OK){
			resp.sendError(status, message);
			return;
		}
	}

	
}










