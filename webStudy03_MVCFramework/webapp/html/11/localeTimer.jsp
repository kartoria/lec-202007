<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.ddit.or.kr/cal" prefix="cal"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/localeTimer</title>
</head>
<body>
<!-- 1. 언어 선택 -->
<!-- 2. 시간대 선택 -->
<form>
<select name="language" onchange="this.form.submit();">
	<c:forEach items="${Locale.getAvailableLocales() }" var="locale">
		<c:set var="language" value="${locale.displayLanguage }"/>
		<c:set var="country" value="${locale.displayCountry }"/>
		<c:if test="${not empty language and not empty country }">
			<option value="${locale.toLanguageTag() }">${language } - ${country }</option>
		</c:if>
	</c:forEach>
</select>
</form>
<c:choose>
	<c:when test="${not empty param.language }">
		<fmt:setLocale value="${param.language }" /> 
	</c:when>
	<c:otherwise>
		<fmt:setLocale value="${pageContext.request.locale }"/>
	</c:otherwise>
</c:choose>
<c:set var="now" value="${cal:generate() }"/>
<fmt:formatDate value="${now.time }" type="both" timeStyle="short" dateStyle="short"/>
</body>
</html>
