<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style type="text/css">
	input, textarea{
 		width: 100%;
 		border: none;
 	}
	#img{
		width: 200px;
		height: 200px;
    	object-fit: cover;
	}
</style>
<div class="container-fluid">
	<div class="row">
		<div class="col-12">
			<h3 class="ml-2">
				<i class="fas fa-home"></i> 상담 관리 > 상담 내역 > 상담 내용
			</h3>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">상담 등록 및 조회</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">학생의 개인 정보 조회 및 상담을 등록할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="col-12">
				<div class="card">
					<table class="table mb-0 text-center">
						<tbody>
							<tr>
								<td rowspan="3" class="w-25" colspan="2" >
									<c:if test="${not empty counDetail.memberVO.memImg }">
										<img id="img" src="${cPath}/memberImages/${counDetail.memberVO.memImg }" class="dark-logo"/>
									</c:if>
									<c:if test="${ empty counDetail.memberVO.memImg }">
										<img id="img" src="${cPath }/images/defaultImg.jpg" class="dark-logo" />
									</c:if>
								</td>
								<td class="table-active" style="vertical-align: middle; "><p class="font-weight-bold">학번</p></td>
								<td style="vertical-align: middle; ">${counDetail.memberVO.memId }</td>
								<td class="table-active" style="vertical-align: middle; "><p class="font-weight-bold">이름</p></td>
								<td style="vertical-align: middle; ">${counDetail.memberVO.memName }</td>
							</tr>
							<tr>
								<td class="table-active" style="vertical-align: middle; "><p class="font-weight-bold">소속</p></td>
								<td style="vertical-align: middle; ">${counDetail.departVO.depName }</td>
								<td class="table-active" style="vertical-align: middle; "><p class="font-weight-bold">학년</p></td>
								<td style="vertical-align: middle; ">
								<fmt:formatNumber var="grd" value="${(counDetail.memberVO.memGrd/2 + 0.5) - ((counDetail.memberVO.memGrd/2 +0.5)%1)}"/>
									${grd}학년
								</td>
							</tr>
							<tr>
								<td class="table-active" style="vertical-align: middle; "><p class="font-weight-bold">전화번호</p></td>
								<td style="vertical-align: middle; ">${counDetail.memberVO.memTel }</td>
								<td class="table-active"><p class="font-weight-bold">상담날짜</p></td>
								<td style="vertical-align: middle; ">
									<select class="custom-select mr-sm-2" id="inlineFormCustomSelect">
										<c:set var="selectDate" value="${selectDate }"/>
										<option>선택</option>
										<c:forEach items="${selectDate }" var="date">
										<option name="cstDate" value="${date.cstNo}">${date.cstDate }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr style="height: 200px;">
								<td class="table-active" style="vertical-align: middle;"><p class="font-weight-bold">상담 내용</p></td>
								<td colspan="6" style="vertical-align: middle; ">
<!-- 									<textarea id="cstContentArea" style="display: none;"> -->
<!-- 										<span id="cstContentText"></span> -->
<!-- 									</textarea> -->
									<div id="cstContentArea">
										<span id="cstContentText"></span>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<div class="col-12">
				<div class="card">
					<div class="table">
						<div class="table-responsive">
<!-- 							<table class="table table-bordered table-striped mb-0"> -->
							<table class="table mb-0 text-center">
								<tbody>
									<tr>
										<th class="text-nowrap" scope="row">상담 날짜</th>
										<td colspan="5">
											<input type="hidden" id="cstDate" name="cstDate"> 
											<select class="custom-select mr-sm-2" id="inlineFormCustomSelectupdate">
											<c:set var="selectDate" value="${selectDate }"/>
												<option selected="">선택</option>
											<c:forEach items="${selectDate }" var="date">
												<option  id="cstDate" value="${date.cstNo}">${date.cstDate }</option>
											</c:forEach>
										</select>
										</td>
								   </tr>
									<tr>
										<th class="text-nowrap" scope="row">상담 내용</th>
										<td colspan="5">
											<textarea id="cstContent" name="cstContent" class="ckeditor"rows="3" placeholder="내용을 입력하세요.">${counDetail.cstContent }</textarea>
										</td>
									</tr>
									<tr>
										<th class="text-nowrap" scope="row">상담한 시간</th>
										<td colspan="5">
											<input type="number" class="form-control" id="cstTime" name="cstTime"/>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div id="btnArea" class="d-flex justify-content-end align-items-center">
				<button type="button" class="btn waves-effect waves-light btn-light px-4 py-2 mx-2" id="resetBtn">취소</button>
				<button type="button" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2" id="insertBtn">등록</button>
			</div>
		</div>
	</div>
</div>
<!-- 개인 정보에서 상담 날짜 선택시, 아직 등록하지 않는 상담이라면 모달창 띄움 -->
<input type="hidden" id="modalCstContentNull"class="btn btn-secondary" data-toggle="modal" data-target="#centermodalAccpt" value="숨겨진모달">
<div class="modal fade" id="centermodalAccpt" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    </div>
		    <div class="modal-body">
		        <h5 style="text-align: center;">아직 등록되지 않았습니다. 상담을 등록해주세요</h5>
		    </div>
		</div>
	</div>
</div>
<!-- 상담 등록 성공시 , 모달창 오픈 -->
<input type="hidden" id="modalInsertSeccess"class="btn btn-secondary" data-toggle="modal" data-target="#modalCounselingInsert" value="숨겨진모달">
<div class="modal fade" id="modalCounselingInsert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    </div>
		    <div class="modal-body">
		        <h5 style="text-align: center;">상담이 등록되었습니다.</h5>
		    </div>
		</div>
	</div>
</div>

<script type="text/javascript">
	// 상담 등록 폼에서 selectbox 선택 눌렀을 때, 상담 내용과 상담 시간 초기화 시키기
	let cstDate;
	$("#inlineFormCustomSelectupdate").change(function(){
		let cstDate = this.value;
		let cstTime = null;
		if(cstDate == "선택"){
			$("#cstTime").val(cstTime);
    		CKEDITOR.instances['cstContent'].setData('');
		}
		$("#cstDate").val(cstDate);
	});
	
	let priCst;
	// 개인정보 상담 날짜 선택하면 밑에 상담 내용 뜨게 하는 기능
	$("#inlineFormCustomSelect").change(function(){
		let cstStudent = ${counDetail.memberVO.memId };
		priCst = $("#inlineFormCustomSelect option:selected").val();
		if(priCst == '선택'){
			$("#cstContentText").empty();
			return;
		} 
		$.ajax({
	    	url : '${cPath}/lms/professor/counseling/chooseDate.do'
	    	, method : "post"
	    	, data :
	    	{
	    		"cstStudent" : cstStudent
	    		, "cstNo" : priCst
	    	}
		    , dataType : "json"
		    , success : function(resp){
		    	if(resp.counVO.cstContent== null){
					$("#cstContentText").text("상담 내용을 등록하지 않았습니다.");
				}else{
					let counText = resp.counVO.cstContent;
					$("#cstContentText").html(counText.replace(/(<([^>]+)>)/ig,"<br>"));
				}
		    }
		    , error : function(request, error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
		    }
	 	});
	});
	let test;
	// 상담 등록 폼에서 셀렉트 박스에 있는 값에 딸려온 데이터를 상담 시간과 상담 내용에 넣기
	$("#inlineFormCustomSelectupdate").change(function(){
		let cstStudent = ${counDetail.memberVO.memId };
		let cstNoupdate = $("#inlineFormCustomSelectupdate option:selected").val();
		if(cstNoupdate == "선택"){
			return;
		}
		$.ajax({
	    	url : '${cPath}/lms/professor/counseling/chooseDate.do'
	    	, method : "post"
	    	, data :
	    	{
	    		"cstStudent" : cstStudent
	    		, "cstNo" :cstNoupdate
	    	}
		    , dataType : "json"
		    , success : function(resp){
		    	let resultcstDate = resp.counVO.cstDate;
		    	let cstContent = resp.counVO.cstContent;
		    	let cstTime = resp.counVO.cstTime;
		    	$("#cstTime").empty();
		    	$("#cstTime").val(cstTime);
		    	CKEDITOR.instances['cstContent'].setData(cstContent);
		    }
		    , error : function(request, error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
		    }
	 	});
	});
	let test1;
	// 상담 등록하기
	let counselingInsertForm = $("#counselingInsertForm");
	$("#insertBtn").on("click",function(){
		let cstContent = CKEDITOR.instances['cstContent'].getData();
		let cstStudent = ${counDetail.memberVO.memId };
		let cstNoupdate = $("#inlineFormCustomSelectupdate option:selected").val();
		cstDate = $("#cstDate").val();
		let cstTime = $("#cstTime").val();
		if(cstDate !=""){
			 $.ajax({
		    	url : '${cPath}/lms/professor/counseling/proInsert.do'
		    	, method : "post"
		    	, data : 
		    		{
		    			"cstStudent" : cstStudent
		    			, "cstContent" : cstContent
		    			, "cstTime" : cstTime
		    			, "cstNo" : cstNoupdate
		    		}
			    , dataType : "json"
			    , success : function(resp){
			    	$("#modalInsertSeccess").click();
			    	CKEDITOR.instances['cstContent'].setData('');
			    	$("#cstTime").val('');
			    	if($("#inlineFormCustomSelect option:selected").val() != '선택'){
						$("#cstContentText").html(cstContent);
			    	}
			    	$("#inlineFormCustomSelectupdate").val(priCst).prop('selected', true);
			    }
			    , error : function(request, error){
			    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			    }
	 		});
		}else{
			alert("상담 날짜를 입력하세요");
		}
	});
	
	// Reset 버튼!
	$("#resetBtn").on("click", function(){
		$("#cstContentArea").empty();
		$("#inlineFormCustomSelectupdate option:first").prop('selected', true);
		$("#inlineFormCustomSelect option:first").prop('selected', true);
		CKEDITOR.instances['cstContent'].setData('');
    	$("#cstTime").val('');
	});
	
	
</script>
