<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/sample.jsp?time=<%= (new Date()).getTime()%>"></script>
</head>
<body>
<h4>Cache 제어</h4>
<pre>
Cache-Control(HTTP/1.1), progma(Http.1.0), Rcpirers(만료시간)
</pre>
</body>
</html>