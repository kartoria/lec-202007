<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />
<form:form commandName="board"
	action="${cPath}/cyber/qna/insert.do" id="qnaForm" method="post">
	<!-- hidden 값들 -->
	<form:hidden path="boNo" value="${board.memberVO.boNo}"/>
	<form:hidden path="boParent" value="${param.parent }" />
	<input type="hidden" name="memId" value="${authMember.memId }" />
	<input type="hidden" name="boGroupCode" value="CYQNA" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-12">
				<h3 class="ml-2">
					<i class="fas fa-home"></i> 사이버 캠퍼스 > Q&A
				</h3>
			</div>
		</div>  
		<div class="row my-3 border-bottom border-cyber">
			<div class="col-12 d-flex justify-content-start align-items-center">
				<h2 class="font-weight-bold">질문글 등록</h2>
			</div>
		</div>
		<div class="row mt-3 mb-5">
			<div class="col-12 d-flex justify-content-start align-items-center">
				<h5 class="ml-2">Q&A 질문글을 등록할 수 있습니다.</h5>
			</div>
		</div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped mb-0">
				<tbody id="listbody">
					<tr>
						<th class="text-nowrap" scope="row">제목</th>
						<td colspan="5"><form:input path="boTitle"
								class="form-control" id="boTitle" required="required"/>
					</tr>
					<tr>
						<th class="text-nowrap" scope="row">비밀글 여부</th>
						<td colspan="3">
							<div class="btn-group" data-toggle="buttons">
								<label class="btn btn-light active"> <form:radiobutton
										path="boSecret" class="custom-control-input" label="공개글"
										value="Y" id="boPublic" required="required" checked="checked" />
								</label> <label class="btn btn-light"> <form:radiobutton
										path="boSecret" class="custom-control-input" label="비밀글"
										value="N" id="boSecret" required="required"/>
								</label>
							</div>
						</td>
					</tr>
					<tr>
						<th class="text-nowrap" scope="row">내용</th>
						<td colspan="5"><form:textarea path="boContent"
								 id="boContent" rows="3" required="required"></form:textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row my-3">
			<div class="col-12 d-flex justify-content-end align-items-center">
				<div class="form-inline">
					<div class="form-group">
						<th class="float-right" scope="row" colspan="2">
							<form:button type="submit"
								class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2">등록</form:button>
							<form:button type="reset" 
								class="btn waves-effect waves-light btn-light px-4 py-2 mx-2"
								id="resetBtn">취소</form:button>
						</th>
					</div>
				</div>
			</div>
		</div>	
	</div>
</form:form>

<!-- 내용에 값을 입력하지 않았을 때, 나오는 모달창 -->
<input type="hidden" id="modalContentValidate" class="btn btn-secondary"
	data-toggle="modal" data-target="#centermodalAccpt" value="숨겨진모달">
<div class="modal fade" id="centermodalAccpt" tabindex="-1"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<h5 style="text-align: center;">내용을 입력해주세요</h5>
			</div>
		</div>
	</div>
</div>

<!-- 제목에 값을 입력하지 않았을 때, 나오는 모달창 -->
<input type="hidden" id="modalTitleValidate" class="btn btn-secondary"
	data-toggle="modal" data-target="#centermodalAccpt" value="숨겨진모달">
<div class="modal fade" id="centermodalAccpt" tabindex="-1"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<h5 style="text-align: center;">내용을 입력해주세요</h5>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		let qnaForm = $("#qnaForm");
		qnaForm.submit(function(e) {
			var messageLength = CKEDITOR.instances['boContent'].getData()
					.replace(/<[^>]*>/gi, '').length;
			if (!messageLength) {
				document.getElementById("modalContentValidate").click();
				e.preventDefault();
			}
		});
		qnaForm.validate({
			onsubmit : true,
			onfocusout : function(element, event) {
				return this.element(element);
			},
			errorPlacement : function(error, element) {
				error.appendTo($(element).parents("td:first"));
			}
		});
		// 수정시 action 변경
		let command = '${command}';
		if (command == "MODIFY") {
			qnaForm.attr("action", "${cPath}/cyber/qna/update.do");
		}

	});
	CKEDITOR.replace("boContent", {
		 filebrowserImageUploadUrl: '${cPath}/board/imageUpload.do?command=QuickUpload&type=Images'
	});
	$("#resetBtn").on("click", function(){
		location.href="${cPath}/cyber/qna/list.do";
	});
</script>