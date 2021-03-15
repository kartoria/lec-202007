<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 강의 관리 > 성적 등록</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">성적 등록</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex align-items-center">
			<h5 class="ml-2 mb-0">이번 학기의 출석점수, 과제점수, 중간고사, 기말고사의 점수를 합친 총점을 기준으로 성적을 평가해주세요</h5><br>
		</div>
		<div class="col-12 d-flex align-items-center ml-2">
			<h5 class="text-danger mb-0">사이버캠퍼스-내 강의실-학습활동-출결 관리  에서 [출석점수등록]을 진행하여야 합니다.</h5>
		</div>
		<div class="col-12 d-flex align-items-center ml-2">
			<h5 class="text-primary">출석 : 10% , 과제 : 20%, 중간고사 : 35%, 기말고사 : 35%  학교 내부 평가기준으로 산정됩니다.</h5>
		</div>
	</div>
	<form:form id="selectLectureForm" commandName="insertGradeVO">
	<div class="row my-3">
		<div class="col-12 col-md-8 d-flex justify-content-end align-items-center">
			<div class="d-flex custom-control custom-radio custom-control-inline">
				<form:radiobutton path="howToSort" id="radio-1" value="학번" cssClass="custom-control-input"/>
				<label class="custom-control-label" for="radio-1">학번 순</label>
			</div>
			<div class="custom-control custom-radio custom-control-inline">
				<form:radiobutton path="howToSort" id="radio-2" value="석차" cssClass="custom-control-input"/>
				<label class="custom-control-label" for="radio-2">석차 순</label>
			</div>
		</div>
		<div class="col-12 col-md-4 d-flex align-items-stretch">
	        <form:select id="lectureSelect" path="lecCode" cssClass="form-control">
           		<form:options items="${lectureList}" itemLabel="subName" itemValue="lecCode"/>
           		<c:if test="${empty lectureList}">
           			<option value="">강의 이력이 없습니다.</option>
           		</c:if>
	        </form:select>
		</div>
	</div>
    </form:form>
	<div class="row my-3">
		<div class="col-12 table-responsive-md">
			<table class="table">
				<thead class="thead-light">
					<tr class="text-center text-dark">
						<th class="font-weight-bold">학번</th>
						<th class="font-weight-bold">이름</th>
						<th class="font-weight-bold">출석점수(10)</th>
						<th class="font-weight-bold">과제점수(20)</th>
						<th class="font-weight-bold">중간고사(35)</th>
						<th class="font-weight-bold">기말고사(35)</th>
						<th class="font-weight-bold">총점(100)</th>
						<th class="font-weight-bold">석차</th>
						<th class="font-weight-bold">평가</th>
					</tr>
				</thead>
				<tbody class="text-center text-dark">
				<c:choose>
					<c:when test="${not empty studentScoreList}">
						<c:forEach items="${studentScoreList}" var="studentScore" varStatus="status">
							<tr>
								<td>${studentScore.memId}</td>
								<td>${studentScore.memName}</td>
								<td>${studentScore.scrAttend eq null ?  '-' : studentScore.scrAttend} </td>
								<td>${studentScore.scrTask eq null ? '-' : studentScore.scrTask}</td>
								<td>${studentScore.scrMiddle eq null ? '-' : studentScore.scrMiddle}</td>
								<td>${studentScore.scrFinal eq null ? '-' : studentScore.scrFinal}</td>
								<td>${studentScore.totalScore eq null ? '-' : studentScore.totalScore}</td>
								<td>${studentScore.rank}</td>
								<td>
									<c:if test="${not empty studentScore.totalScore}">
									<select class="selectGrade form-control" data-tlec-no="${studentScore.tlecNo}">
										<c:forEach items="${gradeCodeList}" var="gradeCode">
											<option value="${gradeCode.slistCode}" ${studentScore.slistCode eq gradeCode.slistCode ? "selected" : ""}  >
												${gradeCode.slistCode}
											</option>
										</c:forEach>
									</select>
									</c:if>
									<c:if test="${empty studentScore.totalScore}">
									<input type="text" class="form-control"  value="성적정보가 없습니다." readonly/>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="8">수강생 정보가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</div>    

<form:form id="insertGradeForm" commandName="insertGradeVO" action="${cPath}/lms/professor/class/insertGrade.do" >
	<form:hidden path="tlecNo"/>
	<form:hidden path="slistCode"/>
</form:form>
<script type="text/javascript">
	let insertGradeForm = $("#insertGradeForm");
	
	insertGradeForm.ajaxForm({
		dataType:"json",
		success:function(resp){
			new Noty({
				 text: resp.text, 
				 layout: resp.layout,
				 type: resp.type,
				 timeout: resp.timeout,
			}).show();
		}	
	});	
	
	$("#lectureSelect, input[name='howToSort']").on("change", function(){
		$("#selectLectureForm").submit();
	});
	
	$(".selectGrade").on("change", function(){
		let tlecNo = $(this).data("tlecNo");
		let slistCode = $(this).val();
		insertGradeForm.find("[name='tlecNo']").val(tlecNo);
		insertGradeForm.find("[name='slistCode']").val(slistCode);
		
		insertGradeForm.submit();
	});
</script>
