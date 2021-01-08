<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-bordered" data-prod-id="${prod.prod_id }">
	<tr>
		<th class="text-center">상품코드</th>
		<td class="pb-1">${prod.prod_id }</td>
	</tr>
	<tr>
		<th class="text-center">상품명</th>
		<td class="pb-1">${prod.prod_name }</td>
	</tr>
	<tr>
		<th class="text-center">분류</th>
		<td class="pb-1">${prod.prod_lgu }</td>
	</tr>
	<tr>
		<th class="text-center">거래처</th>
		<td class="pb-1">
			<table class="table">
				<thead>
					<tr>
						<th>거래처코드</th>
						<th>거래처명</th>
						<th>담당자</th>
						<th>연락처</th>
						<th>팩스번호</th>
						<th>이메일</th>
						<th>지역</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${prod.buyer.buyer_id }</td>
						<td>
						<c:url value="/buyer/buyerView.do" var="viewURL">
							<c:param name="what" value="${prod.buyer.buyer_id }"/>
						</c:url>
						<a href="${viewURL }">${prod.buyer.buyer_name }</a>
						</td>
						<td>${prod.buyer.buyer_charger }</td>
						<td>${prod.buyer.buyer_comtel }</td>
						<td>${prod.buyer.buyer_fax }</td>
						<td>${prod.buyer.buyer_mail }</td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
	<tr>
		<th class="text-center">구매가</th>
		<td class="pb-1">${prod.prod_cost }</td>
	</tr>
	<tr>
		<th class="text-center">판매가</th>
		<td class="pb-1">${prod.prod_price }</td>
	</tr>
	<tr>
		<th class="text-center">세일가</th>
		<td class="pb-1">${prod.prod_sale }</td>
	</tr>
	<tr>
		<th class="text-center">상품요약</th>
		<td class="pb-1">${prod.prod_outline }</td>
	</tr>
	<tr>
		<th class="text-center">상세정보</th>
		<td class="pb-1">${prod.prod_detail }</td>
	</tr>
	<tr>
		<th class="text-center">이미지경로</th>
		<td class="pb-1">
			<img class="thumbnail" src="${pageContext.request.contextPath }/prodImages/${prod.prod_img }" />
		</td>
	</tr>
	<tr>
		<th class="text-center">재고수량</th>
		<td class="pb-1">${prod.prod_totalstock }</td>
	</tr>
	<tr>
		<th class="text-center">입고일</th>
		<td class="pb-1">${prod.prod_insdate }</td>
	</tr>
	<tr>
		<th class="text-center">적정재고</th>
		<td class="pb-1">${prod.prod_properstock }</td>
	</tr>
	<tr>
		<th class="text-center">상품크기</th>
		<td class="pb-1">${prod.prod_size }</td>
	</tr>
	<tr>
		<th class="text-center">색상</th>
		<td class="pb-1">${prod.prod_color }</td>
	</tr>
	<tr>
		<th class="text-center">배송방법</th>
		<td class="pb-1">${prod.prod_delivery }</td>
	</tr>
	<tr>
		<th class="text-center">단위</th>
		<td class="pb-1">${prod.prod_unit }</td>
	</tr>
	<tr>
		<th class="text-center">입고량</th>
		<td class="pb-1">${prod.prod_qtyin }</td>
	</tr>
	<tr>
		<th class="text-center">출고량</th>
		<td class="pb-1">${prod.prod_qtysale }</td>
	</tr>
	<tr>
		<th class="text-center">마일리지</th>
		<td class="pb-1">${prod.prod_mileage }</td>
	</tr>
	<tr>
		<th>구매자목록</th>
		<td>
			<table class="table">
				<thead class="text-center">
					<tr>
						<th>아이디</th>
						<th>이름</th>
						<th>휴대폰</th>
						<th>이메일</th>
						<th>주소1</th>
						<th>마일리지</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty prod.memberList }">
						<c:forEach var="member" items="${prod.memberList }">
							<tr>
								<td>${member.mem_id }</td>
								<td>${member.mem_name }</td>
								<td>${member.mem_hp }</td>
								<td>${member.mem_mail }</td>
								<td>${member.mem_add1 }</td>
								<td>${member.mem_mileage }</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty prod.memberList }">
						<tr>
							<td colspan="6">구매자 정보가 없음.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</td>
	</tr>
</table>