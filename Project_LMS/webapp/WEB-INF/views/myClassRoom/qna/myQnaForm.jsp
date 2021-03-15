<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
   prefix="security"%>
<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />	 
<input type="hidden" name="lecCode" value="${lecCode }"/> 
<div class="container-fluid">
	<div class="row">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 내 강의실 > 학습 정보 > 질문 게시판
			</h5>
		</div>
	</div>
	<div class="row p-0 my-2">
		<div class="col-9 text-left ml-3">
			<h3 class="font-weight-bold">질문 등록</h3>
		</div>
	</div>
	<div class="card">
		<div class="card-body">
			<div class="table-responsive">
				<form:form id="qnaForm" action="${cPath }/myclass/${lecCode}/qnaBoInsert.do" commandName="board" method="post">
					<input type="hidden" name="memId" value="${authMember.memId }" />
					<input type="hidden" name="boGroupCode" value="QNA" />
					<form:hidden path="boNo"/>
				<table class="table table-bordered table-striped mb-0">
					<tbody>
						<tr>
							<th class="text-nowrap" scope="row"><form:label path="boTitle" cssClass="required">제목</form:label></th>
							<td colspan="5">
								<form:input path="boTitle" cssClass="form-control"
									id="placeholder" placeholder="제목을 입력하세요." required="required" />
							</td>
						</tr>
						<tr>
							<th class="text-nowrap" scope="row"><form:label path="boTitle" cssClass="required">내용</form:label></th>
							<td colspan="5">
							<form:textarea path="boContent"
								required="required" cssClass="form-control" rows="3"
								placeholder="내용을 입력하세요." id="lec_content"></form:textarea></td>
							<!-- CKEDITER -->
						</tr>
						<tr>
							<th class="text-nowrap" scope="row" colspan="2">
							<div class="text-right">
								<button type="submit" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-1" id="insertBtn">등록</button>
								<button type="button" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1" id="resetBtn">취소</button>
							</div>
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

$("#qnaForm").validate(validateOptions);

CKEDITOR.replace("boContent", {
	 filebrowserImageUploadUrl: '${cPath}/board/imageUpload.do?command=QuickUpload&type=Images'
});

//수정시 action 변겨
let command =  '${command}';
let lecCode = $("input[name='lecCode']").val();
if(command=="MODIFY"){
   $("#qnaForm").attr("action","${cPath}/myclass/"+lecCode+"/qnaBoUpdate.do");
}
</script>