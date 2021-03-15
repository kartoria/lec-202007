<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16" href="${cPath}/resource/assets/images/favicon.png">

<!-- calendar -->
<link rel="stylesheet" href="${cPath}/css/calendar.css">
<link href='${cPath}/js/fullcalendar-5.5.1/lib/main.css' rel='stylesheet' />
<%-- <link href="${cPath}/resource/assets/libs/fullcalendar/dist/fullcalendar.min.css" rel="stylesheet"> --%>
<link href="${cPath}/resource/assets/extra-libs/c3/c3.min.css" rel="stylesheet">
<link href="${cPath}/resource/assets/libs/chartist/dist/chartist.min.css" rel="stylesheet">
<link href="${cPath}/resource/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.css" rel="stylesheet" />
<%-- <link href="${cPath}/resource/assets/libs/fullcalendar/dist/fullcalendar.min.css" rel="stylesheet"/> --%>
<link href="${cPath}/resource/bootModal/dist/bootstrap4-modal-fullscreen.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<link href="${cPath}/resource/dist/css/style.min.css" rel="stylesheet">
<link rel="stylesheet" href="${cPath}/js/noty-3.1.4/noty.css">

<!-- script -->
<script src="${cPath}/resource/assets/libs/jquery/dist/jquery.min.js"></script>
<script src="${cPath}/js/jquery.form.min.js"></script>
<script src="${cPath}/resource/assets/extra-libs/taskboard/js/jquery.ui.touch-punch-improved.js"></script>
<script src="${cPath}/resource/assets/extra-libs/taskboard/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${cPath}/js/jquery-validation-1.19.2/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cPath}/js/jquery-validation-1.19.2/additional-methods.min.js"></script>
<script src="${cPath}/resource/assets/libs/popper.js/dist/umd/popper.min.js"></script>
<script src="${cPath}/resource/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>
<script src="//mozilla.github.io/pdf.js/build/pdf.js"></script>
<script type="text/javascript" src="${cPath}/js/noty-3.1.4/noty.min.js"></script>

<!-- apps -->
<script src="${cPath}/resource/dist/js/app-style-switcher.js"></script>
<script src="${cPath}/resource/dist/js/feather.min.js"></script>
<script src="${cPath}/resource/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>

<!-- slimscrollbar scrollbar JavaScript -->
<script src="${cPath}/resource/assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
<script src="${cPath}/resource/assets/extra-libs/sparkline/sparkline.js"></script>

<!--Custom JavaScript -->
<script src="${cPath}/resource/dist/js/custom.min.js"></script>

<script type="text/javascript" src="${cPath}/js/viewjs/message/message.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

<!-- datepicker -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<style type="text/css">
 * {
 	 font-family: 'Nanum Gothic', sans-serif;
     font-size: 17px;
     font-weight: bold;
 }
 
 input {
 	 font-family: 'Nanum Gothic', sans-serif;
     font-weight: bold;
 }
</style>

<!-- ========= CK EDITOR ============ -->
<script type="text/javascript" src="${cPath}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	$.getContextPath = function(){
		return "${cPath}";
	}
	
	const language ="${pageContext.response.locale.language}";
	if(language != "en"){
		$.getScript($.getContextPath()+`/js/jquery-validation-1.19.2/localization/messages_\${language}.min.js`);
	}
</script>


<c:if test="${not empty message}">
	<script type="text/javascript">
	$(function(){
		new Noty({
			 text:'${message.text }', 
			 layout: '${message.layout }',
			 type: '${message.type }',
			 timeout: ${message.timeout },
			 progressBar: true
		}).show();
	});
	</script>
	<c:remove var="message" scope="session" />
</c:if>

<c:if test="${not empty msg}">
	<script type="text/javascript">
	$(function(){
		new Noty({
			 text:'${msg.text}', 
			 layout: '${msg.layout}',
			 type: '${msg.type}',
			 timeout: ${msg.timeout},
			 progressBar: true
		}).show();
	});
	</script>
	<c:remove var="msg" scope="session"/>
</c:if>

<c:if test="${not empty pageLink}">
	<script type="text/javascript">
	$(function() {
		   /*
		    * 메뉴 상태유지 210306
		    * @author  조예슬
		    */
		   let pageLink = window.location.protocol+"//"+window.location.host+$.getContextPath()+'${pageLink}';
		  	console.log(pageLink);
		   $('a').each(function() {
		    	  let href = this.href; // http://localhost/admin/studentView.do
		         if(href === pageLink) {// url과 a의 href가 정확히 일치할 때만 메뉴색가 진한 검은색
		        	 $(this).addClass('active text-dark');
		         }
		   });
		});
	</script>
	<c:remove var="pageLink" scope="session"/>
</c:if>

<script type="text/javascript">
$(function(){
	$("<script>").attr("src", "${cPath}/resource/assets/extra-libs/c3/d3.min.js").appendTo("head");
	$("<script>").attr("src", "${cPath}/resource/assets/extra-libs/c3/c3.min.js").appendTo("head");
	$("<script>").attr("src", "${cPath}/resource/assets/extra-libs/prism/prism.js").appendTo("head");
	$("<script>").attr("src", "${cPath}/resource/assets/libs/chartist/dist/chartist.min.js").appendTo("head");
	$("<script>").attr("src", "${cPath}/resource/assets/libs/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.min.js").appendTo("head");
	$("<script>").attr("src", "${cPath}/resource/assets/extra-libs/jvector/jquery-jvectormap-2.0.2.min.js").appendTo("head");
	$("<script>").attr("src", "${cPath}/resource/assets/extra-libs/jvector/jquery-jvectormap-world-mill-en.js").appendTo("head");
	$("<script>").attr("src", "${cPath}/resource/dist/js/pages/dashboards/dashboard1.min.js").appendTo("head");
	$("<script>").attr("src", "${cPath}/js/fullcalendar-5.5.1/lib/main.js").appendTo("head");
	
// 	// push 알림용 websocket
// 	const PROTOCOL = location.protocol=="http:"?"ws:":"wss";
// 	const DOMAIN = location.hostname;
// 	const CONTEXTPATH = $.getContextPath();
// 	const PORT = location.port?":"+location.port : "";
// 	var pushWs = new WebSocket(PROTOCOL+"//"+DOMAIN+PORT+CONTEXTPATH+"/pushMessage");
// 	var consolePrintHandler=function(event){
// 		console.log(event);
// 	}
// 	pushWs.onmessage=function(event){
// 		let payload = event.data;
// 		let messageVO = JSON.parse(payload);
// // 		alert(messageVO.message);
// 		if(messageVO.message){
// 			getAttendanceList.submit();
// 		};
// 	}
// 	pushWs.onerror=consolePrintHandler;
	
	//leftMenu와 로고클릭했을때 이동하는 경로에 경로변수가있는걸 el태그로 바꿔줌
	$(".sidebar-link").each(function(idx, item){
		let menuPath = $(this).attr("href");
// 		console.log("변경 전 : " + menuPath);
		menuPath = menuPath.replace("@{lecCode}", "${lecCode}");
// 		console.log("변경 후 : " + menuPath);
		$(this).attr("href", menuPath);
	});
	
	$(".required").each(function(){
		$("<span class='text-danger ml-2'>*</span>").appendTo($(this));
	});
	
	$("#lectureSelectInLeftMenu").on("change", function(){
		let lecCode = $(this).val();
		console.log(lecCode);
		let lectureSelectForm = $("#lectureSelectForm");
		location.href="${cPath}/myclass/"+lecCode+"/dashList.do";
	});
});
</script>
<script src="${cPath}/resource/dist/js/sidebarmenu.js"></script>

<c:if test="${not empty hideHomeBtn}">
	<script type="text/javascript">
		$(function(){
			$("#mainBtn").remove();
		});	
	</script>
</c:if>

<!--  =========================================== -->
