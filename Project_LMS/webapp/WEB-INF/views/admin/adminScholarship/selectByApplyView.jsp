<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${cPath}/css/admin/scholarship.css">
<div class="container-fluid">
	<div class="card-body">
		<div class="row my-3">
			<div class="col-12">
				<h5 class="ml-2">
					<i class="fas fa-home"></i> 장학관리 > 교내장학생 선발
				</h5>
			</div>
		</div>
	    <div class="row my-3 d-flex align-items-center mb-4 border-bottom border-admin p-0 my-2">
	        <h3 class="font-weight-bold">신청 장학생 상세조회</h3>
		</div>
		<div class="row my-3">
			<div class="col-11">
				<h5 class="pb-3 ml-2">교내 장학생 선발 전 신청 상세내용을 조회할 수 있습니다.</h5>
			</div>
		</div>
	    <form id="selectApply" method="post" action="${cPath}/admin/updateScholarApply.do">
	    <div class="row">
		    <div class="col-12">
		            <div class="card">
		                <div class="table-responsive">
		                                <input type="hidden" name="sfundNo" value="${schFund.sfundNo }">
		                                <input type="hidden" name="codeResult" id="codeResult" value="${schFund.codeResult }">
		                    <table class="table">
		                        <tbody id="listBody">
		                            <tr>
		                                <td class="table-active font-weight-bold">신청번호</td>
		                                <td colspan="2">${schFund.sfundNo }</td>
		                                <td class="table-active font-weight-bold">학번</td>
		                                <td colspan="2">${schFund.sfundStudent }</td>
		                            </tr>
		                            <tr>
		                                <td class="table-active font-weight-bold">성명</td>
		                                <td colspan="2">${schFund.memberVO.memName }</td>
		                                <td class="table-active font-weight-bold">추천교수코드</td>
		                                <td colspan="2">${schFund.sfundProfessor }</td>
		                            </tr>
		                            <tr>
		                                <td class="table-active font-weight-bold">학기</td>
		                                <td colspan="2">
		                                	${fn:substring(schFund.sfundSmst,0,4) }년도
		                     	  			${fn:substring(schFund.sfundSmst,5,6) }학기</td>
		                                <td class="table-active font-weight-bold">장학금명</td>
		                                <td colspan="2">${schFund.schVO.schName }</td>
		                            </tr>
		                            <tr>
		                             	 <td class="table-active font-weight-bold">금액</td>
		                                <td colspan="2"><fmt:formatNumber value="${schFund.sfundValue }" pattern="#,###" />원</td>
		                                <td class="table-active font-weight-bold">신청결과</td>
		                                <td colspan="2" id="result">${schFund.codeVO.codeName }</td>
		                            </tr>
		                            <tr>
		                            	 <td class="table-active font-weight-bold">사유</td>
		                                <td colspan="5" style="height: 200px;">
		                                	${schFund.sfundReason }
		                                </td>
		                            </tr>
		                        </tbody>
		                    </table>
					</div>
				</div>
			</div>
		</div>
	    </form>
	    <!-- 버튼 -->
		<div class="row my-3" id="buttonDiv">
			<div class="col-12 d-flex justify-content-end align-items-center">
				<div class="form-inline">
					<div class="form-group">
						<button type="button" id="resetBtn" class="btn waves-effect waves-light btn-light px-4 py-2 mx-2">목록</button>
					</div>
					<div class="form-group">
						<button type="button" id="acceptBtn" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2">접수</button>
					</div>
					<div class="form-group">
						<button type="button" id="rejectBtn" class="btn waves-effect waves-light btn-warning px-4 py-2 mx-2">신청반려</button>
					</div>
					<div class="form-group">
						<button type="button" id="cancelBtn" class="btn waves-effect waves-light btn-danger px-4 py-2 mx-2">접수취소</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>

$("#resetBtn").on("click", function(){
	location.href="${cPath}/admin/scholarshipSelectByApply.do";
});

$("#acceptBtn").on("click",function(e){
	e.preventDefault();
	let result = $("#result").text();
	if(result=="접수"){
		alert("이미 접수된 내역이 포함되어있습니다.");
		return;
	}
	document.getElementById("codeResult").value ="ACCEPT";

	
	$.ajax({
		url : "${cPath}/admin/updateScholarApply.do"
		,method : "post"
		,data : $("#selectApply").serialize()
		,dataType : "json"
		,success : function(data){
			alert(data.message);
			$("#result").text(data.schFundVO.codeVO.codeName);
			document.getElementById("codeResult").value = data.schFundVO.codeResult;
		}
	})

	return false;
});	

$("#cancelBtn").on("click",function(e){
	e.preventDefault();
	let result = $("#result").text();
	if(result!="접수"){
		alert("선발되지 않은 내역입니다.");
		return;
	}
	document.getElementById("codeResult").value ="APPLY";
	$.ajax({
		url : "${cPath}/admin/updateScholarApply.do"
		,method : "post"
		,data : $("#selectApply").serialize()
		,dataType : "json"
		,success : function(data){
			alert(data.message);
			$("#result").text(data.schFundVO.codeVO.codeName);
			document.getElementById("codeResult").value = data.schFundVO.codeResult;
		}
	})
	
	return false;
});	

$("#rejectBtn").on("click",function(e){
	e.preventDefault();
	
	let result = $("#result").text();
	if(result=="반려"||result=="지급"||result=="접수"){
		alert("이미 처리된 내역입니다.");
		return false;
	}
	document.getElementById("codeResult").value ="REJECT";
	$.ajax({
		url : "${cPath}/admin/updateScholarApply.do"
		,method : "post"
		,data : $("#selectApply").serialize()
		,dataType : "json"
		,success : function(data){
			alert(data.message);
			$("#result").text(data.schFundVO.codeVO.codeName);
			document.getElementById("codeResult").value = data.schFundVO.codeResult;
		}
	})
	
	return false;
});

</script>