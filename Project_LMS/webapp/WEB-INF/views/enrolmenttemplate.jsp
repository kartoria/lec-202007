<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수강신청 페이지</title>
<tiles:insertAttribute name="enrolmentPreScript" />
<!-- <script  src="http://code.jquery.com/jquery-latest.min.js"></script> -->

<!-- This Page CSS -->
<link rel="stylesheet" type="text/css" href="${cPath}/resource/assets/extra-libs/prism/prism.css">
</head>
<body>
	<tiles:insertAttribute name="enrolmentContents" />
	<tiles:insertAttribute name="enrolmentFooter"/>
</body>



</html>