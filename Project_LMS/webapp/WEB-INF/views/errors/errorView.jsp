<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>에러 처리 페이지</h4>
<h1>ExceptionHandlerControllerAdvice에 의해 처리됨</h1>
${exception }
<pre>
<c:forEach items="${exception.stackTrace }" var="ste">
	${ste }
</c:forEach>
</pre>
</body>
</html>