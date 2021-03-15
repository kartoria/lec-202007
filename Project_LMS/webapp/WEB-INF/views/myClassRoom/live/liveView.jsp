<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/myclass/${lecCode}/liveList.do" var="listUrl"></c:url>
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 마이클래스 > 학습목차 > 실시간 강의
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 col-md-8 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">${liveVO.liveWeek}주차 실시간 강의 입니다.</h2>
		</div>
		<div class="col-12 col-md-4 d-flex justify-content-end align-items-end">
			<h4 class="font-weight-bold">${liveVO.uploadTime}</h4>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">실시간 강의가 끝나면 다시보기 영상으로 제공됩니다.</h5>
		</div>
	</div>
	<c:if test="${not empty streamKey and authMember.memId eq liveVO.memId}">
	<div class="alert alert-info my-3 mx-5" role="alert">
		<span class="text-dark">스트림 키 : </span>${streamKey}
	</div>
	</c:if>
	<div class="row my-3">
		<div class="col-12">
			<div id="tabDiv" class="row">
				<div class="col-12 pr-0 d-flex justify-content-end">
					<ul class="nav nav-tabs">
						<li class="nav-item"><a href="#chatAria" data-toggle="tab" aria-expanded="false" class="nav-link active"> <span class="d-none d-lg-block">채팅</span>
						</a></li>
						<li class="nav-item">
							<a href="#attAria" data-toggle="tab" aria-expanded="true" class="nav-link"> 
								<span class="d-none d-lg-block">출석부</span>
							</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div id="youtubeDiv" class="col-8 embed-responsive embed-responsive-16by9">
					<iframe class="embed-responsive-item" src="https://www.youtube.com/embed/${liveVO.broadcastId}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				</div>
				<div id="sideDiv" class="col-4 p-0 tab-content d-flex">
					<div id="chatAria" class="tab-pane show active w-100" style="height: 578.609px">
						<iframe class="w-100 h-100" src="https://www.youtube.com/live_chat?v=${liveVO.broadcastId}&embed_domain=localhost"></iframe>
					</div>
					<div id="attAria" class="tab-pane overflow-auto border w-100" style="height: 578.609px">
						<table class="table table-sm">
							<thead>
								<tr class="align-middle text-center">
									<th class="w-25">학번</th>
									<th class="w-25">이름</th>
									<th class="w-25">출석시간</th>
									<th class="w-25">출결상태 <a id="refreshBtn" href="javascript:void(0)"><i class="fas fa-redo"></i></a></th>
								</tr>
							</thead>
							<tbody id="attendanceBody">
								<c:forEach items="${attendanceList}" var="att" varStatus="status">
									<tr>
										<td class="align-middle pl-2 text-center">${att.memId}</td>
										<td class="align-middle pl-2 text-center">${att.memName}</td>
										<td class="align-middle pl-2 text-center attenTimeTd">${att.attenTime}</td>
										<td class="attenCodeTd">
<%-- 											<c:choose> --%>
<%-- 												<c:when test="${empty att.attenTime}"> --%>
<!-- 													<input type="text" class="form-control text-center" readonly="readonly" value="미출결"> -->
<%-- 												</c:when> --%>
<%-- 												<c:otherwise> --%>
													<select class="selectAttendance form-control" data-mem-id="${att.memId}" data-mem-name="${att.memName}">
														<c:forEach items="${attendanceCodeList}" var="code">
															<option class="text-center" value="${code.attenCode}">${code.codeName}</option>
														</c:forEach>
													</select>
<%-- 												</c:otherwise> --%>
<%-- 											</c:choose></td> --%>
									</tr>
								</c:forEach>
								<c:if test="${empty attendanceList}">
									<tr>
										<td colspan="4" class="text-center">수강중인 학생이 없습니다.</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-12">
			<form:form id="btnForm" commandName="liveVO" action="${cPath}/myclass/deleteLive.do" cssClass="form-inline d-flex justify-content-end">
				<form:hidden path="lecCode" />
				<form:hidden path="liveNo" />
				<c:if test="${authMember.memId eq liveVO.memId}">
					<div class="form-group">
						<input type="submit" value="삭제" class="btn waves-effect waves-light btn-warning px-4 py-2" />
					</div>
				</c:if>
				<div class="form-group">
					<a href="${listUrl}" class="btn waves-effect waves-light btn-light px-4 py-2 ml-3">목록으로</a>
				</div>
			</form:form>
		</div>
	</div>
</div>

<form:form id="updateAttendanceForm" commandName="liveVO" action="${cPath}/myclass/updateAttendance.do">
	<form:hidden path="lecCode" value="${lecCode}" />
	<form:hidden path="liveWeek" value="${liveVO.liveWeek}" />
	<form:hidden path="professorId" value="${liveVO.memId}" />
	<form:hidden path="memName"/>
	<form:hidden path="memId" />
	<form:hidden path="attenCode" />
	
</form:form>

<form:form id="getAttendanceListForm" commandName="liveVO" action="${cPath}/myclass/getAttendanceList.do">
	<form:hidden path="lecCode" value="${lecCode}" />
	<form:hidden path="liveWeek" value="${liveVO.liveWeek}" />
</form:form>


<c:if test="${authMember.memId eq liveVO.memId }">
	<script type="text/javascript">
		let updateAttendanceForm = $("#updateAttendanceForm");

		updateAttendanceForm.ajaxForm({
			dataType : "json",
			success : function(resp) {
				new Noty({
					 text: resp.text, 
					 layout: resp.layout,
					 type: resp.type,
					 timeout: resp.timeout,
				}).show();
			}
		});

		$(".selectAttendance").on("change", function() {
			let memId = $(this).data("memId");
			let memName = $(this).data("memName");
			let attenCode = $(this).val();
			if (attenCode != "" || attenCode != null) {
				updateAttendanceForm.find("[name='memName']").val(memName);
				updateAttendanceForm.find("[name='memId']").val(memId);
				updateAttendanceForm.find("[name='attenCode']").val(attenCode);

				updateAttendanceForm.submit();
			}
		});
	</script>
</c:if>


<script type="text/javascript">

	$(function(){
		let attendanceBody = $("#attendanceBody");
		
		let getAttendanceList = $("#getAttendanceListForm").ajaxForm({
			dataType: "json",
			success: function(resp){
				console.log("submit");
				let attenTimeList = resp.attenTimeList;
				if(attenTimeList){
					$(attenTimeList).each(function(idx, att){
// 						console.log(attendanceBody.children("tr:eq("+idx+")"));
// 						console.log(att.attenTime);
// 						console.log(att.attenCode);
						attendanceBody.children("tr:eq("+idx+")").children(".attenTimeTd").text(att.attenTime);
						let attenCodeSelectTag = attendanceBody.children("tr:eq("+idx+")").children(".attenCodeTd").children(".selectAttendance");
						if(att.attenCode == null){
							attenCodeSelectTag.val("ABSENT").prop("selected", true);
						}else{
							attenCodeSelectTag.val(att.attenCode).prop("selected", true);
						}
					});
				}
			}
		}).submit();
		
		$("#refreshBtn").on("click", function(){
			getAttendanceList.submit();
			return false;
		});
		
		// push 알림용 websocket
		const PROTOCOL = location.protocol=="http:"?"ws:":"wss";
		const DOMAIN = location.hostname;
		const CONTEXTPATH = $.getContextPath();
		const PORT = location.port?":"+location.port : "";
		var pushWs = new WebSocket(PROTOCOL+"//"+DOMAIN+PORT+CONTEXTPATH+"/pushMessage");
		var consolePrintHandler=function(event){
			console.log(event);
		}
		pushWs.onmessage=function(event){
			let payload = event.data;
			let messageVO = JSON.parse(payload);
			let messageTargetList = messageVO.messageTargetList
			if(messageVO.messageType == "LIVEATTENDANCE"){
				getAttendanceList.submit();
			};
			console.log(messageTargetList);
			if( messageTargetList.includes("${authMember.memId}") ){
				new Noty({
					 text: messageVO.message, 
					 layout: 'topCenter',
					 type: 'info',
					 timeout: 2000,
					 progressBar: true
				}).show();
			}
		}
		pushWs.onerror=consolePrintHandler;
		
	});
</script>

<c:if test="${authMember.memId ne liveVO.memId }">
	<script type="text/javascript">
		$(function() {
			$(".selectAttendance").attr("disabled", "disabled");
		});
	</script>
</c:if>


<c:if test="${chat eq false}">
	<script type="text/javascript">
		$(function() {
			$("#sideDiv").remove();
			$("#tabDiv").remove();
			$(".card").children(".row").addClass("justify-content-center");
		})
	</script>
</c:if>


