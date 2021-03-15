<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>	
<c:set var="pagingVO" value="${paginationInfo.pagingVO }"/>
<form id="searchForm">
    <input type="hidden" name="page"/>
    <input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType }"/>
	<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }"/>
</form>
<div class="container-fluid border px-3" style="min-height:300px">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 상담관리 > 상담 신청 현황</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">상담 신청 현황</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">학생이 상담 신청한 현황을 볼 수 있습니다.</h5>
		</div>
	</div>
	<div class="row my-3">
		<div class="input-group col-12 d-flex justify-content-end align-items-stretch">
          	<div id="inputUI"class="col-4 d-flex justify-content-end align-items-stretch">
	        	<div id="searchArea" class="input-group">
	        		<div class="form-group flex-grow-1">
			        	<select name="searchType" class="form-control w-100 h-100">
							<option value>전체</option>
							<option value="name" ${'name' eq param.searchType?"selected":""  }>이름</option>
							<option value="id" ${'id' eq param.searchType?"selected":""  }>학번</option>
						</select>
					</div>
					<div class="form-group flex-grow-1">
	               		<input id="searchInput" type="text" name="searchWord" onkeyup="enterkey()" class="form-control w-100 h-100" placeholder="검색 내용을 입력하세요.">
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
			<div class="row my-3">
				<div class="col-12">
					<table class="table">
						<thead class="thead-light">
							<tr class="text-center text-dark">
								<th class="font-weight-bold">순번</th>
								<th class="font-weight-bold">학번</th>
								<th class="font-weight-bold">이름</th>
								<th class="font-weight-bold">전화번호</th>
								<th class="font-weight-bold">전공학과</th>
								<th class="font-weight-bold">상담희망일자</th>
								<th class="font-weight-bold">상담희망시간</th>
								<th class="font-weight-bold">접수 확인</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="counList" value="${pagingVO.dataList }" />
								<c:if test="${not empty counList }">
									<c:set var="k" value="0"/>
									<c:set var="j" value="0"/>
									<c:forEach items="${counList }" var="coun" varStatus="status">
										<tr id="remove${coun.cstNo }" class="text-center">
											<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
											<td>${coun.cstStudent }
											</td>
											<td>${coun.memberVO.memName }</td>
											<td>${coun.memberVO.memTel }</td>
											<td>${coun.departVO.depName }</td>
											<td>${coun.cstDate }</td>
											<td>${coun.cstApptm }</td>
											<td>
												<input type="hidden" id="cstNoteAceept" value="승인완료"/>
												<button type="button" id="recognize" onClick="accept(${coun.cstNo },${coun.cstStudent } )" value="승인" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2">접수</i></button>
												<button type="button" id="negative" onClick="reject(${coun.cstNo },${coun.cstStudent })" value="반려" class="btn waves-effect waves-light btn-danger px-4 py-2 mx-2" data-toggle="modal" data-target="#centermodal">반려</button>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty counList }">
								<tr>
									<td colspan="8" class="text-center">
										<h3>신청 학생이 없습니다.</h3>
									</td>
								</tr>
								</c:if>
						</tbody>
					</table>
				</div>
				<br><br>
				<div id="pagingArea" class="col-sm-12 d-flex justify-content-center">
					<ui:pagination paginationInfo="${paginationInfo }" jsFunction="pageLinkNumber" type="bsNumber"/>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 상담 신청 거절할 때 사유 적는 모달창과 데이터 전달 -->
<div class="modal fade" id="centermodal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myCenterModalLabel">승인 불가 사유</h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <form class="mt-3" id="rejectForm">
                    <div class="form-group">
                        <textarea class="form-control" rows="3" id="cstNote" placeholder="접수 불가 사유를 적어주세요"></textarea>
                    </div>
                </form>
            </div>
             <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            	<button type="button" id="submitBtn" class="btn btn-primary">제출</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!-- 상담 신청 접수 처리 완료시 뜨는 모달창 -->
<input type="hidden" id="modalAccptBtn"class="btn btn-secondary" data-toggle="modal" data-target="#centermodalAccpt" value="숨겨진모달">
<div class="modal fade" id="centermodalAccpt" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    </div>
		    <div class="modal-body">
		        <h5 style="text-align: center;">승인되었습니다.</h5>
		    </div>
		</div>
	</div>
</div>


<!-- 상담 신청 반려 처리 완료시 뜨는 모달창 -->
<input type="hidden" id="modalNegativetBtn"class="btn btn-secondary" data-toggle="modal" data-target="#centermodalNegative" value="숨겨진모달">
<div class="modal fade" id="centermodalNegative" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    </div>
		    <div class="modal-body">
		        <h5 style="text-align: center;">반려되었습니다.</h5>
		    </div>
		</div>
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

// 접수 승인 
function accept(cstNo,cstStu){
	console.log(cstNo);
	cstAccpt =$("#recognize").val();
	console.log(cstAccpt);
	var cstNoteAceept = $("#cstNoteAceept").val();
	console.log(cstNoteAceept);
	$.ajax({
			url : '${cPath}/lms/professor/counseling/updateApply.do'
			, method : 'post'
			, data : 
			{
				"cstAccpt" : cstAccpt
				, "cstNote" : cstNoteAceept
				, "cstNo" : cstNo
				,"cstStudent":cstStu
			}	
			, dataType : 'json'
			, success : function(resp){
				var modal = document.getElementById("modalAccptBtn").click();
				if(cstAccpt == "승인"){
					$("#remove"+cstNo).remove();
				}
// 				location.href= '${cPath}/lms/professor/counseling/apply.do';
			}
			, error : function (request, error, textStatus){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				alert(textStatus);
			}
		});
}

//접수 거부
function reject(cstNo,cstStu){
	console.log(cstNo);
	cstAccpt =$("#negative").val();
	console.log(cstAccpt);
	$("#centermodal").on('show.bs.modal', function(){
		$("#submitBtn").on("click", function(){
			var cstNote = $("#cstNote").val();
			console.log(cstNote);
			console.log(cstAccpt);	
			$.ajax({
				url : '${cPath}/lms/professor/counseling/updateApply.do'
				, type : 'post'
				, data : 
					{
						"cstAccpt" : cstAccpt
						, "cstNote" : cstNote
						, "cstNo" : cstNo
						,"cstStudent":cstStu
					}
 				, dataType : 'json'
				, success : function(resp){
					var modal = document.getElementById("modalNegativetBtn").click();
					if(cstAccpt == "반려"){
						$("#remove"+cstNo).remove();
						$("#centermodal").modal('hide');
					}
// 					location.href= '${cPath}/lms/professor/counseling/apply.do';
				}
				, error : function (request, error){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
				}
			});
		});
	});
}
</script>