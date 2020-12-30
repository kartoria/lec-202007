<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form method="post" enctype="multipart/form-data">
	<input type="hidden" class="form-control" name="bo_no" />
	<input type="hidden" class="form-control" required 
		name="bo_ip" value="${pageContext.request.remoteAddr}" />
	<table class="table table-bordered">
		<tr>
			<th class="text-center">제목</th>
			<td class="pb-1">
				<input type="text" class="form-control"
				required name="bo_title" value="${board.bo_title}" />
				<span class="error">${errors.bo_title }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">작성자</th>
			<td class="pb-1">
				<input type="text" class="form-control"
				required name="bo_writer" value="${board.bo_writer}" />
				<span class="error">${errors.bo_writer }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">비밀번호</th>
			<td class="pb-1">
				<input type="text" class="form-control" required name="bo_pass" />
				<span
				class="error">${errors.bo_pass }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">이메일</th>
			<td class="pb-1">
				<input type="text" class="form-control"
				name="bo_mail" value="${board.bo_mail}" />
				<span class="error">${errors.bo_mail }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">첨부파일</th>
			<td class="pb-1" id="fileArea">
				<div class="input-group">
					<input type="file" class="form-control" name="bo_files" />
					<span class="btn btn-primary plusBtn">+</span>
				</div>
				<span class="error">${errors.bo_files }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">내용</th>
			<td class="pb-1">
				<textarea name="bo_content" class="form-control">${board.bo_content }</textarea>
				<span class="error">${errors.bo_content }</span>
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
	$("#fileArea").on("click", ".plusBtn", function(){
		let clickDiv = $(this).parents("div.input-group");
		let newDiv = clickDiv.clone();
		let fileTag = newDiv.find("input[type='file']");
		fileTag.val("");
		clickDiv.after(newDiv);		
	});
</script>