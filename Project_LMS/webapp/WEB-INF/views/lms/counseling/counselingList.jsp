<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>
<c:set var="pagingVO" value= "${paginationInfo.pagingVO }"/>
<form id="searchForm">
    <input type="hidden" name="page"/>
    <input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType }"/>
	<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }"/>
</form>
<div class="container-fluid border px-3">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 상담 > 상담 내역</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">상담 내역</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">해당 상담을 선택하시면 상담 내용을 확인할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row my-3">
		<div class="input-group col-12 d-flex justify-content-end align-items-stretch">
          	<div id="inputUI"class="col-4 d-flex justify-content-end align-items-stretch">
	        	<div id="searchArea" class="input-group">
		          	<div class="form-group">
						<select name="searchType" class="form-control w-100 h-100">
							<option value>전체</option>
							<option value="name" ${'name' eq param.searchType?"selected":""  }>교수이름</option>
							<option value="date" ${'date' eq param.searchType?"selected":""  }>상담일짜</option>
							<option value="accpt" ${'accpt' eq param.searchType?"selected":""  }>접수여부</option>
						</select>
					</div>
					<div class="form-group flex-grow-1">
		       			<input type="text" type="text" name="searchWord" onkeyup="enterkey()" class="form-control w-100 h-100" placeholder="검색 내용을 입력하세요.">
		       		</div>
		       		<div class="form-group">
		                <button id="searchBtn" type="button" class="btn waves-effect waves-light btn-primary px-4 h-100">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search form-control-icon"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
						</button>
					</div>
					
	            </div>
			</div>
       </div>
	</div>
	<div class="row my-3">
		<div class="col-12">
			<table class="table">
				<thead class="thead-light">
					<tr class="text-center text-dark">
						<th class="font-weight-bold">순번</th>
						<th class="font-weight-bold">교수 이름</th>
						<th class="font-weight-bold">상담 날짜</th>
						<th class="font-weight-bold">상담 여부</th>
					</tr>
				</thead>
				<tbody>
				<c:set var="counList" value="${pagingVO.dataList }"/>
					<c:if test="${not empty counList }">
						<c:forEach items="${counList }" var="coun" varStatus="status">
							<tr class="text-center">
								<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
								<td>
									<a href="${cPath}/lms/student/counseling/detail.do?what=${coun.cstNo }">
										${coun.memberVO.memName }
									</a>
								</td>
								<td>${coun.cstDate }</td>
								<td>${coun.cstAccpt }</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty counList }">
						<tr class="text-center">
							<td colspan="4">상담 내역이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<div id="pagingArea" class="col-sm-12 d-flex justify-content-center">
				<ui:pagination paginationInfo="${paginationInfo }" jsFunction="pageLinkNumber" type="bsNumber"/>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

function pageLinkNumber(event){
	
	event.preventDefault();
	let page = $(event.target).data("page");
	searchForm.find("[name='page']").val(page);
	console.log(page);
	searchForm.submit();
	return false;
}

var searchForm = $("#searchForm");
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

function enterkey(){
	if(window.event.keyCode==13){
		$("#searchBtn").click();
	}
}
</script>