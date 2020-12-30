<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.enumpkg.ServiceKind"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="menuForm" action="${pageContext.request.contextPath }/module.do">
	<input type="hidden" name="service" />
</form>    
 <div class="position-sticky pt-3">
   <ul class="nav flex-column menuUl">
     <li class="nav-item">
       <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath }">
         <span data-feather="home"></span>
         Dashboard
       </a>
     </li>
	<%
		ServiceKind[] kinds = ServiceKind.values();
		for(ServiceKind service : kinds){
			boolean model2 = StringUtils.isNotBlank(service.getMenu().getMenuURI());
			%>
			<li class="nav-item">
				<%
					if(model2){
						%>
						<a class="nav-link" href="${pageContext.request.contextPath }<%=service.getMenu().getMenuURI() %>"><%=service.getMenu().getMenuText() %></a>
						<%					
					}else{
						%>
						<a class="nav-link model1" href="#" data-service="<%=service.name() %>"><%=service.getMenu().getMenuText() %></a>
						<%					
					}
				%>
			</li>
			<%
		}
	%>
   </ul>

<!--     <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted"> -->
<!--       <span>Saved reports</span> -->
<!--       <a class="link-secondary" href="#" aria-label="Add a new report"> -->
<!--         <span data-feather="plus-circle"></span> -->
<!--       </a> -->
<!--     </h6> -->
<!--     <ul class="nav flex-column mb-2"> -->
<!--       <li class="nav-item"> -->
<!--         <a class="nav-link" href="#"> -->
<!--           <span data-feather="file-text"></span> -->
<!--           Current month -->
<!--         </a> -->
<!--       </li> -->
<!--       <li class="nav-item"> -->
<!--         <a class="nav-link" href="#"> -->
<!--           <span data-feather="file-text"></span> -->
<!--           Last quarter -->
<!--         </a> -->
<!--       </li> -->
<!--       <li class="nav-item"> -->
<!--         <a class="nav-link" href="#"> -->
<!--           <span data-feather="file-text"></span> -->
<!--           Social engagement -->
<!--         </a> -->
<!--       </li> -->
<!--       <li class="nav-item"> -->
<!--         <a class="nav-link" href="#"> -->
<!--           <span data-feather="file-text"></span> -->
<!--           Year-end sale -->
<!--         </a> -->
<!--       </li> -->
<!--     </ul> -->
 </div>
<script type="text/javascript">
	let menuForm = $("#menuForm");
	$(".menuUl").on("click", "a.model1", function(event){
		event.preventDefault();
		menuForm.find("[name='service']").val($(this).data("service"));
		menuForm.submit();
		return false;
	});
</script>