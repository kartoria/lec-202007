<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<link rel="stylesheet" href="${cPath}/css/admin/student.css">
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 학생관리 > 학생조회
			</h5>
		</div>
	</div>
 	<div class="row mb-3 border-bottom border-admin p-0 my-2">
		 <div class="col-12">
			<h2 class="font-weight-bold">학생 정보 수정</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">학생정보를 수정할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-body">
					<form:form commandName="stuVO" id="stuUpdateForm" method="post" action="${cPath }/admin/updateStudent.do">
						<table class="table text-center">
							<tbody id="listBody">
								<tr>
									<td rowspan="4" colspan="2">
										<c:if test="${not empty memVO.memImg }">
											<img id="img" class="imgSize" src="${cPath}/memberImages/${memVO.memImg }" alt='${cPath}/images/noImage.png' />
										</c:if>
										<c:if test="${ empty memVO.memImg }">
											<img id="img" class="imgSize" src="${cPath}/images/noImg.png" />
										</c:if>
									</td>
									<td class="table-active"><p class="font-weight-bold">이름</p></td>
									<td>
										<form:input path="memName" class="form-control readable" value="${memVO.memName }"/> 
										<span><form:errors path="memName"  element="span" cssClass="text-danger" /></span>
									</td>
									<td class="table-active"><p class="font-weight-bold">학번</p></td>
									<td><form:input path="memId" cssClass="form-control" readonly="true" /></td>
									<td class="table-active"><p class="font-weight-bold">학년</p></td>
									<td ><fmt:formatNumber var="grd" value="${(stuVO.memGrd/2 + 0.5) - ((stuVO.memGrd/2 + 0.5)%1)}"/> 
											${grd }학년 </td>
								</tr>
								<tr>
									<td class="table-active"><p class="font-weight-bold">주민등록번호</p></td>
									<td>${stuVO.memReg1 } - ******* </td>
									<td class="table-active"><p class="font-weight-bold">학과</p></td>
									<td colspan="3"><input type="hidden" name="depNo" value="${stuVO.depNo}" required >
										<div class="float-left"><form:input path="depName" cssClass="form-control" readonly="true"  /></div>
										<div class="float-left"><button type="button" class="btn waves-effect waves-light btn-rounded btn-warning" data-toggle="modal"  data-target="#changeDepartment">학과변경</button></div>
									</td>
								</tr>
								<tr>
									<td class="table-active"><p class="font-weight-bold">전화번호</p></td>
									<td><form:input path="memTel" id="telId" cssClass="form-control"  />
										<span id="telText"><form:errors path="memTel" element="span" cssClass="text-danger"/></span>
									</td>
									<td class="table-active"><p class="font-weight-bold">이메일</p></td>
									<td><form:input path="memMail" id="mailId" cssClass="form-control" />
										<span><form:errors path="memMail" element="span" cssClass="text-danger"/></span>
									</td>
									<td class="table-active"><p class="font-weight-bold">학적상태</p></td>
									<td>
										<form:select path="memState" cssClass="form-control"  >
										<form:option value="ING">재학</form:option>
										<form:option value="END">졸업</form:option>
										<form:option value="REST">휴학</form:option>
										<form:option value="LEFT">자퇴</form:option>
										</form:select>	
										<form:errors path="memState" element="span" cssClass="text-danger" />	
									</td>
								</tr>
								<tr>
									<td class="table-active"><p class="font-weight-bold">주소</p></td>
									<td>${stuVO.memZip } ${stuVO.memAddr1 }</td>
									<td class="table-active"><p class="font-weight-bold">상세주소</p></td>
									<td colspan="3">${stuVO.memAddr2 }</td>
								</tr>
								<tr>
									<td colspan="3" class="table-active" ><p class="font-weight-bold">계좌</p></td>
									<td colspan="5">
										<div class="table-responsive">
											<table class="table">
												<tr>
													<th class="table-info text-center">은행</th>
													<th scope="col">${stuVO.memBank }</th>
													<th class="table-info text-center">예금주</th>
													<th scope="col">${stuVO.memName }</th>
													<th class="table-info text-center">계좌번호</th>
													<th scope="col">${stuVO.memAcn }</th>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</form:form>	
				</div>
			</div>
			<div class="text-right">
			<button type="button" id="resetBtn" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1">취소</button>
			<button type="button" id="updateBtn"  class="btn waves-effect waves-light btn-warning px-4 py-2 mx-1">수정</button>
			</div>							
		</div>
	</div>	
</div>
<!-- ============= 학과 수정 모달 ================ -->
<!-- ========================================= -->
<div id="changeDepartment" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="danger-header-modalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header modal-colored-header">
				<h4 class="modal-title font-weight-bold" id="modalLabel">학과 변경</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
			</div>
			<div class="modal-body d-flex">
				<select class="form-control w-50 mr-1" id="college" name="college" 
					onChange="changeCollege()" required>
					<option value="">단과대</option>
				</select>
				<select class="form-control w-50" id="department" name="depNo" required>
					<option value="">학과선택</option>
				</select>	
			</div>
			<div class="modal-footer" style="margin: auto;">
				<button type="button" class="btn btn-light" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-success" data-dismiss="modal" id="goUpBtn">변겅</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script>
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

$("#stuUpdateForm").validate(validateOptions);

$(function(){
	// 학부, 학적 코드 조회 (모달)
	$.ajax({
		url : "${cPath }/admin/getCollegeAndState.do"
		,method : "get"
		,dataType : "json"
		,success : function(data){
			makeCollegeSelect(data);
		}
	});
});

//학부, 학적상태 selectBox 생성
function makeCollegeSelect(data){
	let collegeList = data.collegeList;
	let stateList = data.stateList;
	let html = '<option value >학부선택</option>';
	for(let i = 0 ; i < collegeList.length ; i++){
		html += '<option value='+collegeList[i].code+' >'+collegeList[i].codeName+'</option>';
	}
	$("#college").html(html);
	
}

//학부 선택시
function changeCollege(){
	let collegeList = document.getElementById("college");
	let college = collegeList.options[collegeList.selectedIndex].value;
	$.ajax({
		url : "${cPath}/admin/getDepartment.do?college="+college
			,type : "get"
			,dataType : "json"
			,success : function(data){
				makeDepartSelect(data);
			}
	})
}

// 학과 selectBox 생성
function makeDepartSelect(data){
	let department = data;
	var html = '<option value >학과선택</option>';
	for(var i = 0 ; i < department.length ; i++){
		html += '<option value='+department[i].depNo+' >'+department[i].depName+'</option>';
	}
	$("#department").html(html);
	
}

//전화번호 체크
$("#telId").bind("keyup", function(event){
	let regNumber2 = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	let temp2 = $("#telId").val();
	if(!regNumber2.test(temp2)){
		$("#telId").val(temp2.replace(/[^0-9]/g,""));
	}
});


//취소 클릭시
$("#resetBtn").on("click", function(){
	location.href="${cPath}/admin/studentList.do";
});
//학과 변경 클릭시
$("#goUpBtn").on("click",function(){
	let depNo = $("#department option:selected").val();
	let depName = $("#department option:selected").text();

	$("input[name='depName']").val(depName);
	$("input[name='depNo']").val(depNo);
	
});
//수정 버튼클릭시
$("#updateBtn").on("click",function(){
	let emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	let mail=$("#mailId").val();
	if(mail!=null){
		if(!emailRule.test(mail)) {            
	        alert("이메일 형식에 맞게입력해주세요 (ex : mymail@naver.com )");
	        return false;
		}    
	}
        
        
	$("#stuUpdateForm").submit();
});

</script>							