<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<style type="text/css">
.ui-timepicker-container{ 
     z-index:1151 !important; 
}
#datepicker1{
 	background-image : url(${cPath}/images/lmsimages/counseling/calendar.png); 
 	background-position: right; 
 	background-repeat: no-repeat; 
 	padding-right: 40px; 
/*  	border-radius : 2px; */
 	width: 100%; 
 	height: 30px; 
}
#datepicker2{
 	background-image : url(${cPath}/images/lmsimages/counseling/calendar.png); 
 	background-position: right; 
 	background-repeat: no-repeat; 
 	padding-right: 40px; 
 	border-radius : 2px;
 	width: 100%; 
 	height: 30px; 
}
</style>
<c:set var="pagingVO" value= "${paginationInfo.pagingVO }"/>
<form id="searchForm">
	<input type="hidden" name="page"/>
	<input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType }"/>
	<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }"/>
</form>
<div class="container-fluid border px-3">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 상담 > 상담신청</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">상담신청</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-11">
			<h5 class="pb-5 ml-2">분할 납부를 신청할 수 있습니다.</h5>
		</div>
		<div class="col-1 m-auto">
            	<div class="col-xm-4 ml-auto">
				<button type="button" id="modalBtn" class="btn waves-effect waves-light btn-primary" data-toggle="modal"
						data-target="#scrollable-modal">상담 신청</button>
<!-- 				<button type="button" id="modifyBtn" class="btn waves-effect waves-light btn-warning" data-toggle="modal" -->
<!-- 					data-target="#scrollable-modalUpdate">수정</button> -->
<!-- 				<button type="button" id="deleteBtn" onclick="deleteCounselingApply()" class="btn waves-effect waves-light btn-danger">삭제</button>  -->
            	</div>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-12">
			<table class="table">
				<thead class="thead-light">
					<tr class="text-center text-dark">
						<th class="font-weight-bold">순번</th>
						<th class="font-weight-bold">상담할 교수</th>
						<th class="font-weight-bold">상담 희망 날짜</th>
						<th class="font-weight-bold">상담 희망 시간</th>
						<th class="font-weight-bold">접수 여부</th>
						<th class="font-weight-bold">비고 / 반려사유</th>
						<th class="font-weight-bold">삭제</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="counList" value="${pagingVO.dataList }" />
						<c:if test="${not empty counList }">
							<c:set var="k" value="0"/>
							<c:forEach items="${counList }" var="coun" varStatus="status">
								<c:set var="k" value="${k+1 }"/>
								<tr id="${coun.cstNo }" class="text-center">
									<td>${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }</td>
									<td>${coun.memberVO.memName }</td>
									<td>${coun.cstDate }</td>
									<td>${coun.cstApptm }</td>
									<td>${coun.cstAccpt }</td>
									<td>
										<c:if test="${coun.cstAccpt eq '접수'}">
											접수 중입니다.
										</c:if>
										<c:if test="${coun.cstAccpt ne '접수'}">
											${coun.cstNote }
										</c:if>
									</td>
									<td>
										<c:if test="${coun.cstAccpt eq '반려' }">
											<button type="button" id="deleteBtn" onclick="deleteCounselingApply(${coun.cstNo})" class="btn waves-effect waves-light btn-danger">삭제</button>
										</c:if>
										<c:if test="${coun.cstAccpt ne '반려' }">
											<button type="button" id="deleteBtn" onclick="deleteCounselingApply(${coun.cstNo})" class="btn waves-effect waves-light btn-danger">삭제</button>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty counList }">
							<tr class="text-center">
								<td colspan="7">상담 현황 정보가 없습니다.</td>
							</tr>
						</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>
	<!-- ============= 삭제 확인 모달 ================ -->
	<!-- ========================================= -->
	<div id="delModal" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="danger-header-modalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header modal-colored-header bg-danger">
					<h4 class="modal-title font-weight-bold" id="danger-header-modalLabel">삭제 여부 확인</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
				</div>
				<div class="modal-body">
					<h4 class="font-weight-bold">정말 해당 게시글을 삭제하시겠습니까?</h4>
				</div>
				<div class="modal-footer" style="margin: auto;">
					<button type="button" id="closeBtn" class="btn btn-light" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="goDelBtn">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<!-- 상듬 등록 시, 상담하고픈 시간이 이미 예약되어 있는 상태라면 알림창 -->
	<input type="hidden" id="modalTime"class="btn btn-secondary" data-toggle="modal" data-target="#modalTimetarget" value="숨겨진모달">
	<div class="modal fade" id="modalTimetarget" tabindex="-1" role="dialog" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
			    <div class="modal-header bg-danger text-white">
			      	<h5 class="modal-title ">경고창</h5>
			        <button type="button" class="modalTimeclose" data-dismiss="modal" aria-hidden="true">×</button>
			    </div>
			    <div class="modal-body ">
						<p><h3><strong>이미 예약된 상담이 있습니다.</strong></h3></p>
			    </div>
			</div>
		</div>
	</div>
		<!-- Insert 부분 -->
	<div class="modal fade" id="scrollable-modal" tabindex="-1"
		role="dialog" aria-labelledby="scrollableModalTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-scrollable" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="scrollableModalTitle">상담 신청</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12 col-md-6 col-lg-12">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">상담 신청 교수</h4>
	                               	<input type="hidden" name="cstNo" value="${coun.cstNo }"/>
	                               	<input type="hidden" id="cstAccpt" name="cstAccpt" value="접수">
										<div class="form-group mb-4">
											<div id="applyInsertOption"></div>
										</div>
								</div>
								<div class="card-body">
									<h4 class="card-title">상담 희망 날짜</h4>
									<div class="form-group">
										<input type="text" id="datepicker1" placeholder="상담 일짜를 선택해주세요">
									</div>
								</div>
								<div class="card-body">
									<h4 class="card-title">상담 희망 시간</h4>
									<div class="form-group">
<!-- 										<input type="time" id="cstApptm" class="form-control" name="cstApptm"> -->
											<input type="text" id="cstApptm" class="form-control" value="00:00 AM">
										<div class="text-center">
											<span id="spanInsertArea" class="text-danger"></span><br>
											<span id="spanDatePickerArea" class="text-danger"></span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn waves-effect waves-light btn-rounded btn-secondary"
						data-dismiss="modal">취소</button>
					<input type="button" onclick="ApplyCounseling()"
						class="btn waves-effect waves-light btn-rounded btn-success" value="신청">
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<!-- update modal -->
	<div class="modal fade" id="scrollable-modalUpdate" tabindex="-1"
		role="dialog" aria-labelledby="scrollableModalTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-scrollable" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="scrollableModalTitle">상담 신청 수정</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12 col-md-6 col-lg-12">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">상담 신청 교수</h4>
	                               	<input type="hidden" id="cstAccptupdate" name="cstAccpt" value="접수">
										<div class="form-group mb-4">
												<div id="applyUpdateOption"></div>
										</div>
								</div>
								<div class="card-body">
									<h4 class="card-title">상담 희망 날짜</h4>
									<div class="form-group">
										<input type="text" id="datepicker2" placeholder="상담 일자를 선택해주세요">
									</div>
								</div>
								<div class="card-body">
									<h4 class="card-title">상담 희망 시간</h4>
									<div class="form-group">
										<input type="time" id="cstApptmupdate" class="form-control" name="cstApptm">
									</div>
									<div class="text-center">
											<span id="spanUpdateArea" class="text-danger"></span><br>
											<span id="spanDatePickerUpdateArea" class="text-danger"></span>
										</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button"
						class="btn waves-effect waves-light btn-rounded btn-secondary"
						data-dismiss="modal">취소</button>
					<input type="button" onclick="ApplyCounselingUpdate()"
						class="btn waves-effect waves-light btn-rounded btn-success" value="신청">
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.update modal -->



<form id="deleteForm" action="${cPath}/lms/student/counseling/applydelete.do" method="POST">
	<input type="hidden" name="cstNo" id="cstNo"> 
</form>
<script type="text/javascript">
	$.datepicker.setDefaults({
	    dateFormat: 'yy-mm-dd',
	    prevText: '이전 달',
	    nextText: '다음 달',
	    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	    showMonthAfterYear: true,
	    yearSuffix: '년'
	});
	
	let datepickerDate = null;
	let datepickerDate1 = null;
	let regcstDate;
	let datepickerDate2;
	let timePicker1;
	let timePicker2;
	$(function() {
		$("#modalBtn").on("click", function(){
			var trSelect1 = "";
			var trSelect2 = "";
			var trOption1 = "";
			var trOption2 = "";
			$.ajax({
				url :'${cPath}/lms/student/counseling/chooseList.do'
				, method : 'post'
				, dataType : 'json'
				, success : function(resp) {
					let max = resp.chooseList.length
					trSelect1 += ("<select class='custom-select mr-sm-2' id='inlineFormCustomSelect'>")
					trOption2 += ("<option selected=''>선택</option>");
					if(resp.chooseList != null){
						console.log(resp.chooseList);
						for(let i=0; i<max; i++){
							let memName = resp.chooseList[i].memberVO.memName;
							let memId = resp.chooseList[i].memberVO.memId;
							trOption1 += ("<option name='cstProfessor' value="+memId+">"+memName+"</option>");
						}
					}
	 				trSelect2 += ("</select>");
					$("#applyInsertOption").empty();
					$("#applyInsertOption").append(trSelect1+trOption2+trOption1+trSelect2);
			    }
				, error : function(request, error){
			    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			    }
			});
		});
		
		$("#modifyBtn").on("click", function(){
			var trSelect1 = "";
			var trSelect2 = "";
			var trOption1 = "";
			var trOption2 = "";
			$.ajax({
				url :'${cPath}/lms/student/counseling/chooseList.do'
				, method : 'post'
				, dataType : 'json'
				, success : function(resp) {
					let max = resp.chooseList.length
					trSelect1 += ("<select class='custom-select mr-sm-2' id='inlineFormCustomSelectupdate'>")
					trOption2 += ("<option selected=''>선택</option>");
					if(resp.chooseList != null){
						console.log(resp.chooseList);
						for(let i=0; i<max; i++){
							let memName1 = resp.chooseList[i].memberVO.memName;
							let memId1 = resp.chooseList[i].memberVO.memId;
							trOption1 += ("<option name='cstProfessor' value="+memId1+">"+memName1+"</option>");
						}
					}
	 				trSelect2 += ("</select>");
					$("#applyUpdateOption").empty();
					$("#applyUpdateOption").append(trSelect1+trOption2+trOption1+trSelect2);
			    }
				, error : function(request, error){
			    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			    }
			});
		});
		
		$("#applyInsertOption").on('change', '#inlineFormCustomSelect', function(event){
			console.log(event.target.value);
			if(event.target.value != '선택'){
				$("#spanInsertArea").text("");
			}
		});
		
		$("#applyUpdateOption").on('change', '#inlineFormCustomSelectupdate', function(event){
			console.log(event.target.value);
			if(event.target.value != '선택'){
				$("#spanUpdateArea").text("");
			}
		});
		
	    $("#datepicker1").datepicker({
	    	minDate:0
	    	, onSelect : function(dateText){
	    		datepickerDate = this.value;
	    		console.log(datepickerDate);
	    		$("#spanDatePickerArea").text("");
	    	}	
	    	, required : true
	    });
	    $("#datepicker2").datepicker({
	    	minDate:0
	    	, onSelect : function(dateText){
	    		datepickerDate2 = this.value;
	    		$("#spanDatePickerUpdateArea").text("");
	    		console.log("Selected date : " + dateText + "; input's current value: " + this.value);
	    	}	
	    });
	    
//   		$("#inlineFormCustomSelect option:selected").on("change", function(){
//   			console.log("ggggggggggggggggggg");
//   		});
  		
	    $('#cstApptm').timepicker({
	    	timeFormat : 'HH:mm'
	    	, startTime : '00:00'
	    	, interval : 60
	    	, dynamic : false
	    	, dropdown : true
	    	, scrollbar : true
	    	, required : true
	    	, change : function(){
	    		$("#spanInsertArea").text("");
	    		var cstProfessor = $("#inlineFormCustomSelect option:selected").val();
	    		console.log(cstProfessor);
	    		if(cstProfessor == '선택'){
	    			let getValue = $(this).val();
	    			timePicker1 = getValue.substr(0,2);
	    			$("#spanInsertArea").text("교수를 선택해주세요");
	    		}
	    		if(datepickerDate == null){
	    			$("#spanDatePickerArea").text("상담 희망 날짜를 선택해주세요");
	    		}
	    		var reg = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
	    		if(reg.test(datepickerDate)) {
	    			regcstDate = datepickerDate.replace(reg,"");
	    		}
	    		if(cstProfessor != '선택') {
	    			let getValue = $(this).val();
	    			timePicker1 = getValue.substr(0,2);
      			}
	    	}
	    });
	    		
	    	$("#cstApptmupdate").timepicker({
	    		timeFormat : 'HH:mm'
    	    	, startTime : '00:00'
    	    	, interval : 60
    	    	, dynamic : false
    	    	, dropdown : true
    	    	, scrollbar : true
    	    	, required : true
    	    	, change : function(){
    	    		$("spanUpdateArea").text("");
    	    		var cstProfessor = $("#inlineFormCustomSelectupdate option:selected").val();
    	    		if(cstProfessor == '선택'){
    	    			let getValue = $(this).val();
    	    			timePicker2 = getValue.substr(0,2);
    	    			console.log(timePicker2);
    	    			$("#spanUpdateArea").text("교수를 선택해주세요");
    	    		}
    	    		if(datepickerDate2 == null){
    	    			$("#spanDatePickerUpdateArea").text("상담 희망 날짜를 선택해주세요");
    	    		}
    	    		var reg = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
    	    		if(reg.test(datepickerDate1)) {
    	    			regcstDate1 = datepickerDate2.replace(reg,"");
    	    		}
    	    		if(cstProfessor != '선택') {
    	    			let getValue = $(this).val();
    	    			timePicker2 = getValue.substr(0,2);
          			}
    	    	}
	    	});
	});
	
// 	if(timePicker1 != null){
// 		var cstProfessor = $("#inlineFormCustomSelect option:selected").val();
// 		if(cstProfessor == '선택'){
// 			$("#spanInsertArea").text("교수를 선택해주세요");
// 			$(".timepicker").timpicker({defaultTime : '00:00'});
// // 			$("#cstApptm").val('');
// 		} 
// 		var cstProfessor = $("#inlineFormCustomSelect option:selected").val();
// 		if(cstProfessor == '선택'){
// 			timPicker1 = $(this).val("");
// 			$("#spanInsertArea").text("교수를 선택해주세요");
// 			console.log(timePicker1);
// 		}
// 		console.log(time);
// 			$.ajax({
// 		    	url : '${cPath}/lms/student/counseling/selectChooseTime.do'
// 		    	, method : 'post'
// 		    	, data : 
// 		    		{
// 		    			"cstProfessor" : cstProfessor
// 		    		}
// 		    , dataType : "json"
// 		    , success : function(resp){
// 		    	console.log(resp.chooseTimeList);
// 		    	let max = resp.chooseTimeList.length;
// 		    	for(let i=0; i<max; i++){
// 		    		let cstApptm = resp.chooseTimeList[i].cstApptm;
// 		    		if(cstApptm == realTiem){
// 		    			$("#cstApptm").val('');
// 						$("#spanInsertArea").text("이미 상담된 예약이 있습니다.");
// 		    		} else {
// 						$("#spanInsertArea").text("");
// 		    		}
// 		    	}
// 		    }
// 		    , error : function(request, error){
// 		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
// 		    }
// 	   	});
// 	}
	
// 	$("#modifyBtn").hide();
// 	$("#deleteBtn").hide();
	
	function pageLinkNumber(event){
		event.preventDefault();
		let page = $(event.target).data("page");
		searchForm.find("[name='page']").val(page);
		console.log(page);
		searchForm.submit();
		return false;
	}

	var searchForm = $("#searchForm");
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
	
// 	$('input[type="checkbox"][name="checkBox"]').click(function(){
// 		if($(this).prop('checked')){
// 			$('input[type="checkbox"][name="checkBox"]').prop('checked', false);
// 			$(this).prop('checked', true);
// 		}
// 	});
	
// 	var result;
// 	function getCheckboxValue(event) {
// 		if(event.target.checked){
// 			$("#modalBtn").hide();
// 			$("#modifyBtn").show();
// 			$("#deleteBtn").show();
// 			result = event.target.value;
// 		} else {
// 			result ="";
// 			$("#modalBtn").show();
// 			$("#modifyBtn").hide();
// 			$("#deleteBtn").hide();
// 		}
// 	}
	
	$("#inlineFormCustomSelect").change(function(){
		var cstProfessor = this.value;
		console.log(cstProfessor);
		
	});
	
	$("#scrollable-modalUpdate").on("click", function(){
		var cstProfessor = $("#inlineFormCustomSelectupdate option:selected").val();
		console.log(cstProfessor);
	});
	
	
	
	// 상담 등록	
	function ApplyCounseling(){
		var cstProfessor = $("#inlineFormCustomSelect option:selected").val();
		var cstApptm = $("#cstApptm").val();
// 		var cstDate = $("#cstDate").val();
		var reg = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
		var regcstDate = null
// 		if(reg.test(cstDate)) {
// 			regcstDate = cstDate.replace(reg, "");
// 		}
		if(reg.test(datepickerDate)){
			regcstDate = datepickerDate.replace(reg,"");
		}		
		var realcstApptm = cstApptm.substr(0,2);
		var cstAccpt = $("#cstAccpt").val();
		if(cstProfessor == "선택" || realcstApptm== "" || regcstDate== null){
			alert("항목을 입력해주세요");
			return;
		} 
		var promise = $.ajax({
		    	url : '${cPath}/lms/student/counseling/selectChooseTime.do'
		    	, method : 'post'
		    	, data : 
		    		{
		    			"cstProfessor" : cstProfessor
		    		}
		    , dataType : "json"
		    , success : function(resp){
		    	console.log(resp.chooseTimeList);
		    	let max = resp.chooseTimeList.length;
		    	for(let i=0; i<max; i++){
		    		let cstApptmChoose = resp.chooseTimeList[i].cstApptm;
		    		let cstDateChoose = resp.chooseTimeList[i].cstDate;
		    		if(cstApptmChoose == timePicker1 && parseInt(regcstDate) == parseInt(cstDateChoose)){
		    			console.log(cstApptmChoose);
		    			console.log(timePicker1);
		    			console.log(regcstDate);
		    			console.log(cstDateChoose);
		    			$("#cstApptm").val('');
						$("#spanInsertArea").text("이미 상담된 예약이 있습니다.");
						return;
		    		}
		    	}
		    }
	   	});
		promise.done(function(){
			$("#spanInsertArea").text("");
				$.ajax({
	 		    	url : '${cPath}/lms/student/counseling/applyInsert.do'
	 		    	, method : 'post'
	 		    	, data : 
	 		    		{
	 		    			"cstProfessor" : cstProfessor
	 		    			, "cstDate" : regcstDate
	 		    			, "cstApptm" : realcstApptm
	 		    			, "cstAccpt" : cstAccpt
	 		    		}
	 		    , dataType : "json"
	 		    , success : function(resp){
	 		    	$("#scrollable-modal").modal('hide');
	 		    	location.href="${cPath}/lms/student/counseling/apply.do";
	 		    }
	 		    , error : function(request, error){
	 		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
	 		    }
	 	    });
		});
	}
	
	
	// 상담 수정
	function ApplyCounselingUpdate(){
		var cstProfessor = $("#inlineFormCustomSelectupdate option:selected").val();
		var cstApptm = $("#cstApptmupdate").val();
// 		var cstDate = $("#cstDateupdate").val();
		var cstNo = $("#cstNo").val()
		var reg = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
		var regcstDate1 = null
// 		if(reg.test(cstDate)) {
// 			regcstDate1 = cstDate.replace(reg, "");
// 		}
		if(reg.test(datepickerDate2)){
			regcstDate1 = datepickerDate2.replace(reg,"");
		}	
		var realcstApptm1 = cstApptm.substr(0,2);
		var cstAccpt1 = $("#cstAccptupdate").val();
		if(cstProfessor == "선택" || regcstDate1 == "" || realcstApptm1 == null){
			alert("항목을 입력해주세요");
			return;
		} 
			
		var promise =	$.ajax({
		    	url : '${cPath}/lms/student/counseling/selectChooseTime.do'
		    	, method : 'post'
		    	, data : 
		    		{
		    			"cstProfessor" : cstProfessor
		    		}
		    , dataType : "json"
		    , success : function(resp){
		    	console.log(resp.chooseTimeList);
		    	let max = resp.chooseTimeList.length;
		    	for(let i=0; i<max; i++){
		    		let cstApptm = resp.chooseTimeList[i].cstApptm;
		    		let cstDate = resp.chooseTimeList[i].cstDate;
		    		if(cstApptm == timePicker2 && parseInt(regcstDate1) == parseInt(cstDate)){
		    			$("#cstApptmupdate").val('');
		    			$("#spanUpdateArea").text("이미 상담된 예약이 있습니다.");
		    			return;
		    		}
		    	}
		    }
	   	});
		    		
		    
		promise.done(function(){
			let cstNo = $("#cstNo").val()
			$("#spanUpdateArea").text("");
			 $.ajax({
			    	url : '${cPath}/lms/student/counseling/applyupdate.do'
			    	, method : 'post'
			    	, data : 
			    		{
			    			"cstProfessor" : cstProfessor
			    			, "cstDate" : regcstDate1
			    			, "cstApptm" : realcstApptm1
			    			, "cstAccpt" : cstAccpt1
			    			, "cstNo" : cstNo
			    		}
			    , dataType : "json"
			    , success : function(data){
			    	$("#scrollable-modalUpdate").modal('hide');
			    	location.href="${cPath}/lms/student/counseling/apply.do";
			    }
			    , error : function(request, error){
			    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			    }
		    });
		});
	}
	
	// 상담 삭제
	
	function deleteCounselingApply(cstNum){
		$("#delModal").modal();
		$("#goDelBtn").on("click",function(){
		$("#cstNo").val(cstNum);
		$("#deleteForm").submit();
		// 		$.ajax({
// 			url : '${cPath}/lms/student/counseling/applydelete.do'
// 		    	, type : 'post'
// 		    	, data : {"cstNo" : cstNo}
// 		    , dataType : "json"
// 		    , success : function(data){
// 		    	location.href="${cPath}/lms/student/counseling/apply.do";
// 		    }
// 		    , error : function(request, error){
// 		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
// 		    }
// 		});
		});
	}
	

	$("#cstApptm").on("click",function(){
		var cstProfessor = $("#inlineFormCustomSelect option:selected").val();
		if(cstProfessor == '선택'){
			$("#spanInsertArea").text("교수를 선택해주세요");
			$("#cstApptm").val('');
		} 
	});
	
	$("#cstApptmupdate").on("click",function(){
		var cstProfessor = $("#inlineFormCustomSelectUpdate option:selected").val();
		if(cstProfessor == '선택'){
			$("#spanUpdateArea").text("교수를 선택해주세요");
			$("#cstApptmupdate").val('');
		}
	});
	
// 	$('.timepicker').timepicker();
// 	$('#cstApptm').on("change", function() {
// 		let test111 = $(this).val();
// 		console.log(test111);
//     });
// 		$('#cstApptm').timepicker();
// 		$('#cstApptmr').on('selectTime',function(){
// 	    	console.log("click");
// 	    	console.log(this.value);
// 	    	var time = $("#cstApptm").timepicker('getTime');
// 	    	console.log(time);
// 	    });
	
	// 상담 신청(insert), 상담 시간 중복 방지 
// 	$("#cstApptm").on("change",function(){
// 		let time = this.value;
// 		console.log(time);
// 		let realTiem = time.substr(0,2)
// 		var cstProfessor = $("#inlineFormCustomSelect option:selected").val();
// 		if(cstProfessor == '선택'){
// 			$("#spanInsertArea").text("교수를 선택해주세요");
// 			$("#cstApptm").val('');
// 		} 
// 		console.log(time);
// 			$.ajax({
// 		    	url : '${cPath}/lms/student/counseling/selectChooseTime.do'
// 		    	, method : 'post'
// 		    	, data : 
// 		    		{
// 		    			"cstProfessor" : cstProfessor
// 		    		}
// 		    , dataType : "json"
// 		    , success : function(resp){
// 		    	console.log(resp.chooseTimeList);
// 		    	let max = resp.chooseTimeList.length;
// 		    	for(let i=0; i<max; i++){
// 		    		let cstApptm = resp.chooseTimeList[i].cstApptm;
// 		    		if(cstApptm == realTiem){
// 		    			$("#cstApptm").val('');
// 						$("#spanInsertArea").text("이미 상담된 예약이 있습니다.");
// 		    		} else {
// 						$("#spanInsertArea").text("");
// 		    		}
// 		    	}
// 		    }
// 		    , error : function(request, error){
// 		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
// 		    }
// 	   	});
// 	});
		
	$("#cstApptmupdate").on("change",function(){
		var cstProfessor = $("#inlineFormCustomSelectupdate option:selected").val();
		if(cstProfessor == '선택'){
			$("#spanUpdateArea").text("교수를 선택해주세요");
			$("#cstApptm").val('');
		} 
	});
	
</script>