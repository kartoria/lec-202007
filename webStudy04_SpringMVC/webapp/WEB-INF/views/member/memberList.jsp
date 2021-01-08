<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<table class="table">
	<thead class="text-center">
		<tr>
			<th><spring:message code="member.mem_id" /></th>
			<th><spring:message code="member.mem_name" /></th>
			<th><spring:message code="member.mem_hp" /></th>
			<th><spring:message code="member.mem_mail" /></th>
			<th><spring:message code="member.mem_add1" /></th>
			<th><spring:message code="member.mem_mileage" /></th>
		</tr>
	</thead>
	<tbody id="listBody" class="text-center">
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<form id="searchForm">
					<input type="hidden" name="page" />
					<input type="hidden" name="searchType" />
					<input type="hidden" name="searchWord" />
				</form>
				<div id="inputUI" class="row justify-content-center mb-3">
					<div class="col-3">
						<select name="searchType" class="form-control mr-1">
							<option value>전체</option>
							<option value="name">이름</option>
							<option value="address">지역</option>
						</select>
					</div>
					<div class="col-4">
						<input type="text" name="searchWord"  class="form-control mr-3" />
					</div>
					<div class="col-auto">	
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary"/>
					</div>
				</div>	
				<div id="pagingArea"></div>
			</td>
		</tr>
	</tfoot>
</table>
<script type="text/javascript">
	let listBody = $("#listBody");
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
		  	let memberList = resp.pagingVO.dataList;
		  	let pagingHTML = resp.pagingVO.pagingHTML;
		  	let trTags = [];
		  	if(memberList.length>0){
		  		$(memberList).each(function(idx, member){
		  			trTags.push(
		  				$("<tr>").append(
		  					$("<td>").text(member.mem_id)		
		  					, $("<td>").text(member.mem_name)		
		  					, $("<td>").text(member.mem_hp)		
		  					, $("<td>").text(member.mem_mail)		
		  					, $("<td>").text(member.mem_add1)		
		  					, $("<td>").text(member.mem_mileage)		
		  				)		
		  			);
		  		});
		  	}else{
		  		trTags.push(
			  		$("<tr>").html(
		  				$("<td colspan='6'>").addClass("text-center").text("검색 결과 없음.")
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




















