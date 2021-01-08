<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/jdbcDesc.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>
<form id="searchForm">
	<input type="text" name="property_name" value='${paramVO.property_name }'/>
	<input type="text" name="property_value" value="${paramVO.property_value }"/>
	<input type="text" name="description" value="${paramVO.description }"/>
	<input type="submit" value="검색" />
</form>
<pre>
1. driver 를 빌드패스에 추가
2. driver loading
7. 자원 해제
</pre>
<table>
	<tbody id="listBody">
		<c:forEach items="${dbProps }" var="propVO">
				<tr>
					<td>${propVO.property_name }</td>
					<td>${propVO.property_value }</td>
					<td>${propVO.description }</td>
				</tr>
		</c:forEach>
	</tbody>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/asyncForm.js"></script>
<script type="text/javascript">
	let listBody = $("#listBody");
	$("#searchForm").asyncForm({
		success:function(resp){
			listBody.empty();
			let trTags = [];
			if(resp.dbProps.length==0){
				trTags.push(
					$("<tr>").html(
						$("<td>").text("검색 결과 없음.")	
								 .attr("colspan", "3")
					)		
				);
			}else{
				$.each(resp.dbProps, function(index, propVO){
					trTags.push(
						$("<tr>").append(
							$("<td>").text(propVO.property_name)	
							, $("<td>").text(propVO.property_value)	
							, $("<td>").text(propVO.description)	
						)
					);
				});
			}
			listBody.html(trTags);
		}
	});
</script>
</body>
</html>









