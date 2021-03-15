<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<style type="text/css">
#pdfArea{
	width: 50%;
	height: 1000px;
	margin-left: 25%;
	position: relative;
}
#iframId{
	width: 80%;
	height: 960px;
}

#insteadPdfArea{
	width: 50%;
	height: 1200px;
	margin-left: 30%;
}
</style>
<security:authorize access="isAuthenticated()">
<security:authentication property="principal" var="principal"/>
<c:set var="authMember" value="${principal.realMember }" />
</security:authorize>
<div class="container-fluid">
	<div class="row">
		<div class="col-12">
			<h3 class="ml-2">
				<i class="fas fa-home"></i> 등록금 > 납부 확인서 발급
			</h3>
		</div>
	</div>
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-9 text-left">
			<h2 class="font-weight-bold">등록금 납부 확인서</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-11">
			<h5 class="pb-5 ml-2">등록금 납부 확인서를 발급 받을 수 있습니다.</h5>
		</div>
	</div>
	<c:if test="${resultCount eq 1 }">
		<c:if test="${firstPayCheck.payCheck eq 'N'}">
			<div id="pdfArea">
				<iframe id="iframId" src="${cPath}/lms/student/tuition/tuitionpaymentPDF.do" width="100%" height="1200px"></iframe>
			</div>
		</c:if>
	</c:if>
	<c:if test="${firstPayCheck.payCheck eq 'Y'}">
		<div class="text-center" style="width: 100%; height:480px; margin-top: 300px;">
			<h2>분할 납부를 신청하셨으므로 이용이 불가능합니다.</h2>
		</div>
	</c:if>
	<c:if test="${resultCount eq 0 }">
		<div class="text-center" style="width: 100%; height:480px; margin-top: 300px;">
			<h2>등록금 납부 기간이 아닙니다.</h2>
		</div>
	</c:if>
	<input type="hidden" id="getStatus"/>
</div>
	<!-- 가상계좌를 발급받지 않고 등록금 고지서 페이지에 접속하였을 때 뜨는 모달창 -->
	<div class="modal fade" id="virtualAccountCheck" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered">
	        <div class="modal-content">
	            <div class="modal-header">
	               	<h3 class="modal-title" id="myCenterModalLabel">알림창</h3>
					<button type="button" class="close" data-dismiss="modal"
	                    aria-hidden="true">×</button>
	            </div>
	            <div class="modal-body text-center">
	            	<p>
	            	아직 가상계좌를 발급하지 않으신 상태입니다.<br>
	            	납부 확인서 발급은 '등록금 고지서' 페이지에서 가상계좌를 발급을 받은 후<br>
	            	등록금 납부가 완료된 후에 이용해주세요.
	            	</p>
				</div>
				<div class="modal-footer">
	                <button type="button" id="tuitionBill" class="btn btn-primary">가상계좌 발급 받으러 가기</button>
	            </div>
	        </div><!-- /.modal-content -->
	   </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<!-- 결제 상태가 미결제 일때 뜨는 모달창 -->
	<div class="modal fade" id="virtualAccountStatusCheck" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered">
	        <div class="modal-content">
	            <div class="modal-header">
	               	<h3 class="modal-title" id="myCenterModalLabel">알림창</h3>
					<button type="button" class="close" data-dismiss="modal"
	                    aria-hidden="true">×</button>
	            </div>
	            <div class="modal-body text-center">
	            	<p>
	            	등록금 미납<br>
	            	등록금을 납부하시고 이용해주세요.
	            	</p>
				</div>
	        </div><!-- /.modal-content -->
	   </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
<script type="text/javascript">
$(function(){
	$('#iframId').ready(function(){
		$.ajax({
			url : "${cPath}/pdf/pdfInsert.do",
			data : {"pdfName":"납부확인서"},
			method : "post",
			dataType : "json",
		});
	});
});
let vbankCheck = '${vaTuitionVO.payImp}'+"";
let payAmountCheck = '${firstPayCheck.payAmount}'+"";
let payDivisionCheck = '${firstPayCheck.payDivision}';
let payCheck =  '${firstPayCheck.payCheck}';
console.log(payAmountCheck);
let dateCheck = '${resultCount}';
let status;
window.onload = function(){
	if(dateCheck == '1'){
		if(vbankCheck == 0){
			$("#pdfArea").hide();
			$("#virtualAccountCheck").modal();
			$("#tuitionBill").on("click", function(){
				location.href="${cPath}/lms/student/tuition/tuitionbill.do";
			});
		}
	}
	
	
	if(vbankCheck != 0){
	//----------------------- 토큰 발급 ----------------------------
			$.ajax({
			    url: "${cPath}/lms/student/tuition/getTokenTuitionPayment.do",
			    method: "post", // POST method
			    dataType : 'json',
			    headers: { "Content-Type": "application/json" }, // "Content-Type": "application/json"
			    success:function(resp){
					console.log("토큰 : "+resp.result);
					if(payAmountCheck == 0){
						$("#getStatus").click();
					}
				}
				, error : function(request, error){
			    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			    }
		    });
	
		$("#getStatus").on("click",function(){
			$.ajax({
			    url: "${cPath}/lms/student/tuition/getStatusTuitionPayment.do",
			    method: "post", // POST method
			    dataType : 'json',
			    headers: { "Content-Type": "application/json" }, // "Content-Type": "application/json"
			    success:function(resp){
			    	status = resp.status;
			    	if(payDivisionCheck == 'N' && payCheck == 'N'){
				    	if(payAmountCheck == 0){
							$("#pdfArea").hide();
							$("#virtualAccountStatusCheck").modal();
						}
			    	} 
			    	if(status == "paid"){
			    		console.log(status);
			    		$("#pdfArea").show();
			    		let paidDate = resp.formattedDate;
			    		console.log(paidDate);
			    		let paidAmount = resp.paid_amount;
			    		console.log(paidAmount);
			    		$.ajax({
			    			url :'${cPath}/lms/student/tuition/paidUpdate.do'
			    			, method : "post"
			    			, dataType : "json"
			    			, data :
			    			{
			    				"payAmount" : paidAmount
			    				, "payDate" : paidDate
			    			}
			    			,success : function(resp){
			    				console.log("업데이트 성공이요");
			    			}
		    				, error : function(request, error){
		    			    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
		    			    }
			    		}, {once:true});
			    	}
			    	
				}
				, error : function(request, error){
			    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			    }
		  });
		});
	}
}

</script>
