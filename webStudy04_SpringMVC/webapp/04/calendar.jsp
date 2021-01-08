<%@page import="kr.or.ddit.enumpkg.ServiceKind"%>
<%@page import="java.util.TimeZone"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String yearStr= request.getParameter("year");
String monthStr= request.getParameter("month");
String language = request.getParameter("language");
String timeZone = request.getParameter("timeZone");

Locale clientLocale = request.getLocale();
if(language!=null){
	clientLocale = Locale.forLanguageTag(language);
}
DateFormatSymbols dfs = DateFormatSymbols.getInstance(clientLocale);

TimeZone zone = TimeZone.getDefault();
if(timeZone!=null){
	zone = TimeZone.getTimeZone(timeZone);
}

Calendar cal = Calendar.getInstance(zone, clientLocale);
boolean currentFlag = true;
int currentYear = cal.get(YEAR);
int currentMonth = cal.get(MONTH);
//cal.set(Calendar.MONTH, 10-1);
int current = cal.get(DAY_OF_MONTH);
cal.set(DAY_OF_MONTH, 1);

if(yearStr!=null && yearStr.matches("\\d{4}")){
	cal.set(YEAR, Integer.parseInt(yearStr));
}
if(monthStr!=null && monthStr.matches("[01]?[0-9]")){
	cal.set(MONTH, Integer.parseInt(monthStr));
}
if(cal.get(YEAR)!=currentYear || cal.get(MONTH)!=currentMonth){
	currentFlag = false;
}

int lastDate = cal.getActualMaximum(DAY_OF_MONTH);
int offset = cal.get(DAY_OF_WEEK) - 1;

cal.add(MONTH, -1);
int beforeYear = cal.get(YEAR);
int beforeMonth = cal.get(MONTH);

cal.add(MONTH, 2);
int nextYear = cal.get(YEAR);
int nextMonth = cal.get(MONTH);

cal.add(MONTH, -1);
%>    
<style type="text/css">
	td,th{
		border: 1px solid black;
	}
	table {
		border-collapse: collapse;
		width: 100%;
		height: 500px;
	}
	.red{
		color: red;
	}
	.blue{
		color: blue;
	}
	.current{
		background-color: orange;
	}
</style>
<a href="#" data-year="<%=beforeYear %>" data-month="<%=beforeMonth %>" class="controlA">이전달</a>
<h4><%=String.format("%tc", cal) %></h4>
<a href="#" data-year="<%=nextYear%>" data-month="<%=nextMonth%>" class="controlA">다음달</a>
<form id="calendarForm">
<input type="hidden" name="service" value="<%=ServiceKind.CALENDAR.name() %>" />
<input type="number" name="year" placeholder="2020" value="<%=cal.get(YEAR)%>"/>
<select name="month">
	<%
		String[] months = dfs.getMonths();
		String optPtrn = "<option value='%s' %s>%s</option>";
		for(int idx=0; idx<months.length-1; idx++){
			String selected = idx==cal.get(MONTH)? "selected" : "";
			out.println(String.format(optPtrn, idx, selected, months[idx]));
		}
	%>
</select>
<select name="language">
	<%
		Locale[] locales = Locale.getAvailableLocales();
		for(Locale tmp : locales){
			String displayLanguage = tmp.getDisplayLanguage(tmp);
			if(displayLanguage.isEmpty()) continue;
			String selected = tmp.equals(clientLocale)?"selected":"";
			out.println(String.format(optPtrn, tmp.toLanguageTag(), selected, displayLanguage));
		}
	%>
</select>
<select name="timeZone">
	<%
		String[] ids = TimeZone.getAvailableIDs();
		for(String zoneId : ids){
			TimeZone tmp = TimeZone.getTimeZone(zoneId);
			String selected = tmp.equals(zone)?"selected":"";
			out.println(String.format(optPtrn, zoneId, selected, tmp.getDisplayName(clientLocale)));
		}
	%>
</select>
<input type="submit" value="전송" />
</form>
<table>
<thead>
	<tr>
		<%
			String[] weekdays = dfs.getWeekdays();
			for(int idx=SUNDAY; idx<=SATURDAY; idx++){
				%>
				<th><%=weekdays[idx] %></th>
				<%
			}
		%>
	</tr>
</thead>
<tbody>
<%
	String tdPtrn = "<td class='%s'>%s</td>";
	int count = 1;
	for(int row=1; row<=6; row++){
		out.println("<tr>");
		for(int col=1; col<=7; col++){
			int date = count++ - offset;
			String clz = col==1? "red" : col==7? "blue" : currentFlag&&(date==current)? "current": "normal";
			String dateStr = date < 1? "" : date > lastDate? "" : date+"";
			out.println(String.format(tdPtrn, clz, dateStr));
		}
		out.println("</tr>");		
	}
%>
</tbody>
</table>
<script type="text/javascript">
	let calendarForm = $("#calendarForm");
	calendarForm.find(':input[name]').on("change", function(){
		calendarForm.submit();
	});
	$(".controlA").on("click", function(event){
		event.preventDefault();
		let year = $(this).data("year");
		let month = $(this).data("month");
		calendarForm.find("[name='year']").val(year);
		calendarForm.find("[name='month']").val(month);
		calendarForm.submit();
		return false;
	});
</script>