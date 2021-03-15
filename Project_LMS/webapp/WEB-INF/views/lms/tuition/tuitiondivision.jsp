<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<div class="container-fluid">
	<div class="row">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 등록금 > 분할 납부 신청
			</h5>
		</div>
	</div>
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-9 text-left">
			<h2 class="font-weight-bold">분할 납부 신청</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-11">
			<h5 class="pb-5 ml-2">분할 납부를 신청할 수 있습니다.</h5>
		</div>
		<div class="col-1 m-auto">
			<c:if test="${firstPayCheck.payAmount eq 0 }">
             	<c:if test="${not empty tuitionVO }">
	              	<c:if test="${tuitionVO.payDivision ne 'Y' }">	
		               	<div class="col-xm-4 ml-auto">
							<button id="tuitionBtn" class="btn btn-info">신청</button>
		               	</div>
	              	</c:if>	
             	</c:if>
            </c:if>
		</div>
        <div id="alertArea" class="alert alert-danger alert-dismissable fade show">
			<strong>동의 여부를 체크하세요.</strong>
           	<button type="button" class="close" data-dismiss="alert">&times;</button>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<table class="table">
				<thead class="thead-light text-center">
					 <tr>
					<th>원납액</th>
						<c:if test="${not empty tuitionVO.depFee }">
							<td class="text-right">
								<fmt:formatNumber value="${tuitionVO.depFee }" maxFractionDigits="3"/>원
							</td>
						</c:if>
						<c:if test="${ empty tuitionVO.depFee }">
							<td class="text-right">0 원</td>
						</c:if>
					<th>장학내역</th>
						<c:if test="${not empty tuitionVO.sfundValue }">
							<td class="text-right">
								<fmt:formatNumber value="${tuitionVO.sfundValue }" maxFractionDigits="3"/>원
							</td>
						</c:if>
						<c:if test="${empty tuitionVO.sfundValue }">
							<c:set var="tuitionVO.sfundValue" value="0"/>
							<td class="text-right">0원</td>
						</c:if>
					<th>실납입액</th>
					<c:set var="realTuition" value="${tuitionVO.depFee - tuitionVO.sfundValue}" />
						<c:if test="${not empty realTuition }">
							<c:if test="${realTuition ge 0 }">
								<td class="text-right">
									<c:set var="real"/>
									<fmt:formatNumber value="${realTuition }" maxFractionDigits="3"/>원
								</td>
							</c:if>
							<c:if test="${realTuition lt 0 }">
								<td class="text-right">0 원</td>
							</c:if>
						</c:if>
					<th>처리상태</th>
					<td id="TdText">
					<c:if test="${empty tuitionVO }">	
						<span>신청 대상자가 아닙니다.</span>
					</c:if>
					<c:if test="${not empty tuitionVO }">	
						<c:if test="${not empty tuitionVO.payDivision }">
							<c:if test="${tuitionVO.payDivision eq 'Y'}">
								<c:if test="${not empty tuitionVO.payCheck }">
									<c:if test="${tuitionVO.payCheck eq 'Y' }">									
									 	<span id="center"> 승인</span>
									 </c:if>	
									<c:if test="${tuitionVO.payCheck eq 'N' }">										
									 	<span id="center"> 불가</span>
								 	</c:if>
									<c:if test="${tuitionVO.payCheck eq 'A' }">										
									 	<span id="center"> 처리 대기</span>
								 	</c:if>
							 	</c:if>
						 	</c:if>
							<c:if test="${tuitionVO.payDivision eq 'N'}">
							 	<span id="center">신청 안함</span>
					 		</c:if>
						</c:if>
						<c:if test="${empty tuitionVO.payDivision }">
						 	<span id="center">신청 대상자가 아닙니다.</span>
						</c:if>
					</c:if>
					</td>
                         </tr>
				</thead>
			</table>
		</div>
	</div>
<br><br><br>
<c:if test="${tuitionVO.payCheck eq 'Y' }">	
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-9 text-left">
			<h2 class="font-weight-bold">분할 납부 현황</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-11">
			<h5 class="pb-5 ml-2">분할 신청 현황를 확인할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row my-3">
		<div class="input-group col-12 d-flex justify-content-end align-items-stretch">
          	<div id="inputUI" class="col-3 d-flex justify-content-end align-items-stretch">
	        	<c:set var="divisionGetDateList" value="${divisionGetDateList }"/>
				<c:if test="${not empty divisionGetDateList }">
	           		<c:forEach items="${divisionGetDateList }" var="division">
	           			<c:choose>
	           				<c:when test="${division.payStart ge 0 && division.payEnd ge 0}">
	              				<div class="col-xm-4 ml-auto mr-4">
									<button id="payBtn" class="btn btn-info">계좌발급</button>
	                			</div>
							</c:when>
						</c:choose>
					</c:forEach>
	           	</c:if>
			</div>
       </div>
	</div>
	<div class="row">
	    <div class="col-lg-12">
            <div class="table-responsive ">
                <table class="table text-center">
                    <thead class="thead-light">
						<tr>
							<th class="font-weight-bold" colspan="2">분 납 차 수</th>
							<th class="font-weight-bold" colspan="2">납부 예정 금액</th>
							<th class="font-weight-bold" colspan="2">실 납 입 액</th>
							<th class="font-weight-bold" colspan="2">납 부 계 좌</th>
							<th class="font-weight-bold" colspan="2">납 부 일 자</th>
							<th class="font-weight-bold" colspan="2">납 부 여 부</th>
							<th class="font-weight-bold" colspan="2">확인서 발급</th>
						</tr>
					</thead>
					<tbody>
					<c:set var="total" value="0"/>
					<c:if test="${not empty tuitionList }">
						<c:forEach items="${tuitionList }" var="tuition" varStatus="status">
							<tr>
								<c:set var="total" value="${total+tuition.payAmount }" />
								<td colspan="2" id="center">${status.count }차</td>
								<c:if test="${tuition.payNumber ne 0}">
									<td colspan="2" class="text-right">
										<fmt:formatNumber value="${tuition.payExcpect }" maxFractionDigits="3"/>원
									</td>
								</c:if>
								<c:if test="${empty tuition.payExcpect }">
									<td colspan="2" class="text-right">0 원</td>
								</c:if>
								<c:if test="${not empty tuition.payAmount }">
									<td colspan="2"class="text-right">
										<fmt:formatNumber value="${tuition.payAmount }" maxFractionDigits="3"/>원
									</td>
								</c:if>
								<c:if test="${empty tuition.payAmount }">
									<td colspan="2" class="text-right">0 원</td>
								</c:if>
								<c:if test="${not empty tuition.payAcn and  not empty tuition.payBank}">
									<td colspan="2">${tuition.payBank } ${tuition.payAcn }</td>
								</c:if>
								<c:if test="${empty tuition.payAcn or empty tuition.payBank}">
									<td colspan="2"> - </td>
								</c:if>
								<c:if test="${not empty tuition.payStart and not empty tuition.payEnd}">
									<td colspan="2" id="center">${tuition.payStart } ~ ${tuition.payEnd  }</td>
								</c:if>
								<c:if test="${empty tuition.payStart or empty tuition.payEnd }">
									<td colspan="2" id="center"> - </td>
								</c:if>
								<c:if test="${tuition.payAmount ne 0}">
									<td colspan="2" id="center">
									완납
									</td>
								</c:if>
								<c:if test="${tuition.payAmount eq 0}">
									<td colspan="2" id="center">
									미납
									</td>
								</c:if>
								<c:if test="${tuition.payAmount ne 0}">
									<td>
									<a href="#none" onClick="window.open('${cPath }/lms/student/tuition/tuitionDivisionPayPDF.do?payNumber=${tuition.payNumber }','new','scrollbars=yes,resizable=no width=800px height=1000px, left=650,top=50');return false"><button class="btn btn-success"><i class="fas fa-file-pdf"></i>&nbsp;확인서 발급</button></a>
									</td>
								</c:if>
								<c:if test="${tuition.payAmount eq 0}">
								<td>-</td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if>
			 			<tr>
			    			<c:if test="${realTuition-total lt 0}">
								<td colspan="12" rowspan="1" class="text-center">총 납부 금액 : <fmt:formatNumber value="${total }" maxFractionDigits="3"/>원<br> -  완납  - </td>
							</c:if>
							<c:if test="${realTuition-total eq 0}">
								<td colspan="12" rowspan="1" class="text-center">총 납부 금액 : <fmt:formatNumber value="${total }" maxFractionDigits="3"/>원<br> -  완납  - </td>
							</c:if>
							<c:if test="${realTuition-total gt 0 }">
								<td colspan="12" rowspan="1"class="text-center">현재까지 총 납부 금액 : <fmt:formatNumber value="${total }" maxFractionDigits="3"/>원<br> -  미납  -</td>
							</c:if>
						</tr>
					</tbody>
				</table>
	            <c:if test="${realTuition-total lt 0 || realTuition-total eq 0}" >
	        		<a href="#none" onClick="window.open('${cPath }/lms/student/tuition/tuitionDivisionPDF.do','new','scrollbars=yes,resizable=no width=800px height=1000px, left=650,top=50');return false"><button class="btn btn-success float-right"><i class="fas fa-file-pdf"></i>&nbsp;완납 확인서 발급</button></a>
	            </c:if>
			</div>
		</div>
	</div>
</c:if>
</div>

<div class="modal fade" id="tuitionModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="myCenterModalLabel">분할 납부 신청</h3>
				<button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
				<div id="searchArea" class="card-body">
					<div class="input-group">
						<p><span class="badge badge-warning">주의</span>동의 여부를 체크하셔야 신청이 가능합니다.</p>
							<table id="table1" class="table table-hover table-primary">
							  	<tbody>
									<tr>	
										<td>처리상태</td>
										<td >
											<c:if test="${tuitionVO.payDivision eq 'Y'}">
												<c:if test="${tuitionVO.payCheck eq 'Y' }">									
												 	<span> 승인</span>
												 </c:if>	
												<c:if test="${tuitionVO.payCheck eq 'N' }">										
												 	<span> 불가</span>
											 	</c:if>
												<c:if test="${tuitionVO.payCheck eq 'A' }">										
												 	<span> 처리 대기</span>
											 	</c:if>
										 	</c:if>
											<c:if test="${tuitionVO.payDivision eq 'N'}">
											 	<span>신청 안함</span>
										 	</c:if>
										</td>
									</tr>
									<tr>	
										<td>동의 여부</td>
										<td>
											<div class="btn-group" id="radioGroup">
												<div class="custom-control custom-radio">
													<input type="radio" name="radio_option" id="radio-1" class="custom-control-input"  value="Y">
													<label class="custom-control-label" for="radio-1">동의</label>
												</div>
												<div class="custom-control custom-radio">
													<input type="radio" name="radio_option" id="radio-2" class="custom-control-input" checked="checked" value="N"> 
														<label class="custom-control-label" for="radio-2">미동의</label>
												</div>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						<button id="modalBtn" type="button" class="btn btn-primary btn-lg " data-dismiss="modal">
							신청
							<i class="fas fa-location-arrow"></i>
						</button>
					</div>
				</div>
			</div>
        </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

	<!-- 아직 0차에 가상계좌를 발급 받지 않을 경우 -->
	<div class="modal fade" id="virtualAccountStatusCheck" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered">
	        <div class="modal-content">
	            <div class="modal-header">
	               	<h3 class="modal-title" id="myCenterModalLabel">알림창</h3>
					<button type="button" class="close" data-dismiss="modal"
	                    aria-hidden="true">×</button>
	            </div>
	            <div class="modal-body text-center"">
	            	<p>
						등록금 고지서를 먼저 확인하세요.
	            	</p>
				</div>
				<div class="modal-footer">
		                <button type="button" onclick="location.href='${cPath}/lms/student/tuition/tuitionbill.do'" class="btn btn-primary">확인하러 가기</button>
		            </div>
	        </div><!-- /.modal-content -->
	   </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<!-- 등록금 납부 기간이 아닐 때 뜨는 모달 -->
	<div class="modal fade" id="NoDateCheck" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered">
	        <div class="modal-content">
	            <div class="modal-header">
	               	<h3 class="modal-title" id="myCenterModalLabel">알림창</h3>
					<button type="button" class="close" data-dismiss="modal"
	                    aria-hidden="true">×</button>
	            </div>
	            <div class="modal-body" style="text-align: center;">
	            	<p>
	            	등록금 납부 기간이 아닙니다.
	            	</p>
				</div>
	        </div><!-- /.modal-content -->
	   </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
<security:authorize access="isAuthenticated()">
<security:authentication property="principal" var="principal"/>
<c:set var="authMember" value="${principal.realMember }" />
</security:authorize>
<input type="hidden" id="getStatus"/>
<script type="text/javascript">
let countSchedule = ${countSchedule };
let imp_uid;
let vbankNum;
let vbabkName;
let memName = '${authMember.memName}';
let memTel = '${authMember.memTel}';
let amount;
let vbankCheck = '${vaTuitionVO.payImp}'+"";
let payExpect;
let payCountCheck = '${countPayCheck}';
let divisionAmount = '${divisionAmount.payAmount}'+"";
let payDivision = '${tuitionVO.payDivision}';

	if(payDivision == 'Y'){
		$("#tuitionBtn").hide();
	}

	if(countSchedule == 0){
		$("#NoDateCheck").modal();
	}
	$("#alertArea").hide();
	<c:if test="${tuitionMessage eq 'OK'}">
		alert("분할 납부 신청 성공");
	</c:if>	
	<c:if test="${tuitionMessage eq 'FAILE'}">
		alert("분할 납부 신청 실패");
	</c:if>	
	let firstPayImpCheck = '${firstPayImp.payImp}'+"";
	$("#tuitionBtn").on("click", function(){
		if(firstPayImpCheck == 0){
			$("#virtualAccountStatusCheck").modal();
		}else{
			$("#tuitionModal").modal();	
		}
	});
	
	
	$("#modalBtn").on("click", function(){
		let radioVal = $('input[name="radio_option"]:checked').val();
		if(radioVal =='Y'){
		$.ajax({
				url : "${cPath}/lms/student/tuition/tuitionUpdate.do",
				method : "get"
			});
			$("#tuitionBtn").hide();
			$("#alertArea").hide();
			$("#TdText").empty();
			$("#TdText").text("처리 대기");
		}
		if(radioVal =='N'){
			$("#alertArea").show();
		}
	});
	
	
	if(vbankCheck != 0 ){
		//----------------------- 토큰 발급 ----------------------------
		$.ajax({
		    url: "${cPath}/lms/student/tuition/getTokenTuitionDivision.do",
		    method: "post", // POST method
		    dataType : 'json',
		    headers: { "Content-Type": "application/json" }, // "Content-Type": "application/json"
		    success:function(resp){
				console.log("토큰 : "+resp.result);
				if(divisionAmount == 0){
					$("#getStatus").click();
				}
			}
			, error : function(request, error){
		    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
		    }
	    });
		$("#getStatus").on("click",function(){
			$.ajax({
			    url: "${cPath}/lms/student/tuition/getStatusTuitionDivision.do",
			    method: "post", // POST method
			    dataType : 'json',
			    headers: { "Content-Type": "application/json" }, // "Content-Type": "application/json"
			    success:function(resp){
			    	status = resp.status;
			    	if(status == "paid"){
			    		console.log(status);
//	 		    		$("#insteadPdfArea").hide();
			    		let paidDate = resp.formattedDate;
			    		let paidAmount = resp.paid_amount;
			    		$.ajax({
			    			url :'${cPath}/lms/student/tuition/paidDivisionUpdate.do'
			    			, method : "post"
			    			, dataType : "json"
			    			, data :
			    			{
			    				"payAmount" : paidAmount
			    				, "payDate" : paidDate
			    			}
			    			,success : function(resp){
			    			}
		    				, error : function(request, error){
		    			    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
		    			    }
			    		});
			    	}
				}
				, error : function(request, error){
			    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			    }
		  });
		});
		
		if(payCountCheck != 0){
			$.ajax({
				url : '${cPath}/lms/student/tuition/tuitionDivisionSelectPayExpect.do'
				, method : 'post'
				, dataType : 'json'
				, success:function(resp){
					payExpect = resp.result.payExcpect;
					console.log("payExpect : " + payExpect);
				}
				, error : function(request, error){
			    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			    }
			});
		}
	}
	
	
	$("#payBtn").on("click",function(){
		var IMP = window.IMP; // 생략가능
		IMP.init('imp80117856');
		// 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
		// i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
		IMP.request_pay({
			pg : 'settle', // version 1.1.0부터 지원.
			pay_method : 'vbank',
			merchant_uid : 'merchant_' + new Date().getTime(),
			name : '가상계좌 발급',
			amount : payExpect,
			buyer_email : 'yourEmailID@****.com',
			buyer_name : memName,
			buyer_tel : memTel,
// 			vbank_due : splanEndDate,
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
// 				amount = rsp.paid_amount;
				imp_uid = rsp.imp_uid;
// 				success = rsp.success;
				$.ajax({
					url : '${cPath}/lms/student/tuition/tuitionDivisionUpdate.do'
					, method : 'post'
					, data :
						{
						"payAcn" : vbankNum
						, "payBank" : vbankName
						, "payImp" : imp_uid
						}
					, dataType : 'json'
					, success:function(resp){
						
					}
					, error : function(request, error){
				    	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
				    }
				});
				$("#payBtn").hide();
			} else {
				var msg = '발급에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
			}
			console.log(msg);
		});
	});
</script>