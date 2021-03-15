<%--
* [[개정이력(Modification Information)]]
* 2021. 2. 2.      PC-17      수정내용
* ----------  ---------  -----------------
* 2021. 2. 2.      PC-17      최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="${cPath}/resource/assets/images/favicon.png">
<!-- Custom CSS -->
<link
	href="${cPath}/resource/assets/libs/fullcalendar/dist/fullcalendar.min.css"
	rel="stylesheet">
<link
	href="${cPath}/resource/assets/extra-libs/c3/c3.min.css"
	rel="stylesheet">
<link
	href="${cPath}/resource/assets/libs/chartist/dist/chartist.min.css"
	rel="stylesheet">
<link
	href="${cPath}/resource/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css"
	rel="stylesheet" />
<!-- Custom CSS -->
<link
	href="${cPath}/resource/dist/css/style.min.css"
	rel="stylesheet">
<link
	href="${cPath}/resource/bootModal/dist/bootstrap4-modal-fullscreen.min.css"
	rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- This Page CSS -->
<link rel="stylesheet" type="text/css"
	href="${cPath}/resource/assets/extra-libs/prism/prism.css">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->

<title>학적 조회</title>
<style type="text/css">
#contentArea{
	float: right;
	width: 80%;
	margin-right: 5%;
}
#leftTitle {
	float: right;
}
</style>
</head>
<body>

<div id="main-wrapper" data-theme="light" data-layout="vertical"
	data-navbarbg="skin6" data-sidebartype="full"
	data-sidebar-position="fixed" data-header-position="fixed"
	data-boxed-layout="full">
	<aside class="left-sidebar" data-sidebarbg="skin6">
		<!-- Sidebar scroll-->
		<div class="scroll-sidebar" data-sidebarbg="skin6">
			<!-- Sidebar navigation-->
			<nav class="sidebar-nav">
				<ul id="sidebarnav">
					<li class="nav-small-cap"><span class="hide-menu">Smart
							LMS</span></li>
					<li class="sidebar-item"><a class="sidebar-link has-arrow"
						href="javascript:void(0)" aria-expanded="false"><i
							data-feather="star" class="feather-icon"></i><span
							class="hide-menu">수강신청</span></a>
						<ul aria-expanded="false"
							class="collapse  first-level base-level-line">
							<li class="sidebar-item">
								<a href="${cPath}/enrolment/list.do" class="sidebar-link">
									<span class="hide-menu">수강신청 </span>
								</a>
							</li>
							<li class="sidebar-item">
								<a href="${cPath}/enrolment/profileList.do" class="sidebar-link">
									<span class="hide-menu">학적조회 </span>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</nav>
			<!-- End Sidebar navigation -->
		</div>
		<!-- End Sidebar scroll-->
	</aside>
	<div id="contentArea">
		<br><br>
		<div id="leftTitle">
			<span id="timeZone"></span> <input type="button" id="timeBtn"
				class="btn btn-primary" value="연장"> <a href="#none"
				onclick="window.open('${cPath}/enrolment/schedulePdf.do','new','scrollbars=yes,resizable=no width=800px height=1000px, left=0,top=0');return false">
				<input type="button" class="btn btn-primary" value="시간표">
			</a> <a href="${cPath}/">
				<button class="btn btn-primary">사이트맵</button>
			</a>
		</div>
		<h2><strong> 수강신청 - 학적조회</strong></h2>
		<hr>
		<br>
		<jsp:include page="${cPath}/WEB-INF/views/profile/stuProfile.jsp"></jsp:include>
	</div>
</div>
</body>
<script type="text/javascript">
$(function() {
	$("#updateBtn").hide();
	//세션 타이머 ==========================================================
	var intervals = window.localStorage.getItem('intervals');
	var intervalsMin = window.localStorage.getItem('intervalsMin');
	if(intervals==null ||intervalsMin==null){
		intervals =59;
		intervalsMin=29;
	}
	function timer() {
		setTimeout(timer, 1000);
		intervals--;
		window.localStorage.setItem('intervals', intervals);
		window.localStorage.setItem('intervalsMin', intervalsMin);
		$("#timeZone").text(intervalsMin + ":" + intervals);
		if (intervals == 0) {
			if (intervalsMin != 0) {
				intervalsMin--;
				intervals = 59;
			} else {
				window.localStorage.removeItem('intervals');
				window.localStorage.removeItem('intervalsMin');
				let url = "${cPath}/logout.do";
				$(location).attr('href', url);
			}
		}
	}
	setTimeout(timer, 10);
	
	//타이머 연장 ==========================================================
	$("#timeBtn").on("click", function() {
		intervals = 59;
		intervalsMin = 29;
		$("#timeZone").text(intervalsMin + ":" + intervals);
	});
	
	});

</script>
	<script src="${cPath}/resource/assets/libs/popper.js/dist/umd/popper.min.js"></script>
	<script src="${cPath}/resource/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- apps -->
	<script src="${cPath}/resource/dist/js/app-style-switcher.js"></script>
	<script src="${cPath}/resource/dist/js/feather.min.js"></script>
	<script src="${cPath}/resource/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
	<script src="${cPath}/resource/dist/js/sidebarmenu.js"></script>
	<!--Custom JavaScript -->
	<script src="${cPath}/resource/dist/js/custom.min.js"></script>
	<!--This page JavaScript -->
	<script src="${cPath}/resource/assets/extra-libs/c3/d3.min.js"></script>
	<script src="${cPath}/resource/assets/extra-libs/c3/c3.min.js"></script>
	<script src="${cPath}/resource/assets/libs/moment/min/moment.min.js"></script>
	<script src="${cPath}/resource/assets/libs/fullcalendar/dist/fullcalendar.min.js"></script>
	<script src="${cPath}/resource/dist/js/pages/calendar/cal-init.js"></script>
	<!-- This Page JS -->
	<script src="${cPath}/resource/assets/extra-libs/prism/prism.js"></script>
	<script src="${cPath}/resource/assets/libs/chartist/dist/chartist.min.js"></script>
	<script src="${cPath}/resource/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js"></script>
	<script src="${cPath}/resource/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js"></script>
	<script src="${cPath}/resource/assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js"></script>
	<script src="${cPath}/resource/dist/js/pages/dashboards/dashboard1.min.js"></script>
</html>