<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />
<style type="text/css">
.accTable th {
	height: 15px;
}

.applypaper {
	width: 60em;
	font-size: 12pt;
	border: 3px solid darkgray;
	padding: 10px;
}

.mainTable {
	
}

.table-bordered, .table-bordered td, .table-bordered th {
	border: 1px solid darkgray;
}

.bottomText {
	font-size: 25pt;
	text-align: center;
}

.bottomBody {
	height: 300px;
}
/* div#header { */
/* 	position:fixed; */
/* } */
</style>
<div class="container-fluid col-12 col-xl-9 m-auto">
	<div class="applypaper card">
		<div class="card-img-top">
			<div class="mt-5 ml-15">
				<div class="align-middle">
					<c:if test="${not empty testMap }">
						<c:forEach items="${testMap }" var="test" varStatus="status">
							<input type="hidden" name="tmpTestType" value="${test.value.testType}"/>
							<div class="text-center pb-4 border-bottom">
								<c:choose>
									<c:when test="${test.value.testType eq 'M'}">
										<p class="ml-3 text-center display-4">중간 고사</p>
									</c:when>
									<c:when test="${test.value.testType eq 'F'}">
										<p class="ml-3 text-center display-4">기말 고사</p>
									</c:when>
								</c:choose>
							</div>
							<div class="mt-4 col-12 pb-5 text-center">
								<table class="table table-bordered table-striped mb-0">
									<tbody id="listBody">
										<tr>
											<td class="table-active font-weight-bold h3">시험 문항</td>
											<td>${test.value.testQno }문항</td>
											<input type="hidden" id="totalQno"
												value="${test.value.testQno }" />
											<td class="table-active font-weight-bold h3">응시 시간</td>
											<!-- 세션 타이머 시작.. -->
											<%-- 	                          <td>${test.value.totalTime }</td> --%>
											<td id="timeZone"></td>
											<input type="hidden" id="totalTime"
												value="${test.value.totalTime }" />
										</tr>
								</table>
							</div>
						</c:forEach>
					</c:if>
					<div id="body" style="height:1000px; overflow-y:auto">
						<c:if test="${not empty obList }">
							<c:forEach items="${obList }" var="obtest" varStatus="status">
								<div class="row">
									<div class="col-10 ml-5">
										<h3>
											<span class="badge badge-info">객관식</span>
										</h3>
									</div>
									<div class="col-1">
										<h4 id="point" class="font-weight-bold">${obtest.otestScore}점</h4>
									</div>
								</div>
								<div>
									<div class="row justify-content-center">
										<div class="col-11">
											<table class="table">
												<thead class="thead-light">
													<tr>
														<th>${obtest.testQno }.</th>
														<th scope="col" colspan="2" class="font-weight-bold">${obtest.otestQ }</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty obtest.deList }">
														<c:forEach items="${obtest.deList }" var="detail"
															varStatus="status">
															<tr>
																<td width="100px">${detail.dtlNo })</td>
																<td width="60px"><input class="form-check-input obBtn"
																	type="radio" name="${detail.testQno }"
																	id="${detail.testQno }+${detail.dtlNo }"
																	value="${detail.testQno }"
																	data-dtlno="${detail.dtlNo }"
																	data-testqno="${detail.testQno }"
																	data-testno="${detail.testNo}"></td>
																<td><label
																	for="${detail.testQno }+${detail.dtlNo }">${detail.dtlContent }</label></td>
															</tr>
														</c:forEach>
													</c:if>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${not empty sbList }">
							<c:forEach items="${sbList }" var="sbtest" varStatus="status">
								<div class="row">
									<div class="col-10 ml-5">
										<h3>
											<span class="badge badge-warning">주관식</span>
										</h3>
									</div>
									<div class="col-1">
										<h4 id="point" class="font-weight-bold">${sbtest.stestScore}점</h4>
									</div>
								</div>
								<div>
									<div class="row justify-content-center">
										<div class="col-11">
											<table class="table">
												<thead class="thead-light">
													<tr>
														<th>${sbtest.testQno }.</th>
														<th scope="col" colspan="2" class="font-weight-bold">${sbtest.stestQ }</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td colspan="3"><textarea class="form-control sbtest"
																rows="3"
																style="margin-top: 0px; margin-bottom: 0px; height: 136px;"
																placeholder="답안을 등록하세요"
																data-sbtestqno="${sbtest.testQno }"></textarea></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:if>
						<div class="mt-4 col-12">
							<table class="table table-bordered table-striped mb-0">
								<tbody id="listBody">
									<tr>
										<td class="font-weight-bold h3" colspan="2"><i
											class="fas fa-exclamation-circle"> 풀지 않은 문항은 0점 처리 됩니다.</i></td>
										<td class="table-active font-weight-bold h3">총 배점</td>
										<td>100점</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="row justify-content-center m-4">
							<button type="button"
								class="btn waves-effect waves-light btn-secondary justify-content-right btn-lg"
								id="nextBtn">답안지 제출</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- ============= 답안 미작성 확인 모달================ -->
<!-- ========================================= -->
<div id="noAns" class="modal fade" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-body p-4">
				<div class="text-center">
					<i class="dripicons-warning h1 text-warning"></i>
					<h4 class="font-weight-bold">답안 미작성 확인</h4>
					<p class="mt-3">답안을 작성하지 않은 문제가 존재합니다.</p>
					<button type="button" class="btn btn-warning my-2"
						data-dismiss="modal">문제 풀기</button>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- ============= 응시 확인 모달 ================ -->
<!-- ========================================= -->
<div id="startModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="danger-header-modalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header modal-colored-header bg-primary">
				<h4 class="modal-title font-weight-bold"
					id="danger-header-modalLabel">응시 여부 확인</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<h4 class="font-weight-bold">해당 시험을 제출하시겠습니까?</h4>
				<span class="badge badge-warning">주의</span> 풀지 않는 문항은 배점이 0점 처리 됩니다.
			</div>
			<div class="modal-footer" style="margin: auto;">
				<button type="button" class="btn btn-light" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-primary" id="goAnswer">제출</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<form id="answerInsertForm" method="post"
	action="${cPath}/myclass/${lecCode}/answerInsert.do">
	<input type="hidden" name="testType">
</form>
<%-- <form:form id="answerInsertForm" method="post" action="${cPath}/myclass/${lecCode}/answerInsert.do" commandName="ansList"> --%>

<%-- </form:form> --%>
<script type="text/javascript">
$("#resetBtn").on("click", function(){
	window.history.back();
});

// ============ 정답 ================
let myAnswer = new Map();
let form = $("#answerInsertForm");
let testno = null;
let totalQno = $("#totalQno").val();

// 객관식 정답
$(".obBtn").on("click", function(){
	// 답
	let dtlno = $(this).data("dtlno");
	// 번호
	let testqno = $(this).data("testqno");
	testno = $(this).data("testno");
	myAnswer.set(testqno,dtlno);
	console.log(myAnswer);
});

// 주관식 정답
$(".sbtest").change(function() {
	// 답
	let sbAnswer = $(this).val();
	// 번호
	let sbtestqno = $(this).data("sbtestqno");
	myAnswer.set(sbtestqno,sbAnswer);
	console.log(myAnswer);
});

$("#nextBtn").on("click", function() {
	let testType = $("input[name='tmpTestType']").val();
	$("input[name='testType']").val(testType);
	console.log(testType);
	let i=0;
	for(let [key, value] of myAnswer){
		console.log(key + "=" +value);
		form.append(
			$("<input>").attr({"type":"hidden","name":"ansList["+i+"].testQno","value":key}),		
			$("<input>").attr({"type":"hidden","name":"ansList["+i+"].sansAns","value":value}),
			$("<input>").attr({"type":"hidden","name":"ansList["+i+"].memId","value":${authMember.memId }}),
			$("<input>").attr({"type":"hidden","name":"ansList["+i+"].testNo","value":testno})
		)
		i=i+1;
	}
	if(myAnswer.size < totalQno){
		$("#noAns").modal();
	} else {
		$("#startModal").modal();
	}
});

$("#goAnswer").on("click", function() {
// 	let testType = $("input[name='tmpTestType']").val();
// 	console.log(testType)
// 	$("input[name='testType']").val(testType);
	form.submit();
});

// ============== 타이머 ================
$(function() {
	let totalTime = $("#totalTime").val();
	// 초
	window.localStorage.setItem('testTime', 59);
	// 분
	window.localStorage.setItem('testTimeMin', totalTime-1);
	
	var testTime = window.localStorage.getItem('testTime');
	var testTimeMin = window.localStorage.getItem('testTimeMin');
	
	
	if (testTime == null || testTimeMin == null) {
		testTime = 59;
		testTimeMin = totalTime-1;
	}
	function timer() {
		setTimeout(timer, 1000);
		testTime--;
		window.localStorage.setItem('testTime', testTime);
		window.localStorage.setItem('testTimeMin', testTimeMin);
		$("#timeZone").text(testTimeMin + ":" + testTime);
		if (testTime == 0) {
			if (testTimeMin != 0) {
				testTimeMin--;
				testTime = 59;
			} else {
				window.localStorage.removeItem('testTime');
				window.localStorage.removeItem('testTimeMin');
			}
		}
	}
	setTimeout(timer, 10);
});

// =============== 뒤로가기 막기 ============
$(function() {
	history.pushState(null,null, location.href)
	window.onpopstate = function(event) {
		history.go(1);
	}
});
</script>