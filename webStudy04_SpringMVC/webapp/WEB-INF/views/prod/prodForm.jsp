<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<form id="prodForm" method="post" enctype="multipart/form-data">
	<input type="hidden" required name="prod_id" value="${prod.prod_id }" />
	<table class="table">
		<tr>
			<th class="text-center">상품코드</th>
			<td class="pb-1">${prod.prod_id }</td>
		</tr>
		<tr>
			<th class="text-center">상품명</th>
			<td class="pb-1">
				<input type="text" class="form-control"
				required name="prod_name" value="${prod.prod_name }" />
				<span class="error">${errors.prod_name }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">분류</th>
			<td class="pb-1">
				<select class="form-control" required name="prod_lgu" data-init-value="${prod.prod_lgu }">
					<option value>분류선택</option>
				</select>
				<span class="error">${errors.prod_lgu }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">거래처</th>
			<td class="pb-1">
				<select class="form-control" required name="prod_buyer" data-init-value="${prod.prod_buyer }">
					<option value>거래처선택</option>
				</select>
				<span class="error">${errors.prod_buyer }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">구매가</th>
			<td class="pb-1">
				<input type="number" class="form-control"
				required name="prod_cost" value="${prod.prod_cost }" />
				<span class="error">${errors.prod_cost }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">판매가</th>
			<td class="pb-1">
				<input type="number" class="form-control"
				required name="prod_price" value="${prod.prod_price }" />
				<span class="error">${errors.prod_price }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">세일가</th>
			<td class="pb-1">
				<input type="number" class="form-control"
				required name="prod_sale" value="${prod.prod_sale }" />
				<span class="error">${errors.prod_sale }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">상품요약</th>
			<td class="pb-1">
				<input type="text" class="form-control"
				required name="prod_outline" value="${prod.prod_outline }" />
				<span class="error">${errors.prod_outline }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">상세정보</th>
			<td class="pb-1">
				<textarea name="prod_detail" class="form-control">${prod.prod_detail }</textarea>
				<span class="error">${errors.prod_detail }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">이미지경로</th>
			<td class="pb-1">
				<input type="file" class="form-control" name="prod_image" />
				<span class="error">${errors.prod_img }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">재고수량</th>
			<td class="pb-1">
				<input type="number" class="form-control"
				required name="prod_totalstock"
				value="${prod.prod_totalstock }" />
				<span class="error">${errors.prod_totalstock }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">적정재고</th>
			<td class="pb-1">
				<input type="number" class="form-control"
				required name="prod_properstock"
				value="${prod.prod_properstock }" />
				<span class="error">${errors.prod_properstock }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">상품크기</th>
			<td class="pb-1">
				<input type="text" class="form-control"
				name="prod_size" value="${prod.prod_size }" />
				<span class="error">${errors.prod_size }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">색상</th>
			<td class="pb-1">
				<input type="text" class="form-control"
				name="prod_color" value="${prod.prod_color }" />
				<span class="error">${errors.prod_color }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">배송방법</th>
			<td class="pb-1">
				<input type="text" class="form-control"
				name="prod_delivery" value="${prod.prod_delivery }" />
				<span class="error">${errors.prod_delivery }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">단위</th>
			<td class="pb-1">
				<input type="text" class="form-control"
				name="prod_unit" value="${prod.prod_unit }" />
				<span class="error">${errors.prod_unit }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">입고량</th>
			<td class="pb-1">
				<input type="number" class="form-control"
				name="prod_qtyin" value="${prod.prod_qtyin }" />
				<span class="error">${errors.prod_qtyin }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">출고량</th>
			<td class="pb-1">
				<input type="number" class="form-control"
				name="prod_qtysale" value="${prod.prod_qtysale }" />
				<span class="error">${errors.prod_qtysale }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">마일리지</th>
			<td class="pb-1">
				<input type="number" class="form-control"
				name="prod_mileage" value="${prod.prod_mileage }" />
				<span class="error">${errors.prod_mileage }</span>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="text-center pt-2">
				<input type="submit" class="btn btn-primary ml-5" value="저장" />
				<input type="reset" class="btn btn-warning" value="취소" />
				<a class="btn btn-secondary" href='<c:url value="/prod/prodList.do"/>'>목록으로</a>
			</td>
		</tr>
	</table>
</form>
<script src="${pageContext.request.contextPath }/js/prod/others.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/prod/prodForm.js"></script>