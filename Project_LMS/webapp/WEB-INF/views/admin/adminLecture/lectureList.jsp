<%--
@author 조예슬
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<link rel="stylesheet" href="${cPath}/css/admin/lecture.css">
<title>개설 강의 조회</title>
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 교과목관리 > 강의관리
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-admin p-0">
		<div class="col-12">
			<h2 class="font-weight-bold">강의 관리</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">강의 조회/수정/폐강처리를 할 수 있습니다.</h5>
		</div>
	</div>
 <!-- 클라이언트 입력 -->
	<!-- 검색 Div-->
	<div class="row mb-3 mt-3">
		<div class="col-12 pt-4 pl-3" id="inputUI">
		<form class="form-inline"  >
			<div>
			<h4 class="font-weight-bold d-inline">개설학기</h4>
			<select class="form-control" name="smst" id="lecSmst" required>
				<option value="">선택</option>
			</select>
			<h4 class="font-weight-bold d-inline">단과대</h4>
			<select class="form-control" id="college" name="college"
				onChange="changeCollege()" required>
				<option value="">선택</option>
			</select>
			</div>
			<div>
			<h4 class="font-weight-bold d-inline">학과</h4>
			<select class="form-control" id="department" name="department" onChange="changeDepartment()" required>
				<option value="">선택</option>
			</select>
			</div>
			<div>
			<h4 class="font-weight-bold d-inline" >교수</h4>
			<select class="form-control" name="professor" id="proList" required>
				<option value="">선택</option>
			</select>
			</div>	
			<div>
			<h4 class="font-weight-bold d-inline" >폐강여부</h4>
			<select class="form-control" name="lecDelete" id="lecDelete" >
				<option value="">선택</option>
				<option value="Y">폐강</option>
				<option value="N">폐강제외</option>
			</select>
			</div>	
			<div id="searchDiv" class="pt-2">
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
	<table class="table table-sm mt-5 text-center">
		<thead class="bg-primary text-white">
			<tr>
				<th>강의번호</th>
				<th>과목코드</th>
				<th>강의명</th>
				<th>교수코드</th>
				<th>교수이름</th>
				<th>정원</th>
				<th>수강인원</th>
				<th>개설학년</th>
				<th>개설학기</th>
				<th>단과대</th>
				<th>학과명</th>
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
				<div class="col-sm-12 align-items-center">
					<div class="dataTables_paginate paging_simple_numbers"
						id="pagingArea">
					</div>
				</div>
	         </td>
	      </tr>
	   </tfoot>
	</table>
</div>
<!-- 서버로 전송 -->
<form id="searchForm"  action="${cPath}/admin/lectureSearchList.do">
   <input type="hidden" name="screenSize" value="10">
   <input type="hidden" name="page"/>
   <input type="hidden" name="smst"/>
   <input type="hidden" name="college"/>
   <input type="hidden" name="department"/>
   <input type="hidden" name="professor"/>
   <input type="hidden" name="lecDelete"/>
   <input type="hidden" name="searchType"/>
	<input type="hidden" name="searchWord" />
</form>
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
	
	let smstList = data.smstList;
	let html2 = '<option value>학기선택</option>';
	for(let i = 0 ; i < smstList.length ; i++){
		html2 += '<option value='+smstList[i].lecSmst+' >'+smstList[i].lecSmst+'학기</option>';
	}
	$("#lecSmst").html(html2);
	
	
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

function changeDepartment(){
	let depList = document.getElementById("department");
	let depNo = depList.options[depList.selectedIndex].value;
	$.ajax({
		url : "${cPath}/admin/lecSelectDepartment.do?depNo="+depNo
			,type : "get"
			,dataType : "json"
			,success : function(data){
				makeProfessorSelect(data);
			}
	})
}

function makeProfessorSelect(data){
	let proList = data;
	let html = "<option value>지도교수</option>";
	for(let i = 0 ; i < proList.length ; i++){
		html += '<option value='+proList[i].memId+' >'+proList[i].memName+'['+proList[i].memId+']'+'</option>';
	}
	$("#proList").html(html);
	
}


let listBody = $("#listBody");

// 비동기로 처음 강의 리스트 조회
let pagingArea = $("#pagingArea"); 

	let searchForm = $("#searchForm").ajaxForm({
		dataType:"json",
		resetForm:true,
		success:function(resp){
			console.log(resp);
		  	let lecList = resp.pagingVO.dataList;
		  	let pagingHTML = resp.pagingVO.pagingHTML;
		  	let trTags = [];
		  	if(lecList.length>0){
		  		$(lecList).each(function(idx, lec){
		  			trTags.push(
		  				$("<tr>").append(
		  					$("<td>").text(lec.lecCode)		
		  					,$("<td>").text(lec.subCode)		
		  					, $("<td class='text-primary'>").html($("<a>").attr("href","${cPath}/admin/lectureView.do?lecCode="+lec.lecCode).text(lec.subName)).addClass("text-left pl-5")
		  					, $("<td>").text(lec.memId)		
		  					, $("<td>").text(lec.memName)	
		  					, $("<td>").text(lec.lecFull+"명")	
		  					, $("<td style='text-align:center'>>").text(lec.lecNmt+"명")	
		  					, $("<td style='text-align:center'>").text(lec.lecGrd+"학년")	
		  					, $("<td>").text(lec.lecSmst)	
		  					, $("<td>").text(lec.codeName)	
		  					, $("<td>").text(lec.depName)	
		  				).data("lecture",lec)		
		  			);
		  		});
		  	}else{
		  		trTags.push(
			  		$("<tr class='text-center'>").html(
			  			$("<td colspan='10'>").text("검색 결과 없음.")
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
