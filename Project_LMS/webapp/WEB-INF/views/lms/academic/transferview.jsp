<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<security:authorize access="isAuthenticated()">
<security:authentication property="principal" var="principal"/>
<c:set var="authMember" value="${principal.realMember }" />
</security:authorize>
<div class="container-fluid border px-3" style="min-height:300px">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i>학적 관리 > 전과 신청서 발급</h5>
		</div>
	</div>
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-9 text-left">
			<h2 class="font-weight-bold">전과 신청서</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-11">
			<h5 class="pb-5 ml-2">전과 신청서를 발급 받을 수 있습니다.</h5>
		</div>
	</div>
	<c:if test="${authMember.memState eq 'ING'}">
		<div id="pdfArea" class="container" style="width: 50%; height: 1000px;">
			<iframe id="iframId" src="${cPath}/lms/student/academic/transferPdf.do" style="width: 80%; height: 960px;" ></iframe>
		</div>
	</c:if>
	<c:if test="${authMember.memState ne 'ING'}">
		<div class="text-center" style="width: 100%; height:480px; margin-top: 300px;">
		 	<h2>재학생만 전과 신청서 발급이 가능합니다.</h2>
		 </div>
	</c:if>
</div>
<script type="text/javascript">
$(function(){
	$('#iframId').ready(function(){
		$.ajax({
			url : "${cPath}/pdf/pdfInsert.do",
			data : {"pdfName":"전과신청서"},
			method : "post",
			dataType : "json",
		});
	});
});
</script>
