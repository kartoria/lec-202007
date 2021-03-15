<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<style type="text/css">
.errorText{
	color: red;
}
.mainTable input{border: 0px;}
/* .fixedHeader { */
/* 	position: sticky; */
/* 	top: 0; */
/* } */
.stuListDiv{width:400px;height:600px;}
.applypaper {
	width: 60em;
	font-size: 12pt;
	border: 3px solid darkgray;
	padding: 10px;
}
/* .applypaper{width: 60em;height:680px;font-size: 12pt;border: 1px solid black;padding: 10px;} */
.applyform{color:black;}
.mainTable{color: black;} 
.mainTable, .mainTable td, .mainTable th{border: 1px solid darkgray; padding: 2px;} 
.bottomText{font-size:25pt; text-align:center;}
</style>
<div class="container-fluid border px-3" style="min-height: 300px">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 강의 관리 > 강의 계획서 등록
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">강의 계획서 등록</h2>
		</div>
	</div>
	<div class="row mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h4 class="ml-2">
				<span id="errorWeekText"><small>강의를 선택후 계획서를 작성할 수
						있습니다.</small></span>
			</h4>
		</div>
	</div>
	<div class="applyform container">
		<div class="applypaper card">
			<div class="card-img-top">
				<div class="container">
					<div class="mt-5 ml-15">
						<div class="align-middle">
							<div class="text-center">
								<p class="ml-3 text-center display-4">강의 계획서</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<hr>
			<div class="row">
				<label class="col-md-4 text-right"></label>
				<div class="col-md-4">
					<c:if test="${not empty message }">
						<h3 class="errorText">
							<strong>${message }</strong>
						</h3>
					</c:if>
				</div>
			</div>
			<form id="form" method="post">
				<input type="hidden" value="no" name="subCode" id="subCode">
				<input type="hidden" value="no" name="subName" id="subName">
				<div class="card-body">
					<table class="mainTable table table-bordered text-center">
						<tr>
							<td><span class="text-danger">* </span>강의명</td>
							<td colspan="3"><select class="form-control"
								id="exampleFormControlSelect2">
									<c:if test="${not empty subjectList }">
										<option value="no" selected="">강의명을 선택하세요</option>
										<c:forEach items="${subjectList }" var="subject">
											<option value="${subject.lecCode }">${subject.subName }</option>
										</c:forEach>
									</c:if>
									<c:if test="${ empty subjectList }">
										<option value="no" selected="">개설 강의가 없습니다.</option>
									</c:if>
							</select> <span id="errorName" class="errorText"></span></td>
						</tr>
						<tr>
							<td><span class="text-danger">* </span>주교재</td>
							<td><input type="text" id="lecMbk" name="lecMbk"
								class="form-control" placeholder="주교재를 입력하세요"></td>
							<td>부교재</td>
							<td><input type="text" id="lecSbk" name="lecSbk"
								class="form-control" placeholder="부교재를 입력하세요"></td>
						</tr>
						<tr>
							<td colspan='6'><span class="text-danger">*</span>주차별 계획</td>
						</tr>
						<c:forEach var="i" begin="1" end="15" step="1">
							<tr class="p-3 h-1">
								<td>${i }주차</td>
								<td colspan="8"><input type="text" id="lecWeek${i }"
									class="form-control" placeholder="강의 내용을 입력하세요" value=""></td>
							</tr>
						</c:forEach>
					</table>
					<div>
						<div class="bottomBody mt-2 text-center">
							<p class="mt-3 mb-3">위와 같이 강의 계획서를 작성합니다.</p>
						</div>
					</div>
					<div class="text-center">
						<img id="logo1" src="${cPath}/images/logo/smartLMS_logo1.png"
							width="50px" alt="Logo"> <img id="logo2"
							src="${cPath}/images/logo/smartLMS_logo2.png" width="350px"
							alt="Logo">
					</div>
				</div>
		</div>
		<input type="hidden" name="applyList" id="applyList">
		</form>
	</div>
		<div class="form-actions mt-5">
			<div class="col-3 m-auto">
				<button type="button" id="btn1" class="btn btn-light ml-4 mr-2">미리보기</button>
				<button type="button" id="btn2" class="btn btn-info">등록/수정</button>
			</div>
		</div>
</div>
<script type="text/javascript">
$("#btn1").on("click",function(){
	let subCode = $("#subCode").val();
	if(subCode == 'no'){
		$("#errorName").show();
		$("#errorName").text("강의 명을 선택하세요.");
	}else{
		$.ajax({
			url : "${cPath}/pdf/pdfInsert.do",
			data : {"pdfName":"강의계획서"},
			method : "post",
			dataType : "json",
		});
		window.open("", "result", 'width=800px, height=1000px, left=0, top=0, location, menubar, scrollbars, resizable'); 
		form.action = "${cPath}/lms/professor/class/Plan.do"; 
		form.target = "result"; 
		form.submit();
	}
});
let weekList  = new Array();
let updateForm = $("#form"); 
//등록
$("#btn2").on("click",function(){
	let listFlag =0;
	for(let i=1; i<16;i++){
		weekList[i]=$("#lecWeek"+i).val();
		if(weekList[i]==""){
			listFlag +=1;
		}
	}
	
	$("#applyList").val(weekList);
	console.log(weekList);
	if(listFlag==0){
		
		$.ajax({
			url : "${cPath}/lms/professor/class/apply.do",
			data : updateForm.serialize(),
			method : "post",
			dataType : "json",
			success : function(resp) {
				if(resp.message!=null){
					if(resp.message=="OK"){
						alert("강의 등록을 성공했습니다.");
						location.reload();
					}else{
						if(resp.message=="NotName"){
							$("#errorName").text("강의명을 선택해주세요.");
						}
				
	}
					}
				},
				error : function(xhr) {
					console.log(xhr);
				}
			});
		} else {
			if ($("#exampleFormControlSelect2").val() == "no") {
				$("#errorNameText").css("color", "red");

			} else {
				$("#errorWeekText").css("color", "red");

			}
		}
	});

	$("#exampleFormControlSelect2")
			.change(
					function() {
						$("#errorName").hide();
						$("#errorNameText").css("color", "#7C8798");
						$("#lecMbk").val("");
						$("#lecSbk").val("");
						$(errorWeekText).css('color', '');
						$("#subCode").val(this.value);
						let firstOptionText = "강의 명을 선택하세요";
						let optionText = $(
								"#exampleFormControlSelect2 option:selected")
								.text();
						if (optionText == firstOptionText) {
							$("#subName").val("no");
						} else {
							$("#subName")
									.val(
											$(
													"#exampleFormControlSelect2 option:selected")
													.text());
						}

						$("[id^='lecWeek']").val("");
						$
								.ajax({
									url : "${cPath}/lms/professor/class/selectPlan.do",
									data : {
										"lecCode" : this.value
									},
									method : "post",
									dataType : "json",
									success : function(resp) {
										if (resp.lectureVO != null) {
											$("#lecMbk").val(
													resp.lectureVO.lecMbk);
											$("#lecSbk").val(
													resp.lectureVO.lecSbk);
										}
										if (resp.lecPlanList != null) {
											for (let i = 0; i < resp.lecPlanList.length; i++) {
												let j = i + 1;
												$("#lecWeek" + j)
														.val(
																resp.lecPlanList[i].lecContent);
											}
										}
									},
									error : function(xhr) {
										console.log(xhr);
									}
								});

					});
	
	$(function(){
		$(".mainTable td").addClass("align-middle");
	});
</script>
