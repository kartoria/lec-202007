<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	.flag{
		width: 100px;
		height: 100px;
	}
</style>
<input type="image" src="${pageContext.request.contextPath }/images/korean.jpg" 
	class="flag" id="KO"
/>
<input type="image" src="${pageContext.request.contextPath }/images/us.png" 
	class="flag" id="EN"
/>
<div id="resultArea">

</div>
<pre>
<%-- <%=Locale.ENGLISH.toLanguageTag() %> --%>
<%-- accept-language : <%=acceptLanguage %> --%>
<%-- <%=message %> --%>
</pre>
<script type="text/javascript">
	let resultArea = $("#resultArea");
	$(".flag").on("click", function(event){
		let language = $(this).prop("id");
<%-- 		location.href="${pageContext.request.contextPath }/02/getMessage.jsp?lang="+language; --%>
		$.ajax({
			url:"${pageContext.request.contextPath }/02/getMessage.do",
			data:{
				lang:language
			},
			method:"get",
			dataType:"xml", // Accept : applicaiton/json | Content-Type
			success:function(resp){
// 				$("#resultArea").html(resp.message);
				let xml = $(resp);
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














