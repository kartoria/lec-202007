<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<style type="text/css">
select {
	width: 20%;
}
.td {
	width: 15%;
}
</style>
<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 내 강의실 > 학습활동 > 과제
			</h5>
		</div>
	</div>
	<div class="card-body">
		<div class="col-11">
			<!-- 기한 -->
			<div class="alert alert-primary" role="alert">
				<input type="hidden" id="startDate" value="${task.taskSday }" /> <input
					type="hidden" id="endDate" value="${task.taskDday }" /> <i
					class="	fas fa-calendar-minus"> 과제 기한 -</i> ${task.taskSday} -
				${task.taskDday}
			</div>
			<!-- 배점 -->
			<div class="alert alert-success" role="alert">
				<i class="fas fa-bookmark"> 배점 -</i> ${task.taskCredit }점
			</div>
			<div class="card">
				<div class="table-responsive">
					<table class="table">
						<tbody id="listBody">
							<tr>
								<td class="table-active font-weight-bold td">제목</td>
								<td>${task.taskTitle }</td>
							</tr>
							<tr style="height: 400px">
								<td colspan="2">
									<div>
										<h4>${task.taskContent }</h4>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- 버튼 -->
			<div class="row my-3">
				<div class="col-12 d-flex justify-content-end align-items-center">
					<div class="form-inline">
						<div class="form-group">
							<c:url value="/myclass/${lecCode}/taskList.do" var="listURL" />
							<button type="button"
								class="btn waves-effect waves-light btn-light px-4 py-2 mx-2"
								id="resetBtn">목록</button>
							<security:authorize access="hasRole('ROLE_PROFESSOR')">
								<c:url value="/myclass/${lecCode}/taskUpdateForm.do"
									var="updateURL">
									<c:param name="taskNo" value="${task.taskNo }" />
								</c:url>
								<button type="button"
									class="btn waves-effect waves-light btn-warning px-4 py-2 mx-2"
									id="updateBtn">수정</button>
								<button type="button"
									class="btn waves-effect waves-light btn-danger px-4 py-2 mx-2"
									id="delBtn">삭제</button>
							</security:authorize>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- ============ 댓글 등록 ================= -->
	<!-- ==================================== -->
	<security:authorize access="hasRole('ROLE_STUDENT')">
		<form id="taskSbForm" method="post" action="${cPath}/taskSb/insert.do"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="taskNo" value="${task.taskNo }" />
			<div class="col-md-11">
				<b class="font-weight-bold">COMMENTS</b>
				<div class="card text-white" style="background-color: lightgray;">
					<div class="card-body">
						<div class="custom-file">
							<input type="file" id="inputGroupFile04" name="realFile" />
						</div>
						<textarea id="taskReply" name="taskReply" class="form-control"
							rows="5" placeholder="과제 내용을 작성해주세요."></textarea>
						<button type="button" class="btn btn-block btn-xs btn-secondary"
							id="submitBtn">등록</button>
					</div>
				</div>
			</div>
		</form>
	</security:authorize>
	<!-- ============ 댓글 LIST ================= -->
	<!-- ==================================== -->
	<div class="col-md-11">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table">
					<tbody id="trListBody">
					<thead class="thead-light">
						<tr>
							<th colspan="8"><h4 class="font-weight-bold">
									과제 제출 <span class="replyCountSpan text-danger"></span>개
								</h4></th>
						</tr>
					</thead>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-2 m-auto">
				<div id="pagingArea" class="text-center"></div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="lecCodeHidden" value="${lecCode}" />
<!-- 과제 제출 리스트 -->
<form:form id="searchForm" method="post"
	action="${cPath}/taskSb/list.do">
	<input type="hidden" name="taskNo" value="${task.taskNo }" />
	<input type="hidden" name="page" />
</form:form>
<!-- 과제  배점 등록 -->
<form:form id="insertPoint" method="post"
	action="${cPath}/taskSb/insertPoint.do">
	<input type="hidden" name="lecCode" value="${lecCode}" />
	<input type="hidden" name="taskNo" value="${task.taskNo }" />
</form:form>

<!-- ============= 삭제 확인 모달 ================ -->
<!-- ========================================= -->
<div id="delModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="danger-header-modalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header modal-colored-header bg-danger">
				<h4 class="modal-title font-weight-bold"
					id="danger-header-modalLabel">삭제 여부 확인</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<h4 class="font-weight-bold">정말 해당 과제를 삭제하시겠습니까?</h4>
			</div>
			<div class="modal-footer" style="margin: auto;">
				<button type="button" class="btn btn-light" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-danger" id="goDelBtn">삭제</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- ============= 등록한 과제 확인 모달================ -->
<!-- ========================================= -->
<div id="alertModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="danger-header-modalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header modal-colored-header bg-warning">
				<h4 class="modal-title font-weight-bold"
					id="danger-header-modalLabel">과제 등록 여부</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<h4 class="font-weight-bold">이미 제출하여 중복 제출이 불가한 과제입니다.</h4>
			</div>
			<div class="modal-footer" style="margin: auto;">
				<button type="button" class="btn btn-light" data-dismiss="modal">확인</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- ============= 삭제 확인 모달 ================ -->
<!-- ========================================= -->
<div id="delRepModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="danger-header-modalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header modal-colored-header bg-danger">
				<h4 class="modal-title font-weight-bold"
					id="danger-header-modalLabel">삭제 여부 확인</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<h4 class="font-weight-bold">정말 해당 과제를 삭제하시겠습니까?</h4>
			</div>
			<div class="modal-footer" style="margin: auto;">
				<button type="button" class="btn btn-light" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-danger" id="goDelRepBtn">삭제</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- /.modal -->
<!-- ============= 과제 확인 모달================ -->
<!-- ========================================= -->
<div id="noAns" class="modal fade" tabindex="-1" role="dialog"
	aria-hidden="true">
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-body p-4">
				<div class="text-center">
					<i class="dripicons-warning h1 text-warning"></i>
					<h4 class="font-weight-bold">제출 불가 기간</h4>
					<p class="mt-3">
						현재 과제 제출이 <br>불가능한 기간입니다.
					</p>
					<button type="button" class="btn btn-warning my-2"
						data-dismiss="modal">확인</button>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- =============== 과제 게시글 (교수) 삭제 =============== -->
<!-- =============================================== -->
<form:form id="delForm" commandName="task" method="post"
	action="${cPath}/myclass/${lecCode}/taskDel.do">
	<form:hidden path="taskNo" />
	<input type="hidden" name="memId" value="${principal.realMember }" />
</form:form>

<!-- =============== 제출 과제 (학생) 삭제 =============== -->
<!-- =============================================== -->
<form:form id="delRepForm" commandName="task" method="post"
	action="${cPath}/taskSb/delete.do">
	<input type="hidden" name="memId" value="${principal.realMember }" />
	<input type="hidden" name="tasksubmitNo" />
</form:form>

<!-- =============== 제출 과제  배점 =============== -->
<!-- =============================================== -->
<form:form id="taskScrForm" commandName="taskSb" method="post">
	<input type="hidden" name="tasksubmitNo" />
	<input type="hidden" name="lecCode" value="${lecCode}" />
	<input type="hidden" name="memId" value="${memId}">
	<input type="hidden" name="taskScr" />
</form:form>

<script type="text/javascript">
	let lecCode = $("#lecCodeHidden").val();

	$("#updateBtn").on("click", function() {
		location.href = "${updateURL }";
	});
	$("#delBtn").on("click", function() {
		$("#delModal").modal();
	});
	$("#goDelBtn").on("click", function() {
		$("#delForm").submit();
	});

	// paging
	let pagingArea = $("#pagingArea");
	let pagingA = pagingArea.on('click', "a", function() {
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;
	});

	let listTable = $("#trListBody");
	let taskReply = $("#taskReply");

	// =============== 과제 게시글 댓글 =======================	
	let searchForm = $("#searchForm").ajaxForm({
		dataType : "json",
		success : function(resp) {
		console.log(resp);
		listTable.find("tbody").empty();
		pagingArea.empty();
		let taskSbList = resp.pagingVO.dataList;
		let authMemId = resp.memId;
		let trTags = [];
		if (taskSbList) {
				// 하나씩 꺼내기
				$(taskSbList).each(function(idx, taskSb) {
					let tr = $("<tr>");
						tr.append(
							$("<td>").html("<h3>"+ taskSb.memId+ "</h3>").addClass("font-weight-bold").attr("data-memId",taskSb.memId),
							$("<td>").html("<h3>"+ taskSb.memberVO.memName+ "</h3>").addClass("font-weight-bold"),
							$("<td>").text(taskSb.taskReply).attr("data-tasksubmitno",taskSb.tasksubmitNo),
							$("<td>").text(taskSb.taskSubmitDate.substring(0,10)),
							$("<td>").html("<i class='fa fa-link'><a href='#;' onclick='downloadSb("+ taskSb.tasksubmitNo+ ")'>"+ taskSb.attFilename+ "</a></i>"));
									// 본인이 작성한 글 일때
									if (taskSb.memId == "${authMember.memId }") {
										// 1. 자기가 쓴 댓글이면 삭제버튼 넣어주기
										tr.append($("<td>").append(
											$("<button onClick='deleteBtn("+ taskSb.tasksubmitNo+ ")'>").attr({type : "button","id" : "myrepDel"}).addClass(
													"btn waves-effect waves-light btn-danger px-4 py-2 replyDelete").text("삭제")));
										// 2. 이미 과제를 등록했다고 등록 폼 막기
										taskReply.attr({"readOnly" : "readOnly","placeholder" : "이미 제출된 과제입니다."});
										$("#submitBtn").on("click",function(e) {
											e.preventDefault();
											//$("#alertModal").modal();
										});
										// 3. 제출했는데 배점을 받았을 때  2/5 이렇게
									} else {
										tr.append($("<td>"));
									}
									// 교수님일 때 
									if ("${authMember.memType }" == "ROLE_PROFESSOR") {
										if (taskSb.taskScr == null) {
											// 1. 배점을 안등록했을 떄
											let trFor = "";
											for (var i = 0; i < taskSb.taskCredit + 1; i++) {
												trFor += "<option value="+i+">"+ i+ "</option>";
											}
											tr.append(
												$("<td>").html(
														$("<select>").html(trFor).addClass("form-control selectBox")),
															$("<td>").append(
																$("<button onClick='insertPoint()'>").attr("type","button")
																		.addClass("btn waves-effect waves-light btn-primary px-4 py-2")
																		.text("등록")));
										} else {
											// 2. 배점을 등록했을 때 
											tr.append(
													$("<td>").text(taskSb.taskScr+ "점"),
													$("<td>").append(
														$("<button>").attr("type","button")
															.addClass("btn waves-effect waves-light btn-secondary px-4 py-2")
															.text("완료")))
										}
									}
									tr.data("taskSb", taskSb);
									trTags.push(tr);
								});
			} else {
				trTags.push($("<tr>").html($("<td>").text("현재 제출된 과제가 존재하지 않습니다.")));
				// 과제 등록 폼 막은거 풀기
				taskReply.removeAttr("readOnly", "placeholder");
			}
			$(".replyCountSpan")
					.text(resp.pagingVO.totalRecord);
			listTable.html(trTags);
			if (taskSbList.length > 0) {
				pagingArea.html(resp.pagingVO.pagingHTML);
			}
		},
		error : function(xhr) {
			console.log(xhr);
		}
	}).submit();

	// =============== 댓글 등록 ==================
	let taskSbForm;
	$("#submitBtn").on("click", function() {
		taskSubmit();
	});

	function taskSubmit() {
		taskSbForm = $("#taskSbForm").ajaxForm({
			dataType : "json",
			success : function(resp) {
				if (resp.result == "OK") {
					taskSbForm.get(0).reset();
					searchForm.find("[name='page']").val(1);
					searchForm.submit();
				}
			},
			error : function(xhr) {
				console.log(xhr);
			}
		}).submit();
	}

	//=========== 댓글 삭제 ================
	function deleteBtn(data) {
		let delRepForm = $("#delRepForm");
		delRepForm.find("input[name='tasksubmitNo']").val(data);

		$("#delRepModal").modal();

		$("#goDelRepBtn").on("click",function() {
			let tasksubmitNo = delRepForm.find("input[name='tasksubmitNo']").val();

			let taskSbDel = delRepForm.ajaxForm({
				dataType : "json",
				success : function(resp) {
					$("#delRepModal").modal('hide');
					if (resp.result == "OK") {                                 
						taskSbForm.get(0).reset();
						searchForm.find("[name='page']").val(1);
						taskReply.removeAttr("readOnly");
						taskReply.attr("placeholder","과제 내용을 작성해주세요.");
						$("#submitBtn").on("click",function() {
							$("#alertModal").hide();
							taskSbForm.get(0).reset();
							taskSubmit();
						});
						searchForm.submit();
					}
				},
				error : function(xhr) {
					console.log(xhr);
				}
			}).submit();
		});
	};
	// ============== 다운로드 ==============
	function downloadSb(tasksubmitNo) {
		location.href = "${cPath}/taskSb/download.do?tasksubmitNo="
				+ tasksubmitNo;
	}

	// =============== 과제 배점 등록 ===================
	function insertPoint(taskScr) {
		let btn = $(".btn-primary")
		// 1. 과제 번호
		let tasksubmitno = btn.parent().parent().children("td:eq(2)").data(
				"tasksubmitno");
		// 2. 등록할 배점 selected option value
		let selected = btn.parent().parent().children("td:eq(6)").find(
				".selectBox option:selected").val();
		let taskScrForm = $("#taskScrForm");
		let memId = btn.parent().parent().children("td:eq(0)").data("memid"); // 학생아이디
		taskScrForm.find("input[name='memId']").val(memId);
		taskScrForm.find("input[name='tasksubmitNo']").val(tasksubmitno);
		taskScrForm.find("input[name='taskScr']").val(selected);
		taskScrForm.attr("action", "${cPath}/taskSb/taskScrInsert.do");

		let taskScrInsert = taskScrForm.ajaxForm({
			dataType : "json",
			success : function(resp) {
				console.log(resp)
				if (resp.result == "OK") {
					searchForm.find("[name='page']").val(1);
					searchForm.submit();
				}
			},
			error : function(xhr) {
				console.log(xhr);
			}
		}).submit();
	}

	// ================= 과제 마감 시간 =====================
	let startDate = $("#startDate").val();
	let endDate = $("#endDate").val();

	$(function() {
		// 오늘날짜 구하기
		nStart = new Date().getTime(); //시작시간 체크(단위 ms)
		let date = new Date();
		let year = date.getFullYear(); //년도
		let month = date.getMonth() + 1; //월
		let day = date.getDate(); //일
		if ((day + "").length < 2) {
			day = "0" + day;
		}
		today = year + "-0" + month + "-" + day; // 오늘 날짜 (2017-02-07)

		if ("${authMember.memType }" == "ROLE_STUDENT") {
			if (!(startDate <= today && today <= endDate)) {
				$("#taskSbForm").hide();
				$("#myrepDel").hide();
				// 마감된 과제 일 때
				$("#noAns").modal();
			}
		}
		
	});
	$("#resetBtn").on("click", function() {
		location.href="${listURL }";
	});
		
</script>
