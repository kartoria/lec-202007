<%@page import="java.util.Date"%>
<%@ page import="kr.or.ddit.enumpkg.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/userAgent.jsp</title>
</head>
<body>
<h4>User Agent</h4>
<!-- 클라이언트가 전송한 요청에서 시스템에 대한 정보를 추출. -->
<!-- 당신의 브라우저는 "크롬"입니다 형태의 메시지 전송(enum 문법 활용) -->
<%
	String agent = request.getHeader("user-agent");
	String name = Browser.getBrowserName(agent);
	Date now = null;
%>
당신의 브라우저는 <%=name %>입니다.
</body>
</html>


















