<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />	
<div class="container-fluid">
	<form:form commandName="taskVO" id="taskForm" action="${cPath}/myclass/${lecCode}/taskInsert.do" method="post">
	<form:hidden path="taskNo" />
	<div class="card">
		<div class="card-body">
				<div class="row">
					<div class="col-12">
						<h5 class="ml-2">
							<i class="fas fa-home"></i> 내 강의실 > 학습 정보 > 학습 공지
						</h5>
					</div>
				</div>
				<div class="row p-0 my-2">
					<div class="col-9 text-left">
						<h3 class="font-weight-bold">과제 등록</h3>
					</div>
				</div>
			<div class="table-responsive">
				<table class="table table-bordered table-striped mb-0">
					<tbody>
						<tr>
							<th class="text-nowrap" onClick="putData()" scope="row">과제명</th>
							<td colspan="5">
								<form:input path="taskTitle" cssClass="form-control"
								id="placeholder" placeholder="과제명을 입력하세요." cssStyle="width:50%;" required="required" />
								<form:errors path="taskTitle" element="span" cssClass="error"/>
							</td>
						</tr>
						<tr>
							<th class="text-nowrap" scope="row">제출 인정 시간</th>
							<td colspan="5">
									<div class="row">
										<div class="col-7 form-inline">
											<i class="fas fa-calendar-minus"></i>&nbsp;
											<form:input type="text" id="startDate" path="taskSday"
												cssClass="form-control" required="required" /> -
											<i class="fas fa-calendar-minus"></i>&nbsp;
											<form:input type="text" id="endDate" path="taskDday"
												cssClass="form-control" required="required" />
										</div>
										<div class="col-lg-4">
											<span class="badge badge-warning">주의</span> 제출 인정 시간은 등록 시간보다
											빠를 수 없습니다.
										</div>
									</div>
								</td>
						</tr>
						<tr>
							<th class="text-nowrap" scope="row">과제 배점</th>
							<td colspan="5">
								<div class="grid-structure">
								    <div class="row">
								        <div class="col-lg-5">
								        	<form:select path="taskCredit" cssClass="form-control" cssStyle="width:50%;">
												<c:forEach var="i" begin="1" end="10">
		                                            <form:option value="${i}">${i}</form:option>
												</c:forEach>
								        	</form:select>
								        </div>
								        <div class="col-lg-7">
								        	<span class="badge badge-warning">주의</span>
								        	과제 배점은 최대 10점까지 입니다.
								        </div>
								    </div>
								</div>
							</td>
						</tr>
						<tr>
							<th class="text-nowrap" scope="row">내용</th>
							<td colspan="5">
							<textarea  name="taskContent" class="form-control" id="taskContent" rows="3" 
								style="margin-top: 0px; margin-bottom: 0px; height: 161px;" required="required">4차 혁명 조사후 파일 기간내에 제출하시길 바랍니다.
<%-- 								${taskVO.taskContent} --%>
							</textarea>
							<form:errors path="taskContent" element="span" cssClass="text-danger"/>
							<!-- CKEDITER -->
							<script type="text/javascript">
								 CKEDITOR.replace('taskContent'
								                , {height: 500                                                  
				                 });
							</script>
							</td>
						</tr>
						<tr>
							<th class="text-nowrap" scope="row" colspan="2">
								<div class="text-right">
									<input type="submit" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-1" id="insertBtn" value="등록">
									<button type="button"
										class="btn waves-effect waves-light btn-light px-4 py-2 mx-1"
										id="resetBtn">취소</button>
								</div>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</form:form>	
</div>
<script type="text/javascript">
$(function (){
	$("#resetBtn").on("click", function(){
		window.history.back();
	});
	// 수정시 action 변겨
	let command =  '${command}';
	if(command=="MODIFY"){
       $("#taskForm").attr("action","${cPath}/myclass/${lecCode}/taskUpdate.do");
    }
	
	// 날짜 막기
	$("#startDate").datepicker({
		dateFormat: "yy-mm-dd", // 날짜의 형식
		minDate: 0,
		nextText: ">",
		prevText: "<",
		onSelect: function (date) {
			var endDate = $('#endDate');
			var startDate = $(this).datepicker('getDate');
			var minDate = $(this).datepicker('getDate');
			endDate.datepicker('setDate', minDate);
			startDate.setDate(startDate.getDate() + 30);
			endDate.datepicker('option', 'maxDate', startDate);
			endDate.datepicker('option', 'minDate', minDate);
		}
	});
	$('#endDate').datepicker({
		dateFormat: "yy-mm-dd", // 날짜의 형식
		nextText: ">",
		prevText: "<"
	});
	
	// ======== validator ========
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

	$("#taskForm").validate(validateOptions);
	
});	

function putData(){
	$("input[name='taskTitle']").val("4차 혁명 조사후 파일제출");
}

</script>