<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath }/js/bootstrap/css/bootstrap.min.css">    
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/noty-3.1.4/noty.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-validation-1.19.2/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-validation-1.19.2/additional-methods.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-validation-1.19.2/localization/messages_ko.min.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/noty-3.1.4/noty.min.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery.form.min.js"></script>
<script type="text/javascript">
	$.getContextPath = function(){
		return "${cPath}";
	}
	
	const language = "${pageContext.response.locale.language}";
	if(language != "en")
		$.getScript($.getContextPath()+`/js/jquery-validation-1.19.2/localization/messages_\${language}.min.js`);
</script>

<style type="text/css">
	.error{
		color: red;
	}
	.invalid{
		border-color: red;
	}
	.thumbnail{
		width: 100px;
		height: 100px;
	}
	.delAtt{
		width: 10px;
		height: 10px;
	}
	.flag{
		width: 30px;
		height: 30px;
	}
</style>