<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="pagingVO" value= "${paginationInfo.pagingVO }"/>
<div class="container-fluid border px-3">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h3 class="ml-2">
				<i class="fas fa-home"></i> 등록금 > 납부 이력 조회
			</h3>
		</div>
	</div>
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-9 text-left">
			<h2 class="font-weight-bold">납부 이력 조회</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-11">
			<h5 class="pb-5 ml-2">납부 내역을 조회할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<table 	class="table">
				<thead class="thead-light">
					<tr class="text-center text-dark">
						<th class="font-weight-bold">순번</th>
						<th class="font-weight-bold">학기</th>
						<th class="font-weight-bold">원납금액</th>
						<th class="font-weight-bold">실납입금</th>
						<th class="font-weight-bold">납부 유형</th>
						<th class="font-weight-bold">납부 완료일</th>
						<th class="font-weight-bold">납부 여부</th>
					</tr>
				</thead>
				<tbody>
				<c:set var="vacountList" value="${pagingVO.dataList }" />
					<c:if test="${not empty vacountList }">
						<c:forEach items="${vacountList }" var="va" varStatus="status">
							<tr class="text-center">
								<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
								<td>
									${fn:substring(va.smst,0,4) }년도
									${fn:substring(va.smst,5,6) }학기
								</td>
								<td class="text-right font-weight-bold">
									<fmt:formatNumber value="${va.payExcpect }" maxFractionDigits="3"/>원
								</td>
								<td class="text-right font-weight-bold">
									<c:if test="${not empty va.payAmount }">
										<fmt:formatNumber value="${va.payAmount }" maxFractionDigits="3"/>원
									</c:if>
									<c:if test="${empty va.payAmount }">
										0원
									</c:if>
								</td>
									<c:choose>
										<c:when test="${va.payNumber eq 0}">
										 	<td>원금 납부</td>
										</c:when>
										<c:when test="${va.payNumber ne 0 }">
											<td>분할 납부 ${va.payNumber }차</td>
										</c:when>
									</c:choose>
								<td>${va.payDate }</td>
								<td class="font-weight-bold">
									<c:if test="${not empty va.payAmount }">
										완납
									</c:if>
									<c:if test="${empty va.payAmount }">
										미납
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty vacountList }">
						<tr class="text-center">
							<td colspan="7" class="font-weight-bold">등록금 납부 내역이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
	<br>
	<div id="pagingArea">
		<ui:pagination paginationInfo="${paginationInfo }" jsFunction="pageLinkNumber" type="bsNumber"/>
	</div>
</div>
<script type="text/javascript">
function pageLinkNumber(event){
	event.preventDefault();
	let page = $(event.target).data("page");
	searchForm.find("[name='page']").val(page);
	console.log(page);
	searchForm.submit();
	return false;
}

var searchForm = $("#searchForm");
$("#searchBtn").on("click", function(){
	let inputs = $(this).parents("div#inputUI").find(":input[name]");
	$(inputs).each(function(index, input){
		let name = $(this).attr("name");
		let value = $(this).val();
		let hidden = searchForm.find("[name='"+name+"']");
		hidden.val(value);
	});
	searchForm.submit();
});
</script>