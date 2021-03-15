<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<div class="col-2 my-5">
	<div class="each-icon container Abox-1 w-85 py-5 go-page" data-href="${cPath}/admin/studentList.do">
		<div class="icon-wrap">
			<img src="${cPath}/images/admin/adminSelectPage/selectPageStu.png" width="100px" height="100px"/>
			<h2 class="text-white mt-2">학생 관리</h2>
		</div>
		<div class="icon-text">
			<div class="cta">
				<i class="fa fa-arrow-right" aria-hidden="true"></i>
			</div>
		</div>
	</div>
</div>
<div class="col-2  my-5">
	<div class="each-icon container Abox-2 w-85 py-5 go-page" data-href="${cPath}/admin/tuition.do">
		<div class="icon-wrap">
			<img src="${cPath}/images/admin/adminSelectPage/education-cost.png" width="100px" height="100px"/>
			<h2 class="text-white mt-2">등록금 관리</h2>
		</div>
		<div class="icon-text">
			<div class="cta">
				<i class="fa fa-arrow-right" aria-hidden="true"></i>
			</div>
		</div>
	</div>
</div>
<div class="col-2 my-5">
	<div class="each-icon container Abox-3 w-85 py-5 go-page" data-href="${cPath}/admin/lectureList.do">
		<div class="icon-wrap">
			<img src="${cPath}/images/admin/adminSelectPage/online-class.png" width="100px" height="100px"/>
			<h2 class="text-white mt-2">강의 관리</h2>
		</div>
		<div class="icon-text">
			<div class="cta">
				<i class="fa fa-arrow-right" aria-hidden="true"></i>
			</div>
		</div>
	</div>
</div>
<div class="col-2 my-5">
	<div class="each-icon container Abox-4 w-85 py-5 go-page" data-href="${cPath}/admin/scholarshipType.do">
		<div class="icon-wrap">
			<img src="${cPath}/images/admin/adminSelectPage/money.png" width="100px" height="100px"/>
			<h2 class="text-white mt-2">장학금 관리</h2>
		</div>
		<div class="icon-text">
			<div class="cta">
				<i class="fa fa-arrow-right" aria-hidden="true"></i>
			</div>
		</div>
	</div>
</div>
<div class="col-2 my-5">
	<div class="each-icon container Abox-5 w-85 py-5 go-page" data-href="${cPath}/admin/calendarView.do">
		<div class="icon-wrap">
			<img src="${cPath}/images/admin/calender.png" width="100px" height="100px"/>
			<h2 class="text-white mt-2">학사 일정 관리</h2>
		</div>
		<div class="icon-text">
			<div class="cta">
				<i class="fa fa-arrow-right" aria-hidden="true"></i>
			</div>
		</div>
	</div>
</div>
<div class="col-2 my-5">
	<div class="each-icon container Abox-6 w-85 py-5 go-page" data-href="${cPath}/cyber/main.do">
		<div class="icon-wrap">
			<img src="${cPath}/images/admin/adminSelectPage/online-class.png" width="100px" height="100px"/>
			<h2 class="text-white mt-2">사이버 캠퍼스</h2>
		</div>
		<div class="icon-text">
			<div class="cta">
				<i class="fa fa-arrow-right" aria-hidden="true"></i>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(".each-icon").on("click", function(){
	let goPage = $(this).data("href");
	location.href=goPage;
});

$(function(){
	$("#mainBtn").remove();
});
</script>

