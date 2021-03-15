<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<style type="text/css">
 #logo1 { 
 	width: 40px; 
 	height: auto; 
 } 
 #logo2 { 
 	width: 180px; 
 	height: auto; 
 }
 .topbar{ 
  	background-color:${topbarColor} !important
 } 
</style>
<security:authorize access="isAuthenticated()">
	<security:authentication property="principal" var="principal" />
	<c:set var="authMember" value="${principal.realMember}" />
</security:authorize>

<header class="topbar" data-navbarbg="skin6">
	<nav class="navbar top-navbar navbar-expand-md">
		<div class="navbar-header px-3" data-logobg="skin6">
			<!-- This is for the sidebar toggle which is visible on mobile only -->
			<a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)">
				<i class="ti-menu ti-close"></i>
			</a>
			<a id="logo" href="${cPath}/${main}" class="text-decoration-none"> 
				<b class="logo-icon"><img id="logo1" src="${cPath}/images/logo/smartLMS_logo1.png" alt="homepage" class="dark-logo" /></b>
				<b class="logo-text"><img id="logo2" src="${cPath}/images/logo/smartLMS_logo2.png" alt="homepage" class="dark-logo" /></b>
			</a>
			<a class="topbartoggler d-block d-md-none waves-effect waves-light" href="javascript:void(0)" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><i class="ti-more"></i></a>
		</div>
		
		<div class="navbar-collapse collapse" id="navbarSupportedContent">
			<ul class="navbar-nav float-right ml-auto mr-3 pr-1">
				<li id="mainBtn" class="nav-item d-flex align-items-center mx-3">
					<a href="${cPath}/" class="btn btn-info px-4">HOME</a>
				</li>
				<!-- Notification -->
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle pl-md-3 position-relative" href="javascript:void(0)" id="bell" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
						<span><i data-feather="bell" class="svg-icon"></i></span> 
						<span id="msgCount" class="badge badge-primary notify-no rounded-circle"></span>
					</a>
					<div class="dropdown-menu dropdown-menu-left mailbox animated bounceInDown">
						<ul class="list-style-none">
							<li>
								<div id="messageArea" class="message-center notifications position-relative">
									<!-- Message -->
								</div>
							</li>
						</ul>
					</div>
				</li>
			</ul>
			<ul class="navbar-nav float-right">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="javascript:void(0)" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <img src="${imagePath}" alt="user" class="rounded-circle" width="40"> <span class="ml-2 d-none d-lg-inline-block text-dark">${authMember.memName} ${sessionScope.memType}로 접속중입니다.<i data-feather="chevron-down" class="svg-icon"></i></span></a>
					<div class="dropdown-menu dropdown-menu-right user-dd animated flipInY">
						<c:if test="${authMember.memType ne 'ROLE_ADMIN' }">
							<a class="dropdown-item" href="${cPath}/profile/profileList.do"><i data-feather="user" class="svg-icon mr-2 ml-1"></i>내 정보</a>
						</c:if>
						<form action="${cPath}/logout.do" method="post">
							<button type="submit" class="dropdown-item"><i data-feather="power" class="svg-icon mr-2 ml-1"></i>로그아웃</button>
						</form>
					</div>
				</li>
			</ul>
		</div>
	</nav>
</header>
