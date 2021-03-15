<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style type="text/css">
	#textArea {
		resize: none;
		width: 100%;
	}
	
	#tableArea {
		text-align: center;
	}
	
	#thArea {
		background-color: #CCFFCC;
	}
</style>
<div class="container-fluid">
	<div class="row">
		<div class="col-12">
			<h3 class="ml-2">
				<i class="fas fa-home"></i> 상담 > 상담 내역 > 상담 상세 내역
			</h3>
		</div>
	</div>
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-9 text-left">
			<h2 class="font-weight-bold">상담 상세 내역 조회</h2>
		</div>
	</div>
	<div class="row">
		<div class="col-11">
			<h5 class="pb-5 ml-2">상담한 내역을 상세하게 조회할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12"><br>
			<table id="tableArea" class="table table-bordered">
				<label>상담 상세 정보</label>
				<tbody>
		        	<tr>
		                <td class="table-active">학&nbsp;&nbsp;생&nbsp;&nbsp;이&nbsp;&nbsp;름</td>
		                <td>${coun.stuName }</tdh>
		                <td class="table-active">교&nbsp;&nbsp;수&nbsp;&nbsp;이&nbsp;&nbsp;름</td>
		                <td>${coun.proName }</td>
		            </tr>
		            <tr>
						<td class="table-active">소&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;속</td>
						<td>${coun.departVO.depName }</td>
		                <td class="table-active">학&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;번</td>
		                <td>${coun.cstStudent }</td>
		            </tr>
		            <tr>
		                <td class="table-active">학&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;년</td>
		                <td>
		                	<fmt:formatNumber var="grd" value="${(coun.memberVO.memGrd/2 + 0.5) - ((coun.memberVO.memGrd/2 +0.5)%1)}"/>
		                	${grd}학년
		                </td>
		                <td class="table-active">이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;메&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;일</td>
		                <td>
		                	<c:if test="${not empty coun.memberVO.memMail }">
			                	${coun.memberVO.memMail }
		                	</c:if>
		                	<c:if test="${empty coun.memberVO.memMail }">
			                	-
		                	</c:if>
		                </td>
		            </tr>
		            <tr>
		            	<td class="table-active">전&nbsp;&nbsp;화&nbsp;&nbsp;번&nbsp;&nbsp;호</td>
		            	<td>
		            		<c:if test="${not empty coun.memberVO.memTel}">
			            		${coun.memberVO.memTel }
		            		</c:if>
		            		<c:if test="${empty coun.memberVO.memTel}">
			            		-
		            		</c:if>
		            	</td>
		                <td class="table-active">상&nbsp;&nbsp;담&nbsp;&nbsp;날 &nbsp;&nbsp;짜</td>
		                <td>${coun.cstDate }</td>
		            </tr>
		        </tbody>
		    </table><br>
		    <div>
   			    <label>상담 내용</label> 
				<textarea id="textArea" rows="10" disabled="disabled" >${coun.cstContent }</textarea>
		    </div>
		</div>
	</div> <!-- end card-body-->
</div> <!-- end card-->

<!-- 상담을 아직 하지 않았을 때(상담 내용이 null) 뜨는 모달창 -->
<input type="hidden" id="contentNullModal"class="btn btn-secondary" data-toggle="modal" data-target="#centermodalNegative" value="숨겨진모달">
<div class="modal fade" id="centermodalNegative" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    </div>
		    <div class="modal-body">
		        <h5 style="text-align: center;">아직 상담 내역이 없습니다.</h5>
		    </div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	let content = ${coun.cstContent }+"";
	if(content == 0){
		$("#contentNullModal").click();
	}
});
</script>