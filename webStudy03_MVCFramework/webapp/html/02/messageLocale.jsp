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

<input type="image" src="${pageContext.request.contextPath }/images/korean.jpg" class="flag" id="ko"/>
<input type="image" src="${pageContext.request.contextPath }/images/us.png" class="flag" id="en"/>
<div id="resultArea">

</div>
<pre>
<%-- accept-language : <%=acceptLanguage %> --%>
<%-- locale : <%=locale %> --%>
<%-- lang : <%=lang %> --%>
</pre>

<script type="text/javascript">
	let resultArea = $("resultArea");
	$(".flag").on("click", function(event){
		let language = $(this).prop("id");
<%-- 		location.href="${pageContext.request.contextPath }/02/getMessage.jsp?lang=" + language; --%>
		$.ajax({
			url:"${pageContext.request.contextPath }/02/getMessage.do",
			data:{
				"lang" : language
			},
			method:"get",
			dataType:"xml", // Accept : application/json | Content-Type
			success:function(data){
				let xml = $(data);
				let message = xml.find("message");
				console.log(message.html());
				resultArea.html(message.html());
			},
			error:function(xhr){
				console.log(xhr.status);
			}
		});
	});
</script>
</body>
</html>