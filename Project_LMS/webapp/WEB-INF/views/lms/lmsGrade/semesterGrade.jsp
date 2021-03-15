<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container-fluid">
<div class="row my-3">
	<div class="col-12 d-flex justify-content-start align-items-center">
		<h5 class="ml-2">
			<i class="fas fa-home"></i> 학점 및 성적 > 금학기 성적
		</h5>
	</div>
</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">금학기 성적</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">강의평가 이후 이번 학기의 성적평가 결과를 조회할 수 있습니다. 미채점으로 표시되는 강의는 아직 해당 강의의 담당교수님께서 성적평가를 하지않은 강의입니다.</h5>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-12">
			<table class="table table-bordered text-center">
				<thead class="bg-primary text-white">
					<tr>
						<th>강의번호</th>
						<th>교과구분</th>
						<th>교과목명</th>
						<th>학점</th>
						<th>성적등급</th>
						<th>내가 평가한 점수</th>
					</tr>
				</thead>
				<tbody class="bg-white text-dark">
					<c:forEach items="${gradeList}" var="grade" varStatus="status">
						<tr>
							<td><a href="#" class="lecCode" data-lec-code="${grade.lecCode }">${grade.lecCode }</a></td>
							<td>${grade.subDetail }</td>
							<td>${grade.subName }</td>
							<td>${grade.subCredit }</td>
					<c:choose>
						<c:when test="${empty grade.tlecScore}">
							<td colspan="2">
								<button class="tlecScoreInsertBtn btn btn-warning px-5" 
									data-toggle="modal"
									data-sub-name="${grade.subName }"
									data-tlec-no="${grade.tlecNo }"
									data-target="#myModal">강의평가 미완료</button>
							</td>
						</c:when>
						<c:otherwise>
							<td>${grade.code }</td>
							<td>${grade.tlecScore }</td>
						</c:otherwise>
						</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>	
</div>
<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel"></h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <form id="tlecScoreForm" action="${cPath}/lms/student/grade/lectureEvaluation.do" method="post">
            	<input type="hidden" name="tlecNo"/>
	            <div class="modal-body">
	            	<div class="custom-control custom-radio">
		            	<input class="custom-control-input" type="radio" id="radio1" name="tlecScore" value="1" class="custom-control-input">
		            	<label for="radio1" class="custom-control-label">매우 별로</label>
	            	</div>
	            	<div class="custom-control custom-radio">
		            	<input class="custom-control-input" type="radio" id="radio2" name="tlecScore" value="2" class="custom-control-input">
		            	<label for="radio2" class="custom-control-label">별로</label>
	            	</div>
	            	<div class="custom-control custom-radio">
		            	<input class="custom-control-input" type="radio" id="radio3" name="tlecScore" value="3" class="custom-control-input">
		            	<label for="radio3" class="custom-control-label">보통</label>
	            	</div>
	            	<div class="custom-control custom-radio">
		            	<input class="custom-control-input" type="radio" id="radio4" name="tlecScore" value="4" class="custom-control-input">
		            	<label for="radio4" class="custom-control-label">좋음</label>
	            	</div>
	            	<div class="custom-control custom-radio">
		            	<input class="custom-control-input" type="radio" id="radio5" name="tlecScore" value="5" class="custom-control-input">
		            	<label for="radio5" class="custom-control-label">매우 좋음</label>
	            	</div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-light"
	                    data-dismiss="modal">취소</button>
	                <input type="submit" class="btn btn-primary" value="평가완료">
	            </div>
           	</form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script type="text/javascript">
	let myModal = $("#myModal");
	$(".tlecScoreInsertBtn").on("click", function(){
		console.log($(this).data("subName"));
		let subName = $(this).data("subName");
		let tlecNo = $(this).data("tlecNo");
		$("input[name='tlecNo']").val(tlecNo);
		$(".modal-title").html(subName + " 강의를 들어보니 어떠셨나요?");
	});
	
	$("#tlecScoreForm").ajaxForm({
		dataType:"json",
		success:function(resp){
			if(resp.result == "OK"){
				alert("등록이 완료되었습니다.");
				location.reload();
			}
		}
	});
	
	$(".lecCode").on("click", function(event){
		event.preventDefault();
		let lecCode = $(this).data("lecCode");
		let link = "${cPath}/plan.do?lecCode="+lecCode;
		window.open(link,'new','scrollbars=yes,resizable=no width=800px height=1000px, left=0,top=0');
		return false
	});
</script>

