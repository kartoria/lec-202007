package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MimeType contenType = MimeType.parseAcceptToMimeType(request);		
		response.setContentType(contenType.toString());
		
		String language = request.getParameter("lang");
		String acceptLanguage = request.getHeader("accept-language");	
		Locale locale = request.getLocale();
		if(language!=null && !language.isEmpty()){
			locale = Locale.forLanguageTag(language.toLowerCase());	
		}
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.msg.message", locale);
		String message = bundle.getString("bow");
		
		if(MimeType.PLAIN.equals(contenType)) {
			try(
				PrintWriter out = response.getWriter();	
			){
				out.println(message);
			}
		}else if(MimeType.JSON==contenType) {
			Map<String, Object> data = new HashMap<>();
			data.put("message", message);
			// { "message" : "데이터" }
			StringBuffer json = new StringBuffer();
			String propPtrn = " \"%s\" : \"%s\"  ";
			json.append("{");
			for( Entry<String, Object> entry  : data.entrySet() ) {
				String name = entry.getKey();
				Object value = entry.getValue();
				json.append(String.format(propPtrn, name, value));
				json.append(",");
			}
			int lastIdx = json.lastIndexOf(",");
			json.deleteCharAt(lastIdx);
			json.append("}");
			try(
				PrintWriter out = response.getWriter();	
			){
				out.println(json);
			}
		}else if(MimeType.XML==contenType) {
			Map<String, Object> data = new HashMap<>();
			data.put("message", message);
			StringBuffer xml = new StringBuffer();
			String propPtrn = "<%1$s>%2$s</%1$s>";
			xml.append("<root>");
			for( Entry<String, Object> entry  : data.entrySet() ) {
				String name = entry.getKey();
				Object value = entry.getValue();
				xml.append(String.format(propPtrn, name, value));
			}
			xml.append("</root>");
			try(
				PrintWriter out = response.getWriter();	
			){
				out.println(xml);
			}
		}else {
			request.setAttribute("message", message);
			String view = "/WEB-INF/views/getMessage.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		
	}
}











