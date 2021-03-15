<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<link rel="stylesheet" href="${cPath}/css/admin/scholarship.css">
<title>장학유형 등록</title>
<form:form id="schForm" action="${cPath}/admin/insertScholarType.do" commandName="schVO" method="post" >
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 장학관리 > 장학금 유형 관리
			</h5>
		</div>
	</div>
	<div class="row mb-3 border-bottom border-admin p-0 my-2">
		<div class="col-12">
			<c:set var="type" value="${TYPE }"/>
	        <c:if test="${type eq 'MODIFY' }">
	           <h2 class="font-weight-bold">장학유형 수정</h2>
	        </c:if>
	        <c:if test="${type ne 'MODIFY' }">
	           <h2 class="font-weight-bold">장학유형 등록</h2>
	        </c:if> 
        </div> 
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">장학 유형 등록/수정 할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-body row d-inline-flex">
					<div class="col-6">
						<table id="scholarTable" class="table text-center bd-gray">
							<tbody>
								<tr>
									<td class="table-active">장학코드 :</td>
									<td >
									<input type="text" class="form-control"
										name="schCode" value="${schVO.schCode }"  readonly>
									</td>
								</tr>
								<tr>
									<td class="table-active"><span class="text-danger">*</span>장학명 :</td>
									<td>
									<form:input path="schName" class="form-control" required="required" />	
									<form:errors path="schName" element="span" cssClass="error" placeholder="15자 이내로 입력하세요." />	
									</td>
								</tr>
								<tr>
									<td class="table-active"><span class="text-danger">*</span>장학유형 :</td>
									<td>
									<select name="schType" class="form-control" required >	
									<option value="">유형선택</option>		
									<option value="APPLY" <c:out value="${schVO.schType eq 'APPLY' ? 'selected' : ''} "/>>교내장학금</option>
									<option value="RECOM" <c:out value="${schVO.schType eq 'RECOM' ? 'selected' : ''} "/>>추천장학금</option>
									</select>		
									<form:errors path="schType" element="span" cssClass="error"/>	
									</td>
								</tr>
								<tr>
									<td class="table-active"><span class="text-danger">*</span>지급액 :</td>
									<td>
									<input type="hidden" name="schAmountDetail" value="전액">
									<form:input type="text" class="form-control" inputmode="numeric" path="schAmount" placeholder="숫자만 입력하세요."  />	
									</td>
								</tr>
								<tr>
									<td class="table-active">장학상세 :</td>
									<td>
									<textarea class="form-control" id="lec_content" rows="12" required="required" 
									   name="schDetail"	>${schVO.schDetail }
									</textarea>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-6 float-left pl-5 border border-secondary">
						<table class="exampleTable table text-center w-100">
								<tr>
									<td colspan="4"><span class="text-danger">&lt등록 예시&gt</span></td>
								</tr>
								<tr>
									<td class="table-active w-25">장학코드</td>
									<td>8003</td>
									<td class="table-active">장학명</td>
									<td>멘토링 장학금</td>
								</tr>
								<tr>
									<td class="table-active">장학유형</td>
									<td colspan="3">교내(APPLY)</td>
								</tr>
								<tr>
									<td class="table-active">장학금액</td>
									<td>500,000 원</td>
									<td class="table-active">지급상세</td>
									<td>전액</td>
								</tr>	
								<tr>
									<td class="table-active">장학상세</td>
									<td colspan="3">
									자기계발 목표와 계획을 수립한 후, 담당 교수가 밀착지도함으로써 우리대학 인재상을 육성하고 장학금을 지원하는 교수-학생 멘토링 장학금, 자기계발계획을 작성후 신청, 담당 교수와 학기중 3회 이상 면담 후 상담일지 작성, 대학에서 운영하는 특강이나 프로그램에 2회이상 참여해야함. 활동 완료 후 장학금 지급
									</td>
								</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="text-right">
				<c:if test="${type eq 'MODIFY' }">
				<button type="button" id="updateBtn" class="btn waves-effect waves-light btn-warning px-4 py-2 mx-1">수정</button>
           		 </c:if>
           		 <c:if test="${type ne 'MODIFY' }">
           		<button id="insertBtn" type="button" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-1">등록</button>
				</c:if>
				<button type="button" id="resetBtn" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1">취소</button>
			</div>
		</div>
	</div>
</div>
</form:form>
<script>
$(document).on('keyup','input[inputmode=numeric]',function(event){
	this.value = this.value.replace(/[^0-9]/g,'');   // 입력값이 숫자가 아니면 공백
	this.value = this.value.replace(/,/g,'');          // ,값 공백처리
	this.value = this.value.replace(/\B(?=(\d{3})+(?!\d))/g, ","); // 정규식을 이용해서 3자리 마다 , 추가 	
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

$("#schForm").validate(validateOptions);


$("#insertBtn").on("click",function(e){
	e.preventDefault();
	
	let tmpAmount = $("input[name='schAmount']").val();
	tmpAmount = tmpAmount.replace(",","");
	$("input[name='schAmount']").val(tmpAmount);
	console.log(tmpAmount);
	
	$("#schForm").submit();
	
	return false;
});

$("#updateBtn").on("click",function(e){
	e.preventDefault();
	
	$("#schForm").attr("action","${cPath}/admin/updateScholarType.do");
	
	let tmpAmount = $("input[name='schAmount']").val();
	tmpAmount = tmpAmount.replace(",","");
	$("input[name='schAmount']").val(tmpAmount);
	console.log(tmpAmount);
	
	
	$("#schForm").submit();
	
	return false;
});

$("#resetBtn").on("click",function(){
// 	$("input[name='subCode']")
	location.href="${cPath}/admin/scholarshipType.do";
});



</script>
