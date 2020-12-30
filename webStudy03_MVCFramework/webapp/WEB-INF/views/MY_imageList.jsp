<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String selected = null;
	Cookie[] cookies = request.getCookies();
	Cookie imageSelected = null;
	if(cookies!=null){
		for(Cookie tmp : cookies){
			if("imageSelected".equals(tmp.getName())){
				imageSelected = tmp;
				selected = URLDecoder.decode(tmp.getValue(), "UTF-8");
				break;
			}
		}
	}
%>
<html>
	<head>
		<style type="text/css">
			body{
				background-color: aqua;
			}
		</style>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script type="text/javascript">
// 			function changeCallback(event){
// 				console.log(event.target.value);
// 				location.href="/webStudy01/imageView.do?image="+ event.target.value;
// 			}
			
			$(function(){
				let imageName = "";
				<%if(imageSelected != null){%>
					$("select").val("<%=imageSelected.getValue()%>").prop("selected", true);
					imageName =  $("select").val();
					$("#imageArea").html(
						$("<img>").attr("src", "<%= request.getContextPath() %>/imageView.do?image=" + imageName)
					);
				<%}%>
				
				
				$("select").on("change", function(event){
					imageName =  $(this).val();
//	 				<img src="../imageView.do?image="/>
					$("#imageArea").html(
						$("<img>").attr("src", "<%= request.getContextPath() %>/imageView.do?image=" + imageName)
					);
					$.ajax({
						url : "${pageContext.request.contextPath }/myimageList.do",
						method : "post",
						data : {"imageName" : imageName},
						dataType : "json"
					});
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
