<%@page import="java.util.Date"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%Date creationTime = new Date(session.getCreationTime()); %>
<% %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session Timer</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	let timeArea = $("#timeArea");
	
	function timer(){
		$.ajax({
			url : "",
			method : "get",
			data : "",
			dataType : "",
			success : function(data){
				$("span").html(data)
			},
			error : function(xhr){
				console.log(xhr);
			}
		});
	}
	
	$(function(){
		setInterval(timer, 1000);
	});
</script>
</head>
<body>
	<h1>남은 세션 시간 : <span></span></h1>

</body>
</html>