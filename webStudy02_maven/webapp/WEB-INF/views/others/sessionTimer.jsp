<%@page import="java.util.Date"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="timeArea">
</div>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/sessionTimer.js"></script>
<script type="text/javascript">
	$("#timerArea").sessionTimer({
		timeout : <%=session.getMaxInactiveInterval() %>,
		sessionURL : "<%=request.getContextPath() %>/02/getMessage.do"
	});
</script>