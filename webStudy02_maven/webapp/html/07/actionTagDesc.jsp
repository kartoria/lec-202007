<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/actionTagDesc.jsp</title>
</head>
<body>
<h4>Action Tag</h4>
<pre>
	Custom Tag 사용방법 ex)&lt;prefix:taxName attributes &gt;
	1. custom tag 라이브러리를 빌드패스에 추가
	2. tablib 지시자로 라이브러리를 로딩(prefix 결정).
	3. &lt;prefix:tagName attributes &gt;
	
	액션태그 : jsp 스펙에 따라 기본 제공되는 커스텀 테그	, prefix:jsp
	<%-- <jsp:forward page="/html/02/userAgent.jsp"></jsp:forward> --%>
	
		 : 서버 사이드 테그  -> 응답데이터에 커스텀 테그가 포함되지 않음
<%-- 	<jsp:include page="/html/02/userAgent.jsp"></jsp:include> --%>
	
	**include 방식의 종류
		1. 정적 내포 : jsp 소스를 파싱하는 단계에서 소스 자체를 내포하는 구조.
			1) page 하나를 대상으로 한 내포 : include 지시자
			2) application 자체를 대상으로 한 내포 : web.xml의 jsp-config 활용
		2. 동적 내포 : 요청에 대한 처리가 이루어질때 runtime에 실행 결과를 내포하는 구조.
			1) RequestDispatcher.include
			2) PageContext.include
			3) include 액션 태그
			
	<%
// 		pageContext.include("/02/userAgent.jsp");
// 		out.print("=========>" + now.getTime());	
	%>
	
<!--jsp:useBean은 변수선언과 setAttribute 두개를 동시에 수행한다 밑의 주석같이 -->
	<jsp:useBean id="today" class="java.util.Date" scope="request"></jsp:useBean>
	<%--
		Date today = (Date)request.getAttribute("today");
		if(today==null){
			Date today = new Date();
			request.setAttribute("today", today);
		}
	--%>
	<%=today %>
	<%=request.getAttribute("today") %>
	
	
	
	<!-- 밑의 프로퍼티는 xxx를 getXxx로 바꿔서 수행한다 -->
	<jsp:getProperty property="time" name="today"/>
	<%-- today.getTime(); --%>
	
	<jsp:setProperty property="time" name="today" value="0"/>
	<%-- today.setTime(0);  --%>
	
	<!-- param에 적은 키로 파라미터를 받아서 세팅해준다 -->
	<jsp:setProperty property="year" name="today" param="year"/>
	<jsp:setProperty property="month" name="today" param="month"/>
	<jsp:setProperty property="date" name="today" param="date"/>
	<%=today %>
	
	
	
</pre>
</body>
</html>