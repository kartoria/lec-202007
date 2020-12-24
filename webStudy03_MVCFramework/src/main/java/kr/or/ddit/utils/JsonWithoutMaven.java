package kr.or.ddit.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.or.ddit.enumpkg.MimeType;

public class JsonWithoutMaven {
	//객체 생성 없이도 접근할 수 있도록 static
	public static void toJsonResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Enumeration<String> names = req.getAttributeNames();
		String ptrn = " \"%s\" : \"%s\" ";
		StringBuffer json = new StringBuffer();
		json.append("{");
		
		
		while (names.hasMoreElements()) {
			//key
			String name = (String) names.nextElement();
			//value
			Object value = req.getAttribute(name);
			
//													//문자열로 바꾸기
			json.append(String.format(ptrn, name, value.toString()));
			json.append(",");
		}
		//마지막 들어간 콤마 제거
		int lastIdx = json.lastIndexOf(",");
		json.deleteCharAt(lastIdx);
		json.append("}");
		//마샬링 끝=====================================
		

		//컨텐트 타입 마임 체크
		String contentType = resp.getContentType();
		if(contentType == null) {
			resp.setContentType(MimeType.JSON.toString());
		}
		//직렬화
		try(
			//예외는 doPost가 가져가고 다시 tomcat이 가져간다.
			PrintWriter out = resp.getWriter();	
		){
//			out.println(json);
		}
	}
}
