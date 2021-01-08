<%@page import="java.net.URL"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>자원의 식별</h4>
<pre>
	자원의 종류
	1) File System Resource : 파일시스템 절대 경로(drive)를 통해 접근
	2) ClassPath Resource : classpath 이후의 절대경로(qualified name)를 통해 접근
	3) Web Resource : URL 체계를 통해 웹상에서 접근
	
	웹리소스 식별(identify) 방법
	URI : Uniformed Resource Identifier
	URL(Locator) : 자원의 위치를 기준으로 식별
	URN(Name) : 자원의 명칭을 기준으로 식별
	URC(Content) : 자원의 속성을 기준으로 식별
	
	URL 표기 방식
	ex) http://IP:80/depth.../name
	protocol://IP[domainname]:port/depth..../name
	
	절대경로 : 
		protocol://IP[domainname]:port/depth..../name
		clien side : 반드시 context root 부터 경로 기술
			ex) /webStudy01/images/cat1.jpg 
		server side : context root 이후의 경로를 기술.
		 
	상대경로 : 브라우저의 주소를 기준으로 상대 경로가 시작됨.
		ex) ../depth/name
	
</pre>
<%
	String url = "/images/cat1.jpg";
	URL resURL = getServletContext().getResource(url);
	File file = new File(resURL.getFile());
	
%>
<%=file.getAbsolutePath() %>
<%=file.length() %>
<img src="${pageContext.request.contextPath }/images/cat1.jpg" />
<img src="../images/cat1.jpg" />
</body>
</html>
















