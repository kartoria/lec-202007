<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.ddit.or.kr/cal" prefix="cal" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body onload="timezone.focus();">
<!-- 1. 언어_국가 선택 -->
<!-- 2. 시간대 선택 -->
<form>
	<select id="language" name="language" onchange="this.form.submit();" tabindex="1">
		<c:forEach items="${Locale.getAvailableLocales() }" var="locale">
			<c:set var="language" value="${locale.displayLanguage }" />
			<c:set var="country" value="${locale.displayCountry }" />
			<c:if test="${not empty language and not empty country }">
				<option value="${locale.toLanguageTag() }" ${locale.toLanguageTag() eq param.language?"selected":"" }>${language }[${country }]</option>
			</c:if>
		</c:forEach>
	</select>
	<select id="timezone" name="timezone" onchange="this.form.submit();" tabindex="2">
		<c:forEach items="${cal:getTimeZones() }" var="zone">
			<option value="${zone.ID }" ${zone.ID eq param.timezone ? "selected":"" }>${zone.displayName }</option>
		</c:forEach>
	</select>
</form>

<c:choose>
	<c:when test="${not empty param.language}">
		<fmt:setLocale value="${param.language }"/>
	</c:when>
	<c:otherwise>
		<fmt:setLocale value="${pageContext.request.locale }"/>
	</c:otherwise>
</c:choose>
<c:if test="${not empty param.timezone }">
	<fmt:setTimeZone value="${param.timezone }"/>
</c:if>
<c:set var="now" value="${cal:generate() }" />
포맷팅 전 시간 : ${now.time } <br />
포맷팅 후 시간 : <fmt:formatDate value="${now.time }" type="both" timeStyle="long" dateStyle="long"/>
</body>
</html>




























