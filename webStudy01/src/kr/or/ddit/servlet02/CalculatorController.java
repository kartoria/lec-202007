package kr.or.ddit.servlet02;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.CalculatorSign;

@WebServlet("/Calculator.do")
public class CalculatorController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String numStr1 = (String)req.getParameter("num1");
		String numStr2 = (String)req.getParameter("num2");
		String sign = (String)req.getParameter("sign");
	
		if(numStr1.isEmpty() || numStr1 == null 
				|| numStr2.isEmpty() || numStr2 == null
				|| sign.isEmpty()    || sign    == null ) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		float num1 = Float.parseFloat(numStr1);
		float num2 = Float.parseFloat(numStr2);
		
		// 받아온 파라미터로 연산기호 얻어오기
		String calSign = CalculatorSign.getCal(sign);
		
		// 계산해서 문자열로 받기 
		String result = calculation(num1, num2, calSign);
		
		
		req.setAttribute("result", result);
		RequestDispatcher dis = req.getRequestDispatcher("/02/calResult.jsp");
		dis.forward(req, resp);
	}
	
	
	
	
	private String calculation(float num1, float num2, String sign) {
		float resultNum = 0;
		if("+".equals(sign)) {
			resultNum = add(num1, num2);
		}else if("-".equals(sign)) {
			resultNum = sub(num1, num2);
		}else if("*".equals(sign)) {
			resultNum = mul(num1, num2);
		}else if("/".equals(sign)) {
			resultNum = div(num1, num2);
		}
		return fmt(resultNum);
	}
	
	// 사칙연산
	private float add(float a, float b){
		return a+b;
	}
	private float sub(float a, float b){
		return a-b;
	}
	private float mul(float a, float b){
		return a*b;
	}
	private float div(float a, float b){
		return (float)a/b;
	}
	
	// float 문자열 처리
	private String fmt(float num)
	{
	    if(num == (long) num)
	        return String.format("%d",(long)num);
	    else {
	    	return Float.toString(num);
	    }
	}
}
