<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<script type="text/javascript" src="${cPath}/js/Chart.js-2.9.4/dist/Chart.js"></script>
<style type="text/css">
.listBody {
	overflow: scroll;
}
</style>
<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />
<input type="hidden" name="lecCode" value="${lecCode }"/>
<div class="container-fluid">
	<div class="row">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> MyClass > 시험 조회
			</h5>
		</div>
	</div>
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-9 text-left">
			<h2 class="font-weight-bold">시험</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<h5 class="pb-5 ml-2">수강생의 시험 성적 및 문항을 조회할 수 있습니다.</h5>
		</div>
	</div>
	<div class="card bg-light mb-3">
		<div class="card-header">시험 조회</div>
		<div class="card-body">
			<div class="row pt-7">
				<div class="col-10 m-auto">
					<table class="table table-bordered">
						<thead class="thead-dark">
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
							<c:if test="${not empty testMap }">
								<c:forEach items="${testMap }" var="test" varStatus="status">
									<tr class="table-light">
										<td><c:choose>
												<c:when test="${test.value.testType eq 'M'}">
													<h4>중간 고사</h4>
												</c:when>
												<c:when test="${test.value.testType eq 'F'}">
													<h4>기말 고사</h4>
												</c:when>
											</c:choose></td>
										<td><h4>${fn:substring(test.value.testStart, 0, 16)}</h4></td>
										<input type="hidden" id="startDate"
											value="${fn:substring(test.value.testStart, 0, 16)}" />
										<td><h4>${fn:substring(test.value.testEnd, 0, 16)}</h4></td>
										<input type="hidden" id="endDate"
											value="${fn:substring(test.value.testEnd, 0, 16)}" />
										<td><h4>${test.value.testQno }문항</h4></td>
										<td><h4>${test.value.totalTime }분</h4></td>
										<td>
											<button type="button"
												class="btn waves-effect waves-light btn-info px-4 py-2 mx-2 listBtn"
												data-testno="${test.key }">
												<i class="fas fa-edit"></i> 문항 조회
											</button>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
<%-- 	<canvas id="scoreChart" style="display: block; width: 685px; height: 342px;" width="685" height="342" class="chartjs-render-monitor"></canvas> --%>
	<div class="row">
		<div class="col-6">
			<h4 class="font-weight-bold">수강생 성적 조회</h4>
		</div>
		<div class="col-6 text-right">
			<a id="downExcelJxls" class="btn btn-success" href="#"><i class="fas fa-file-excel"> excel 다운로드</i></a>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<table class="table" style="height:350px; overflow-y:auto">
				<thead class="thead-light">
					<tr>
						<th class="font-weight-bold" rowspan="2">학번</th>
						<th class="font-weight-bold" rowspan="2">이름</th>
						<th class="font-weight-bold text-primary" colspan="2" align="center">중간 고사</th>
						<th class="font-weight-bold text-primary" colspan="2" align="center">기말 고사</th>
					</tr>
					<tr>
						<th class="font-weight-bold">원점수(100점)</th>
						<th class="font-weight-bold">백분율(35점)</th>
						<th class="font-weight-bold">원점수(100점)</th>
						<th class="font-weight-bold">백분율(35점)</th>
					</tr>
				</thead>
				<tbody id="listBody">
				</tbody>
			</table>
		</div>
	</div>
</div>
<form:form id="searchForm" action="${cPath }/myclass/${lecCode }/proStuScoreList.do">
</form:form>
<form:form commandName="test" action="${cPath }/myclass/${lecCode}/testList.do" id="selectTest">
	<input type="hidden" name="testNo" />
</form:form>
<script type="text/javascript">
let mid = [];
let fin = [];
let searchForm = $("#searchForm").ajaxForm({
	dataType:"json"
	,resetForm:true
	,success:function(resp){
		console.log(resp);
		let scoreList = resp.pagingVO.dataList;
		let trTags = [];
		if(scoreList.length>0){
			$(scoreList).each(function (idx, score) {
				trTags.push(
					$("<tr>").append(
						$("<td>").text(score.memId)						
						,$("<td>").text(score.memName)
						,$("<td>").text((score.scrMiddle)*100/35)
						,$("<td>").text(score.scrMiddle)
						,$("<td>").text((score.scrFinal)*100/35)		
						,$("<td>").text(score.scrFinal)		
					)
				);
				mid.push(
					(score.scrMiddle)*100/35
				);
				fin.push(
					(score.scrFinal)*100/35
				);
			});
		}else {
			trTags.push(
			  		$("<tr>").html(
			  			$("<td colspan='6'>").attr("align","center").text("조건에 맞는 게시글이 존재하지 않습니다.")
			  		)
		  		);
		}
		$("#listBody").html(trTags);
		searchForm.find(":input").val("");
	}
}).clearForm();   
searchForm.submit();

// ======== 선택 시험 문항 조회 ===============
$(".listBtn").on("click", function() {
	let testNo = $(this).data("testno");
	console.log(testNo)
	if (testNo != null && testNo != "") {
		$("#selectTest").find("input[type='hidden']").val(testNo);
	}
	let lecCode = $("input[name='lecCode']").val();
	$("#selectTest").attr("action", "${cPath}/myclass/" + lecCode + "/testView.do");
	$("#selectTest").submit();
});

//========== 엑셀파일 다운===================
$("#downExcelJxls").on("click", function(){
	let lecCode = $("input[name='lecCode']").val();
	location.href="${cPath}/myClass/"+lecCode+"/downloadTestExcel.do";
// 	$("#searchForm").attr("action","${cPath}/myClass/"+lecCode+"/downloadTestExcel.do").attr("method","post");
// 	$("#searchForm").submit();
	return true;
});
// $(function () {
// 	console.log(mid);
// 	console.log(fin);
// 	let chartLabels = [];
// 	let chartData = [];
	
// 	var ctx2 = document.getElementById('scoreChart');
// 	var heatChart = new Chart(ctx2, {
// 	    type: 'bar',
// 	    data : {
// 	          labels: ['중간고사', '기말고사'],
// 	          datasets: [{
// 	             label: "평균"
// 	                    ,data: [1,3,0]
// 	            ,backgroundColor: [
// 	               'rgba(23, 28, 42, 0.3)'
// 	               ,'rgba(23, 28, 42, 0.3)'
// 	            ]
// 	            ,borderColor: [
// 	               'rgba(23, 28, 42, 0.3)'
// 	               ,'rgba(23, 28, 42, 0.3)'
// 	            ]   
// 	                }
// 	          ,{
// 	             label: "최고점"
// 	              ,data: [3,1,0]
// 	         ,backgroundColor: [
// 	             'rgba(48, 152, 72, 1)'
// 	         ,'rgba(48, 152, 72, 1)'
// 	         ]
// 	         ,borderColor: [
// 	            'rgba(48, 152, 72, 1)'
// 	            ,'rgba(48, 152, 72, 1)'
// 	         ]   
// 	          }]
// 	      },
// 	      options: {
// 	         responsive: false
// 	   }
// 	});
// });
</script>