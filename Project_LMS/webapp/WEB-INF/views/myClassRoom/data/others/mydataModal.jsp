<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<table style="margin: auto;">
	<tbody>
		<tr>
			<input type="hidden" name="boNo" value="${board.boNo }" />
			<td><h4 class="font-weight-bold">제목</h4></td>
			<td id="title">
			<input type=text name="boTitle"
					id="example-input-large" class="form-control form-control-lg"
					placeholder="제목을 입력하세요" value="${board.boTitle }" required  style="width:100%;"/></td>

		</tr>
		<tr>
			<td><h4 class="font-weight-bold">자료 내용</h4></td>
			<td><textarea name="boContent" class="form-control"
					rows="3" placeholder="자료 내용을 입력하세요" style="height: 200px;width:100%;" required="required">${board.boContent }</textarea></td>
		</tr>
		<tr>
			<td><h4 class="font-weight-bold">강의 자료</h4></td>
 			<td>
 				<c:if test="${not empty board.attachList }">
					<c:forEach items="${board.attachList }" var="attach" varStatus="idx">
						<span title="다운로드:"${attach.attDowncount }" class="attachSpan">
							<button type="button" class=" btn btn-danger btn-circle delAtt" data-att-no="${attach.attNo }"><i class="fas fa-minus"></i></button>
							${attach.attFilename } 
						</span>			
					</c:forEach>
				</c:if>
				<input type="file" id="inputGroupFile04" name="boFiles" value="${board.boFiles }" /> 
 			</td> 
		</tr>
	</tbody>
</table>
<script type="text/javascript">
// 첨부파일 삭제
let boardUpdate = $("#boardUpdate");
$(".delAtt").on("click", function() {
	let attNo = $(this).data("attNo");
	// 삭제버튼 누른 파일을 delAttNos 로 가져가기
	boardUpdate.append(
			$("<input>").attr({
				"type":"hidden"
				, "name":"delAttNos"
			}).val(attNo)
	);
	$(this).parent("span:first").hide();
});
</script>