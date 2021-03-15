<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="${cPath}/css/admin/scholarship.css">
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 장학관리 > 성적 장학생 선발
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-admin p-0 my-2">
		<div class="col-12">
			<h2 class="font-weight-bold">성적 장학생 선발</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">성적을 산출하여 성적장학생을 선발 할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row mb-3 mt-3">
        <div class="col-12 pt-3 pl-3" id="searchBody">
           	<form id="searchForm" class="form-inline" action="${cPath}/admin/produceScholarshipByGrade.do" method="post">
           		<div>
	            	<h4 class="font-weight-bold d-inline" >단과대</h4>
	            	<input type="hidden" name="scoreVOCollege" value="${scoreFormVO.colleage }">
	            	<select class="form-control" id="colleage" name="colleage" onChange="changeCollege()" required>
	                       <option value="" >단과대</option>
	                </select>
                </div>
                <div>
	            	<h4 class="font-weight-bold d-inline">학과</h4>
	            	<input type="hidden" name="scoreVOdepNo" value="${scoreFormVO.depNo }">
	            	<select class="form-control" id="department" name="depNo" required>
	                       <option value="" >학과선택</option>
	                 </select>
                 </div>
                 <div>
	                 <h4 class="font-weight-bold d-inline">학년</h4>
	            	<select class="form-control" id="grade" name="memGrd" required>
	                       <option value="">학년</option>
							<option value="1" <c:out value="${scoreFormVO.memGrd eq 1 ? 'selected' :'' } "/>>
								1학년 1학기</option>
							<option value="2" <c:out value="${scoreFormVO.memGrd eq 2 ? 'selected' :'' } "/>>
								1학년 2학기</option>
							<option value="3" <c:out value="${scoreFormVO.memGrd eq 3 ? 'selected' :'' } "/>>
								2학년 1학기</option>
							<option value="4" <c:out value="${scoreFormVO.memGrd eq 4 ? 'selected' :'' } "/>>
								2학년 2학기</option>
							<option value="5" <c:out value="${scoreFormVO.memGrd eq 5 ? 'selected' :'' } "/>>
								3학년 1학기</option>
							<option value="6" <c:out value="${scoreFormVO.memGrd eq 6 ? 'selected' :'' } "/>>
								3학년 2학기</option>
							<option value="7" <c:out value="${scoreFormVO.memGrd eq 7 ? 'selected' :'' } "/>>
								4학년 1학기</option>
							<option value="8" <c:out value="${scoreFormVO.memGrd eq 8 ? 'selected' :'' } "/>>
								4학년 2학기</option>		
	                   </select>
                  </div>  
                  <div>
	                   <h4 class="font-weight-bold d-inline">학기</h4>
	                   <input type="hidden" name="scoreVOSmst" value="${scoreFormVO.scrSmst }">
	              		<select class="form-control" name="scrSmst" id="scrSmst" required>
	                   </select>
                  </div>
                  <div>
						<button type="button" id="searchBtn" class="btn waves-effect waves-light btn-primary px-4 h-100">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search form-control-icon"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
						</button>
				  </div>
                  <div>
				  </div>
			</form>
         </div>
    </div>
    <div class="row">
    	<div class="col-12 text-right mb-3">
	        <button type="button" class="btn btn-success" id="getBtn"><i class="fas fa-check" ></i> 선발</button>
			<button type="button" class="btn btn-danger" id="cancelBtn"><i class="fas fa-times" ></i> 선발취소</button>
			<button type="button" class="btn waves-effect waves-light btn-light ml-3" id="printBtn"><i class="icon-printer"></i>출력</button>
    	</div>
    </div> 
    <div class="row">
		<div class="scholarGradeTable col-12" id="printDiv" >
			<div id="acceptedHeader" class="text-center"><h1>성적 장학생</h1></div>
	       <form id="selectForm" method="post" action="${cPath }/admin/insertScholarByGrade.do">
				<table id="table1" class="table table-sm text-center">
					<thead class="thead-light">
						<tr>
							<th class="ckboxall fixedHeader bg-primary text-white font-weight-bold"><label><input type="checkbox" id="checkall"></label></th>
							<th class="fixedHeader bg-primary text-white font-weight-bold">단과대</th>
							<th class="fixedHeader bg-primary text-white font-weight-bold">학과</th>
							<th class="fixedHeader bg-primary text-white font-weight-bold">학번</th>
							<th class="fixedHeader bg-primary text-white font-weight-bold">학년</th>
							<th class="fixedHeader bg-primary text-white font-weight-bold">이름</th>
							<th class="fixedHeader bg-primary text-white font-weight-bold">이수학점</th>
							<th class="fixedHeader bg-primary text-white font-weight-bold">평점평균</th>
							<th class="fixedHeader bg-primary text-white font-weight-bold">학기</th>
							<th class="fixedHeader bg-primary text-white font-weight-bold">석차</th>
							<th class="fixedHeader bg-primary text-white font-weight-bold">장학금 코드</th>
							<th class="fixedHeader bg-primary text-white font-weight-bold">장학금명</th>
							<th class="fixedHeader bg-primary text-white font-weight-bold">장학금액</th>
							<th class="fixedHeader bg-primary text-white font-weight-bold">선발여부</th>
						</tr>
					</thead>
					<tbody id="listBody">
					<c:set var="scoreList" value="${semeScoreList }"/>
					<c:if test="${not empty scoreList }">
						<c:forEach items="${scoreList }" var="score" varStatus="status">
								<tr data-studentid="${score.memId }" data-smst="${currentSmst}">
									<c:if test="${score.rank lt 5}">
										<td><input type="checkbox" name="chk"></td>
									</c:if>
									<c:if test="${score.rank ge 5}">
										<td></td>
									</c:if>
									<td>${score.codeVO.codeName}</td>
									<td>${score.departmentVO.depName}</td>
									<td>${score.memId}</td>
									<td>${score.memberVO.memGrd}학년</td>
									<td>${score.memberVO.memName}</td>
									<td>${score.scrCredit}</td>
									<td>${score.scrAgv}</td>
									<td>${score.scrSmst}</td>
									<td>${status.count}</td>
									<c:if test="${score.rank eq 1}">
										<td>${scoreScholar1Type.schCode }<input type="hidden" name="hiddenScharCode" value="${scoreScholar1Type.schCode }"></td>
										<td>${scoreScholar1Type.schName }</td>
										<fmt:formatNumber var="depFee1" groupingUsed="true" value="${score.departmentVO.depFee}"/>
										<td>${depFee1 }<input type="hidden" name="hiddenSfundValue" value="${score.departmentVO.depFee }"></td>
										<td id="codeResult${status.count }">${score.schoFundVO.codeResult eq null ? '선발전' : score.schoFundVO.codeResult }<input type="hidden" name="hiddenResult" value="${score.schoFundVO.codeResult eq null ? '선발전' : score.schoFundVO.codeResult }"><input type="hidden" name="hiddenSfundNo" value="${score.schoFundVO.sfundNo }"></td>
									</c:if>	
									<c:if test="${score.rank !=1 and score.rank lt 3}">
										<td>${scoreScholar2Type.schCode }<input type="hidden" name="hiddenScharCode" value="${scoreScholar2Type.schCode }"></td>
										<td>${scoreScholar2Type.schName }</td>
										<fmt:formatNumber var="depFee2" groupingUsed="true" value="${score.departmentVO.depFee /2}"/>
										<td>${depFee2 }<input type="hidden" name="hiddenSfundValue" value="${score.departmentVO.depFee/2 }"></td>
										<td id="codeResult${status.count }">${score.schoFundVO.codeResult eq null ? '선발전' : score.schoFundVO.codeResult }<input type="hidden" name="hiddenResult" value="${score.schoFundVO.codeResult eq null ? '선발전' : score.schoFundVO.codeResult }"><input type="hidden" name="hiddenSfundNo" value="${score.schoFundVO.sfundNo }"></td>
									</c:if>	
									<c:if test="${score.rank ge 3 and score.rank lt 5}">
										<td>${scoreScholar2Type.schCode }<input type="hidden" name="hiddenScharCode" value="${scoreScholar2Type.schCode }"></td>
										<td>${scoreScholar2Type.schName }</td>
										<fmt:formatNumber var="depFee3" groupingUsed="true" value="${(score.departmentVO.depFee /2)/2}"/>
										<td>${depFee3 }<input type="hidden" name="hiddenSfundValue" value="${(score.departmentVO.depFee /2)/2}"></td>
										<td id="codeResult${status.count }">${score.schoFundVO.codeResult eq null ? '선발전' : score.schoFundVO.codeResult }<input type="hidden" name="hiddenResult" value="${score.schoFundVO.codeResult eq null ? '선발전' : score.schoFundVO.codeResult }"><input type="hidden" name="hiddenSfundNo" value="${score.schoFundVO.sfundNo }"></td>
									</c:if>	
									<c:if test="${score.rank ge 5}">
										<td colspan="4">대상자가 아닙니다.</td>
									</c:if>	
								</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty scoreList }">
						<tr class="text-right">
							<td colspan="8">조회된 정보가 없음.</td>
						</tr>
					</c:if>
					
				</tbody>
				</table>
			</form>   
		</div>
	</div>
</div>
<form id="hiddenDep">

</form>		
<!-- ============= 확인 모달 ================ -->
<!-- ========================================= -->
<div id="result" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="danger-header-modalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header modal-colored-header bg-danger">
				<h4 class="modal-title font-weight-bold" id="danger-header-modalLabel">선발 완료</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<h4 class="font-weight-bold">선발이 완료되었습니다.</h4>
			</div>
			<div class="modal-footer" style="margin: auto;">
				<button type="button" class="btn btn-light" data-dismiss="modal">닫기</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<object id="factory" style="display:none" viewastext classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
codebase="http://www.test.com/ActiveX/ScriptX.cab#Version=6,1,431,2">
</object>

<script>

$(function(){
	$("#acceptedHeader").hide();
	// 학부조회
	$.ajax({
		url : "${cPath }/admin/getScholarshipCollege.do"
		,method : "get"
		,dataType : "json"
		,success : function(data){
			makeCollegeSelect(data);
		}
	});

});

// 학부 select만들기
function makeCollegeSelect(data){
	
	let collegeList = data.collegeList;
	let smstList = data.smstList;
	let html = '<option value >학부선택</option>';
	for(let i = 0 ; i < collegeList.length ; i++){
		html += '<option value='+collegeList[i].code+'>'+collegeList[i].codeName+'</option>';
	}
	$("#colleage").html(html);
	
	// 검색 후 가져온 VO 로  검색조건 selected 
	for(let i = 0 ; i < collegeList.length ; i++){
		let already = $("input[name='scoreVOCollege']").val();
		if(already==collegeList[i].code){
			$("#colleage").val(collegeList[i].code).prop("selected",true);
			changeCollege();
		}
	}
	
	let html2 = '<option value >학기</option>';
	for(let i = 0 ; i < smstList.length ; i++){
		html2 += '<option value='+smstList[i].scrSmst+' >'+smstList[i].scrSmst+'학기</option>';
	}
	$("#scrSmst").html(html2);
	
	for(let i = 0 ; i < smstList.length ; i++){
		let already = $("input[name='scoreVOSmst']").val();
		if(already==smstList[i].scrSmst){
			$("#scrSmst").val(smstList[i].scrSmst).prop("selected",true);
		}
	}
	
}
// 학부선택 -> 학과조회
function changeCollege(){
	let collegeList = document.getElementById("colleage");
	let college = collegeList.options[collegeList.selectedIndex].value;
	$.ajax({
		url : "${cPath}/admin/selectCollege.do?college="+college
			,type : "get"
			,dataType : "json"
			,success : function(data){
				makeDepartSelect(data);
			}
	})
}

// 학과 select만들기
function makeDepartSelect(data){
	var html = '<option value >학과선택</option>';
	for(var i = 0 ; i < data.department.length ; i++){
		html += '<option value='+data.department[i].depNo+' >'+data.department[i].depName+'</option>';
	}
	$("#department").html(html);
	
	for(var i = 0 ; i < data.department.length ; i++){
		let already = $("input[name='scoreVOdepNo']").val();
		if(already==data.department[i].depNo){
			$("#department").val(data.department[i].depNo).prop("selected",true);
		}
	}
	
}

let searchForm = $("#searchForm");

// 검색 버튼 클릭시
$("#searchBtn").on("click",function(e){
	e.preventDefault();
	let colleage = document.getElementById("colleage");
	let department = document.getElementById("department");
	let grade = document.getElementById("grade");
	let scrSmst = document.getElementById("scrSmst");
	if(colleage.value =="" ||department.value == ""||grade.value == ""||scrSmst.value == "") {
		alert("모든 항목을 선택해 주세요.");
		return;
	}
	searchForm.submit();
	
	return false;
});

// 체크박스 전체 체크
$(function(){
	$("#checkall").click(function(){
		if($("#checkall").prop("checked")){
			$("input[name=chk]").prop("checked",true);
		}else{
			$("input[name=chk]").prop("checked",false);
		}
	})	
		
});


// 선발 버튼 클릭시
$("#getBtn").on("click",function(e){
	e.preventDefault();
	if( $("input[name=chk]:checked").length == 0 ){
	    alert("항목을 하나이상 체크하세요");
	    return false;
	}
	let checkbox = $("input[name=chk]:checked");
	let result ="";
	checkbox.each(function(i) {
		let tr = checkbox.parent().parent().eq(i);
		let td = tr.children();
		result = td.find("input[name=hiddenResult]").val();
		
		if(result=="접수"){
			alert("이미 선발된 내역이 포함되어있습니다.");
			return false;
		}
		if(result=="지급"){
			alert("이미 장학금이 지불완료된 내역입니다.");
			return false;
		}
		
		let scharCode = td.find("input[name=hiddenScharCode]").val();
		let tmpsfundValue = td.find("input[name=hiddenSfundValue]").val();
		let sfundValue = Math.floor(tmpsfundValue);
		let sfundStudent =tr.data("studentid");
		let sfundSmst =tr.data("smst");
		let input1 = document.createElement('input');
		input1.setAttribute("type", "hidden"); 
		input1.setAttribute("class", "hiddenInput"); 
		input1.setAttribute("name", "applyList["+i+"].scharCode"); 
		input1.setAttribute("value",scharCode);
		let input2 = document.createElement('input');
		input2.setAttribute("type", "hidden"); 
		input2.setAttribute("class", "hiddenInput"); 
		input2.setAttribute("name", "applyList["+i+"].sfundValue"); 
		input2.setAttribute("value",sfundValue);
		let input3 = document.createElement('input');
		input3.setAttribute("type", "hidden"); 
		input3.setAttribute("class", "hiddenInput"); 
		input3.setAttribute("name", "applyList["+i+"].sfundStudent"); 
		input3.setAttribute("value",sfundStudent);
		let input4 = document.createElement('input');
		input4.setAttribute("type", "hidden"); 
		input4.setAttribute("class", "hiddenInput"); 
		input4.setAttribute("name", "applyList["+i+"].sfundSmst"); 
		input4.setAttribute("value",sfundSmst);
		let input5 = document.createElement('input');
		input5.setAttribute("type", "hidden"); 
		input5.setAttribute("class", "hiddenInput"); 
		input5.setAttribute("name", "applyList["+i+"].codeResult"); 
		input5.setAttribute("value","ACCEPT");
		$("#selectForm").append(input1);
		$("#selectForm").append(input2);
		$("#selectForm").append(input3);
		$("#selectForm").append(input4);
		$("#selectForm").append(input5);
	})
	
	if(result=="선발전"){
		
		$.ajax({
				url : "${cPath }/admin/insertScholarByGrade.do"
				,method : "post"
				,data : $("#selectForm").serialize()
				,dataType : "json"
				,success : function(data){
					alert(data.message);
					let collegeList = document.getElementById("colleage");
					let college = collegeList.options[collegeList.selectedIndex].value;
					$.ajax({
						url : "${cPath}/admin/selectCollege.do?college="+college
							,type : "get"
							,dataType : "json"
							,success : function(data){
								makeDepartSelect(data);
								let sfundSmst =$("tr").data("smst");
								console.log(sfundSmst);
								$("input[name='scoreVOSmst']").val(sfundSmst);
								// 기존 input hidden 태그 삭제
								$(".hiddenInput").remove();
								
								$("#searchBtn").trigger("click");
							}
					})
				
				}
		})

	}
	
	return false;
});

// 성적 선발 취소 버튼 클릭시
$("#cancelBtn").on("click",function(e){
	e.preventDefault();
	$("#selectForm").attr("action", "${cPath}/admin/cancelScholarScoreList.do");
	if( $("input[name=chk]:checked").length == 0 ){
	    alert("항목을 하나이상 체크하세요");
	    return false;
	}
	let checkbox = $("input[name=chk]:checked");
	let result ="";
	checkbox.each(function(i){
		let tr = checkbox.parent().parent().eq(i);
		let td = tr.children();
		let sfundNo = td.find("input[name=hiddenSfundNo]").val();
		result = td.find("input[name=hiddenResult]").val();
		
		if(result=="비대상"){
			alert("선발되지 않은 내역입니다.");
			return false;
		}
		if(result=="지급"){
			alert("이미 장학금이 지불완료된 내역입니다.");
			return false;
		}
		if(result=="선발전"){
			alert("선발되지 않은 내역입니다.");
			return false;
		}
		
		var input1 = document.createElement('input');
		input1.setAttribute("type", "hidden"); 
		input1.setAttribute("class", "hiddenInput"); 
		input1.setAttribute("name", "applyList["+i+"].sfundNo"); 
		input1.setAttribute("value",sfundNo);
		$("#selectForm").append(input1);
		
	})
	if(result=="접수"){
		$.ajax({
			url : "${cPath }/admin/cancelScholarScoreList.do"
			,method : "post"
			,data : $("#selectForm").serialize()
			,dataType : "json"
			,success : function(data){
				alert(data.message);
				let collegeList = document.getElementById("colleage");
				let college = collegeList.options[collegeList.selectedIndex].value;
				$.ajax({
					url : "${cPath}/admin/selectCollege.do?college="+college
						,type : "get"
						,dataType : "json"
						,success : function(data){
							makeDepartSelect(data);
							// 기존 input hidden 태그 삭제
							$(".hiddenInput").remove();
							
							$("#searchBtn").trigger("click");
						}
				})
			}
		})
		
	}
	return false;
});	

//프린트 
$("#printBtn").on("click",function(){
	$("#acceptedHeader").show();
	var con_test = confirm("해당 페이지를 출력하시겠습니까?");
	if(con_test == true){
	    var initBody = document.body.innerHTML;
	    window.onbeforeprint = function () {
			
	       document.body.innerHTML = document.getElementById("printDiv").innerHTML;
	    }
	    window.onafterprint = function () {
	       window.location.reload()
	       
	    }
	    window.print();
	}
	else if(con_test == false){
	  $("#acceptedHeader").hide();
	  return false;
	}
	
	$("#acceptedHeader").hide();
}); 



</script>