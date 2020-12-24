<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="errors" class="java.util.LinkedHashMap" scope="request"/>
<form method="post" id="prodForm">
	<table class="table table-responsive">
		<tr>
			<th class="text-center">상품 분류</th>
			<td class="pb-1">
				<select name="prod_lgu" class="form-control">
					<option value>분류선택</option>
				</select>
				<span class="error"><%=errors.get("prod_lgu") %></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">판매자</th>
			<td class="pb-1">
				<select name="prod_buyer" class="form-control">
					<option value>거래처선택</option>
				</select>
				<span class="error"><%=errors.get("prod_buyer") %></span>		 
			</td>
		</tr>
		<tr>
			<th class="text-center">상품 이름</th>
			<td class="pb-1">
				<input type="text" name="prod_name" class="form-control">
				<span class="error"><%=errors.get("prod_anme") %></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">구매가격</th>
			<td class="pb-1">
				<input type="number" name="prod_cost" class="form-control editable">
				<span class="error"><%=errors.get("prod_cost") %></span>		 
			</td>
		</tr>
		<tr>
			<th class="text-center">상품가격</th>
			<td class="pb-1">
				<input type="number" name="prod_price" class="form-control editable">
				<span class="error"><%=errors.get("prod_price") %></span>		 
			</td>
		</tr>
		<tr>
			<th class="text-center">판매가격</th>
			<td class="pb-1">
				<input type="number" name="prod_sale" class="form-control editable">
				<span class="error"><%=errors.get("prod_sale") %></span>		 
			</td>
		</tr>
		<tr>
			<th class="text-center">상품설명</th>
			<td class="pb-1">
				<input type="text" name="prod_outline" class="form-control editable">
				<span class="error"><%=errors.get("prod_outline") %></span>		 
			</td>
		</tr>
		<tr>
			<th class="text-center">상세설명</th>
			<td class="pb-1">
				<input type="text" name="prod_detail" class="form-control editable">
				<span class="error"><%=errors.get("prod_detail") %></span>		 
			</td>
		</tr>
		<tr>
			<th class="text-center">총 재고량</th>
			<td class="pb-1">
				<input type="number" name="prod_totalstock" class="form-control editable">
				<span class="error"><%=errors.get("prod_properstock") %></span>		 
			</td>
		</tr>
		<tr>
			<th class="text-center">적정재고</th>
			<td class="pb-1">
				<input type="number" name="prod_properstock" class="form-control editable">
				<span class="error"><%=errors.get("prod_properstock") %></span>		 
			</td>
		</tr>
		<tr>
			<th class="text-center">사이즈</th>
			<td class="pb-1">
				<input type="text" name="prod_size" class="form-control editable">
				<span class="error"><%=errors.get("prod_size") %></span>		 
			</td>
		</tr>
		<tr>
			<th class="text-center">색상</th>
			<td class="pb-1">
				<input type="text" name="prod_color" class="form-control editable">
				<span class="error"><%=errors.get("prod_color") %></span>		 
			</td>
		</tr>
		<tr>
			<th class="text-center">주의사항</th>
			<td class="pb-1">
				<input type="text" name="prod_delivery" class="form-control editable">
				<span class="error"><%=errors.get("prod_delivery") %></span>		 
			</td>
		</tr>
		<tr>
			<th class="text-center">단위</th>
			<td class="pb-1">
				<input type="text" name="prod_unit" class="form-control editable">
				<span class="error"><%=errors.get("prod_unit") %></span>		 
			</td>
		</tr>
		<tr>
			<th class="text-center">입고수량</th>
			<td class="pb-1">
				<input type="number" name="prod_qtyin" class="form-control editable">
				<span class="error"><%=errors.get("prod_qtyin") %></span>		 
			</td>
		</tr>
		<tr>
			<th class="text-center">판매수량</th>
			<td class="pb-1">
				<input type="number" name="prod_qtysale" class="form-control editable">
				<span class="error"><%=errors.get("prod_qtysale") %></span>		 
			</td>
		</tr>
		<tr>
			<th class="text-center">마일리지</th>
			<td class="pb-1">
				<input type="number" name="prod_mileage" class="form-control editable">
				<span class="error"><%=errors.get("prod_mileage") %></span>		 
			</td>
		</tr>
		<tr>
			<td colspan="2" class="text-center pt-2">
				<input type="submit" class="btn btn-primary ml-5" value="저장" />
				<input type="reset" class="btn btn-secondary" value="취소" />
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript">
	let prod_buyerTag = $("[name='prod_buyer']");
	
	let prod_lguTag = $("[name='prod_lgu']").on("change", function(){
		let buyer_lgu = this.value;
		prod_buyerTag.find("option:gt(0)").hide();
		prod_buyerTag.find("option."+buyer_lgu).show();
	});
	
	
	$.ajax({
		url:"<%=request.getContextPath()%>/prod/getOthers.do",
		dataType:"json",
		success:function(resp){
			let buyerOpts = [];
			let lprodOpts = [];
			$(resp.buyerList).each(function(idx, buyer){
				buyerOpts.push(
					$("<option>").text(buyer.buyer_name)
								.attr("value", buyer.buyer_id)
								.addClass(buyer.buyer_lgu)
								.prop("selected", "${pagingVO.searchDetail.prod_buyer}"==buyer.buyer_id)
				);
			});
			$(resp.lprodList).each(function(idx, lprod){
				lprodOpts.push(
					$("<option>").text(lprod.lprod_nm)
								.attr("value", lprod.lprod_gu)
								.prop("selected", "${pagingVO.searchDetail.prod_lgu}"==lprod.lprod_gu)
				);
			});
			prod_lguTag.append(lprodOpts);
			prod_buyerTag.append(buyerOpts);
		},
		error:function(xhr){
			console.log(xhr);
		}
	});
</script>
