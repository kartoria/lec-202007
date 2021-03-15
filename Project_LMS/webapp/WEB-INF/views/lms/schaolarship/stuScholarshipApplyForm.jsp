<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springmodules.org/tags/commons-validator" prefix="validator"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<style>
.accTable th{height: 15px;}
.applypaper{width: 60em;font-size: 12pt;border: 1px solid black;padding: 10px;}
.applyform{color:black;}
.mainTable{color: black;}
.mainTable textArea{border: 0px;}
.table-bordered, .table-bordered td, .table-bordered th{border: 1px solid darkgray;}
.bottomText{font-size:25pt; text-align:center;}
.bottomBody{height: 300px;}
</style>
<div class="container-fluid">
	<div class="row">
		<div class="col-12">
			<h3 class="ml-2">
				<i class="fas fa-home"></i> 장학금 > 교내 장학금 신청 > 신청서 작성
			</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-body">
					<!-- 여기부터신청서 -->
				<p class="text-info ml-8"><b>아래 정보가 다를경우 </b>학사관리 > 개인정보<b> 변경에서 변경하시길 바랍니다.</b></p> 
				<form method="post" id="applyScholarship" action="${cPath}/lms/student/schaolarship/applyInsert.do">
				<input type="hidden" name="codeResult" value="APPLY">
				<input type="hidden" name="sfundStudent" value="${stuVO.memId }" required>
				<input type="hidden" name="sfundValue" required >
				<div class="applyform container" >
				   <div class="applypaper card">
				     <div class="card-img-top">
				           <div class="container">
				              <div class="mt-5 ml-15">
				                 <div class="align-middle">
				                  <div class="text-center">            
				                        <p class="ml-3 text-center display-4">교내 장학금 신청서</p>  
				                  </div>
				                 </div>
				              </div>
				         </div>     
				     </div>
				     <hr>
				     <div class="card-body">
				          <table class="mainTable table table-bordered text-center">
				              <tr>
				                <td>학과</td>
				                <td>${stuVO.depName}</td>
				                <td>학번</td>
				                <td>${stuVO.memId}</td>
				                <td>이름</td>
				                <td>${stuVO.memName}</td>
				              </tr>
				              <tr>
				                <td><span class="text-danger">*</span>장학유형</td>
				                <td><select id="scholarship" name="scharCode" class="form-control" required="required">
										<option>선택</option>
									</select>
				                </td>
				                <td>주소</td>
				                <td colspan="3">${stuVO.memZip } ${stuVO.memAddr1 } ${stuVO.memAddr2 }</td>
				              </tr>
				              <tr>
        						 <td>전화번호</td>
								 <td>${stuVO.memTel }</td>
				                 <td>계좌</td>
				                 <td colspan='3'>[ ${stuVO.memBank } ] ${stuVO.memAcn } </td>
				              </tr>
				              <tr>
				                 <td colspan='6'><span class="text-danger">*</span>신청사유(구체적으로 작성)</td>
				              </tr>
				              <tr>
				                 <td colspan='6'><textarea class="form-control" name="sfundReason" cols="155" rows="10" required></textarea></td>
				              </tr>
				         </table>
				           <div>
				              <div class="bottomBody mt-2 text-center">
				                 <p class="mt-3 mb-3">위와 같이 장학금을 신청합니다.</p>
				                 <p class="today mt-3 mb-3"></p>
				                 <p class="text-right mt-3 mb-3">신청자 : ${stuVO.memName }</p>
				              </div>
				           </div>
				           <div class="text-center">
				              <img id="logo1" src="${cPath}/images/logo/smartLMS_logo1.png" width="50px" alt="Logo">
				           	  <img id="logo2" src="${cPath}/images/logo/smartLMS_logo2.png" width="350px" alt="Logo">
				           </div>
				     </div>
				   </div>
				</div>	
				</form>				
				</div>
			</div>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-end align-items-center">
			<div class="form-inline">
				<div class="form-group">
					<button type="button" id="cancelBtn" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1">취소</button>
				</div>
				<div class="form-group">
					<button id="stuApplyScholarBtn" type="button" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-1">신청</button>
				</div>
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

$("#applyScholarship").validate(validateOptions);

//신청 가능 장학금 리스트 가져오기
$(function(){
	
	//오늘날짜 구하기
	let date = new Date(); 
	let year = date.getFullYear(); 
	let month = new String(date.getMonth()+1); 
	let day = new String(date.getDate()); 

	// 한자리수일 경우 0을 채워준다. 
	if(month.length == 1){ 
	  month = "0" + month; 
	} 
	if(day.length == 1){ 
	  day = "0" + day; 
	} 

	$(".today").text(year + "년 " + month + "월 " + day+"일");
	
	
	$.ajax({
		url :'${cPath}/lms/student/schaolarship/apply.do'
		, method : 'post'
		, dataType : 'json'
		, success : function(data) {
			makeScholarshipSelect(data);
		}
		, error : function(request, error){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	});
});

function makeScholarshipSelect(data){
	let chooseList = data.chooseList;
	let schHtml= "<option value=''>장학유형선택</option>";
	if(chooseList.length>0){
		for(let i = 0 ; i < chooseList.length ; i++){
			schHtml += '<option value='+chooseList[i].schCode+' value2='+chooseList[i].schAmount+'>'+chooseList[i].schName+'</option>';
		}
	}else{
		schHtml += '<option value >'+'신청가능 장학금 없음'+'</option>';
	}
	$("#scholarship").html(schHtml);
}

//장학금 신청	
$("#stuApplyScholarBtn").on("click",function(){
	
	let sfundValue = $("#scholarship option:selected").attr('value2');
	console.log(sfundValue);
	$("input[name='sfundValue']").val(sfundValue);
	$("#applyScholarship").submit();
	
});


</script>