<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="${cPath}/css/admin/lecture.css">
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 교과목관리 > 과목관리
			</h5>
		</div>
	</div>
	<div class="row mb-3 border-bottom border-admin p-0">
		<div class="col-12">
			<h2 class="font-weight-bold">과목 상세 조회</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">과목 상세정보를 조회할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row mt-3">
		<div class="col-sm-12">
			<div class="card">
				<table class="table text-center">
					<tbody>
						<tr>
							<td class="table-active"><p class="font-weight-bold">과목코드</p></td>
							<td>${subVO.subCode }</td>
							<td class="table-active"><p class="font-weight-bold">과목명</p></td>
							<td>${subVO.subName }</td>
						</tr>
						<tr>
							<td class="table-active"><p class="font-weight-bold">학점</p></td>
							<td>${subVO.subCredit }</td>
							<td class="table-active"><p class="font-weight-bold">이수구분</p></td>
							<td>${subVO.subDetail }</td>
						</tr>
						<tr>
							<td class="table-active"><p class="font-weight-bold">개설년도</p></td>
							<td colspan="3">${subVO.subDate }</td>
						</tr>	
						<tr>
							<td class="table-active"><p class="font-weight-bold">학부/학과</p></td>
							<td colspan="3">
								<div id="depTable" class="table-responsive">
									<table class="table">
										<tbody>
											<tr>
												<th class="table-info text-center" >단과대</th>
												<th class="table-info text-center" >학과</th>
											</tr>
											<tr>
												<td class="text-center">${subVO.codeName }</td>
												<td class="text-center"> ${subVO.depName}</td>
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
			<button type="button" id="updateBtn" class="btn waves-effect waves-light btn-warning px-4 py-2 mx-1">수정</button>	
			<button type="button" id="resetBtn" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1">목록</button>
			</div>	
		</div>
	</div>
</div>
<form id="subCodeForm" method="post" action="${cPath }/admin/updateSubjectForm.do">
	<input type="hidden" name="subCode" value="${subVO.subCode }">
</form>
<script>
//수정버튼클릭
$("#updateBtn").on("click",function(){
	$("#subCodeForm").submit();
});
//목록버튼클릭
$("#resetBtn").on("click", function(){
	location.href="${cPath}/admin/subjectList.do"
});
//삭제버튼클릭
$("#deleteBtn").on("click",function(){
	$("#delModal").modal();
});
//삭제여부 확인버튼 클릭
$("#goDelBtn").on("click", function() {
	$("#subCodeForm").attr("action", "${cPath }/admin/deleteSubjectForm.do");
	$("#subCodeForm").submit();
});	
</script>