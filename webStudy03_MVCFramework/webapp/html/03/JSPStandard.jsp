<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="1kb" autoFlush="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4> JSP (Java Server Page) </h4>
	<pre>
		: jsp 소스 표준 구성 요소 (스펙)
		1. 정적 텍스트 요소
		2. 스크립트
			1) 스크립틀릿   (scriptlet) 	: <% //java code %>
			2) 표현식		(expression) 	: <%="출력 데이터"%>
			3) 지시자		(Directive) 	: <%--@ 설정정보 --%>
			4) 선언부		(Declaration) 	: <%--! 전역 멤버 --%>
			5) 주석		(Comment)		: <%-- 주석 --%>
<!-- 				주석1 -->
				<%-- 주석2--%>
				client side : HTML JAVASCRIPT, css
				server side : java, jsp 
		3. 기본객체 (내장객체)
		4. 액션 태그 (jsp action tag)
		5. 표현 언어 (Expression Language, EL)
		6. JSTL (tag library)
		<%
			for(int i = 1; i<600; i++){
				out.println(i+"번째 출력");
				if(i==70){
					throw new RuntimeException("강제 발생 오류");
				}
			}
		%>
	</pre>
</body>
</html>