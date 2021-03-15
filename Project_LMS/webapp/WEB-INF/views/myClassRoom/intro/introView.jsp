<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 내 강의실 > 학습목차 > 강의소개</h5>
		</div>
	</div>
	
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">강의 소개</h2>
		</div>
	</div>
	
	<div class="row my-3">
		<div class="col-12 table-responsive">
			<table id="table1" class="table">
				<tbody>
					<tr>
						<th>강의 명</th>
						<td colspan="3">${lec.subjectVo.subName }</td>
					</tr>
					<tr>
						<th>강의 코드</th>
						<td>${lec.lecCode }</td>
						<th>과목 코드</th>
						<td>${lec.subCode }</td>
					</tr>
					<tr>
						<th>개설 학기</th>
						<td>2020년 1학기</td>
						<th>담당 교수</th>
						<td>${lec.memberVo.memName }</td>
					</tr>
					<tr>
						<th>학점</th>
						<td>${lec.subjectVo.subCredit }</td>
						<th>개설학년</th>
						<td>${lec.lecGrd }학년</td>
					</tr>
					<tr>
						<th>강좌 일수</th>
						<td>${lec.lecDays }주차</td>
						<th>수강 인원</th>
						<td>${lec.lecNmt }명</td>
					</tr>
					<tr>
						<th>주교재</th>
						<td colspan="3">${lec.lecMbk }</td>
					</tr>
					<tr>
						<th>부교재</th>
						<td colspan="3">${lec.lecSbk }</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-12 table-responsive">
			<table class="table table-sm">
				<thead class="bg-primary text-white">
					<tr>
						<th class="text-center font-weight-bold" colspan="2">강의 계획</th>
					</tr>
				</thead>
				<tbody class="text-center">
					<c:if test="${not empty lec.lecPlanList}">
						<c:forEach items="${lec.lecPlanList }" var="lecPlan" varStatus="idx">
							<tr>
								<th class="w-25">${lecPlan.lecWeek}주차</th>
								<td class="w-75">${lecPlan.lecContent}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		$("tbody > tr > th").addClass("bg-light font-weight-bold text-dark text-center").css("width", "16.66%");
		$("tbody > tr > td").addClass("bg-white text-left");
		
		$("#table1 > tbody > tr > td").each(function(){
			let colspan = $(this).attr("colspan");
			if(colspan == 3) $(this).css("width","83.34%");
			else $(this).css("width", "33.34%");
		});
	});
</script>
