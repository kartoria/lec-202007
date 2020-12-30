<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/fileUploadForm</title>
</head>
<body>
<form method="post" action='<c:url value="/servlet06/fileUpload.do"/>' enctype="multipart/form-data">
	<ul>
		<li>text : <input type="text" name="textParam" /> </li>
		<li>number : <input type="number" name="numberParam" /> </li>
		<li>file : <input accept="image" type="file" name="uploadFile" /> </li>
		<li><input type="submit" value="전송" /> </li>
	</ul>
</form>
<c:if test="${not empty result }">
	text param : ${result.textParam }
	number param : ${result.numberParam }
	<img src="<c:url value='/prodImages/${result.uploadFile }'/>">
	<c:remove var="result" scope="session"/>
</c:if>
</body>
</html>