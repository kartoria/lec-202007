<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<tr>
	<td class="table-active"><p class="font-weight-bold">장학코드</p></td>
	<td>${schVO.scharCode }</td>
	<td class="table-active"><p class="font-weight-bold">장학명</p></td>
	<td>${schVO.schName }</td>
	<td class="table-active"><p class="font-weight-bold">학기</p></td>
	<td>${fn:substring(schVO.sfundSmst,0,4) }년도
		${fn:substring(schVO.sfundSmst,5,6) }학기</td>
</tr>
<tr>
	<td class="table-active"><p class="font-weight-bold">신청일자</p></td>
	<td >${schVO.sfundResdate }</td>
	<td class="table-active"><p class="font-weight-bold">신청상태</p></td>
	<td>${schVO.codeName }</td>
	<td class="table-active"><p class="font-weight-bold">처리일자(지급일자)</p></td>
	<td>${schVO.sfundGetdate }</td>
</tr>
<tr>
	<td class="table-active"><p class="font-weight-bold">신청사유</p></td>
	<td colspan="5">${schVO.sfundReason }</td>
</tr>
					

