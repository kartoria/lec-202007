<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />
<style type="text/css">
.input-disabled{
   background-color: expression((this.readOnly) ? '#ffffff' : '#000000');
}
</style>
<div class="container-fluid">
   <div class="d-flex align-items-center mb-4">
      <h3 class="card-title font-weight-bold">Q&A 게시판</h3>
   </div>
   <div class="row">
      <div class="col-12">
         <div class="table-responsive border border-8">
            <table class="table">
               <tbody id="listBody">
                  <tr>
                     <td class="table-active font-weight-bold">제목</td>
                     <td colspan="5">${board.boTitle }</td>
                  </tr>
                  <tr>
                     <td class="table-active font-weight-bold">작성자</td>
                     <td>${board.memName }</td>
                     <td class="table-active font-weight-bold">작성 날짜</td>
                     <td>${fn:substring(board.boDate,0,16)}</td>
                     <td class="table-active font-weight-bold">조회수</td>
                     <td>${board.boHit }</td>
                  </tr>
                  <tr>
                     <td colspan="6" style="height: 400px;">${board.boContent }</td>
                  </tr>
               </tbody>
            </table>
         </div>
      </div>
   </div>
   <!-- 버튼 -->
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-end align-items-center">
			<div class="form-inline">
				<div class="form-group">
					<button type="button"
						class="btn waves-effect waves-light btn-light px-4 py-2 mx-2"
						id="resetBtn">목록</button>
						<c:url value="/myclass/${lecCode }/qnaList.do" var="listURL" />
						<c:url value="/myclass/${lecCode}/qnaBoUpdateForm.do" var="updateURL">
							<c:param name="boNo" value="${board.boNo }" />
						</c:url>
					<c:if test="${authMember.memId eq board.memId}">
							<button type="button"
								class="btn waves-effect waves-light btn-warning px-4 py-2 mx-2"
								id="updateBtn">수정</button>
							<button type="button"
								class="btn waves-effect waves-light btn-danger px-4 py-2 mx-2"
								id="delBtn">삭제</button>
						</c:if>
				</div>
			</div>
		</div>
	</div>

   <!-- 답변 수정 부분 -->
   <security:authorize access="hasRole('ROLE_PROFESSOR')">
      <!-- 답변 조회(수정버튼/삭제버튼 있음) -->
      <div class="row" id="rep">
         <div class="col-12" id='repCardForm'>
            <div class="card" style="background-color: lightgray;">
               <div class="card-header">
					<div class="col-7">
		                  <i class='fas fa-comment'></i> 답변
					</div>
<!-- 					<div class="col-4 d-flex justify-content-right"> -->
<!-- 		                  <span id="insertDate"></span> -->
<!-- 					</div> -->
               </div>
               <div class="card-body">
                  <div class="col-12">
                   <form:form id="repInsertForm" action="${cPath }/myclass/${lecCode}/qnaInsert.do" commandName="board" method="post">
					   <input type="hidden" name="boParent" value="${board.boNo }" /> 
					   <input type="hidden" name="boTitle" value="RE:${board.boTitle }" /> 
					   <input type="hidden" name="memId" value="${authMember.memId }" /> 
					   <input type="hidden" name="boGroupCode" value="QNA" />
					   <div class="bg-white mb-2 font-weight-bold h-10" id="bgWhite" style="height: 100px;">
					   </div>
                      	<textarea id="repContent" name="boContent" class="form-control mb-2"
                        	rows='5'></textarea> 
					</form:form>
                  </div>
                  <div class="col-12 text-right" id="btnGroup">
                  	<button class="btn waves-effect waves-light btn-info px-4 py-2 mx-1" id="repInsert">등록</button>
                  	<button class="btn waves-effect waves-light btn-danger px-4 py-2 mx-1" id="repDelete">삭제</button>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </security:authorize>
   </div>
<!-- ============= 삭제 확인 모달 ================ -->
<!-- ========================================= -->
<div id="delModal" class="modal fade" tabindex="-1" role="dialog"
   aria-labelledby="danger-header-modalLabel" aria-hidden="true">
   <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
         <div class="modal-header modal-colored-header bg-danger">
            <h4 class="modal-title font-weight-bold"
               id="danger-header-modalLabel">삭제 여부 확인</h4>
            <button type="button" class="close" data-dismiss="modal"
               aria-hidden="true">×</button>
         </div>
         <div class="modal-body">
            <h4 class="font-weight-bold">정말 해당 게시글을 삭제하시겠습니까?</h4>
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
<form:form id="goRepView"
   action="${cPath }/myclass/${lecCode }/qnaRepView.do" method="post">
   <input type="hidden" name="boParent" value="${board.boNo }" />
   <input type="hidden" name="lecCode" value="${lecCode }" />
   <input type="hidden" name="memId" value="${authMember.memId }" />
</form:form>
<form:form id="goRepDel" action="${cPath }/myclass/${lecCode }/qnaRepDel.do" method="post" commandName="board">
	<input type="hidden" name="boNo"/>
</form:form>
<form:form id="goDel" action="${cPath }/myclass/${lecCode }/qnaBoDelete.do">
	<input type="hidden" name="boNo" value="${board.boNo }" />
</form:form>
<script type="text/javascript">
//=============== 댓글 =======================   
let goRepView = $("#goRepView").ajaxForm({
   dataType : "json"
   ,success : function(resp) {
 	 console.log(resp);
      console.log(resp.repBoard);
      let repForm = $("#repForm");
      // 있으면 수정+삭제+div
      if (resp.repBoard != null) {
     	$("#bgWhite").text(resp.repBoard.boContent);
     	$("#insertDate").text(resp.repBoard.boDate)
     	$("#bgWhite").show();
//      	$("#repUpdate").show();
     	$("#repDelete").attr("data-bono",resp.repBoard.boNo).show();
     	
      // 없으면 등록+textarea   
      } else if (resp.repBoard == null) {
    	$("#repContent").show();
     	$("#repInsert").show();
      }
   }
   ,error : function(xhr) {
      console.log(xhr);
   }
}).submit();

$(function(){
	$("#btnGroup button").hide();
	$("#repContent").hide();
	$("#bgWhite").hide();
	$("#repInsert").on("click", function() {
		let repInsertForm = $("#repInsertForm").ajaxForm({
			 dataType : "json"
			,success : function(resp) {
				$("#repContent").hide();
				$("#repInsert").hide();
				goRepView.submit();		 
			}
			,error : function(xhr) {
				console.log(xhr);
			}
		}).submit();
	});	
	$("#repDelete").on("click", function() {
		let boNo = $(this).data("bono");
		$("input[name='boNo']").val(boNo);
		let goRepDel = $("#goRepDel").ajaxForm({
			 dataType : "json"
			,success : function(resp) {
				console.log(resp);
				$("#bgWhite").hide();
				$("#insertDate").hide();
				$("#repDelete").hide();
				goRepView.submit();		 
			}
			,error : function(xhr) {
				console.log(xhr);
			}
		}).submit();
	});	
});

//===== 글 ======
$("#updateBtn").on("click", function() {
	location.href="${updateURL }";
});	

$("#resetBtn").on("click",function(){
	location.href="${listURL }";
});

$("#delBtn").on("click", function() {
	$("#delModal").modal();
});

$("#goDelBtn").on("click", function() {
	$("#goDel").submit();
});
</script>