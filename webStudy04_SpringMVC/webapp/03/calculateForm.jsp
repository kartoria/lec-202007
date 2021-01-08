<%@page import="kr.or.ddit.enumpkg.OperateType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		let resultArea = $("#resultArea");
		let now = $("#now");
		$(document).ajaxError(function(xhr){
			console.log(xhr);
		});
		$("#calculateForm").on("submit", function(event){
			event.preventDefault();
			console.log(this);
			console.log($(this));
			let url = this.action? this.action : "" ;
			let data = $(this).serialize();
			let method = this.method ? this.method: "get";
			let dataType = "json";
			let success = function(resp){
// 				console.log(resp);
				resultArea.html(resp.data);
				now.html(resp.now);
			}
			console.log(data);
			let options = {
				url : url
				, data : data
				, method : method
				, dataType : dataType
				, success : success
			}
			$.ajax(options);
			return false;
		});
	});
</script>
<form id="calculateForm" action="${pageContext.request.contextPath }/03/calculate.do" method="post" ">
	<input type="number" name="leftOp" required />
	<select name="operator" required>
		<option value="">연산자</option>
		<%
			for( OperateType tmp : OperateType.values()){
				%>
				<option value="<%=tmp.name() %>"><%=tmp.getSign() %></option>
				<%
			}
		%>
	</select>
	<input type="number" name="rightOp" required />
	<button type="submit">=</button>
	<span id="now"></span>
	<span id="resultArea"></span>
</form>
















