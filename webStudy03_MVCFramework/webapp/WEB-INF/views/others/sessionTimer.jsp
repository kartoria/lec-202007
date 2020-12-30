<%@page import="java.util.Date"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="timeArea">
</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/sessionTimer.js"></script>
<script type="text/javascript">
	$("#timerArea").sessionTimer({
		timeout : <%=session.getMaxInactiveInterval() %>,
		sessionURL : "${pageContext.request.contextPath }/02/getMessage.do"
	});
</script>