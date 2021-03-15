<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pagingVO" value="${paginationInfo.pagingVO }" />
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 사이버 캠퍼스 > 공지사항</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">공지사항</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">사이버 캠퍼스 이용 공지 사항을 조회할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row my-3 d-flex justify-content-end">
		<div id="inputUI" class="col-12 col-sm-10 col-lg-8 col-xl-4 form-inline d-flex justify-content-xl-end justify-content-center align-items-stretch">
			<div class="form-group flex-grow-3">
				<select name="searchType" class="form-control w-100 h-100" id="selectSearch">
					<option value="">전체</option>
					<option value="title" ${'title' eq param.searchType?"selected":"" }>제목</option>
					<option value="date" ${'date' eq param.searchType?"selected":"" }>날짜</option>
				</select>
			</div>
			<div class="form-group flex-grow-1">
				<input id="searchInput" type="text" name="searchWord" onkeyup="enterkey()" class="form-control w-100 h-100">
			</div>
			<div class="form-group flex-grow-3">
				<button id="searchBtn" type="button" class="btn waves-effect waves-light btn-primary px-4 h-100" data-toggle="modal" data-target="#exampleModal">
					<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search form-control-icon">
						<circle cx="11" cy="11" r="8"></circle>
						<line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
				</button>
			</div>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-12 table-responsive">
			<table class="table">
				<thead class="thead-light">
					<tr class="text-center">
						<th style="width:5%" class="font-weight-bold"></th>
						<th style="width:10%" class="font-weight-bold">글번호</th>
						<th style="width:45%" class="font-weight-bold">제목</th>
						<th style="width:10%" class="font-weight-bold">작성자</th>
						<th style="width:20%" class="font-weight-bold">작성 날짜</th>
						<th style="width:5%" class="font-weight-bold">조회수</th>
					</tr>
				</thead>
				<tbody class="bg-white">
					<c:set var="boardList" value="${pagingVO.dataList }" />
					<c:if test="${not empty boardList }">
						<c:forEach items="${boardList }" var="board" varStatus="status">
							<c:url var="viewURL" value="/cyber/notice/${board.boNo}/view.do" />
							<tr class="text-center text-dark">
								<td><i class="fa fa-list"></i></td>
								<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
								<td class="text-left"><a href="${viewURL}">${board.boTitle }</a></td>
								<td>관리자</td>
								<td>${fn:substring(board.boDate,0,10)}</td>
								<td>${board.boHit }</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty boardList }">
						<tr>
							<td colspan="6" align="center">조건에 맞는 게시글이 존재하지 않습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<security:authentication property="principal" var="principal" />
		<div class="row my-3">
			<div class="col-12 d-flex justify-content-end align-items-center">
				<c:set var="authMember" value="${principal.realMember}" />
				<a class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2" href="${cPath}/cyber/notice/insertForm.do" role="button" id="insertBtn">등록</a>
			</div>
		</div>
	</security:authorize>
	<!-- ============================================================== -->
	<!--페이징 -->
	<!-- ============================================================== -->
	<div class="row my-3">
		<div class="dataTables_paginate paging_simple_numbers col-12 d-flex justify-content-center align-items-stretch" id="pagingArea">
			<ui:pagination paginationInfo="${paginationInfo }" jsFunction="pageLinkNumber" type="bsNumber" />
		</div>
	</div>
</div>
<!-- 검색 폼 -->
<form id="searchForm">
	<input type="hidden" name="page" /> 
	<input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType}" /> 
	<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }" />
	<input type="hidden" name="boGroupCode" value="CYNTC" />
</form>
<!-- 검색 폼 -->

<script type="text/javascript">
// == 페이징 ==
function pageLinkNumber(event) {
	event.preventDefault();
	let page = $(event.target).data("page");
	searchForm.find("[name='page']").val(page);
	searchForm.submit();
	return false;
}

// === 검색 ===
let searchForm = $("#searchForm");
$("#searchBtn").on("click", function() {
	let inputs = $(this).parents("div#inputUI").find(":input[name]");
	$(inputs).each(function(index, input) {
		let name = $(this).attr("name");
		let value = $(this).val();
		let hidden = searchForm.find("[name='" + name + "']");
		hidden.val(value);
	});
	searchForm.submit();
})

function enterkey(){
if(window.event.keyCode==13){
	$("#searchBtn").click();
}
}
</script>