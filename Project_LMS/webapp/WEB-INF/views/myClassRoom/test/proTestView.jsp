<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<script src="${cPath }/js/pdf/html2canvas (1).js"></script>	
<script src="${cPath }/js/pdf/jspdf.min.js"></script>	
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
.testPaper {
	overflow: auto;
	height: 1000px;
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
<div class="row">
	<div class="col-4 ml-auto">
		<button type="button" class="btn waves-effect waves-light btn-light pl-4 pr-4" id="printBtn"><i class="fas fa-print"></i>&nbsp;출력</button>
	</div>
</div>
<div class="container-fluid col-12 col-xl-9 m-auto">
	<div class="applypaper card" >
		<div class="testPaper card-img-top" >
			<div class="mt-5 ml-15" id="print">
				<div class="align-middle">
					<c:if test="${not empty testMap }">
						<c:forEach items="${testMap }" var="test" varStatus="status">
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
											<td class="table-active font-weight-bold h3">응시 시간</td>
											<td>${test.value.totalTime }</td>
										</tr>
								</table>
							</div>
						</c:forEach>
					</c:if>
					<div id="body">
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
														<th scope="col" colspan="2" class="font-weight-bold" data-toptestqno=${obtest.otestQ }>${obtest.otestQ }</th>
														<th scope="col" colspan="2" class="font-weight-bold text-danger">정답 : ${obtest.otestA }번</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty obtest.deList }">
														<c:forEach items="${obtest.deList }" var="detail"
															varStatus="status">
															<tr>
																<td>${detail.dtlNo })</td>
																<td>${detail.dtlContent }</td>
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
														<td colspan="3"><textarea class="form-control sbtest text-danger font-weight-bold"
																rows="3"
																style="margin-top: 0px; margin-bottom: 0px; height: 136px;"
																readonly="readonly"
																data-sbtestqno="${sbtest.testQno }" >${sbtest.stestA }</textarea></td>
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
								id="nextBtn">이전</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$("#nextBtn").on("click", function(){
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

$(function() {
	$('#printBtn').click(function() { // pdf저장 button id
	    html2canvas($('#print')[0]).then(function(canvas) { //저장 영역 div id
		
	    // 캔버스를 이미지로 변환
	    var imgData = canvas.toDataURL('image/png');
		     
	    var imgWidth = 190; // 이미지 가로 길이(mm) / A4 기준 210mm
// 	    var pageHeight = imgWidth * 1.414;  // 출력 페이지 세로 길이 계산 A4 기준
	    var pageHeight = imgWidth * 1;  // 출력 페이지 세로 길이 계산 A4 기준
	    var imgHeight = canvas.height * imgWidth / canvas.width;
	    var heightLeft = imgHeight;
	    var margin = 0; // 출력 페이지 여백설정
	    var doc = new jsPDF('p', 'mm');
	    var position = 0;
	       
	    // 첫 페이지 출력
	    doc.addImage(imgData, 'PNG', margin, position, imgWidth, imgHeight);
	    heightLeft -= pageHeight;
	         
	    // 한 페이지 이상일 경우 루프 돌면서 출력
	    while (heightLeft >= 20) {
	        position = heightLeft - imgHeight;
	        doc.addPage();
	        doc.addImage(imgData, 'PNG', 0, position, imgWidth, imgHeight);
	        heightLeft -= pageHeight;
	    }
	    // 파일 저장
	    doc.save('exam-question.pdf');
		});
	});
})

</script>