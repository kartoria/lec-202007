<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계산기</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" 
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
	
<style type="text/css">
	input[type='number']{
		width:200px;
		height:30px;
	}
	select{
		width:50px;
		height:36px;
	}
	div{
		margin-top:25px;
		border: 1px solid #ddd;
		width:476px;
		height:50px;
	}
	button{
		width:50px;
		height:35px;
	}
</style>
</head>
<body>
	<h3>사칙연산</h3>
	<form id="calFm">
		<input type="number" name="num1">
		<select name="sign">
			<option value="plus" selected="selected">+</option>
			<option value="minus">-</option>
			<option value="multiple">*</option>
			<option value="division">/</option>
		</select>
		<input type="number" name="num2">
		<button type="button">계산</button>
	</form>
	<div>
		<p>계산 결과 : <span></span></p>
	</div>
	
<script type="text/javascript">
	$("button").on("click", function(){
		console.log("클릭");
		$.ajax({
			  url : "<%=request.getContextPath() %>/Calculator"
			, method : "post"
			, data : $("#calFm").serialize()
			, dataType: "text"
			, success:function(data){
				  $("span").html(data);
			  }
			, error:function(xhr){
				console.log(xhr.status);
			  }
		});
	});
</script>
</body>
</html>