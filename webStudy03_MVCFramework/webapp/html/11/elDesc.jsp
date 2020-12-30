<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/elDesc.jsp</title>
</head>
<body>
<h4>EL(Expression Langage)</h4>
<pre>
	: (속성) 값을 표현할 목적의 스크립트 형태 언어.
	\${속성명}
	<% String data = "데이터"; 
	   pageContext.setAttribute("data", "페이지 데이터");
	   request.setAttribute("data", "요청 데이터");
	   session.setAttribute("data", "세션 데이터");
	   application.setAttribute("data", "어플리케이션 데이터"); %>
	${asdasd}
	${pageScope.data}
	${requestScope.data}
	${sessionScope.data}
	${applicationScope.data}
	
	1. 속성 데이터 출력의 목적으로 사용
	
	2. 연산자 적용
		1) 단항 연산자 : empty(속성이 비어있으면 true, 문자열, 배열, collection 피연산자에 사용)
		2) 이항 연산자 :
			- 산술연산자 : +-*/%
			${1+1}, ${1+"1"}, ${"1"+"1"}, ${4/2 }, ${3/2 }
			 
			- 논리연산자 &&(and), ||(or), !P%(not)
			${true and true }, ${true or false }, ${not false }
			${not abc}, ${abc + 1}, ${abc-bcd }
			
			- 비교연산자 : >(gt), <(lt), >=(ge), <=(le), ==(eq), !=(ne)
		3) 삼항 연산자 : 조건식? 참문장 : 거짓문장
			${empty abc ? "없음" : "있음"}	
			${not empty abc ? "있음" : "없음"}
			
	3. 집합객체에 의한 접근 방법 지원
	4. 객체의 멤버에 대한 접근 방법 지원
	5. EL 기본객체(Map&lt;String, ?&gt;)
		1) 영역 객체 : pageScope, requestScope, sesionScope, applicationScope
		2) 파라미터 객체 : param (Map&lt;String, String&gt;), paramValues (Map&lt;String, String[]&gt;)
		3) 헤더 객체 : header (Map&lt;String, String&gt;), headerValues (Map&lt;String, String[]&gt;)
		4) 쿠키 객체 : cookie (Map&lt;String, Cookie&gt;)
		5) 컨텍스트 초기화 파라미터 객체 : initParam(Map&lt;String, String&gt;)
		6) pageContext
		
		
		이닛 파라미터 : <%=application.getInitParameter("contentFolder")%>, ${initParam.contentFolder }, ${initParam["contentFolder"] }
		
		리퀘스트 : <%=pageContext.getRequest() %>, ${pageContext.request }
		
		컨텍스트 패스 : ${pageContext.request.contextPath }, ${pageContext.request.contextPath }, ${pageContext["request"]["contextPath"] }
		
		
		
		쿠키 : <%=new CookieUtils(request).getCookie("JSESSIONID").getValue() %>, ${cookie.JSESSIONID.value }, ${cookie["JSESSIONID"]["value"] }
		
		헤더 : <%=request.getHeader("user-agent") %>, ${header.user-agent }, ${header["user-agent"] }
		헤더 밸류스 : <%=request.getHeaders("accept-language") %>, ${headerValues["accept-language"] }
		
		
		파라미터 : <%=request.getParameter("name") %>, ${param.name }, ${param["name"] }
		파라미터 밸류스 : <%--=request.getParameterValues("name")[1] --%>, ${paramValues.name[1] }, ${paramValues["name"][1] }
		
		<%
			MemberVO member = MemberVO.builder().mem_id("a001").build();
			pageContext.setAttribute("member", member);
		%>
		변수 : <%=member.getMem_id() %>, ${member.getMem_id() }, ${member.mem_id }, ${member["mem_id"] }
		메소드 : <%=member.getTest() %>, ${member.getTest() }, ${member.test }, ${member["test"] }
		
		
		<%
			List<String> list = new ArrayList();
			list.add("value1");
			list.add("value2");
			pageContext.setAttribute("list", list);
			String[] array = new String[]{"value1", "value2"};
			pageContext.setAttribute("array", array);
			Set set = new HashSet();
			set.add("value1");
			set.add("value2");
			pageContext.setAttribute("set", set);
			Map map = new HashMap();
			pageContext.setAttribute("map", map);
			map.put("key-1", "value1");
			map.put("key2", "value2");
		%>		
		배열   : <%=array[1] %>   , ${array[3] }                <!-- 2번째꺼는 배열 범위 넘어가면 오류없고 화이트스페이스 -->
		리스트 : <%=list.get(1) %>, ${list.get(1) }, ${list[3] } <!-- 3번째꺼는 배열 범위 넘어가면 오류없고 화이트스페이스 -->
		셋     : <%=set %>, ${set }
		맵     : <%=map.get("key-1") %>, ${map.get("key-1") }, ${map["key-1"] }, ${map.key-1 } <!-- 제일 권장하는 방법은 3번째 (4번째방법은 키에 기호가들어가면 이상해짐) -->
	
</pre>
</body>
</html>