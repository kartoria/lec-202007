<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 학점 및 성적 > 출결 관리
			</h5>
		</div>
	</div>
	<div class="row mt-3 mb-5 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">강의별 출결 정보</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-4 text-left text-gray my-auto">
			<ul class="list-inline my-auto">
			  <li class="list-inline-item mx-3">출석 : ○</li>
			  <li class="list-inline-item mx-3">결석 : ／</li>
			  <li class="list-inline-item mx-3">지각 : △ </li>
			  <li class="list-inline-item mx-3">조퇴 : ▽ </li>
			</ul>
		</div>
		<div class="col-4 text-right text-gray my-auto ">
			<label class="my-auto w-100" for="lectureSelect">조회할 과목을 선택하세요</label>
		</div>
		<div class="col-4 text-right text-dark">
			<form:form id="selectLectureForm" commandName="gradeVO">
		        <form:select id="lectureSelect" path="lecCode" class="form-control" >
            		<form:options items="${lectureList}" itemLabel="subName" itemValue="lecCode"/>
            		<c:if test="${empty lectureList}">
            			<option value="">수강 정보가 없습니다.</option>
            		</c:if>
		        </form:select>
	        </form:form>
		</div>
	</div>
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
				<tbody class="bg-white text-dark">
					<tr>
						<td>${totalAttendance.totalTime }</td>
						<td>${totalAttendance.countAttend }</td>
						<td>${totalAttendance.countLate + totalAttendance.countEarly }</td>
						<td>${totalAttendance.countAbsent }</td>
						<td>${totalAttendance.avgAtten}%</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-12 text-left text-dark">
			<table id="table1" class="table table-sm table-bordered text-center">
				<thead class="bg-primary text-white">
					<tr>
						<th>주차</th>
						<th>날짜</th>
						<th>강의시간</th>
						<th>출결정보</th>
						<th>비고</th>
					</tr>
				</thead>
				<tbody class="bg-white text-dark">
				<c:choose>
					<c:when test="${not empty attendanceList}">
						<c:forEach items="${attendanceList}" var="attendance" varStatus="status">
							<tr>
								<td>${attendance.week}</td>
								<td>${attendance.attenDate}</td>
								<td>${attendance.ltime}</td>
								<td class="text-danger font-weight-bold" data-atten-time="${attendance.attenTime }">${attendance.codeName}</td>
								<td class="text-left pl-2">${attendance.attenNote}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">출결정보가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row mx-3">
		<div class="col-12 text-right text-gray">
		</div>
	</div>
</div>    

<script type="text/javascript">
	$("#lectureSelect").on("change", function(){
		$("#selectLectureForm").submit();
	});
</script>
