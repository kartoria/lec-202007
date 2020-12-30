<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-bordered">
	<thead class="thead-dark">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>추천수</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="boardList" value="${pagingVO.dataList }" />
		<c:if test="${not empty boardList }">
			<c:forEach items="${boardList }" var="board">
				<tr>
					<td>${board.rnum }</td>
					<td>${board.bo_title }[${board.rep_cnt }]</td>
					<td>${board.bo_writer }</td>
					<td>${board.bo_date }</td>
					<td>${board.bo_hit }</td>
					<td>${board.bo_rec }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty boardList }">
			<tr>
				<td colspan="6">조건에 맞는 게시글이 없음.</td>
			</tr>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<form id="searchForm">
					<input type="hidden" name="page" />
					<input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType }"/>
					<input type="hidden" name="searchWord"  value="${pagingVO.searchVO.searchWord }"/>
				</form>
				<div id="inputUI" class="row justify-content-center mb-3">
					<div class="col-3">
						<select name="searchType" class="form-control mr-1">
							<option value>전체</option>
							<option value="title" ${'title' eq param.searchType?"selected":"" }>제목</option>
							<option value="writer" ${'writer' eq param.searchType?"selected":"" }>작성자</option>
							<option value="content" ${'content' eq param.searchType?"selected":"" }>내용</option>
						</select>
					</div>
					<div class="col-4">
						<input type="text" name="searchWord"  class="form-control mr-3"  value="${pagingVO.searchVO.searchWord }"/>
					</div>
					<div class="col-auto">	
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
						<input type="button" value="새글쓰기" id="insertBtn" class="btn btn-primary"/>
					</div>
				</div>
				<div id="pagingArea">${pagingVO.pagingHTML }</div>
			</td>
		</tr>
	</tfoot>
</table>
<script type="text/javascript">
	$("#insertBtn").on("click", function(){
		location.href="<c:url value='/board/boardInsert.do'/>";
	});
	$("#pagingArea").on("click", "a" ,function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;
	});
	let searchForm = $("#searchForm");
	$("#searchBtn").on("click", function(){
		let inputs = $(this).parents("div#inputUI").find(":input[name]");
		$(inputs).each(function(index, input){
			let name = $(this).attr("name");
			let value = $(this).val();
			let hidden = searchForm.find("[name='"+name+"']");
			hidden.val(value);
		});
		searchForm.submit();
	});
</script>

