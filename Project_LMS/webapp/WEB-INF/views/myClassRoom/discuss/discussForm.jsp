<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="validator"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<style type="text/css">
	.container-fluid{
		min-width:1200px;
		max-width:1500px;
	}
</style>
<div class="container-fluid">
	<div class="card">
		<div class="card-body">
			<c:if test="${boardVO.flag eq 'create'}">
				<h4 class="card-title">토론 주제 등록</h4>
				<c:set value="${cPath}/myclass/${lecCode}/disInsert.do" var="goUrl"/>
			</c:if>
			<c:if test="${boardVO.flag eq 'modify'}">
				<h4 class="card-title">토론 주제 수정</h4>
				<c:set value="${cPath}/myclass/${lecCode}/disUpdate.do" var="goUrl"/>
			</c:if>
			<p class="text-muted font-13">학생들이 자유롭게 토론하도록 토론 주제를 등록할 수 있습니다.</p>
			<div class="table-responsive">
			<form:form commandName="boardVO" id="boardForm" action="${goUrl }" >
				<form:hidden path="boNo"/>
				<form:hidden path="memId"/>
				<form:hidden path="boGroupCode"/>
				<form:hidden path="lecCode"/>
				<form:hidden path="flag"/>
				<table class="table table-bordered table-striped mb-0">
					<tbody>
						<tr>
							<th class="text-nowrap" scope="row">토론 주제</th>
							<td colspan="5">
								<form:input path="boTitle" type="text" cssClass="form-control" placeholder="제목을 입력하세요."/>
								<form:errors path="boTitle" element="span" cssClass="text-danger"/>
							</td>
							
						</tr>
						<tr>
							<th class="text-nowrap" scope="row">내용</th>
							<td colspan="5">
								<form:textarea path="boContent" cssClass="form-control" rows="3" placeholder="내용을 입력하세요."/>
								<form:errors path="boContent" element="span" cssClass="text-danger"/>
							</td>
						</tr>
						<tr>
							<th class="text-nowrap" scope="row" colspan="2">
								<input type="submit" value="등록" class="btn waves-effect waves-light btn-rounded btn-info"/>
								<button type="button" class="btn waves-effect waves-light btn-rounded btn-light" id="resetBtn">취소</button>
							</th>
						</tr>
					</tbody>
				</table>
			</form:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function (){
	$("#resetBtn").on("click", function(){
		window.history.back();
	});
});	

CKEDITOR.replace("boContent", {
	 filebrowserImageUploadUrl: '${cPath}/board/imageUpload.do?command=QuickUpload&type=Images'
});

$("#fileArea").on("click", ".plusBtn", function(){
	let clickDiv = $(this).parents("div.input-group");
	let newDiv = clickDiv.clone();
	let fileTag = newDiv.find("input[type='file']");
	fileTag.val("");
	clickDiv.after(newDiv);		
});

let boardForm =$("#boardForm");
$(".delAtt").on("click", function(){
	let att_no = $(this).data("attNo");
	boardForm.append(
		$("<input>").attr({
			"type":"text"
			, "name":"delAttNos"
		}).val(att_no)
	);
	$(this).parent("span:first").hide();
});

</script>