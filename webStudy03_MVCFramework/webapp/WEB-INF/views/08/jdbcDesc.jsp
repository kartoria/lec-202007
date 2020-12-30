<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.ddit.vo.DatabasePropertyVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/jdbcDesc.jsp</title>
<jsp:include page="/html/include/preScript.jsp"></jsp:include>
</head>
<body>
	<h4>JDBC(Java DataBase Connectivity)</h4>
	<jsp:useBean id="paramVO" class="kr.or.ddit.vo.DatabasePropertyVO"/>
	<jsp:setProperty property="*" name="paramVO"/>
	<form id="searchForm">
		<input type="text" name="property_name" value='<jsp:getProperty property="property_name" name="paramVO"/>'/>
		<input type="text" name="property_value" value='<jsp:getProperty property="property_value" name="paramVO"/>'/>
		<input type="text" name="description" value='<jsp:getProperty property="description" name="paramVO"/>'/>
		<input type="submit" name="검색"/>
	</form>
	<pre>
	1. driver를 빌드패스에 추가
	2. driver loading
	3. Connection 생성
	<%
		List<DatabasePropertyVO> list = (List) request.getAttribute("dbProps");
	%>
	
	7. 자원 해제
	
	</pre>
	<table>
		<tbody id="listBody">	
<%
		for(Object vo : list){
	%>
		<tr>
			<td><%=((DatabasePropertyVO)vo).getProperty_name()%></td>	
			<td><%=((DatabasePropertyVO)vo).getProperty_value()%></td>	
			<td><%=((DatabasePropertyVO)vo).getDescription()%></td>	
		</tr>
<%	} %>
		</tbody>
	</table>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/asyncForm.js"></script>
<script type="text/javascript">
	let listBody = $("#listBody");
	$("#searchForm").asyncForm({
		success:function(resp){
			listBody.empty();
			let trTags = [];
			if(!resp.dbProps){
				trTags.push(
					$("<tr>").html($("<td>").text("검색결과 없음").attr("colspan", "3"));		
				);			
			}else{
				let trTags = [];
				$.each(resp.dbProps, function(index, propVO){
					trTags.push($("<tr>").append(
						$("<td>").text(propVO.property_name),
						$("<td>").text(propVO.property_value),
						$("<td>").text(propVO.description)
					));
				});
			}
			listBody.html(trTags);
		}
	});
</script>

</body>
</html>