<%@page import="kr.or.ddit.enumpkg.CalculatorSign"%>
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
	<form method="post" action="<%=request.getContextPath() %>/Calculator.do">
		<input type="number" name="num1" required>
		<select name="sign">
			<% for(CalculatorSign tmp : CalculatorSign.values()){ %>
				<option value="<%=tmp.name()%>"><%=tmp.getCalSign() %></option>
			<% } %>
		</select>
		<input type="number" name="num2" required>
		<input type="submit" value="계산">
	</form>
	<div>
		<p>계산 결과 : <span></span></p>
	</div>
	
<script type="text/javascript">
	$(function(){
		let span = $("span");
		$(document).ajaxError(function(xhr){
			console.log(xhr);
		});
		$("form").on("submit", function(event){
			event.preventDefault();
			let url = this.action ? this.action : "" ;
			let data = $(this).serialize();
			let method = this.method ? this.method : "get";
			let dataType = "json";
			let success = function(resp){
				console.log(resp);
				span.html(resp.result);
			}
			let options = {
					 		url : url
							, method : method
							, data : data
							, dataType: dataType
							, success: success
						};
			$.ajax(options);
			return false;
		});
	});
</script>
</body>
</html>