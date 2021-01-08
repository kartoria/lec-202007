<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	#listBody>tr{
		cursor: pointer;
	}
</style>    
<!--    분류명, 상품명, 거래처명으로 검색 기능 제공  -->
<table class="table">
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
				<div id="inputUI" class="row justify-content-center mb-3">
					<div class="col-auto">
						<select name="prod_lgu" class="form-control" data-init-value="${param.prod_lgu }">
							<option value>분류선택</option>
						</select>
					</div>
					<div class="col-auto">
						<select name="prod_buyer" class="form-control" data-init-value="${param.prod_buyer }">
							<option value>거래처선택</option>
						</select>
					</div>
					<div class="col-auto">
						<input type="text" name="prod_name" class="form-control"/>
					</div>
					<div class="col-auto">
						<input type="button" value="검색"  id="searchBtn" class="btn btn-primary"/>
						<input type="button" value="신규등록"  id="createBtn" class="btn btn-primary"/>
					</div>
				</div>	
				<div id="pagingArea"></div>
			</td>
		</tr>
	</tfoot>
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
			<input type="button" value="수정" class="btn btn-primary mr-3" id="modifyBtn" />
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	      </div>
	    </div>
	</div>
</div>
<script src="${pageContext.request.contextPath }/js/prod/others.js"></script>
<script src="${pageContext.request.contextPath }/js/prod/prodList.js"></script>
