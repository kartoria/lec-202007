<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />
<input type="hidden" name="lecCode" value="${lecCode }"/>	
<form:form commandName="board" id="boardForm" action="${cPath}/myclass/${lecCode}/notiInsert.do" method="post" enctype="multipart/form-data">
	<!-- hidden 값들 -->
	<form:hidden path="boNo" />
	<form:hidden path="boParent"/>
	<input type="hidden" name="memId" value="${authMember.memId }" />
	<input type="hidden" name="boGroupCode" value="NTC" />
	<div class="container-fluid">
	<div class="row">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 내 강의실 > 학습 정보 > 학습 공지
			</h5>
		</div>
	</div>
	<div class="row p-0 my-2">
		<div class="col-9 text-left ml-3">
			<h3 class="font-weight-bold">공지 등록</h3>
		</div>
	</div>
		<div class="table-responsive">
			<table class="table table-bordered table-striped mb-0">
				<tbody>
					<tr>
						<th class="text-nowrap" scope="row" ><form:label path="boTitle" cssClass="required">제목</form:label></th>
						<td colspan="5"><form:input path="boTitle"
								cssClass="form-control" required="required" /> <form:errors
								path="boTitle" element="span" cssClass="error" /></td>
					</tr>
					<tr>
						<th class="text-nowrap" scope="row"><form:label path="boContent" cssClass="required">내용</form:label></th>
						<td colspan="5"><form:textarea path="boContent"
								required="required" cssClass="form-control" rows="3"
								placeholder="내용을 입력하세요." id="lec_content"></form:textarea></td>
					</tr>
					<tr>
						<th class="text-nowrap font-weight-bold" scope="row">첨부 파일</th>
						<td colspan="5">
							<div class="card-body">
								<c:if test="${not empty board.attachList }">
									<c:forEach items="${board.attachList }" var="attach"
										varStatus="idx">
										<span title="다운로드:"
											${attach.attDowncount }" class="attachSpan">
											<button type="button" class=" btn btn-danger btn-circle"
												data-att-no="${attach.attNo }" id="delAtt">
												<i class="fas fa-minus"></i>
											</button> ${attach.attFilename }
										</span>
									</c:forEach>
								</c:if>
								<form:input type="file" id="inputGroupFile04" path="boFiles" />
							</div> <form:errors path="boFiles" element="span" cssClass="error" />
						</td>
					</tr>
					<tr>
						<th class="text-nowrap" scope="row" colspan="2">
							<div class="text-right">
								<button type="submit"
									class="btn waves-effect waves-light btn-primary px-4 py-2 mx-1"
									id="insertBtn">등록</button>
								<button type="button"
									class="btn waves-effect waves-light btn-light px-4 py-2 mx-1"
									id="resetBtn">취소</button>
							</div>
						</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</form:form>
<script type="text/javascript">
$(function (){
	// 수정시 action 변경
	let lecCode = $("input[name='lecCode']").val();
	let command =  '${command}';
	if(command=="MODIFY"){
	   $("#boardForm").attr("action","${cPath}/myclass/"+lecCode+"/notiUpdate.do");
	}
	
	$("#resetBtn").on("click", function(){
		location.href = "${cPath }/myclass/"+lecCode+"/noticeList.do";
	});
});	
let boardForm = $("#boardForm");
$("#delAtt").on("click", function() {
	let attNo = $(this).data("attNo");
	// 삭제버튼 누른 파일을 delAttNos 로 가져가기
	boardForm.append(
			$("<input>").attr({
				"type":"hidden"
				, "name":"delAttNos"
			}).val(attNo)
	);
	$(this).parent("span:first").hide();
});

// ======== validator ========
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

CKEDITOR.replace("boContent", {
	 filebrowserImageUploadUrl: '${cPath}/board/imageUpload.do?command=QuickUpload&type=Images'
});

</script>