<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${cPath}/css/admin/scholarship.css">
<div class="container-fluid">
		<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 장학관리 > 장학유형
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-admin p-0 my-2">
		<div class="col-12">
			<h2 class="font-weight-bold">장학 유형</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">장학 유형 조회/등록/수정/비활성화 처리 할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-12 text-right">
			<a class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2" href="${cPath}/admin/insertScholarTypeForm.do">신규등록</a>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<table class="table table-bordered">
			<thead class="bg-primary text-white">
				<tr>
					<th>장학금코드</th>
					<th>장학금명</th>
					<th>장학금액</th>
					<th>장학유형</th>
					<th class="w-50">지급기준</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="scholarshipList" value="${scholarship }" />
				<c:if test="${not empty scholarshipList }">
					<c:forEach items="${scholarshipList }" var="scholarship">
						<tr onclick="showModal(${scholarship.schCode })">
							<td id="schCode">${scholarship.schCode}</td>
							<td>${scholarship.schName}</td>
							<td>
							<fmt:formatNumber value="${scholarship.schAmount }" pattern="#,###" />
							[${scholarship.schAmountDetail }]</td>
							<td><c:out value="${scholarship.schType eq 'SCORE' ? '성적' :scholarship.schType eq 'APPLY' ? '교내' :'추천'} "/></td>
							<td>${scholarship.schDetail}</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty scholarshipList }">
					<tr>
						<td colspan="4">게시글 정보가 없음.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
		</div>
	</div>
</div>

<!-- Center modal content -->
<div class="modal fade" id="btnModal" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myCenterModalLabel">해당 장학금유형을 수정/삭제 하시겠습니까?</h4>
				
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				
			</div>
			<div class="modalbodyBtn modal-body">
				<button type="button" onClick="updateType()"class="btn waves-effect waves-light btn-lg btn-primary">수정</button>
				<button type="button" onClick="deleteType()"class="btn waves-effect waves-light btn-lg btn-warning">삭제</button>
				<p class="pTag">(삭제시 해당 유형은 비활성으로 전환됩니다.)</p>	
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script>
function showModal(code){
	msgArea = $("#btnModal").modal();
	$("#schCode").text(code);
	msgArea.modal("show");
	
	msgArea.on("click",".msgBtn",function(){
		msgArea.modal("hide");
	});
};

function updateType(){
	let code = $("#schCode").text();
	location.href="${pageContext.request.contextPath }/admin/updateScholarForm.do?schCode="+code;
	
}

function deleteType(){
	let code = $("#schCode").text();
	location.href="${pageContext.request.contextPath }/admin/deleteScholarType.do?schCode="+code;
}


</script>
