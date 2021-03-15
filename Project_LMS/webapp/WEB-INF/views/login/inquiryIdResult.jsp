<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>학번/사번 및 비밀번호 찾기</title>
<!-- Custom CSS -->
<link href="${cPath}/resource/dist/css/style.min.css" rel="stylesheet">
</head>

<body>
	<!-- main-wrapper -->
	<div class="main-wrapper">
		<div class="auth-wrapper d-flex no-block justify-content-center align-items-center position-relative">
			<div class="row">
				<div class="card">
                           <div class="card-body">
                               <h4 class="card-title">조회한 결과입니다.</h4>
                               <div class="table-responsive">
                                   <table class="table table-bordered table-striped mb-0">
                                       <thead>
                                           <tr>
                                               <th class="text-center">학번 / 사번</th>
                                               <th class="text-center">이름</th>
                                               <th class="text-center">소속</th>
                                           </tr>
                                       </thead>
                                       <tbody>
	                                       <c:forEach items="${memberList }" var="member">
	                                           <tr>
	                                               <td>${member.memId }</td>
	                                               <td>${member.memName }</td>
	                                               <td>${member.departmentVO.depName }</td>
	                                           </tr>
	                                       </c:forEach>
                                       </tbody>
                                       <tfoot>
                                       		<tr>
										       <td colspan="3"><button type="button" onclick="window.close()" class="btn btn-block btn-secondary">창 닫기</button></td>
                                       		</tr>
                                       </tfoot>
                                   </table>
                               </div> <!-- end table-responsive-->
                           </div> <!-- end card-body-->
                       </div> <!-- end card -->
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