<%@page import="org.apache.commons.lang3.StringUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
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

<tiles:insertAttribute name="preScript"/>
</head>
<body>
<div id="topMenu">
	<tiles:insertAttribute name="topMenu"/>
</div>
<div id="leftMenu">
	<tiles:insertAttribute name="leftMenu"/>
</div>
<div id="contents">
	<tiles:insertAttribute name="contents"/>
</div>
<div id="footer">
	<tiles:insertAttribute name="footer"/>
</div>
</body>
</html>