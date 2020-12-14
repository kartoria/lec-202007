package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.MimeType;

@WebServlet("/02/getMessage.do")
public class GetMessageServlet extends HttpServlet{
	
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getOutputStream().close();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MimeType contentType = MimeType.parseAcceptToMimeType(req);
		resp.setContentType(contentType.toString());
		
		String lang = req.getParameter("lang");
		String acceptLanguage = req.getHeader("accept-language");
		Locale locale = req.getLocale();
		if(lang!=null && !lang.isEmpty()){
			locale = Locale.forLanguageTag(lang.toLowerCase());
		}
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.msg.message", locale);
		
		String message = bundle.getString("bow");
		
		if(MimeType.PLAIN.equals(contentType)) {
			try ( PrintWriter out = resp.getWriter();){
				out.println(message);
			}
		}else if(MimeType.JSON == contentType){ // equals도 가능
			Map<String, Object> data = new HashMap<>();
			data.put("message", message);
			StringBuffer json = new StringBuffer();
			String propPtrn = "\"%s\" : \"%s\" ";
			json.append("{");
			for(Entry<String, Object> entry : data.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				json.append(String.format(propPtrn, key, value));
				json.append(",");
			}
			json.deleteCharAt(json.lastIndexOf(","));
			json.append("}");
			try ( PrintWriter out = resp.getWriter();){
				out.println(json);
			}
		}else if(MimeType.XML == contentType){
			Map<String, Object> data = new HashMap<>();
			data.put("message", message);
			StringBuffer xml = new StringBuffer();
			String propPtrn = "<%1$s>%2$s</%1$s>";
			xml.append("<root>");
			for(Entry<String, Object> entry : data.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				xml.append(String.format(propPtrn, key, value));
			}
			xml.append("</root>");
			try ( PrintWriter out = resp.getWriter();){
				out.println(xml);
			}
		}else {
			req.setAttribute("message", message);
			String view = "/WEB-INF/views/getMessage.jsp";
			req.getRequestDispatcher(view).forward(req, resp);
		}
	}
}
