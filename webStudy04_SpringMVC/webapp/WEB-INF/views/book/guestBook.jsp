<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<h1>방명록</h1>

<form action="${pageContext.request.contextPath }/book" method="post"
	enctype="Multipart/form-data">
	<table class="table form-inline">
		<tr>
			<td><input type="text" required name="gb_writer"
				class="form-control" placeholder="작성자" /></td>
			<td><input type="password" required name="gb_pass"
				class="form-control" placeholder="비밀번호" /></td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="input-group">
					<textarea class="form-control mb-2 mr-2" rows="2"
						placeholder="내용 200자 이내" maxlength="200" name="gb_content"></textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="input-group">
					<input type="file" class="form-control mb-2 mr-2"
						placeholder="프로필 이미지" name="gb_profile" />
				</div>
			</td>
		</tr>
	</table>
	<input type="button" class="btn btn-primary" value="작성" />
</form>

