<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String msg = (String)request.getParameter("msg"); %>
<% String mem_id = (String) session.getAttribute("mem_id"); %>
<% String authMember = (String) session.getAttribute("authMember"); %>
<% session.removeAttribute("mem_id"); %>
<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login페이지</title>
<jsp:include page="jquery.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		$("#loginForm").on("submit", function(){
			let valid = true;
			$(":input[name]").each(function(index, element){
				if($(this).prop("required")){
					if(!$(this).val()){
						
					}					
				}
				let ptrn = $(this).attr("pattern");
				if(ptrn){
					console.log(ptrn);
					let regex = new RegExp(ptrn);
					valid = valid && regex.test(value);
				}
			});
			return valid;
		});
	});
</script>
<script type="text/javascript">
	alert("<%=msg%>")
	if("로그인실패" == "<%=msg%>"){
		alert("아이디와 비밀번호가 다릅니다.");
		history.replaceState({}, '', 'loginForm.jsp');
	}
</script>
</head>
<body>
<%if(StringUtils.isBlank(authMember)){ %>
	<form action="<%=request.getContextPath() %>/login/loginProcess.do" method="post">
		<ul>
			<li>
				<input type="text" name="mem_id" value="<%=mem_id %>" required/></li>
			<li> 
				<input type="text" name="mem_pass" required pattern="^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+).{5, 12}$"/> 
				<input type="submit" value="로그인"/>
			</li>
		</ul>
	</form>
<%}else{ %>
	<h1>이미 로그인중입니다.</h1>
<%} %>
</body>
</html>