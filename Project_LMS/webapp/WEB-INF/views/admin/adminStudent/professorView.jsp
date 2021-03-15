<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link rel="stylesheet" href="${cPath}/css/admin/student.css">
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 학생관리 > 교수조회
			</h5>
		</div>
	</div>
	<div class="row mb-3 border-bottom border-admin p-0 my-2">
		<div class="col-12">
			<h2 class="font-weight-bold">교수 상세 정보</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">교수 상세정보를 조회할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="card"> 
				<div class="card-body">
					<div>
						<table class="proViewTable table">
							<tr>
								<td class="w-25" rowspan="4" colspan="2">
									<c:if test="${not empty memVO.memImg }">
										<img id="img" class="imgSize" src="${cPath}/memberImages/${memVO.memImg }" />
									</c:if>
									<c:if test="${ empty memVO.memImg }">
										<img id="img" class="imgSize" src="${cPath}/images/defaultImg.jpg" />
									</c:if>
								</td>
								<td class="table-active"><p class="font-weight-bold">사번 </p></td>
								<td>${memVO.memId}</td>
								<td class="table-active"><p class="font-weight-bold">이름</p></td>
								<td>${memVO.memName}</td>
								<td class="table-active"><p class="font-weight-bold">학과</p></td>
								<td>${memVO.depName}</td>
							</tr>
							<tr>
							</tr>
							<tr>
								<td class="table-active"><p class="font-weight-bold">근무상태</p></td>
								<td>${memVO.description}</td>
								<td class="table-active"><p class="font-weight-bold">주민번호</p></td>
								<td colspan="3">${memVO.memReg1} - ******* </td>
							</tr>
							<tr>
								<td class="table-active"><p class="font-weight-bold">전화번호</p></td>
								<td id="formatNum"><input type="hidden" name="memTel" value="${memVO.memTel }"></td>
								<td class="table-active"><p class="font-weight-bold">입사년도</p></td>
								<td>${memVO.memAdmission}</td>
								<td class="table-active"><p class="font-weight-bold">이메일</p></td>
								<td>${memVO.memMail }</td>
							</tr>
							<tr>
								<td class="table-active" ><p class="font-weight-bold">주소</p></td>
								<td colspan="3"><span id="span1">${memVO.memAddr1 }</span></td>
								<td class="table-active" ><p class="font-weight-bold">상세 주소</p></td>
								<td colspan="2"><span id="span2">${memVO.memAddr2 }</span></td>
							</tr>
							<tr>
								<td class="table-active" ><p class="font-weight-bold">계좌</p></td>
								<td colspan="5">
									<div class="table-responsive pl-2">
										<table class="table">
											<tr>
												<th class="table-info text-center">은행</th>
												<td scope="col">${memVO.memBank }</td>
												<th class="table-info text-center">예금주</th>
												<td scope="col">${memVO.memName }</td>
												<th class="table-info text-center">계좌번호</th>
												<td scope="col" colspan="2">${memVO.memAcn }</td>
											</tr>
										</table>
									</div>
								</td>
								<td class="table-active"><p class="font-weight-bold">퇴사년도</p></td>
								<td>${memVO.memGraduation}</td>
							</tr>
						</table>
					</div>	
				</div>
			</div>	
			<div class="text-right">
				<button id="updateBtn" type="button" class="btn waves-effect waves-light btn-warning px-4 py-2 mx-2">수정</button>
				<button type="button" id="resetBtn" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1">목록</button>
			</div>
		</div>		
	</div>			
</div>
<form id="updateForm" method="post" action="${cPath}/admin/updateProfessorForm.do">
<input type="hidden" name="memId" value="${memVO.memId}">
</form>
<script type="text/javascript">
$(function (){
	
	let memTel = $("input[name='memTel']").val();
	let formatNum = '';
	if(memTel==null){
		memTel = "";
	}else if(memTel.length==11){
        formatNum = memTel.replace(/(\d{3})(\d{4})(\d{4})/, '$1-****-$3');
	}
	
	$("#formatNum").text(formatNum);
	
});

$("#updateBtn").on("click", function(){
		$("#updateForm").submit();
});

$("#resetBtn").on("click",function(){
	location.href="${cPath}/admin/professorList.do";
});


</script>