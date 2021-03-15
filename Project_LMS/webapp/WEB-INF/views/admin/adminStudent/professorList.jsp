<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<link rel="stylesheet" href="${cPath}/css/admin/student.css">    
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 학생관리 > 교수관리
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-admin p-0 my-2">
		<div class="col-12">
			<h2 class="font-weight-bold">교수 관리</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">교수 신규등록/조회/수정 할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row mb-3 mt-3">
		<div class="selectDiv col-12">			
			<form class="form-inline" id="searchForm" method="post" action="${cPath}/admin/professorSearchList.do">
				<div>
				<h4 class="font-weight-bold d-inline" >단과대</h4>
				<select class="form-control" id="college" name="college"
					onChange="changeCollege()" required>
					<option value="">단과대</option>
				</select>
				</div>
				<div>
				<h4 class="font-weight-bold d-inline" >학과</h4>
				<select class="form-control" id="department" name="department" required>
					<option value="">학과선택</option>
				</select>
				</div>
				<div>
				<h4 class="font-weight-bold d-inline" >근무상태</h4>
				<select class="form-control" id="state" name="state" required>
					<option value="">상태</option>
				</select>
				</div>
				<div class="p-2">
				<h4 class="font-weight-bold d-inline" >검색</h4>
				<select id="searchType" class="form-control" name="searchType" >
			       <option value="">전체</option> 
			       <option value="name"
			       ${'name' eq searchVO.searchType?"selected":"" }>이름</option>
			       <option value="stuCode"
			       ${'stuCode' eq searchVO.searchType?"selected":"" }>교번</option>
			    </select>
			    <input type="text"  name="searchWord" onkeypress="enterkey();" class="form-control" value="${param.searchWord}"> <!-- el 예시를 위해 null뜨게 내비둠 -->
			    <button type="button" id="searchBtn" class="btn waves-effect waves-light btn-info">검색</button>
				</div>
			</form>
		</div>
	</div>
	<div class="row mt-3">
		<div class="col-12">
			<table class="totalTable table-sm text-center" >
				<thead>
					<tr>
						<th class="font-weight-bold text-dark">통계</th>
						<th class="font-weight-bold text-dark">합계</th>
						<th class="font-weight-bold text-dark">재직</th>
						<th class="font-weight-bold text-dark">휴직</th>
						<th class="font-weight-bold text-dark">퇴사</th>
						<th class="font-weight-bold text-dark">퇴직</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>계</td>
						<td id="total"></td>
						<td id="ingCnt"></td>
						<td id="restCnt"></td>
						<td id="leftCnt"></td>
						<td id="endCnt"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row mb-3 mt-3">
		<div class="listTable col-12">
			<table class="table table-sm text-center">
				<thead class="thead-light">
					<tr>
						<th class="fixedHeader bg-primary text-white font-weight-bold">학과</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">교번</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">이름</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">전화번호</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">근무상태</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">입사년도</th>
					</tr>
				</thead>
				<tbody id="listBody">
				</tbody>
			</table>
		</div>
	</div>
	<div class="row mb-3 mt-3">
		<div class="col-12 text-right">
			<a id="downExcelJxls" class="btn btn-success ml-4" href="#" data-search='${searchVO }'><i class="fas fa-file-excel"> excel 다운로드</i></a>
			<a class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2" href="${cPath}/admin/insertProfessorForm.do">신규등록</a>
		</div>
	</div>
</div>

<script>
$(function(){
	// 학부, 학적 코드 조회
	$.ajax({
		url : "${cPath }/admin/getCollegeAndState.do"
		,method : "get"
		,dataType : "json"
		,success : function(data){
			makeCollegeAndStateSelect(data);
		}
	});
	
	$("#searchBtn").trigger("click");
	
});

// 학부, 학적상태 selectBox 생성
function makeCollegeAndStateSelect(data){
	let collegeList = data.collegeList;
	let stateList = data.stateList;
	let html = '<option value >학부선택</option>';
	for(let i = 0 ; i < collegeList.length ; i++){
		html += '<option value='+collegeList[i].code+' >'+collegeList[i].codeName+'</option>';
	}
	$("#college").html(html);
	
	let html2 = '<option value >상태</option>';
	for(let i = 0 ; i < stateList.length ; i++){
		html2 += '<option value='+stateList[i].code+' >'+stateList[i].description+'</option>';
	}
	$("#state").html(html2);
	
	
}

//학부 선택시
function changeCollege(){
	let collegeList = document.getElementById("college");
	let college = collegeList.options[collegeList.selectedIndex].value;
	$.ajax({
		url : "${cPath}/admin/getDepartment.do?college="+college
			,type : "get"
			,dataType : "json"
			,success : function(data){
				makeDepartSelect(data);
			}
	})
}

// 학과 selectBox 생성
function makeDepartSelect(data){
	let department = data;
	var html = '<option value >학과선택</option>';
	for(var i = 0 ; i < department.length ; i++){
		html += '<option value='+department[i].depNo+' >'+department[i].depName+'</option>';
	}
	$("#department").html(html);
	
}


//검색 버튼 클릭시
$("#searchBtn").on("click",function(e){
	e.preventDefault();
	
	$.ajax({
		url : "${cPath}/admin/professorSearchList.do"
		,method : "post"
		,data : $("#searchForm").serialize()
		,dataType : "json"
		,success : function(resp){
			let memList = resp;
			// 검색된 총 학생 합계
			$("#total").text(memList.length);
			// 재직수
			let ingCnt=0
			// 휴직수
			let restCnt=0
			// 퇴사수
			let leftCnt=0
			// 퇴직수
			let endCnt=0
			
			let trTags = '';
			if(memList.length>0){
				$(memList).each(function(idx, mem){
					let memTel = mem.memTel;
					let formatNum = '';
					if(memTel==null){
						memTel = "";
					}else if(memTel.length==11){
			            formatNum = memTel.replace(/(\d{3})(\d{4})(\d{4})/, '$1-****-$3');
					}
					trTags += '<tr>';
					trTags +=   '<td>'+mem.depName+'<input type="hidden" id="memId" name="memId" value="'+mem.memId+'"/></td>';
					trTags +=   '<td><a href="${cPath}/admin/professorView.do?memId='+mem.memId+'">'+mem.memId+'</a></td>';
					trTags +=   '<td>'+mem.memName+'</td>';
					trTags +=   '<td>'+formatNum+'</td>';
					trTags +=   '<td>'+mem.description+'<input type="hidden" id="memState" name="memState" value="'+mem.memState+'"/></td>';
					trTags +=   '<td>'+(mem.memAdmission ==null? "":mem.memAdmission)+'</td>';
			        trTags += '</tr>';
			        
			        // 학생 상태 합계 
			        if(mem.description=="재직"){
			        	ingCnt = ingCnt+1;
			        }else if(mem.description=="휴직"){
			        	restCnt = restCnt+1;
			        }else if(mem.description=="퇴사"){
			        	leftCnt = leftCnt+1;
			        }else if(mem.description=="퇴직"){
			        	endCnt = endCnt+1; 
			        }
			        
				});
			}else{
				trTags += '<tr><td colspan="5">검색 결과 없음.</td></tr>';
			}
			$("#listBody").html(trTags);
			$("#ingCnt").text(ingCnt);
			$("#restCnt").text(restCnt);
			$("#leftCnt").text(leftCnt);
			$("#endCnt").text(endCnt);
			
		}
	})
	
	return false;
});

// 교수 엑셀파일 다운
$("#downExcelJxls").on("click", function(){
	
	$("#searchForm").attr("action","${cPath}/admin/downloadProfessorListExcel.do");
	
	$("#searchForm").submit();
	
	return true;
});
</script>