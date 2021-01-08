<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>    
<script type="text/javascript">
	function clickHandler(event){
		event.preventDefault();
		let href = event.target.dataset["href"];
		let logoutForm = document.logoutForm;
		logoutForm.action = href;
		logoutForm.submit();
		return false;
	}
</script>

<h4>Welcome page</h4> 
<c:choose>
	<c:when test="${not empty sessionScope.authMember }">
		<form name="logoutForm" method="post"></form>
		<a href="${pageContext.request.contextPath }/mypage.do">${authMember.mem_name }</a>님 
		<a href="#" onclick="clickHandler(event);" data-href="${pageContext.request.contextPath }/login/logout.do">로그아웃</a>
	</c:when>
	<c:otherwise>
		<a href="${pageContext.request.contextPath }/login/loginForm.do">로그인</a>
		<a href="${pageContext.request.contextPath }/member/registMember.do">회원가입</a>
	</c:otherwise>
</c:choose>

















