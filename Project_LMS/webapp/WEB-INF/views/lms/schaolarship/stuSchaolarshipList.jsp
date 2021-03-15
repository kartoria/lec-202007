<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 	
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="pagingVO" value="${paginationInfo.pagingVO }" />
<style type="text/css">
#zero_config {
	text-align: center;
}
#searchArea{
	float : right;
}
#semesterArea{
 	margin-top : 25px; 
}
#pagingDiv{
	margin-right: 50%;	
}
#pagingArea {
	margin-right: 10%;
}
</style>
<div class="container-fluid">
	<div class="row">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 장학금 > 장학금 수혜 내역
			</h5>
		</div>
	</div>
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-9 text-left">
			<h2 class="font-weight-bold">장학금 수혜 내역</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-11">
			<h5 class="pb-5 ml-2">현재까지 장학금 수혜내역을 조회할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-body">
					<div class="table-responsive">
						<div id="zero_config_wrapper"
							class="dataTables_wrapper container-fluid dt-bootstrap4">
							<div class="row">
								<div id="semesterArea" class="col-sm-12 col-md-2">
									<div class="dataTables_length" id="zero_config_length">
										 <form id="searchForm">
	           				 	 			<input type="hidden" name="page" />
	           				 	 			<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }"/>
	       								 </form>
										<div id="inputUI" class="row mb-3">
											<select name="searchWord" id="searchWord"
												aria-controls="zero_config"
												class="form-control form-control-sm">
													<option value=" ">전체</option>
												<c:set var="schList" value="${semeSchList }"/>
												<c:if test="${not empty schList }">
												<c:forEach items="${schList }" var="sch" varStatus="status">
													<option value="${sch.sfundSmst }" <c:out value="${searchVO.searchWord eq sch.sfundSmst ? 'selected' :'' } "/>>
														${sch.sfundSmst }학기</option>
												</c:forEach>
												</c:if>	
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<table id="zero_config"
										class="table table-striped table-bordered no-wrap dataTable"
										role="grid" aria-describedby="zero_config_info">
										<thead>
											<tr role="row">
												<th class="sorting_asc" tabindex="0"
													aria-controls="zero_config" rowspan="1" colspan="1"
													aria-sort="ascending"
													aria-label="Name: activate to sort column descending"
													style="width: 0px;">순번</th>
												<th class="sorting" tabindex="0" aria-controls="zero_config"
													rowspan="1" colspan="1"
													aria-label="Position: activate to sort column ascending"
													style="width: 0px;">학기</th>
												<th class="sorting" tabindex="0" aria-controls="zero_config"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"
													style="width: 0px;">장학명</th>
												<th class="sorting" tabindex="0" aria-controls="zero_config"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"
													style="width: 0px;">신청일자</th>
												<th class="sorting" tabindex="0" aria-controls="zero_config"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"
													style="width: 0px;">수혜 일자</th>
												<th class="sorting" tabindex="0" aria-controls="zero_config"
													rowspan="1" colspan="1"
													aria-label="Office: activate to sort column ascending"
													style="width: 0px;">수혜금액</th>
											</tr>
										</thead>
										<tbody>
											<c:set var="schList" value="${pagingVO.dataList }"/>
											<c:if test="${not empty schList }">
											<c:set var="schTotal" value="0"/>
											<c:forEach items="${schList }" var="sch" varStatus="status">
											<tr role="row" class="odd">
												<td><span class="badge badge-light-warning">${status.count}</span></td>
												<td>${sch.sfundSmst}</td>
												<td>${sch.schName}</td>
												<td>${sch.sfundResdate}</td>
												<td>${sch.sfundGetdate}</td>
												<td style="text-align: right;"><fmt:formatNumber value="${sch.sfundValue }" pattern="#,###" />원</td>
											<c:set var="schTotal" value="${schTotal + sch.sfundValue}"/> 
											</tr>
											</c:forEach>
											</c:if>
											<c:if test="${empty schList }">
												<tr>
													<td colspan="6">수혜 정보가 없음.</td>
												</tr>
											</c:if>
											<tr role="row" class="odd">
												<td colspan="3">
														<label>총 장학 금액</label>
												</td>
												<td colspan="4"  style="text-align: right;">
	<%-- 											<c:out value="${schTotal }">만원</c:out> --%>
													<fmt:formatNumber value="${schTotal}" pattern="#,###" />원
	<%-- 												<input id="totalschaolarship" type="text" class="form-control" disabled="disabled" value="${schTotal}원"> --%>
												</td>
											</tr>
										</tbody>
										<tfoot>
										<tr>
											<td colspan="6">
											
	      								  	<!-- ============================================================== -->
											<!--페이징 -->
											<!-- ============================================================== -->
											<div id="pagingDiv" class="col-sm-12">
												<div class="dataTables_paginate paging_simple_numbers"
													id="pagingArea">
													<ui:pagination paginationInfo="${paginationInfo }"
														jsFunction="pageLinkNumber" type="bsNumber" />
												</div>
											</div>
										</tr>
										</tfoot>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
//=== 검색 ===
let searchForm = $("#searchForm");
//== 페이징 ==
function pageLinkNumber(event){
	event.preventDefault();
	let page = $(event.target).data("page");
	searchForm.find("[name='page']").val(page);
	searchForm.submit();
	return false;
}

$("#searchWord").on("change",function(){
	let smst = $(this).children("option:selected").text();
	let name = $(this).attr("name");
	let value = $(this).val();
	let hidden = searchForm.find("[name='"+name+"']");
	hidden.val(value);
	searchForm.submit();
});

</script>