<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />	
<div class="container-fluid">
<div class="row">
		<div class="col-12">
			<h5 class="ml-2"><i class="fas fa-home"></i> MyClass > 시험 </h5>
		</div>
	</div>
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-9 text-left">
			<h2 class="font-weight-bold">시험</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<h5 class="pb-5 ml-2">현재 응시 가능한 시험 목록과 점수를 조회할 수 있습니다.</h5>
		</div>
	</div>
		<div class="row">
		<div class="col-12">
			<table class="table">
				<thead class="thead-light">
                <tr>
                    <th class="font-weight-bold">시험 구분</th>
                    <th class="font-weight-bold">응시 시작 날짜</th>
                    <th class="font-weight-bold">응시 마감 날짜</th>
                    <th class="font-weight-bold">총 문항</th>
                    <th class="font-weight-bold">응시 시간</th>
                    <th class="font-weight-bold">응시 구분</th>
                </tr>
            </thead>
            <tbody>
                	<c:choose>
                		<c:when test="${not empty testMap }">
	               			<c:forEach items="${testMap }" var="test" varStatus="status">
			                <tr>
			                    <td>
			                    	<c:choose>
										<c:when test="${test.value.testType eq 'M'}">
											<h4>중간 고사</h4>
										</c:when>
										<c:when test="${test.value.testType eq 'F'}">
											<h4>기말 고사</h4>
										</c:when>
									</c:choose>	
		                    	</td>
			                    <td>${fn:substring(test.value.testStart, 0, 16)}</td>
			                    <td>${fn:substring(test.value.testEnd, 0, 16)}</td>
			                    <td>${test.value.testQno }문항</td>
			                    <td>${test.value.totalTime }분</td>
			                    <td>
		                    	<!-- 만약 응시를 했으면 응시 시작, 쳤으면 점수 확인  -->
			                    	<c:set var="testKey" value="${test.key }"/>
			                    	<c:choose>
			                    		<c:when test="${fn:contains(stuAnsList, testKey)}">
				                    		<button type="button" class="btn btn-success btn-rounded checkBtn"
				                    			data-testno="${test.key }" data-testtype="${test.value.testType }" 
				                    			><i class="fas fa-clipboard-list"></i> 점수 확인</button>
			                    		</c:when>
			                    		<c:otherwise>
				                    		<button type="button" class="btn btn-outline-success btn-rounded testBtn"
				                    			data-testno="${test.key }" data-startdate="${fn:substring(test.value.testStart, 0, 16)}" data-enddate="${fn:substring(test.value.testEnd, 0, 16)}">
				                    		<i class="fas fa-check"></i> 응시 시작</button>
			                    		</c:otherwise>
			                    	</c:choose>
			                   	</td>
		               		 </tr>
	                   		</c:forEach>
                		</c:when>
                		<c:otherwise>
                			<tr>
                				<td colspan="5">현재 등록된 시험이 존재하지 않습니다.</td>
                			</tr>
                		</c:otherwise>
                	</c:choose>
            </tbody>
        </table>
    </div>
</div>
</div>
<!-- ============= 응시 확인 모달 ================ -->
<!-- ========================================= -->
<div id="startModal" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="danger-header-modalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header modal-colored-header bg-primary">
				<h4 class="modal-title font-weight-bold" id="danger-header-modalLabel">응시 여부 확인</h4>
			 <button type="button" class="close" data-dismiss="modal"
                      aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<h4 class="font-weight-bold">해당 시험에 응시하시겠습니까?</h4>
				<span class="badge badge-warning">주의</span> 응시 시작 이후 재 응시는 불가합니다.
			</div>
			<div class="modal-footer" style="margin: auto;">
				<button type="button" class="btn btn-light" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-primary" id="goTestBtn">응시</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- ============= 응시 불가 모달================ -->
<!-- ========================================= -->
 <div id="noAns" class="modal fade" tabindex="-1" role="dialog"
     aria-hidden="true">
     <div class="modal-dialog modal-sm modal-dialog-centered">
         <div class="modal-content">
             <div class="modal-body p-4">
                 <div class="text-center">
                     <i class="dripicons-warning h1 text-warning"></i>
                     <h4 class="font-weight-bold">응시 시간 확인</h4>
                     <p class="mt-3">현재 시험 응시가 불가능한 기간입니다.</p>
                     <button type="button" class="btn btn-warning my-2"
                         data-dismiss="modal">확인</button>
                 </div>
             </div>
         </div><!-- /.modal-content -->
     </div><!-- /.modal-dialog -->
 </div><!-- /.modal -->
<!-- ============= 점수 확인 모달================ -->
<!-- ========================================= -->
  <div class="modal fade" id="checkScore" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
              <div class="modal-header">
                  <h4 class="modal-title font-weight-bold">시험 점수 확인</h4>
                  <button type="button" class="close" data-dismiss="modal"
                      aria-hidden="true">×</button>
              </div>
              <div class="modal-body">
              	<table class="table table-bordered">
					<tr>
						<td class="font-weight-bold table-dark w-25">시험 명</td>
						<td id="testName"></td>
					</tr>
					<tr>
						<td class="font-weight-bold table-dark w-25">총  점</td>
						<td id="testTotal"></td>
					</tr>
				</table>
              </div>
          </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->
 
<form:form id="testView" action="${cPath}/myclass/${lecCode}/testView.do">
	<input type="hidden" name="testNo"/>
</form:form>
<form:form id="stuScoreForm" action="${cPath }/myclass/${lecCode}/stuScoreList.do" commandName="score">
	<input type="hidden" name="testNo"/>
	<input type="hidden" name="memId" value="${authMember.memId }"/>
	<input type="hidden" name="testType"/>
</form:form>
<script type="text/javascript">
$(function(){
	// 오늘날짜 구하기
	 nStart = new Date().getTime();      //시작시간  cd체크(단위 ms)
	 let date = new Date();
	 let year = date.getFullYear(); //년도
	 let month = date.getMonth()+1; //월
	 let day = date.getDate(); //일
	 let hours = date.getHours(); // 시간
	 let minutes = date.getMinutes();; // 분
	 
     if ((day+"").length < 2) {       
        day = "0" + day;
     }
	 if((hours+"").length < 2){
		 hours = "0" + hours;
	 }
	 if((minutes+"").length < 2){
		 minutes = "0" + minutes;
	 }
     today = year+"-0"+month+"-"+day+" "+hours+":"+minutes; // 오늘 날짜 (2017-02-07 10:00)
   	 
   	$(".testBtn").on("click", function(){
   		let startDate = $(this).data("startdate");
   		let endDate = $(this).data("enddate");
   		console.log(today)
	   	console.log(startDate)
	   	console.log(endDate)
   		if(!(startDate<=today && today<=endDate)){
   			$("#noAns").modal();
   		} else {
  	   	
   			let testNo = $(this).data("testno");
   			$("#testView").find("[name='testNo']").val(testNo);
   			$("#startModal").modal();
   		}
   	});
});
// ============= 테스트 응시 ================
$("#goTestBtn").on("click", function() {
	$("#testView").submit();
});

// ============= 점수 확인 ================
$(".checkBtn").on("click", function() {
	let testNo = $(this).data("testno");
	let testType = $(this).data("testtype");
	$("input[name='testType']").val(testType);
	
	let stuScoreForm = $("#stuScoreForm");
	stuScoreForm.find("[name='testNo']").val(testNo);
	let stuAjax = stuScoreForm.ajaxForm({
		dataType : "json"
		, success : function(resp){
			if("M"==resp.studentScoreVO.testType){
				$("#testName").text("중간 고사");
				$("#testTotal").text((resp.ansScore.scrMiddle)*(100/35)+"점")
			}else {
				$("#testName").text("기말 고사");
				$("#testTotal").text((resp.ansScore.scrFinal)*(100/35)+"점")
			}
		}
		, error : function(xhr) {
			console.log(xhr);
		}
	}).submit();
	$("#checkScore").modal();
});
</script>