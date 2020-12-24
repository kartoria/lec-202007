package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.TemplateUtils;

/**
 * 서블릿의 콜백메소드 종류 
 * lifecycle callback(singleton) : init, destroy
 * request callback : service , doXXX
 * 1) service : request method 판단 -> do[MethodName] 콜백을 호출하여 요청 처리 위임.
 * 2) doXXX : request method 따라 처리될 구체적인  작업을 정의.
 */
public abstract class UseTemplateServlet extends HttpServlet{
	@Override
	protected final void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(getMimeType());
		Map<String, Object> dataMap = new HashMap<>();
		Map<String, String[]> parameterMap =  request.getParameterMap();
//		dataMap.putAll(parameterMap);
		for(Entry<String, String[]> entry : parameterMap.entrySet()) {
			String paramName = entry.getKey();
			String[] paramValues = entry.getValue();
			dataMap.put(paramName, paramValues.length==1?paramValues[0]:paramValues);
		}
		
		getDataMap(dataMap, request);
		String html = TemplateUtils.replaceTemplateToData(request, dataMap);
		try(PrintWriter out = response.getWriter()){
			out.println(html);
		}
	}
	
	public abstract String getMimeType();

	public abstract void getDataMap(Map<String, Object> dataMap, HttpServletRequest request);

}
