<%@page import="java.util.Arrays"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style type="text/css">
			body{
				background-color: aqua;
			}
		</style>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script type="text/javascript">
// 			function changeCallback(event){
// 				console.log(event.target.value);
// 				location.href="/webStudy01/imageView.do?image="+ event.target.value;
// 			}
			
			$(function(){
				$("select").on("change", function(event){
					let imageName =  $(this).val();
					let imgTags = [];
					$(imageName).each(function(index, imageName){
						imgTags.push(
								$("<img>").attr("src", "<%= request.getContextPath() %>/imageView.do?image=" + imageName)
						);
					});
//	 				<img src="../imageView.do?image="/>
					$("#imageArea").html(imgTags);
					
					if(imgTags.length>0){
						let data = JSON.stringify(imageName);
						$.ajax({
							url:"${pageContext.request.contextPath }/imageView.do",
							data: data,
							method:"post",
							contentType:"application/json;charset=UTF-8",
						});
					}
				});
			});
		</script>
	</head>
	<body>
<% 
	String[] imageFiles = (String[]) request.getAttribute("imageFiles");
	String[] array =  (String[]) request.getAttribute("array");
	if(array == null) array = new String[0];
%>
		<h4>${title}</h4>
		<h4>${today}</h4>
		<select size="10" multiple="multiple">
<%		Arrays.sort(array);
		for(String image: imageFiles){
			String selected = Arrays.binarySearch(array, image) >= 0 ? "selected" : "";%>
			<option <%=selected %>><%=image %></option>
	<% 	} %>
		</select>
		<div id="imageArea">
		
		<% for(String image : array){ %>
			<img src="${pageContext.request.contextPath }/imageView.do?image=<%=image %>"/>
		<% } %>
		</div>
	</body>
</html>
