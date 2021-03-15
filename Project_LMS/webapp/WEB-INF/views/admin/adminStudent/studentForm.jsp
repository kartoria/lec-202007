<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid border px-3" style="min-height:300px">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 학생관리 > 신입생 등록</h5>
		</div>
	</div>
	
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">신입생 등록</h2>
		</div>
	</div>
	
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"> 
				학과를 선택한뒤 신입생 정보를 입력하세요.	학번 부여를 위해 한 학과씩 일괄 처리 됩니다.
			</h5>
		</div>
	</div>
	<div class="row my-3 ml-3 mb-5" id="rightArea">
		<div class="col-12">
			<table id="table1" class="table table-sm table-bordered thead-light">
				<tbody>
					<tr class="text-center">
						<th class="font-weight-bold" colspan="3" class="w-25">이름 <span class="text-danger">*</span></th>
						<th class="font-weight-bold" colspan="3" class="w-25">주민등록 번호 <span class="text-danger">*</span></th>
						<th class="font-weight-bold" colspan="3" class="w-25">학과 <span class="text-danger">*</span></th>
						<th class="font-weight-bold" colspan="3" class="w-25"> - </th>
					</tr>
					<tr>
						<td colspan="3" class=""><input type="text" id="nameInput" class="form-control" >
							<span id="nameError" class="text-danger"> </span>
						</td>
						<td ><input type="text" id="reg1" class="form-control" >
							<span id="reg1Error" class="text-danger"></span> 
						</td>
						<td> - </td>
						<td><input type="text" id="reg2"  class="form-control" >
							 <span id="reg2Error" class="text-danger"></span>
						 </td>
						<td  >	
					       <select name="searchType" class="custom-select mr-sm-2" id="inlineFormCustomSelect1">
                           		<c:if test="${not empty codeList }">
                               		<option selected>단과대학를 선택하세요</option>
                           			<c:forEach items="${codeList }" var="code">
                                   		 <option value="${code.codeName }">${code.codeName }</option>
                                   	</c:forEach>
                                </c:if>
                            </select>
                            <span id="select1Error" class="text-danger"> </span>
						</td>
						<td colspan="2">    
				         	<select name="searchType" class="custom-select mr-sm-2" id="inlineFormCustomSelect2">
                          		<option value=''>학과를 선택하세요</option>
               		 	 	</select>
               		 	 	<span id="select2Error" class="text-danger"> </span>
						</td>	
						<td class="text-center pr-0">
							<button id="successBtn1" class="btn waves-effect waves-light btn-primary"><i class="icon-plus"></i> 학생추가</button>
						</td>
						<td class="text-left pl-0">	
							<button id="modalBtn" class="btn btn-success ml-2"><i class="fas fa-file-excel">Excel 일괄 등록</i></button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row mt-3">		
		<div id="totalStudentArea" class="col-12">
			<h3 id="stuSpan" class="font-weight-bold">등록할 학생</h3>
			<table id="table2" class="table">
				 <thead class="thead-light">
				 	<tr class="text-center text-dark">
				 		<th class="font-weight-bold">이름</th>
				 		<th class="font-weight-bold">학과</th>
				 		<th class="font-weight-bold">주민등록번호</th>
				 		<th id="thdel" class="font-weight-bold">삭제</th>
				 	</tr>
				 </thead>
				 <tbody id="tbodyArea">
				 		<tr class="text-center">
				 			<td class="font-weight-bold" colspan="4">등록할 학생을 추가해주세요.</td>
				 		</tr>
				 </tbody>
			</table>
		</div>
	</div>
	<div class="row my-3" id="buttonDiv">
		<div class="col-12 d-flex justify-content-end align-items-center">
			<div class="form-inline">
				<div class="form-group">
					<button id="successBtn2" class="btn waves-effect waves-light btn-primary px-3 py-2 mx-1">신입생 등록</button>
				</div>
				<div class="form-group">
					<button id="resetBtn" class="btn waves-effect waves-light btn-danger px-3 py-2 mx-1">초기화</button>
				</div>
			</div>
		</div>
	</div>
</div>			 
			
	<div class="modal fade" id="excelModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="myCenterModalLabel">엑셀 일괄 처리</h3>
				<button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×</button>
            </div>
            <div class="modal-body ">
                <span class="text-danger">(샘플 양식에 맞게 작성 후 업로드하시기 바랍니다.)</span>
				<div class="form-group d-flex justify-content-center align-items-center">
<!-- 				<form id="excelForm"  method="post" enctype="multipart/form-data"> -->
					<input type="file" name="excelFile" id="excel_file" class="form-control-file" onchange="excelUp(this);">
<!-- 				</form> -->
				<a class="btn waves-effect waves-light btn-light px-2 py-2 mx-2 w-75 mr-1" href="javascript:;" onclick="downFile(this)"><i class="fas fa-file-excel"></i> 샘플양식 다운</a> 
				<button class="btn btn-primary px-2 py-2 mx-2 w-75" id="excel_upload">업로드/등록</button>
			</div>
			</div>
        </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
</div><!-- /.modal -->		
<script type="text/javascript">
let smst = ${codeVO.codeName};
let depNoReal = "";
let option1=null;
let option2=null;




//단과대 선택
$("#inlineFormCustomSelect1").change(function(){
  $("#select1Error").text("");
	option1 = this.value;
	$.ajax({
		url : "${cPath}/admin/adminDepNameList.do",
		data : {"codeName":this.value},
		method : "post",
		dataType : "json",
		success : function(resp) {
			if (resp.departmentList !=null) {
				$("#inlineFormCustomSelect2").find("option").remove().end().append("<option value=''>학과를 선택하세요</option>");
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
//학과 선택
$("#inlineFormCustomSelect2").change(function(){
    $("#select2Error").text("");
	option2 = this.value;
	$.ajax({
		url : "${cPath}/admin/studentFormDepNo.do",
		data : {"depName":this.value},
		method : "post",
		dataType : "json",
		success : function(resp) {
			depNoReal = resp.departmentVO.depNo;
		},
		error : function(xhr) {
			console.log(xhr);
		}
	});
});
let array = new Array() ;
let idx = 0;
let selectOption ;
//아래에 추가 버튼
$("#successBtn1").on("click",function(){
	
	var value1 = $("#reg1").val();
	var reg1 = /^\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$/;

	var value2 = $("#reg2").val();
	var reg2 = /^[1-4]\d{6}$/;
	let name = $("#nameInput").val();
	if(!reg1.test(value1) || !reg2.test(value2) ||name==null || option1==null ||option2 ==null) {
		if(!reg1.test(value1)){
		    $("#reg1Error").text("주민등록번호 형식에 맞게 입력하세요.");
		}
		if(!reg2.test(value2)){
		    $("#reg2Error").text("주민등록번호 형식에 맞게 입력하세요.");
		}
		if(name ==""){
			$("#nameError").text("이름을 입력하세요.");
		}
		if(option1==null){
		    $("#select1Error").text("단과대를 선택하세요.");
		}
		if(option2 ==null){
		    $("#select2Error").text("단과대를 선택하세요.");
		}
	} else {
		$("#nameError").text("");
	    $("#reg1Error").text("");
	    $("#reg2Error").text("");
	    $("#select1Error").text("");
	    $("#select2Error").text("");
		
		$('#inlineFormCustomSelect1').attr('disabled', 'true');
		$('#inlineFormCustomSelect2').attr('disabled', 'true');
	
		selectOption  = $("#inlineFormCustomSelect2 option:selected").val();
		let reg1 = $("#reg1").val();
		let reg2 = $("#reg2").val();
		let subArray = new Array();
		subArray.push(name);
		subArray.push(reg1);
		subArray.push(reg2);
		let flag=0;
		if(array.length==0){
				array.push(subArray);
		}else{
			for(let i=0;i<array.length;i++){
				if(array[i][1]==reg1 && array[i][2]==reg2){
					alert("주민등록 번호 중복");
					return;
				}else{
					flag=1;
				}
			}
		}
		if(flag==1){
			array.push(subArray);
		}
		array = array.sort((a, b) => a[0].localeCompare(b[0]));
		
		makeTable(array);
		console.log("추가된 배열 :" +array);
	
		$('#nameInput').val('');
		$('#reg1').val('');
		$('#reg2').val('');
		idx += 1;
	}

	
	

});
function makeTable(array){
	$("#tbodyArea").empty();
	let tableText = "";
	$("#tbodyArea").text("");
	for(let i=0; i<array.length;i++){
		let j = i+1;
		tableText += ("<tr id='tr"+i+"' class='text-center text-dark'>");
		tableText += ("<td class='font-weight-bold'>"+array[i][0]+"</td>");
		tableText += ("<td class='font-weight-bold'>"+selectOption+"</td>");
		tableText += ("<td class='font-weight-bold'>"+array[i][1]+" "+array[i][2]+"</td>");
		tableText += ("<td class='font-weight-bold'><button class='btn btn-primary' onclick='btnId("+array[i][1]+","+array[i][2]+")'>X</button></td>");
	}
	$("#tbodyArea").append(tableText);
}

// 삭제 버튼
function btnId(reg1,reg2){
	console.log(reg1+":"+reg2);
	let num=99999;
	for(let i=0; i<array.length;i++){
		if(array[i][1]==reg1 && array[i][2]==reg2) {
			num=i;
		}
	}
	let deleteArray = array.splice(num,1);
	makeTable(array);
	if(array.length==0){
		location.reload();
	}
	
}

//초기화버튼
$("#resetBtn").on("click",function(){
	window.location.href = '${cPath}/admin/studentForm.do';
});


$("#successBtn2").on("click",function(){
	let leng = array.length;
	if(leng==1){
		let subArray = new Array();
		array.push(subArray);
	}
	console.log(array);
 	$.ajaxSettings.traditional = true;
 	$.ajax({
 		url : "${cPath}/admin/studentFormInsert.do",
 		data : {
 				"array":array,
 				"depNo":depNoReal
 				},
 		method : "post",
 		dataType : "json",
 		success : function(resp) {
			if(resp.result!=null){
				alert("등록 성공");
				window.location.href = '${cPath}/admin/studentForm.do';
			}
		
 		},
 		error : function(xhr) {
 			console.log(xhr);
 		}
 	});
});

$("#nameInput").on("change keyup paste", function(){
	$("#nameError").text(" ");
});
$("#reg1").on("change keyup paste", function(){
	$("#reg1Error").text(" ");
});
$("#reg2").on("change keyup paste", function(){
	$("#reg2Error").text(" ");
});



//주민등록번호 앞자리  숫자만 입력 
$("#reg1").bind("keyup", function(event){
	var regNumber3 = /^[0-9]*$/;
	var temp3 = $("#reg1").val();
	if(!regNumber3.test(temp3)){
		$("#reg1Error").text("숫자만 입력하세요");
		$("#reg1").val(temp3.replace(/[^0-9]/g,""));
	}
});
//주민등록번호 뒷자리  숫자만 입력 
$("#reg2").bind("keyup", function(event){
	var regNumber3 = /^[0-9]*$/;
	var temp3 = $("#reg2").val();
	if(!regNumber3.test(temp3)){
		$("#reg2Error").text("숫자만 입력하세요");
		$("#reg2").val(temp3.replace(/[^0-9]/g,""));
	}
});



//엑셀 파일 첨부
const excelFile = $("#excel_file");
excelFile.on("change",function(){
	const uploadFile = this.files[0];
	const ext = uploadFile.name.substr(uploadFile.name.lastIndexOf(".")+1).toLowerCase();
	
	if(ext !="xls" && ext!="xlsx"){
		alert("엑셀 파일을 업로드 해주세요.");
		this.value=null;
	}
});



//엑셀 업로드 
const excelUpload = $("#excel_upload");
function excelUp(input){
    let file = input.files[0];
	let formData = new FormData();
	formData.append("excelFile",file);
	
	excelUpload.on("click",function(){
		
		$.ajax({
		    type : "POST",
		    url : "${cPath }/excelUpload.do",
		    data : formData,
		    async: false,
	        processData: false,
	        contentType: false,
	        dataType:"json",
		    success : function(resp) {
				$('#excelModal').modal('hide');
				$("#thdel").hide();
				$("#tbodyArea").empty();
				$("#successBtn2").hide();
				$('#successBtn1').attr('disabled', true);
				$("#stuSpan").text("Excel - 등록된 학생");
				
				let tableText = "";
				$("#tbodyArea").text("");
				for(let i=0; i<resp.memberList.length;i++){
					tableText += ("<tr id='tr"+i+"' class='text-center text-dark'>");
					tableText += ("<td class='font-weight-bold '>"+resp.memberList[i].memName+"</td>");
					tableText += ("<td class='font-weight-bold'>"+resp.memberList[i].depNo+"</td>");
					tableText += ("<td class='font-weight-bold'>"+resp.memberList[i].memReg1+" - "+resp.memberList[i].memReg2+"</td>");
				}
				$("#tbodyArea").append(tableText);

		    },
		    error : function(err) {
		        alert(err.status);
		    }
		});
	});
}
	


//샘플 양식 다운로드
function downFile(obj){ 
	$(obj).attr('href' , '${cPath}/excel/studentInsertSample.xlsx'); 
	$(obj).attr('download' , 'sample.xlsx');
	$('#excelModal').modal('hide');

} 
//엑셀 모달
$("#modalBtn").on("click",function(){
	$("#excelModal").modal();	
});


</script>



