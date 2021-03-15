<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">
#delBtn {
	float: right;
}
#point {
	width: 10%;
	float: right;
}
</style>
<div class="container-fluid">
	<div class="card">
		<div class="card-body">
			<h4 class="card-title">시험 등록</h4>
			<p class="text-muted font-13">
				<span class="badge badge-success">2번</span> 원하시는 문항의 타입을 추가해주세요.
			</p>
			<div class="table-responsive">
				<table class="table table-bordered table-striped mb-0">
					<tbody>
						<tr>
							<th class="text-nowrap" scope="row">객관식 문항</th>
							<td colspan="5"><span class="badge badge-pill badge-danger">주의</span>
								객관식 문항은 최대 30개까지 가능합니다.<br>
								<button type="button"
									class="btn waves-effect waves-light btn-light">문항 추가</button></td>
							<th class="text-nowrap" scope="row">주관식 문항</th>
							<td colspan="5"><span class="badge badge-pill badge-danger">주의</span>
								주관식 문항은 최대 20개까지 가능합니다.<br>
								<button type="button"
									class="btn waves-effect waves-light btn-light">문항 추가</button>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 객관식 -->
	<div class="col-md-8">
		<div class="card">
			<div class="card-body">
				<button type="button"
				class="btn waves-effect waves-light btn-rounded btn-danger" id="delBtn">삭제</button>
				<input type="number" class="form-control" id="point">
					<h3><span class="badge badge-info">객관식</span></h3>
				<div>
					<table class="table">
						<thead class="thead-light">
							<tr>
								<th >1</th>
								<th scope="col" colspan="2"><input type="text" class="form-control"
									id="placeholder" placeholder="문제를 등록하세요"></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td><input class="form-check-input" type="radio"
									name="inlineRadioOptions" id="inlineRadio1" value="option1"></td>
								<td><input type="text" class="form-control"
									id="placeholder" placeholder="지문을 등록하세요."></td>
							</tr>
							<tr>
								<td>2</td>
								<td><input class="form-check-input" type="radio"
									name="inlineRadioOptions" id="inlineRadio1" value="option1"></td>
								<td><input type="text" class="form-control"
									id="placeholder" placeholder="지문을 등록하세요."></td>
							</tr>
							<tr>
								<td>3</td>
								<td><input class="form-check-input" type="radio"
									name="inlineRadioOptions" id="inlineRadio1" value="option1"></td>
								<td><input type="text" class="form-control"
									id="placeholder" placeholder="지문을 등록하세요."></td>
							</tr>
							<tr>
								<td>4</td>
								<td><input class="form-check-input" type="radio"
									name="inlineRadioOptions" id="inlineRadio1" value="option1"></td>
								<td><input type="text" class="form-control"
									id="placeholder" placeholder="지문을 등록하세요."></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- 주관식 -->
	<div class="col-md-8">
		<div class="card">
			<div class="card-body">
				<button type="button"
				class="btn waves-effect waves-light btn-rounded btn-danger" id="delBtn">삭제</button>
				<input type="number" class="form-control" id="point">
					<h3><span class="badge badge-warning">주관식</span></h3>
				<div>
					<table class="table">
						<thead class="thead-light">
							<tr>
								<th >1</th>
								<th scope="col" colspan="2"><input type="text" class="form-control"
									id="placeholder" placeholder="문제를 등록하세요"></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="3">
									<textarea class="form-control" rows="3" style="margin-top: 0px; margin-bottom: 0px; height: 136px;"  placeholder="답안을 등록하세요"></textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-8">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col" colspan="2"><i class="fas fa-exclamation-circle"> 총 배점과 현재 배점이 일치해야 등록이 가능합니다.</i> </th>
					<th scope="col" class="bg-warning text-white">현재 배점</th>
					<th scope="col">100</th>
					<th scope="col" class="bg-warning text-white">총 배점</th>
					<th scope="col">100</th>
				</tr>
				<tr>
					<th class="text-nowrap" scope="row" colspan="6">
						<button type="button"
							class="btn waves-effect waves-light btn-rounded btn-info"
							id="nextBtn">등록</button>
						<button type="button"
							class="btn waves-effect waves-light btn-rounded btn-light"
							id="resetBtn">취소</button>
					</th>
				</tr>
			</thead>
		</table>
	</div>

</div>
<!-- ================== 응시 여부 확인 모달 ================= -->
<!-- ================================================== -->
<!-- Warning Header Modal -->
<div id="startModal" class="modal fade" tabindex="-1" role="dialog"
    aria-labelledby="warning-header-modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-colored-header bg-warning">
                <button type="button" class="close" data-dismiss="modal"
                    aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <p>시험을 등록하시겠습니까?<br>
               	 응시 시작일 이후로 수정이 불가합니다.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light"
                    data-dismiss="modal">취소</button>
                <button type="button" class="btn btn-warning">등록</button>
            </div>
        </div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
$(function (){
	$("#resetBtn").on("click", function(){
		window.history.back();
	});
	
	$("#nextBtn").on("click", function() {
		$("#startModal").modal();
	});
});	

</script>