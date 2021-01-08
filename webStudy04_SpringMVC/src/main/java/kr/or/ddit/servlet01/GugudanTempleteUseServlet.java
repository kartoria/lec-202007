package kr.or.ddit.servlet01;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/01/gugudan.tmpl")
public class GugudanTempleteUseServlet extends UseTemplateServlet {

	@Override
	public String getMimeType() {
		return "text/html;charset=UTF-8";
	}

	@Override
	public void getDataMap(Map<String, Object> dataMap, HttpServletRequest req) {
		String minDanStr = req.getParameter("minDan");
		String maxDanStr = req.getParameter("maxDan");
		
		int minDan = 2;
		int maxDan = 9;
		if(minDanStr!=null && minDanStr.matches("[2-9]")) {
			minDan = Integer.parseInt(minDanStr);
		}
		if(maxDanStr!=null && maxDanStr.matches("[2-9]")) {
			maxDan = Integer.parseInt(maxDanStr);
		}
		
		dataMap.put("title", "구구단");
		StringBuffer gugudanTrTags = new StringBuffer();
		dataMap.put("gugudanTrTags", gugudanTrTags);
		String ptrn = "<td>%d*%d=%d</td>";
		for(int dan=minDan; dan<=maxDan; dan++) {
			gugudanTrTags.append("<tr>");
			for(int mul=1; mul<=9; mul++) {
				gugudanTrTags.append(String.format(ptrn, dan, mul, (dan*mul)));				
			}
			gugudanTrTags.append("</tr>");
		}
	}

}









