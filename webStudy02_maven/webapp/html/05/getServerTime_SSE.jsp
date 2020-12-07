<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/event-stream; charset=UTF-8"
    pageEncoding="UTF-8" buffer="8kb" %>
<%
    boolean flag = true;
   	while(flag == true){
  		Thread.sleep(1000);
  		Date now = new Date();
  		// 밑은 전송할 데이터인데 들여쓰기나 엔터에 주의해야함. 엔터 두번으로 다음데이터를 구분
%>
id: <%=now.getTime() %>
event:test
data: <%=now %>
data: line data


<% 		out.flush();
   	}
%>

