<%@page import="kr.or.ddit.vo.NotyMessageVO"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<tiles:insertAttribute name="preScript" />
</head>
<body class="vh-100">
	<div class="d-flex flex-column h-100">
	<header>
		<tiles:insertAttribute name="topMenu" />
	</header>
	<div class="container-fluid flex-grow-1 overflow-auto">
		<div class="row h-100">
			<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
				<tiles:insertAttribute name="leftMenu" />
			</nav>
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<tiles:insertAttribute name="contents" />
			</main>
		</div>
	</div>
	<tiles:insertAttribute name="footer" />
	<c:if test="${not empty message }">
		<script type="text/javascript">
<%-- 		alert("<%=message %>"); --%>
			new Noty({
				 text:'${message.text }', 
				 layout: '${message.layout }',
				 type: '${message.type }',
				 timeout: ${message.timeout },
				 progressBar: true
			}).show();
		</script>
		<c:remove var="message" scope="session" />
	</c:if>
	</div>
</body>
</html>









