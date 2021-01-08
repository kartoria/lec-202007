<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<form id="btsForm" action="?service=BTS" method="post">
	<select name="bts" onchange="$('#btsForm').submit();" required>
		<option value="">멤버선택</option>
		<c:forEach items="${btsDB }" var="entry">
			<c:set var="btsVO" value="${entry.value }" />
			<c:set var="selectedAttr" value='${btsVO eq sessionScope.selected?"selected":"" }' />
			<option value="${btsVO.code }" ${selectedAttr }>${btsVO.name }</option>
		</c:forEach>
	</select>
</form>

















