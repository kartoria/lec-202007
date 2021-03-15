<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="validator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
input[type="text"]{
	width: 100%;
}
.imgSize{
	width: 50%;
	height: 300px;
}
h5,h2{
	color: #7c8798;
}
</style>
<div class="container-fluid border px-3" style="min-height:300px">
	<c:if test="${updateBtn eq 'OK'}">
		<div class="row my-3">
			<div class="col-12 d-flex justify-content-start align-items-center">
				<h5 class="ml-2"><i class="fas fa-home"></i> 개인정보 > 개인정보 수정</h5>
			</div>
		</div>
		<div class="row my-3 border-bottom border-cyber">
			<div class="col-12 d-flex justify-content-start align-items-center">
				<h2 class="font-weight-bold">개인정보 수정</h2>
			</div>
		</div>
		<div class="row mt-3 mb-5">
			<div class="col-12 d-flex justify-content-start align-items-center">
				<h5 class="ml-2"> 개인정보를 수정하세요.</h5>
			</div>
		</div>
	</c:if>
	<c:if test="${updateBtn ne 'OK'}">
		<div class="row my-3">
			<div class="col-12 d-flex justify-content-start align-items-center">
				<h5 class="ml-2"><i class="fas fa-home"></i> 최초로그인</h5>
			</div>
		</div>
		<div class="row my-3 border-bottom border-cyber">
			<div class="col-12 d-flex justify-content-start align-items-center">
				<h2 class="font-weight-bold">개인정보 등록</h2>
			</div>
		</div>
		<div class="row mt-3 mb-5">
			<div class="col-12 d-flex justify-content-start align-items-center">
				<h5 class="ml-2"> 개인정보를 등록하세요.</h5>
			</div>
		</div>
	</c:if>
	<form:form commandName="profileVO" id="updateForm"   enctype="multipart/form-data">
		<div class="col-12">
			
			<table class="table text-center">
			<caption style="caption-side:top;"><span class="text-danger">*</span> 필수 입력 </caption>
				<tbody id="listBody">
					<tr>
						<td rowspan="5" colspan="2" class="w-25">
							<img id="img" class="imgSize" src="${imagePath }"  />
						</td>
						<td class="table-active"><p class="font-weight-bold">학번</p></td>
						<td colspan="2">${profileVO.memId } <form:hidden path="memId" value="${profileVO.memId }"/> </td>
						<td class="table-active"><p class="font-weight-bold">
							<form:label path="memName" cssClass="required">이름 
							</form:label>
							</p>
						</td>
						<td colspan="2">
							<form:input path="memName" class="form-control" value="${profileVO.memName }"/> 
							<span><form:errors path="memName"  element="span" cssClass="text-danger" /></span>
						</td>
					</tr>
					<tr>
						<td class="table-active"><p class="font-weight-bold">학과</p></td>
						<td colspan="2">${profileVO.depName } <form:hidden path="depName" value="${profileVO.depName }"/></td>
						<td class="table-active"><p class="font-weight-bold">학년</p></td>
						<td colspan="2"><fmt:formatNumber var="grd" value="${(profileVO.memGrd/2 + 0.5) - ((profileVO.memGrd/2 + 0.5)%1)}"/>
							${grd }학년 <form:hidden path="memGrd" value="${profileVO.memGrd }"/></td>
					</tr>
					<tr>
						<td class="table-active"><p class="font-weight-bold">비밀번호</p></td>
						<td colspan="5"> <form:password path="memPass" class="form-control" placeholder="변경시에만 입력하세요." /></td>
					</tr>
					<tr>
						<td class="table-active"><p class="font-weight-bold">사진 수정</p></td>
						<td colspan="2"><form:input path="memImage" type="file" class="form-control" id="fileInp" value="${profileVO.memImg }"  accept="image/gif, image/jpeg, image/png" />
						</td>
						<td class="table-active"><p class="font-weight-bold">생년 월일</p></td>
						<td colspan="2"><form:input path="memReg1" type="number" class="form-control" value="${profileVO.memReg1 }"/>
							<span><form:errors path="memReg1"  element="span" cssClass="text-danger" /></span>
						</td>
					</tr>
					<tr>
						<td class="table-active"><p class="font-weight-bold">
							<p class="font-weight-bold">
								<form:label path="memTel" cssClass="required">전화번호
								</form:label>
							</p>
						</td>
						<td colspan="2"><form:input path="memTel" class="form-control" value="${profileVO.memTel }"
							id="telId" placeholder="전화번호 입력" />
							<span id="telText" class="text-danger"><form:errors path="memTel" element="span" cssClass="text-danger"/></span>	
						</td>
						<td class="table-active">
							<p class="font-weight-bold">
								<form:label path="memMail" cssClass="required">이메일
								</form:label>
							</p>
						</td>
						<td colspan="2">
							<form:input path="memMail" type="email" class="form-control" value="${profileVO.memMail }"  />
							<span><form:errors path="memMail" element="span" cssClass="text-danger"/></span>	
						</td>
					</tr>
					<tr>
						<td class="table-active" >
							<p class="font-weight-bold">
								<form:label path="memAddr1" cssClass="required">주소
								</form:label>
							</p>
						</td>
						<td colspan="1">		
							<input type="button"  class="btn btn-primary" value="주소검색" onclick="goPopup();">
						</td>
						<td colspan="3">
							<span id="span1">${profileVO.memAddr1 }</span>
							<form:hidden path="memAddr1" id="addrInput" class="form-control"  value="${profileVO.memAddr1 }"/> 
								<span><form:errors path="memAddr1"  element="span" cssClass="text-danger"/></span>	
						</td>
						<td class="table-active" >
							<p class="font-weight-bold">
								<form:label path="memAddr2" cssClass="required">상세 주소
								</form:label>
							</p>
						
						</td>
						<td colspan="2">
							<span id="span2">${profileVO.memAddr2 }</span>
							<form:hidden path="memAddr2" id="addr2Input" class="form-control"  value="${profileVO.memAddr2 }"/> 
							<span><form:errors path="memAddr2" element="span" cssClass="text-danger" /></span>	
						</td>
					</tr>
					<tr>
						<td class="table-active" >
							<p class="font-weight-bold">
								<form:label path="memBank" cssClass="required">계좌
								</form:label>
							</p>
						</td>
						
						<td colspan="1">
							<span id="bankOption">${profileVO.memBank }</span>
						</td>
						<td colspan="1">
						<form:hidden path="memBank"  class="form-control" id="memBank"  value="${profileVO.memBank }"/> 
						<select class="form-control" id="inlineFormCustomSelect1">
						 	<option value="">은행 선택하세요</option>
		                       <option value="경남은행">경남은행</option>
		                       <option value="광주은행">광주은행</option>
		                       <option value="국민은행">국민은행</option>
		                       <option value="기업은행">기업은행</option>
		                       <option value="농협중앙회">농협중앙회</option>
		                       <option value="농협회원조합">농협회원조합</option>
		                       <option value="대구은행">대구은행</option>
		                       <option value="도이치은행">도이치은행</option>
		                       <option value="부산은행">부산은행</option>
		                       <option value="산업은행">산업은행</option>
		                       <option value="상호저축은행">상호저축은행</option>
		                       <option value="새마을금고">새마을금고</option>
		                       <option value="수협중앙회">수협중앙회</option>
		                       <option value="신한금융투자">신한금융투자</option>
		                       <option value="신한은행">신한은행</option>
		                       <option value="신한은행">신협중앙회</option>
		                       <option value="외환은행">외환은행</option>
		                       <option value="우리은행">우리은행</option>
		                       <option value="우체국">우체국</option>
		                       <option value="전북은행">전북은행</option>
		                       <option value="제주은행">제주은행</option>
		                       <option value="카카오뱅크">카카오뱅크</option>
		                       <option value="케이뱅크">케이뱅크</option>
		                       <option value="하나은행">하나은행</option>
		                       <option value="한국씨티은행">한국씨티은행</option>
		                       <option value="HSBC">HSBC은행</option>
		                       <option value="제일은행">SC제일은행</option>
						</select>
		
		
						<span><form:errors path="memBank" element="span" cssClass="text-danger" /></span>
						</td>
						<td class="table-active"><p class="font-weight-bold">예금주</p></td>
						<td >
							<span>${profileVO.memName }</span>
						</td>
						<td class="table-active">
							<p class="font-weight-bold">
								<form:label path="memAcn" cssClass="required">계좌 번호
								</form:label>
							</p>
						</td>
						<td colspan="2">
							<form:input path="memAcn" class="form-control" value="${profileVO.memAcn }"/>
							<span><form:errors path="memAcn" element="span" cssClass="text-danger" /></span>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="d-flex align-items-center mt-4 float-right">
				<c:if test="${updateBtn eq 'OK'}">
				<button class="btn btn-primary btn-lg " type="button"
					 aria-haspopup="true" aria-expanded="false"
					id="updateBtn">수정</button>
				</c:if>
				<c:if test="${updateBtn ne 'OK'}">
				<button class="btn btn-primary btn-lg " type="button"
					 aria-haspopup="true" aria-expanded="false"
					id="updateBtn2">최초 등록</button>
				</c:if>
			</div>
		</div>
	
	</form:form>
</div>

<script type="text/javascript">



	
//opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

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
$(function(){

});

	//개인정보 수정 ===========================================================================================
	$("#updateBtn").on("click",function(){
		$("#updateForm").attr("action","${cPath}/profile/profileUpdate.do");
		$("#updateForm").submit();
	});
	$("#updateBtn2").on("click",function(){
		$("#updateForm").attr("action","");
		let updateForm = $("#updateForm");
		$.ajax({
			url : "${cPath}/first/firstprofileUpdate.do",
			data : updateForm.serialize(),
			method : "post",
			dataType : "json",
			success : function(resp) {
				if(resp.Result=="OK"){
					alert("정보 등록을 성공하셨습니다.");
					window.location = 'http://localhost/${cPath}';

				}
				if(resp.Result=="NO"){
					$("#telText").text("전화번호는 필수 입력사항입니다.");
				}
			},
			error : function(xhr) {
				console.log(xhr);
			}
		});


	});
	//전화번호 체크
	$("#telId").bind("keyup", function(event){
		var regNumber2 = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
		var temp2 = $("#telId").val();
		if(!regNumber2.test(temp2)){
			$("#telText").text("숫자만 입력하세요.");
			$("#telId").val(temp2.replace(/[^0-9]/g,""));
		}
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
	$("#inlineFormCustomSelect1").change(function(){
		let Option1 = this.value;
		$("#bankOption").text(Option1);
		$("#memBank").val(Option1);
	});
	
</script>