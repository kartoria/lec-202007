<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	2:00 -> 1:59 -> 1:58
	
	<%=session.getId() %>(<%=new Date(session.getCreationTime()) %>)
				<%=new Date(session.getLastAccessedTime()) %>
				<%=session.getMaxInactiveInterval() %>s
				<%session.setMaxInactiveInterval(3*60); %>
				<%=session.getMaxInactiveInterval() %>s
</body>
</html>