<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="authMember" scope="session" class="kr.or.ddit.vo.MemberVO"></jsp:useBean>
<h4><%=authMember.getMem_name() %>님의 마이페이지</h4>
<table class="table">
		<tr>
			<th>아이디</th>
			<td><%=authMember.getMem_id()%></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=authMember.getMem_name() %></td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><%=authMember.getMem_regno1() %></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><%=authMember.getMem_regno2() %></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><%=authMember.getMem_bir() %></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><%=authMember.getMem_zip() %></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><%=authMember.getMem_add1() %></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><%=authMember.getMem_add2() %></td>
		</tr>
		<tr>
			<th>집전번</th>
			<td><%=authMember.getMem_hometel() %></td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td><%=authMember.getMem_comtel() %></td>
		</tr>
		<tr>
			<th>메일</th>
			<td><%=authMember.getMem_mail() %></td>
		</tr>
		<tr>
			<td colspan="2">
			<button id="updateModalBtn" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#updateModal">수정</button>
			<button id="removeModalBtn" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#removeModal">탈퇴</button>
			</td>
		</tr>
		
	</table>

	<!-- 회원수정 모달-->
	<div class="modal fade" id="updateModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					수정하시겠습니까?
				</div>
				<div class="modal-body">
					<form method="get" id="passwordForm" action="<%=request.getContextPath()%>/member/modifyMember.do">
						<table class="table">
							<tr>
								<th>비밀번호</th>
								<td>
									<input type="text" class="form-control" name="mem_pass"/>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" id="updateBtn">수정</button>
				</div>
			</div>
		</div>	
	</div>
	
	
	<!-- 회원탈퇴 모달-->
	<div class="modal fade" id="removeModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					탈퇴하시겠습니까?
				</div>
				<div class="modal-body">
					<form method="post" id="passwordForm" action="<%=request.getContextPath()%>/member/removeMember.do">
						<table class="table">
							<tr>
								<th>비밀번호</th>
								<td><input type="text" class="form-control" name="mem_pass"/></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" id="removeBtn">취소</button>
				</div>
			</div>
		</div>	
	</div>
	
<script>
	$(function(){
		$("#updateBtn").on("click", function(){
			$("#updateModal").modal("hide");
		});
		$("#removeBtn").on("click", function(){
			$("#removeModal").modal("hide");
		});
	});
</script>