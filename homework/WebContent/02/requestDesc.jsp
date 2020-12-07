<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	String param = request.getParameter("param");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>requestDesc.jsp</title>
</head>
<body>
	<%=param %>
	<h4>HttpServletRequest</h4>
	<pre>
		: http 프로토콜을 이용하여 서블릿을 대상으로 발생한 요청에 대한 캡슐화
		
		HTTP request 패키징 분석	
		1) Request Line : URL Method Protocol/version
			http method : 요청의 목적, 요청의 패키징 방식
			GET(R)      : 조회
			POST(C)     : 생성
			PUT/PATCH(U): 수정
			DELETE(D)   : 삭제
			HEADER
			OPTION      : preflight 요청에 사용.
			TRACE          
			
		2) Request Header : meta data
		
		3) Request Body (only POST) : content body, message body
		
		** request 의 메소드 종류
		<%=request.getCharacterEncoding() %>
		<%=request.getContentLength() %>
		<%=request.getContentType() %>
		server : <%=request.getLocalAddr() %>, <%=request.getLocalName() %>, <%=request.getLocalPort() %>
		<%=request.getRemoteAddr() %>, <%=request.getRemoteHost() %>, <%=request.getRemotePort() %>
		<%=request.getRequestURI() %>, <%=request.getMethod() %>, <%=request.getProtocol() %>
		<%=request.getRequestURL() %>
		<%=request.getQueryString() %>: ?sector1&sector2...sectorN
									  sector="param_name=param_value"
		<%=request.getLocale() %>								
		
		** 요청파라미터 확보
		String getParameter(name), getParameterValues(name)
		Map&lt;String, String[]&gt; getParameterMap(), Enumeration&lt;String&gt; getParameterNames()
		
		**파라미터에 포함된 특수문자 처리(RFC 2396 규약에 따라 URL encoding 방식으로 인코딩되어 전달됨.)
		GET
		POST
	</pre>	
	<form method="post">
		<input type="hidden" name="_method" value="PUT">
	</form>
	<table>
			<%
				Enumeration<String> names = request.getHeaderNames();
				while(names.hasMoreElements()){
					
					String headerName = names.nextElement();
					String headerValue = request.getHeader(headerName);
			%>
				<tr>
					<td><%=headerName %></td>
					<td><%=headerValue %></td>
				</tr>
			<% 	} %>
	</table>
</body>
</html>