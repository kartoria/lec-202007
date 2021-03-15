<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 마이클래스 > 학습목차 > 실시간 강의</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">주차별 강의 목록</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">이전 강의들은 다시보기로 제공됩니다.</h5>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-12 table-responsive">
			<table class="table">
				<thead class="thead-light">
					<tr class="text-dark text-center">
						<th class="font-weight-bold" style="width:5%"></th>
						<th class="font-weight-bold" style="width:5%">주차</th>
						<th class="font-weight-bold" style="width:50%">강의 제목</th>
						<th class="font-weight-bold" style="width:10%">방송 시작시간</th>
					</tr>
				</thead>
				<tbody class="bg-white">
				<c:forEach items="${liveList}" var="live" varStatus="status">
					<c:url value="/myclass/${lecCode}/${live.liveNo}/liveView.do" var="liveUrl"></c:url>
					<tr class="text-center text-dark">
						<td class="align-middle"><i class="fa fa-list"></i></td>
						<td class="align-middle">${live.liveWeek}</td>
						<td class="align-middle text-left"><a href="${liveUrl}">${live.broadcastTitle}</a></td>
						<td class="align-middle">${live.uploadTime}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty liveList}">
					<tr class="text-danger">
						<td class="text-center" colspan="5">강의가 없습니다.</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
	</div>
	<c:if test="${authMember.memId eq professorId}">
		<div class="row my-3">
			<div class="col-12 d-flex justify-content-end align-items-center">
				<a href="${cPath}/myclass/${lecCode}/liveForm.do" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2">실시간 방송 등록</a>
			</div>
		</div>
	</c:if>
</div>
