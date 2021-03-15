<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<input type="hidden" name="lecCode" value="${lecCode }"/>
<div class="container-fluid">
	<!-- *************************************************************** -->
	<!-- End Top Leader Table -->
	<!-- *************************************************************** -->
	<div class="card-body">
		<div class="row">
			<div class="col-12">
				<h5 class="ml-2">
					<i class="fas fa-home"></i> 내 강의실 > 학습 정보 > 학습 공지
				</h5>
			</div>
		</div>
		<div class="row p-0 my-2">
			<div class="col-9 text-left">
				<h2 class="font-weight-bold">공지 사항</h2>
			</div>
		</div>
		<div class="col-12">
			<div class="card">
				<div class="table-responsive">
					<table class="table">
						<tbody id="listBody">
							<tr>
								<td class="table-active font-weight-bold">제목</td>
								<td colspan="5">${board.boTitle }</td>
							</tr>
							<tr>
								<td class="table-active font-weight-bold">작성자</td>
								<td>${board.memName }</td>
								<td class="table-active font-weight-bold">작성 날짜</td>
								<td>${fn:substring(board.boDate,0,10)}</td>
								<td class="table-active font-weight-bold">조회수</td>
								<td>${board.boHit }</td>
							</tr>
							<tr>
								<td colspan="6" style="height: 400px;">${board.boContent }
								</td>
							</tr>
							<tr>
								<td class="table-active font-weight-bold">첨부파일</td>
								<td colspan="5"><c:if test="${not empty board.attachList }">
										<c:forEach items="${board.attachList }" var="attach"
											varStatus="idx">
											<c:url value="${cPath}/board/download.do" var="downloadURL">
												<c:param name="what" value="${attach.attNo }" />
											</c:url>
											<a href="${downloadURL }"> ${attach.attFilename } </a>
										</c:forEach>
									</c:if></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row my-3">
				<div class="col-12 d-flex justify-content-end align-items-center">
					<div class="form-inline">
						<div class="form-group">
							<button type="button"
								class="btn waves-effect waves-light btn-light px-4 py-2 mx-2"
								id="resetBtn">목록</button>
							<security:authorize access="hasRole('ROLE_PROFESSOR')">
								<security:authentication property="principal" var="principal" />
								<c:set var="authMember" value="${principal.realMember }" />
								<c:url value="/myclass/${lecCode}/notiUpdateForm.do"
									var="updateURL">
									<c:param name="boNo" value="${board.boNo }" />
								</c:url>
								<button type="button"
									class="btn waves-effect waves-light btn-warning px-4 py-2 mx-2"
									id="updateBtn">수정</button>
								<button type="button"
									class="btn waves-effect waves-light btn-danger px-4 py-2 mx-2"
									id="delBtn">삭제</button>
							</security:authorize>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- ============= 삭제 확인 모달 ================ -->
<!-- ========================================= -->
<div id="delModal" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="danger-header-modalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header modal-colored-header bg-danger">
				<h4 class="modal-title font-weight-bold" id="danger-header-modalLabel">삭제 여부 확인</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
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
<form:form id="delForm" method="post" action="${cPath }/myclass/${lecCode}/notiDelete.do" commandName="board">
	<form:hidden path="boNo"/>
</form:form>
<script type="text/javascript">

$(function() {
	let lecCode = $("input[name='lecCode']").val();
	$("#updateBtn").on("click", function() {
		location.href="${updateURL }";
	});	
	$("#delBtn").on("click", function() {
		$("#delModal").modal();
	});	
	$("#goDelBtn").on("click", function() {
		$("#delForm").submit();
	});	
	$("#resetBtn").on("click", function() {
		location.href = "${cPath }/myclass/"+lecCode+"/noticeList.do";
	});	
});
</script>