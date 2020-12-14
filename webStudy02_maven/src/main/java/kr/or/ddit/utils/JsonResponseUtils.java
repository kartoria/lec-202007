package kr.or.ddit.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MimeType;

public class JsonResponseUtils {
	//객체 생성 없이도 접근할 수 있도록 static
	public static void toJsonResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//마샬링
		//1. 데이터 필요
		Enumeration<String> names = req.getAttributeNames();
		//data가 무조건 문자열로만 가지는 않는다.
		//마샬링을 지원하는 라이브러리 (MVN Repository 검색 -> Jackson DataBind, core, annotations 2.11.2)
		//web-inf -> lib에 jar파일을 넣으면 build path를 추가하지 않아도 알아서 추가가 되며
		//이 것이 Libraries 하위에 자동으로 추가 된다.
		String ptrn = " \"%s\" : \"%s\" ";
		
		//마샬링 대상
		Map<String, Object> dataMap = new HashMap<>();
		while (names.hasMoreElements()) {
			//key
			String name = (String) names.nextElement();
			//value
			Object value = req.getAttribute(name);
			
			//request가 가지고 있는 모든 속성이 dataMap으로 들어간다.
			dataMap.put(name, value);
		}
		//maven databind 사용 방법
		ObjectMapper mapper = new ObjectMapper();
		
		//마샬링
//		mapper.writer 계열
								//마샬링의 대상(req)
								//req에서 어떤 것?
								//dataMap
//		String json = mapper.writeValueAsString(dataMap);
		
		//언마샬링
//		mapper.reader() 계열

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
			mapper.writeValue(out, dataMap);
		}
	}
}
