<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/servletContextDesc.jsp</title>
</head>
<body>
<h4>ServletContext application</h4>
<pre>
	CAC(Context Aware Computing)
	: 하나의 웹어플리케이션과 어플리케이션이 운영되는 컨테이너에 대한 정보를 캡슐화한 객체(singleton).
	1.  dynamic web module version 확인
	    <%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
	2. 파일의 mime type 확인    
	3. logging : <% application.log("의도적으로 기록한 로그 메시지"); %>
	4. 컨텍스트 파라미터 확보 : <%=application.getInitParameter("contentFolder") %>
	5. web resource 확보 : getRealPath, getResource, getResourceAsStream
	<%
		String url ="/images/cat1.jpg";
		String targetUrl = "/06";
		String path = application.getRealPath(url);
		String targetPath = application.getRealPath(targetUrl);
		out.println(path);
		File file = new File(path);
		File targetFile = new File(targetPath, file.getName());
		
		try(
			FileInputStream input = new FileInputStream(file);
			FileOutputStream output = new FileOutputStream(targetFile);
		){		
			IOUtils.copy(input, output);
		}		
		out.println(targetFile.exists());
	%>
</pre>
<img src="<%=request.getContextPath() + targetUrl+"/"+targetFile.getName() %>" />
</body>
</html>















