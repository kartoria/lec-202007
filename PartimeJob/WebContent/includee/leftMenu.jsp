<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="menuForm" action="${pageContext.request.contextPath }/module.do">
	<input type="hidden" name="service" />
</form>    
 <div class="position-sticky pt-3">
   <ul class="nav flex-column menuUl">
     <li class="nav-item">
       <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath }">
         <span data-feather="home">첫 페이지</span>
       </a>
     </li>
   </ul>
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














