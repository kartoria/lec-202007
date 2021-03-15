<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<div class="container-fluid border px-3" style="min-height:300px">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 강의 관리 > 개설 강의 목록</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">개설 강의 목록</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">과목을 선택하시면 강의계획서를 확인할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row text-center">
		<div class="col-sm-12">
			<table class="table" >
				<thead class="thead-light">
					<tr class="text-center text-dark">
						<th class="font-weight-bold">과목 코드</th>
						<th class="font-weight-bold">과목명</th>
						<th class="font-weight-bold">강의실</th>
						<th class="font-weight-bold">강의 시간</th>
						<th class="font-weight-bold">개설 학년</th>
					</tr>
				</thead>
				<tbody>
				<c:if test="${not empty proLectureList }">
				<c:set var="ltimeTimeText" value=""/>
					<c:forEach items="${proLectureList }" var="proLecture" varStatus="status">
						<c:if test="${proLectureList[status.index+1].lecCode eq proLecture.lecCode }">
							<c:set var="ltimeTimeText" value="${ltimeTimeText}  ${ proLecture.ltimeDay }요일  ${proLecture.ltimeStart }교시  ~ ${proLecture.ltimeEnd }교시 ,"/>
						</c:if>
						<c:if test="${proLectureList[status.index+1].lecCode ne proLecture.lecCode }">
							<tr class="issuedInsert text-center "
								onclick="window.open('${cPath}/plan.do?lecCode=${proLecture.lecCode }','new','scrollbars=yes,resizable=no width=800px height=1000px, left=0,top=0');return false">
								
								<td class="w-20">${proLecture.lecCode }</td>
								<td class="w-20">${proLecture.subName }</td>
								<td class="w-20">${proLecture.roomCode }</td>
								<td class="w-30">
									${ltimeTimeText}
									${ proLecture.ltimeDay }요일  ${proLecture.ltimeStart }교시  ~ ${proLecture.ltimeEnd }교시
								</td>
								<td class="w-10">${proLecture.lecGrd }</td>
								<c:set var="ltimeTimeText" value=""/>
							</tr>
						</c:if>
					</c:forEach>
				</c:if>	
				<c:if test="${ empty proLectureList }">
					<tr class="text-center ">
						<td colspan="5"><h4>개설 강의가 없습니다.</h4></td>
					</tr> 
				</c:if>	
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$('.issuedInsert').on("click",function(){
		$.ajax({
			url : "${cPath}/pdf/pdfInsert.do",
			data : {"pdfName":"강의계획서"},
			method : "post",
			dataType : "json",
		});
	});
});
</script>

			