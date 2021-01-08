<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/scopeDesc.jsp</title>
</head>
<body>
<h4>SCOPE(영역)</h4>
<pre>
	: 속성(attribute) 데이터를 공유하기 위해 정의된 4가지 저장 영역
	setAttribute(name, value),  getAttribute(name), removeAttribute(name)
	1. page Scope : pageContext 가 관리하는 맵, 한페이지내에서만 공유되는 영역.
	2. request Scope : request 가 관리하는 Map&lt;String,Object&gt;, reqeust와 생명주기 동일.
	3. session Scope : session 이 관리하는 Map&lt;String,Object&gt;, session 과 생명주기 동일.
	4. application Scope : servletContext가 관리하는 Map&lt;String,Object&gt; servletContext와 생명주기 동일.
</pre>
</body>
</html>