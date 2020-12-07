<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/scopeDesc</title>
</head>
<body>
<h4>SCOPE(영역)</h4>
<pre>
	: 속성 데이터를 공유하기 위해 ㅓㅇ의된 4가지 저장d역
	page Scope : pageContext가 관리하는 맵, 한페이지내에서만 공유되는 영역.
	request Scope : request가 관리하는 Map&lt;String,Object&gt;, request의 생명주기 동일
	session Scope : session이 관리하는 Map&lt;String,Object&gt;, session과 생명주기 동일
	application Scope : servletContext가 관리하는 Map&lt;String,Object&gt; servletContext와 생명주기 동일
</pre>
</body>
</html>