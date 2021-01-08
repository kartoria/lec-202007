<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script type="text/javascript" src="${cPath }/js/ckeditor/ckeditor.js"></script>	
<form:form modelAttribute="board" id="boardForm" method="post" enctype="multipart/form-data">
	<input type="hidden" class="form-control" name="bo_no" value="${board.bo_no }"/>
	<input type="hidden" name="bo_parent" value="${param.parent }" />
	<input type="hidden" class="form-control" required 
		name="bo_ip" value="${pageContext.request.remoteAddr}" />
	<table class="table table-bordered">
		<tr>
			<th class="text-center">제목</th>
			<td class="pb-1">
				<input type="text" class="form-control"
				required name="bo_title" value="${board.bo_title}" />
				<span class="error">${errors.bo_title }</span>
				<form:errors path="bo_title" element="span" cssClass="error"/>
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
				<div>
					<c:if test="${not empty board.attatchList }">
						<c:forEach items="${board.attatchList }" var="attatch" varStatus="vs">
							<span title="다운로드:${attatch.att_downcount }" class="attatchSpan">
								<img src="${cPath }/images/delete.png" class="delAtt" data-att-no="${attatch.att_no }"/>
								${attatch.att_filename } &nbsp; ${not vs.last?"|":"" }
							</span>
						</c:forEach>		
					</c:if>
				</div>
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
				<textarea name="bo_content" id="bo_content" class="form-control">${board.bo_content }</textarea>
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
</form:form>
<script type="text/javascript">
	CKEDITOR.replace("bo_content", {
		 filebrowserImageUploadUrl: '${cPath }/board/imageUpload.do?command=QuickUpload&type=Images'
	});

	$("#fileArea").on("click", ".plusBtn", function(){
		let clickDiv = $(this).parents("div.input-group");
		let newDiv = clickDiv.clone();
		let fileTag = newDiv.find("input[type='file']");
		fileTag.val("");
		clickDiv.after(newDiv);		
	});
	
	let boardForm =$("#boardForm");
	$(".delAtt").on("click", function(){
		let att_no = $(this).data("attNo");
		boardForm.append(
			$("<input>").attr({
				"type":"text"
				, "name":"delAttNos"
			}).val(att_no)
		);
		$(this).parent("span:first").hide();
	});
	
	boardForm.validate({
		onsubmit:true,
		onfocusout:function(element, event){
			return this.element(element);
		},
		errorPlacement: function(error, element) {
			error.appendTo( $(element).parents("td:first") );
	  	}
	});
</script>












