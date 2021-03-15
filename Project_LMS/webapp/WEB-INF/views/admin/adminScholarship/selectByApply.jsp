<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" href="${cPath}/css/admin/scholarship.css"> 
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 장학관리 > 교내 장학생 선발
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-admin p-0 my-2">
		<div class="col-12">
			<h2 class="font-weight-bold">교내장학생 선발</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">신청된 내역 중 교내 장학생을 선발 할 수 있습니다.</h5>
		</div>
	</div>
       <div class="row mb-3 mt-3">
           <div class="col-12 pt-3 pl-3" id="searchBody">
              	<form id="searchForm" class="form-inline" action="${cPath}/admin/scholarshipSearchSelectByApply.do" method="post">
              		<div>
                    <h4 class="font-weight-bold d-inline">학기</h4>
                    <select class="form-control" name="sfundSmst" id="sfundSmst" required>
                             <option value="">학기</option>
                           <c:set var="smstList" value="${smstList }"/> 
						<c:if test="${not empty smstList }">
						<c:forEach items="${smstList }" var="smst" varStatus="status">  
	               	  		<option value="${smst.sfundSmst }">
              	  				${fn:substring(smst.sfundSmst,0,4) }년도
              	  				${fn:substring(smst.sfundSmst,5,6) }학기
             	  				</option>
	           	  		</c:forEach>
	          	  		</c:if>	
                 	  </select>
                 	</div> 
	               	<div>
	               	<h4 class="font-weight-bold d-inline" >장학유형</h4>
	               	<select class="form-control" name="scharCode" id="scharCode" required>
	                         <option value="" >선택</option>
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
              <button type="button" class="btn btn-success" id="getBtn"><i class="fas fa-check" ></i> 접수</button>
			  <button type="button" class="btn btn-danger" id="cancelBtn"><i class="fas fa-times" ></i> 접수취소</button>
			  <button type="button" class="btn waves-effect waves-light btn-light ml-3" id="printBtn"><i class="icon-printer"></i>출력</button>
		  </div>
	  </div>
	  <div class="row">	  	
        <div class="mainTable col-12" id="printDiv" >
        	<div id="acceptedHeader" class="text-center"><h1>교내 장학생</h1></div>
         	<form:form id="selectApply" commandName="ScholarshipFundListVO" method="post" action="${cPath }/admin/updateScholarApplyList.do">
			<table class="table table-sm text-center">
				<thead class="thead-light">
					<tr>
						<th class="ckboxall fixedHeader bg-primary text-white font-weight-bold"><input type="checkbox" id="checkall"></th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">신청번호</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">성명</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">학번</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">추천교수코드</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">학기</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">신청날짜</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">장학금명</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">금액</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">사유</th>
						<th class="fixedHeader bg-primary text-white font-weight-bold">신청 결과</th>
					</tr>
				</thead>
				<tbody id="listBody">
					<tr class="text-center">
						<td colspan="11">조회된 정보가 없음.</td>
					</tr>
				</tbody>
			</table>
			</form:form>   
		</div>
	 </div>
</div>


<script>

$(function(){
	// 프린트 타이틀 숨김
	$("#acceptedHeader").hide();
});

let searchForm = $("#searchForm");

// 학기 정보
$("#sfundSmst").on("change",function(){
	let smstList = document.getElementById("sfundSmst");
	let smst = smstList.options[smstList.selectedIndex].value;
	$.ajax({
		url : "${cPath}/admin/chooseByScholarType.do"
			,type : "get"
			,dataType : "json"
			,success : function(data){
				makeSelect(data);
			}
	})
});

// 장학종류 셀렉트
function makeSelect(data){
	var html = "<option>선택</option>";
	for(var i = 0 ; i < data.scholarship.length ; i++){
		html += '<option value="'+data.scholarship[i].schCode+'">'+data.scholarship[i].schName+'</option>';
	}
	$("#scharCode").html(html);
	
}


// 검색필터
$("#searchBtn").on("click",function(e){
	e.preventDefault();
	let sfundSmst = document.getElementById("sfundSmst");
	let scharCode = document.getElementById("scharCode");
	if(sfundSmst.value =="" ||scharCode.value == "") {
		alert("모든 항목을 선택해 주세요.");
		return;
	}
	
	$.ajax({
		url : "${cPath}/admin/scholarshipSearchSelectByApply.do"
		,method : "post"
		,data : searchForm.serialize()
		,dataType : "json"
		,success : function(resp){
			console.log(resp);
			let scholarApplyList = resp.scholarApplyList;
			let trTags = '';
			if(scholarApplyList.length>0){
				$(scholarApplyList).each(function(idx, sch){
					let value =  sch.sfundValue;
					let sfundValue = value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
					trTags += '<tr data-coderesult="'+sch.codeVO.codeName+'" data-sfundno="'+sch.sfundNo+'">';
					trTags += '<td><input type="checkbox" id="chk" name="chk" /></td>';
					trTags +=   '<td>'+sch.sfundNo+'</td>';
					trTags +=   '<td>'+sch.memberVO.memName+'</td>';
					trTags +=   '<td><a href="${cPath}/admin/applyScholarView.do?sfundNo='+sch.sfundNo+'">'+sch.sfundStudent+'</a></td>';
					trTags +=   '<td>'+(sch.sfundProfessor ==null? "":sch.sfundProfessor) +'</td>';
					trTags +=   '<td>'+sch.sfundSmst+'</td>';
					trTags +=   '<td>'+sch.sfundResdate+'</td>';
					trTags +=   '<td>'+sch.schVO.schName+'</td>';
					trTags +=   '<td>'+sfundValue+'</td>';
					trTags +=   '<td>'+(sch.sfundReason ==null? "":(sch.sfundReason).substring(0,10) )+'</td>';
					trTags +=   '<td >'+sch.codeVO.codeName+"<input type='hidden'name='result' value='"+sch.codeResult+"'>"+'</td>';


			        trTags += '</tr>';
				});
			}else{
				trTags +=   '<tr><td colspan="11">조회된 내용이 없음</td></tr>';
			}
			$("#listBody").html(trTags);
		}
	})
	
	return false;
});
	
// 체크박스 전체 체크
$("#checkall").click(function(){
	if($("#checkall").prop("checked")){
		$("input[name=chk]").prop("checked",true);
	}else{
		$("input[name=chk]").prop("checked",false);
	}
});	

// 신청 장학생 접수
$("#getBtn").on("click",function(e){
	e.preventDefault();
	if( $("input[name=chk]:checked").length == 0 ){
	    alert("항목을 하나이상 체크하세요");
	    return;
	}
	let checkbox = $("input[name=chk]:checked");
	
	let result ="";
	// 마지막으로 체크된 것만 input hidden으로만들기
	checkbox.each(function(i){
		let tr = checkbox.parent().parent().eq(i);
		let td = tr.children();
		result = tr.data("coderesult");
		
		if(result=="접수"){
			alert("이미 접수된 내역이 포함되어있습니다.");
			return false;
		}
		if(result=="지급"){
			alert("이미 장학금이 지불완료된 내역입니다.");
			return false;
		}
		if(result=="반려"){
			alert("이미 장학금이 반려된 내역입니다.");
			return false;
		}
		
		let sfundNo =tr.data("sfundno");
		let input1 = document.createElement('input');
		input1.setAttribute("type", "hidden"); 
		input1.setAttribute("class", "hiddenInput"); 
		input1.setAttribute("name", "applyList["+i+"].sfundNo"); 
		input1.setAttribute("value",sfundNo);
		let input2 = document.createElement('input');
		input2.setAttribute("type", "hidden"); 
		input2.setAttribute("class", "hiddenInput"); 
		input2.setAttribute("name", "applyList["+i+"].codeResult"); 
		input2.setAttribute("value","ACCEPT");
		$("#selectApply").append(input1);
		$("#selectApply").append(input2);
		
	})
	
	
	if(result=="신청"){
		
		$.ajax({
			url : "${cPath }/admin/updateScholarApplyList.do"
			,method : "post"
			,data : $("#selectApply").serialize()
			,dataType : "json"
			,success : function(data){
				alert(data.message);
				
				$("#checkall").prop("checked", false);
				
				$("input:checkbox[name='chk']").prop("checked", false);
				
				$(".hiddenInput").remove();
				
				$("#searchBtn").trigger("click");
			}
		})
		
	}
	
	return false;
});	

$("#cancelBtn").on("click",function(e){
	e.preventDefault();
	if( $("input[name=chk]:checked").length == 0 ){
	    alert("항목을 하나이상 체크하세요");
	    return;
	}
	let checkbox = $("input[name=chk]:checked");
	if(checkbox==null || checkbox==""){
		alert("접수 대상을 선택해주세요.");
		return;
	}
	let result ="";
	checkbox.each(function(i){
		let tr = checkbox.parent().parent().eq(i);
		let td = tr.children();
		result = tr.data("coderesult");
		
		if(result=="지급"){
			alert("이미 장학금이 지불완료된 내역입니다.");
			return false;
		}
		if(result=="반려"){
			alert("이미 장학금이 반려된 내역입니다.");
			return false;
		}
		if(result=="신청"){
			alert("접수되지 않은 내역입니다.");
			return false;
		}
		
		let sfundNo =tr.data("sfundno");
		console.log(sfundNo);
		let input1 = document.createElement('input');
		input1.setAttribute("type", "hidden"); 
		input1.setAttribute("class", "hiddenInput"); 
		input1.setAttribute("name", "applyList["+i+"].sfundNo"); 
		input1.setAttribute("value",sfundNo);
		let input2 = document.createElement('input');
		input2.setAttribute("type", "hidden"); 
		input2.setAttribute("class", "hiddenInput"); 
		input2.setAttribute("name", "applyList["+i+"].codeResult"); 
		input2.setAttribute("value","APPLY");
		$("#selectApply").append(input1);
		$("#selectApply").append(input2);
		
	})
	if(result=="접수"){
		
		$.ajax({
			url : "${cPath }/admin/cancelScholarApplyList.do"
			,method : "post"
			,data : $("#selectApply").serialize()
			,dataType : "json"
			,success : function(data){
				alert(data.message);
				
				$("#checkall").prop("checked", false);
				
				$("input:checkbox[name='chk']").prop("checked", false);
				
				$(".hiddenInput").remove();
				
				$("#searchBtn").trigger("click");
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
	// 프린트 타이틀 보이기
	$("#acceptedHeader").hide();
}); 

	
</script>		
