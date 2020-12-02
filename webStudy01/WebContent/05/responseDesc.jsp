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
HttpServletResponse : 서버에서 클라이언트로 전송되는 응답과 관련된 모든 정보를 캡슐화
  Http에서 response 패키징 방식
	1. response Line
		1) Status Code : 처리 결과를 지칭하는 숫자 코드 (sendError, sendRedirect)
		    (1) 100~ : ING(WebSocket)
		    (2) 200~ : OK
		    (3) 300~ : 클라이언트에게 추가 액션을 요구 (****중-요****)
		    		302/307(Moved) : Location Header와 병행 사용 (리다이렉션 방식)
		    		304 -> Not Modified : 정적 리소스는 캐시로 저장된다. 그 후 클라이언트가 요청을 할 때, 요청하는 자료가 이전의 자료와 동일하다면 캐시를 뒤져볼 것을 요청하는 코드이다.
		    (4) 400~ : 클라이언트 사이드 오류로 인한 FAIL 
		    		400 -> Bad Request
		    		401 -> Unauthorized : 들어올 수 있으나, 허가된 유저만 출입 가능
		    		403 -> Forbidden : 아예 들어올 수 없음
		    		404 -> Not Found
		    		405 -> Method Not Allowed : Post 방식을 원하는데 지원하지 않을 때
		    		415 -> Media Not Supported
		    (5) 500~ : 서버 사이드 오류로 인한 FAIL
	    
	  2) Protocol/ver
	  
	2. response Header : metadata header, 응답에 대한 설정 정보
		<a href="cacheControl.jsp">Cache-Control,</a>
		<a href="refresh.jsp">refresh</a>
		<a href="flowControl.jsp">Location</a>
		Content-Type <% response.setContentType("text/plain; charset=UTF-8"); %>
		Cache-Control
		Refresh
		Location-Header
	
	3. response Body : message body, content body
</pre>
</body>
</html>