<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<style type="text/css">
			body{
				background-color: aqua;
			}
		</style>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
		<script type="text/javascript">
// 			function changeCallback(event){
// 				console.log(event.target.value);
// 				location.href="/webStudy01/imageView.do?image="+ event.target.value;
// 			}
			
			$(function(){
				$("select").on("change", function(event){
					let imageName =  $(this).val();
//	 				<img src="../imageView.do?image="/>
					$("#imageArea").html(
						$("<img>").attr("src", "<%= request.getContextPath()%>/imageView.do?image=" + imageName)
					);
				});
			});
		</script>
	</head>
	<% String[] imageFiles = (String[]) request.getAttribute("imageFiles"); %>
	<body>
		<h4>${title}</h4>
		<h4>${today}</h4>
		<select>
		<%	for(String image: imageFiles){ %>
					<option><%=image %> </option>
		<% 	} %>
		</select>
		<div id="imageArea">
			
		</div>
	</body>
</html>
