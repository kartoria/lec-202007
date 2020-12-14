<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/performance.jsp</title>
</head>
<body>
<h4>성능 확인</h4>
<pre>
	소요 시간(response time) : latency time + procesing time
	<a href="oneConnOneProcess.jsp">한번의 연결 한번의 처리 : 8ms, 0ms</a>
	<a href="100Conn100Process.jsp">백번 연결 백번 처리 : 680ms, 7ms</a>
	<a href="oneConn100Process.jsp">한번 연결 백번 처리 : 16ms, 5ms</a>
</pre>
</body>
</html>