<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<security:authentication property="principal" var="principal"/>
<c:set var="authMember" value="${principal.realMember }" />
<c:set var="pagingVO" value="${paginationInfo.pagingVO }" />
<div class="container-fluid">
	<!-- *************************************************************** -->
	<!-- End Top Leader Table -->
	<!-- *************************************************************** -->
	<form id="searchForm">
		<input type="hidden" name="page" /> <input type="hidden"
			name="searchType" value="${pagingVO.searchVO.searchType }" /> <input
			type="hidden" name="searchWord"
			value="${pagingVO.searchVO.searchWord }" />
	</form>
	<div class="row">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 내 강의실 > 학습 정보 > 질문 게시판
			</h5>
		</div>
	</div>
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-9 text-left">
			<h2 class="font-weight-bold">질문 게시판</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-11">
			<h5 class="pb-5 ml-2">질문을 등록하고 답변을 조회할 수 있습니다.</h5>
		</div>
		<div class="col-1 m-auto">
			<!-- =========== 버튼 입력 부분 ============= -->
			<security:authorize access="hasRole('ROLE_STUDENT')">
				<a class="btn waves-effect waves-light btn-info px-4 py-2"
					href="${cPath}/myclass/${lecCode}/qnaBoInsertForm.do" role="button" id="insertBtn">등록</a>
			</security:authorize>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div id="tableArea">
				<table class="table">
					<thead class="thead-light">
						<tr>
							<th class="font-weight-bold">글번호</th>
							<th class="font-weight-bold">제목</th>
							<th class="font-weight-bold">작성자</th>
							<th class="font-weight-bold">등록 날짜</th>
							<th class="font-weight-bold">조회수</th>
							<th class="font-weight-bold">답변 여부</th>
						</tr>
					</thead>
					<tbody id="listBody">
						<c:set var="boardList" value="${pagingVO.dataList }" />
						<c:if test="${not empty boardList }">
							<c:forEach items="${boardList }" var="board" varStatus="status">
								<tr>
									<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
									<c:url var="viewURL" value="/myclass/${lecCode}/${board.boNo}/qnaView.do" />
									<td><a href="${board.boDelete ne 'Y' ? viewURL : '#' }">${board.boTitle }</a></td>
									<td>${board.memName }</td>
									<td>${fn:substring(board.boDate,0,10)}</td>
									<td>${board.boHit }</td>
									<td>
									<c:set var="boNo" value="${board.boNo }"/>
									<c:choose>
			                    		<c:when test="${fn:contains(repList, boNo)}">
											<a class="btn btn-secondary px-4 py-2 font-weight-bold text-white" 
											role="button">답변 완료</a>
										</c:when>
			                    		<c:otherwise>
				                    		<a class="btn btn-light px-4 py-2 font-weight-bold"
											role="button">답변 대기</a>
			                    		</c:otherwise>
			                    	</c:choose>
									<!-- 답변 여부 확인 -->	
									</td>
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
	</div>
	<div class="row">
		<div class="col-1"></div>
		<div class="dataTables_paginate paging_simple_numbers col-8"
			id="pagingArea">
			<ui:pagination paginationInfo="${paginationInfo }"
				jsFunction="pageLinkNumber" type="bsNumber" />
		</div>
		<div id="inputUI" class="col-3">
			<div id="searchArea" class="row p-0 justify-content-right">
				<div class="col-1"></div>
				<div class="col-3 p-0 text-right">
					<select name="searchType" class="form-control mr-1">
						<option value="">전체</option>
						<option value="writer"
							${'writer' eq param.searchType?"selected":""  }>작성자</option>
						<option value="title"
							${'title' eq param.searchType?"selected":""  }>내용</option>
					</select>
				</div>
				<div class="col-6 p-0 text-center">
					<input id="searchInput" type="text" name="searchWord"
						onkeyup="enterkey()" class="form-control">
				</div>
				<div class="col-2 p-0 text-left">
					<button id="searchBtn" type="button" class="btn btn-primary w-100"
						data-toggle="modal" data-target="#exampleModal">
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
							viewBox="0 0 24 24" fill="none" stroke="currentColor"
							stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
							class="feather feather-search form-control-icon">
									<circle cx="11" cy="11" r="8"></circle>
									<line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
					</button>
				</div>
			</div>
		</div>

	</div>
</div>

<script type="text/javascript">
var searchForm = $("#searchForm");
function pageLinkNumber(event){
	event.preventDefault();
	let page = $(event.target).data("page");
	searchForm.find("[name='page']").val(page);
	console.log(page);
	searchForm.submit();
	return false;
}
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
function enterkey(){
	if(window.event.keyCode==13){
		$("#searchBtn").click();
	}
}
</script>