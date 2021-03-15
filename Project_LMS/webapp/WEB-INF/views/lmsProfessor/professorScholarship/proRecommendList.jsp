<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 	
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<c:set var="pagingVO" value="${paginationInfo.pagingVO }" />   
<div class="container-fluid">
	<div class="row">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 장학 관리 > 교내 장학생 추천
			</h5>
		</div>
	</div>
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-9 text-left">
			<h2 class="font-weight-bold">장학생 추천</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-11">
			<h5 class="pb-5 ml-2">장학생을 추천할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row mx-3 mb-2">
		<div class="col-12">
			<div class="card">
				<div class="card-body">
					<div id="zero_config_wrapper"
						class="dataTables_wrapper container-fluid dt-bootstrap4">
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
											<c:set var="schList" value="${semester }"/>
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
						<div class="col-sm-12">
								<button type="button" id="recommendBtn" onclick="location.href='${cPath}/lms/professor/scholarship/recommendForm.do' " class="btn waves-effect waves-light btn-primary float-right mb-3" >추천</button>
							<table id="zero_config"
								class="table table-striped table-bordered no-wrap dataTable text-center"
								 aria-describedby="zero_config_info">
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
											aria-label="Position: activate to sort column ascending"
											style="width: 0px;">학번</th>
										<th class="sorting" tabindex="0" aria-controls="zero_config"
											rowspan="1" colspan="1"
											aria-label="Position: activate to sort column ascending"
											style="width: 0px;">학생이름</th>
										<th class="sorting" tabindex="0" aria-controls="zero_config"
											rowspan="1" colspan="1"
											aria-label="Office: activate to sort column ascending"
											style="width: 0px;">장학명</th>
										<th class="sorting" tabindex="0" aria-controls="zero_config"
											rowspan="1" colspan="1"
											aria-label="Office: activate to sort column ascending"
											style="width: 0px;">추천일자</th>
										<th class="sorting" tabindex="0" aria-controls="zero_config"
											rowspan="1" colspan="1"
											aria-label="Office: activate to sort column ascending"
											style="width: 0px;">신청사유</th>
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
									<tr onClick="schDetail(${sch.sfundNo})" role="row" class="odd" >
										<td><span class="badge badge-light-warning">${status.count}</span></td>
										<td>${sch.sfundSmst}</td>
										<td>${sch.sfundStudent}</td>
										<td>${sch.memberVO.memName}</td>
										<td>${sch.schVO.schName}</td>
										<td>${sch.sfundResdate}</td>
										<td>${fn:substring(sch.sfundReason, 0, 10)}...</td>
										<td onclick="event.stopPropagation()">${sch.codeVO.codeName}
										<c:if test="${sch.codeVO.codeName eq '신청' }">
										<button type="button" id="cancelBtn" class="btn btn-secondary"  onClick="cancelRecommend('${sch.sfundNo},${sch.codeVO.codeName }')" ><!-- <i class="ti-trash"></i> -->취소</button>
										</c:if>
										</td>
									</tr>
									</c:forEach>
									</c:if>
									<c:if test="${empty schList }">
										<tr>
											<td colspan="8">게시글 정보가 없음.</td>
										</tr>
									</c:if>
								</tbody>
								<tfoot>
								<tr>
									<td colspan="8">
									
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
<!-- /.modal -->
<!-- 신청 상세 내역 modal 부분 -->
<div class="modal fade" id="schDetail" tabindex="-1"
	role="dialog" aria-labelledby="scrollableModalTitle" aria-hidden="true">
	<div class="modal-dialog modal-xl" >
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="scrollableModalTitle">추천내역 상세</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
						<table class="table table-sm text-center" id="modalBody">
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

<form id="cancelForm">
	<input type="hidden" name="sfundNo">
</form>

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

// 신청 철회 버튼 클릭
function cancelRecommend(data){
	let dataArr = data.split(',');
	let sfundNo = dataArr[0];
	let codeName = dataArr[1];
	
	if(codeName=="지급"){
		alert("이미 지급된 내역입니다.");
		return;
	}
	if(codeName=="접수"){
		alert("이미 접수된 내역입니다.");
		return;
	}
	
	let result = confirm("신청을 철회하실건가요?");
	if(result){
		let hidden = $("#cancelForm").find("[name='sfundNo']");
		hidden.val(sfundNo);	
		$.ajax({
			url :'${cPath}/lms/professor/scholarship/cancelRecommend.do'
	    	,method : 'post'
	    	, data : $("#cancelForm").serialize()
		    , dataType : "json"
		    , success : function(resp){
				alert(resp.message);		    	
	    	location.href="${cPath}/lms/professor/scholarship/recommendList.do";
		    }
		    , error : function(request, error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
		    }
   		 });
		
	}else{
		return;
	}
}


function schDetail(sfundNo){
	console.log("dhkt");
	$("#schDetail").find("#modalBody").load("${cPath}/lms/professor/scholarship/"+sfundNo+"/scholarshipDetail.do",function(){
		$("#schDetail").modal("show");
	});
};  

</script>