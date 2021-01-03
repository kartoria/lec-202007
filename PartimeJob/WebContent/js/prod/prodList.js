	$("#createBtn").on("click", function(){
		location.href=$.getContextPath()+"/prod/prodInsert.do";
	});

	let prod_buyerTag = $("[name='prod_buyer']");
	let prod_lguTag = $("[name='prod_lgu']").getLprodAndBuyer(prod_buyerTag);	

	$("#inputUI :input").on("change", function(){
		searchBtn.trigger("click");
	});
	
	
	let listBody = $("#listBody");
	let prodViewModal = $("#prodViewModal").on("hidden.bs.modal", function(){
		$(this).find(".modal-body").empty();
	});
	
	prodViewModal.on("click", "#modifyBtn", function(){
 		let prodId = prodViewModal.find("table").data("prodId");
 		if(prodId)
			location.href=$.getContextPath()+"/prod/prodUpdate.do?what="+prodId;
	});
	
	listBody.on("click", "tr", function(){
		let prod = $(this).data("prod");
		prodViewModal.find(".modal-body").load($.getContextPath()+"/prod/prodView.do?what="+prod.prod_id, function(){
			prodViewModal.modal("show");
		});
	});
	
	let pagingArea = $("#pagingArea").on("click", "a" ,function(event){
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		searchForm.find("[name='page']").val("");
		return false;
	});
	
	let searchForm = $("#searchForm").ajaxForm({
		dataType:"json",
		success:function(resp){
		  	let prodList = resp.pagingVO.dataList;
		  	let pagingHTML = resp.pagingVO.pagingHTML;
		  	let trTags = [];
		  	if(prodList.length>0){
		  		$(prodList).each(function(idx, prod){
		  			trTags.push(
		  				$("<tr>").append(
		  					$("<td>").text(prod.prod_id)		
		  					, $("<td>").text(prod.prod_lgu)		
		  					, $("<td>").text(prod.prod_name)		
		  					, $("<td>").text(prod.prod_cost)		
		  					, $("<td>").text(prod.prod_price)		
		  					, $("<td>").text(prod.prod_buyer)		
		  					, $("<td>").text(prod.prod_mileage)		
		  				).data("prod", prod)		
		  			);
		  		});
		  	}else{
		  		trTags.push(
			  		$("<tr>").html(
			  			$("<td colspan='7'>").addClass("text-center").text("검색 결과 없음.")
			  		)
		  		);
		  		
		  	}
	  		let remainRowCnt = resp.pagingVO.screenSize - trTags.length;
	  		for(let i=0; i<remainRowCnt; i++){
	  			trTags.push($("<tr>").html($("<td colspan='7'>").html("&nbsp;")));
	  		}
		  	listBody.html(trTags);
		  	pagingArea.html(pagingHTML);
		}
	}).submit();
	
	let searchBtn = $("#searchBtn").on("click", function(){
		let inputs = $(this).parents("div#inputUI").find(":input[name]");
		$(inputs).each(function(index, input){
			let name = $(this).attr("name");
			let value = $(this).val();
			let hidden = searchForm.find("[name='"+name+"']");
			hidden.val(value);
		});
		searchForm.submit();
	});