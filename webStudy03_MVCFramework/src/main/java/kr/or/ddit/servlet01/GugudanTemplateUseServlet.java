package kr.or.ddit.servlet01;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/01/gugudan.tmpl")
public class GugudanTemplateUseServlet extends UseTemplateServlet{
	@Override
	public String getMimeType() {
		return "text/html;charset=UTF-8";
	}

	@Override
	public void getDataMap(Map<String, Object> dataMap, HttpServletRequest request) {
		String minDanStr = request.getParameter("minDan");
		String maxDanStr = request.getParameter("maxDan");
		
		int minDan = 2;
		int maxDan = 9;
		if(minDanStr != null && minDanStr.matches("[2-9]")) {
			minDan = Integer.parseInt(minDanStr);
			maxDan = Integer.parseInt(maxDanStr);
		}
		StringBuffer gugudanTrTags = new StringBuffer();
		String ptrn = "<tr><td>%d*%d=%d</td>";
		for(int dan=minDan; dan<=maxDan; dan++) {
			for(int mul=1; mul<=9; mul++) {
				gugudanTrTags.append(String.format(ptrn, dan, mul, (dan*mul)));
			}
		}
		gugudanTrTags.append("</tr>");
		
		dataMap.put("title", "구구단");
		dataMap.put("gugudanTrTags", gugudanTrTags);
		
	}

}
