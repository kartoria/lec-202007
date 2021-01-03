<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/DataTables/datatables.min.css">
<script
	src="${pageContext.request.contextPath }/js/DataTables/datatables.min.js"></script>
<form method="post" id="albaForm" enctype="multipart/form-data">
	<table class="table table-responsive">
		<tr>
			<th class="text-center">이름</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" required name="al_name"
				value="${alba.al_name }" /> <span class="error">${errors.al_name }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">나이</th>
			<td class="pb-1"><input type="number"
				class="form-control editable" required name="al_age"
				value="${alba.al_age }" /> <span class="error">${errors.al_age }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">우편번호</th>
			<td class="pb-1">
				<div class="input-group">
					<input type="text" class="form-control editable" required readonly
						name="al_zip" value="${alba.al_zip }" id="al_zip" tabindex="-1" />
					<button id="zipBtn" type="button" class="btn btn-primary"
						data-bs-toggle="modal" data-bs-target="#zipModal">우편번호 검색</button>
					<span class="error">${errors.al_zip }</span>
				</div>
			</td>
		</tr>
		<tr>
			<th class="text-center">주소1</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" required readonly name="al_add1"
				value="${alba.al_add1 }" id="al_add1" tabindex="-1" /> <span
				class="error">${errors.al_add1 }</span></td>
		</tr>
		<tr>
			<th class="text-center">주소2</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" required readonly name="al_add2"
				value="${alba.al_add2 }" id="al_add2" tabindex="-1" /> <span
				class="error">${errors.al_add2 }</span></td>
		</tr>

		<tr>
			<th class="text-center">주민번호1</th>
			<td class="pb-1"><input type="text" class="form-control"
				required name="al_regno1" value="${alba.al_regno1 }" pattern="\d{6}"
				maxlength="6" /> <span class="error">${errors.al_regno1 }</span></td>
		</tr>
		<tr>
			<th class="text-center">주민번호2</th>
			<td class="pb-1"><input type="text" class="form-control"
				required name="al_regno2" value="${alba.al_regno2 }" size="7" /> <span
				class="error">${errors.al_regno2 }</span></td>
		</tr>
		<tr>
			<th class="text-center">휴대폰</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" name="al_hp" value="${alba.al_hp }" />
				<span class="error">${errors.al_hp }</span></td>
		</tr>
		<tr>
			<th class="text-center">학력</th>
			<td class="pb-1"><select class="form-control editable"
				name="al_code">
					<option value>학력 선택</option>
			</select> <span class="error">${errors.al_code }</span></td>
		</tr>
		<tr>
			<th class="text-center">성별</th>
			<td class="pb-1"><select class="form-control" name="al_gen">
					<option value>성별 선택</option>
			</select> <span class="error">${errors.al_gen }</span></td>
		</tr>
		<tr>
			<th class="text-center">이메일</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" required name="al_mail"
				value="${alba.al_mail }" /> <span class="error">${errors.al_mail }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">경력사항</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" name="al_job"
				value="${alba.al_career }" /> <span class="error">${errors.al_career }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">특기사항</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" name="al_spec"
				value="${alba.al_spec }" /> <span class="error">${errors.al_spec }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">비고</th>
			<td class="pb-1"><input type="text"
				class="form-control editable" name="al_spec"
				value="${alba.al_spec }" /> <span class="error">${errors.al_spec }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">사진</th>
			<td class="pb-1"><input type="file"
				class="form-control editable" name="al_image" /> <span
				class="error">${errors.al_img }</span></td>
		</tr>
		<tr>
			<td colspan="2" class="text-center pt-2"><input type="submit"
				class="btn btn-primary ml-5" value="저장" /> <input type="reset"
				class="btn btn-secondary" value="취소" /></td>
		</tr>
	</table>
</form>

<script
	src="${pageContext.request.contextPath }/js/commons/searchZip.js"></script>
<script type="text/javascript">
	$(function() {
		const validateOptions = {
			onsubmit : true,
			onfocusout : function(element, event) {
				return this.element(element);
			},
			errorPlacement : function(error, element) {
				error.appendTo($(element).parents("td:first"));
			}
		}
// 		const EDITABLE = ${"MODIFY" eq command};
		if (EDITABLE) {
			//========입력제한 UI 설정==============================================================
			$("#albaForm input:not(.editable)").prop("readonly", true);
			$("button.insertOnly").hide().prop("disable", true);
			//==================================================================================
		}
		let validator = $("#albaForm").validate(validateOptions);

		//========우편번호 검색=================================================================
		$.searchZip({
			zipCodeTag : $("#al_zip").get(0),
			add1Tag : $("#al_add1").get(0),
			add2Tag : $("#al_add2").get(0)
		});
		//==================================================================================
	});
</script>



