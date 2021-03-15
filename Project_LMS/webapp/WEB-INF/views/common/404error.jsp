<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>페이지를 찾을 수 없습니다.</title>
<!-- Custom CSS -->
<link href="${cPath}/resource/dist/css/style.min.css" rel="stylesheet">
<script src="${cPath}/resource/assets/libs/jquery/dist/jquery.min.js"></script>
<style type="text/css">
#logo1 {
	width: 40px;
	height: auto;
}

#logo2 {
	width: 200px;
	height: auto;
}

.back {
	background: #f9fbfd !important;
}
</style>
<script type="text/javascript">
	$(function(){
		$("#returnBtn").on("click", function(){
			window.history.back();
		});
	});		
</script>
</head>
<body>
	<!-- main-wrapper -->
	<div class="main-wrapper">
		<div class="auth-wrapper d-flex no-block justify-content-center align-items-center position-relative" style="background:url(${cPath}/resource/assets/images/big/auth-bg.jpg) no-repeat center center;">
			<div class="auth-box row">
				<div class="col-12 back p-5">
					<div class="row my-2 d-flex justify-content-center">
						<div id="logoArea" class="text-center">
							<img id="logo1" src="${cPath}/images/logo/smartLMS_logo1.png" alt="wrapkit"> <img id="logo2" src="${cPath}/images/logo/smartLMS_logo2.png" alt="wrapkit">
						</div>
					</div>
					<div class="row my-2">
						<div class="col-12 text-center">
							<h1 class="text-danger">404 ERROR</h1>
						</div>
					</div>
					<div class="row my-2">
						<div class="col-12 text-center">
							<h5>페이지를 찾을 수 없습니다.</h5>
						</div>
					</div>
					<div class="row my-2">
						<div class="col-12 d-flex justify-content-center">
							<button id="returnBtn" type="button" class="btn waves-effect waves-light btn-light px-4 py-2 mx-2">뒤로가기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>