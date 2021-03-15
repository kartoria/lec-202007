<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<div class="container-fluid">
<input type="hidden" name="memPass"/>
<input type="hidden" name="memId" id="memId" value="${board.memId }"/>
<input type="hidden" name="boNo" id="boNo" value="${board.memberVO.boNo }">
<input type="hidden" name="boParent" id="boParent" value="${board.boParent }"/>
	<div class="row">
		<div class="col-12">
			<h3 class="ml-2">
				<i class="fas fa-home"></i> 사이버 캠퍼스 > Q&A
			</h3>
		</div>
	</div>  
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">질문글</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">Q&A 질문글을 상세하게 볼 수 있습니다.</h5>
		</div>
	</div>
	<div class="row my-3">
	    <div class="col-12">
             <div class="card">
                 <div class="table-responsive">
                     <table class="table">
                         <tbody id="listBody">
                             <tr>
                                 <td class="table-active"><b>제목</b></td>
                                 <td colspan="2">${board.boTitle }</td>
                             </tr>
                             <tr>
                                 <td class="table-active">작성자</td>
                                 <td>${board.memName }</td>
                                 <td class="table-active">작성 날짜</td>
                                 <td>${board.boDate }</td>
                                 <td class="table-active">조회수</td>
                                 <td>${board.boHit }</td>
                             </tr>
                             <tr>
								 <c:choose>
								 	<c:when test="${board.boDelete eq 'Y'}">
								 		<td colspan="5">
								 		 <h3 style="color:red;">삭제된 게시글입니다.</h3>
								 		</td>
								 	</c:when>
								 	<c:otherwise>
		                                 <td colspan="6">
		                                 	<div style="min-height: 300px;">
			                                 	${board.boContent }
		                                 	</div>
		                                 </td>
								 	</c:otherwise>
								 </c:choose>
                             </tr>
                         </tbody>
                     </table>
				</div>
			</div>
		</div>
	</div>
	<!-- 버튼 -->
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-end align-items-center">
			<div class="form-inline">
				<div class="form-group">
					<button type="button" class="btn waves-effect waves-light btn-light px-4 py-2 mx-2" id="listBtn">목록</button>
				</div>
				<div class="form-group">	
					<security:authorize access="isAuthenticated()">
					<security:authentication property="principal" var="principal"/>
					<c:set var="authMember" value="${principal.realMember }" />
					<c:url value="/cyber/qna/updateForm.do" var="updateURL">
						<c:param name="what" value="${board.memberVO.boNo}"/>
					</c:url>
					<c:if test="${authMember.memId eq board.memId || authMember.memId eq 'admin' }">
						<button type="button"
							class="btn waves-effect waves-light btn-warning px-4 py-2 mx-2"
							id="updateBtn">수정</button>
						<button type="button" id="deleteBtn"
							class="btn waves-effect waves-light btn-danger px-4 py-2 mx-2">삭제</button>
					</c:if>
<%-- 					<c:if test="${authMember.memId eq 'admin'}"> --%>
<%-- 						<button type="button" id="adminDeleteBtn" value="${board.memberVO.boNo }" --%>
<!-- 							class="btn waves-effect waves-light btn-danger px-4 py-2 mx-2">삭제</button> -->
<%-- 					</c:if> --%>
					</security:authorize>
				</div>
			</div>
		</div>
	</div>
		
	<!-- ============ 답변 등록 폼 ============ -->
	<c:if test="${authMember.memId eq board.memId || authMember.memId eq 'admin'}">
	<div class="row">
		<div class="col-12 col-lg-12">
			<b>COMMENTS</b>
		</div>
	</div>
	<form id="replyInsertForm" action="${cPath}/reply/insert.do" method="post">
		<div class="row">
			<div class="col-12 col-lg-12">
                <div class="input-group">
                    <textarea name="repContent" class="form-control" style="resize:none" rows="3" placeholder="답변을 등록해주세요."></textarea>
                    <div class="input-group-append">
                    	<input type="hidden" name="repNo">
                    	<input type="hidden" name="boNo" value="${board.memberVO.boNo }" />
                        <button class="btn btn-primary" type="submit">답변 등록</button>
                    </div>
                </div>
	        </div>
	    </div>
    </form>
    </c:if>
    <br>
	<!-- ============ 댓글 LIST =================== -->		
	<div class="row">
		<div class="col-12 col-lg-12">
			<div class="table-responsive">
		    	<table id="replyTable" class="table no-wrap text-dark">
		    	<thead class="table-info">
		    		<tr>
		    			<th colspan="4">전체 댓글 <span class="replyCountSpan text-danger"></span>개</th>
		    		</tr>
		    	</thead>
		    	<tbody id="listBody">
		    	</tbody>
		    	</table>
			</div>
		</div>
	</div>
	<div id="pagingArea" class="text-center" style="margin-left: 700px;"></div>
</div>

<hr>
<div class="row mx-5">
	<div class="col-12">
		<c:if test="${not empty previousBoard }">
			<h4>
				<a href="${cPath }/cyber/qna/${previousBoard.boNo }/view.do"  class="text-dark">
					<i class="fas fa-angle-up"></i> 이전글 - ${previousBoard.boTitle } | ${previousBoard.boDate }
				</a>
			</h4>
		</c:if>
		<c:if test="${empty previousBoard }">
			<h4>이전글이 없습니다.</h4>
		</c:if>
	</div>
</div>
<div class="row mx-5">	
	<div class="col-12">
		<c:if test="${not empty nextBoard }">
			<h4>
				<a href="${cPath }/cyber/qna/${nextBoard.boNo }/view.do" class="text-dark">
					<i class="fas fa-angle-down"></i> 다음글 - ${nextBoard.boTitle } | ${previousBoard.boDate }
				</a>
			</h4>
		</c:if>
		<c:if test="${empty nextBoard }">
			<h4>다음글이 없습니다.</h4>
		</c:if>
	</div>
</div>
<hr>



<!-- 댓글 불러오는 폼 -->
<form id="searchForm" action="${cPath}/reply/list.do" method="post" >
	<input type="hidden" name="boNo" value="${board.memberVO.boNo }" />
		<input type="hidden" name="page" />
	
</form>

<!-- 댓글 삭제하는 폼 -->
<form id="replyDeleteForm" action="${cPath}/reply/delete.do" method="post">
	<input type="hidden" name="repNo" required/>
	<input type="hidden" name="memId" required/>
</form>	

<form:form id="delForm" method="post" action="${cPath}/cyber/qna/delete.do">
	<input type="hidden" name="boNo" value="${board.memberVO.boNo }"/>
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
			<div class="modal-body text-center">
				<h4 class="font-weight-bold">정말 해당 게시글을 삭제하시겠습니까?</h4>
			</div>
			<div class="modal-footer" style="margin: auto;">
				<button type="button" class="btn btn-light" data-dismiss="modal">취소</button>
				<c:if test="${authMember.memId eq board.memId && authMember.memId eq 'admin'}">
					<button type="button" class="btn btn-danger" id="goAdminDelBtn">삭제</button>
				</c:if>
				<c:if test="${authMember.memId ne 'admin' && authMember.memId eq board.memId }">
					<button type="button" class="btn btn-danger" id="goDelBtn">삭제</button>
				</c:if>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<!-- ============= 관리자 삭제 확인 모달 ================ -->
<!-- ========================================= -->
<div id="adminDeleteModal" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="danger-header-modalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header modal-colored-header bg-danger">
				<h4 class="modal-title font-weight-bold" id="danger-header-modalLabel">삭제 여부 확인</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
			</div>
			<div class="modal-body text-center">
				<h4 class="font-weight-bold">정말 해당 게시글을 삭제하시겠습니까?</h4>
			</div>
			<div class="modal-footer" style="margin: auto;">
<!-- 				<button type="button" class="btn btn-light" data-dismiss="modal">취소</button> -->
<!-- 				<button type="button" class="btn btn-danger" id="goAdminDelBtn">삭제</button> -->
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<!-- 비번 체크 모달 -->
<div class="modal fade" id="passModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                	<h3 class="modal-title" id="myCenterModalLabel">비밀번호 재입력</h3>
				<button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
				<div id="searchArea" class="card-body">
					<div class="input-group col-12 d-flex justify-content-center align-items-center">
						<div class="form-group">
							<p><span class="badge badge-warning">주의</span> 비밀번호를 입력해야 삭제가 가능합니다.</p>
						</div>
						<div class="form-group flex-grow-1">
							<input type="password" id="example-input-large" name="memPass" class="form-control form-control-lg" placeholder="비밀번호를 입력해주세요.">
						</div>
						<div class="form-group">
							<button id="modalBtn" type="button" class="btn btn-primary btn-lg">
								<i class="fas fa-location-arrow"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
        </div><!-- /.modal-content -->
   </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 비번 입력했을 때 불일치 시에 뜨는 알림창 모달 -->
<input type="hidden" id="modalNegativetBtn"class="btn btn-secondary" data-toggle="modal" data-target="#modalPassNotMatch" value="숨겨진모달">
<div class="modal fade" id="modalPassNotMatch" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
		    <div class="modal-header bg-danger text-white">
		      	<h5 class="modal-title ">경고창</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    </div>
		    <div class="modal-body ">
				<p>비밀번호가 일치하지 않습니다.</p>
		    </div>
		</div>
	</div>
</div>

<!-- 삭제 성공 시 완료 창! -->
<input type="hidden" id="seccessDeleteQna"class="btn btn-secondary" data-toggle="modal" data-target="#DeleteCompleteModal" value="숨겨진모달">
<div class="modal fade" id="DeleteCompleteModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
		    <div class="modal-header">
		      	<h5 class="modal-title ">알림창</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    </div>
		    <div class="modal-body">
					<p><h3><strong></strong>글이 삭제되었습니다!</h3></p>
		    </div>
		</div>
	</div>
</div>
<!-- /.modal -->

<script type="text/javascript">
$("#listBtn").on("click", function(){
	location.href="${cPath}/cyber/qna/list.do";
});

$("#updateBtn").on("click", function(){
	location.href="${updateURL} ";
});

$("#deleteBtn").on("click", function(){
	$("#delModal").modal();
});

$("#adminDeleteBtn").on("click", function(){
	$("#adminDeleteModal").modal();
});

$("#goDelBtn").on("click", function() {
	$(".close").click();
	$("#passModal").modal();
});	

$("#goAdminDelBtn").on("click", function(){
	let boNo = $("#boNo").val();
	let adminId = '${authMember.memId}';
	$.ajax({
		url : '${cPath}/cyber/qna/deletePassCheck.do'
		, type : 'post'
		, data : 
			{
			 "memId" : adminId
			, "boNo" : boNo
			}
		, dataType : 'json'
		, success : function(resp){
			if(resp.result == "OK"){
				$("#delForm").submit();
			} 
			if(resp.result == "FAILED"){
				$("#modalNegativetBtn").click();
			}
		}
		, error : function(request, error){
	    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
	    }
	});
});

$("#modalBtn").on("click", function(){
	let memPass = $("#example-input-large").val();
	let memId = $("#memId").val();
	let boNo = $("#boNo").val();
	$.ajax({
		url : '${cPath}/cyber/qna/deletePassCheck.do'
		, method : 'post'
		, data : 
			{
				"memPass" : memPass
				, "memId" : memId
				, "boNo" : boNo
			}
		, dataType : 'json'
		, success : function(resp){
			if(resp.result == "OK"){
				$("#delForm").submit();
			} 
			if(resp.result == "FAILED"){
				$("#modalNegativetBtn").click();
			}
		}
		, error : function(request, error){
	    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
	    }
	});
});

let replyInsertForm = $("#replyInsertForm").ajaxForm({
	dataType : "json",
	success:function(resp){
		if(resp.result == "OK"){
			replyInsertForm.get(0).reset();
			searchForm.find("[name='page']").val(1);
			searchForm.submit();
		}else if(resp.msg){
			alert(resp.msg);
		}
	}
	, error : function(request, error){
    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
    }
});





let replyDeleteForm = $("#replyDeleteForm").ajaxForm({
	dataType : "json",
	success:function(resp){
		if(resp.result == "OK"){
			replyInsertForm.get(0).reset();
			searchForm.submit();
		}else if(resp.msg){
			alert(resp.msg);
		}
	}
});

function deleteReply(event){
	if(!confirm("정말 삭제하시겠습니까?")) return false;
	let reply = $(this).parents("tr:first").data("reply");
	$(replyDeleteForm).find("[name='repNo']").val(reply.repNo);
	$(replyDeleteForm).find("[name='memId']").val(reply.memId);
	$(replyDeleteForm).submit();
}

let listTable = $("#replyTable").on("click", ".delBtn", deleteReply).find("#listBody");
let pagingArea = $("#pagingArea");

let pagingA = pagingArea.on('click', "a" ,function(){
	let page = $(this).data("page");
	searchForm.find("[name='page']").val(page);
	searchForm.submit();
	return false;
});
let searchForm = $("#searchForm").ajaxForm({ // 댓글 불러오기
	dataType : "json",
	success : function(resp) {
		listTable.find("tbody").empty(); // 테이블 한번 비워주기
		pagingArea.empty(); // 페이징버튼도 비우기
		let replyList = resp.dataList;
		console.log(replyList);
		let authMemId = resp.memId;
		let trTags = [];
		if(replyList){
			$(replyList).each(function(idx, reply){ // 댓글하나씩 꺼내오기
				let tr = $("<tr>");
				tr.append(
						$("<td>").text(reply.member.memName).attr("data-memId", reply.memId),
						$("<td>").html(reply.repContent),
						$("<td>").text(reply.repDate)
				);
				if(reply.memId == authMemId || authMemId == 'admin'){
					tr.append($("<td>").append( // 자기가 쓴 댓글이면 삭제버튼 넣어주기
							  	$("<input>").attr({type:"button", value:"삭제"}).addClass("btn btn-light delBtn"))
					);
				}else{
					tr.append($("<td>"));
				}
				tr.data("reply", reply);
				trTags.push(tr);
			});
		}else{
			trTags.push($("<tr>").html($("<td>").text("댓글이 없음."))); // 댓글없는거 표시해주기
		}
		$(".replyCountSpan").text(resp.totalRecord); // 전체 댓글 갯수 표시해주기
		let remainRowCnt = resp.screenSize - trTags.length;
  		for(let i=0; i<remainRowCnt; i++){
  			trTags.push($("<tr>").html($("<td colspan='4'>").html("&nbsp;")));
  		}
		listTable.html(trTags);
		if(replyList.length>0)
			pagingArea.html(resp.pagingHTML);
		
		$("#replyTable tr>td:nth-child(1)").css("width", "10%");
		$("#replyTable tr>td:nth-child(2)").css("width", "70%");
		$("#replyTable tr>td:nth-child(3)").css("width", "10%").addClass("text-right");
		$("#replyTable tr>td:nth-child(4)").css("width", "10%").addClass("text-left");
	},
	error : function(errResp) {
		console.log(errResp);
	}
}).submit(); // 페이지 로드 후 1페이지의 댓글 요청.
</script>