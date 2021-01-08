<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/actionTagDesc.jsp</title>
</head>
<body>
<h4> Action Tag </h4>
<pre>
	Custom Tag 사용방법 ex) &lt;prefix:tagName attributes &gt;
	1. custom tag 라이브러리를 빌드패스에 추가
	2. taglib 지시자로 라이브러리를 로딩(prefix 결정).
	3. &lt;prefix:tagName attributes &gt;
	
	액션 태그 : jsp 스펙에 따라 기본 제공되는 커스텀 태그, prefix:jsp
<%-- 	<jsp:forward page="/02/userAgent.jsp" /> --%>
<%-- 		<jsp:include page="/02/userAgent.jsp" /> --%>
		** include 방식의 종류(시점, 대상)
			1. 정적 내포 :jsp 소스를 파싱하는 단계에서 소스 자체를 내포하는 구조.
				1) page 하나를 대상으로 한 내포 : include 지시자
				2) 어플리케이션 자체를 대상으로 한 내포 : web.xml의 jsp-config 활용
			2. 동적 내포 : 요청에 대한 처리가 이루어질때 runtime에 실행 결과를 내포하는 구조.
				1) RequestDispatcher.include
				2) PageContext.include
				3) include 액션 태그
			<%
// 				pageContext.include("/02/userAgent.jsp");
// 				out.println("============>"+now.getTime());
			%>
		<jsp:useBean id="today" class="java.util.Date" scope="request"></jsp:useBean>		
		<%--
			Date today = (Date)request.getAttribute("today");
			if(today==null){
				today = new Date();
				request.setAttribute("today", today);
			}
			today.setTime(0);
		--%>
		<jsp:getProperty property="time" name="today"/>
		<jsp:setProperty property="time" name="today" value="0"/>
		<jsp:getProperty property="time" name="today"/>
<%-- 		<jsp:setProperty property="year" name="today" param="year"/> --%>
<%-- 		<jsp:setProperty property="month" name="today" param="month"/> --%>
<%-- 		<jsp:setProperty property="date" name="today" param="date"/> --%>
		<jsp:setProperty property="*" name="today"/>
		
		<%=today %>
		<%=request.getAttribute("today") %>
			
		
</pre>
</body>
</html>













