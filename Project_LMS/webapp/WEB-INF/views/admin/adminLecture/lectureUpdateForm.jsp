<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<link rel="stylesheet" href="${cPath}/css/admin/lecture.css">
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 교과목관리 > 강의관리
			</h5>
		</div>
	</div>
	<div class="row mb-3 border-bottom border-admin p-0">
		<div class="col-12">
			<h2 class="font-weight-bold">강의 수정</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">강의 정보를 수정할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-body">
				<form:form commandName="lecVO"  method="post" id="updateLecForm" action="${cPath}/admin/updateLecture.do">
					<table class="table">
						<tbody>
							<tr>
								<td class="table-active"><p class="font-weight-bold">과목코드</p></td>
								<td><input type="text" name="subCode" value="${lecVO.subCode }" class="form-control" readonly></td>
								<td class="table-active"><p class="font-weight-bold">강의코드</p></td>
								<td><input type="text" name="lecCode" value="${lecVO.lecCode }" class="form-control" readonly></td>
								<td class="table-active"><p class="font-weight-bold">강의명</p></td>
								<td><input type="text" name="subName" value="${lecVO.subjectVo.subName }" class="form-control" readonly></td>
							</tr>
							<tr>
								<td class="table-active"><p class="font-weight-bold">교수코드</p></td>
								<td><input type="text" name="memId" value="${lecVO.memId }" class="form-control" readonly></td>
								<td class="table-active"><p class="font-weight-bold">교수명</p></td>
								<td><input type="text" name="memName" value="${lecVO.memberVo.memName }" class="form-control" readonly></td>
								<td class="table-active"><p class="font-weight-bold">개설학년</p></td>
								<td>
									<select name="lecGrd" class="form-control" required="required">
										<option value>선택</option>
										<c:forEach var="idx" begin="1" end="4" step="1">
											 	<option value="${idx}" <c:out value="${lecVO.lecGrd eq idx ? 'selected' :'' } "/>>${idx}학년</option>
										</c:forEach>
									</select>
									<form:errors path="lecGrd" element="span" cssClass="error"/>
								</td>
							</tr>
							<tr>
								<td class="table-active"><p class="font-weight-bold">정원</p></td>
								<td><input type="text" name="lecFull" value="${lecVO.lecFull }" class="form-control" ></td>
								<td class="table-active"><p class="font-weight-bold">수강일수</p></td>
								<td><input type="number" name="lecDays" value="${lecVO.lecDays }" class="form-control"></td>
								<td class="table-active"><p class="font-weight-bold">개설학기</p></td>
								<td>${lecVO.lecSmst }</td>
							</tr>
							<tr>
								<td class="table-active"><p class="font-weight-bold">강의실/강의시간</p></td>
								<td></td>
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
								</c:if>
								</td>
							</tr>
							<tr>
								<td class="table-active"><p class="font-weight-bold">학부/학과</p></td>
								<td colspan="5">
									<div id="depTable" class="table-responsive">
										<table class="table">
											<tbody>
												<tr>
													<th class="table-info" style="text-align: center;">단과대</th>
													<th class="table-info" style="text-align: center;">학과</th>
												</tr>
												<tr>
													<td style="text-align: center;">${lecVO.code.codeName }</td>
													<td style="text-align: center;">${lecVO.departmentVo.depName }</td>
												</tr>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					</form:form>	
				</div>
			</div> <!-- card -->
			<div id="btnDiv">
			<button type="button" id="updateBtn" class="btn waves-effect waves-light btn-warning px-4 py-2 mx-1">수정</button>
			<button type="button" id="cancelBtn" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1">취소</button>
			</div>
		</div>
	</div>
</div>

<script>
$("#updateBtn").on("click",function(){
	$("#updateLecForm").submit();
	
});
$("#cancelBtn").on("click", function(){
	window.history.back();
});
</script>