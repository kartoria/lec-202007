<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container-fluid">
	<div class="card">
		<div class="card-body">
			<h4 class="card-title">시험 등록</h4>
			<p class="text-muted font-13"><span class="badge badge-success">1번</span>
			시험 분류, 시험 응시 기간을 설정해주세요.</p>

			<div class="table-responsive">
				<table class="table table-bordered table-striped mb-0">
					<tbody>
						<tr>
							<th class="text-nowrap" scope="row">시험 분류</th>
							<td colspan="5">
							<select class="form-control" id="exampleFormControlSelect1">
                                <option>시험 분류</option>
                                <option>중간 고사</option>
                                <option>기말 고사</option>
                            </select>
						</tr>
						<tr>
							<th class="text-nowrap" scope="row">시험 응시 기간</th>
							<td colspan="5">
								<div class="grid-structure">
								    <div class="row">
								        <div class="col-lg-6">
											<input type="date" class="form-control" value="2018-05-13" width="10">
								        </div>
								        <div class="col-lg-6">
											<input type="date" class="form-control" value="2018-05-15">
								        </div>
								    </div>
								</div>
							</td>
						</tr>
						<tr>
							<th class="text-nowrap" scope="row" colspan="2">
								<button type="button" class="btn waves-effect waves-light btn-rounded btn-info" id="nextBtn">다음</button>
								<button type="button" class="btn waves-effect waves-light btn-rounded btn-light" id="resetBtn">취소</button>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function (){
	$("#resetBtn").on("click", function(){
		window.history.back();
	});
	
	$("#nextBtn").on("click", function() {
		location.href= "${cPath}/myclass/testMake.do";		
	});
});	

</script>