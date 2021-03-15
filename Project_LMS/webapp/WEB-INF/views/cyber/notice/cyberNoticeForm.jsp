<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />	
<form:form commandName="board" id="boardForm" action="${cPath}/cyber/notice/insert.do" method="post" enctype="multipart/form-data">
	<!-- hidden 값들 -->
	<form:hidden path="boNo" />
	<form:hidden path="boParent"/>
	<input type="hidden" name="memId" value="${authMember.memId }" />
	<input type="hidden" name="boGroupCode" value="CYNTC" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-12">
				<h5 class="ml-2">
					<i class="fas fa-home"></i> 사이버 캠퍼스 > 공지 사항
				</h5>
			</div>
		</div>
		<div class="row p-0 my-2">
			<div class="col-9 text-left">
				<h3 class="font-weight-bold">공지 등록</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<div class="table-responsive border border-8">
					<table class="table table-bordered table-striped mb-0">
						<tbody>
							<tr>
								<th class="text-nowrap" scope="row"><form:label
										path="boTitle" cssClass="required" onClick="putData()">제목</form:label></th>
								<td colspan="5"><form:input path="boTitle"
										cssClass="form-control" required="required" /> <form:errors
										path="boTitle" element="span" cssClass="error" /></td>
							</tr>
							<tr>
								<th class="text-nowrap" scope="row"><form:label
										path="boContent" cssClass="required">내용</form:label></th>
								<td colspan="5">
										<textarea rows="3" name="boContent">
											1. 수강신청 확인 및 변경기간: 2021. 3. 2.(화) 09:00 ~ 3. 8.(월) 18:00
											   ※신입생(추가합격자 포함), 재입학자, 편입생, 시간제등록생은 부여된 학번으로 통합정보시스템에 로그인하여 수강신청 확인 및 변경(ID: 학번, PW(최초): 학번과 동일)
											 
											2. 접속요령
											    SmartLMS 홈페이지(수강신청 바로가기를 통해 접속)
											     - 홈페이지접속 ⇒ -「수강신청 이동」클릭
											       ※ 학번으로 로그인 하여 수강신청(최초 비밀번호: 주민등록번호 앞 6자리)
											         ※ 정상적인 방법 이외의 방법으로 수강신청을 시도할 때에는 접속이 불가함
											       ※ 아이디 및 비밀번호는 통합정보시스템 아이디, 비밀번호와 동일
											
											※자세한 사항은 첨부파일을 참고바랍니다.
										</textarea>
										<form:errors
										path="boContent" element="span" cssClass="error" /></td>
							</tr>
							<tr>
								<th class="text-nowrap" scope="row">첨부 파일</th>
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
		</div>
	</div>

</form:form>
<script type="text/javascript">
$(function (){
	$("#resetBtn").on("click", function(){
		location.href = "${cPath}/cyber/notice/list.do";
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

// 수정시 action 변겨
let command =  '${command}';
if(command=="MODIFY"){
   $("#boardForm").attr("action","${cPath}/cyber/notice/update.do");
}

function putData(){
	$("input[name='boTitle']").val("2021학년도 제1학기 일반대학 수강신청 확인 및 변경기간 안내");
}

</script>