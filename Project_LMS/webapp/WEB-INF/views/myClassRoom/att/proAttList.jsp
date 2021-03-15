<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<security:authorize access="isAuthenticated()">
	<security:authentication property="principal" var="principal" />
	<c:set var="authMember" value="${principal.realMember }" />
	<input type="hidden" id="memType" value="${authMember.memType }">
</security:authorize>

<div class="container-fluid border px-3" style="min-height: 300px">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 학습활동 > 출결관리
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">출결 관리</h2>
		</div>
	</div>
	<div class="row mt-3 mb-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">
				<span class="font-weight-bold">온라인 출결 - </span>
			</h5>
			<div class="col-4 text-left text-gray my-auto">
				<ul class="list-inline my-auto">
					<li class="list-inline-item mx-3">출석 : ○</li>
					<li class="list-inline-item mx-3">결석 : ／</li>
					<li class="list-inline-item mx-3">미완료 : △</li>
				</ul>
			</div>
			<!-- 출석: o | 결석 x | 조퇴/지각 △ -->
		</div>
		<table class="attInfoTable table-bordered w-50 text-left ml-3">
			<tr>
				<td class="font-weight-light">출결 점수 등록</td>
				<td class="text-left font-weight-light">해당 강의에대한 학생의 출결점수에 반영됩니다.</td>
			</tr>
			<tr>
				<td class="font-weight-light">출결 정정</td>
				<td class="text-left font-weight-light">각각의 출결정보에 대해 교수 권한으로 변경이 가능합니다.</td>
			</tr>
		</table>
	</div>
	<c:if test="${authMember.memType eq 'ROLE_PROFESSOR' }">
		<div class="row my-2 d-flex justify-content-end align-items-center">
			<div class="col-3 ">
				<select class="form-control" id="inlineFormCustomSelect1">
					<option>선택하세요</option>
					<c:if test="${not empty attList }">
						<c:forEach items="${attList }" var="att">
							<option value="${att.memId }">${att.memName }</option>
						</c:forEach>
					</c:if>
				</select>
			</div>
		</div>
	</c:if>
	<div class="row my-3">
		<div class="col-12">
			<table class="table table-sm table-bordered text-center ">
				<thead class="thead-light text-dark">
					<tr>
						<th>총 강의시간</th>
						<th>출석</th>
						<th>미완료</th>
						<th>결석</th>
						<th>출석률</th>
					</tr>
				</thead>
				<tbody class="totalAttAvg bg-white text-dark">
				</tbody>
			</table>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-12">
			<div class="table-responsive">
				<table class="table table-sm table-bordered text-center">
					<tbody id="tbodyArea">
						<tr>
							<th class="bg-primary text-white font-bold">주차</th>
							<th class="bg-primary text-white font-bold">출석방법</th>
							<th class="bg-primary text-white font-bold">출석여부</th>
							<th class="bg-primary text-white font-bold">출석날짜</th>
							<th class="bg-primary text-white font-bold">총 영상재생시간</th>
							<!-- 							<th class="bg-primary text-white font-bold">접속시간</th> -->
							<th class="bg-primary text-white font-bold">영상시간</th>
						</tr>
						<c:forEach var="i" begin="1" end="15" step="1">
							<tr>
								<td class="font-weight-bold">${i}주차</td>
								<td class="font-weight-bold"><span id="week${i}-how">-</span></td>
								<td class="font-weight-bold"><span id="week${i}">-</span></td>
								<td class="font-weight-bold"><span id="week${i}-date">-</span></td>
								<td class="font-weight-bold" id="week${i}-totalView">-</td>
								<%-- 								<td class="font-weight-bold" id="week${i}-session">-</td> --%>
								<td class="font-weight-bold" id="week${i}-totalLen">-</td>
							</tr>
						</c:forEach>
						<tr class="text-center text-dark">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="row d-flex justify-content-end align-items-stretch">
		<div class="form-group">
			<form id="insertAttScoreFrom" method="post" action="${cPath}/myclass/${lecCode}/insertAttScore.do" class="form-inline flex-fill justify-content-end align-items-stretch">
				<input type="hidden" name="tlecNo"> <select name="scrAttend" id="scrAttend" class="form-control" required="required">
					<option selected>점수선택</option>
					<c:forEach var="i" begin="0" end="10" step="1">
						<option value="${i}">${i}점</option>
					</c:forEach>
				</select>
			</form>
		</div>
		<div class="form-group">
			<button id="insertAttBtn" type="button" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2">출결점수등록</button>
		</div>
		<div class="form-group">
			<button class="updateBtn btn float-right waves-effect waves-light btn-warning px-4 py-2 mx-2">출결 정정</button>
			<span id="errorText" class="text-danger"></span>
		</div>
	</div>
</div>

<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title" id="myCenterModalLabel">출결 수정</h3>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<div id="searchArea" class="card-body">
					<div class="input-group">

						<select class="form-control" id="modalSelect1">

						</select> <select class="form-control" id="modalSelect2">
							<option>출결을 선택하세요.</option>
							<option value="ATTEND">출석</option>
							<option value="LATE">지각</option>
							<option value="ABSENT">결석</option>
							<option value="EARLY">조퇴</option>
						</select>
						<form id="modalForm">
							<input type="hidden" id="memId" name="memId" /> <input type="hidden" id="week" name="week" value="NO" /> <input type="hidden" id="attenCode" name="attenCode" value="NO" />
						</form>
						<button id="modalBtn" type="button" class="btn btn-primary btn-lg">
							<i class="fas fa-location-arrow"></i>
						</button>
						<div class="row">
							<p id="errorSpan" class="text-danger"></p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->



<script type="text/javascript">
	$(function() {
		$(".updateBtn").hide();
		$("#insertAttScoreFrom").hide();
		$("#insertAttBtn").hide();

		// 수정 버튼 눌렀을 시
		$(".updateBtn").on("click", function() {

			if ($("#scrAttend").attr("disabled")) {
				alert("이미 출석 점수가 등록이 되었습니다.");
				return false;
			}

			$("#updateModal").modal();
			$("#errorSpan").text("");
		});

		$("#modalBtn")
				.on(
						"click",
						function() {
							let weekVal = $("#week").val();
							let attenCodeVal = $("#attenCode").val();
							if (weekVal != "NO" && attenCodeVal != "NO") {
								let modalForm = $("#modalForm");
								$
										.ajax({
											url : "${cPath}/myclass/${lecCode}/proAttUpdate.do",
											data : modalForm.serialize(),
											method : "post",
											dataType : "json",
											success : function(resp) {
												if (resp.result > 0) {
													alert("출결이 변경 되었습니다.");
													$(location)
															.attr("href",
																	"${cPath}/myclass/${lecCode}/proAttList.do");

												} else {
													$("#errorText").text(
															"출결이 변경되지 않았습니다.");
												}
											},
											error : function(xhr) {
												console.log(xhr);
											}
										});

							} else {
								$("#errorSpan").text("변결할 주차와 출결 상태를 입력하세요.");
							}
						});

		<c:if test="${authMember.memType eq 'ROLE_STUDENT' }">
		selectAtt('${authMember.memId}');
		</c:if>
	});

	function selectAtt(memId) {
		$("#scrAttend").attr("disabled", false);

		let max = 0;
		$.ajax({
			url : "${cPath}/myclass/${lecCode}/proAttSelect.do",
			data : {"memId" : memId},
			method : "post",
			dataType : "json",
			success : function(resp) {
				// 영상시청기록 리스트
				let viewRecordList = resp.viewRecordList;
				// 출석률
				let totalAttendance = resp.totalAttendance;
				// 출석률 테이블
				let trTags = [];
				let tr = $("<tr>");
				tr.append($("<td>").text(totalAttendance.totalTime)
						, $("<td>").text(totalAttendance.countAttend)
						, $("<td>").text(totalAttendance.countEarly + totalAttendance.countLate)
						, $("<td>").text(totalAttendance.countAbsent)
						, $("<td>").text(totalAttendance.avgAtten + "%"));
				trTags.push(tr);

				$(".totalAttAvg").html(trTags);

				// 수강번호
				let tlecNo = resp.takeLecVO.tlecNo;
				$("input[name='tlecNo']").val(tlecNo);
				// 학생성적 출석 등록 여부
				if (resp.studentScoreVO != null) {
					let scrAttend = resp.studentScoreVO.scrAttend;
					if (scrAttend >= 0) {
						$("#scrAttend").val(scrAttend).prop("selected", true);
						$("#scrAttend").attr("disabled", true);
					}
					$("#scrAttend").attr("disabled", false);
				}
				console.log(resp.attList);
				if (resp.attList != null) {
					max = resp.attList.length;
					$("#modalSelect1").empty();
					// 수정 버튼 눌렀을시 변경 할 주차 생성
					$("#modalSelect1").append("<option>주차를 선택하세요</option>");
					for (let idx = 0; idx < max; idx++) {
						let weekNum = resp.attList[idx].week;
						$("#modalSelect1").append("<option value="+weekNum+">" + weekNum + "주차</option>");
					}
					// 출석 주차 id에 맞춰 세팅
					for (let i = 0; i < max; i++) {
						// 주차 아이디
						let weekId = "week" + resp.attList[i].week;
						// 출석방법
						let howId = "week" + resp.attList[i].week + "-how";
						// 출석날짜 아이디
						let dateId = "week" + resp.attList[i].week + "-date";
						// 재생시간
						let viewId = "week" + resp.attList[i].week + "-totalView";
						// 접속시간
						// let sessionId = "week"+resp.attList[i].week+"-session";
						// 영상길이
						let lenId = "week" + resp.attList[i].week
								+ "-totalLen";

						if (resp.attList[i].attenCode == "ATTEND") {
							$("#" + weekId).text("O");
						} else if (resp.attList[i].attenCode == "ABSENT") {
							$("#" + weekId).text("X");
						} else {
							$("#" + weekId).text("△");
						}
						
						// 영상 시청 기록
						if (viewRecordList.length > 0) {
							for (let j = 0; j < viewRecordList.length; j++) {
								$("#"+howId).text("실시간 강의");
								$("#" + dateId).text(resp.attList[i].attenDate);
								if (viewRecordList[j].week == resp.attList[i].week) {
									$("#" + dateId).text(viewRecordList[j].viewLast);
									$("#" + viewId).text(((viewRecordList[j].viewTime) / 60).toFixed(2) + " 분");
									// $("#"+sessionId).text(((viewRecordList[j].viewSession)/60).toFixed(2)+" 분");
									$("#" + lenId).text(((viewRecordList[j].videoLen) / 60).toFixed(2) + " 분");
									$("#"+howId).text("영상강의");
									break;
								}
							}
						}
					}
				}
			},
			error : function(xhr) {
				console.log(xhr);
			}
		});
	}

	$("#inlineFormCustomSelect1").change(function() {
		// 	$("#scrAttend").attr("disabled",false);
		// 	$("#scrAttend").prop("selected", false);

		option1 = this.value;
		if (option1 == "선택하세요") {
			$(".updateBtn").hide();
			for (let j = 1; j < 16; j++) {
				$("#week" + j).text("-");
			}
		} else {
			let memTypeFlag = $("#memType").val();
			if (memTypeFlag == "ROLE_PROFESSOR") {
				$(".updateBtn").show();
				$("#insertAttScoreFrom").show();
				$("#insertAttBtn").show();
			}
		}
		$("#memId").val(option1);

		selectAtt(option1);
	});

	$("#modalSelect1").change(function() {
		let modalOption1 = this.value;
		$("#week").val(modalOption1);
	});
	$("#modalSelect2").change(function() {
		let modalOption2 = this.value;
		$("#attenCode").val(modalOption2);

	});

	$("#insertAttBtn").on("click", function() {
		let scrAttend = $("#scrAttend option:selected").val();
		console.log(scrAttend);
		if (scrAttend == "" || scrAttend == null) {
			alert("점수를 선택해 주세요.");
			return false;
		}
		$.ajax({
			url : "${cPath}/myclass/${lecCode}/insertAttScore.do",
			type : "post",
			data : $("#insertAttScoreFrom").serialize(),
			dataType : "json",
			success : function(data) {
				alert(data.message);
				if (data.message = "등록 성공") {
					$("#scrAttend").attr("disabled", true);
				}
			}
		});
	});
</script>
