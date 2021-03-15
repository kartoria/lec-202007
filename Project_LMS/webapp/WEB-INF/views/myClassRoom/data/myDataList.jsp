<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 내 강의실 > 학습목차 > 강의 자료
			</h5>
		</div>
	</div>
	<div class="row border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">강의 자료</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">학습에 관한 강의 자료를 등록할 수 있습니다.</h5>
		</div>
	</div>
	
	<!-- ===================== 교수만 보이게 ======================= -->
	<security:authorize access="hasRole('ROLE_PROFESSOR')">
	<div class="row my-3">
		<div class="col-12">
			<a id="goModal" href="javascript:void(0);">
				<div class="card">
					<div class="card-body d-flex justify-content-center align-items-center">
						<button type="button" class="btn btn-primary btn-circle">
							<i class="fa fa-plus"></i>
						</button>
					</div>
				</div>
			</a>
		</div>
	</div>
	</security:authorize>
		
	<c:if test="${empty boardList }">
	<div class="row my-3">
		<div class="col-12">
			<security:authorize access="hasRole('ROLE_STUDENT')">
				<div class="card">
					<div class="card-body">
						<h3 class="mb-0 font-weight-bold text-center">강의 자료가 없습니다.</h3>
					</div>
				</div>
			</security:authorize>
		</div>
	</div>
	</c:if>
	
	
	<c:forEach items="${boardList}" var="board" varStatus="status">
	<c:if test="${status.index mod 2 eq 0}">
	<div class="row d-flex align-items-center">
	</c:if>
		<div class="col-6">
			<div class="card">
				<div class="card-body">
					<div class="row d-flex justify-content-between">
						<div class="col-auto flex-grow-1 px-0">
						<c:forEach items="${board.attachList}" var="attach" varStatus="idx">
							<c:url value="/board/download.do" var="downloadURL">
								<c:param name="what" value="${attach.attNo}" />
							</c:url>
							<a href="${downloadURL}">
								<button type="button" class="btn btn-success btn-circle ${idx.first ? "" : "ml-3"}"><i class="fa fa-link"></i></button>
								${attach.attFilename}
							</a>
						</c:forEach>
						</div>
						<security:authorize access="hasRole('ROLE_PROFESSOR')">
							<div class="col-auto flex-fill px-0 text-right">
								<button type="button" id="delBtn" class="btn btn-danger btn-circle"  data-bono="${board.boNo }" data-toggle="tooltip" title="파일 삭제" data-placement="top">
									<i class="fa fa-minus"></i>
								</button>
								<button type="button" id="updateBtn" class="btn btn-warning btn-circle" data-bono="${board.boNo }" data-toggle="tooltip" title="파일 수정" data-placement="top">
									<i class="fa fa-undo"></i>
								</button>
							</div>
						</security:authorize>
					</div>
					<!-- 			               	<input type="hidden" name=lecture /> -->
					<input type="hidden" class="boardNo" name="boNo" value="${board.boNo }" />
					<div class="row my-3">
						<div class="col-12">
							<h4 class="card-text text-dark font-weight-bold">${board.boTitle }</h4>
						</div>
					</div>
					<div class="row border p-2">
						<div class="col-12" style="height:100px; overflow-y:auto">
							<h5 class="card-text">${board.boContent }</h5>
						</div>
					</div>
				</div>
			</div>
		</div>
	<c:if test="${status.index mod 2 eq 1 || status.last}">
	</div>
	</c:if>
	</c:forEach>
</div>
<!-- ============== 강의 자료 등록 모달 =================== -->
<!-- ================================================ -->
<form:form modelAttribute="board" id="boardForm" action="${cPath}/myclass/${lecCode}/dataInsert.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="boNo" value="${board.boNo }" />
	<input type="hidden" name="boGroupCode" value="REF">
	<input type="hidden" name="lecCode" value="${lecCode }">
	<input type="hidden" name="memId" value="${authMember.memId }">
	<div id="uploadModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="danger-header-modalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" style="width: 50%">
			<div class="modal-content">
				<div class="modal-header modal-colored-header bg-primary">
					<h4 class="modal-title font-weight-bold" id="danger-header-modalLabel">강의 자료 등록</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				</div>
				<div class="modal-body ml-5">
					<table>
						<tbody>
							<tr>
								<td><h4 class="font-weight-bold">제목</h4></td>
								<td id="title"><form:input path="boTitle" id="example-input-large" cssClass="form-control form-control-lg" placeholder="제목을 입력하세요" required="required" cssStyle="width:150%;" /></td>
							</tr>
							<tr>
								<td><h4 class="font-weight-bold">자료 내용</h4></td>
								<td><form:textarea path="boContent" cssClass="form-control" rows="3" placeholder="자료 내용을 입력하세요" required="required" cssStyle="width:150%;height: 200px;" /></td>
							</tr>
							<tr>
								<td><h4 class="font-weight-bold">강의 자료</h4></td>
								<td><form:input type="file" path="boFiles" id="inputGroupFile04" required="required" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer modal-lg" style="margin: auto;">
					<button type="button" class="btn btn-light" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary" id="insertBtn">등록</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</form:form>
<!-- ============== 강의 자료 수정 모달 =================== -->
<!-- ================================================ -->
<form:form method="post" commandName="board" id="boardUpdate" enctype="multipart/form-data" action="${cPath}/myclass/${lecCode}/dataUpdate.do">
	<input type="hidden" name="boGroupCode" value="REF">
	<input type="hidden" name="lecCode" value="${lecCode }">
	<input type="hidden" name="memId" value="${authMember.memId }">
	<div id="updateModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="danger-header-modalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" style="width: 50%">
			<div class="modal-content">
				<div class="modal-header modal-colored-header bg-primary">
					<h4 class="modal-title font-weight-bold" id="danger-header-modalLabel">강의 자료 수정</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer modal-lg" style="margin: auto;">
					<button type="button" class="btn btn-light" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary" id="upBtn">수정</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</form:form>
<!-- ============= 삭제 확인 모달 ================ -->
<!-- ========================================= -->
<div id="delModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="danger-header-modalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header modal-colored-header bg-danger">
				<h4 class="modal-title font-weight-bold" id="danger-header-modalLabel">삭제 여부 확인</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<h4 class="font-weight-bold">정말 해당 게시글을 삭제하시겠습니까?</h4>
			</div>
			<div class="modal-footer" style="margin: auto;">
				<button type="button" class="btn btn-light" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-danger" id="goDelBtn">삭제</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<form:form id="delForm" commandName="board" method="post" action="${cPath}/myclass/${lecCode}/dataDel.do">
	<input type="hidden" name="boNo" value="${board.boNo }" />
</form:form>
<script type="text/javascript">
$(function(){
	// 부트스트랩 파일 경로 떼기
// 	$("#inputGroupFile01").on("click", function() {
// 		console.log("hello");
// 	});
// 	if($('div label').text()!="") $('label').text($('label').text().split('\\').reverse()[0]);
	let fileAddCard = $("#goModal").children(".card");
	$("#goModal").on("click", function() {
		$("#uploadModal").modal();
	}).hover(
		function(){
			fileAddCard.addClass("bg-light");
		},
		function(){
			fileAddCard.removeClass("bg-light");
		}
	);
	
	$("#insertBtn").on("click", function() {
		$("#boardForm").submit();	
	});
	
	$("#upBtn").on("click", function() {
		$("#boardUpdate").submit();	
	});
	
	let updateModal = $("#updateModal").on("hidden.bs.modal", function(){
		$(this).find(".modal-body").empty();
	});
	$(".btn-warning").on("click", function(){
		let lecCode= '${lecCode}';
		let boNo = $(this).data("bono");
		console.log(boNo);
		updateModal.find(".modal-body").load("${cPath}/myclass/"+lecCode+"/dataUpdateForm.do?boNo="+boNo, function(){
			updateModal.modal("show");
		})
	});
	$(".btn-danger").on("click", function(){
		let boNo = $(this).data("bono");
		$("#delForm input").attr("value",boNo);
		$("#delModal").modal();
	});
	
	$("#goDelBtn").on("click", function() {
		$("#delForm").submit();
	});	
	
	const validateOptions = {
	        onsubmit:true,
	        onfocusout:function(element, event){
	           return this.element(element);
	        },
	        errorPlacement: function(error, element) {
	           element.tooltip({
	              title: error.text()
	              , placement: "right"
	              , trigger: "manual"
	              , delay: { show: 500, hid: 100 }
	           }).on("shown.bs.tooltip", function() {
	              let tag = $(this);
	              setTimeout(() => {
	                 tag.tooltip("hide");
	              }, 3000)
	           }).tooltip('show');
	          }
	     }

	$("#boardForm").validate(validateOptions);
	$("#boardUpdate").validate(validateOptions);
	
	
});
</script>