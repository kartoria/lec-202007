<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-bordered">
	<tr>
		<th class="text-center">글 번호</th>
		<td class="pb-1">${board.bo_no }</td>
	</tr>
	<tr>
		<th class="text-center">제목</th>
		<td class="pb-1">${board.bo_title }</td>
	</tr>
	<tr>
		<th class="text-center">내용</th>
		<td class="pb-1">${board.content }</td>
	</tr>
	<tr>
		<th class="text-center">작성일</th>
		<td class="pb-1">${board.bo_date }</td>
	</tr>
	<tr>
		<th class="text-center">조회수</th>
		<td class="pb-1">${board.bo_rec }</td>
	</tr>
	<tr>
		<th class="text-center">추천수</th>
		<td class="pb-1">${board.bo_hit }</td>
	</tr>
	<tr>
		<th class="text-center">아이피</th>
		<td class="pb-1">${board.bo_ip }</td>
	</tr>
	<tr>
		<th class="text-center">이메일</th>
		<td class="pb-1">${board.bo_mail }</td>
	</tr>
</table>