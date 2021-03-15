<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="validator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="${cPath}/css/admin/student.css">
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 학생관리 > 교수조회 
			</h5>
		</div>
	</div>
	<div class="row mb-3 border-bottom border-admin p-0 my-2">
		<div class="col-12">
			<c:set var="type" value="${TYPE}"/>
			<input type="hidden" name="type" value=${TYPE }>
			<c:if test="${type eq 'MODIFY' }">
			<h2 class="font-weight-bold">교수 정보 수정</h2>
			</c:if>
			<c:if test="${type eq 'INSERT' }">
			<h2 class="font-weight-bold">교수등록</h2>
			</c:if>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">교수 정보를 등록/수정할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row">
		<div class="col-10">
			<div class="card"> 
				<form:form commandName="memVO" id="insertForm" action="${cPath}/admin/insertProfessor.do"  enctype="multipart/form-data" >
				<c:if test="${type eq 'INSERT' }">
				<input type="hidden" name="memType" value="ROLE_PROFESSOR" >
				<input type="hidden" name="memState" value="ING" >
				</c:if>
				<div class="card-body">
					<div class="card">
						<div>
						<table class="proInsertTable table">
						<caption><span class="importantText">*</span> 필수 입력 </caption>
							<tr>
								<td rowspan="4" colspan="2">
									<c:if test="${not empty memVO.memImg }">
										<img id="img" class="imgSize" src="${cPath}/memberImages/${memVO.memImg }" alt='${cPath}/images/noImage.png' />
									</c:if>
									<c:if test="${ empty memVO.memImg }">
										<img id="img" class="imgSize" src="${cPath}/images/noImg.png" />
									</c:if>
								</td>
								<td class="table-active"><p class="font-weight-bold">이름 <span class="importantText">*</span></p></td>
								<td colspan="2">
									<form:input path="memName" class="form-control readable" value="${memVO.memName }"/> 
									<span><form:errors path="memName"  element="span" cssClass="text-danger" /></span>
								</td>
								<td class="table-active">사번</td>
								<td>
								<input type="text" name="memId" value="${memVO.memId}" class="form-control" readonly>
								</td>
								<td class="table-active"><p class="font-weight-bold">학부/학과<span class="importantText">*</span></p></td>
								<c:set var="type" value="${TYPE}"/>
								<!-- 등록 일때 -->
								<c:if test="${type eq 'INSERT' }">
								<td colspan="2">
								<div class="d-flex">
									<select class="form-control w-50 mr-1" id="college" name="college" 
										onChange="changeCollege()" required>
										<option value="">단과대</option>
									</select>
									<select class="form-control w-50" id="department" name="depNo" required>
										<option value="">학과선택</option>
									</select>	
								</div>									
								</td>
								</c:if>
								<!-- 수정 일때 -->
								<c:if test="${type eq 'MODIFY' }">
								<td colspan="2">
									<input type="hidden" name="depNo" value="${stuVO.depNo}" required >
									<div class="float-left w-75"><form:input path="depName" cssClass="form-control" readonly="true"  /></div>
									<div class="float-left"><button type="button" class="btn waves-effect waves-light btn-rounded btn-warning" data-toggle="modal"  data-target="#changeDepartment">변경</button></div>
								</td>
								</c:if>
							</tr>
							<tr>
							</tr>
							<tr>
								<td class="table-active"><p class="font-weight-bold">사진</p></td>
								<td colspan="2">
									<div class="input-group mb-3">
                                        <div class="custom-file">
                                            <input type="file" name="memImage" class="custom-file-input" id="fileInp" value="${memVO.memImg }" >
                                            <label class="custom-file-label" for="inputGroupFile01"></label>
                                        </div>
                                   	</div>
								</td>
								<td class="table-active"><p class="font-weight-bold">생년 월일</p></td>
								<c:if test="${type eq 'MODIFY' }"><td colspan="4"></c:if>
								<c:if test="${type eq 'INSERT' }"><td colspan="4"></c:if><form:input path="memReg1" type="number" class="form-control readable" value="${memVO.memReg1 }"/>
									<span><form:errors path="memReg1"  element="span" cssClass="text-danger" /></span>
								</td>
							</tr>
							<tr>
								<td class="table-active"><p class="font-weight-bold">전화번호 <span class="importantText">*</span></p></td>
								<td colspan="2"><form:input path="memTel" class="form-control" value="${memVO.memTel }"
									id="telId" placeholder="전화번호 입력" />
									<span id="telText"><form:errors path="memTel" element="span" cssClass="text-danger"/></span>	
								</td>
								<td class="table-active"><p class="font-weight-bold">이메일 <span class="importantText">*</span></p></td>
								<c:if test="${type eq 'MODIFY' }"><td colspan="4"></c:if>
								<c:if test="${type eq 'INSERT' }"><td colspan="4"></c:if>
									<form:input path="memMail" type="email" class="form-control" value="${memVO.memMail }"  />
									<span><form:errors path="memMail" element="span" cssClass="text-danger"/></span>	
								</td>
							</tr>
							<tr>
								<td class="table-active" ><p class="font-weight-bold">주소 <span class="importantText">*</span></p>
								</td>
								<td colspan="1">		
									<input type="button"  class="btn waves-effect waves-light btn-outline-primary" value="주소검색" onclick="goPopup();">
								</td>
								<td colspan="3">
									<span id="span1">${memVO.memAddr1 }</span>
									<form:hidden path="memAddr1" id="addrInput" class="form-control"  value="${memVO.memAddr1 }"/> 
										<span><form:errors path="memAddr1"  element="span" cssClass="text-danger"/></span>	
								</td>
								<td class="table-active" ><p class="font-weight-bold">상세 주소 <span class="importantText">*</span></p>
								</td>
								<c:if test="${type eq 'MODIFY' }"><td colspan="4"></c:if>
								<c:if test="${type eq 'INSERT' }"><td colspan="4"></c:if>
									<span id="span2">${memVO.memAddr2 }</span>
									<form:hidden path="memAddr2" id="addr2Input" class="form-control"  value="${memVO.memAddr2 }"/> 
									<span><form:errors path="memAddr2" element="span" cssClass="text-danger" /></span>	
								</td>
							</tr>
							<tr>
								<td class="table-active" ><p class="font-weight-bold">계좌 <span class="importantText">*</span></p></td>
								<td colspan="1">
								<form:select class="form-control" path="memBank" id="inlineFormCustomSelect1">
								   <form:option value="">은행 선택하세요</form:option>
			                       <form:option value="경남은행">경남은행</form:option>
			                       <form:option value="광주은행">광주은행</form:option>
			                       <form:option value="국민은행">국민은행</form:option>
			                       <form:option value="기업은행">기업은행</form:option>
			                       <form:option value="농협중앙회">농협중앙회</form:option>
			                       <form:option value="농협회원조합">농협회원조합</form:option>
			                       <form:option value="대구은행">대구은행</form:option>
			                       <form:option value="도이치은행">도이치은행</form:option>
			                       <form:option value="부산은행">부산은행</form:option>
			                       <form:option value="산업은행">산업은행</form:option>
			                       <form:option value="상호저축은행">상호저축은행</form:option>
			                       <form:option value="새마을금고">새마을금고</form:option>
			                       <form:option value="수협중앙회">수협중앙회</form:option>
			                       <form:option value="신한금융투자">신한금융투자</form:option>
			                       <form:option value="신한은행">신한은행</form:option>
			                       <form:option value="신한은행">신협중앙회</form:option>
			                       <form:option value="외환은행">외환은행</form:option>
			                       <form:option value="우리은행">우리은행</form:option>
			                       <form:option value="우체국">우체국</form:option>
			                       <form:option value="전북은행">전북은행</form:option>
			                       <form:option value="제주은행">제주은행</form:option>
			                       <form:option value="카카오뱅크">카카오뱅크</form:option>
			                       <form:option value="케이뱅크">케이뱅크</form:option>
			                       <form:option value="하나은행">하나은행</form:option>
			                       <form:option value="한국씨티은행">한국씨티은행</form:option>
			                       <form:option value="HSBC">HSBC은행</form:option>
			                       <form:option value="제일은행">SC제일은행</form:option>
								</form:select>
								<span><form:errors path="memBank" element="span" cssClass="text-danger" /></span>
								</td>
								<td class="table-active"><p class="font-weight-bold">예금주</p></td>
								<td >
									<span>${memVO.memName }</span>
								</td>
								<td class="table-active"><p class="font-weight-bold">계좌번호 <span class="importantText">*</span></p></td>
								<td colspan="2">
									<form:input path="memAcn" class="form-control" value="${memVO.memAcn }"/>
									<span><form:errors path="memAcn" element="span" cssClass="text-danger" /></span>
								</td>
								<c:if test="${type eq 'MODIFY' }">
								<td class="table-active">상태변경</td>
								<td><form:select path="memState" id="memState" class="form-control">
									<form:option value="">선택</form:option>
									<form:option value="ING">재직</form:option>
									<form:option value="REST">휴직</form:option>
									<form:option value="LEFT">퇴사</form:option>
									<form:option value="END">퇴직</form:option>
							    	  </form:select> </td>
								</c:if>
							</tr>
						</table>
						</div>
					</div>
				</div>
				</form:form>
			</div>	
			<div class="text-right">
				<c:if test="${type eq 'MODIFY' }">
				<button type="button" id="updateBtn" class="btn waves-effect waves-light btn-warning px-4 py-2 mx-2">수정</button>
				</c:if>
				<c:if test="${type eq 'INSERT' }">
				<button id="insertBtn" type="button" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-1">등록</button>
				</c:if>
				<button type="button" id="resetBtn" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1">취소</button>
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
<script type="text/javascript">
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

$("#insertForm").validate(validateOptions);


$(function(){
	// 학부, 학적 코드 조회 =======================================================================================
	$.ajax({
		url : "${cPath }/admin/getCollegeAndState.do"
		,method : "get"
		,dataType : "json"
		,success : function(data){
			makeCollegeAndStateSelect(data);
		}
	});
	
	let type = $("input[name='type']").val();
	if(type=='MODIFY'){
		$(".readable").attr("readonly",true);
	}
	
});

// 학부, 학적상태 selectBox 생성=======================================================================================
function makeCollegeAndStateSelect(data){
	let collegeList = data.collegeList;
	let stateList = data.stateList;
	let html = '<option value >학부선택</option>';
	for(let i = 0 ; i < collegeList.length ; i++){
		html += '<option value='+collegeList[i].code+' >'+collegeList[i].codeName+'</option>';
	}
	$("#college").html(html);
	
	let html2 = '<option value >상태</option>';
	for(let i = 0 ; i < stateList.length ; i++){
		html2 += '<option value='+stateList[i].code+' >'+stateList[i].description+'</option>';
	}
	$("#state").html(html2);
	
	
}

//학부 선택시=======================================================================================
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

// 주소 API 
function goPopup(){
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
  var pop = window.open("${cPath}/profile/jusoPopup.do","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
  //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}
/** API 서비스 제공항목 확대 (2017.02) **/
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn
						, detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm, buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo){
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	$("#addrInput").val(roadAddrPart1);
	$("#addr2Input").val(addrDetail);
	$("#span1").text(roadAddrPart1);
	$("#span2").text(addrDetail);
}

//취소 클릭시
$("#resetBtn").on("click", function(){
	location.href="${cPath}/admin/professorList.do";
});

//교수등록버튼 클릭시 ===========================================================================================
$("#insertBtn").on("click",function(){
	$("#insertForm").submit();
});

//교수 정보수정 버튼 클릭시 ====================================================
$("#updateBtn").on("click",function(e){
	e.preventDefault();
	
	$("#insertForm ").attr("action","${cPath}/admin/updateProfessor.do");
	let updateForm = $("#updateForm");
	$("#insertForm").submit();
	
	return false;
});

//전화번호 체크=======================================================================================
$("#telId").bind("keyup", function(event){
	var regNumber2 = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	var temp2 = $("#telId").val();
	if(!regNumber2.test(temp2)){
		$("#telId").val(temp2.replace(/[^0-9]/g,""));
	}
});

//학과 변경 클릭시=======================================================================================
$("#goUpBtn").on("click",function(){
	let depNo = $("#department option:selected").val();
	let depName = $("#department option:selected").text();

	$("input[name='depName']").val(depName);
	$("input[name='depNo']").val(depNo);
	
});

//이미지 업로드시 화면에 출력===================================================================================
$("#fileInp").change(function(){
	if(this.files &&this.files[0]){
		let reader = new FileReader;
		reader.onload = function(data){
			$("#img").attr("src", data.target.result).width(200);
			$("#memImg").val(data.target.result);
		}
		reader.readAsDataURL(this.files[0]);
	}
});
</script>