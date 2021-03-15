<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>


<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="${cPath}/resource/assets/images/favicon.png">
<!-- Custom CSS -->
<link
	href="${cPath}/resource/assets/libs/fullcalendar/dist/fullcalendar.min.css"
	rel="stylesheet">
<link
	href="${cPath}/resource/assets/extra-libs/c3/c3.min.css"
	rel="stylesheet">
<link
	href="${cPath}/resource/assets/libs/chartist/dist/chartist.min.css"
	rel="stylesheet">
<link
	href="${cPath}/resource/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css"
	rel="stylesheet" />
<!-- Custom CSS -->
<link
	href="${cPath}/resource/dist/css/style.min.css"
	rel="stylesheet">
<link
	href="${cPath}/resource/bootModal/dist/bootstrap4-modal-fullscreen.min.css"
	rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- This Page CSS -->
<link rel="stylesheet" type="text/css"
	href="${cPath}/resource/assets/extra-libs/prism/prism.css">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->

<title>수강신청</title>
<style>
table {
	width: 100%;
	text-align: center;
}

#interestTableArea {
	width: 100%;
}
.interestTable {
	width: 100%;
}

.interestTable td {
	width: 11%;
}

#table1 {
	margin-top: 20px;
/* 	margin-bottom: 50px; */
}

#table2 {
	margin-bottom: 150px;
}

th, td {
	padding: 10px;
	font-size: 5px;
}

#topArea {
	padding-left: 5%;
	padding-top: 2%;
	padding-right: 6%;
}

#leftArea {
	width: 69%;
	float: left;
	padding-left: 5%;
	padding-right: 2.5%;
}

#rightArea {
	width: 29%;
	float: left;
	padding-right: 5%;
	padding-left: 2.5%;
}

#enrolmentArea {
	width: 100%;
}

#guidArea {
	width: 100%;
	padding-top: 10px;
}

#guidAreaTd {
	text-align: left;
}

#attentionArea {
	width: 100%;
}


#searchText {
	width: 100%;
}

#searchTypeArea {
	width: 100%;
	float: left;
}

#possibleArea {
	width: 100%;
}

#rightTitle {
	float: left;
}

#leftTitle {
	float: right;
}

.enrBtn {
	width: 70px;
	height: auto;
	font-size: 3px;
	font-weight: bolder;
}

#titleText {
	font-size: 10px;
}

#timeArea {
	float: right;
}
#contentArea {
	float: right;
	width: 90%;
}
#alertArea{
	width: 50%;
	margin: auto;
}

</style>


</head>
<body>
	<c:set var="pagingVO" value="${paginationInfo.pagingVO }" />
	<security:authorize access="isAuthenticated()">
		<security:authentication property="principal" var="principal" />
		<c:set var="authMember" value="${principal.realMember }" />

	</security:authorize>




	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">
		<aside class="left-sidebar" data-sidebarbg="skin6">
			<!-- Sidebar scroll-->
			<div class="scroll-sidebar" data-sidebarbg="skin6">
				<!-- Sidebar navigation-->
				<nav class="sidebar-nav">
					<ul id="sidebarnav">
						<li class="nav-small-cap"><span class="hide-menu">Smart
								LMS</span></li>
						<li class="sidebar-item"><a class="sidebar-link has-arrow"
							href="javascript:void(0)" aria-expanded="false"><i
								data-feather="star" class="feather-icon"></i><span
								class="hide-menu">수강신청</span></a>
							<ul aria-expanded="false"
								class="collapse  first-level base-level-line">
								<li class="sidebar-item">
									<a href="${cPath}/enrolment/list.do" class="sidebar-link">
										<span class="hide-menu">수강신청 </span>
									</a>
								</li>
								<li class="sidebar-item">
									<a href="${cPath}/enrolment/profileList.do" class="sidebar-link">
										<span class="hide-menu">학적조회 </span>
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</nav>
				<!-- End Sidebar navigation -->
			</div>
			<!-- End Sidebar scroll-->
		</aside>
		<div id=contentArea>
			<div id="topArea">
				<div id="rightTitle">
					<span id="titleText"> ${authMember.memName } 학생 수강신청 사이트 </span>
				</div>
				<div id="leftTitle">
					<span id="timeZone"></span> <input type="button" id="timeBtn"
						class="btn btn-primary" value="연장"> <a href="#none"
						onclick="window.open('${cPath}/enrolment/schedulePdf.do','new','scrollbars=yes,resizable=no width=800px height=1000px, left=0,top=0');return false">
						<input type="button" class="btn btn-primary" value="시간표">
					</a> <a href="${cPath}/">
						<button class="btn btn-primary">사이트맵</button>
					</a>
				</div>
				<div id="searchTypeArea">
					<fieldset>
						<legend> 검색방법 </legend>
						<div id="alertArea" class="alert alert-danger alert-dismissable fade show">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<strong>수강이 취소 되었습니다.</strong>
						</div>
						<div class="btn-group" id="radioGroup">
							<div class="custom-control custom-radio">
								<input type="radio" name="radio_option" id="radio-1"
									class="custom-control-input" checked="checked" value="#">
								<label class="custom-control-label" for="radio-1">전체조회</label>
							</div>
							<div class="custom-control custom-radio">
								<input type="radio" name="radio_option" id="radio-2"
									class="custom-control-input" value="#"> <label
									class="custom-control-label" for="radio-2">관심과목</label>
							</div>
						</div>

					</fieldset>
				</div>
			</div>






			<div id="leftArea">
				<div id="enrolmentArea">
					<div class="col-12" id="attentionArea">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">개설 강의</h4>
								<h3 class="card-subtitle">과목 명을 누르시면 강의 계획서를 확인할 수 있습니다.</h3>
							</div>
							<div class="table-responsive">
								<table class="table" id="table1">
									<thead class="thead-light">
										<tr>
											<th>수강신청
												<form id="insertForm">
													<input type="hidden" id="lecCodeID" name="enrolmentLecCode">
													<input type="hidden" id="memID" name="enrolmentMemId">
												</form>
											</th>
											<th>관심등록</th>
											<th>수강코드</th>
											<th>과목명</th>
											<th>담당교수</th>
											<th>정원</th>
											<th>수강인원</th>
											<th>개설학년</th>
											<th>이수구분</th>
											<th>학점</th>
										</tr>
									</thead>
									<tbody id="bodyArea">
										<c:set var="lectureList" value="${pagingVO.dataList }" />
										<c:if test="${not empty lectureList }">
											<c:set var="i" value="0" />
											<c:forEach items="${lectureList }" var="lecture">
												<c:set var="i" value="${i+1}" />
												<c:set var="insertBtn" value="insertBtn${i}" />
												<c:set var="interestBtn" value="interestBtn${i }" />
												<tr>
													<td><input type="button" id="${insertBtn }"
														onclick="insert('${lecture.lecCode}','${authMember.memId }','${insertBtn }','${lecture.subjectVo.subName }','${lecture.memberVo.memName }','${lecture.lecFull }','${lecture.lecNmt }','${lecture.lecGrd }','${lecture.subjectVo.subDetail }','${lecture.subjectVo.subCredit }')"
														class="enrBtn btn btn-warning" value="수강신청" /></td>

													<td><input type="button" id="${interestBtn }"
														class="enrBtn btn btn-primary"
														onclick="interest('${lecture.lecCode}','${authMember.memId }','${interestBtn }')"
														value="관심등록"></td>
													<td>${lecture.lecCode }</td>
													<td><a href="#none"
														onclick="window.open('${cPath}/plan.do?lecCode=${lecture.lecCode }','new','scrollbars=yes,resizable=no width=800px height=1000px, left=0,top=0');return false">
															${lecture.subjectVo.subName } </a></td>
													<td>${lecture.memberVo.memName }</td>
													<td>${lecture.lecFull }</td>
													<td>${lecture.lecNmt }</td>
													<td>${lecture.lecGrd }</td>
													<td>${lecture.subjectVo.subDetail }</td>
													<td>${lecture.subjectVo.subCredit }</td>
												</tr>
											</c:forEach>
										</c:if>
									</tbody>

									<tfoot id="tfootArea">
										<tr>
											<td colspan="11">
												<form id="searchForm2">
													<input type="hidden" name="page" /> 
													<input type="hidden"
														name="searchType" value="${pagingVO.searchVO.searchType }" />
													<input type="hidden" name="searchWord"
														value="${pagingVO.searchVO.searchWord }" />
												</form>
												<div id="inputUI" class="row justify-content-center mb-3">
													<div class="col-3">
														<select name="searchType" class="form-control mr-1">
															<option value="subName"
																${'subName' eq param.searchType?"selected":"" }>개설과목</option>
															<option value="lecGrd"
																${'lecGrd' eq param.searchType?"selected":"" }>개설학년</option>
														</select>
													</div>
													<div class="col-4">
														<input type="text" name="searchWord"
															class="form-control mr-3"
															value="${pagingVO.searchVO.searchWord }" />
													</div>
													<div class="col-auto">
														<input type="button" value="검색" id="searchBtn2"
															class="btn btn-primary" />
													</div>
												</div>
												<div id="pagingArea">
													<ui:pagination paginationInfo="${paginationInfo }"
														jsFunction="pageLinkNumber" type="bsNumber" />
												</div>
											</td>
										</tr>
									</tfoot>
								</table>
								<div id="interestTableArea"></div>
							</div>
						</div>
					</div>
				</div>
				<div id="Area2">
					<div id="attentionArea">
						<div class="col-12" id="attentionArea">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">수강신청 내역</h4>
								</div>
								<div class="table-responsive">
									<table class="table" id="table2">
										<thead class="thead-light">
											<tr>
												<th>수강취소</th>
												<th>수강코드
													<form id="deleteForm">
														<input type="hidden" id="dlelecCodeID"
															name="delenrolmentLecCode"> <input type="hidden"
															id="delmemID" name="dleenrolmentMemId">
													</form>
												</th>
												<th>과목명</th>
												<th>담당교수</th>
												<th>정원</th>
												<th>수강인원</th>
												<th>개설학년</th>
												<th>이수구분</th>
												<th>학점</th>
											</tr>
										</thead>
										<tbody id="tbodyArea">
											<c:set var="detailList" value="${pagingVO.dataList2 }" />
											<c:if test="${not empty detailList }">
												<c:set var="j" value="0" />
												<c:forEach items="${detailList }" var="detail">
													<c:set var="j" value="${j+1 }" />
													<c:set var="deleteBtn" value="deleteBtn${j }" />
													<tr id="${j }">
														<td><button id="${deleteBtn}"
																class="enrBtn btn btn-primary"
																onclick="deleted('${detail.lecCode}','${authMember.memId }','${deleteBtn}','${detail.subjectVo.subCredit }')">수강취소</button>
														</td>
														<td>${detail.lecCode }</td>
														<td><a href="#none"
															onclick="window.open('${cPath}/plan.do?lecCode=${detail.lecCode }','new','scrollbars=yes,resizable=no width=800px height=1000px, left=0,top=0');return false">
																${detail.subjectVo.subName } </a></td>
														<td>${detail.memberVo.memName }</td>
														<td>${detail.lecFull }</td>
														<td>${detail.lecNmt }</td>
														<td>${detail.lecGrd }</td>
														<td>${detail.subjectVo.subDetail }</td>
														<td>${detail.subjectVo.subCredit }</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="rightArea">
				<div id="guidArea">
					<div class="col-12" id="attentionArea">
						<div class="card">
							<div class="table-responsive">
								<table class="table" id="table2">
									<thead class="thead-light">
										<tr>
											<th scope="col">관심과목 안내</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td id="guidAreaTd">1. 관심과목 등록은 수강신청 1주일전부터 가능합니다. <br>
											<br> 2. 관심과목이란 수강신청 전에 나의 관심과목을 미리 목록에 등록해 두었다가 수강신청시 과목
												검색을 거치지 않고 바로 수강 신청을 할 수 있도록 하는 방법입니다.<br>
											<br> 3. 검색조건(과목명, 학과별, 교양필수, 교양핵심, 교양선택, 교직과목)을 통해 관심과목을
												미리 등록하시기 바랍니다.<br>
											<br> 4. 관심과목으로 등록했다고 해도 수강신청이 된 상태는 아니니, 특별히 주의하시기 바라며
												반드시 수강신청 기간에 수강신청을 하시기 바랍니다.<br>
											<br> 5. 수강신청 오픈 전에는 담당교수 및 강의시간 등이 변경 될 수 있습니다. 따라서
												관심과목으로 등록할 시점의 내용과 수강신청 시점의 내용이 달라질 수 있으므로 수강 신청시 반드시 내용을
												확인하기시 바랍니다.<br>
											<br> 6. 20과목까지만 등록할 수 있습니다.<br>
											<br>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<br>
				<br>
				<br>
				<div id="possibleArea">
					<div class="col-12" id="attentionArea">
						<div class="card">
							<div class="table-responsive">
								<table class="table" id="table2">
									<thead class="thead-light">
										<tr>
											<th colspan="6">수강신청 가능 학점</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td colspan="6"><h2>20 학점</h2></td>
										</tr>
										<tr>
											<td>최대</td>
											<td>20</td>
											<td>최소</td>
											<td>12</td>
											<td>이월</td>
											<td>0</td>
										</tr>
										<tr>
											<td colspan="6"><h6>
													<strong> 수강신청학점</strong>
												</h6></td>
										</tr>
										<tr>
											<td colspan="6"><h3>
													<strong><span id="totalArea"></span> </strong>
												</h3></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">
		let totalCredit = 0; //총 신청 한 학점	
		let deleteForm = $("#deleteForm");
		
		let flag = window.localStorage.getItem('flag');
		
		//시작 때 받아오는 것
		$(function() {
			$("#alertArea").hide();
			
			//페이지 접속시 관심 조회 바로 시작.
			if(flag=='on'){
				$("#radio-2").click();
				window.localStorage.setItem('flag', 'off');
			}
			
			
			
			let firstTotal = 0;
			<c:forEach items="${paginationInfo.pagingVO.dataList2 }" var="detail3">
			firstTotal += Number('${detail3.subjectVo.subCredit }');
			</c:forEach>
			plusTotalCredit(firstTotal);

			//세션 타이머 ==========================================================
			var intervals = window.localStorage.getItem('intervals');
			var intervalsMin = window.localStorage.getItem('intervalsMin');
			if(intervals==null ||intervalsMin==null){
				intervals =59;
				intervalsMin=29;
			}
			function timer() {
				setTimeout(timer, 1000);
				intervals--;
				window.localStorage.setItem('intervals', intervals);
				window.localStorage.setItem('intervalsMin', intervalsMin);
				$("#timeZone").text(intervalsMin + ":" + intervals);
				if (intervals == 0) {
					if (intervalsMin != 0) {
						intervalsMin--;
						intervals = 59;
					} else {
						window.localStorage.removeItem('intervals');
						window.localStorage.removeItem('intervalsMin');
						let url = "${cPath}/logout.do";
						$(location).attr('href', url);
					}
				}
			}
			setTimeout(timer, 10);

			//타이머 연장 ==========================================================
			$("#timeBtn").on("click", function() {
				intervals = 59;
				intervalsMin = 29;
				$("#timeZone").text(intervalsMin + ":" + intervals);
			});

		});
	//동기 페이징 ===================================================
		function pageLinkNumber(event) {
			let tableBodyText="";
			event.preventDefault();
			let page = $(event.target).data("page");
			console.log(page);
			searchForm.find("[name='page']").val(page);
			let pagingTable = "";
			
			searchForm.submit();
			
			return false;

		}
		let searchForm = $("#searchForm2");
		$("#searchBtn2").on("click", function() {
			let inputs = $(this).parents("div#inputUI").find(":input[name]");
			$(inputs).each(function(index, input) {
				let name = $(this).attr("name");
				let value = $(this).val();
				let hidden = searchForm.find("[name='" + name + "']");
				hidden.val(value);
			});
			let tableBodyText2="";
			

			
			searchForm.submit();

		});
		let insertForm = $("#insertForm");

		//전체 조회  ======================================================================================================	
		$("#radio-1").on("click", function() {
			$("#Area2").show();
// 			location.reload();
			$(".interestTable").hide();
			$("#bodyArea").show();
			$("#tfootArea").show();
			$.ajax({
				url : "${cPath}/enrolment/list.do",
				method : "get",
				success : function(resp) {
					var url = "${cPath}/enrolment/list.do";
					$(location).attr('href',url);

				},
				error : function(xhr) {
					console.log(xhr);
				}
			});
		});

		//관심 과목 취소 ======================================================================================================	
		function deleteInterest(lecCode, memId, idx) {
			$("#dlelecCodeID").val(lecCode);
			$("#delmemID").val(memId);
			$("#interest" + idx).remove();
			$.ajax({
				url : "${cPath}/enrolment/deleteInterest.do",
				data : deleteForm.serialize(),
				method : "post",
				dataType : "json",
				success : function(resp) {
					if (resp.deleteResult == 1) {
						
					}
				},
				error : function(xhr) {
					console.log(xhr);
				}
			});
		}

		//관심 과목 조회 ======================================================================================================	
		$("#radio-2").on("click",function() {
							$("#Area2").hide();

							let memId = '${authMember.memId }';
							$("#memID").val(memId);
							$("#bodyArea").hide();
							$("#tfootArea").hide();
							$(".interestTable").hide();
							let interestTableContent = "";
							let flag = 0;
							$.ajax({
										url : "${cPath}/enrolment/interestList.do",
										data : insertForm.serialize(),
										method : "post",
										dataType : "json",
										success : function(resp) {
											let max = resp.interestResult.length;
											if (resp.interestResult != null) {
												interestTableContent += "<tbody class='interestTable'>";
												for (let i = 0; i < max; i++) {
													let param1 = resp.interestResult[i].lecCode;
													let param2 = i;
													let param3 = resp.interestResult[i].subjectVo.subCredit;
													let param4 = resp.interestResult[i].lecNmt;
													interestTableContent += ("<tr id='interest"+i+"'>");
													interestTableContent += ("<td><button id='insert2Btn"
															+ i
															+ "' class='enrBtn btn btn-warning' onclick='insert2("
															+ param1
															+ ",${authMember.memId },"
															+ param2
															+ ","
															+ param3
															+ ","
															+ resp.interestResult[i].lecFull
															+ "," + param4 + ")'>수강신청</button></td>");
													interestTableContent += ("<td><button class='enrBtn btn btn-primary' onclick='deleteInterest("
															+ resp.interestResult[i].lecCode
															+ ",${authMember.memId },"
															+ i + ")')>관심취소</button></td>");
													interestTableContent += ("<td>"
															+ resp.interestResult[i].lecCode + "</td>");
													interestTableContent += ("<td>"
															+ resp.interestResult[i].subjectVo.subName + "</td>");
													interestTableContent += ("<td>"
															+ resp.interestResult[i].memberVo.memName + "</td>");
													interestTableContent += ("<td>"
															+ resp.interestResult[i].lecFull + "</td>");
													interestTableContent += ("<td>"
															+ resp.interestResult[i].lecNmt + "</td>");
													interestTableContent += ("<td>"
															+ resp.interestResult[i].lecGrd + "</td>");
													interestTableContent += ("<td>"
															+ resp.interestResult[i].subjectVo.subDetail + "</td>");
													interestTableContent += ("<td>"
															+ resp.interestResult[i].subjectVo.subCredit + "</td>");
													interestTableContent += ("</tr>");
												}

												interestTableContent += "</tbody>"
												$("#interestTableArea").append(
														interestTableContent);
											}
										},
										error : function(xhr) {
											console.log(xhr);
										}
									});

						});

		//관심 등록 ======================================================================================================	
		function interest(lecCode, memId, interestBtn) {
			console.log(lecCode);
			console.log(memId);
			console.log(interestBtn);
			$("#lecCodeID").val(lecCode);
			$("#memID").val(memId);
			$.ajax({
						url : "${cPath}/enrolment/interest.do",
						data : insertForm.serialize(),
						method : "post",
						dataType : "json",
						success : function(resp) {
							if (resp.interestResult == 1) {
								$("#" + interestBtn).attr("disabled",
										"disabled");
								$("#" + interestBtn).val("관심과목");
							}
						},
						error : function(xhr) {
							alert("이미 관심 등록된 강의 입니다.");
						}
					});
		};

		//수강 신청 =======================================================================================================	
		let x = 1;
		function insert(lecCode, memId, insertBtnId, subName, memName, lecFull,
				lecNmt, lecGrd, subDetail, subCredit) {
			if (totalCredit < 18) {
				if (Number(lecFull) > Number(lecNmt)) {
					var insertTr = "";

					//총학점 계산
					plusTotalCredit(subCredit);

					let idBtnText = "btn" + x;
					$("#lecCodeID").val(lecCode);
					$("#memID").val(memId);
					lecNmt = Number(lecNmt) + 1;
					$.ajax({
								url : "${cPath}/enrolment/insert.do",
								data : insertForm.serialize(),
								method : "post",
								dataType : "json",
								success : function(resp) {
									if (resp.result == 1) {
										insertTr += "<tr id=TrId"+x+">";
										insertTr += ("<td><button class='enrBtn btn btn-primary' onclick='deleted2("
												+ lecCode
												+ ","
												+ memId
												+ ","
												+ x + "," + subCredit + ")')>수강취소</button></td>");
										insertTr += ("<td>" + lecCode + "</td>");
										insertTr += ("<td>" + subName + "</td>");
										insertTr += ("<td>" + memName + "</td>");
										insertTr += ("<td>" + lecFull + "</td>");
										insertTr += ("<td>" + lecNmt + "</td>");
										insertTr += ("<td>" + lecGrd + "</td>");
										insertTr += ("<td>" + subDetail + "</td>");
										insertTr += ("<td>" + subCredit + "</td>");
										insertTr += "</tr>";
										$("#" + insertBtnId).attr("disabled",
												"disabled");
										$("#tbodyArea").append(insertTr);
										x = x + 1;
									}
								},
								error : function(xhr) {
									console.log(xhr);
								}
							});
				} else {
					alert("수강 정원 초과.");
				}
			} else {
				alert("신청 가능 학점을 넘겼습니다.");
			}
		};
		// 관심 조회에서 수강신청
		function insert2(lecCode, memId, idx, credit, lecFull, lecNmt) {
			if (totalCredit < 18) {
				if (Number(lecFull) > Number(lecNmt)) {

					plusTotalCredit(credit);

					$("#lecCodeID").val(lecCode);
					$("#memID").val(memId);
					let btnID = "insert2Btn" + idx;
					let interestTrId = "interest" + idx;
					$("#" + interestTrId).remove();
					$.ajax({
								url : "${cPath}/enrolment/insert.do",
								data : insertForm.serialize(),
								method : "post",
								dataType : "json",
								success : function(resp) {
									if (resp.result == 1) {
									}
								},
								error : function(xhr) {
									console.log(xhr);
								}
							});
				} else {
					alert("수강 정원 초과");
				}
			} else {
				alert("신청 가능 학점을 넘겼습니다.");
			}

		}

		// 수강취소===================================================================================================================
		function deleted2(lecCode, memId, idx, subCredit) {
			minusTotalCredit(subCredit);
			let Idtext = "TrId" + idx;
			$("#" + Idtext).remove();
			$("#dlelecCodeID").val(lecCode);
			$("#delmemID").val(memId);
			$.ajax({
				url : "${cPath}/enrolment/delete.do",
				data : deleteForm.serialize(),
				method : "post",
				dataType : "json",
				success : function(resp) {
					if (resp.deleteResult == 1) {
						$("#alertArea").show();
					}
				},
				error : function(xhr) {
					console.log(xhr);
				}
			});
		}

		function deleted(lecCode, memid, deletBtnId, subCredit) {
			minusTotalCredit(subCredit);

			$("#dlelecCodeID").val(lecCode);
			$("#delmemID").val(memid);
			let idx = deletBtnId.replace("deleteBtn", "");
			$("#" + idx).remove();
			$.ajax({
				url : "${cPath}/enrolment/delete.do",
				data : deleteForm.serialize(),
				method : "post",
				dataType : "json",
				success : function(resp) {
					if (resp.deleteResult == 1) {
						$("#alertArea").show();
					}
				},
				error : function(xhr) {
					console.log(xhr);
				}
			});

		};
		//총 학점  더하기=================================================================
		function plusTotalCredit(subCredit) {
			totalCredit += Number(subCredit);
			$("#totalArea").text(totalCredit);
		}
		//총 학점  뺴기=================================================================
		function minusTotalCredit(subCredit) {
			totalCredit -= Number(subCredit);
			$("#totalArea").text(totalCredit);
		}
	</script>

	<%-- <script src="${cPath}/resource/assets/libs/jquery/dist/jquery.min.js"></script> --%>
	<script
		src="${cPath}/resource/assets/libs/popper.js/dist/umd/popper.min.js"></script>
	<script
		src="${cPath}/resource/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- apps -->
	<script
		src="${cPath}/resource/dist/js/app-style-switcher.js"></script>
	<script
		src="${cPath}/resource/dist/js/feather.min.js"></script>
	<script
		src="${cPath}/resource/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
	<script
		src="${cPath}/resource/dist/js/sidebarmenu.js"></script>
	<!--Custom JavaScript -->
	<script
		src="${cPath}/resource/dist/js/custom.min.js"></script>
	<!--This page JavaScript -->
	<script
		src="${cPath}/resource/assets/extra-libs/c3/d3.min.js"></script>
	<script
		src="${cPath}/resource/assets/extra-libs/c3/c3.min.js"></script>
	<script
		src="${cPath}/resource/assets/libs/moment/min/moment.min.js"></script>
	<script
		src="${cPath}/resource/assets/libs/fullcalendar/dist/fullcalendar.min.js"></script>
	<script
		src="${cPath}/resource/dist/js/pages/calendar/cal-init.js"></script>
	<!-- This Page JS -->
	<script
		src="${cPath}/resource/assets/extra-libs/prism/prism.js"></script>
	<script
		src="${cPath}/resource/assets/libs/chartist/dist/chartist.min.js"></script>
	<script
		src="${cPath}/resource/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
	<script
		src="${cPath}/resource/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
	<script
		src="${cPath}/resource/assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
	<script
		src="${cPath}/resource/dist/js/pages/dashboards/dashboard1.min.js"></script>
</body>
</html>