<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/DataTables/datatables.min.css"></link>
<script src="<%=request.getContextPath() %>/js/DataTables/datatables.min.js"></script>

<form method="post" id="passwordForm">
	<table class="table">
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="text" class="form-control" name="mem_pass"/>
			</td>
		</tr>
	</table>
</form>

