<%--
@author 조예슬
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<link rel="stylesheet" href="${cPath}/css/admin/lecture.css">
<title>과목 조회</title>
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 교과목관리 > 과목조회
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-admin p-0">
		<div class="col-12">
			<h2 class="font-weight-bold">과목 조회</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">과목 신규등록/조회/수정 할 수 있습니다.</h5>
		</div>
	</div>
<!-- 서버로 전송 -->
<form id="searchForm"  action="${cPath }/admin/subjectSearchList.do">
   <input type="hidden" name="screenSize" value="10">
   <input type="hidden" name="page"/>
   <input type="hidden" name="college"/>
   <input type="hidden" name="department"/>
   <input type="hidden" name="subDetail"/>
   <input type="hidden" name="searchType"/>
	<input type="hidden" name="searchWord" />
</form>
 <!-- 클라이언트 입력 -->
	<!-- 검색 폼 -->
	<div class="row mb-3">
		<div class="col-12 pt-4 pl-3 mg-3" id="inputUI">
		<form class="form-inline" >
			<div>
			<h4 class="font-weight-bold d-inline">단과대</h4>
			<select class="form-control" id="college" name="college"
				onChange="changeCollege()" required>
				<option value="">선택</option>
			</select>
			</div>
			<div>
			<h4 class="font-weight-bold d-inline">학과</h4>
			<select class="form-control" id="department" name="department" required>
				<option value="">선택</option>
			</select>
			</div>
			<div>
			<h4 class="font-weight-bold d-inline">이수구분</h4>
			<select class="form-control" id="subDetail" name="subDetail" required>
				<option value="">선택</option>
				<option value="교필">교필</option>
				<option value="전필">전필</option>
			</select>
			</div>
			<div id="searchDiv">
			<h4 class="font-weight-bold d-inline">검색</h4>
			<select id="searchType" class="form-control" name="searchType" >
		       <option value="">전체</option> 
		       <option value="subCode"
		       ${'lecCode' eq searchVO.searchType?"selected":"" }>과목코드</option>
		       <option value="subName"
		       ${'ledName' eq searchVO.searchType?"selected":"" }>과목명</option>
		    </select>
		    <input type="text"  name="searchWord" onkeypress="enterkey();" class="form-control" value="${param.searchWord}"> <!-- el 예시를 위해 null뜨게 내비둠 -->
		    <button type="button" id="searchBtn" class="btn waves-effect waves-light btn-info">검색</button>
			</div>
		</form>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-12 text-right">
			<a class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2" href="${cPath}/admin/insertSubjectForm.do">신규등록</a>
		</div>
	</div>
<table class="table table-sm text-center">
	<thead class="bg-primary text-white">
		<tr>
			<th>단과대</th>
			<th>학과명</th>
			<th>과목코드</th>
			<th>과목명</th>
			<th>이수구분</th>
			<th>학점</th>
			<th>개설년도</th>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
	 <tfoot>
      <tr>
         <td colspan="7">
            	<!-- ============================================================== -->
			<!--페이징 -->
			<!-- ============================================================== -->
			<div class="col-sm-10">
				<div class="dataTables_paginate paging_simple_numbers"
					id="pagingArea">
				</div>
			</div>
         </td>
      </tr>
   </tfoot>
</table>
</div>
<script type="text/javascript">
$(function(){
	$.ajax({
		url : "${cPath }/admin/lecCollegeList.do"
		,method : "get"
		,dataType : "json"
		,success : function(data){
			makeCollegeAndSmstSelect(data);
		}
	});
});
function makeCollegeAndSmstSelect(data){
	let collegeList = data.colList;
	let html = '<option value>학부선택</option>';
	for(let i = 0 ; i < collegeList.length ; i++){
		html += '<option value='+collegeList[i].code+' >'+collegeList[i].codeName+'</option>';
	}
	$("#college").html(html);
	
}
function changeCollege(){
	let collegeList = document.getElementById("college");
	let college = collegeList.options[collegeList.selectedIndex].value;
	$.ajax({
		url : "${cPath}/admin/lecSelectCollege.do?college="+college
			,type : "get"
			,dataType : "json"
			,success : function(data){
				makeDepartSelect(data);
			}
	})
}
function makeDepartSelect(data){
	let department = data;
	var html = '<option value>학과선택</option>';
	for(var i = 0 ; i < department.length ; i++){
		html += '<option value='+department[i].depNo+' >'+department[i].depName+'</option>';
	}
	$("#department").html(html);
	
}


let listBody = $("#listBody");

let pagingArea = $("#pagingArea"); 

	let searchForm = $("#searchForm").ajaxForm({
		dataType:"json",
		resetForm:true,
		success:function(resp){
			console.log(resp);
		  	let subList = resp.pagingVO.dataList;
		  	let pagingHTML = resp.pagingVO.pagingHTML;
		  	let trTags = [];
		  	if(subList.length>0){
		  		$(subList).each(function(idx, sub){
		  			trTags.push(
		  				$("<tr>").append(
		  					$("<td>").text(sub.codeName).addClass("text-left pl-5")		
		  					,$("<td>").text(sub.depName).addClass("text-left pl-5")
		  					, $("<td>").text(sub.subCode)		
		  					, $("<td class='text-primary'>").html($("<a>").attr("href","${cPath}/admin/subjectView.do?subCode="+sub.subCode).text(sub.subName)).addClass("text-left pl-5")	
		  					, $("<td>").text(sub.subDetail)	
		  					, $("<td>").text(sub.subCredit)	
		  					, $("<td>").text(sub.subDate)	
		  				).data("subject",sub)		
		  			);
		  		});
		  	}else{
		  		trTags.push(
			  		$("<tr class='text-center'>").html(
			  			$("<td colspan='7'>").text("검색 결과 없음.")
			  		)
		  		);
			}
		  	listBody.html(trTags);
		  	pagingArea.html(pagingHTML);
	  	
		} 	
	}).clearForm();   
	searchForm.submit();


pagingArea.on("click", "a", function(event) {
   event.preventDefault(); 
   let page = $(this).data("page");
   searchForm.find("[name='page']").val(page);
   searchForm.submit();
   return false;
});	

let searchBtn= $("#searchBtn").on("click", function() {
   searchForm.find(":input").val("");
   let inputs = $(this).parents("div#inputUI").find(":input[name]");
   $(inputs).each(function(index, input) {
      let name = $(this).attr("name");
      let value = $(this).val();
      let hidden = searchForm.find("[name='"+name+"']");
      hidden.val(value);
   });
   searchForm.submit();
});
	
	
</script>
