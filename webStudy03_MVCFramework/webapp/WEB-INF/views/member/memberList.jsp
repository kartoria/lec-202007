<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-bordered">
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>주소1</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${pagingVO.dataList }" var="member">
			<tr>
				<td>${member.mem_id }</td>
				<td>${member.mem_name }</td>
				<td>${member.mem_hp }</td>
				<td>${member.mem_mail }</td>
				<td>${member.mem_add1 }</td>
				<td>${member.mem_mileage }</td>
			</tr>
	</c:forEach>
	<%--
		for(Object element : memberList){
			MemberVO member = (MemberVO)element;
			--%>
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
							<option value="name">이름</option>
							<option value="address">지역</option>
						</select>
					</div>
					<div class="col-auto">
						<input type="text" name="searchWord"  class="form-control mr-3" 
							value="${pagingVO.searchVO.searchWord }"/>
					</div>
					<div class="col-auto">	
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
					</div>
				</div>	
				${pagingVO.pagingHTML }
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




















