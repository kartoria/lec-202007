<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<h4>기본 객체(Implicit Object)</h4>
<pre>
1. request(HttpServeltRequest)
2. response(HttpServletResponse)
3. out(JspWriter) : 응답데이터 기록이나 버퍼 제어 및 관리에 사용
4. <a href="sessionDesc.jsp">session(HttpSession)</a>
5. <a href="servletContextDesc.jsp">application(ServletContext)</a>
6. page(Object)==this
7. config(ServletConfig)
8. exception(Throwable) : 일반 페이지에서는 비활성화(page  지시자의 isErrorPage="true" 로 활성화시킴)
9. pageContext(*****) : 가장 먼저 생성되는 기본객체, 나머지 기본객체에 대한 getter 를 가짐.
	<%=pageContext.getRequest() == request %>
	<%=((HttpServletRequest) pageContext.getRequest()).getContextPath() %>
	${pageContext.request.contextPath }
	1) Scope 제어 <% pageContext.setAttribute("test", "value", PageContext.REQUEST_SCOPE); %>
				<%=request.getAttribute("test") %>
	2) 흐름 제어 : dispatch 방식
	3) 에러 데이터 확보
	<%--
// 		pageContext.include("/06/sessionDesc.jsp"); 
		request.getRequestDispatcher("/06/sessionDesc.jsp").include(request, response);
	
	--%>	
	
	이게 어디에서 보이나 확인!!!		
</pre>















