package kr.or.ddit.servlet01;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/01/gugudan1.tmpl")
public class GugudanTemplateUseServlet2 extends UseTemplateServlet{
	@Override
	public String getMimeType() {
		return "text/html;charset=UTF-8";
	}

	@Override
	public void getDataMap(Map<String, Object> dataMap, HttpServletRequest request) {
		String minStr = request.getParameter("minDan") != null ? request.getParameter("minDan") : "0";
		String maxStr = request.getParameter("maxDan") != null ? request.getParameter("maxDan") : "0";
		System.out.println(minStr);
		System.out.println(maxStr);
		int min = 0;
		int max = 0;
		if(!(minStr == "" && maxStr == "")) {
			min = Integer.parseInt(minStr);
			max = Integer.parseInt(maxStr);
		}
		String tr = "";
		if(min == 0 && max == 0) {
			tr = "<tr><td>몇 단부터 몇 단까지 출력할지 입력해주세요</td></tr>";
		}else if(min < 0 || max < 0 || min > max) {
			tr = "<tr><td>제대로 입력해주세요</td></tr>";
		}else {
			for(int i = min; i<=max; i++) {
				tr += "<tr><td colspan='5'>"+ i +"단</td></tr>";
				for(int j=1; j<10; j++) {
					tr += "<tr><td>"+ i +"</td>";
					tr += "<td>*</td>";
					tr += "<td>"+ j +"</td>";
					tr += "<td>=</td>";
					tr += "<td>"+ i*j + "</td>";
					tr += "</tr>";
				}
			}
		}
			
		dataMap.put("gugudanTrTags", tr);
	}

}
