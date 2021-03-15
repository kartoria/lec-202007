<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container-fluid">
	<div class="card">

		<div class="card-body">
			<h4 class="card-title">강의 자료 등록</h4>
			<p class="text-muted font-13">강의 자료가 등록됩니다.</p>

			<div class="table-responsive">
				<table class="table table-bordered table-striped mb-0">
					<tbody>
						<tr>
							<th class="text-nowrap" scope="row">제목</th>
							<td colspan="5"><input type="text" class="form-control"
								id="placeholder" placeholder="제목을 입력하세요."></td>
						</tr>
						<tr>
							<th class="text-nowrap" scope="row">내용</th>
							<td colspan="5">
							<textarea class="form-control" id="data_content" rows="3" placeholder="내용을 입력하세요." 
								style="margin-top: 0px; margin-bottom: 0px; height: 161px;">
							</textarea>
							<!-- CKEDITER -->
							<script type="text/javascript">
								 CKEDITOR.replace('data_content'
								                , {height: 500                                                  
								                 });
							</script>
							</td>
						</tr>
						<tr>
							<th class="text-nowrap" scope="row">첨부파일</th>
							<td colspan="5">
							<div class="card-body">
									<div class="custom-file">
										<input type="file" class="custom-file-input"
											id="inputGroupFile04"> <label
											class="custom-file-label" for="inputGroupFile04">파일을 선택하세요</label>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th class="text-nowrap" scope="row" colspan="2">
								<button type="button" class="btn waves-effect waves-light btn-rounded btn-info">등록</button>
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
});	

</script>