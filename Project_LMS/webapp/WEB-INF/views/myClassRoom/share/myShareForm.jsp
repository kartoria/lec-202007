<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container-fluid">
	<div class="card">
		<div class="card-body">
			<h4 class="card-title">자료 공유</h4>
			<p class="text-muted font-13">강의와 관련한 자료를 자유롭게 공유할 수 있습니다.</p>
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
							<textarea class="form-control" id="lec_content" rows="3" placeholder="내용을 입력하세요." 
								style="margin-top: 0px; margin-bottom: 0px; height: 161px;">
							</textarea>
							<!-- CKEDITER -->
							<script type="text/javascript">
								 CKEDITOR.replace('lec_content'
								                , {height: 500                                                  
								                 });
							</script>
							</td>
						</tr>
						<tr>
							<th class="text-nowrap" scope="row">첨부 파일</th>
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