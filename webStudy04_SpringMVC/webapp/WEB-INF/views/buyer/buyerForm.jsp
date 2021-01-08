<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/DataTables/datatables.min.css"> 
<script src="${pageContext.request.contextPath }/js/DataTables/datatables.min.js"></script>

<form method="post" action="<c:url value='${currentAction }'/>" enctype="multipart/form-data">

<input type="hidden" readonly name="buyer_id" value="${buyer.buyer_id }" />

<table class="table">
	<tr class="row">
		<th class="col-3">거래처명</th>
		<td class="col-9">
			<input class="form-control" type="text" name="buyer_name" value="${buyer.buyer_name }" />
			<span class="error">${errors.buyer_name }</span>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">거래처분류코드</th>
		<td class="col-9">
			<select name="buyer_lgu" class="form-control" data-init-value="${buyer.buyer_lgu }">
				<option value="">상품분류</option>
			</select>
			<span class="error">${errors.buyer_lgu }</span>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">은행명</th>
		<td class="col-9">
			<input class="form-control" type="text" name="buyer_bank" value="${buyer.buyer_bank }" />
			<span class="error">${errors.buyer_bank }</span>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">계좌번호</th>
		<td class="col-9">
			<input class="form-control" type="text" name="buyer_bankno" value="${buyer.buyer_bankno }" />
			<span class="error">${errors.buyer_bankno }</span>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">계좌주</th>
		<td class="col-9">
			<input class="form-control" type="text" name="buyer_bankname" value="${buyer.buyer_bankname }" />
			<span class="error">${errors.buyer_bankname }</span>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">거래처전경</th>
		<td class="col-9">
			<input class="form-control" type="file" name="buyer_image"/>
			<span class="error">${errors.buyer_img }</span>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">우편번호</th>
		<td class="col-9">
			<div class="input-group">
				<input class="form-control" type="text"  name="buyer_zip" value="${buyer.buyer_zip }" 
						maxLength="7" pattern="[0-9]{3}-[0-9]{3}" readonly
						data-msg-="우편번호 필수" data-msg-pattern="형식확인"/>
				<span class="error">${errors.buyer_zip }</span>
				<button id="zipBtn" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#zipModal">우편번호 검색</button>
			</div>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">주소1</th>
		<td class="col-9">
			<div class="form-group">
				<input type="text" class="col form-control"  name="buyer_add1" value="${buyer.buyer_add1 }" 
						maxLength="80" readonly  data-msg="주소 필수" />
				<span class="error">${errors.buyer_add1 }</span>
			</div>		
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">주소2</th>
		<td class="col-9">
			<div class="form-group">
			<input type="text" class="col form-control"  name="buyer_add2" value="${buyer.buyer_add2 }" 
						maxLength="80" readonly  data-msg="주소 필수" />
				<span class="error">${errors.buyer_add2 }</span>
			</div>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">회사전번</th>
		<td class="col-9">
			<input class="form-control" type="text" name="buyer_comtel" value="${buyer.buyer_comtel }" />
			<span class="error">${errors.buyer_comtel }</span>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">팩스번호</th>
		<td class="col-9">
			<input class="form-control" type="text" name="buyer_fax" value="${buyer.buyer_fax }" />
			<span class="error">${errors.buyer_fax }</span>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">이메일</th>
		<td class="col-9">
			<input class="form-control" type="text" name="buyer_mail" value="${buyer.buyer_mail }" />
			<span class="error">${errors.buyer_mail }</span>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">담당자명</th>
		<td class="col-9">
			<input class="form-control" type="text" name="buyer_charger" value="${buyer.buyer_charger }" />
			<span class="error">${errors.buyer_charger }</span>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">담당자사진</th>
		<td class="col-9">
			<input class="form-control" type="file" name="charger_image"/>
			<span class="error">${errors.charger_img }</span>
		</td>
	</tr>
	<tr class="row">
		<th class="col-3">내선번호</th>
		<td class="col-9">
			<input class="form-control" type="text" name="buyer_telext" value="${buyer.buyer_telext }" />
			<span class="error">${errors.buyer_telext }</span>
		</td>
	</tr>
	<tr class="row">
		<td class="col text-center">
			<input type="submit" value="저장" class="btn btn-primary"/>
			<input type="reset" value="취소" class="btn btn-warning"/>
			<a class="btn btn-secondary" href='<c:url value="/buyer/buyerList.do"/>'>목록으로</a>
		</td>
	</tr>
</table>
</form>
<script src="${pageContext.request.contextPath }/js/prod/others.js"></script>
<script src="${pageContext.request.contextPath }/js/commons/searchZip.js"></script>
<script type="text/javascript">
	let prod_lguTag = $("[name='buyer_lgu']").getLprodAndBuyer();
	$.searchZip({
		zipCodeTag:$("[name='buyer_zip']").get(0),
		add1Tag:$("[name='buyer_add1']").get(0),
		add2Tag:$("[name='buyer_add2']").get(0)
	});
</script>
