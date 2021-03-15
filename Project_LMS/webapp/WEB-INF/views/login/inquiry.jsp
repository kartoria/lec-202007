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
<title>학번/사번 및 비밀번호 찾기</title>
<!-- Custom CSS -->
<link href="${cPath}/resource/dist/css/style.min.css" rel="stylesheet">
<style type="text/css">
	.inquiryFormDiv{
		border: 1px solid gray;
		margin:10px;
		padding : 10px;
	}
</style>
<!-- 검색했는데 결과가없을때 alert창 띄우는용도 -->
<c:if test="${not empty msg}">
	<script type="text/javascript">
		alert("${msg}");
		<c:if test="${not empty location}">
			window.href = "${location}";	
		</c:if>
	</script>
</c:if>
</head>
<body>
	<!-- main-wrapper -->
	<div class="main-wrapper">
		<div class="auth-wrapper d-flex no-block justify-content-center align-items-center position-relative">
			<div class="col-lg-5 col-md-7 bg-white">
				<!-- inquiryFormDiv -->
				<div class="inquiryFormDiv">
					<h4 class="mt-3 text-center">학번 / 사번 찾기</h4>
					<form class="mt-4" action="${cPath}/login/inquiryId.do" method="post">
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label class="text-dark" for="memName">이름</label> 
									<input class="form-control" id="memName" name="memName" type="text" value="${memName }" placeholder="이름을 입력해주세요">
								</div>
							</div>
							<div class="col-lg-12">
								<div class="form-group">
									<label class="text-dark" for="memReg1">주민등록번호 앞자리</label> 
									<input class="form-control" id="memReg1" name="memReg1" type="text" value="${memReg1 }" placeholder="주민등록번호 앞자리를 입력해주세요">
								</div>
							</div>
							<div class="col-lg-12 text-center">
								<button type="submit" class="btn btn-block btn-primary">학번 / 사번 찾기</button>
							</div>
						</div>
					</form>
				</div>
				<!-- inquiryFormDiv END -->
				
				<!-- inquiryFormDiv -->
				<div class="inquiryFormDiv">
					<h4 class="mt-3 text-center">비밀번호 찾기</h4>
					<form class="mt-4" action="${cPath}/login/inquiryPass.do" method="post">
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label class="text-dark" for="memName">학번</label> 
									<input class="form-control" id="memName" name="memName" type="text" placeholder="학번을 입력해주세요">
								</div>
							</div>
							<div class="col-lg-12">
								<div class="form-group">
									<label class="text-dark" for="memMail">이메일</label> 
									<input class="form-control" id="memMail" name="memMail" type="text" placeholder="이메일을 입력해주세요">
								</div>
							</div>
							<div class="col-lg-12 text-center">
								<button type="submit" class="btn btn-block btn-primary">비밀번호 찾기</button>
							</div>
						</div>
					</form>
				</div>
				<!-- inquiryFormDiv END -->
			</div>
		</div>
	</div>
	<!-- main-wrapper END-->
	
	<script src="${cPath}/resource/assets/libs/jquery/dist/jquery.min.js "></script>
	<!-- Bootstrap tether Core JavaScript -->
	<script
		src="${cPath}/resource/assets/libs/popper.js/dist/umd/popper.min.js "></script>
	<script
		src="${cPath}/resource/assets/libs/bootstrap/dist/js/bootstrap.min.js "></script>

</body>

</html>