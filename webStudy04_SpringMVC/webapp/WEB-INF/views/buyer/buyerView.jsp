<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
	<table class="table table-bordered">
		<tr>
			<th>거래처코드</th>
			<td>${buyer.buyer_id }</td>
		</tr>
		<tr>
			<th>거래처명</th>
			<td>${buyer.buyer_name }</td>
		</tr>
		<tr>
			<th>거래처분류</th>
			<td>${buyer.buyer_lgu }</td>
		</tr>
		<tr>
			<th>은행</th>
			<td>${buyer.buyer_bank }</td>
		</tr>
		<tr>
			<th>계좌</th>
			<td>${buyer.buyer_bankno }</td>
		</tr>
		<tr>
			<th>계좌주</th>
			<td>${buyer.buyer_bankname }</td>
		</tr>
		<tr>
			<th>거래처 전경</th>
			<td>
				<c:if test="${not empty buyer.buyer_img }">
					<img src="${cPath }/buyerImages/${buyer.buyer_img }" class="thumbnail"/>
				</c:if>
			</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${buyer.buyer_zip }</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${buyer.buyer_add1 }</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${buyer.buyer_add2 }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${buyer.buyer_comtel }</td>
		</tr>
		<tr>
			<th>팩스번호</th>
			<td>${buyer.buyer_fax }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${buyer.buyer_mail }</td>
		</tr>
		<tr>
			<th>담당자</th>
			<td>
			<c:if test="${not empty buyer.charger_img }">
				<img src="data:image/*;base64,${buyer.base64ChargerImage }" class="thumbnail" />
			</c:if>
			${buyer.buyer_charger }</td>
		</tr>
		<tr>
			<th>내선번호</th>
			<td>${buyer.buyer_telext }</td>
		</tr>
		<tr>
			<th>거래물품</th>
			<td>
			<table class="table">
				<thead class="text-center">
					<tr>
						<th>상품명</th>
						<th>구매가</th>
						<th>판매가</th>
						<th>세일가</th>
						<th>상품개요</th>
						<th>마일리지</th>
					</tr>
				</thead>
				<tbody id="listBody">
					<c:set var="prodList" value="${buyer.prodList }"/>
					<c:choose>
						<c:when test="${not empty prodList }">
							<c:forEach items="${prodList }" var="prod">
								<tr>
									<td>
										<c:url value="/prod/prodView.do" var="viewURL">
											<c:param name="what" value="${prod.prod_id }" />
										</c:url>
										<a href="${viewURL }">${prod.prod_name }</a>
									</td>
									<td>${prod.prod_cost }</td>
									<td>${prod.prod_price }</td>
									<td>${prod.prod_sale }</td>
									<td>${prod.prod_outline }</td>
									<td>${prod.prod_mileage }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6">거래 물품이 없음.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="text-center">
				<input type="button" value="목록으로" class="btn btn-primary"
					onclick="location.href='<c:url value="/buyer/buyerList.do" />';" />
				<c:url value="/buyer/buyerUpdate.do" var="updateURL">
					<c:param name="what" value="${buyer.buyer_id }" />
				</c:url>		
				<input type="button" value="수정하기" class="btn btn-primary"
					onclick="location.href='${updateURL }';" />
			</td>
		</tr>
	</table>
	<div class="modal fade" id="prodViewModal" tabindex="-1" aria-labelledby="prodViewModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-fullscreen" data-bs-backdrop="static" >
		  <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title h4" id="prodViewModalLabel">상품 상세 조회</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		      
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		      </div>
		    </div>
		</div>
	</div>
	<script>
		let prodViewModal = $("#prodViewModal").on("hidden.bs.modal", function(){
			$(this).find(".modal-body").empty();
		});
		
		$("#listBody").on("click", "a", function(event){
			event.preventDefault();
			let prodURL = $(this).attr("href");
			prodViewModal.find(".modal-body").load(prodURL, function(){
				prodViewModal.modal("show");
			});
			return false;
		});
	</script>

