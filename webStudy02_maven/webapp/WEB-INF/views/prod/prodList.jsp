<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--    분류명, 상품명, 거래처명으로 검색 기능 제공  -->
<table class="table table-bordered">
	<thead>
		<tr>
			<th>상품코드</th>
			<th>상품분류명</th>
			<th>상품명</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>거래처명</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
				<form id="searchForm">
					<input type="hidden" name="page" />
					<input type="hidden" name="prod_lgu" />
					<input type="hidden" name="prod_buyer" />
					<input type="hidden" name="prod_name" />
				</form>
				<div id="inputUI" class="row">
					<div class="col-auto">
						<select name="prod_lgu" class="form-control">
							<option value>분류선택</option>
						</select>
					</div>
					<div class="col-auto">
						<select name="prod_buyer" class="form-control">
							<option value>거래처선택</option>
						</select>
					</div>
					<div class="col-auto">
						<input type="text" name="prod_name" class="form-control"/>
					</div>
					<div class="col-auto">
						<input type="button" value="검색"  id="searchBtn"/>
					</div>
				</div>	
				<div id="pagingArea"></div>
			</td>
		</tr>
	</tfoot>
</table>
<script type="text/javascript">
	let prod_lguTag = $("[name='prod_lgu']").on("change", function(){
		let buyer_lgu = this.value;
		prod_buyerTag.find("option:gt(0)").hide();
		prod_buyerTag.find("option."+buyer_lgu).show();
	});
	let prod_buyerTag = $("[name='prod_buyer']");
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

	let listBody = $("#listBody");
	let pagingArea = $("#pagingArea");
	
	let searchForm = $("#searchForm").ajaxForm({
		dataType:"json",
		resetForm:true,
		success:function(resp){
		  	let prodList = resp.pagingVO.dataList;
		  	let pagingHTML = resp.pagingVO.pagingHTML;
		  	let trTags = [];
		  	if(prodList.length>0){
		  		$(prodList).each(function(idx, prod){
		  			trTags.push(
		  				$("<tr class='trTag'>").append(
		  					$("<td>").append($("<a href='<%=request.getContextPath() %>/prod/prodView.do?prod_id="+prod.prod_id+"'>").text(prod.prod_id))
		  					, $("<td>").text(prod.prod_lgu)
		  					, $("<td>").text(prod.prod_name)		
		  					, $("<td>").text(prod.prod_cost)		
		  					, $("<td>").text(prod.prod_price)		
		  					, $("<td>").text(prod.prod_buyer)		
		  					, $("<td>").text(prod.prod_mileage)		
		  				)
		  			);
		  		});
		  	}else{
		  		trTags.push(
			  		$("<tr>").html(
			  			$("<td colspan='7'>").text("검색 결과 없음.")
			  		)
		  		);
		  		
		  	}
		  	listBody.html(trTags);
		  	pagingArea.html(pagingHTML);
// 		  	console.log(searchForm[0]);
		  	searchForm.find(":input[name='page']").val("");
		}
	}).submit();
	
	
<%-- 	$("[name='searchType']").val("<%=Objects.toString( pagingVO.getSearchVO().getSearchType(), "") %>"); --%>
	pagingArea.on("click", "a" ,function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		searchForm.find("[name='page']").val("");
		return false;
	});
	
	$("#searchBtn").on("click", function(){
		let inputs = $(this).parents("div#inputUI").find(":input[name]");
		$(inputs).each(function(index, input){
			let name = $(this).attr("name");
			let value = $(this).val();
			let hidden = searchForm.find("[name='"+name+"']");
			hidden.val(value);
		});
		searchForm.submit();
	});
</script>
