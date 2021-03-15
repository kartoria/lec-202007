<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<link rel="stylesheet" href="${cPath}/css/admin/student.css">  
<input type="hidden" name="" >
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 학생관리 > 학생조회
			</h5>
		</div>
	</div>
 	<div class="row mb-3 border-bottom border-admin p-0 my-2">
		 <div class="col-12">
			<h2 class="font-weight-bold">학생 상세 정보 </h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">학생 상세 정보를 조회할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-body">
					<div>
						<table class="table text-center">
							<tr>
								<td class="w-25" rowspan="4" colspan="2">
									<c:if test="${not empty stuVO.memImg }">
										<img id="img" class="imgSize" src="${cPath}/memberImages/${stuVO.memImg }" />
									</c:if>
									<c:if test="${ empty stuVO.memImg }">
										<img id="img" class="imgSize"  src='${cPath}/images/defaultImg.jpg' />
									</c:if>
								</td>
								<td class="table-active"><p class="font-weight-bold">이름 </p></td>
								<td>${stuVO.memName }</td>
								<td class="table-active"><p class="font-weight-bold">학부/학과<span class="importantText">*</span></p></td>
								<td>${stuVO.depName}</td>
							</tr>
							<tr>
							</tr>
							<tr>
								<td class="table-active"><p class="font-weight-bold">학년</p></td>
								<td><fmt:formatNumber var="grd" value="${(stuVO.memGrd/2 + 0.5) - ((stuVO.memGrd/2 + 0.5)%1)}"/> 
										${grd}학년 </td>
								<td class="table-active"><p class="font-weight-bold">주민번호</p></td>
								<td colspan="3">${stuVO.memReg1} - ******* </td>
							</tr>
							<tr>
								<td class="table-active"><p class="font-weight-bold">주소</p></td>
								<td>${stuVO.memZip } ${stuVO.memAddr1}</td>
								<td class="table-active"><p class="font-weight-bold">상세주소</p></td>
								<td colspan="3">${stuVO.memAddr2}</td>
							</tr>
							<tr>
								<td class="table-active"><p class="font-weight-bold">학적상태</p></td>
								<td>${stuVO.codeName}</td>
								<td class="table-active"><p class="font-weight-bold">이메일</p></td>
								<td>${stuVO.memMail}</td>
								<td class="table-active"><p class="font-weight-bold">전화번호</p></td>
								<td id="formatNum"><input type="hidden" name="memTel" value="${stuVO.memTel}"></td>
							</tr>
							<tr>
								<td class="table-active" ><p class="font-weight-bold">계좌</p></td>
								<td colspan="5">
									<div class="table-responsive pl-5 ml-5">
										<table class="table">
											<tr>
												<th class="table-info text-center">은행</th>
												<th scope="col">${stuVO.memBank }</th>
												<th class="table-info text-center">예금주</th>
												<th scope="col">${stuVO.memName }</th>
												<th class="table-info text-center">계좌번호</th>
												<th scope="col">${stuVO.memAcn }</th>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>	
				</div>
			</div>
			<div class="text-right">
				<c:if test="${stuVO.codeName ne '졸업' or '자퇴'}">
				<button type="button" id="updateBtn" class="btn waves-effect waves-light btn-warning px-4 py-2 mx-2">수정</button>
				</c:if>
				<button type="button" id="resetBtn" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1">목록</button>
			</div>							
		</div>
	</div>	
</div>
<form id="updateForm" action="${cPath }/admin/updateStudentForm.do">
 <input type="hidden" name="memId" value="${stuVO.memId }">
</form>
<form id="searchForm" method="post" action="${cPath }/admin/studentList.do">
	<input type="hidden" name="college" value="${searchVO.college}" >
	<input type="hidden" name="department" value="${searchVO.department}" >
	<input type="hidden" name="state" value="${searchVO.state}" >
	<input type="hidden" name="grade" value="${searchVO.grade}" >
	<input type="hidden" name="memType" value="${searchVO.memType}" >
	<input type="hidden" name="searchType" value="${searchVO.searchType}" >
	<input type="hidden" name="searchWord" value="${searchVO.searchWord}" >
</form>
<script>

$(function(){
	let memTel = $("input[name='memTel']").val();
	let formatNum = '';
	if(memTel==null){
		memTel = "";
	}else if(memTel.length==11){
        formatNum = memTel.replace(/(\d{3})(\d{4})(\d{4})/, '$1-****-$3');
	}
	
	$("#formatNum").text(formatNum);
	
	
	
});

$("#resetBtn").on("click", function(){
	$("#searchForm").submit();
// 	location.href="${cPath}/admin/studentList.do";
});
$("#updateBtn").on("click",function(){
	$("#updateForm").submit();
});

</script>							