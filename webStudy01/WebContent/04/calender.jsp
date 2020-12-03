<%@page import="java.util.TimeZone"%>
<%@page import="static java.util.TimeZone.*"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String yearStr = request.getParameter("year");
	String monthStr = request.getParameter("month");
	String language = request.getParameter("language");
	String timezone = request.getParameter("timezone");
	
	
	Locale clientLocale = request.getLocale();
	if(language != null){
		clientLocale = Locale.forLanguageTag(language);
	}
	DateFormatSymbols dfs = DateFormatSymbols.getInstance(clientLocale);
	
	// 클라이언트의 국가를 찾아줌
	TimeZone zone = TimeZone.getDefault();
	if(timezone!=null){
		zone = TimeZone.getTimeZone(timezone);
	}
	
	//Date는 대부분 deprecated , Calendar 사용
	Calendar cal = Calendar.getInstance(zone, clientLocale);
	
	//현재의 날짜와 달라졌다면 flag를 false로 바꾸는 것
	boolean currentFlag = true;
	int currentYear = cal.get(YEAR);
	int currentMonth = cal.get(MONTH);
    //숫자 최대 4개 (XXXX년)
	int current = cal.get(DAY_OF_MONTH);
	cal.set(DAY_OF_MONTH, 1);
	if(yearStr != null && yearStr.matches("\\d{4}")){
		cal.set(YEAR, Integer.parseInt(yearStr));
	}
									    
	if(monthStr != null && monthStr.matches("[01]?[0-9]")){
		cal.set(MONTH, Integer.parseInt(monthStr));
	}								    
	if(cal.get(YEAR)!=currentYear && cal.get(MONTH)!=currentMonth){
		//현재 날짜와 다르다면,
		currentFlag = false;
	}
	
	
	//static import asdfasdfasdf.*

	//사람이 보기에는 10월이겠지만, 컴퓨터의 입장에서는 1월이 0월이다.
	//따라서 10월을 보고싶다면 10-1을 입력
	//시간은 11월 1일로 된 것	
	cal.set(DAY_OF_MONTH, 1);
	//마지막 날짜 잡기(매 달 마지막 날이 다르다.)
	int lastDate = cal.getActualMaximum(DAY_OF_MONTH);
	
	//달력에서 1일 이전의 빈 칸의 개수를 반환
	int offset = cal.get(DAY_OF_WEEK) - 1;
	
	//현재 달 기준으로 1달 후 , -를 붙이면 이전 달
	cal.add(MONTH, -1);
	
	int beforeYear = cal.get(YEAR);
	int beforeMonth = cal.get(MONTH);
	
	cal.add(MONTH, 2);
	
	int nextYear = cal.get(YEAR);
	int nextMonth = cal.get(MONTH);
	
	cal.add(MONTH, -1);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	td,th{
		border : 2px solid black;
	}
	table {
	/* 경계선이 겹치는 경우 방지 */
	border-collapse: collapse;
	width : 100%;
	height : 500px;
	}
	.red{
		color : red;
	}
	.blue{
		color : blue;
	}
	.current{
		background-color : orange;
	}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<!-- jquery의 data function -->
<a href="#" data-year="<%=beforeYear %>" data-month="<%=beforeMonth %>" class="controlA">이전달</a>
<!-- 클릭 시 링크 이동이 아닌 form을 통한 이동을 하겠다는 의미 -->
<h4><%=String.format("%tc", cal) %></h4>
<a href="#" data-year="<%=nextYear%>" data-month="<%=nextMonth%>" class="controlA">다음달</a>

<form id = "calendarForm">
<input type="number" name="year" placeholder="2020" value="<%=cal.get(YEAR)%>"/>
<select name="month">
	<%
		String[] months = dfs.getMonths();
		String optPtrn = "<option value = '%d' %s>%s</option>";
		for(int idx = 0; idx < months.length-1; idx++){
			String selected = idx==cal.get(MONTH) ? "selected" : "";
			out.println(String.format(optPtrn, idx, selected, months[idx]));			
		}
	%>
</select>
<select name="language">
	<%
		String ptrn = "<option value = '%s' %s>%s</option>";
		Locale[] locales = Locale.getAvailableLocales();
		for(Locale tmp : locales){
			String displayLanguage = tmp.getDisplayLanguage(tmp);
			if(displayLanguage.isEmpty()) continue;
			String selected = tmp.equals(clientLocale) ? "selected" : "";
			out.println(String.format(ptrn, tmp.toLanguageTag(), selected, displayLanguage));
		}
	%>
</select>
<select name="timezone">
	<%
		String[] ids = TimeZone.getAvailableIDs();
		for(String zoneId : ids){
			TimeZone tmp = TimeZone.getTimeZone(zoneId);
			String selected = tmp.equals(zone)?"selected":"";
			out.println(String.format(ptrn, zoneId, selected, tmp.getDisplayName(clientLocale)));
		}
	%>
</select>
<input type="submit" value="전송">
</form>

<table>
<thead>
	<tr>
		<%
			String[] weekdays = dfs.getWeekdays();
			for(int idx = SUNDAY; idx <= SATURDAY; idx++){
				%>
				<th><%=weekdays[idx] %></th>
				<%
			}
		%>
	</tr>
</thead>
<tbody>
<%	
	String tdPtrn = "<td class = '%s'>%s</td>";
	//날짜의 일련번호 넣기
	int count = 1;
	//매달 첫 날의 요일에 따라 1일이 어떤 칸에 들어갈 지가 달라진다.
	for(int row = 1; row <=6; row++){
		out.println("<tr>");
		for(int col = 1; col <=7; col++){ 
			int date = count++ - offset;
			String clz = col == 1? "red" : col == 7 ? "blue" : currentFlag&&(date==current)? "current" : "normal";
			String dateStr = date < 1 ? "" : date > lastDate ? "" : date + "";
			out.println(String.format(tdPtrn, clz, dateStr));						
		} 
		out.println("</tr>");
	} 
%>
</tbody>
</table>
<script type="text/javascript">
	let calendarForm = $("#calendarForm");
	
	/* 모든 입력 태그 중 input[name]인 것 */
	calendarForm.find(':input[name]').on("change",function(){
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
		/* 처음에 일단 이벤트를 막을 때 
		event + event.preventDefault(); + return false;
		   let -> 스코프 변수 범위의 제한이 있다. (더 명확해짐)
		   var -> 변수 범위의 제한이 없다.
		*/
	})
</script>
</body>
</html>