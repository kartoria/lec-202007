<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/flowControl.jsp</title>
</head>
<body>
<h4>Flow Control</h4>
<pre>
	HTTP : connectless, stateLess
	1. Dispatch : RequestDispatcher 를 이용해서 서버 내에서 이동.
		1) forward : 최종 도착지에서 완전한 UI가 전송되는 구조.
		2) include : 출발지와 도착지에서 만들어진 모든 UI가 전송되는 구조.
	2. Redirect : 최초 요청이 전송되고, 일부 처리가 이루어진 후, 
				body가 없고, 302/307(location) 메타 데이터를 가진 응답이 클라이언트로 전송.
				브라우저는 location 방향으로 새로운 요청을 전송하고, 최종 응답이 최종 도착지에서 전송됨.
	
		
	
</pre>
</body>
</html>