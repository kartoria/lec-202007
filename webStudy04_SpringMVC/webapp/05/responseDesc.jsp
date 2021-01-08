<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/responseDesc.jsp</title>
</head>
<body>
<h4>HttpServletResponse</h4>
<pre>
	: 서버에서 클라이언트로 전송되는 응답과 관련된 모든 정보 캡슐화 
	Http 에서 response 패키징 방식
	1. response Line : Status Code, Protocol/ver
		Status Code : 처리 결과를 지칭하는 숫자 코드 (sendError, sendRedirect)
		1) 100~ : ING....(WebSocket)
		2) 200~ : OK
		3) 300~ : 클라이언트의 추가 액션 요구
			304(Not Modified)
			302/307(Moved) : Location 헤더와 병행 사용
		4) 400~ : 클라이언트 오류로 실패
			404(Not Found), 400(Bad Request)
			401(UnAuthorized), 403(Forbidden)
			405(Method Not Allowed), 415(Media Not Supported)
		5) 500~ : 서버 오류로 실패 (500)
	2. response Header : metadata header
		Content-Type, 
		<a href="cacheControl.jsp">Cache-Control,</a> 
		<a href="refresh.jsp">Refres</a>, 
		<a href="flowControl.jsp">Location</a>
		<%
// 			response.setHeader("Content-Type", "text/plain;charset=UTF-8");
			response.setContentType("text/plain;charset=UTF-8");
		%>
	3. response Body : message body, content body
	
	
</pre>
<img src="${pageContext.request.contextPath }/images/cat1.jpg" />
</body>
</html>













