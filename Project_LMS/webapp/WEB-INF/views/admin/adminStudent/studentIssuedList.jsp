<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="pagingVO" value="${paginationInfo.pagingVO }" />
<div class="container-fluid border px-3" style="min-height:300px">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 학생관리 > 문서발급내역</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">문서발급내역</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-11">
			<h5 class="pb-1 ml-2">학생들이 발급받는 문서 내역을 조회할 수 있습니다.</h5>
		</div>
	</div>
	<div id="tmpSearchDiv" class="row my-3">
		<div class="input-group col-12 d-flex justify-content-end align-items-stretch">
			<div id="inputUI"  class="col-4 d-flex justify-content-end align-items-stretch">
			    <input type="text" name="searchWord" onkeypress="enterkey();" placeholder="문서명을 입력하세요." class="form-control w-100 h-100" value="${param.searchWord}">
	                <button id="searchBtn" type="button" class="btn waves-effect waves-light btn-primary px-4 h-100">
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search form-control-icon"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
			    </button>
			    <button type="button" class="btn waves-effect waves-light btn-light w-25 ml-1" id="printBtn"><i class="fas fa-print"></i>출력</button>
			</div>
		</div>
	</div>
	 <form id="searchForm">
	    <input type="hidden" name="page" />
	    <input type="hidden" name="searchType" value="issName" />
	 	<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }"/>
	 </form>
	<div class="row"> 	
		<div class="col-12" id="printDiv" > 	
			<table class="mainTable table">
				<thead class="thead-light">
					<tr class="text-center">
						<th class="font-weight-bold">발급번호</th>
						<th class="font-weight-bold">학번</th>
						<th class="font-weight-bold">이름</th>
						<th class="font-weight-bold">발급문서</th>
						<th class="font-weight-bold">발급일자</th>
						
					</tr>
				</thead>
				<tbody id="listBody">
					<c:set var="issuedList" value="${pagingVO.dataList }"/>
					<c:if test="${not empty issuedList }">
						<c:forEach items="${issuedList }" var="issued">
								<tr class="text-center ">
									<td class="font-weight-bold">${issued.code}-${issued.issNo} </td>
									<td class="font-weight-bold">${issued.memId}</td>
									<td class="font-weight-bold">${issued.memName}</td>
									<td class="font-weight-bold">${issued.issName}</td>
									<td class="font-weight-bold">${issued.issDate}</td>
								</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty issuedList }">
						<tr class="text-center ">
							<td colspan="4" class="font-weight-bold">발급 정보가 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
				 <tfoot>
			      <tr>
			         <td colspan="4">
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


<script type="text/javascript">


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
 

 

// 프린트 
$("#printBtn").on("click",function(){
	var con_test = confirm("해당 페이지를 출력하시겠습니까?");
	if(con_test == true){
	    var initBody = document.body.innerHTML;
	    window.onbeforeprint = function () {
	       document.body.innerHTML = document.getElementById("printDiv").innerHTML;
	    }
	    window.onafterprint = function () {
	       window.location.reload();
	       
	    }
	    window.print();
	}
	else if(con_test == false){
	  return false;
	}
	
}); 

</script>