<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container-fluid border px-3" style="min-height:300px">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 강의관리 > 시간표</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">시간표</h2>
		</div>
	</div>
	
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">강의할 시간표입니다.</h5>
		</div>
	</div>
	<div id="pdfArea" class="container" style="width: 50%; height: 1000px;">
		<iframe id="iframId" src="${cPath}/lms/professor/class/SchedulePDF.do" style="width: 80%; height: 960px;" ></iframe>
	</div>
</div>


<script type="text/javascript">
$(function(){
	$('#iframId').ready(function(){
		$.ajax({
			url : "${cPath}/pdf/pdfInsert.do",
			data : {"pdfName":"시간표"},
			method : "post",
			dataType : "json",
		});
	});
});





</script>
