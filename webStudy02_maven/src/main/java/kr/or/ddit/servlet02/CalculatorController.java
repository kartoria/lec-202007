package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.or.ddit.enumpkg.CalculatorSign;
import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.utils.JsonResponseUtils;

@WebServlet("/Calculator.do")
public class CalculatorController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MimeType contentType = MimeType.parseAcceptToMimeType(req);
		resp.setContentType(contentType.toString());
		String numStr1 = (String)req.getParameter("num1");
		String numStr2 = (String)req.getParameter("num2");
		String sign = (String)req.getParameter("sign");
		
		String ptr = "^[+-]?\\d*(\\.?\\d*)$";
		if(!numStr1.matches(ptr) || numStr1 == null 
				|| !numStr2.matches(ptr) || numStr2 == null
				|| sign.isEmpty()    || sign    == null ) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		float num1 = Float.parseFloat(numStr1);
		float num2 = Float.parseFloat(numStr2);
		CalculatorSign cs = null;
		
		cs = CalculatorSign.valueOf(sign);
		
		float resultNum = cs.calculation(num1, num2);
		String result = fmt(resultNum);
		
		if(MimeType.PLAIN.equals(contentType)) {
			try ( PrintWriter out = resp.getWriter();){
				out.println(result);
			}
		}else if(MimeType.JSON == contentType){ // equals도 가능
			req.setAttribute("result", result);
			JsonResponseUtils.toJsonResponse(req, resp);
		}else {
			req.setAttribute("result", result);
			RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/views/calResult.jsp");
			dis.forward(req, resp);
		}
		
	}
	
	
	// float 문자열 처리
	private String fmt(float num)
	{
	    if(num == (long) num)
	        return String.format("%d",(long)num);
	    else {
	    	return Float.toString((int)(num*100+0.5)/100f);
	    }
	}
}
