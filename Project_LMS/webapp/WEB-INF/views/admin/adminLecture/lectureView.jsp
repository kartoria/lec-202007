<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="${cPath}/css/admin/lecture.css">
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 교과목관리 > 강의관리
			</h5>
		</div>
	</div>
	<div class="row mb-3 border-bottom border-admin p-0 my-2">
		<div class="col-12">
			<h2 class="font-weight-bold">강의 상세 조회</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">강의 상세정보를 조회할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row mt-3">
		<div class="col-sm-12">
			<div class="card">
				<table class="table text-center">
					<tbody>
						<tr>
							<td class="table-active"><p class="font-weight-bold">과목코드</p></td>
							<td>${lecVO.subCode }</td>
							<td class="table-active"><p class="font-weight-bold">강의코드</p></td>
							<td>${lecVO.lecCode }</td>
							<td class="table-active"><p class="font-weight-bold">강의명</p></td>
							<td>${lecVO.subjectVo.subName }</td>
						</tr>
						<tr>
							<td class="table-active"><p class="font-weight-bold">교수코드</p></td>
							<td>${lecVO.memId }</td>
							<td class="table-active"><p class="font-weight-bold">교수명</p></td>
							<td colspan="3">${lecVO.memberVo.memName }</td>
						</tr>
						<tr>
							<td class="table-active"><p class="font-weight-bold">개설학년</p></td>
							<td>${lecVO.lecGrd }학년</td>
							<td class="table-active"><p class="font-weight-bold">정원</p></td>
							<td>${lecVO.lecFull }명</td>
							<td class="table-active"><p class="font-weight-bold">수강인원</p></td>
							<td>${lecVO.lecNmt }명<input type="hidden" name="lecNmt" value="${lecVO.lecNmt }"></td>
						</tr>
						<tr>
							<td class="table-active"><p class="font-weight-bold">개설학기</p></td>
							<td colspan="5">
							<c:set var="lecSmst" value="${lecVO.lecSmst }"/>
							${fn:substring(lecSmst,0,4) }년
							${fn:substring(lecSmst,5,6) }학기
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="table-active"><p class="font-weight-bold">강의실/강의시간</p></td>
							<td colspan="5">
							<c:set var="timeList" value="${timeList }" />
							<c:if test="${not empty timeList }">
								<c:forEach items="${timeList }" var="room"
									varStatus="status">
									<p>${room.roomCode }호 
									${room.ltimeStart }-${room.ltimeEnd }교시
									${room.ltimeDay }요일</p>
								</c:forEach>
							</c:if>
							<c:if test="${empty collegeList }">
								<c:if test="${lecVO.lecDelete eq 'Y'}">
									<p class="text-danger">폐강</p>
								</c:if>
							</c:if>
							</td>
							<td></td>
						</tr>
						<tr>
							<td class="table-active"><p class="font-weight-bold">학부/학과</p></td>
							<td colspan="5">
								<div id="depTable" class="table-responsive">
									<table class="table">
										<tbody>
											<tr>
												<th class="table-info text-center" >단과대</th>
												<th class="table-info text-center" >학과</th>
											</tr>
											<tr>
												<td class="text-center">${lecVO.code.codeName }</td>
												<td class="text-center"> ${lecVO.departmentVo.depName }</td>
											</tr>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div> <!-- card -->
			<div id="btnDiv">
			<c:if test="${lecVO.lecDelete eq 'N'}">
				<button type="button" id="updateBtn" class="btn waves-effect waves-light btn-warning px-4 py-2 mx-1">수정</button>
				<button type="button" id="deleteBtn" class="btn waves-effect waves-light btn-danger px-4 py-2 mx-1">폐강</button>
			</c:if>	
			<button type="button" id="resetBtn" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1">목록</button>
			</div>	
		</div>
	</div>
</div>
<form id="lecCodeForm" action="${cPath }/admin/updateLectureForm.do">
	<input type="hidden" name="lecDelete" >
	<input type="hidden" name="lecCode" value="${lecVO.lecCode }">
</form>
<!-- ============= 삭제 확인 모달 ================ -->
<!-- ========================================= -->
<div id="delModal" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="danger-header-modalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header modal-colored-header bg-danger">
				<h4 class="modal-title font-weight-bold" id="danger-header-modalLabel">삭제 여부 확인</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<h4 class="font-weight-bold">해당 강의를 폐강하시겠습니까?</h4>
			</div>
			<div class="modal-footer" style="margin: auto;">
				<button type="button" class="btn btn-light" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-danger" id="goDelBtn">폐강</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script>
$("#updateBtn").on("click",function(){
	$("#lecCodeForm").submit();
});
$("#resetBtn").on("click", function(){
	location.href="${cPath}/admin/lectureList.do"
});
$("#deleteBtn").on("click",function(){
	let lecNmt = $("input[name=lecNmt]").val();
// 	if(lecNmt>0){
// 		alert("수강중인 학생이 있으므로 폐강이 불가합니다.");
// 		return false;
// 	}
	$("#delModal").modal();
});
$("#goDelBtn").on("click", function() {
	$("#lecCodeForm").attr("action","${cPath}/admin/deleteLecture.do");
	$("input[name='lecDelete']").val("Y");
	$("#lecCodeForm").submit();
});	
</script>