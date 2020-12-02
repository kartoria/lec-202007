<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Refresh" content="5;url=https://www.naver.com"> -->
<title>05/refresh.jsp</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" 
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function init() {
// 		$("#stopBtn").on("click", function(){
// 			es.close();
// 		});
// 		let es = new EventSource("getServerTime_SSE.jsp");
// 		es.onopen=function(event){
// 			console.log(event);
// 		}
// 		es.addEventListener("test", function(event){
// 			console.log(event);
// 			timeArea.text(event.data);
// 		});
// 		$(es).on("test", function(event){
// 			console.log(event);
// 			timeArea.text(event.data);
// 		});
		es.onerror=function(event){
			if(event.target.readyState!=EventSource.CLOSED){
				es.close();
			}
			console.log(event);
		}
// 		setTimeout(() => {
// 			location.reload();
// 		}, 1000);
		let timeArea = $("#timeArea");
// 		setInterval(() => {
// 			fetch("getServerTime.jsp").then(function(response){
// 				if(response.ok){
// 					console.log(response);
// 					return response.text();
// 				}else{
// 					console.log(response.status);
// 				}
// 			}).then(function(text){
// 				timeArea.text(text);
// 			});
			$.ajax({
				url : "getServerTime.jsp",
				method : "get",
				dataType : "text",
			}).done(function(){
				timeArea.text(resp)
			});		
// 		}, 1000);
	}
</script>
</head>
<body onload="init();">
<h4>Refresh : 서버사이드의 갱신데이터 조회 방법</h4>
<button type="button" id="stopBtn">STOP</button>
<%--
	response.setIntHeader("Refresh", 1);
--%>
	<p>
		현재 서버의 시각 : <span id="timeArea"></span>
	</p>
</body>
</html>