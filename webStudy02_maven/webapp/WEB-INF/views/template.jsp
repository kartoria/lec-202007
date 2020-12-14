<%@page import="org.apache.commons.lang3.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>layout</title>

<tiles:insertAttribute name="preScript"/>
</head>
<body>

	<header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
	<div id="topMenu">
		<tiles:insertAttribute name="topMenu"/>
	</div>
	</header>
	
	<nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
		<div id="leftMenu">
			<tiles:insertAttribute name="leftMenu"/>
		</div>
	</nav>
	
	<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
		<div>
			<tiles:insertAttribute name="contents"/>
		</div>
	</main>
	<div id="footer">
		<tiles:insertAttribute name="footer"/>
<%  String msg = (String)request.getAttribute("msg");
	if(StringUtils.isBlank(msg)){
		msg = (String)session.getAttribute("msg");
		session.removeAttribute("msg"); //flash attribute
	}
	if(StringUtils.isNotBlank(msg)){ %>
		<script type="text/javascript">
			alert("<%=msg%>");
		</script>
<%	} %>
	</div>
</body>
</html>