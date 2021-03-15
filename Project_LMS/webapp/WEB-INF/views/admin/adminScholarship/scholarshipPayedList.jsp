<%--
* [[개정이력(Modification Information)]]
* 2021. 1. 23.      PC-17      수정내용
* ----------  ---------  -----------------
* 2021. 1. 23.      PC-17      최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<link rel="stylesheet" href="${cPath}/css/admin/scholarship.css">    
<c:set var="pagingVO" value="${paginationInfo.pagingVO }" />
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 장학관리 > 장학금 수혜 목록
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-admin p-0 my-2">
		<div class="col-12">
			<h2 class="font-weight-bold">장학금 수혜 목록</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">현재까지 장학금 수혜내역을 조회할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row mb-3 mt-3 p-2">
	<!-- 서버로 전송 -->
	 <form id="searchForm">
	    <input type="hidden" name="page" />
	    <input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType }"/>
	 	<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }"/>
	 </form>
	 <!-- 클라이언트 입력 -->
	<!-- 검색 폼 -->
	<div id="inputUI" class="col-12">
		<div class="d-flex justify-content-end align-items-stretch">
			<div>
			<select id="searchType" class="form-control w-100" name="searchType" >
		       <option value="">선택</option> 
		       <option value="name"
		       ${'name' eq searchVO.searchType?"selected":"" }>이름</option>
		       <option value="stuCode"
		       ${'stuCode' eq searchVO.searchType?"selected":"" }>학번</option>
		       <option value="schCode"
		       ${'schCode' eq searchVO.searchType?"selected":"" }>학부명</option>
		       <option value="schName"
		       ${'schName' eq searchVO.searchType?"selected":"" }>학과명</option>
		    </select>
		    </div>
		    <div>
		    <input type="text" name="searchWord" onkeypress="enterkey();" class="form-control w-100" value="${param.searchWord}"> <!-- el 예시를 위해 null뜨게 내비둠 -->
		    </div>
		    <div>
		    <button type="button" id="searchBtn" class="btn waves-effect waves-light btn-info">검색</button>
			</div>
			<div >
			    <button type="button" class="btn waves-effect waves-light btn-success ml-1" id="printBtn"><i class="icon-printer"></i>출력</button>
			</div>   
		</div>
	</div>
	</div>
	<div class="row"> 	
		<div class="col-12" id="printDiv" > 	
			<table class="table text-center w-100">
				<thead class="thead-light">
					<tr>
						<th class="font-weight-bold">지급번호</th>
						<th class="font-weight-bold">장학코드</th>
						<th class="font-weight-bold">이름</th>
						<th class="font-weight-bold">학번</th>
						<th class="font-weight-bold">지급날짜</th>
						<th class="font-weight-bold">장학금명</th>
						<th class="font-weight-bold">금액</th>
						<th class="font-weight-bold">승인여부</th>
					</tr>
				</thead>
				<tbody id="listBody">
					<c:set var="schList" value="${pagingVO.dataList }"/>
					<c:if test="${not empty schList }">
						<c:forEach items="${schList }" var="sch">
								<tr >
									<td>${sch.sfundNo}</td>
									<td>${sch.scharCode}</td>
									<td>${sch.memberVO.memName }</td>
									<td onClick="schDetail(${sch.sfundNo})"><a href="#">${sch.sfundStudent}</a></td>
									<td>${sch.sfundGetdate}</td>
									<td>${sch.schVO.schName}</td>
									<td><fmt:formatNumber value="${sch.sfundValue }" pattern="#,###" />원</td>
									<td>${sch.codeVO.codeName}</td>
								</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty schList }">
						<tr>
							<td colspan="8">수혜 정보가 없음.</td>
						</tr>
					</c:if>
				</tbody>
				 <tfoot>
			      <tr>
			         <td colspan="7">
			            <!-- ============================================================== -->
						<!--페이징 -->
						<!-- ============================================================== -->
						<div class="col-12">
							<div class="dataTables_paginate paging_simple_numbers"
								id="pagingArea">
								<ui:pagination paginationInfo="${paginationInfo }"
									jsFunction="pageLinkNumber" type="bsNumber" />
							</div>
						</div>
			         </td>
			      </tr>
			   </tfoot>
			</table>
		</div>
	</div>
</div>

<!-- /.modal -->
<!-- 신청 상세 내역 modal 부분 -->
<div class="modal fade" id="schDetail" tabindex="-1"
	role="dialog" aria-labelledby="scrollableModalTitle" aria-hidden="true">
	<div class="modal-dialog modal-lg" >
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title font-weight-bold" id="scrollableModalTitle" >수혜내역 상세</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
						<table class="table" id="viewTable">
							<tbody id="modalBody">
							</tbody>
						</table>
			</div>
			<div class="modal-footer">
				<button type="button"
					class="btn waves-effect waves-light btn-secondary px-4 py-2 mx-2"
					data-dismiss="modal">닫기</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript">

// $(function(){
// 	$.ajax({
// 		url : "${cPath}/admin/getAllScholarSemester.do"
// 			,type : "get"
// 			,dataType : "json"
// 			,success : function(data){
// 				makeSmstSelect(data);
// 			}
// 	})
	
// });

// function makeSmstSelect(data){
// 	let smstList = data;
// 	var html = "<option>선택</option>";
// 	for(var i = 0 ; i < data.smstList.length ; i++){
// 		html += '<option value="'+smstList[i].sfundSmst+'">'+smstList[i].sfundSmst+'</option>';
// 	}
// 	$("#smst").html(html);
	
// }

function enterkey(){
	 if ( event.which == 13 ) {
	        $('#searchBtn').click();
	        return false;
	 }
}

   let listBody = $("#listBody");
   
   listBody.on("click", "tr", function(){
		let board = $(this).find('td:first').text();
		console.log(board);
	});
   
// === 검색 ===
	let searchForm = $("#searchForm");
// == 페이징 ==
	function pageLinkNumber(event){
		event.preventDefault();
		let page = $(event.target).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;
	}


   $("#searchBtn").on("click", function() {
	
   let inputs = $(this).parents("div#inputUI").find(":input[name]");
   $(inputs).each(function(index, input) {
      let name = $(this).attr("name");
      let value = $(this).val();
      let hidden = searchForm.find("[name='"+name+"']");
      hidden.val(value);
   });
   searchForm.submit();
});
 

function schDetail(sfundNo){
	$("#schDetail").find("#modalBody").load("${cPath}/admin/"+sfundNo+"/scholarshipDetail.do",function(){
		$("#schDetail").modal("show");
	});
};   
 

// 프린트 
$("#printBtn").on("click",function(){
	var con_test = confirm("해당 페이지를 출력하시겠습니까?");
	if(con_test == true){
	    var initBody = document.body.innerHTML;
	    window.onbeforeprint = function () {
	       document.body.innerHTML = document.getElementById("printDiv").innerHTML;
	    }
	    window.onafterprint = function () {
	       window.location.reload()
	       
	    }
	    window.print();
	}
	else if(con_test == false){
	  return false;
	}
	
}); 

</script>