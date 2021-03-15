<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
.imgSize{
   width: 100%;
    height: 100%;
    object-fit: cover;
}
.box {
    width: 200px;
    height: 200px; 
    border-radius: 10%;
    overflow: hidden;
    
}
h5,h2{
	color: #7c8798;
}
</style>

<div class="container-fluid border  px-3" style="min-height:300px">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 개인정보</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">개인정보 </h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"> 개인정보를 확인하세요.</h5>
		</div>
	</div>
	<div class="row my-3 mt-5">
		<div class="col-12">
			<div class="float-right mr-5">
				<button class="btn btn-warning btn-lg dropdown-toggle" type="button"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
					id="updateBtn">수정</button>
			</div>
			<div class="container">
				<div class="box m-auto mt-5">
					<img id="img" class="imgSize" src="${imagePath }"  />
					<c:if test="${empty profileVO.memImg  }">
						<img id="img2" class="imgSize" src='${cPath}/images/defaultImg.jpg'  alt='${cPath}/images/defaultImg.jpg'/>
					</c:if>
					<c:if test="${not empty profileVO.memImg  }">
						<img id="img2" class="imgSize" src='${cPath }/memberImages/${profileVO.memImg }'  />
					</c:if>

				</div>
			</div>
			<div class="row my-3 mt-5">
				<table class="table mx-5" style="table-layout: fixed;">
					<tbody id="listBody" class="text-center ">
						<tr>
							<td class="table-active"><p class="font-weight-bold">학번</p></td>
							<td >${profileVO.memId }</td>
								
							<td class="table-active"><p class="font-weight-bold">이름</p></td>
							<td >${profileVO.memName }</td>
							<td class="table-active"><p class="font-weight-bold">생년월일</p></td>
							<td>${profileVO.memReg1 }</td>
						</tr>
						<tr>
							<td class="table-active"><p class="font-weight-bold">학과</p></td>
							<td>${profileVO.depName }</td>
							<td class="table-active"><p class="font-weight-bold">학년</p></td>
							<td >
								<fmt:formatNumber var="grd" value="${(profileVO.memGrd/2 + 0.5) - ((profileVO.memGrd/2 + 0.5)%1)}"/>
								${grd }학년
							</td>
							<td class="table-active"><p class="font-weight-bold">전화번호</p></td>
							<td>${profileVO.memTel }</td>
	
						</tr>
						<tr>
							<td class="table-active"><p class="font-weight-bold">이메일</p></td>
							<td >${profileVO.memMail }</td>
							<td class="table-active" ><p class="font-weight-bold">주소</p>
							</td>
							<td >
								<span id="span1">${profileVO.memAddr1 }</span>
							</td>
							<td class="table-active" ><p class="font-weight-bold">상세 주소</p>
							</td>
							<td >
								<span id="span2">${profileVO.memAddr2 }</span>
							</td>
						</tr>
						<tr>
							<td class="table-active" ><p class="font-weight-bold">계좌</p></td>
							
							<td colspan="1">
								<span id="bankOption">${profileVO.memBank }</span>
							</td>
							<td class="table-active"><p class="font-weight-bold">예금주</p></td>
							<td >
								<span>${profileVO.memName }</span>
							</td>
							<td class="table-active"><p class="font-weight-bold">계좌번호</p></td>
							<td >
								<span>${profileVO.memAcn }</span>
							</td>
						</tr>
						
						
						
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- ============================================================== -->
<!-- 수정 시 비밀번호 입력 모달  -->
<!-- ============================================================== -->
<div class="modal fade" id="passModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="myCenterModalLabel">비밀번호 재입력</h3>
				<button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
				<div id="searchArea" class="card-body">
					<div class="input-group">
						<p><span class="badge badge-warning">주의</span>비밀번호를 재입력 하셔야 수정을 할 수 있습니다</p>
						<form id="modalForm" action="${pageContext.request.contextPath}/profile/profilePass.do" method="post">
							<input type="password" id="example-input-large" name="memPass" class="form-control form-control-lg" placeholder="비밀번호를 입력해주세요.">
						</form>
						<button id="modalBtn" type="button" class="btn btn-primary btn-lg">
							
							<i class="fas fa-location-arrow"></i>
						</button>
					</div>
				</div>
			</div>
        </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
$(function (){
	$("#updateBtn").on("click", function(){
		$("#passModal").modal();	
	});
	
	$("#modalBtn").on("click", function(){
		$("#modalForm").submit();
	});
	$("#img2").hide();
});
</script>