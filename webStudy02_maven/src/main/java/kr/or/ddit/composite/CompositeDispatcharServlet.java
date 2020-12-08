package kr.or.ddit.composite;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompositeDispatcharServlet extends HttpServlet{
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String serlvetPath = req.getServletPath();
//		ex) /(\\S+)\\.composite
//		\\S => 공백이 아닌 무조건 한 문자
		Pattern ptrn = Pattern.compile("/(\\S+)\\.composite");
		Matcher matcher =  ptrn.matcher(serlvetPath);
		matcher.find();
		String logicalView = matcher.group(1);
//		matcher.group(0); /\\(\\S+)\\.composite
		System.out.println(logicalView);
		
		Properties properties =  (Properties)req.getServletContext().getAttribute("compositeConfig");
		
		String view = properties.getProperty("template");
		
		String includePathPtrn = properties.getProperty("contents");
		String includePath = includePathPtrn.replaceAll("\\$\\{\\w+\\}", logicalView); // 알파벳이나 숫자
		req.setAttribute("includePath", includePath );
		req.getRequestDispatcher(view).forward(req, resp);
	}
}
