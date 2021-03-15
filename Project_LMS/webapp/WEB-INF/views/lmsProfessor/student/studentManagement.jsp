<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>	
<div class="container-fluid border px-3" style="min-height:300px">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i>학생관리</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">지도학생</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">학과 학생 목록입니다.</h5>
		</div>
	</div>
	<div class="row my-3">
		<div class="input-group col-12 d-flex justify-content-end align-items-stretch">
          	<div id="inputUI"class="col-4 d-flex justify-content-end align-items-stretch">
	        	<div id="searchArea" class="input-group">
    				<div class="form-group flex-grow-1">
		       			<input type="text" type="text" name="searchWord" onkeyup="enterkey()" class="form-control  w-100 h-100" placeholder="검색 내용을 입력하세요.">
		       		</div>
		       		<div class="form-group">
		                <button id="searchBtn" type="button" class="btn waves-effect waves-light btn-primary  h-100">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search form-control-icon"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
						</button>
					</div>
					<div class="form-group">
						<a id="downExcelJxls" class="btn btn-success px-3 h-100" href="#" data-page='${paginationInfo.pagingVO.currentPage }'><i class="fas fa-print"></i> Excel</a>
					</div>
	            </div>
			</div>
       </div>
	</div>
	
	<c:set var="pagingVO" value="${paginationInfo.pagingVO }" />
			<div class="row my-3 d-flex justify-content-center align-items-center">
				<div class="col-10">
					<table class="table">
						<thead class="thead-light">
							<tr class="text-center text-dark">
								<th class="font-weight-bold">학번</th>
								<th class="font-weight-bold">이름</th>
								<th class="font-weight-bold">개인정보</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="memberList" value="${pagingVO.dataList }"/>
                         			<c:if test="${not empty memberList }">
                         			 	<c:forEach items="${memberList }" var="member">
									<tr class="text-center " >
										<td class="w-33">${member.memId }</td>
										<td class="w-33">${member.memName }</td>
										<td class="w-33">
											<a href="#" onclick="window.open('${pageContext.request.contextPath }/lms/professor/student/profile.do?memId=${member.memId}','new','scrollbars=yes,resizable=no width=1000px height=1000px, left=0,top=0');return false">
		                                    	<i data-feather="user" class="feather-icon"></i>
		                                    </a>
	                                    </td>
									</tr>
								</c:forEach>
                         			</c:if>
                         			<c:if test="${ empty memberList }">
								<tr class="text-center " >
									<td colspan="3">
										<h3>지도 학생이 없습니다.</h3>
                                    </td>
								</tr>
                         			</c:if>
						</tbody>
					</table>
				</div>
			</div>
			 <div id="pagingArea" class="col-sm-12 d-flex justify-content-center">
				<ui:pagination paginationInfo="${paginationInfo }" jsFunction="pageLinkNumber" type="bsNumber" /> 
			</div>
	</div>
<form id="searchForm2" action="${cPath}/lms/professor/student/list.do">
	<input type="hidden" id="pageHidden" name="page" /> 
	<input type="hidden" name="searchWord"
		value="${pagingVO.searchVO.searchWord }" />
</form>

<script type="text/javascript">
function pageLinkNumber(event) {
	let tableBodyText="";
	event.preventDefault();
	let page = $(event.target).data("page");
	searchForm.find("[name='page']").val(page);
	let pagingTable = "";
	
	searchForm.submit();

	return false;
}
let searchForm = $("#searchForm2");
$("#searchBtn").on("click", function() {
	let inputs = $(this).parents("div#inputUI").find(":input[name]");
	$(inputs).each(function(index, input) {
		let name = $(this).attr("name");
		let value = $(this).val();
		let hidden = searchForm.find("[name='" + name + "']");
		hidden.val(value);
	});
	let tableBodyText2="";
	
	searchForm.submit();

});	

// $("#downExcelJxls").on("click", function(){
// 	let id = $(this).prop("id");
// 	let page = $(this).data("page")
// 	let requestURL = "${cPath}/lms/professor/student/downloadExcel.do?page="+page; 
// 	if(id.endsWith("Jxls")){
// 		requestURL += "&jxls=true";
// 	}
// 	$(this).attr("href", requestURL);
// 	return true;
// });

// 학생 엑셀 파일 다운
$("#downExcelJxls").on("click", function(){
	searchForm.attr("action","${cPath}/lms/professor/student/downloadExcel.do");
	searchForm.submit();
	searchForm.attr("action","${cPath}/lms/professor/student/list.do");
	return true;
});

</script>
