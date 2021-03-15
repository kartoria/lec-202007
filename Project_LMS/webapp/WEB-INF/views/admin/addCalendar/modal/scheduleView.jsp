<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
 #viewForm input{
  	border-color: white; 
}
.focusbox {float:left; margin:0; height:auto; color:black;border: black;} 
</style>
<form:form commandName="scheVO" id="viewForm" method="post" action="${cPath }/admin/updateSchedule.do">
   <input type="hidden" name="scheNo" value="${scheNo }">
   <table class="table text-center">
      <tr>
         <td class="table-active">일정명</td>
         <td>
            <form:input path="scheTitle" cssClass="form-control" placeholder="일정명을 입력하세요"  required="required" />
            <form:errors path="scheTitle" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <td class="table-active">시작일</td>
         <td>
            <div>
               <form:input path="scheStart" type="date" cssClass="form-control w-50" id="dateStart" required="required" />
               <form:errors path="scheStart" element="span" cssClass="error" />
            </div>
         </td>
      </tr>
      <tr>
         <td class="table-active">종료일</td>
         <td>
            <div>
               <form:input path="scheEnd" type="date" cssClass="form-control w-50" id="dateEnd"   required="required" />
               <form:errors path="scheEnd" element="span" cssClass="error" />
            </div>
         </td>
      </tr>
      <tr>
         <td class="table-active">내용</td>
         <td>
            <form:textarea path="scheContent"  cols="50" rows="10" cssClass="form-control" required="required" />
            <form:errors path="scheContent" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <td class="table-active">일정 분류</td>
         <td>
            <form:select path="scheSort" cssClass="form-control"  required="required" >
            	<form:option value="">선택</form:option>
            	<form:option value="TLEC">수강신청</form:option>
            	<form:option value="TEST">시험</form:option>
            	<form:option value="GRADE">성적</form:option>
            	<form:option value="PAY">등록금</form:option>
            	<form:option value="ETC">기타</form:option>
            </form:select>
         </td>
      </tr>
   </table>
</form:form>
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
				<h4 class="font-weight-bold">해당 일정을 삭제하시겠습니까?</h4>
			</div>
			<div class="modal-footer" style="margin: auto;">
				<button type="button" class="btn btn-light" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-danger" id="goDelBtn">삭제</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

 
<script>
	
$("#goDelBtn").on("click", function() {
	$("#viewForm").attr("action","${cPath}/admin/deleteSchedule.do");
	$("#viewForm").submit();
}); 

//input 테두리
$('input').focus(function() {
    $(this).addClass('focusbox');
}).blur(function() {
    $(this).removeClass('focusbox');
});

</script> 