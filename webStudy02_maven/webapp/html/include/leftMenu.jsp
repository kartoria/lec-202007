<%@page import="org.apache.commons.lang3.StringUtils"%>
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
	boolean model2 = StringUtils.isNotBlank(service.getMenu().getMenuURI());
%>	
	<li>
	<% if(model2){ %>
		<a href="<%=request.getContextPath() %><%=service.getMenu().getMenuURI() %>"><%=service.getMenu().getMenuText() %></a>
	<%}else{%>
		<a class="model1" data-service="<%=service.name() %>"><%=service.getMenu().getMenuText() %></a>
	<%}%>
	</li>
<%
	}
%>
</ul>
<script type="text/javascript">
	let menuForm = $("#menuForm");
 	$(".menuUl").on("click","a.model1",function(event){
 		event.preventDefault();
 		menuForm.find("[name='service']").val($(this).data("service"));
 		menuForm.submit();
 		return false;
 	});
</script>