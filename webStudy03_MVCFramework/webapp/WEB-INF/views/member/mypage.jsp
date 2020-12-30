<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<h4>${member["mem_name"] }님의 상세 정보</h4>
<table class="col-md-7 table-bordered table-responsive">
	<tr>
		<th class="text-center">아이디</th>
		<td class="text-center pb-1">${member["mem_id"] }</td>
	</tr>
	<tr>
		<th class="text-center">이름</th>
		<td class="text-center pb-1">${member["mem_name"] }</td>
	</tr>
	<tr>
		<th class="text-center">프로필 사진</th>
		<td class="text-center pb-1">
			<c:if test="${not empty member.mem_img }">
				<img class="thumbnail" src="data:image/*;base64,${member.base64Image }" />
			</c:if>
		</td>
	</tr>	
	<tr>
		<th class="text-center">주민번호1</th>
		<td class="text-center pb-1">${member["mem_regno1"] }</td>
	</tr>
	<tr>
		<th class="text-center">주민번호2</th>
		<td class="text-center pb-1">${member["mem_regno2"] }</td>
	</tr>
	<tr>
		<th class="text-center">생일</th>
		<td class="text-center pb-1">${member["mem_bir"] }</td>
	</tr>
	<tr>
		<th class="text-center">우편번호</th>
		<td class="text-center pb-1">${member["mem_zip"] }</td>
	</tr>
	<tr>
		<th class="text-center">주소1</th>
		<td class="text-center pb-1">${member.mem_add1 }</td>
	</tr>
	<tr>
		<th class="text-center">주소2</th>
		<td class="text-center pb-1">${member.mem_add2 }</td>
	</tr>
	<tr>
		<th class="text-center">집전번</th>
		<td class="text-center pb-1">${member.mem_hometel }</td>
	</tr>
	<tr>
		<th class="text-center">회사전번</th>
		<td class="text-center pb-1">${member.mem_comtel }</td>
	</tr>
	<tr>
		<th class="text-center">휴대폰</th>
		<td class="text-center pb-1">${member.mem_hp }</td>
	</tr>
	<tr>
		<th class="text-center">메일</th>
		<td class="text-center pb-1">${member.mem_mail }</td>
	</tr>
	<tr>
		<th class="text-center">직업</th>
		<td class="text-center pb-1">${member.mem_job }</td>
	</tr>
	<tr>
		<th class="text-center">취미</th>
		<td class="text-center pb-1">${member.mem_like }</td>
	</tr>
	<tr>
		<th class="text-center">기념일</th>
		<td class="text-center pb-1">${member.mem_memorial }</td>
	</tr>
	<tr>
		<th class="text-center">기념일자</th>
		<td class="text-center pb-1">${member.mem_memorialday }</td>
	</tr>
	<tr>
		<th class="text-center">마일리지</th>
		<td class="text-center pb-1">${member.mem_mileage }</td>
	</tr>
	<tr>
		<th class="text-center">탈퇴여부</th>
		<td class="text-center pb-1">${"Y" eq member.mem_delete ? "탈퇴" : "이용중" }</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" value="수정" class="btn btn-primary" id="modifyBtn"/>
			<input type="button" value="탈퇴" class="btn btn-warning" id="removeBtn" />
		</td>
	</tr>
	<tr>
		<th>구매목록</th>
		<td>
			<table class="table">
				<thead class="table-dark">
					<tr>
						<th>상품코드</th>
						<th>상품분류</th>
						<th>상품명</th>
						<th>구매가</th>
						<th>판매가</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="prodList" value="${member.prodList }"/>
					<c:if test="${not empty prodList }">
						<c:forEach items="${prodList }" var="prod">
							<tr>
								<td>${prod.prod_id }</td>
								<td>${prod.prod_lgu }</td>
								<td>${prod.prod_name }</td>
								<td>${prod.prod_cost }</td>
								<td>${prod.prod_price }</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty prodList }">
						<tr>
							<td colspan="5">구매 목록이 없음.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</td>
	</tr>
</table>
<!-- Modal -->
<div class="modal fade" id="passwordModal" tabindex="-1" aria-labelledby="passwordModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form action="<c:url value='/member/removeMember.do'/>" method="post">
	      <div class="modal-header">
	        <h5 class="modal-title" id="passwordModalLabel">Modal title</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
			<div class="row g-3 align-items-center">
				<div class="col-auto">
					<label for="mem_pass" class="col-form-label">Password</label>
				</div>
				<div class="col-auto">
					<input type="text" name="mem_pass" id="mem_pass"
						class="form-control" required
						pattern="^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+).{5,12}$" />
				</div>
			</div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">탈퇴</button>
	      </div>
	  </form>
    </div>
  </div>
</div>
<script type="text/javascript">
	$(function(){
		let passwordModal = $("#passwordModal").modal({
								show:false
							}).on("hidden.bs.modal", function(){
								$(this).find("form").get(0).reset();
							});
		$("#removeBtn").on("click", function(){
			passwordModal.modal("show");
		});
		$("#modifyBtn").on("click", function(){
			location.href="<c:url value='/member/modifyMember.do'/>";
		});
	});
</script>












