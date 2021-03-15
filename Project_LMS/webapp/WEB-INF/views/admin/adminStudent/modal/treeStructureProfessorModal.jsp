<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
	<tr>
		<td class="w-25" rowspan="4" colspan="2">
			<c:if test="${not empty memVO.memImg }">
				<img id="img" class="imgSize" src="${cPath}/memberImages/${memVO.memImg }" alt='${cPath}/images/noImage.png' />
			</c:if>
			<c:if test="${ empty memVO.memImg }">
				<img id="img" class="imgSize" src="${cPath}/images/noImg.png" />
			</c:if>
		</td>
		<td class="table-active"><p class="font-weight-bold">사번 </p></td>
		<td>${memVO.memId}</td>
		<td class="table-active"><p class="font-weight-bold">이름</p></td>
		<td>${memVO.memName}</td>
		<td class="table-active"><p class="font-weight-bold">학과</p></td>
		<td>${memVO.depName}</td>
	</tr>
	<tr>
	</tr>
	<tr>
		<td class="table-active"><p class="font-weight-bold">근무상태</p></td>
		<td>${memVO.description}</td>
		<td class="table-active"><p class="font-weight-bold">주민번호</p></td>
		<td colspan="3">${memVO.memReg1} - ******* </td>
	</tr>
	<tr>
		<td class="table-active"><p class="font-weight-bold">전화번호</p></td>
		<td id="formatNum"><input type="hidden" name="memTel" value="${memVO.memTel }"></td>
		<td class="table-active"><p class="font-weight-bold">입사년도</p></td>
		<td>${memVO.memAdmission}</td>
		<td class="table-active"><p class="font-weight-bold">이메일</p></td>
		<td>${memVO.memMail }</td>
	</tr>
	<tr>
		<td class="table-active" ><p class="font-weight-bold">주소</p></td>
		<td colspan="3"><span id="span1">${memVO.memAddr1 }</span></td>
		<td class="table-active" ><p class="font-weight-bold">상세 주소</p></td>
		<td colspan="2"><span id="span2">${memVO.memAddr2 }</span></td>
	</tr>
	<tr>
		<td class="table-active" ><p class="font-weight-bold">계좌</p></td>
		<td colspan="6">
			<table class="table table-sm w-100">
				<tr>
					<th class="table-info text-center">은행</th>
					<td scope="col">${memVO.memBank }</td>
					<th class="table-info text-center">예금주</th>
					<td scope="col">${memVO.memName }</td>
					<th class="table-info text-center">계좌번호</th>
					<td scope="col" colspan="2">${memVO.memAcn }</td>
				</tr>
			</table>
		</td>
	</tr>
