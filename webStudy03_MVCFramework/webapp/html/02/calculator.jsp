<%@page import="kr.or.ddit.enumpkg.CalculatorSign"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<h3>사칙연산</h3>
	<form id="calculateForm" method="post" action="${pageContext.request.contextPath }/Calculator.do">
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
		<p>계산 결과 : <span id="calResult"></span></p>
	</div>
	
<script type="text/javascript">
	$(function(){
		let span = $("#calResult");
		$(document).ajaxError(function(xhr){
			console.log(xhr);
		});
		$("#calculateForm").on("submit", function(event){
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
