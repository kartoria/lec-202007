<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="timerArea">

</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/sessionTimer.js"></script>
<script type="text/javascript">
$(function(){
	$("#timerArea").sessionTimer({
		timeout:${pageContext.session.maxInactiveInterval }, 
		sessionURL:"${pageContext.request.contextPath }/02/getMessage.do"
	});
});
</script>
















