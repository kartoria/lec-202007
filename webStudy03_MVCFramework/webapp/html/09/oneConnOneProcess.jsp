<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/performance.jsp</title>
</head>
<body>
<%
	long start = System.currentTimeMillis();
	try(Connection conn = ConnectionFactory.getConnection();
		Statement stmt = conn.createStatement();		
	){
		String sql = "Select Mem_name From Member Where Mem_id = 'a001'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		out.println(rs.getString(1));
	}
	long end = System.currentTimeMillis();
%>
<%=end-start %>ms
</body>
</html>