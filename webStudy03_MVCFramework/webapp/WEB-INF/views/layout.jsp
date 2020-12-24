<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>layout</title>
<style type="text/css">
   #topMenu{
      width: 100%;
      height: 50px;
   }
   #topMenu li {
      float : left;
      list-style: none;
      padding-right: 50px
   }
   #leftMenu{
      float:left;
      width: 15%;
      height: 500px;
   }
   #leftMenu a{
      background-color : red;
      cursor: pointer;
   }
   
   #contents{
      float:right;
      width: 84%;
      height: 500px;
   }
   
   #footer{
      float : left;
      width: 100%;
      height: 50px;
   }
   div{
      border: 1px solid black;
   }
   

</style>
<jsp:include page="/html/include/preScript.jsp"></jsp:include>
</head>
<body>
<div id="topMenu">
   <jsp:include page="/html/include/topMenu.jsp"/>
</div>
<div id="leftMenu">
   <jsp:include page="/html/include/leftMenu.jsp"/>
</div>
<div id="contents">
   <%
   String includePath = (String)request.getAttribute("includePath");
   if(StringUtils.isNotBlank(includePath)){
      pageContext.include(includePath);
   }
   %>
</div>
<div id="footer">
   <jsp:include page="/html/include/footer.jsp"/>
</div>
</body>
</html>