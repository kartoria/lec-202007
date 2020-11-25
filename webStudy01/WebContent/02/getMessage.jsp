<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/plane; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String lang = request.getParameter("lang");
	String acceptLanguage = request.getHeader("accept-language");
	Locale locale = request.getLocale();
	if(lang!=null && !lang.isEmpty()){
		locale = Locale.forLanguageTag(lang.toLowerCase());
	}
	ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.msg.message", locale);
	String message = bundle.getString("bow");
%>
<%=message %>