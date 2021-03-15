<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!-- Virtual Account -->
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
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
#pdf{
 width : 100%;
 margin: auto;
}
</style>
<div class="container-fluid">
	<div class="row">
		<div class="col-12">
			<h3 class="ml-2">
				<i class="fas fa-home"></i> 등록금 > 등록금 고지서
			</h3>
		</div>
	</div>
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-9 text-left">
			<h2 class="font-weight-bold">등록금 납부 고지서</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-11">
			<h5 class="pb-5 ml-2">등록금 납부 고지서를 발급 받을 수 있습니다.</h5>
		</div>
	</div>
	<c:if test="${resultCount eq 1 }">
		<c:if test="${firstPayCheck.payCheck eq 'N' }">
			<div id="pdfArea">
				<iframe id="iframId" src="${cPath}/lms/student/tuition/tuitionbillPdf.do" width="100%" height="1200px" ></iframe>
			</div>
		</c:if>
	</c:if>
	<c:if test="${firstPayCheck.payCheck eq 'Y' }">
		<div class="text-center" style="width: 100%; height:480px; margin-top: 300px;">
			<h2>분할 납부를 신청하셨습니다.<br> 
			확인서 발급은 분할 납부 1,2,3차를 전액 납부한 후<br>
			분할 납부 신청 페이지에서 이용이 가능합니다.</h2>
		</div>
	</c:if>
	<c:if test="${resultCount eq 0 }">
		<div class="text-center" style="width: 100%; height:480px; margin-top: 300px;">
			<h2>등록금 납부 기간이 아닙니다.</h2>
		</div>
	</c:if>
	<security:authorize access="isAuthenticated()">
	<security:authentication property="principal" var="principal"/>
	<c:set var="authMember" value="${principal.realMember }" />
	</security:authorize>
	<!-- <button id="getStatus">결제상태</button> -->
	
	
	<!-- <input type="hidden" name="payNo" id="payNo"/> -->
	<input type="hidden" id="getToken"/>
	<input type="hidden" id="getStatus"/>
	<!-- <input type="hidden" id="loadModal" data-toggle="modal" data-target="#centermodal"/> -->
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
	            <div class="modal-body" style="text-align: center;">
	            	<p>
	            	아직 가상계좌를 발급하지 않으신 상태입니다.<br>
	            	 가상계좌를 발급받아주세요
	            	</p>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-light" data-dismiss="modal">닫기</button>
	                <button type="button" id="check_module" class="btn btn-primary">가상계좌 발급</button>
	            </div>
	        </div><!-- /.modal-content -->
	   </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<!-- 사용자의 이메일이 구글일때 뜨는 모달창 -->
<!-- 	<div class="modal fade" id="virtualAccountGoogleCheck" tabindex="-1" role="dialog" aria-hidden="true"> -->
<!-- 	    <div class="modal-dialog modal-dialog-centered"> -->
<!-- 	        <div class="modal-content"> -->
<!-- 	            <div class="modal-header"> -->
<!-- 	               	<h3 class="modal-title" id="myCenterModalLabel">알림창</h3> -->
<!-- 					<button type="button" class="close" data-dismiss="modal" -->
<!-- 	                    aria-hidden="true">×</button> -->
<!-- 	            </div> -->
<!-- 	            <div class="modal-body" style="text-align: center;"> -->
<!-- 	            	<p> -->
<!-- 	            	가상계좌 발급 공지를 이메일로 받으시려면 구글 계정에 들어가셔서 <br>'보안'을 클릭 후 보안 수준이 낮은 앱의 액세스'를 <br><span class="text-danger">사용 안함</span>으로 설정하시면 메일을 받으실 수 있습니다. -->
<!-- 	            	</p> -->
<!-- 	            	<br> -->
<!-- 					<p> -->
<!-- 					이메일로 공지를 받으신 후에는 보안을 위해 <br>액세스를 다시 <span class="text-info">사용함</span>으로 바꿔주시기 바랍니다. -->
<!-- 					</p>	            	 -->
<!-- 	            	<br> -->
<!-- 	            	<a href="https://myaccount.google.com/security" target="_blank" id="settingGmail">설정하러 가기<i class="fas fa-check"></i></a> -->
<!-- 				</div> -->
<!-- 				<div class="modal-footer"> -->
<!-- 	                <button type="button" class="btn btn-light" data-dismiss="modal">닫기</button> -->
<!-- 	                <button type="button" id="check" class="btn btn-primary">가상계좌 발급</button> -->
<!-- 	                <button type="button" id="check_module" class="btn btn-primary">가상계좌 발급</button> -->
<!-- 	            </div> -->
<!-- 	        </div>/.modal-content -->
<!-- 	   </div>/.modal-dialog -->
<!-- 	</div>/.modal -->
	
	<div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered">
	        <div class="modal-content">
	            <div class="modal-header">
	               	<h3 class="modal-title" id="myCenterModalLabel">알림창</h3>
					<button type="button" id="closeButton"class="class" data-dismiss="modal"
	                    aria-hidden="true">×</button>
	            </div>
	            <div class="modal-body" style="text-align: center;">
	            	<p>
	            	통보 받은 가상계좌로 등록금을 입금하신 후에야 확인서 발급이 가능합니다.<br>
	            	</p>
				</div>
	        </div><!-- /.modal-content -->
	   </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
<!-- <button onclick="playAlert" id="play">test</button> -->
<script type="text/javascript">
$(function(){
	$('#iframId').ready(function(){
		$.ajax({
			url : "${cPath}/pdf/pdfInsert.do",
			data : {"pdfName":"등록금고지서"},
			method : "post",
			dataType : "json",
		});
	});
});


// 	$('#create_pdf').click(function() {
// 	  //pdf_wrap을 canvas객체로 변환
// 	  html2canvas($('#pdf')[0]).then(function(canvas) {
// 	    var imgData = canvas.toDataURL('image/png'); //캔버스를 이미지로 변환
// 	    var imgWidth = 190;
// 	    var pageHeight = imgWidth * 1.414;
// 	    var imgHeight = canvas.height * imgWidth / canvas.width;
// 	    var heightLeft = imgHeight;
// 	    var margin = 15; // 출력 페이지 여백설정
// 	    var position = 10;
// // 	    doc.addImage(imgData, 'PNG', 0, 0); //이미지를 기반으로 pdf생성
// 	    var doc = new jsPDF('p', 'mm'); //jspdf객체 생성
	    
// 	    doc.addImage(imgData, 'PNG', margin, position, imgWidth, imgHeight);
// 	    heightLeft -= pageHeight;
// 	 // 한 페이지 이상일 경우 루프 돌면서 출력
// 	    while (heightLeft >= 20) {
// 	        position = heightLeft - imgHeight;
// 	        doc.addPage();
// 	        doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
// 	        heightLeft -= pageHeight;
// 	    }
// 	    doc.save('sample-file.pdf'); //pdf저장
// 	  });
// 	});
// $('#create_pdf').on("click", function(){
	
// 	/* documentDefinition : pdf파일에 들어갈 내용 및 여러가지를 정의 */
// 	var documentDefinition = {
// 	//content : pdf의 내용을 정의
// 	content: [
// 	{
// 	text: '가나다라마바사'
// 	}, // 스타일 적용 없이 그냥 출력
// 	{
// 	text: 'Another paragraph, this time a little bit longer to make sure, this line will be divided into at least two lines',
// 	bold: true
// 	}, // 텍스트에 bold 주기
// 	{
// 	text: '가나다라마바사아자타카타파하',
// 	style: 'style_test'
// 	}, // style 부분에 정의된 style_test 적용해보기 및 한글 꺠짐 테스트
// 	{
// 	style: 'tableExample',
// 	table: {
// 	widths: [100, '*', 200, '*'],
// 	body: [
// 	['width=100', 'star-sized', 'width=200', 'star-sized'],
// 	['fixed-width cells have exactly the specified width', {
// 	text: 'nothing interesting here',
// 	italics: true,
// 	color: 'gray'
// 	}, {
// 	text: 'nothing interesting here',
// 	italics: true,
// 	color: 'gray'
// 	}, {
// 	text: 'nothing interesting here',
// 	italics: true,
// 	color: 'gray'
// 	}]
// 	]
// 	}
// 	}//테이블 그리기
// 	],
// 	//하단의 현재페이지 / 페이지 수 넣기
// 	footer: function (currentPage, pageCount) {
// 	return {
// 	margin: 10,
// 	columns: [{
// 	fontSize: 9,
// 	text: [{
// 	text: '--------------------------------------------------------------------------' +
// 	'\n',
// 	margin: [0, 20]
// 	},
// 	{
// 	text: '' + currentPage.toString() + ' of ' +
// 	pageCount,
// 	}
// 	],
// 	alignment: 'center'
// 	}]
// 	};

// 	},
// 	//필요한 스타일 정의하기
// 	styles: {
// 	style_test: {
// 	fontSize: 18,
// 	bold: true,
// 	margin: [0, 0, 0, 0],
// 	alignment: 'center'
// 	},
// 	tableExample: {
// 	margin: [0, 5, 0, 15]
// 	}
// 	},

// 	// 페이지 크기 용지의 크기 사이즈 넣기 또는 특정 사이즈 넣기 { width: number, height: number }
// 	pageSize: 'A4',

// 	/* 페이지 방향 portrait : 가로 , landscape : 세로 */
// 	pageOrientation: 'portrait',
// 	};

// 	var pdf_name = 'pdf파일 만들기.pdf'; // pdf 만들 파일의 이름
// 	pdfMake.createPdf(documentDefinition).download(pdf_name);

// });

	let imp_uid;
	let merchant_uid;
	let payExcept;
	let depFee;
	let splanEndDate;
	let vbankDue = '202102252329';
	let vbankCheck = '${vaTuitionVO.payImp}'+"";
	let dateCheck = '${resultCount}';
	let memMail = '${authMember.memMail}';
	console.log(memMail);
	window.onload = function(){
// 		if(memMail.includes('gmail')){
// 			console.log("구글아이디임");
// 		} else {
// 			console.log("구글 아님");
// 		}
// 		$("#virtualAccountGoogleCheck").on("show.bs.modal",function(){
// 			$("#check").hide();
// 			$("#settingGmail").on("click", function(){
// 				$("#check").show();
// 			});
// 		});

		if(dateCheck == '1'){
			if(vbankCheck == 0){
// 				if(memMail.includes('gmail')){
// 					$("#virtualAccountGoogleCheck").modal();
// 				} else {
					$("#virtualAccountCheck").modal();
// 				}
			}
		}
		//------------------------등록금 지정-------------------------
		$.ajax({
			 url: "${cPath}/lms/student/tuition/getDepFee.do",
			    method: "post", // POST method
			    dataType : 'json',
			    headers: { "Content-Type": "application/json" }, // "Content-Type": "application/json"
			    success:function(resp){
					console.log("등록금 : "+resp.vatuitionVO.depFee);
					depFee = resp.vatuitionVO.depFee;
				}
				, error : function(request, error){
			    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			    }
		});
		
		//----------------------- 토큰 발급 ----------------------------
// 			$.ajax({
// 			    url: "${cPath}/lms/student/tuition/getToken.do",
// 			    method: "post", // POST method
// 			    dataType : 'json',
// 			    headers: { "Content-Type": "application/json" }, // "Content-Type": "application/json"
// 			    success:function(resp){
// 					console.log("토큰 : "+resp.result);
// // 					$("#getStatus").click();
// 				}
// 				, error : function(request, error){
// 			    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
// 			    }
// 		  });
		
		//---------------------------가상 계좌 마감일 가져오기 ------------------------------
		$.ajax({
			url : '${cPath}/lms/student/tuition/tuitionbillPaySatrtDate.do'
			, method : 'post'
			, dataType : 'json'
			, success:function(resp){
				splanEndDate = resp.getStartDate.splanEnd;
				console.log(splanEndDate);
			}
			, error : function(request, error){
		    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
		    }
		});
			
		//----------------------------계좌번호, 은행 없으면 버튼 보이기----------------------
// 		$.ajax({
// 			url : '${cPath}/lms/student/tuition/tuitionbillNullCheck.do'
// 			, method : 'post'
// 			, dataType : 'json'
// 			, success:function(resp){
// 				let payAcn = resp.vatuitionVO.payAcn;
// 				let payBank = resp.vatuitionVO.payBank;
// 				if(payAcn != null || payAcn != null){
// 					$("#check_module").hide();
// 				}
// 			}
// 			, error : function(request, error){
// 		    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
// 		    }
// 		});
		
// 		$.ajax({
// 			url : '${cPath}/lms/student/tuition/tuitionbillpayExcept.do'
// 			, method : 'post'
// 			, dataType : 'json'
// 			, success:function(resp){
// 				payExcept = resp.vbanktuitionVO.payExcpect;
// 			}
// 			, error : function(request, error){
// 		    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
// 		    }
// 		});
	}
	
	
	let vbankNum;
	let vbabkName;
	let memName = '${authMember.memName}';
	let memTel = '${authMember.memTel}';
	let amount;
// 	let payNo =	$("#payNo").val();
	$("#check_module").click(function() {
		$(".close").click();
		var IMP = window.IMP; // 생략가능
		IMP.init('imp80117856');
		IMP.request_pay({
			pg : 'settle', // version 1.1.0부터 지원.
			pay_method : 'vbank',
			merchant_uid : 'merchant_' + new Date().getTime(),
			name : '가상계좌 발급',
			amount : depFee,
			buyer_email : 'yourEmailID@****.com',
			buyer_name : memName,
			buyer_tel : memTel,
			vbank_due : splanEndDate,
			m_redirect_url : 'https://www.yourdomain.com/payments/complete',
		}, function(rsp) {
			if (rsp.success) {
				var msg = '가상계좌 발급이 완료되었습니다.';
				msg += '고유ID : ' + rsp.imp_uid;
				msg += '상점 거래ID : ' + rsp.merchant_uid;
				msg += '결제 금액 : ' + rsp.paid_amount;
				msg += '가상계좌번호 : ' + rsp.vbank_num;
				msg += '은행 : ' + rsp.vbank_name;
				vbankNum = rsp.vbank_num;
				vbankName = rsp.vbank_name;
				amount = rsp.paid_amount;
				imp_uid = rsp.imp_uid;
				success = rsp.success;
				console.log(success);
				//--------------------------------------------------------
				$.ajax({
					url : '${cPath}/lms/student/tuition/tuitionbillUpdate.do'
					, method : 'post'
					, data :
						{
						"payAcn" : vbankNum
						, "payBank" : vbankName
						, "payExcpect" : amount
						, "payImp" : imp_uid
						, "payEnd" : splanEndDate
						}
					, dataType : 'json'
					, success:function(resp){
						$("#successModal").modal();
						$("#closeButton").on("click", function(){
							location.reload();
						});
						$("#successModal").on("click", function(){
							location.reload();
						});
						console.log("Update 성공 : "+resp.result);
						$("#getToken").click();
					}
					, error : function(request, error){
				    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
				    }
				});
			} else {
				var msg = '발급에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
			}
			console.log(msg);
		});
	});
	
	$("#check").click(function() {
		$(".close").click();
		var IMP = window.IMP; // 생략가능
		IMP.init('imp80117856');
		IMP.request_pay({
			pg : 'settle', // version 1.1.0부터 지원.
			pay_method : 'vbank',
			merchant_uid : 'merchant_' + new Date().getTime(),
			name : '가상계좌 발급',
			amount : depFee,
			buyer_email : 'yourEmailID@****.com',
			buyer_name : memName,
			buyer_tel : memTel,
			vbank_due : splanEndDate,
			m_redirect_url : 'https://www.yourdomain.com/payments/complete',
		}, function(rsp) {
			console.log(rsp);
			if (rsp.success) {
				var msg = '가상계좌 발급이 완료되었습니다.';
				msg += '고유ID : ' + rsp.imp_uid;
				msg += '상점 거래ID : ' + rsp.merchant_uid;
				msg += '결제 금액 : ' + rsp.paid_amount;
				msg += '가상계좌번호 : ' + rsp.vbank_num;
				msg += '은행 : ' + rsp.vbank_name;
				vbankNum = rsp.vbank_num;
				vbankName = rsp.vbank_name;
				amount = rsp.paid_amount;
				imp_uid = rsp.imp_uid;
				success = rsp.success;
				console.log(success);
				//--------------------------------------------------------
				$.ajax({
					url : '${cPath}/lms/student/tuition/tuitionbillUpdate.do'
					, method : 'post'
					, data :
						{
						"payAcn" : vbankNum
						, "payBank" : vbankName
						, "payExcpect" : amount
						, "payImp" : imp_uid
						, "payEnd" : splanEndDate
// 						, "payNo" : payNo
// 						,imp_uid : rsp.imp_uid
// 						,merchant_uid : rsp.merchant_uid
						}
					, dataType : 'json'
					, success:function(resp){
						$("#successModal").modal();
						$("#closeButton").on("click", function(){
							location.reload();
						});
						$("#successModal").on("click", function(){
							location.reload();
						});
						console.log("Update 성공 : "+resp.result);
						$("#getToken").click();
					}
					, error : function(request, error){
				    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
				    }
				});
			} else {
				var msg = '발급에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
			}
			console.log(msg);
		});
	});
	
	
	
	
</script>