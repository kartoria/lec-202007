<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<table class="table table-bordered">
	<tr>
		<th class="text-center">제목</th>
		<td class="pb-1">${board.bo_title }</td>
	</tr>
	<tr>
		<th class="text-center">작성자</th>
		<td class="pb-1">${board.bo_writer }[${board.bo_mail }][${board.bo_ip }]</td>
	</tr>
	<tr>
		<th class="text-center">작성일</th>
		<td class="pb-1">${board.bo_date }</td>
	</tr>
	<tr>
		<th class="text-center">조회수</th>
		<td class="pb-1">${board.bo_hit }</td>
	</tr>
	<tr>
		<th class="text-center">추천수</th>		
		<td class="pb-1">
			<span id="recArea">${board.bo_rec }</span>
			<c:if test="${fn:contains(cookie.boardCookie.value, board.bo_no) }">
				<span class="btn btn-secondary ml-2" id="recBtn">추천</span>
			</c:if>
		</td>
	</tr>
	<tr>
		<th class="text-center">첨부파일</th>
		<td class="pb-1">
			<c:if test="${not empty board.attachList }">
				<c:forEach items="${board.attachList }" var="attach" varStatus="vs">
					<span title="다운로드:${attach.att_downcount }">${attach.att_filename }</span>
					${not vs.last?"|":"" }
				</c:forEach>		
			</c:if>
		</td>
	</tr>
	<tr>
		<th class="text-center">내용</th>
		<td class="pb-1">${board.bo_content }</td>
	</tr>
</table>
<script type="text/javascript">
	$("#recBtn").on("click", function(){
		$.ajax({
			url:"${pageContext.request.contextPath }/board/recommend.do",
			data:{
				what : ${board.bo_no}
			},
			dataType:"text",
			success:function(resp){
				if("OK" == resp.trim()){
					let recCnt = $("#recArea").text();
					$("#recArea").text(parseInt(recCnt)+1);
				}
			},
			error:function(xhr){
				console.log(xhr);
			}
		});
	});
</script>