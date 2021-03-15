<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<input type="hidden" id="lecCodeHidden" value="${lecCode}"/>
<div class="container-fluid">
	<!-- =============== 게시글 =================== -->
	<div class="row">
		<div class="col-12 col-lg-10">
			<div class="row">
				<div class="col-7 font-weight-bold text-secondary overflow-auto">
					<h2>토론 게시글</h2>
				</div>
				<div class="col-5 text-right">
					<security:authorize access="isAuthenticated()">
					<security:authentication property="principal" var="principal" />
					<c:set var="authMember" value="${principal.realMember }" />
					<c:if test="${authMember.memId eq boardVO.memId || authMember.memId eq 'admin'}">
					<form id="updateAndDeleteForm" method="post">
						<input type="hidden" name="boNo" value="${boardVO.boNo }"/>
						<input type="hidden" name="memId" value="${boardVO.memId }"/>
						<Button type="button" id="updateBtn" class="btn btn-light px-5">수정</Button>
						<Button type="button" id="" class="btn btn-light px-5" data-toggle="modal" data-target="#myModal">삭제</Button>
					</form>
					</c:if>
				</security:authorize>					
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-12 col-lg-10">
			<div class="card">
				<div class="row">
					<div class="col-12 text-dark pt-3">
						<h3>${boardVO.boTitle}</h3>
					</div>
				</div>
				<div class="row border-bottom border-light">
					<div class="col-9 text-dark">
						${boardVO.memberVO.memName} | ${boardVO.boDate}
					</div>
					<div class="col-3 text-dark pb-1 text-right">
						조회 ${boardVO.boHit} | 댓글<span class="replyCountSpan text-danger"></span>개
					</div>
				</div>
				<div class="row">
					<div class="col-12 text-dark pt-3" style="min-height:200px">
						${boardVO.boContent}
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- =============== 게시글 END ================ -->
	
	<!-- ================ 댓글 등록 ================= -->
	<div class="row">
		<div class="col-12 col-lg-10">
			<b>COMMENTS</b>
		</div>
	</div>
	<form id="replyInsertForm" action="${cPath}/reply/insert.do" method="post">
		<div class="row">
			<div class="col-12 col-lg-10">
				<div class=" bg-light">
					<input type="hidden" name="boNo" value="${boNo }" />
					<textarea name="repContent" class="form-control" style="resize:none" rows="3" placeholder="토론 의견을 달아주세요."></textarea>
				</div>
			</div>
		</div>
		<div class="row pt-1 mb-3">
			<div class="col-12 col-lg-10 text-right">
					<input type="submit" class="btn btn-primary px-5" value="댓글쓰기"/>
			</div>
		</div>
	</form>
	<!-- ============ 댓글 등록 END ================= -->
	
	<!-- ============ 댓글 LIST =================== -->
	<div class="row">
		<div class="col-12 col-lg-10">
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
	<div class="row">
		<div class="col-12 col-lg-10">
			<div id="pagingArea" class="text-center"></div>
		</div>
	</div>
	<!-- ============ 댓글 LIST END ================= -->
</div>

<!-- 댓글 불러오는 폼 -->
<form id="searchForm" action="${cPath}/reply/list.do" method="post" >
	<input type="hidden" name="boNo" value="${boardVO.boNo }" />
	<input type="hidden" name="page" />
</form>

<!-- 댓글 삭제하는 폼 -->
<form id="replyDeleteForm" action="${cPath}/reply/delete.do" method="post">
	<input type="hidden" name="repNo" required/>
	<input type="hidden" name="memId" required/>
</form>	


<!-- ===================== 삭제 시 비밀번호 입력 모달  ========================================================================================== -->
<div id="myModal" class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" aria-modal="true">
	<div class="modal-dialog  modal-dialog-centered" role="document">
		<div class="modal-content">
			<form id="counselingInsert">
				<div class="modal-header">
					<h5 class="modal-title" id="scrollableModalTitle">토론글 삭제</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					정말 삭제하시겠습니까?
				</div>
				<div class="modal-footer">
					<button type="button" class="btn waves-effect waves-light btn-rounded btn-secondary" data-dismiss="modal">취소</button>
					<button type="button" id="deleteBtn" class="btn waves-effect waves-light btn-rounded btn-success">삭제하기</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- ===================== 삭제 시 비밀번호 입력 모달 END ====================================================================================== -->

<script type="text/javascript" src="${cPath}/js/viewjs/discussView.js"></script>