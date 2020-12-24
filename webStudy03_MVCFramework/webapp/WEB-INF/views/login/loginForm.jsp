<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$("#loginForm").on("submit", function() {
			let valid = true;
			$(":input[name]").each(function(index, element) {
				if ($(this).prop("required")) {
					let value = $(this).val();
					if (!value || value.trim().length == 0) {
						valid = valid && false;
					}
					// 					let ptrn = $(this).attr("pattern");
					// 					if(ptrn){
					// 						console.log(ptrn);
					// 						let regex = new RegExp(ptrn);
					// 						valid = valid && regex.test(value);
					// 					}
				}
			});
			return valid;
		});
	});
</script>
<%
	MemberVO authMember = (MemberVO) session.getAttribute("authMember");
	if (authMember == null) {
%>
<form id="loginForm" action="<%=request.getContextPath()%>/login/loginProcess.do" method="post">
	<%
		String mem_id = (String) session.getAttribute("mem_id");
			session.removeAttribute("mem_id");
	%>
	<div class="mb-3 mt-3 row g-3">
		<label for="mem_id" class="col-sm-1 col-form-label">아이디</label>
		<div class="col-auto">
			<input type="text" id="mem_id" name="mem_id" value="<%=Objects.toString(mem_id, "")%>" required class="form-control" />
		</div>
		<div class="form-check col-auto">
			<input class="form-check-input" type="checkbox" value="saveId" name="saveId" id="saveId">
			<label class="form-check-label" for="saveId">아이디 기억하기</label>
		</div>
	</div>
	<div class="mb-3 row g-3">
		<label for="mem_pass" class="col-sm-1 col-form-label">비밀번호</label>
		<div class="col-auto">
			<input type="text" name="mem_pass" required pattern="^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+).{5,12}$" class="form-control" />
		</div>
		<div class="col-auto">
			<button type="submit" class="btn btn-primary mb-3">로그인</button>
		</div>
	</div>
</form>
<%
	}
%>








