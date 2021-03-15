<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="pagingVO" value= "${paginationInfo.pagingVO }"/>
<div class="container-fluid border px-3">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 등록금 > 등록금 납부 내역</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">등록금 납부 내역</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-11">
			<h5 class="pb-1 ml-2">등록금 납부내역을 조회할 수 있습니다.</h5>
		</div>
	</div>
	<!-- 서버로 전송 -->
	<form id="searchForm">
		<input type="hidden" name="page" />
	    <input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType }" />
	 	<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }"/>
	</form>
	<div id="tmpSearchDiv" class="row my-3">
		<div class="input-group col-12 mt-3 d-flex justify-content-end align-items-stretch">
			<div id="inputUI"  class="col-7 d-flex justify-content-end align-items-stretch">
				<select id="searchType" class="form-control col-3 h-100" name="searchType" >
					<option value="">전체</option>
					<option value="name" ${'name' eq searchVO.searchType?"selected":"" }>이름</option>
			        <option value="date" ${'date' eq searchVO.searchType?"selected":"" }>납부날짜</option>
			    </select>
		    	<input type="text" name="searchWord" placeholder="검색어를 입력하세요." onkeypress="enterkey();" class="form-control w-100 h-100" value="${param.searchWord}"> 
		    	<button type="button" id="searchBtn" class="btn waves-effect waves-light btn-info px-4 h-100">
					<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search form-control-icon"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
		    	</button>
				<button type="button" class="btn waves-effect waves-light btn-light ml-3" id="printBtn" style="width: 15%; margin-left: 10px;"><i class="fas fa-print"></i>&nbsp;출력</button>
			</div>
		<div class="col-12 mt-3" id="printDiv">
			<div id="acceptedHeader" class="text-center"><h1>등록금 납부 내역</h1></div>
			<table class="table">
				<thead class="thead-light">
					<tr class="text-center">
						<th class="font-weight-bold">순번</th>
						<th class="font-weight-bold">이름</th>
						<th class="font-weight-bold">학번</th>
						<th class="font-weight-bold">원납금액</th>
						<th class="font-weight-bold">실납입금</th>
						<th class="font-weight-bold">납부 유형</th>
						<th class="font-weight-bold">납부 완료일</th>
						<th class="font-weight-bold">납부 여부</th>
					</tr>
				</thead>
				<tbody>
            	<c:set var="vacountList" value="${pagingVO.dataList }" />
				<c:if test="${not empty vacountList }">
					<c:forEach items="${vacountList }" var="va" varStatus="status">
						<tr class="text-center">
							<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
							<td>${va.memName }</td>
							<td>${va.memId }</td>
							<td class="text-right">
								<fmt:formatNumber value="${va.payExcpect }" maxFractionDigits="3"/>원
							</td>
							<td class="text-right">
								<c:if test="${not empty va.payAmount }">
									<fmt:formatNumber value="${va.payAmount }" maxFractionDigits="3"/>원
								</c:if>
								<c:if test="${empty va.payAmount }">
									0원
								</c:if>
							</td>
							<c:choose>
								<c:when test="${va.payNumber eq 0}">
								 	<td>원금 납부</td>
								</c:when>
								<c:when test="${va.payNumber ne 0 }">
									<td>분할 납부 ${va.payNumber }차</td>
								</c:when>
							</c:choose>
							<td>
								<c:if test="${empty va.payDate }">
								-
								</c:if>
								<c:if test="${not empty va.payDate }">
									${va.payDate }
								</c:if>
							</td>
							<td>
								<c:if test="${va.payDivision eq 'N' && va.payCheck eq 'N' && va.payAmount ne 0}">
									완납
								</c:if>
								<c:if test="${va.payDivision eq 'N' && va.payCheck eq 'N' && va.payAmount eq 0}">
									미납
								</c:if>
								<c:if test="${va.payDivision eq 'Y' && va.payCheck eq 'Y' && va.payAmount eq 0 }">
									분할/미납
								</c:if>
								<c:if test="${va.payDivision eq 'Y' && va.payCheck eq 'Y' && va.payAmount ne 0 }">
									분할/완납
								</c:if>
							</td>
						</tr>
					</c:forEach>	
				</c:if>
            	</tbody>
        	</table>
		</div>
		<div id="pagingArea" class="col-sm-12 d-flex justify-content-center">
			<ui:pagination paginationInfo="${paginationInfo }" jsFunction="pageLinkNumber" type="bsNumber"/>
		</div>
	</div>
</div>
</div>
<!-- Excel Modal -->
<div class="modal fade modal-fullscreen" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Excel</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div>
                   <img id="excelImg" src="${cPath}/images/prolmsimages/stuexcel/stuExcel.jpg"/>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
               <button type="button" class="btn btn-primary">다운로드</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
$("#acceptedHeader").hide();
function pageLinkNumber(event){
	event.preventDefault();
	let page = $(event.target).data("page");
	searchForm.find("[name='page']").val(page);
	console.log(page);
	searchForm.submit();
	return false;
}

function enterkey(){
	if(window.event.keyCode==13){
		$("#searchBtn").click();
	}
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

$("#printBtn").on("click", function(){
	$("#acceptedHeader").show();
	var con_test = confirm("해당 페이지를 출력하시겠습니까?");
	if(con_test == true){
		var initBody = document.body.innerHTML;
		window.onbeforeprint = function(){
			document.body.innerHTML = document.getElementById("printDiv").innerHTML;
		}
		 window.onafterprint = function () {
		       window.location.reload();
		       
		    }
		    window.print();
	} else if(con_test == false) {
		$("#acceptedHeader").hide();
		return false;
	}
	$("#acceptedHeader").hide();
});
</script>