<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<link rel="stylesheet" href="${cPath}/css/admin/student.css">
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 학생관리 > 학생관리
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-admin p-0 my-2">
		<div class="col-12">
			<h2 class="font-weight-bold">학생 관리</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">학생 정보를 조회/수정 할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row">
		<div class="selectDiv col-12">
		<input type="hidden" name="searchVO" value="${searchVO}">			
			<form class="form-inline" id="searchForm" method="post" action="${cPath }/admin/studentSearchList.do">
				<div>
				<h4 class="font-weight-bold d-inline" >단과대</h4>
				<select class="form-control" id="college" name="college"
					onChange="changeCollege()" required>
					<option value="">단과대</option>
					<c:forEach items="${collegeList}" var="college">
						 	<option value="${college.code}" <c:out value="${searchVO.college eq college.code ? 'selected' :'' } "/>>${college.codeName}</option>
					</c:forEach>
				</select>
				</div>
				<div>
				<h4 class="font-weight-bold d-inline" >학과</h4>
				<input type="hidden" name="searchDep" value="${searchVO.department}" >
				<select class="form-control" id="department" name="department" required>
					<option value="">학과선택</option>
				</select>
				</div>
				<div>
				<h4 class="font-weight-bold d-inline" >현학적</h4>
				<select class="form-control" id="state" name="state" required>
					<option value="">상태</option>
					<c:forEach items="${stateList}" var="state">
						 	<option value="${state.code}" <c:out value="${searchVO.state eq state.code ? 'selected' :'' } "/>>${state.codeName}</option>
					</c:forEach>
				</select>
				</div>
				<div>
				<h4 class="font-weight-bold d-inline" >현학기</h4>
				<select class="form-control" name="grade" id="grade" required>
					<option value="">학기</option>
					<c:forEach var="i" begin="1" end="8" step="1">
						<option value="${i}" <c:out value="${searchVO.grade eq i ? 'selected' :'' } "/>>${i} 학기</option>
					</c:forEach>
				</select>
				</div>	
				<div class="p-2">
				<h4 class="font-weight-bold d-inline" >검색</h4>
				<select id="searchType" class="form-control" name="searchType" >
			       <option value="">전체</option> 
			       <option value="name"
			       ${'name' eq searchVO.searchType?"selected":"" }>이름</option>
			       <option value="stuCode"
			       ${'stuCode' eq searchVO.searchType?"selected":"" }>학번</option>
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
						<th class="font-weight-bold text-dark">합계(명)</th>
						<th class="font-weight-bold text-dark">재학(명)</th>
						<th class="font-weight-bold text-dark">휴학(명)</th>
						<th class="font-weight-bold text-dark">제적(명)</th>
						<th class="font-weight-bold text-dark">졸업(명)</th>
					</tr>
				</thead>
				<tbody id="TotalBody">
				</tbody>
			</table>
		</div>
	</div>
	<div class="row mb-3 mt-3">
		<div class="listTable col-12">
			<table class="table table-sm text-center">
				<thead class="thead-light">
					<tr>
						<th class="fixedHeader bg-primary text-white font-weight-bold"><input type="checkbox" id="checkall"></th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">학과</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">학번</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">학년</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">이름</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">상태</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">입학년도</th>
					</tr>
				</thead>
				<tbody id="listBody">
				</tbody>
			</table>
		</div>
	</div>
	<div class="row mb-3 mt-3">
		<div class="col-12 d-flex justify-content-end align-items-stretch">
			<div class="mr-4">
			<select class="form-control d-inline" id="memState" name="memState" required>
				<option value="">학적</option>
				<c:forEach items="${stateList}" var="state">
				 	<option value="${state.code}" <c:out value="${searchVO.state eq state.code ? 'selected' :'' } "/>>${state.codeName}</option>
				</c:forEach>
			</select>
			</div>
			<div>
  			<button type="button" id="chgBtn" class="btn waves-effect waves-light btn-warning d-inline" >일괄변경처리</button>
			<a id="downExcelJxls" class="btn btn-success ml-4" href="#" data-search='${searchVO }'><i class="fas fa-file-excel"> excel 다운로드</i></a>
  			</div>
		</div>
	</div>
</div>
<form id="chgStateForm">

</form>
<script>
$(function(){
	
	$("#searchBtn").trigger("click");
	
	let college = $("input[name='college']").val();
	if(college!=null || college!="" ){
		changeCollege();
	}
	
});

	
// }
// 체크박스 전체선택시
$("#checkall").click(function(){
	if($("#checkall").prop("checked")){
		$("input[name=chk]").prop("checked",true);
	}else{
		$("input[name=chk]").prop("checked",false);
	}
});	

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
	let searchDep = $("input[name='searchDep']").val();
	let department = data;
	var html = '<option value >학과선택</option>';
	for(var i = 0 ; i < department.length ; i++){
		html += '<option value='+department[i].depNo+'>'+department[i].depName+'</option>';
	}
	$("#department").html(html);
	
	// 검색 후 가져온 VO 로  검색조건 selected 
	for(let i = 0 ; i < department.length ; i++){
		if(searchDep==department[i].depNo){
			$("#department").val(department[i].depNo).prop("selected",true);
		}
	}
	
	
}

//검색 버튼 클릭시
$("#searchBtn").on("click",function(e){
	e.preventDefault();
	
	$.ajax({
		url : "${cPath }/admin/studentSearchList.do"
		,method : "post"
		,data : $("#searchForm").serialize()
		,dataType : "json"
		,success : function(resp){
			console.log(resp);
			let memList = resp.memList;
			let gradeTotalList = resp.gradeTotalList;
			
			let trTags = '';
			if(memList.length>0){
				$(memList).each(function(idx, mem){
					let grade =(((mem.memGrd)/2+0.5)-(((mem.memGrd)/2+0.5)%1)) ;
					
					trTags += '<tr>';
					trTags += 	'<td onclick=\'event.cancelBubble=true\'><input type="checkbox" id="chk" name="chk" value="'+mem.memId+'" '+(mem.codeName =="졸업" ? "disabled" : "")+'  /></td>';
					trTags +=   '<td>'+mem.depName+'<input type="hidden" id="memId" name="memId" value="'+mem.memId+'"/></td>';
					trTags +=   '<td><a href="#" onclick="studentView('+mem.memId+')" >'+mem.memId+'</a></td>';
					trTags +=   '<td>'+(mem.memGrd==0?"":(((mem.memGrd)/2+0.5)-(((mem.memGrd)/2+0.5)%1)))+'</td>';
					trTags +=   '<td>'+mem.memName+'</td>';
					if(mem.codeName=="졸업"){
					trTags +=   '<td class="text-danger">'+mem.codeName+'<input type="hidden" id="memState" name="memState" value="'+mem.memState+'"/></td>';
					}else if(mem.codeName=="재학"){
					trTags +=   '<td class="text-success">'+mem.codeName+'<input type="hidden" id="memState" name="memState" value="'+mem.memState+'"/></td>';
					}else if(mem.codeName=="자퇴"){
					trTags +=   '<td class="text-danger">'+mem.codeName+'<input type="hidden" id="memState" name="memState" value="'+mem.memState+'"/></td>';
					}else{
					trTags +=   '<td>'+mem.codeName+'<input type="hidden" id="memState" name="memState" value="'+mem.memState+'"/></td>';
					}
					trTags +=   '<td>'+(mem.memAdmission ==null? "":mem.memAdmission)+'</td>';
			        trTags += '</tr>';
			        
				});
				
			}else{
				trTags += '<tr><td colspan="7">검색 결과 없음.</td></tr>';
			}
			$("#listBody").html(trTags);
			
			//=====통계테이블만들기=======================================================================
			let totalTrTags = '<tr>';
				totalTrTags += '<td>계</td>';
				totalTrTags += '<td class="text-danger">'+gradeTotalList[4].totalCnt+'</td>';
				totalTrTags += '<td class="text-info">'+gradeTotalList[4].ingCnt+'</td>';
				totalTrTags += '<td class="text-info">'+gradeTotalList[4].restCnt+'</td>';
				totalTrTags += '<td class="text-info">'+gradeTotalList[4].leftCnt+'</td>';
				totalTrTags += '<td class="text-info">'+gradeTotalList[4].endCnt+'</td>';
				totalTrTags += '</tr>';
			for(let idx=0 ; idx< 4; idx++){
				totalTrTags += '<tr>';
				totalTrTags += '<td>'+(idx+1)+'학년</td>';
				totalTrTags += '<td>'+gradeTotalList[idx].totalCnt+'</td>';
				totalTrTags += '<td>'+gradeTotalList[idx].ingCnt+'</td>';
				totalTrTags += '<td>'+gradeTotalList[idx].restCnt+'</td>';
				totalTrTags += '<td>'+gradeTotalList[idx].leftCnt+'</td>';
				totalTrTags += '<td>'+gradeTotalList[idx].endCnt+'</td>';
				totalTrTags += '</tr>';
			}
			
			$("#TotalBody").html(totalTrTags);
				
		}
	})
	
	return false;
});

// 학적상태 변경 버튼 클릭시
$("#chgBtn").on("click",function(e){
	if( $("input[name=chk]:checked").length == 0 ){
	    alert("항목을 하나이상 체크하세요");
	    return;
	}
	let checkbox = $("input[name=chk]:checked");
	let memState = $("select[name=memState").val();
	
	let origin ="";
	checkbox.each(function(i){
		let tr = checkbox.parent().parent().eq(i);
		let td = tr.children();
		let inputTag = td.find("input[name=memId]");
		let memId = inputTag.val();
		// 기존 학생 상태 체크
		origin = td.find("input[name=memState]").val();
		
		var input1 = document.createElement('input');
		input1.setAttribute("type", "hidden"); 
		input1.setAttribute("class", "hiddenInput"); 
		input1.setAttribute("name", "stuList["+i+"].memId"); 
		input1.setAttribute("value",memId);
		var input2 = document.createElement('input');
		input2.setAttribute("type", "hidden"); 
		input2.setAttribute("class", "hiddenInput"); 
		input2.setAttribute("name", "stuList["+i+"].memState"); 
		input2.setAttribute("value",memState);
		$("#chgStateForm").append(input1);
		$("#chgStateForm").append(input2);
		
	})
	if(origin=="LEFT"){
		alert("이미 자퇴한 학생이 포함되어있습니다.");
		$(".hiddenInput").remove();
		return false;
	}
	
	if(origin==memState){
		alert("이미 처리된 내역입니다.");
		$(".hiddenInput").remove();
		return false;
	}
	
	$.ajax({
		url : "${cPath }/admin/updateStudentState.do"
		,method : "post"
		,data : $("#chgStateForm").serialize()
		,dataType : "json"
		,success : function(data){
			alert(data.msg);
			$("#checkall").prop("checked", false);
			$("input:checkbox[name='chk']").prop("checked", false);
			$(".hiddenInput").remove();
			$("#searchBtn").trigger("click");
		}
	})
	
	
});		

// 학생 엑셀 파일 다운
$("#downExcelJxls").on("click", function(){
	
	$("#searchForm").attr("action","${cPath}/admin/downloadStudentListExcel.do");
	
	$("#searchForm").submit();
	
	return true;
});


function studentView(memId){
	$("#searchForm").attr("action","${cPath}/admin/studentView.do?memId="+memId);
	$("#searchForm").submit();
}


</script>