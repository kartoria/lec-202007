<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui"%>
<c:set var="pagingVO" value="${paginationInfo.pagingVO }" />
<div class="container-fluid border  px-3">	
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 등록금 > 분할 납부 신청</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">분할 납부 신청</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-1 ml-2">분할 납부 신청내역 조회/승인 할 수 있습니다.</h5>
		</div>
	</div>
    <div class="table-responsive">
		<div id="zero_config_wrapper"
          class="dataTables_wrapper container-fluid dt-bootstrap4">
			<div class="row">
				<div class="col-sm-6">
                	<div id="zero_config_filter" class="dataTables_filter">
                    	<div class="input-group">
                        	<select name="searchType2" class="custom-select mr-sm-2" id="inlineFormCustomSelect1">
							<c:if test="${not empty codeList }">
								<option value="" selected="">단과대학를 선택하세요</option>
                           	<c:if test="${not empty pagingVO.searchVO.searchType2 }">
								<option selected="selected" value="${pagingVO.searchVO.searchType2 }">${pagingVO.searchVO.searchType2 }</option>
                    		<c:forEach items="${codeList }" var="code">
								<c:if test="${code.codeName ne pagingVO.searchVO.searchType2}">
                              		<option value="${code.codeName }">${code.codeName }</option>
                     			</c:if>
                            </c:forEach>
							</c:if>
							<c:if test="${empty pagingVO.searchVO.searchType2 }">
							<c:forEach items="${codeList }" var="code">
                                	<option value="${code.codeName }">${code.codeName }</option>
                              </c:forEach>
							</c:if>
							</c:if>
							</select>
	                        <select name="searchType" class="custom-select mr-sm-2" id="inlineFormCustomSelect2">
	                        	<option value=''>학과를 선택하세요</option>
	                        	<c:if test="${not empty pagingVO.searchVO.searchType }">
	                            		 <option selected="selected" value="${pagingVO.searchVO.searchType }">${pagingVO.searchVO.searchType }</option>
	                             </c:if>
	                        </select>
                  		</div>
                  	</div>
             	</div> 
             	<div class="col-sm-6">
               		<button type="button" class="btn waves-effect waves-light btn-light ml-3 float-right" id="printBtn" style="width: 15%; margin-left: 10px;"><i class="fas fa-print"></i>&nbsp;출력</button>
             	</div>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-12" id="printDiv">
				<div id="acceptedHeader" class="text-center"><h1>분할 납부 신청 내역</h1></div>
				<table id="zero_config"
					class="table">
					<thead class="thead-light ">
						<tr class="text-center text-dark">
							<th class="font-weight-bold">순번</th>
                            <th class="font-weight-bold">이름</th>
                            <th class="font-weight-bold">학번</th>
                            <th class="font-weight-bold">개인정보</th>
<!--                             <th class="font-weight-bold">신청 허가</th> -->
                         </tr>
					</thead>
					<tbody>
					<c:set var="memberList" value="${pagingVO.dataList }"/>
						<c:if test="${not empty memberList }">
						<c:set var="i" value="0" />
							<c:forEach items="${memberList }" var="member" varStatus="status">
								<c:set var="i" value="${i+1 }" />
								<c:set var="trId" value="trId${i }" />
								<tr class="text-center " id="${trId }">
									<td style="width:25%">${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
	                                <td style="width:25%">${member.memName }</td>
	                                <td style="width:25%">${member.memId }</td>
	                                <td style="width:25%">
										<a href="#" onclick="window.open('${pageContext.request.contextPath }/profile/profileList.do?memId=${member.memId }','new','scrollbars=yes,resizable=no width=1000px height=1000px, left=0,top=0');return false">
	                                    <i data-feather="user" class="feather-icon"></i></a>
	                                 </td>
<!-- 	                                 <td style="width:20%"> -->
<%-- 	                                    <button type="button" onclick="update('${member.memId }','success','${trId }')" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2">접수</button> --%>
<%-- 	                                    <button type="button" onclick="update('${member.memId }','faile','${trId }')" class="btn waves-effect waves-light btn-danger px-4 py-2 mx-2">반려</button> --%>
<!-- 	                                 </td> -->
								</tr>
							</c:forEach>
						</c:if>
                        <c:if test="${ empty memberList }">
                        	<tr class="text-center ">
	                        	<td colspan="5" ><h4>신청자가 없습니다.</h4></td>
                        	</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div id="pagingArea">
			<ui:pagination paginationInfo="${paginationInfo }"
				jsFunction="pageLinkNumber" type="bsNumber" />
		</div>
	</div>
</div>
<!-- Excel Modal -->
<form id="searchForm2">
	<input type="hidden" name="page" /> 
	<input type="hidden" id="searchTypeHid"
		name="searchType" value="${pagingVO.searchVO.searchType }" />
	<input type="hidden" id="searchTypeHid2"
		name="searchType2" value="${pagingVO.searchVO.searchType2 }" />
</form>
<script type="text/javascript">
$("#acceptedHeader").hide();
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
	$("#searchBtn2").on("click", function() {
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
$("#inlineFormCustomSelect1").change(function(){
	$("#searchTypeHid2").val(this.value);
		$.ajax({
			url : "${cPath}/admin/adminDepNameList.do",
			data : {"codeName":this.value},
			method : "post",
			dataType : "json",
			success : function(resp) {
				if (resp.departmentList !=null) {
					$("#inlineFormCustomSelect2").find("option").remove().end().append("<option value=''>학과를 선택하세요</option>").append("<option value=''>전체 학과</option>");
					$.each(resp.departmentList, function(i){
						$("#inlineFormCustomSelect2").append("<option value='"+resp.departmentList[i].depName+"'>"+resp.departmentList[i].depName+"</option>");
					});
				}
			},
			error : function(xhr) {
				console.log(xhr);
			}
		});
});
$("#inlineFormCustomSelect2").change(function(){
	$("#searchTypeHid").val(this.value);
	searchForm.submit();
});

function update(memId,resultFlag,trId){
	$.ajax({
		url : "${cPath}/admin/adminTuitionUpdate.do",
		method : "post",
		dataType : "json",
		data : {
				"resultFlag":resultFlag ,
				"memId":memId
				},
		success : function(resp) {
			if(resp.resultText =='OK'){
				$("#"+trId).remove();
				alert("승인 처리 되었습니다.");		
			}
			else if(resp.resultText =='Fail'){
				$("#"+trId).remove();
				alert("반려 처리 되었습니다.");		
			}
			else if(resp.resultText =='NotOK'){
				alert("승인 처리 실패 하였습니다.");		
			}
			else if(resp.resultText =='NotFail'){
				alert("반려 처리 실패 하였습니다.");		
			}
		},
		error : function(xhr) {
			console.log(xhr);
		}
	});
}

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