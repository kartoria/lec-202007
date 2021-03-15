<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>


<c:if test="${first eq 'first'}">
	<c:import url="/student/firstlogin.do"></c:import>
</c:if>

<c:if test="${first ne 'first'}">
<div class="col-3 mx-3 my-5">
	<div class="each-icon box-1 py-5 go-page" data-href="${cPath}/lms/lmsmain.do">
		<div class="icon-wrap">
			<img src="${cPath}/images/admin/adminSelectPage/selectPageStu.png" width="100px" height="100px"/>
			<h1 class="text-white mt-2">학사관리</h1>
		</div>
		<div class="icon-text">
			<div class="cta">
				<i class="fa fa-arrow-right" aria-hidden="true"></i>
			</div>
		</div>
	</div>
</div>
<div class="col-3 mx-3 my-5">
	<div class="each-icon box-2 py-5 go-page" data-href="${cPath}/cyber/main.do">
		<div class="icon-wrap">
			<img src="${cPath}/images/admin/adminSelectPage/online-class.png" width="100px" height="100px"/>
			<h1 class="text-white mt-2">사이버 캠퍼스</h1>
		</div>
		<div class="icon-text">
			<div class="cta">
				<i class="fa fa-arrow-right" aria-hidden="true"></i>
			</div>
		</div>
	</div>
</div>
<div class="col-3 mx-3 my-5" onclick="sessionTimer()">
	<div class="each-icon box-3 py-5 go-page" data-href="${cPath}/enrolment/list.do">
		<div class="icon-wrap">
			<img src="${cPath}/images/admin/adminSelectPage/selectPagenotes.png" width="100px" height="100px" />
			<h1 class="text-white mt-2">수강신청</h1>
		</div>
		<div class="icon-text">
			<div class="cta">
				<i class="fa fa-arrow-right" aria-hidden="true" ></i>
			</div>
		</div>
	</div>
</div>
</c:if>

<script type="text/javascript">
$(".each-icon").on("click", function(){
	let goPage = $(this).data("href");
	location.href=goPage;
});

function sessionTimer(){
	window.localStorage.setItem('flag', 'on');
	window.localStorage.setItem('intervals', 59);
	window.localStorage.setItem('intervalsMin', 29);
}

$(function(){
	$("#mainBtn").remove();
});
</script>
