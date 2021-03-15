<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<tr>
	<td class="table-active font-weight-bold w-25">장학코드</td>
	<td>${schVO.scharCode }</td>
	<td class="table-active font-weight-bold">장학명</td>
	<td>${schVO.schVO.schName }</td>
	<td class="table-active font-weight-bold">학기</td>
	<td>${fn:substring(schVO.sfundSmst,0,4) }년도
		${fn:substring(schVO.sfundSmst,5,6) }학기</td>
</tr>
<tr>
	<td class="table-active font-weight-bold w-25">신청일자</td>
	<td >${schVO.sfundResdate }</td>
	<td class="table-active font-weight-bold">신청상태</td>
	<td>${schVO.codeVO.codeName }</td>
	<td class="table-active font-weight-bold">처리일자(지급일자)</td>
	<td>${schVO.sfundGetdate }</td>
</tr>
<tr>
	<td class="table-active font-weight-bold w-25">학번</td>
	<td >${schVO.sfundStudent }</td>
	<td class="table-active font-weight-bold">이름</td>
	<td>${schVO.memberVO.memName }</td>
	<td class="table-active font-weight-bold">수혜금액</td>
	<td><fmt:formatNumber value="${schVO.sfundValue }" pattern="#,###" />원</td>
</tr>
<tr>
	<td class="table-active font-weight-bold w-25">신청사유</td>
	<td colspan="5">${schVO.sfundReason }</td>
</tr>
			