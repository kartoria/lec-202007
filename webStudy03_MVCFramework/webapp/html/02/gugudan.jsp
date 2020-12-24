<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String minDanStr = request.getParameter("minDan");
String maxDanStr = request.getParameter("maxDan");

int minDan = 2;
int maxDan = 9;
if(minDanStr != null && minDanStr.matches("[2-9]")) {
	minDan = Integer.parseInt(minDanStr);
	maxDan = Integer.parseInt(maxDanStr);
}
StringBuffer gugudanTrTags = new StringBuffer();
String ptrn = "<tr><td>%d*%d=%d</td>";
for(int dan=minDan; dan<=maxDan; dan++) {
	for(int mul=1; mul<=9; mul++) {
		gugudanTrTags.append(String.format(ptrn, dan, mul, (dan*mul)));
	}
}
gugudanTrTags.append("</tr>");
%>
<h4>구구단</h4>
<form>
	min : <input type="number" name ="minDan" value="<%=minDan %>" />
	max : <input type="number" name ="maxDan" value="<%=maxDan %>" />
	<input type="submit" value="전송" />
</form>
<table>
	<%=gugudanTrTags %>
</table>

