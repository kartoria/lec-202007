<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/admin/insertschedule.do" var="scheduleInsert" />
<form:form commandName="scheduleVO" id="scheduleForm" method="post" action="${scheduleInsert }">
   <table class="table">
      <tr>
         <td onClick="putData()" class="table-active">일정명</td>
         <td>
            <form:input path="scheTitle" cssClass="form-control" placeholder="일정명을 입력하세요" required="required" />
            <form:errors path="scheTitle" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <td class="table-active">시작일</td>
         <td>
            <div>
               <form:input path="scheStart" type="date" cssClass="form-control" id="scheStart" required="required" />
               <form:errors path="scheStart" element="span" cssClass="error" />
            </div>
         </td>
      </tr>
      <tr>
         <td class="table-active">종료일</td>
         <td>
            <div>
               <form:input path="scheEnd" type="date" cssClass="form-control" id="scheEnd" required="required" />
               <form:errors path="scheEnd" element="span" cssClass="error" />
            </div>
         </td>
      </tr>
      <tr>
         <td class="table-active">내용</td>
         <td>
            <form:textarea path="scheContent" cssClass="form-control" cols="50" rows="10" required="required" />
            <form:errors path="scheContent" element="span" cssClass="error" />
         </td>
      </tr>
      <tr>
         <td class="table-active">일정 분류</td>
         <td>
            <form:select path="scheSort" cssClass="form-control" required="required" >
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
   <div class="modal-footer">
      <input type="submit" value="등록" class="btn btn-outline-primary"  />
      <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
   </div>
</form:form>

<script type="text/javascript">
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

$("#scheduleForm").validate(validateOptions);

function putData(){
	$("input[name='scheTitle']").val("신입생OT");
	$("textarea[name='scheContent']").val("신입생OT기간입니다.");
}

</script>