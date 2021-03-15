<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 사이버캠퍼스 > 내 강의실 > 토론게시판</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">토론 게시판</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">현재 참여할 수 있는 토론을 조회할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-12 table-responsive">
			<table class="table">
				<thead class="thead-light">
					<tr class="text-center text-dark">
						<th style="width:5%" class="font-weight-bold"></th>
						<th style="width:10%" class="font-weight-bold">번호</th>
						<th style="width:45%" class="font-weight-bold">제목</th>
						<th style="width:10%" class="font-weight-bold">작성자</th>
						<th style="width:20%" class="font-weight-bold">작성일</th>
						<th style="width:5%" class="font-weight-bold">조회수</th>
					</tr>
				</thead>
				<tbody class="bg-white">
				<c:forEach items="${discussList}" var="discuss" varStatus="status">
					<tr class="text-dark">
						<td class="text-center"><i class="fa fa-list"></i></td>
						<td class="text-center">${discuss.rnum}</td>
						<td><a href="${cPath}/myclass/${lecCode}/${discuss.boNo}/disView.do">${discuss.boTitle}</a></td>
						<td class="text-center">${discuss.memberVO.memName}</td>
						<td class="text-center">${discuss.boDate}</td>
						<td class="text-center">${discuss.boHit}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty discussList }">
					<tr>
						<td class="text-center text-danger" colspan="6">게시글이 없습니다.</td>
					</tr>
				</c:if>
				</tbody>
			</table>				
		</div>
	</div>
	<c:if test="${authMember.memId eq professorId}">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-end align-items-center">
			<a class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2" href="${cPath}/myclass/${lecCode}/disInsertForm.do" role="button" id="insertBtn">등록</a>
		</div>
	</div>		
	</c:if>
</div>
