<%@page import="kr.or.ddit.enumpkg.ServiceKind"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="menuForm" data-action="<%=request.getContextPath() %>/module.do">
	<input type="hidden" name="service">
	
</form>    
<ul class="menuUl">
<%
	ServiceKind[] kinds = ServiceKind.values();
	for(ServiceKind service : kinds){
%>	
	<li>
		<a data-service="<%=service.name() %>"><%=service.getMenu().getMenuText() %></a>
	</li>
<%
	}
%>
</ul>
<script type="text/javascript">
	let menuForm = $("#menuForm");
 	$(".menuUl").on("click","a",function(event){
 		event.preventDefault();
 		menuForm.find("[name='service']").val($(this).data("service"));
 		menuForm.submit();
 		return false;
 	});
</script>