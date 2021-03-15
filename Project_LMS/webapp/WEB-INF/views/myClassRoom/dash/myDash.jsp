<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<script type="text/javascript" src="${cPath}/js/Chart.js-2.9.4/dist/Chart.js"></script>
<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />
<div class="container-fluid">
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 내 강의실
			</h5>
		</div>
	</div>
	<div class="row mt-3">
		<div class="col-8 d-flex justify-content-start align-items-center">
			<h3 class="font-weight-bold">최신 공지글</h3>
		</div>
		<div class="col-4 d-flex justify-content-start align-items-center">
			<h3 class="font-weight-bold">TO DO</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-8">
			<div class="card border mb-0" style="height:350px; overflow-y:auto">
				<c:forEach items="${notyList}" var="noty" varStatus="status">
					<div class="row py-3 ${status.last ? "" : "border-bottom"}" style="height:33.33%">
						<div class="col-1 text-light d-flex justify-content-center align-items-center"><i class="fas fa-clipboard-list text-primary fa-2x"></i></div>
						<div class="col-9">
							<h3 class="font-weight-bold">
								<a href="${cPath}/myclass/${lecCode}/${noty.boNo }/notiView.do">${noty.boTitle }</a>
							</h3> 
							<c:set var="content" value="${fn:substring(noty.boContent,0,15)}" /> 
							<span class="font-weight-light">${fn:replace(fn:replace(content,'<p>' ,''), '<img','') }...</span>
						</div>
						<div class="col-2 my-auto">
							<h5 class="font-weight-bold">작성 일자</h5><span class="font-weight-light">${noty.boDate}</span>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="col-4">
			<div class="card border mb-0" style="height:350px; overflow-y:auto">
				<div class="card-body">
					<security:authorize access="hasRole('ROLE_STUDENT')">
						<c:if test="${not empty noSbList }">
							<c:forEach items="${noSbList }" var="noSb" varStatus="status">
								<div class="alert alert-dismissible fade show shadow"
									role="alert" style="background-color: #e7edfd">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
									<div class="row mt-2">
										<div class="col-2">
											<a href="javascript:void(0)"
												class="btn btn-cyan btn-circle mb-2 btn-item"> <svg
													xmlns="http://www.w3.org/2000/svg" width="24" height="24"
													viewBox="0 0 24 24" fill="none" stroke="currentColor"
													stroke-width="2" stroke-linecap="round"
													stroke-linejoin="round" class="feather feather-bell">
															<path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
															<path d="M13.73 21a2 2 0 0 1-3.46 0"></path></svg>
											</a>
										</div>
										<div class="col-10">
											<h4 class="text-dark font-weight-bold mb-2 ">[과제]
												${noSb.taskTitle }</h4>
											<p class="font-14 mb-2 text-muted">마감일:
												${fn:substring(noSb.taskDday, 0, 16)}</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${not empty noTestMap }">
							<c:forEach items="${noTestMap }" var="noTest" varStatus="status">
								<div class="alert alert-dismissible fade show shadow"
									role="alert" style="background-color: #e7edfd">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
									<div class="row mt-2">
										<div class="col-2">
											<a href="javascript:void(0)"
												class="btn btn-cyan btn-circle mb-2 btn-item"> <svg
													xmlns="http://www.w3.org/2000/svg" width="24" height="24"
													viewBox="0 0 24 24" fill="none" stroke="currentColor"
													stroke-width="2" stroke-linecap="round"
													stroke-linejoin="round" class="feather feather-bell">
															<path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
															<path d="M13.73 21a2 2 0 0 1-3.46 0"></path></svg>
											</a>
										</div>
										<div class="col-10">
											<h4 class="text-dark font-weight-bold mb-2 ">
												[시험]
												<c:choose>
													<c:when test="${noTest.value.testType eq 'M'}">
																	중간 고사
																</c:when>
													<c:when test="${noTest.value.testType eq 'F'}">
																	기말 고사
																</c:when>
												</c:choose>
											</h4>
											<p class="font-14 mb-2 text-muted">마감일
												:${fn:substring(noTest.value.testEnd, 0, 16)}</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${empty noTestMap && empty noSbList}">
							<div class="mt-4 activity">
								<div class="d-flex align-items-start border-left-line pb-3">
									<div class="d-flex align-items-start border-left-line">
										<div>
											<a href="javascript:void(0)" class="btn btn-cyan btn-circle mb-2 btn-item"> <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bell">
													<path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
													<path d="M13.73 21a2 2 0 0 1-3.46 0"></path></svg>
											</a>
										</div>
										<div class="ml-3 mt-2">
											<h4 class="text-dark font-weight-bold mb-2 ">현재 제출/응시해야할 항목이 없습니다.</h4>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</security:authorize>
					<security:authorize access="!hasRole('ROLE_STUDENT')">
						<c:choose>
							<c:when test="${not empty proQnaList }">
								<c:forEach items="${proQnaList }" var="proQna" varStatus="status">
									<div class="alert alert-warning alert-dismissible fade show shadow"
										role="alert">
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">×</span>
										</button>
										<div class="row mt-2">
											<div class="col-2">
												<a href="javascript:void(0)" class="btn btn-orange btn-circle mb-2 btn-item"> <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bell">
															<path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"></path>
															<path d="M13.73 21a2 2 0 0 1-3.46 0"></path></svg>
														</a>
											</div>
											<div class="col-10">
												<h4 class="text-dark font-weight-bold mb-2 ">[질문] ${proQna.boTitle }</h4>
														<p class="font-14 mb-2 text-muted">작성자: ${proQna.memName}</p>
											</div>
										</div>
									</div>
								</c:forEach>					
							</c:when>
							<c:otherwise>
								<div class="ml-3 mt-2">
									<h4 class="text-dark font-weight-bold mb-2 ">현재 제출/응시해야할 항목이 없습니다.</h4>
								</div>
							</c:otherwise>
						</c:choose>
					</security:authorize>
				</div>
			</div>
		</div>
	</div>
	<div class="row mt-5">
		<div class="col-8 d-flex justify-content-start align-items-center">
			<h3 class="font-weight-bold">최신 게시글</h3>
		</div>
		<div class="col-4 d-flex justify-content-start align-items-center">
			<h3 class="font-weight-bold">강의 평가 비율</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-8">
			<div id="accordion" class="custom-accordion mb-4">
				<div class="card border mb-0">
					<div class="card-header bg-light" id="headingOne">
						<h5 class="m-0">
							<a class="custom-accordion-title d-block pt-2 pb-2" data-toggle="collapse" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">과제<span class="float-right"><i class="mdi mdi-chevron-down accordion-arrow"></i></span>
							</a>
						</h5>
					</div>
					<div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
						<div class="card-body">
							<table class="table table-sm mb-0">
								<tbody>
									<c:set var="taskList" value="${taskList }" />
									<c:choose>
										<c:when test="${not empty taskList }">
											<c:forEach items="${taskList }" var="task" varStatus="status">
												<tr class="h-100">
													<td class="pb-5"><i class="fas fa-bookmark mr-2 pl-2 pr-2 text-success fa-2x"></i></td>
													<td>
														<h3 class="font-weight-bold">
															<a href="${cPath}/myclass/${lecCode}/${task.taskNo}/taskView.do">${task.taskTitle}</a>
														</h3> <c:set var="content" value="${fn:substring(task.taskContent,0,15)}" /> <span class="font-weight-light">${fn:replace(fn:replace(content,'<p>' ,''), '<img','') }...</span>
													</td>
													<td>
														<p class="font-weight-bold">배점</p> <span class="font-weight-light">${task.taskCredit}</span>
													</td>
													<td>
														<p class="font-weight-bold">마감일자</p> <span class="font-weight-light">${fn:substring(task.taskDday,0,10)}</span>
													</td>
												</tr>
											</c:forEach>
										</c:when>
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- end card-->
				<div class="card border mb-0">
					<div class="card-header bg-light" id="headingTwo">
						<h5 class="m-0">
							<a class="custom-accordion-title collapsed d-block pt-2 pb-2" data-toggle="collapse" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">시험<span class="float-right"><i class="mdi mdi-chevron-down accordion-arrow"></i></span>
							</a>
						</h5>
					</div>
					<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
						<div class="card-body border">
							<table class="table table-sm mb-0">
								<tbody>
									<c:choose>
										<c:when test="${not empty testMap }">
											<c:forEach items="${testMap}" var="test" varStatus="status">
												<tr class="h-100">
													<td class="pb-5"><i class="fas fa-book mr-2 pl-2 pr-2 text-danger fa-2x"></i></td>
													<td><c:choose>
															<c:when test="${test.value.testType eq 'M'}">
																<h3 class="font-weight-bold">
																	<a href="${cPath }/myclass/${lecCode}/testList.do">중간 고사</a>
																</h3>
															</c:when>
															<c:when test="${test.value.testType eq 'F'}">
																<h3 class="font-weight-bold">
																	<a href="${cPath}/myclass/${lecCode}/testList.do">기말 고사</a>
																</h3>
															</c:when>
														</c:choose></td>
													<td>
														<p class="font-weight-bold">시작 일자</p> <span class="font-weight-light">${fn:substring(test.value.testStart, 0, 16)}</span>
													</td>
													<td>
														<p class="font-weight-bold">마감 일자</p> <span class="font-weight-light">${fn:substring(test.value.testEnd, 0, 16)}</span>
													</td>
													<td>
														<p class="font-weight-bold">총 문항</p> <span class="font-weight-light">${test.value.testQno }문항</span>
													</td>
												</tr>
											</c:forEach>
										</c:when>
									</c:choose>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- end card-->
				<div class="card border mb-0">
					<div class="card-header bg-light" id="headingThree">
						<h5 class="m-0">
							<a class="custom-accordion-title collapsed d-block pt-2 pb-2" data-toggle="collapse" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">토론<span class="float-right"><i class="mdi mdi-chevron-down accordion-arrow"></i></span>
							</a>
						</h5>
					</div>
					<div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
						<div class="card-body">
							<table class="table table-sm mb-0">
								<tbody>
									<c:forEach items="${disList}" var="dis" varStatus="status">
										<tr class="h-100">
											<td class="pb-5"><i class="fas fa-comment-dots mr-2 pl-2 pr-2 text-warning fa-2x"></i></td>
											<td>
												<h3 class="font-weight-bold">
													<a href="${cPath}/myclass/${lecCode}/${dis.boNo}/disView.do">${dis.boTitle}</a>
												</h3> <c:set var="content" value="${fn:substring(dis.boContent,0,15)}" /> <span class="font-weight-light">${fn:replace(fn:replace(content,'<p>' ,''), '<img','') }...</span>
											</td>
											<td>
												<p class="font-weight-bold">작성 일자</p> <span class="font-weight-light">${dis.boDate }</span>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- end card-->
			</div>
			<!-- end custom accordions-->
		</div>
		<div class="col-4">
			<div class="card border">
				<div class="card-body">
					<div>
						<div class="chartjs-size-monitor">
							<div class="chartjs-size-monitor-expand">
								<div class=""></div>
							</div>
							<div class="chartjs-size-monitor-shrink">
								<div class=""></div>
							</div>
						</div>
						<canvas id="myChart" class="chartjs" width="770" height="500"></canvas>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$.ajax({
			url : "${cPath}/myclass/attDashChart.do",
			dataType : "json",
			success : function(resp) {
				console.log(resp);
				makeChart(resp)
			},
			error : function(xhr) {
				console.log(xhr);
			}
		});
	});

	function makeChart(resp) {
		let chartLabels = [];
		let chartData = [];
		new Chart(document.getElementById("myChart"), {
			type : "doughnut",
			data : {
				labels : [ "출석", "과제", "중간 고사", "기말 고사" ],
				datasets : [ {
					label : "My First Dataset",
					data : [ resp.assAttend, resp.assTask, resp.assMiddle,
							resp.assFinal ],
					backgroundColor : [ "rgb(255, 99, 132)",
							"rgb(54, 162, 235)", "rgb(255, 205, 86)",
							"rgb(250, 159, 64, 80)" ]
				} ]
			}
		});
	}
</script>
