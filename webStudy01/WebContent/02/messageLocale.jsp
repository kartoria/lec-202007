<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" 
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<style type="text/css">
	.flag{
		width:100px;
		height:100px;
	}
</style>
</head>
<body>

<input type="image" src="<%=request.getContextPath()%>/images/korean.jpg" class="flag" id="ko"/>
<input type="image" src="<%=request.getContextPath()%>/images/us.png" class="flag" id="en"/>
<div id="resultArea">

</div>
<pre>
<%-- accept-language : <%=acceptLanguage %> --%>
<%-- locale : <%=locale %> --%>
<%-- lang : <%=lang %> --%>
</pre>

<script type="text/javascript">
	$(".flag").on("click", function(event){
		let language = $(this).prop("id");
<%-- 		location.href="<%=request.getContextPath()%>/02/getMessage.jsp?lang=" + language; --%>
		$.ajax({
			url:"<%=request.getContextPath() %>/02/getMessage.jsp",
			data:{
				"lang" : language
			},
			method:"get",
			dataType:"text", // Accept : application/json | Content-Type
			success:function(data){
				$("#resultArea").text(data);
			},
			error:function(xhr){
				console.log(xhr.status);
			}
		});
	});
</script>
</body>
</html>