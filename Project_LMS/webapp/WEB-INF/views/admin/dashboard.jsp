<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<script type="text/javascript" src="${cPath}/js/Chart.js-2.9.4/dist/Chart.js"></script>
<style>
#scheduleDiv{height:350px;overflow: auto; }  
.fixedHeader {
	position: sticky;
	top: 0;
}
.font-weight-medium{font-weight: 900 !important;font-size: 2.5em;}
.btn-circle, .btn-circle-lg{width: 32px !important;height: 32px !important;}
</style>

<div class="container-fluid">

	<div class="row">
		<div class="col-8">
			<div class="row h-100">
				<div class="card col-3">
					<div class="pt-4 pl-5">
						<h2 class="text-dark mb-1 font-weight-medium">${depCnt }</h2> 
					</div>
					<div class="pl-5 pb-5">
						전체 학과
					</div>
					<div class="text-right mt-4">
						<i class="fas fa-university fa-2x text-dark"></i>
					</div>
				</div>
				<div class="card col-3">
					<div class="pt-4 pl-5">
						<h2 class="text-dark mb-1 font-weight-medium">${lecCnt }</h2> 
					</div>
					<div class="pl-5 pb-5">
						개설강의 
					</div>
					<div class="text-right mt-4">
						<i class="fas fa-laptop fa-2x text-dark"></i>
					</div>
				</div>
				<div class="card col-3">
					<div class="pt-4 pl-5">
						<h2 class="text-dark mb-1 font-weight-medium">${proCnt }</h2> 
					</div>
					<div class="pl-5 pb-5">
						재직 교수
					</div>
					<div class="text-right mt-4">
						<i class="icon-user fa-2x text-dark"></i> 
					</div>
				</div>
				<div class="card col-3">
					<div class="pt-4 pl-5">
						<h2 class="text-dark mb-1 font-weight-medium">${stuCnt }</h2> 
					</div>
					<div class="pl-5 pb-5">
						재학생
					</div>
					<div class="text-right mt-4">
						<i class="icon-user fa-2x text-dark"></i> 
					</div>
				</div>
			</div>
		</div>
		<div class="card col-4">
			<h4 class="card-title font-weight-bold pt-3 pb-2">최신 공지글</h4>
			<table class="table table-sm mb-0">
				<tbody>
				<c:set var="notyList" value="${notyList }" />
				<c:if test="${not empty notyList }">
					<c:forEach items="${notyList }" var="noty" varStatus="status">
						<tr class="h-100">
							<td class="pb-5">
								<i class="icon-note mr-2 pl-2 pr-2 text-primary fa-lg"></i>
							</td>
							<td>
							<p class="font-weight-bold"><a href="${cPath}/cyber/notice/${noty.boNo}/view.do">${noty.boTitle }</a></p>
							<c:set var="content" value="${noty.boContent}"/>
							<span class="font-weight-light">${fn:replace(fn:replace(content,'<p>' ,''), '<img','') }...</span>
							 
							</td>
							<td>
							<p class="font-weight-bold">작성일자</p>
							<span class="font-weight-light">${noty.boDate}</span>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				</tbody>
			</table>
		</div>
	</div>	
	<div class="row">
		<div id="secondChart" class="card col-4 d-flex">
			<p class="text-center">학과별 학생 수(명)</p>
		   <canvas id="myChart2" class="chartjs-render-monitor" ></canvas>
		</div>
		<div id="firstChart" class="card col-4 d-flex">
			<p class="text-center">최근 5년 입학생 수(명)</p>
		    <canvas id="myChart1"></canvas>
		</div>
		<div class="scheduleTable card col-4 d-flex pt-3 pb-2">
			<h4 class="card-title font-weight-bold">학사일정</h4>
			<div id="scheduleDiv">
				<table class="table table-sm mb-0 text-center">
					<thead class="thead-light">
						<tr>
							<th class="fixedHeader font-weight-bold">일정</th>
							<th class="fixedHeader font-weight-bold">학사내용</th>
						</tr>
					</thead>
					<tbody>
					<c:set var="schList" value="${schList }" />
					<c:if test="${not empty schList }">
						<c:forEach items="${schList }" var="sch" varStatus="status">
							<tr class="h-100">
								<td>
									${sch.scheStart} ~ ${sch.scheEnd }
								</td>
								<td>
									${sch.scheTitle }
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

<script type="text/javascript">
//json 데이터로 받아온 일정 배열에 저장
$(function(){
	$.ajax({
		url : "${cPath}/admin/dashboardAjax.do"
		,type : "get"
		,dataType : "json"
		,success: function (data, textStatus, jqXHR)
	        {
				
				//연도별입학생수
				let stuList = data.stuList;
				//학과별학생수
				let depStuList = data.depStuList;
				
				makeAdmissionStuCountChart(stuList);
				makeDepartmentStuCountChart(depStuList);
				
				
	        }
	});
	
});

// 연도별 입학생수
function makeAdmissionStuCountChart(stuList){
	let chartLabels = [];
	let chartData = [];
	$.each(stuList, function(inx, stu){
		console.log(stu);
		chartLabels.push((stu.memAdmission).substring(0,4));
		chartData.push(stu.depNo);
	});
	console.log(chartLabels);
    new Chart($("#myChart1"), {
        type: 'bar',
        data: {
            labels: chartLabels,
            datasets: [
                {
                	label :"년도" ,
                    backgroundColor:  "#5f76e8",
                    borderColor : "#5f76e8",
                    data: chartData
                }
            ]
        }
		 , options : {
		 scales : {
		     yAxes : [{
		         ticks : { beginAtZero : true }
		     }]
		 }
		}
    });
}
// 학과별 학생수
function makeDepartmentStuCountChart(depStuList){
	let chartLabels = [];
	let chartData = [];
	$.each(depStuList, function(inx, stu){
		chartLabels.push(stu.depName);
		chartData.push(stu.memGrd);
	});
    new Chart($("#myChart2"), {
        type: 'pie',
        data: {
            labels: chartLabels,
            datasets: [
                {
                	backgroundColor : [
                    "rgba(255, 205, 87, 1)"
                   , "rgba(95, 118, 232, 1)"
                   , "rgba(255, 99, 132, 1)"
                   , "#22ca80"
                   , "rgba(54, 162, 235, 1)"
               ]
                  , data: chartData
                }
            ]
        }
		 , options : {
		 scales : {
		     yAxes : [{
		         ticks : { beginAtZero : true }
		     }]
		 }
		}
    });
}

// let bar = $("#myChart1");
// let myChart1 = new Chart(bar, {
//     type : "bar",
//     data : {
//           labels: ["인문학부", "사회과학대학", "공과대학", "경상대학"]
//         , datasets : [{
//               label: "#BarChart"
//             , data: [12, 19, 3, 5]
//             , backgroundColor : [
//                   "rgba(255, 99, 132, 0.2)"
//                 , "rgba(54, 162, 235, 0.2)"
//                 , "rgba(255, 206, 86, 0.2)"
//                 , "rgba(75, 192, 192, 0.2)"
//                 , "rgba(153, 102, 255, 0.2)"
//                 , "rgba(255, 159, 64, 0.2)"
//             ]
//             , borderColor: [
//                   "rgba(255, 99, 132, 1)"
//                 , "rgba(54, 162, 235, 1)"
//                 , "rgba(255, 206, 86, 1)"
//                 , "rgba(75, 192, 192, 1)"
//                 , "rgba(153, 102, 255, 1)"
//                 , "rgba(255, 159, 64, 1)"
//             ]
//             , borderWidth : 1
//         }]
//     }
//     , options : {
//         scales : {
//             yAxes : [{
//                 ticks : { beginAtZero : true }
//             }]
//         }
//     }
// });
// let pie = $("#myChart2");
// let myChart2 = new Chart(pie, {
//     type : "pie",
//     data : {
//           labels: ["인문학부", "사회과학대학", "공과대학", "경상대학"]
//         , datasets : [{
//               label: "#PieChart"
//             , data: [12, 19, 3, 5]
//             , backgroundColor : [
//                   "rgba(255, 99, 132, 0.2)"
//                 , "rgba(54, 162, 235, 0.2)"
//                 , "rgba(255, 206, 86, 0.2)"
//                 , "rgba(75, 192, 192, 0.2)"
//                 , "rgba(153, 102, 255, 0.2)"
//                 , "rgba(255, 159, 64, 0.2)"
//             ]
//         }]
//     }
//     , options : {
//     }
// });
// let line = $("#myChart3");
// let myChart3 = new Chart(line, {
//     type : "bar",
//     data : {
//           labels: ["인문학부", "사회과학대학", "공과대학", "경상대학"]
//         , datasets : [{
//               label: "#lineChart"
//             , data: [12, 19, 3, 5]
// 			,borderColor: "rgba(255, 201, 14, 1)"
// 			,backgroundColor: "rgba(255, 201, 14, 0.5)"
//         }]
//     }
//     , options : {
//         scales : {
//             yAxes : [{
//                 ticks : { beginAtZero : true }
//             }]
//         }
//     }
// });


</script>