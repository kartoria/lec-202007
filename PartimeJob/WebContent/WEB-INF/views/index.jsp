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

















