<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/jstlDesc.jsp</title>
<style type="text/css">
	.green{
		background-color: green;
	}
	.red{
		background-color: red;
	}
</style>
</head>
<body>
<h4>JSTL(Java Standard Tag Library)</h4>
<table>
	<c:forEach var="dan" begin="2" end="9" step="2" varStatus="vs">
		<c:choose>
			<c:when test="${vs.count eq 3 }">
				<c:set var="clzName" value="green" />
			</c:when>
			<c:when test="${vs.last }">
				<c:set var="clzName" value="red" />
			</c:when>
			<c:otherwise>
				<c:set var="clzName" value="normal" />
			</c:otherwise>
		</c:choose>
		<tr class="${clzName }">
			<c:forEach var="mul" begin="1" end="9">
				<td>${dan}*${mul}=${dan*mul }[${vs.count }]</td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>
<pre>
	: 커스텀 태그들 중 널리 쓰이는 것들을 모아놓은 라이브러리.
	
	1. taglib 커스텀 태그 로딩(prefix 결정).
	2. &lt;prefix:tagname attibutes &gt;
	
	** 커스텀 태그 종류
	1. core
		1) EL변수(속성) 지원 : set, remove
		<c:set var="test" value="테스트" scope="page" />
		${test }
		<c:remove var="test" scope="page"/> 
		${test }
		2) 조건문 : if, choose~when~otherwise
			${empty test ? "지워졌음" : test }
			단일조건문 : if
			<c:if test="${empty test }">
				"지워졌음"
			</c:if>
			<c:if test="${not empty test }">
				${test }
			</c:if>
			다중조건문 : choose~when~otherwise
			<c:choose>
				<c:when test="${empty test }">
					"지워졌음"
				</c:when>
				<c:otherwise>
					${test }
				</c:otherwise>
			</c:choose>
		3) 반복문 : forEach, forTokens
			forEach : var, begin, step, end, items, varStatus(LoopTagStatus 객체에 대한 참조)
			forTokens : items, var, delims
			<c:forTokens items="1,2,3" delims="," var="num" varStatus="vs">
				${num * 3 } ${not vs.last ?",":"" }
			</c:forTokens>
			<c:forEach var="i" begin="0" step="1" end="3">
				${i }
			</c:forEach>
			<c:set var="sampleArray" value='<%=new String[]{"value1", "value2"} %>' />
			<c:forEach items="${sampleArray }" var="element">
				${element }
			</c:forEach>
		4) url 재처리(url rewriting) : url, redirect
			<c:url value="/11/jstlDesc.jsp" var="clientURL"/>
			<a href="${clientURL }">세션유지하기</a>
			세션 아이디 : ${pageContext.session.id }
			<c:url value="/prod/prodView.do" var="viewURL">
				<c:param name="what" value="P101000001" />
			</c:url>
			<a href="${viewURL }">상품상세조회</a>
<%-- 			<c:redirect url="/" /> --%>
		5) 기타 : import, out, ...
<%-- 			<c:import url="https://www.daum.net" var="daum"></c:import> --%>
<%-- 			<c:out value="${daum }" escapeXml="false"></c:out> --%>
	2. fmt (국제화) : parsing/formatting, message 처리.
		<fmt:setLocale value="<%=Locale.US %>"/>
		<fmt:formatDate var="dateText" value="<%=new Date() %>" type="both" dateStyle="long" timeStyle="long" />
		${dateText }
		<fmt:parseDate value="${dateText }" type="both" dateStyle="long" timeStyle="long" var="dateObject" />
		${dateObject }
		
		<fmt:setLocale value="<%=Locale.KOREA %>"/>
		<fmt:formatNumber value="100000" type="currency" var="won" />
		${won }
		<fmt:parseNumber value="${won }" type="currency" var="number" />
		<fmt:formatNumber value="${number * 3 }" type="currency" />
		
	** 국제화 메시지 처리 단계
	1. 언어 선택(영어, 한글)
	2. 메시지 번들 작성(message_ko, message_en)
	<fmt:setLocale value="<%=Locale.ENGLISH %>"/>
	<fmt:bundle basename="kr.or.ddit.msg.message">
	3. 메시지 번들 로딩(로케일에 따라)
	4. 메시지키를 이용하여 사용.
		<fmt:message key="bow" />
	</fmt:bundle>
	
	3. functions(fn)
		<c:set var="array" value='<%=new String[]{"value1", "valu2"} %>'></c:set>
		${fn:join(array, "|") }
</pre>
</body>
</html>














