<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<security:authorize access="isAuthenticated()">
	<security:authentication property="principal" var="principal" />
	<c:set var="authMember" value="${principal.realMember }" />
</security:authorize>
<input type="hidden" value="${authMember.memType }" id="realmemType" />
<input type="hidden" value="${authMember.memId }" id="realmemId" />
<div class="container-fluid">
	<c:set var="pagingVO" value="${paginationInfo.pagingVO }" />
	
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2"><i class="fas fa-home"></i> 사이버 캠퍼스 > Q&A</h5>
		</div>
	</div>
	
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">Q & A</h2>
		</div>
	</div>
	
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">질문을 등록하고 답변을 조회할 수 있습니다.</h5>
		</div>
	</div>
	
	<div class="row my-3 d-flex justify-content-end">
		<!-- ============== 제목 & 검색 필드 ============= -->
		<div id="inputUI" class="col-12 col-sm-10 col-lg-8 col-xl-4 form-inline d-flex justify-content-xl-end justify-content-center align-items-stretch">
			<div class="form-group flexgrow-3">
				<select name="searchType" class="form-control w-100 h-100">
					<option value="">전체</option>
					<option value="writer" ${'writer' eq param.searchType?"selected":""  }>작성자</option>
					<option value="title" ${'title' eq param.searchType?"selected":""  }>내용</option>
				</select>
			</div>
			<div class="form-group flex-grow-1">
				<input id="searchInput" type="text" name="searchWord" onkeyup="enterkey()" class="form-control w-100 h-100" placeholder="검색어를 입력하세요.">
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
						<th style="width:20%" class="font-weight-bold">등록 날짜</th>
						<th style="width:5%" class="font-weight-bold"">조회수</th>
					</tr>
				</thead>
				<tbody class="bg-white">
					<c:set var="qnaList" value="${pagingVO.dataList }" />
					<c:if test="${not empty qnaList }">
						<c:forEach items="${qnaList }" var="qna" varStatus="status">
							<c:url var="viewURL" value="/cyber/qna/${qna.boNo}/view.do" />
							<tr class="text-center text-dark">
								<td><i class="fa fa-list"></i></td>
								<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
								<td class="text-left">
									<c:if test="${authMember.memType ne 'ROLE_ADMIN' }">
										<c:if test="${qna.boSecret eq 'N' }">
											<a onclick="checkPassword('${qna.boNo}', '${qna.memId }')"> 
												<i class="fas fa-lock"></i>
												<span>비밀글</span>
												<span class="replyCountSpan text-danger"></span>
											</a>
										</c:if>
										<c:if test="${qna.boSecret eq 'Y' }">
											<a href="${cPath}/cyber/qna/${qna.boNo}/view.do">
												${qna.boTitle } 
												<span class="replyCountSpan text-danger"></span>
											</a>
										</c:if>
									</c:if>
									<c:if test="${authMember.memType eq 'ROLE_ADMIN' }">
										<a href="${cPath}/cyber/qna/${qna.boNo}/view.do">
											${qna.boTitle } 
											<span class="replyCountSpan text-danger"></span>
										</a>
									</c:if>
								</td>
								<td>${qna.memName}</td>
								<td>${fn:substring(qna.boDate,0,10)}</td>
								<td>${qna.boHit}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty qnaList }">
						<tr class="text-center">
							<td colspan="6">등록된 질문글이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-end align-items-center">
			<a class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2" href="${cPath}/cyber/qna/insert.do" role="button" id="insertBtn">등록</a>
		</div>
	</div>
	<div class="row my-3">
		<div class="dataTables_paginate paging_simple_numbers col-12 d-flex justify-content-center align-items-stretch" id="pagingArea">
			<ui:pagination paginationInfo="${paginationInfo }" jsFunction="pageLinkNumber" type="bsNumber" />
		</div>
	</div>
</div>

<!-- 비번 체크 모달 -->
<div class="modal fade" id="passModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="myCenterModalLabel">비밀번호 재입력</h3>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div id="searchArea" class="card-body">
					<div class="input-group col-12 d-flex justify-content-center align-items-center">
						<div class="form-group">
							<p>
								<span class="badge badge-warning">주의</span> 비밀번호를 입력해야 비밀글 보기가 가능합니다.
							</p>
						</div>
						<div class="form-group flex-grow-1">
							<input type="password" id="example-input-large" name="memPass" class="form-control form-control-lg" placeholder="비밀번호를 입력해주세요.">
						</div>
						<div class="form-group">
							<button id="modalBtn" type="button" class="btn btn-primary btn-lg">
								<i class="fas fa-location-arrow"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<form id="searchForm">
	<input type="hidden" name="page" /> 
	<input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType }" /> 
	<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }" />
</form>

<!-- 비번 입력했을 때 불일치 시에 뜨는 알림창 모달 -->
<input type="hidden" id="modalNegativetBtn" class="btn btn-secondary" data-toggle="modal" data-target="#centermodalNegative" value="숨겨진모달">
<div class="modal fade" id="centermodalNegative" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header bg-danger text-white">
				<h5 class="modal-title ">경고창</h5>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body text-center">
				<p>
					접근할 수 없는 글입니다.
				</p>
			</div>
		</div>
	</div>
</div>

<!-- 글이 삭제 되어있을 때, 클릭했을 경우 나오는 모달창 -->
<input type="hidden" id="modalSecretAlam" class="btn btn-secondary" data-toggle="modal" data-target="#centermodalAccpt" value="숨겨진모달">
<div class="modal fade" id="centermodalAccpt" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<h5 style="text-align: center;">비밀글입니다!</h5>
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


let boNo ="";
let memId = "";
let memPass = "";
// 상세 조회 시, 비번 체크
function checkPassword(boNo2,memId2){
	boNo = boNo2;
	memId = memId2;
	let realMemType = $("#realmemType").val();
	let realMemId = $("#realmemId").val();
	
	if(realMemType == 'ROLE_ADMIN' ){
		location.href="${cPath}/cyber/qna/"+boNo+"/view.do";
	}else{
		if(realMemId == memId ){
			$("#example-input-large").val("");
			$("#passModal").modal();
		} else {
			document.getElementById("modalSecretAlam").click();
		}
	}
}

$("#modalBtn").on("click", function(){
	memPass = $("#example-input-large").val();
	$.ajax({
		url : '${cPath}/cyber/qna/deletePassCheck.do'
		, method : 'post'
		, data : 
			{
				"memPass" : memPass
				, "memId" : memId
				, "boNo" : boNo
			}
		, dataType : 'json'
		, success : function(resp){
			console.log(resp.result);
			if(resp.result == "OK"){
				location.href="${cPath}/cyber/qna/"+boNo+"/view.do";
			} 
			if(resp.result == "FAILED"){
				$("#modalNegativetBtn").click();
				$(".close").click();
			}
		}
		, error : function(request, error){
	    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
	    }
	});
});

</script>