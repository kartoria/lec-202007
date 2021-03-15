<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${cPath}/resource/assets/images/favicon.png">
    <title>SmartLMS : 로그인</title>
    <!-- Custom CSS -->
    <link href="${cPath}/resource/dist/css/style.min.css" rel="stylesheet">
    <script src="${cPath}/resource/assets/libs/jquery/dist/jquery.min.js"></script>
	<style type="text/css">
		
	#logo1{
	   width: 40px;
	   height: auto;
	}
	#logo2{
	   width: 200px;
	   height: auto;
	}
	#campusImg{
		background-image: url("${cPath}/images/mainImages/LoginMainImg1.jpg");
	}
	</style>
	<script type="text/javascript">
		<c:if test="${not empty msg}">
		$(function(){
			alert("${msg}");
		});
		<c:remove var="msg" scope="session" />
		</c:if>
	</script>
</head>
<body>
	<!-- main-wrapper -->
    <div class="main-wrapper">
        <div class="auth-wrapper d-flex no-block justify-content-center align-items-center position-relative"
            style="background:url(${cPath}/resource/assets/images/big/auth-bg.jpg) no-repeat center center;">
            <div class="auth-box row">
                <div id="campusImg" class="col-lg-7 col-md-5 modal-bg-img">
                </div>
                <div class="col-lg-5 col-md-7 bg-white">
                    <div class="p-3">
                        <div id="logoArea" class="text-center">
                            <img id="logo1" src="${cPath}/images/logo/smartLMS_logo1.png" alt="wrapkit">
                            <img id="logo2" src="${cPath}/images/logo/smartLMS_logo2.png" alt="wrapkit">
                        </div>
                        
                        <form class="mt-4" action="${cPath}/login/loginProcess.do" method="post">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="text-dark" for="memId">학번/사번</label>
                                        <input class="form-control" value="${cookie.idCookie.value }" id="memId" name="memId" type="text" placeholder="학번 또는 사번을 입력해주세요">
                                    </div>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label class="text-dark" for="memPass">비밀번호</label>
                                        <input class="form-control" id="memPass" name="memPass" type="password" placeholder="비밀번호를 입력해주세요">
                                    </div>
                                </div>
                                <div class="col-lg-12">
	                                <div class="form-check form-check-inline">
	                                    <input type="checkbox" value="saveId" name="saveId" id="saveId" ${not empty cookie.idCookie ?"checked":"" } class="form-check-input"> 
	                                    <label class="text-dark form-check-label" for="saveId">아이디 자동 저장</label>
	                                </div>
	                                <div class="form-check form-check-inline">
	  									<input type="checkbox" name="rememberMe" id="rememberMe" class="form-check-input"> 
	                                    <label class="text-dark form-check-label" for="rememberMe">자동 로그인</label>
									</div>
								</div>
                                <div class="col-lg-12 mt-2 text-center">
	   								<p class="text-danger">${sessionScope["loginFailMsg"]}</p>
	   								<c:remove var="loginFailMsg" scope="session"/>
    								<p class="text-danger">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
    								<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
                                </div>
                                <div class="col-lg-12 text-center">
                                    <button type="submit" class="btn btn-block btn-dark">로그인</button>
                                </div>
                                <div class="col-lg-12 text-center mt-3">
                                   	<span class="text-dark">학번/사번</span> 또는 <span class="text-dark">비밀번호</span>를 <br> 잃어버리셨나요?
                                   	<a href="${cPath}/login/inquiry.do" onclick="window.open(this.href, '팝업창','width=520,height=620'); return false;" class="text-danger">찾기</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- main-wrapper END-->
</body>

</html>