<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/sessionDesc.jsp</title>
</head>
<body>
<h4> HttpSession </h4>
<pre>
	세션 이란?
		통로 : 두 피어사이에 유일하게 개방된 연결.
		기간 : 한클라이언트가 하나의 브라우저를 이용해서 어플리케이션 사용 시작 이벤트를 발생시킨 후,
			종료 이벤트를 발생시킬때까지의 기간.
	세션의 생명주기
		시작 이벤트 : 최초의 요청이 발생하면 세션 시작
			session id : <%=session.getId() %>(<%=new Date(session.getCreationTime()) %>)
			<%=new Date(session.getLastAccessedTime()) %>
			<%=session.getMaxInactiveInterval() %>s
			<% session.setMaxInactiveInterval(3*60); %>
			<%=session.getMaxInactiveInterval() %>s
		종료 이벤트 :
			1) invalidate(명확한 로그아웃)
			2) timeout 이내에 새로운 요청이 발생하지 않으면 세션 만료.
			3) 브라우저 종료
			4) 쿠키 삭제
	세션이 유지되는 구조(session tracking) : 세션 유지를 위한 session id 재전송 방법
		1) COOKIE : session id 가 쿠키의 형태로 클라이언트에 저장되어 재전송
		2) URL : 세션 파라미터 재전송 구조 <a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">세션 유지</a>
		3) SSL : secure socket layer 를 이용해 암호화된 재전송 구조
</pre>
<%=application.hashCode() %>
</body>
</html>















