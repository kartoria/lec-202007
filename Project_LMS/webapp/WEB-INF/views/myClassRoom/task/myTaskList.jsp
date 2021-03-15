<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!-- *************************************************************** -->
<!-- End Top Leader Table -->
<!-- *************************************************************** -->
<c:set var="pagingVO" value="${paginationInfo.pagingVO }" />
<div class="container-fluid">
	<div class="row">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 내 강의실 > 과제
			</h5>
		</div>
	</div>
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-9 text-left">
			<h2 class="font-weight-bold">과제</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-11">
			<h5 class="pb-5 ml-2">과제를 제출하고 점수를 조회할 수 있습니다.</h5>
		</div>
		<div class="col-1 m-auto">
			<!-- =========== 버튼 입력 부분 ============= -->
				<security:authorize access="hasRole('ROLE_PROFESSOR')">
					<security:authentication property="principal" var="principal" />
					<c:set var="authMember" value="${principal.realMember }" />
						<a class="btn waves-effect waves-light btn-info px-4 py-2"
							href="${cPath}/myclass/${lecCode }/taskInsertForm.do" role="button"
							id="insertBtn">등록</a>
				</security:authorize>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<table class="table">
				<thead class="thead-light">
					<tr>
						<th class="font-weight-bold">과제 번호</th>
						<th class="font-weight-bold">과제명</th>
						<th class="font-weight-bold">제출 시작일</th>
						<th class="font-weight-bold">제출 마감일</th>
						<security:authorize access="hasRole('ROLE_STUDENT')">
						<th class="font-weight-bold">제출 여부</th>
						</security:authorize>
					</tr>
				</thead>
				<tbody id="listBody">
					<c:set var="taskList" value="${pagingVO.dataList }" />
					<c:choose>
					<c:when test="${not empty taskList }">
						<c:forEach items="${taskList }" var="task">
							<tr>
								<td><h4>${task.rnum }</h4></td>
								<c:url var="viewURL"
									value="/myclass/${lecCode}/${task.taskNo}/taskView.do" />
								<td><h4>
										<a href="${viewURL }">${task.taskTitle }</a>
									</h4></td>
								<td><h4>
										<i class="fas fa-calendar-alt"></i>&nbsp;${fn:substring(task.taskSday, 0, 11)}
									</h4></td>
								<td><h4>
										<i class="fas fa-calendar-alt"></i>&nbsp;${fn:substring(task.taskDday, 0, 11)}
									</h4></td>
								<security:authorize access="hasRole('ROLE_STUDENT')">
								<td>
										<security:authentication property="principal" var="principal" />
										<c:set var="authMember" value="${principal.realMember }" />
										<c:set var="taskNo" value="${task.taskNo }"/>
					                    	<c:choose>
					                    		<c:when test="${fn:contains(submitList, taskNo)}">
													<button type="button"
														class="btn waves-effect waves-light btn-success">
														<i class=" fas fa-check-circle"></i>
													</button>
												</c:when>
					                    		<c:otherwise>
						                    		<button type="button"
														class="btn waves-effect waves-light btn-outline-success">
														<i class="fas fa-times-circle"></i>
													</button>
					                    		</c:otherwise>
					                    	</c:choose>
					                 </td>   	
									</security:authorize> 
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5" align="center">현재 과제가 존재하지 않습니다.</td>
						</tr>
					</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<!-- ============================================================== -->
	<!--페이징 -->
	<!-- ============================================================== -->
	<div class="col-sm-12">
		<div class="dataTables_paginate paging_simple_numbers" id="pagingArea">
			<ui:pagination paginationInfo="${paginationInfo }"
				jsFunction="pageLinkNumber" type="bsNumber" />
		</div>
	</div>
</div>
<script type="text/javascript">
	//== 페이징 ==
	function pageLinkNumber(event) {
		event.preventDefault();
		let page = $(event.target).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;
	}

	// =========== 과제 제출 여부 ==========
</script>