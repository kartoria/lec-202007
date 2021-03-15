<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>	
<style type="text/css">
.img{
   width: 200px;
    height: 200px;
    object-fit: cover;
}
.box {
    width: 200px;
    height: 200px; 
    border-radius: 10%;
    overflow: hidden;
    margin: auto;
    
}
</style>
<c:set var="pagingVO" value= "${paginationInfo.pagingVO }"/>
<form id="searchForm">
    <input type="hidden" name="page"/>
    <input type="hidden" name="searchType" value="name"/>
	<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }"/>
</form>
<div class="container-fluid border px-3" style="min-height:300px">
	<div class="row my-3">
		<div class="col-12">
			<h3 class="ml-2">
				<i class="fas fa-home"></i> 상담 관리 > 상담 내역
			</h3>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">상담 내역</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">상담 내역을 확인할 학생을 선택하세요.</h5>
		</div>
	</div>
	<div class="row my-3">
		<div class="input-group col-12 d-flex justify-content-end align-items-stretch">
          	<div id="inputUI"class="col-3 d-flex justify-content-end align-items-stretch">
	        	<div id="searchArea" class="input-group">
	        		<div class="form-group flex-grow-1">
						<input id="searchInput" type="text" name="searchWord" onkeyup="enterkey()" class="form-control w-100 h-100" placeholder="이름을 입력하세요.">
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
	<div class="row">
		<div class="col-12">		
			<div id="tableArea">
				<div class="row">
		            <c:set var="counList" value="${pagingVO.dataList }"/>
		           	<c:if test="${not empty counList }">
		            	<c:forEach items="${counList }" var="coun">
			               	<div class="col-lg-3 col-md-6">
								<a href="${cPath}/lms/professor/counseling/detail.do?what=${coun.memberVO.memId}">
				                    <div class="card">
										<div class="card-header text-center">
							            </div>
					                   <div id="imgArea" class="box">
						                   	<c:if test="${not empty coun.memberVO.memImg }">
												<img id="img" class="img" src="${cPath }/memberImages/${coun.memberVO.memImg } "  class="dark-logo" />
											</c:if>
										  	<c:if test="${ empty coun.memberVO.memImg }">
												<img id="img" class="img" src="${cPath }/images/defaultImg.jpg" class="dark-logo" />
											</c:if>
									   </div>
									   <hr>
				                       <div class="card-body text-center">
			                            <h3 class="card-title">
								        		${coun.memberVO.memName }
								       </h3> 
				                        </div>
				                    </div>
								</a>
							<!-- Card -->
			                </div>
                        	</c:forEach>
      				    </c:if>
      				    <c:if test="${ empty counList }">
							<div class="text-center" style="width: 100%; height:200px; margin-top: 50px;">
								<h2>학생이 없습니다.</h2>
							</div>
      				    </c:if>
						<!-- column -->
		           </div>
				<!-- Row -->
	       </div>
       </div>
    </div>
   <!-- End Row -->
   	<div id="pagingArea text-center">
		<ui:pagination paginationInfo="${paginationInfo }" jsFunction="pageLinkNumber" type="bsNumber"/>
	</div>
</div>

<script type="text/javascript">
var searchForm = $("#searchForm");
function pageLinkNumber(event){
	event.preventDefault();
	let page = $(event.target).data("page");
	searchForm.find("[name='page']").val(page);
	console.log(page);
	searchForm.submit();
	return false;
}

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
