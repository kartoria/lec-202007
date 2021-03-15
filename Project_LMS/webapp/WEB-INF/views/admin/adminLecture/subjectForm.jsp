<%--
@author 조예슬
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="${cPath}/css/admin/lecture.css">
<title>강의 등록</title>
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 교과목관리 > 과목관리
			</h5>
		</div>
	</div>
	<div class="row mb-3 border-bottom border-admin p-0 my-2">
		<div class="col-12">
			<c:set var="type" value="${TYPE}"/>
			<input type="hidden" name="type" value=${TYPE }>
			<c:if test="${type eq 'MODIFY' }">
			<h2 class="font-weight-bold">과목 수정</h2>
			</c:if>
			<c:if test="${type eq 'INSERT' }">
			<h2 class="font-weight-bold">과목 신규등록</h2>
			</c:if>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">과목을 등록/수정 할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-12 text-center">
			<div class="card">
				<div class="card-body">
					<form:form method="post" commandName="subVO" id="insertSubForm" action="${cPath }/admin/insertSubject.do">
						<input type="hidden" name="subCode" value="${subVO.subCode }">
						<div class="row float-left pr-5 w-50">
							<table class="mainTable table text-center">
							<c:set var="type" value="${TYPE}"/>
								<c:if test="${type eq 'MODIFY' }">
									<tr>
										<td class="table-active"><span class="text-danger">* </span>학부</td>
										<td>
											<input type="text" name="colCode" value="${subVO.codeName}" class="form-control" readonly>
										</td>
									</tr>
									<tr>
										<td class="table-active"><span class="text-danger">* </span>학과</td>
										<td>
											<input type="text" name="depNo" value="${subVO.depNo}" class="form-control" readonly>
										</td>
									</tr>
								</c:if>
								<c:if test="${type eq 'INSERT' }">
									<tr>
									<td class="table-active"><span class="text-danger">* </span>학부</td>
									<td>
										<select  id="college" name="colCode" onChange="selectCollege()" class="form-control readable" >
											<option value="0">학부 선택</option>
										</select>
									</td>
									</tr>
									<tr>
										<td class="table-active"><span class="text-danger">* </span>학과</td>
										<td>
											<select class="form-control readable" name="depNo" id="department" >
												<option>학과 선택</option>
											</select>
										</td>
									</tr>
								</c:if>
								<tr>
									<td class="table-active"><span class="text-danger">* </span>과목명</td>
									<td>
										<form:input type="text" id="subName" path="subName" class="form-control" required="required" />
										<form:errors path="subName" element="span" cssClass="error"/>
									</td>
								</tr>
								<tr>
									<td class="table-active"><span class="text-danger">* </span>학점</td>
									<td>
										<form:input type="number" min="0" max="10" maxlength="2" path="subCredit" oninput="maxLengthCheck(this)" required="required" class="form-control"/>
										<form:errors path="subCredit" element="span" cssClass="error"/>
									</td>
								</tr>
								<tr>
									<td class="table-active"><span class="text-danger">* </span>이수구분</td>
									<td>
										<form:select class="form-control" path="subDetail">
											<form:option value="전필">전필</form:option>
											<form:option value="교필">교필</form:option>
										</form:select>
										<form:errors path="subDetail" element="span" cssClass="error"/>
									</td>
								</tr>
								<tr>
									<td class="table-active">개설년도</td>
									<td>
										<input type="text" id="subDate" name="subDate" value="${currentSmst.codeName }" class="form-control" readonly>
										<form:errors path="subDate" element="span" cssClass="error"/>
									</td>
								</tr>
							</table>
						</div>
						<div class="row float-left w-50 border border-secondary">
							<table class="table text-center">
								<tbody>
									<tr>
										<td colspan="4"><span class="text-danger">&lt등록 예시&gt</span></td>
									</tr>
									<tr>
										<td class="table-active"><p class="font-weight-bold">과목코드</p></td>
										<td>10001</td>
										<td class="table-active"><p class="font-weight-bold">과목명</p></td>
										<td>한국 문학과 사회</td>
									</tr>
									<tr>
										<td class="table-active"><p class="font-weight-bold">학점</p></td>
										<td>3 학점</td>
										<td class="table-active"><p class="font-weight-bold">이수구분</p></td>
										<td>교선</td>
									</tr>
									<tr>
										<td class="table-active"><p class="font-weight-bold">개설년도</p></td>
										<td colspan="3">2000 년</td>
									</tr>	
									<tr>
										<td class="table-active"><p class="font-weight-bold">학부/학과</p></td>
										<td colspan="3">
											<div id="depTable" class="table-responsive">
												<table class="table">
													<tbody>
														<tr>
															<th class="table-info text-center" >단과대</th>
															<th class="table-info text-center" >학과</th>
														</tr>
														<tr>
															<td class="text-center">인문대학</td>
															<td class="text-center">국어국문학과</td>
														</tr>
													</tbody>
												</table>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</form:form>
				</div>	
			</div> 
			<div id="btnArea">
				<c:if test="${type eq 'MODIFY' }">
					<button type="button" id="updateBtn" class="btn waves-effect waves-light btn-warning px-4 py-2 mx-1">수정</button>
					<button type="button" id="cancelBtn" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1">취소</button>
				</c:if>
				<c:if test="${type eq 'INSERT' }">
					<button id="insertSubjectBtn" type="button" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-1">등록</button>
					<button type="button" id="resetBtn" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1">목록</button>
				</c:if>
			</div>
		</div>
	</div>
</div>	
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

$("#insertSubForm").validate(validateOptions);

// 숫자 체크 ===========================================================================
function maxLengthCheck(object){
    if (object.value.length > object.maxLength){
        object.value = object.value.slice(0, object.maxLength);
    }    
}


$(function(){
// 학부 코드 가져오는 ajax ===========================================================================
	$.ajax({
		url : "${cPath}/admin/insertLecture.do"
			,type : "get"
			,dataType : "json"
			,success : function(data){
				makeCollegeSelect(data);
			}
	});
	
});
// 학부 코드 select 생성 ===========================================================================
function makeCollegeSelect(data){
	console.log(data);
	let colhtml = "<option value='0'>학부선택</option>";
	for(let i = 0 ; i < data.collegeList.length ; i++){
		colhtml += '<option value='+data.collegeList[i].code+'>'+data.collegeList[i].codeName+'</option>';
	}
	$("#college").html(colhtml);
	let CurrentSmst = data.currentSmst.codeName;
	let thisYear = CurrentSmst.substr(0,4);
	$("#subDate").val(thisYear);
	
}

// 학부 선택시 학과가져오기 ===========================================================================
function selectCollege(){
	$(":input").prop("disabled", false);
	let collegeList = document.getElementById("college");
	let college = collegeList.options[collegeList.selectedIndex].value;
	if(college!=null || college!=0 || college!=undefined){
		$.ajax({
			url : "${cPath}/admin/selectLecCollege.do?college="+college
				,type : "get"
				,dataType : "json"
				,success : function(data){
					makeDepartmentSelect(data);
				}
		});
	}
};

// 학과 옵션 만들기 ===========================================================================
function makeDepartmentSelect(data){
	let dephtml = "<option value='0'>학과선택</option>";
	for(let i = 0 ; i < data.department.length ; i++){
		dephtml += '<option value='+data.department[i].depNo+'  >'+data.department[i].depName+'</option>';
	}
	$("#department").html(dephtml);
	
};


// 등록 버튼 클릭시 ===========================================================================
$("#insertSubjectBtn").on("click",function(e){
	e.preventDefault();
	$("#insertSubForm").submit();
	
	return false;
});

// 수정 버튼 클릭시 ====================================================
$("#updateBtn").on("click",function(e){
	e.preventDefault();
	
	$("#insertSubForm ").attr("action","${cPath}/admin/updateSubject.do");
	
	$("#insertSubForm").submit();
	
	return false;
});

$("#resetBtn").on("click",function(){
	location.href="${cPath}/admin/subjectList.do";
});
$("#cancelBtn").on("click",function(){
	let subCode = $("input[name='subCode']").val();
	location.href="${cPath}/admin/subjectView.do?subCode="+subCode;
});

</script>
