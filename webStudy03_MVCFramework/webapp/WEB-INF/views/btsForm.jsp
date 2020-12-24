<%@page import="kr.or.ddit.vo.BtsVO"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Map<String, BtsVO> btsDB = (Map)application.getAttribute("btsDB"); 
	BtsVO selected = (BtsVO) session.getAttribute("selected");
%>
<style type="text/css">
	select{
		width:300px;	
		height:100px;
		text-align: center;
		font-size: 2.0em;
		font-weight: bold;
	}
</style>
<h1>방 탄 소 년 단 소 개 페 이 지 로 이 동</h1>
<form id="btsForm" action="?service=BTS" method="post">
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
			$("#btsForm").submit();
		});
	});
</script>
