<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String mem_id = (String) session.getAttribute("authMember"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인에 성공하셨습니다</title>
<script type="text/javascript">
	function clickHandler(event){
		event.preventDefault();
		let href = event.target.dataset["href"];
		let logoutForm = document.logoutForm;
		logoutForm.action = href;	
		logoutForm.submit();
		return false;
	}
</script>
</head>
<body>
<%if(StringUtils.isNotBlank(mem_id)){ %>
	<form name="logoutForm" method="post"></form>
	<h1>로그인에 성공하셨습니다</h4>
	<h4>아이디 : <%=mem_id %></h4>
	<a href=# onclick="clickHandler(event);" data-href="<%=request.getContextPath()%>/login/logout.do">로그아웃</a>
<%}else{ %>
	<h4><a href="<%=request.getContextPath() %>/login/loginForm.jsp">로그인 하러가기</a></h4>
<%} %>
</body>
</html>