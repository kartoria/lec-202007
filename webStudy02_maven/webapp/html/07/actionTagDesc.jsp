<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/actionTagDesc.jsp</title>
</head>
<body>
<h4>Action Tag</h4>
<pre>
	Custom Tag 사용방법 ex)&lt;prefix:taxName attributes &gt;
	액션태그 : jsp 스펙에 따라 기본 제공되는 커스텀 테그	, prefix:jsp
	<%-- <jsp:forward page="/02/userAgent.jsp"></jsp:forward> --%>
			 : 서버 사이드 테그  -> 응답데이터에 커스텀 테그가 포함되지 않음
	<jsp:include page="/html/02/userAgent.jsp"></jsp:include>

</pre>
</body>
</html>