<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id="printDiv" class="container-fluid">
<div class="row my-3">
	<div class="col-12 d-flex justify-content-start align-items-center">
		<h5 class="ml-2">
			<i class="fas fa-home"></i> 학점 및 성적 > 전체 성적
		</h5>
	</div>
</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">전체 성적</h2>
		</div>
	</div>
	<div class="row mt-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">이번학기를 제외한 이전 학기들의 성적입니다.</h5>
		</div>
	</div>
	<div class="row mt-3">
		<div class="col-12 d-flex justify-content-end align-items-center">
			<button type="button" class="btn waves-effect waves-light btn-light ml-3" id="printBtn"><i class="icon-printer"></i>출력</button>
		</div>
	</div>
	<!-- ================== 첫 div =========================== -->
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-center">
			<table class="table table-sm table-bordered text-center">
				<thead class="thead-light text-dark">
					<tr>
						<th style="width:20%">총 신청학점</th>
						<th style="width:20%">총 획득학점</th>
						<th style="width:20%">졸업이수학점</th>
						<th style="width:20%">평점 계</th>
						<th style="width:20%">평점 평균</th>
					</tr>
				</thead>
				<tbody class="bg-white text-dark">
					<tr>
						<td>${totalGrade.totalCredit }</td>
						<td>${totalGrade.totalGetCredit }</td>
						<td>${totalGrade.totalGetCredit }</td>
						<td>${totalGrade.totalGradeMulCredit }</td>
						<td>${totalGrade.avgGradeMulCredit }</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- ================== 첫 div END ========================= -->
	
	<!-- ================== 두번째 div  ========================== -->
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-center">
			<table class="table table-sm table-bordered text-center">
			  <thead class="thead-light text-dark">
					<tr>
						<th style="width:20%">전공필수</th>
						<th style="width:20%">전공선택</th>
						<th style="width:20%">교양필수</th>
						<th style="width:20%">교양선택</th>
						<th style="width:20%">자유선택</th>
					</tr>
				</thead>
			    <tbody class="bg-white text-dark">
					<tr>
						<td>${totalGrade.totalMRGrade}</td>
						<td>${totalGrade.totalMEGrade}</td>
						<td>${totalGrade.totalGRGrade}</td>
						<td>${totalGrade.totalGEGrade}</td>
						<td>${totalGrade.totalFEGrade}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- ================== 두번째 div END =======================-->
	
	<!-- ================== 세번째 div ===========================-->
	<div class="row my-3">
		<div class="col-3 d-flex justify-content-center">
			<table class="table table-hover table-sm table-bordered text-center">
				<thead class="bg-primary text-white">
					<tr>
						<th>년도</th>
						<th>학기</th>
						<th>이수형태</th>
					</tr>
				</thead>
				<tbody id="smstBody" class="bg-white text-dark">
				<c:forEach items="${smstList}" var="gradeVO" varStatus="status">
					<c:if test="${status.last }">
						<c:set var="lastSmst" value="${status.current.lecSmst}"></c:set>
					</c:if>
					<fmt:formatNumber var="grd" value="${(gradeVO.tlecGrd/2 + 0.5)-((gradeVO.tlecGrd/2 + 0.5)%1)}"/> <!-- n 학년 -->
					<fmt:formatNumber var="smst" value="${gradeVO.tlecGrd % 2 == 0? 2 : 1}"/> <!-- n 학기 -->
					<tr data-lec-smst="${gradeVO.lecSmst}" data-grd="${grd }" data-smst="${smst }">
						<td>${fn:substring(gradeVO.lecSmst,0,4)}</td>
						<td>${grd}-${smst}</td>
						<td>정시학기</td>
					</tr>
				</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4" class="text-danger">학기를 선택하세요</td>
					</tr>
				</tfoot>
			</table>	
		</div>
		<div class="col-9">
			<div class="row">
				<div class="col-12 px-0">
					<table class="table table-sm table-bordered text-center">
						<thead class="bg-primary text-white">
							<tr >
								<th style="width:16.67%">해당학기</th>
								<th style="width:16.67%">신청학점</th>
								<th style="width:16.67%">취득학점</th>
								<th style="width:16.67%">평점계</th>
								<th style="width:16.67%">평점평균</th>
								<th style="width:16.65%">석차</th>
							</tr>
						</thead>
						<tbody class="bg-white text-dark">	
							<tr>
								<td class="grd-smst"></td>
								<td class="totalCredit"></td>
								<td class="getCredit"></td>
								<td class="totalCreditMulGrade"></td>
								<td class="avgCreditMulGrade"></td>
								<td class="rank"></td>
							</tr>
						</tbody>	
					</table>
				</div>
			</div>		
			<div class="row">
				<div class="col-12 px-0">
					<table id="smstTable" class="table table-sm table-bordered text-center">
						<thead class="bg-primary text-white">
							<tr>
								<th style="width:16.67%">강의번호</th>			
								<th style="width:33.34%">과목명</th>			
								<th style="width:16.67%">이수구분</th>			
								<th style="width:8.335%">학점</th>			
								<th style="width:8.335%">등급</th>			
								<th style="width:16.65%">비고</th>			
							</tr>
						</thead>
						<tbody id="listBody" class="bg-white text-dark">
						</tbody>	
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- ================== 세번째 div END =======================-->
</div>

<form id="getGradeListForm" action="${cPath}/lms/student/grade/getGradeList.do" method="post">
	<input type="hidden" name="lecSmst" value="${lastSmst}">
</form>

<script type="text/javascript">
	let listBody = $("#listBody");
	let lecSmst = $("input[name='lecSmst']");
	let gradeListForm = $("#getGradeListForm");
	
	$("#getGradeListForm").ajaxForm({
		dataType:"json",
		success : function(resp){
			listBody.empty();
			let trTags = [];
			let totalCredit = 0; // 신청학점 총합
			let totalGetCredit = 0; // 취득학점 총합
			let totalCreditMulGrade = 0; // (학점 * 등급) 총합
			let smstGradeList = resp.smstGradeList
			if(smstGradeList.length > 0){
				$(smstGradeList).each(function(idx, grade){
					totalCredit += grade.subCredit;
					if(grade.codeName != 0){
						totalGetCredit += grade.subCredit;
						totalCreditMulGrade += grade.codeName * grade.subCredit; 
					}
					
					let tr = $("<tr>");
					tr.append(
							$("<td>").text(grade.lecCode).attr("data-lecCode", grade.lecCode),
							$("<td>").text(grade.subName).addClass("text-left"),
							$("<td>").text(grade.subDetail),
							$("<td>").text(grade.subCredit),
							$("<td>").text(grade.code),
							$("<td>") // 비고
					);
					trTags.push(tr);
				});
				$(".grd-smst").html(resp.lecSmst);
				$(".totalCredit").html(totalCredit);
				$(".getCredit").html(totalGetCredit);
				$(".totalCreditMulGrade").html(totalCreditMulGrade);
				$(".avgCreditMulGrade").html(Math.round(totalCreditMulGrade/totalCredit*100)/100);
				$(".rank").html(resp.rank);
			}else{
				trTags.push($("<tr>").html($("<td>").text("수강이력이 없습니다.").attr("colspan","6")));
			}
			listBody.html(trTags);
		}
	}).submit();
	
	$("#smstBody").on("click", "tr", function(){
		let lecSmstValue = $(this).data("lecSmst");
		lecSmst.val(lecSmstValue);
		gradeListForm.submit();
	});
	
	//프린트 
	$("#printBtn").on("click",function(){
		$("#acceptedHeader").show();
		var con_test = confirm("해당 페이지를 출력하시겠습니까?");
		if(con_test == true){
		    var initBody = document.body.innerHTML;
		    window.onbeforeprint = function () {
				
		       document.body.innerHTML = document.getElementById("printDiv").innerHTML;
		    }
		    window.onafterprint = function () {
		       window.location.reload()
		       
		    }
		    window.print();
		}
		else if(con_test == false){
		  $("#acceptedHeader").hide();
		  return false;
		}
		
		$("#acceptedHeader").hide();
	}); 

	
</script>