<%--
@author 조예슬
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="${cPath}/css/admin/lecture.css">
<title>강의 등록</title>
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 교과목관리 > 강의등록
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-admin p-0">
		<div class="col-12">
			<h2 class="font-weight-bold">강의등록</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">개설되는 강의를 등록할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="card">
				<div class="card-body">
					<form:form method="post" id="insertLecForm" commandName="lecVO"
						action="${cPath }/admin/insertLecture.do">
						<div class="row mb-3">
							<div class="selectBoxArea col-md-12 d-flex" >
							<input type="hidden" name="lecDays" value="15">
								<div class="p-2 w-25">
								<h4 class="text-center font-weight-bold">학부</h4>
								<select  id="college" name="colCode" onChange="selectCollege()" 
									class="form-control w-100" >
									<option value="0">학부 선택</option>
								</select>
								</div>
								<div class="p-2 w-25">
								<h4 class="text-center font-weight-bold">학과</h4>
								<select class="form-control w-100" name="depNo"
									id="department" onChange="selectDepartment()">
									<option>학과 선택</option>
								</select>
								</div>
								<div class="p-2 w-25">
								<h4 class="text-center font-weight-bold">강의실</h4>
								<select class="form-control w-100" name="roomCode" onChange="selectLecRoom()"
									id="lecRoom" required >
									<option>강의실 선택</option>
								</select>
								</div> 
								<div class="p-2 w-25">
								<h4 class="text-center font-weight-bold">교수</h4>
								<select name="memId" id="proList" class="form-control w-100" onChange="selectProfessor()" required>
									<option>교수선택</option>
								</select>
								</div>
								<div class="p-2 w-25">
								<h4 class="text-center font-weight-bold">과목</h4>
								<select class="form-control w-100" id="subject" name="subCode" onChange="selectSubject()"
									id="subject" required >
									<option>과목 선택</option>
								</select>
								</div> 
							</div>
						</div>
						<div class="row d-flex">
							<div class="col-6 pr-0">
							<p class="text-center"><span class="text-danger">*</span> &lt사용가능 강의실/시간&gt</p>
							<table id="timeTable"  class="table-bordered" title="이용가능 시간표">
								<tbody align=center>
									<tr>
										<th class="bg-primary text-white">시간/요일</th>
										<th class="bg-primary text-white">월</th>
										<th class="bg-primary text-white">화</th>
										<th class="bg-primary text-white">수</th>
										<th class="bg-primary text-white">목</th>
										<th class="bg-primary text-white">금</th>
									</tr>
									<c:forEach var="i" begin="1" end="9" step="1">
<!-- 									  <tr> -->
<%-- 										  <td class="timeTalbeTd w-25" >${i }교시</td> --%>
<!-- 											<td class="select-chkbox text-dark"> -->
<%-- 												<input type="checkbox" name="chk" class="chk" value="mon-${i}"> --%>
<!-- 												<div class="select-room"></div> -->
<!-- 											</td> -->
<!-- 											<td class="select-chkbox text-dark"> -->
<%-- 												<input type="checkbox" name="chk" class="chk" value="tue-${i}"> --%>
<!-- 												<div class="select-room"></div> -->
<!-- 											</td> -->
<!-- 											<td class="select-chkbox text-dark"> -->
<%-- 												<input type="checkbox" name="chk" class="chk" value="wed-${i}"> --%>
<!-- 												<div class="select-room"></div> -->
<!-- 											</td> -->
<!-- 											<td class="select-chkbox text-dark"> -->
<%-- 												<input type="checkbox" name="chk" class="chk" value="thu-${i}"> --%>
<!-- 												<div class="select-room"></div> -->
<!-- 											</td> -->
<!-- 											<td class="select-chkbox text-dark"> -->
<%-- 												<input type="checkbox" name="chk" class="chk" value="fri-${i}"> --%>
<!-- 												<div class="select-room"></div> -->
<!-- 											</td> -->
<!-- 									  </tr> -->
									  <tr>
										  <td class="timeTalbeTd w-25" >${i }교시</td>
											<td class="select-chkbox text-dark">
												<input type="checkbox" name="chk" class="chk" value="mon-${i}">
												<div class="select-room"></div>
											</td>
											<td class="select-chkbox text-dark">
												<input type="checkbox" name="chk" class="chk" value="tue-${i}">
												<div class="select-room"></div>
											</td>
											<td class="select-chkbox text-dark">
												<input type="checkbox" name="chk" class="chk" value="wed-${i}">
												<div class="select-room"></div>
											</td>
											<td class="select-chkbox text-dark">
												<input type="checkbox" name="chk" class="chk" value="thu-${i}">
												<div class="select-room"></div>
											</td>
											<td class="select-chkbox text-dark">
												<input type="checkbox" name="chk" class="chk" value="fri-${i}">
												<div class="select-room"></div>
											</td>
									  </tr>
									</c:forEach>
								</tbody>
							</table>
							<p class="text-center"><span class="text-info">강의실 선택후 가능 시간을 확인할 수 있습니다.</span></p>
							</div>
							<div id="div" class="col-6 pl-0 mt-5">
								<table class="inputTable table w-75 text-center">
									<tr>
										<td class="table-active">강의명</td>
										<td>
											<input type="text" id="lectureName" class="form-control" required readonly>
										</td>
									</tr>
									<tr>
										<td class="table-active"><span class="text-danger">*</span> 강의 정원</td>
										<td>
											<input type="number" min="0" max="1000" maxlength="3" name="lecFull" oninput="maxLengthCheck(this)" required class="form-control">
											<form:errors path="lecFull" element="span" cssClass="error"/>
										</td>
									</tr>
									<tr>
										<td class="table-active"><span class="text-danger">*</span> 개설학년</td>
										<td>
											<select name="lecGrd" class="form-control" required="required">
												<option value>선택</option>
												<c:forEach var="idx" begin="1" end="4" step="1">
													 	<option value="${idx}" <c:out value="${lecVO.lecGrd eq idx ? 'selected' :'' } "/>>${idx}학년</option>
												</c:forEach>
											</select>
											<form:errors path="lecGrd" element="span" cssClass="error"/>
										</td>
									</tr>
									<tr>
										<td class="table-active">개설학기</td>
										<td>
											<input type="text" id="lecSmst" name="lecSmst" value="${currentSmst.codeName }" class="form-control" readonly>
											<form:errors path="lecSmst" element="span" cssClass="error"/>
										</td>
									</tr>
								</table>
								<div id="btnArea" class="text-right">
									<button id="insertLectureBtn" type="button" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-1">등록</button>
<!-- 									<button type="reset" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1">취소</button> -->
								</div>
							</div>
						</div>	
					</form:form>
				</div> 
			</div>
		</div>
	</div>		
</div>	
<script>
const validateOptions = {
        onsubmit:true,
        onfocusout:function(element, event){
           return this.element(element);
        },
        errorPlacement: function(error, element) {
           element.tooltip({
              title: error.text()
              , placement: "right"
              , trigger: "manual"
              , delay: { show: 500, hid: 100 }
           }).on("shown.bs.tooltip", function() {
              let tag = $(this);
              setTimeout(() => {
                 tag.tooltip("hide");
              }, 3000)
           }).tooltip('show');
          }
     }

$("#insertLecForm").validate(validateOptions);


function maxLengthCheck(object){
    if (object.value.length > object.maxLength){
        object.value = object.value.slice(0, object.maxLength);
    }    
}



$(function(){
	// 맨처음 강의실 클릭 못하게 막아두기 
	$('input:checkbox').parent('.select-chkbox').addClass('select-disable');
	$('input:checkbox').attr("disabled",true);
// 학부 코드 가져오는 ajax
	$.ajax({
		url : "${cPath}/admin/insertLectureGetCollege.do"
			,type : "get"
			,dataType : "json"
			,success : function(data){
				makeCollegeSelect(data);
			}
	});
	// check박스 변경
	$('.select-room').click(function(){
			
		let checker = $(this).siblings('.chk');
		
		if(checker.prop('checked')){
			$(this).parent('.select-chkbox').removeClass('select-active');
			$(checker).prop('checked',false);
		}else{
			if(!checker.is(":disabled")){
				$(this).parent('.select-chkbox').addClass('select-active');
				$(checker).prop('checked',true);
			}
		}
		
	})
});
// 학부 코드 select 생성
function makeCollegeSelect(data){
	console.log(data);
	let colhtml = "<option value='0'>학부선택</option>";
	for(let i = 0 ; i < data.collegeList.length ; i++){
		colhtml += '<option value='+data.collegeList[i].code+'>'+data.collegeList[i].codeName+'</option>';
	}
	$("#college").html(colhtml);
	
	$("#lecSmst").val(data.currentSmst.codeName);
	
}

// 학부 선택시 학과가져오기
function selectCollege(){
	$('input:checkbox').parent('.select-chkbox').addClass('select-disable');
	$('input:checkbox').attr("disabled",true);
	
	let collegeList = document.getElementById("college");
	let college = collegeList.options[collegeList.selectedIndex].value;
	if(college!=null || college!=0 || college!=undefined){
		$.ajax({
			url : "${cPath}/admin/selectLecCollege.do?college="+college
				,type : "get"
				,dataType : "json"
				,success : function(data){
					makeDepartandRoomSelect(data);
				}
		});
	}
};

// 학과 옵션 만들기
function makeDepartandRoomSelect(data){
	let dephtml = "<option value='0'>학과선택</option>";
	for(let i = 0 ; i < data.department.length ; i++){
		dephtml += '<option value='+data.department[i].depNo+'  >'+data.department[i].depName+'</option>';
	}
	$("#department").html(dephtml);
	
	let roomhtml = "<option value='0'>강의실선택</option>";
	for(let i = 0 ; i < data.lecRoom.length ; i++){
		roomhtml += '<option value='+data.lecRoom[i].code+' >'+data.lecRoom[i].codeName+'</option>';
	}
	$("#lecRoom").html(roomhtml);
};

// 학과 선택시 개설 과목 가져오기
function selectDepartment(){
	$('input:checkbox').parent('.select-chkbox').addClass('select-disable');
	$('input:checkbox').attr("disabled",true);
	
	let depList = document.getElementById("department");
	let depNo = depList.options[depList.selectedIndex].value;
	if(depNo!=null || depNo!=0 || depNo!=undefined){
		$.ajax({
			url : "${cPath}/admin/selectLecDepartment.do?depNo="+depNo
				,type : "get"
				,dataType : "json"
				,success : function(data){
					makeSubjectSelect(data);
				}
		});
	}
};

// 과목 옵션 만들기
function makeSubjectSelect(data){
	let html = "<option value='0'>과목선택</option>";
	for(let i = 0 ; i < data.subject.length ; i++){
		html += '<option value='+data.subject[i].subCode+' >'+data.subject[i].subName+'</option>';
	}
	$("#subject").html(html);
	
	console.log(data);
	let html2 = "<option value='0'>지도교수</option>";
	for(let i = 0 ; i < data.proList.length ; i++){
		html2 += '<option value='+data.proList[i].memId+' >'+data.proList[i].memName+'['+data.proList[i].memId+']'+'</option>';
	}
	$("#proList").html(html2);
	
};

// 과목 select
function selectSubject(){
	
	let lectureName = $("#subject option:selected").text()
	console.log(lectureName);
	$("#lectureName").val(lectureName);
}

// 강의실 선택시 
function selectLecRoom(){	
	$('input:checkbox').parent('.select-chkbox').removeClass('select-disable');
	$('input:checkbox').attr("disabled",false);
	 
	$("#proList option:selected").prop("selected", false);
	
	let college = $("#college option:selected").val();
	let department = $("#department option:selected").val();
	let subject = $("#subject option:selected").val();
	
	if(college==0||department==0){
		alert("모든 항목을 선택 후 강의실 지정가능합니다.");
		return;
	}
	
	let roomList = document.getElementById("lecRoom");
	let room = roomList.options[roomList.selectedIndex].value;
	if(room!=null || room!=0 || room!=undefined){
		$.ajax({
			url : "${cPath}/admin/selectLectureRoom.do"
				,type : "post"
				,data : $("#insertLecForm").serialize()
				,dataType : "json"
				,success : function(data){
					makeWeekSelect(data);
				}
		});
	}
	
	
};
// });

// 교수선택시
function selectProfessor(){
	let proList = document.getElementById("proList");
	let professor = proList.options[proList.selectedIndex].value;
	if(professor!=null || professor!=0 || professor!=undefined){
		$.ajax({
			url : "${cPath}/admin/selectLectureProfessor.do"
				,type : "post"
				,data : $("#insertLecForm").serialize()
				,dataType : "json"
				,success : function(data){
					makeWeekSelect(data);
				}
		});
	}
	
};


// 해당강의실 비어있는 요일, 시간 만들기
function makeWeekSelect(data){
	let roomListVO = data.roomListVO;
	console.log(roomListVO);
	let week = ['월','화','수','목','금'] ;
	let weekName = ['mon','tue','wed','thu','fri'] ;
	for(let i=0; i<roomListVO.length;i++){
		
		for(let a=0; a<roomListVO[i].lecTimeList.length;a++){
			for(let day=0 ; day<5 ; day++){
				if(roomListVO[i].lecTimeList[a].ltimeDay == week[day]){
					for(let j=roomListVO[i].lecTimeList[a].ltimeStart;j<=roomListVO[i].lecTimeList[a].ltimeEnd;j++ ){
					 $('input:checkbox[value='+weekName[day]+'-'+j+']').parent('.select-chkbox').addClass('select-disable');
					 $('input:checkbox[value='+weekName[day]+'-'+j+']').attr("disabled",true);
					}
				}
			}
		}
// 		for(let a=0; a<roomListVO[i].lecTimeList.length;a++){
// 			switch (roomListVO[i].lecTimeList[a].ltimeDay) {
// 			case "월":
// 				for(let j=roomListVO[i].lecTimeList[a].ltimeStart;j<=roomListVO[i].lecTimeList[a].ltimeEnd;j++ ){
// 					 $('input:checkbox[value=mon-'+j+']').parent('.select-chkbox').addClass('select-disable');
// 					 $('input:checkbox[value=mon-'+j+']').attr("disabled",true);
// 				}
// 				break;
// 			case "화":
// 				for(let j=roomListVO[i].lecTimeList[a].ltimeStart;j<=roomListVO[i].lecTimeList[a].ltimeEnd;j++){
// 					$('input:checkbox[value=tue-'+j+']').parent('.select-chkbox').addClass('select-disable');
// 					$('input:checkbox[value=tue-'+j+']').attr("disabled",true);
// 				}
// 				break;
// 			case "수":
// 				for(let j=roomListVO[i].lecTimeList[a].ltimeStart;j<=roomListVO[i].lecTimeList[a].ltimeEnd;j++ ){
// 					$('input:checkbox[value=wed-'+j+']').parent('.select-chkbox').addClass('select-disable');
// 					$('input:checkbox[value=wed-'+j+']').attr("disabled",true);
// 				}
// 				break;
// 			case "목":
// 				for(let j=roomListVO[i].lecTimeList[a].ltimeStart;j<=roomListVO[i].lecTimeList[a].ltimeEnd;j++ ){
// 					$('input:checkbox[value=thu-'+j+']').parent('.select-chkbox').addClass('select-disable');
// 					$('input:checkbox[value=thu-'+j+']').attr("disabled",true);
// 				}
// 				break;
// 			case "금":
// 				for(let j=roomListVO[i].lecTimeList[a].ltimeStart;j<=roomListVO[i].lecTimeList[a].ltimeEnd;j++){
// 					$('input:checkbox[value=fri-'+j+']').parent('.select-chkbox').addClass('select-disable');
// 					$('input:checkbox[value=fri-'+j+']').attr("disabled",true);
// 				}
// 				break;
// 			}
			
// 		}
	}
	
};	


$("#insertLectureBtn").on("click",function(e){
	e.preventDefault();
	console.log("클릭");
	let checkbox = $("input[name=chk]:checked");
	
	let lecGrd = $("input[name='lecGrd']").val();
	
	if(lecGrd==0||lecGrd==""){
		alert("필수 값을 입력해 주세요.");
		return false;
	}
	
	console.log(checkbox);
	if(checkbox.length==0){
		alert("강의실을 선택해주세요.");
		return false;
	}
	if(checkbox.length>4){
		alert("4시간 이상 수업 불가합니다.");
		return false;
	}
	
	// 동적으로 강의실 input 태그 만들어주기
	checkbox.each(function(i) {
		let weekArr = (checkbox[i].value).split('-');
		let name = weekArr[0];
		let time = weekArr[1];
		let input1 = document.createElement('input');
		input1.setAttribute("type", "hidden"); 
		input1.setAttribute("class", "hiddenInput"); 
		input1.setAttribute("name", "timeList["+(i+1)+"]."+name+"");
		input1.setAttribute("value",time);
		$("#insertLecForm").append(input1);
	});
	
	$.ajax({
		url : "${cPath}/admin/insertLecture.do"
			,method : 'post'
			,data : $("#insertLecForm").serialize()
			,dataType : "json"
			,success : function(resp){
				console.log(resp);
				if(resp.msg=="성공"){
					alert("등록 성공했습니다.");
					// 등록 성공 후 강의 상세 페이지로 보내기 
					location.href="${cPath}/admin/lectureView.do?lecCode="+resp.lecCode;
				}else{
					alert(resp.msg);
					// 실패하고 돌아왔을때 
					// 그대로 남아있는 강의실 hidden 으로 들어갔던 것들 지우기
					$(".hiddenInput").remove();
					
					
				}
			}
		    , error : function(request, error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
		    }
	});
	return false;
});


</script>
