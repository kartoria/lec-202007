<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-bordered">
	<thead>
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
		<c:forEach items="${pagingVO.dataList }" var="board">
			<tr class="trTag">
				<td class="bo_no">${board.bo_no }</td>
				<td>${board.bo_title }</td>
				<td>${board.bo_writer }</td>
				<td>${board.bo_ip }</td>
				<td>${board.bo_hit }</td>
				<td>${board.bo_rec }</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<form id="searchForm">
					<input type="hidden" name="page" />
					<input type="hidden" name="searchType" />
					<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }"/>
				</form>
				<div id="inputUI" class="row">
					<div class="col-auto">
						<select name="searchType" class="form-control mr-1">
							<option value>전체</option>
							<option value="title">제목</option>
							<option value="writer">작성자</option>
						</select>
					</div>
					<div class="col-auto">
						<input type="text" name="searchWord"  class="form-control mr-3" 
							value="${pagingVO.searchVO.searchWord }"/>
					</div>
					<div class="col-auto">	
						<input type="button" value="검색" id="insertBtn" class="btn btn-primary"/>
						<input type="button" value="새글쓰기" id="searchBtn" class="btn btn-primary"/>					</div>
						
				</div>	
				<div id="pagingArea">${pagingVO.pagingHTML }</div>
			</td>
		</tr>
	</tfoot>
</table>

<script type="text/javascript">
	let searchForm = $("#searchForm");
	$("[name='searchType']").val("${pagingVO.searchVO.searchType }");
	$(".pagination").on("click", "a" ,function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;
	});
	
	let trTag = $(".trTag");
	trTag.on("click", function(){
		bo_no = $(this).find(".bo_no").html();
		location.href = $.getContextPath()+"/board/boardView.do?bo_no="+bo_no;
	});
	
	$("#insertBtn").on("click", function(){
		location.href="<c:url value='boardInser.do'/>";
	});
	
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

