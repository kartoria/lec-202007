<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 	
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<c:set var="pagingVO" value="${paginationInfo.pagingVO }" />
<style type="text/css">
</style>
	<div class="container-fluid">
		<div class="row">
			<div class="col-12">
				<h5 class="ml-2">
					<i class="fas fa-home"></i> 장학금 > 교내 장학금 신청
				</h5>
			</div>
		</div>
		<div class="row border-bottom border-cyber p-0 my-2">
			<div class="col-9 text-left">
				<h2 class="font-weight-bold">장학금 신청 내역</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-11">
				<h5 class="pb-5 ml-2">현재까지 장학금 신청내역을 조회할 수 있습니다.</h5>
			</div>
		</div>
		<div class="row mx-3 mb-2">
			<div class="col-12">
				<div class="card">
					<div class="card-body text-center">
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
											<div id="inputUI" class="row">
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
										<div class="text-right mb-3">
											<a id="applyBtn" class="btn waves-effect waves-light btn-primary" href="${cPath}/lms/student/schaolarship/stuScholarshipApplyForm.do">장학금 신청</a>
										</div>
										<div>
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
														style="width: 0px;">신청사유</th>
													<th class="sorting" tabindex="0" aria-controls="zero_config"
														rowspan="1" colspan="1"
														aria-label="Office: activate to sort column ascending"
														style="width: 0px;">신청일자</th>
													<th class="sorting" tabindex="0" aria-controls="zero_config"
														rowspan="1" colspan="1"
														aria-label="Office: activate to sort column ascending"
														style="width: 0px;">신청상태</th>
												</tr>
											</thead>
											<tbody>
												<c:set var="schList" value="${pagingVO.dataList }"/>
												<c:if test="${not empty schList }">
												<c:forEach items="${schList }" var="sch" varStatus="status">
												<tr role="row" class="odd" onClick="schApplyView(${sch.sfundNo})">
													<td><input type="hidden" name="sfundNo" value="${sch.sfundNo }">
													<span class="badge badge-light-warning">${status.count}</span></td>
													<td>${sch.sfundSmst}</td>
													<td>${sch.schVO.schName}</td>
													<td>${fn:substring(sch.sfundReason, 0, 10)}...</td>
													<td>${sch.sfundResdate}</td>
													<td>${sch.codeVO.codeName}</td>
												</tr>
												</c:forEach>
												</c:if>
												<c:if test="${empty schList }">
													<tr>
														<td colspan="6">신청 정보가 없음.</td>
													</tr>
												</c:if>
											</tbody>
											<tfoot>
											<tr>
												<td colspan="6">
												
		      								  	<!-- ============================================================== -->
												<!--페이징 -->
												<!-- ============================================================== -->
												<div id="pagingDiv" class="col-sm-12">
													<div class="dataTables_paginate paging_simple_numbers" id="pagingArea">
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
	</div>
<!-- 신청 상세 내역 modal 부분 -->
<div class="modal fade" id="schApplyView" tabindex="-1"
	role="dialog" aria-labelledby="scrollableModalTitle" aria-hidden="true">
	<div class="modal-dialog modal-xl" >
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="scrollableModalTitle">장학금 신청상세</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
						<table class="table table-sm text-center" id="viewTable">
							<tbody id="modalBody">
							</tbody>
						</table>
			</div>
			<div class="modal-footer">
				<button type="button"
					class="btn waves-effect waves-light btn-rounded btn-secondary"
					data-dismiss="modal">닫기</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<form id="schApplyView" action="${cPath }/lms/student/schaolarship/applyView.do">
	<input type="hidden" name="sfundNo" >
</form>
<script>
const validateOptions = {
        onsubmit:true,
        onfocusout:function(element, event){
           return this.element(element);
        },
        errorPlacement: function(error, element) {
           element.tooltip({
              title: error.text()
              , placement: "right"
              , trigger: "manual"
              , delay: { show: 500, hid: 100 }
           }).on("shown.bs.tooltip", function() {
              let tag = $(this);
              setTimeout(() => {
                 tag.tooltip("hide");
              }, 3000)
           }).tooltip('show');
          }
     }

$("#applyScholarship").validate(validateOptions);

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


function schApplyView(sfundNo){
	$("#schApplyView").find("#modalBody").load("${cPath}/lms/student/schaolarship/applyView.do?sfundNo="+sfundNo,function(){
		$("#schApplyView").modal("show");
	});
	
	
}
</script>