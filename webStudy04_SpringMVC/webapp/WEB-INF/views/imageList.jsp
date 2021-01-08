<%@ page import="java.util.Arrays" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<html>
	<style type="text/css">
		body{
			background-color: aqua;
		}
	</style>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script type="text/javascript">
// 		function changeCallback(event){
// 			console.log(event);
// 			console.log(event.target.value);
// 			location.href="/webStudy01/imageView.do?image="+event.target.value;
// 		}
		$(function(){
			$("select").on("change", function(event){
				let imageNames = $(this).val();
				let imgTags = [];
				$(imageNames).each(function(index, imageName){
					imgTags.push(
						$("<img>").attr("src", "${pageContext.request.contextPath }/imageView.do?image="+imageName)		
					);
				});
//	 			<img src="../imageView.do?image="/>
				$("#imageArea").html( imgTags );
				
				if(imgTags.length>0){
					let data = JSON.stringify(imageNames);
					console.log(data);
					$.ajax({
						url:"${pageContext.request.contextPath }/imageView.do",
						data:data,
						method:"post",
						contentType:"application/json;charset=UTF-8",
						error:function(xhr){
							console.log(xhr);
						}
					});
				}
			});
		});
	</script>
	<body>
		<h4>${title }</h4>
		<h4>${today }</h4>
		<select size="10" multiple>
			<c:if test="${empty array }">
				<c:set var="array" value="<%=new String[0] %>" />
			</c:if>
			<c:forEach items="${imageFiles }" var="image">
				<c:set var="selected" value='${Arrays.binarySearch(array, image) >=0 ?"selected":"" }'/>
				<option ${selected }>${image }</option>
			</c:forEach>
		</select>
		<div id="imageArea">
			<c:forEach items="${array }" var="image">
				<c:url value="/imageView.do" var="imageViewURL">
					<c:param name="image" value="${image }" />
				</c:url>
				<img src="${imageViewURL }" />
			</c:forEach>
		</div>
	</body>
</html>









    