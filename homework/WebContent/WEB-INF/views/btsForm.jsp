<%@page import="java.util.Map.Entry"%>
<%@page import="kr.or.ddit.vo.BtsVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Map<String, BtsVO> btsDB = (Map)application.getAttribute("btsDB"); 
	BtsVO selected = (BtsVO) session.getAttribute("selected");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>btsselect.do</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" 
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<style type="text/css">
	select{
		width:300px;	
		height:100px;
		text-align: center;
		font-size: 2.0em;
		font-weight: bold;
	}
</style>
</head>
<body>
<h1>방 탄 소 년 단 소 개 페 이 지 로 이 동</h1>
<form method="post">
	<select name="bts" required="required">
	<option value="">멤버선택</option>
	<%for(Entry<String,BtsVO> entry : btsDB.entrySet()){
		BtsVO btsVO = entry.getValue();
		String selectedAttr = btsVO.equals(selected)? "selected" : "";
	%>
		<option value="<%=btsVO.getCode() %>" <%=selectedAttr %>><%=btsVO.getName() %></option>
	<%}%>
	</select>
</form>

<script type="text/javascript">
	$(function(){
		$("select[name='bts']").on("change", function(){
			console.log(this.value);
			$("form").submit();
		});
	});
</script>
</body>
</html>