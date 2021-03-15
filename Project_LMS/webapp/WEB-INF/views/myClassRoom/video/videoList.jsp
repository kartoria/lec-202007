<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 내 강의실 > 학습목차 > 강의목록 </h5>
		</div>
	</div>
	
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">강의목록</h2>
		</div>
	</div>
	
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">주차별로 업로드되는 강의를 학습할 수 있습니다.</h5>
		</div>
	</div>
        	<!-- ======교수일때만 강의 등록 가능=================  -->
    <security:authorize access="hasRole('ROLE_PROFESSOR')">
	<security:authentication property="principal" var="principal" />
	<c:set var="authMember" value="${principal.realMember }" />
	
	<div class="row mt-2 mb-2">
	    <div class="col-12 d-flex justify-content-end align-items-right">
<!-- 			<div class="col-lg-2"> -->
<!-- 				<div class="grid-container"> -->
					<a class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2"
						href="${cPath}/myclass/${lecCode}/videoForm.do" role="button">영상등록</a>
<!-- 				</div> -->
<!-- 			</div> -->
	    </div>
    </div>
	</security:authorize>
	<div class="row">	    
   		 <div class="col-12">
         	<div class="card">
                <div class="table-responsive">
                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col"><h4 class="font-weight-bold">차수</h4></th>
                                <th scope="col"><h4 class="font-weight-bold">강의 제목</h4></th>
                                <th scope="col"><h4 class="font-weight-bold">출석 시작일</h4></th>
                                <th scope="col"><h4 class="font-weight-bold">출석 마감일</h4></th>
                                <th scope="col"><h4 class="font-weight-bold">강의 시청</h4></th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:if test="${not empty videoList}">
                       			<c:forEach items="${videoList }" var="video">
		                             <tr>
		                                 <td><h5 class="text-dark mb-0 font-16 font-weight-bold"><i class="far fa-play-circle"></i>&nbsp;${video.week }차시</h5></td>
		                                 <td><h4>${video.videoTitle }</h4></td>
		                                 <td><h4><i class="fas fa-calendar-alt"></i>${fn:substring(video.videoAttstart, 0, 10)}</h4></td>
		                                 <td><h4><i class="fas fa-calendar-alt"></i>${fn:substring(video.videoAttend, 0, 10)}</h4></td>
		                                 <c:url var="viewURL" value="/myclass/${lecCode}/${video.videoNo}/videoView.do" />
		                                 <td><a href="${viewURL }"><button type="button" class="btn btn-success playbtn"><i class="fa fa-play">
		                                 <!-- ===교수&관리자/학생일때 버튼다르게=== -->	
		                                <security:authorize access="!hasRole('ROLE_STUDENT')">
										<security:authentication property="principal" var="principal" />
										<c:set var="authMember" value="${principal.realMember }" />
		                                 	영상확인
		                                </security:authorize>
		                                <security:authorize access="hasRole('ROLE_STUDENT')">
										<security:authentication property="principal" var="principal" />
										<c:set var="authMember" value="${principal.realMember }" />
											학습하기
										</security:authorize>
		                                 </i></button></a></td>
		                             </tr>
                        			</c:forEach>
                        	</c:if>
                        </tbody>
                    </table>
                </div>
			</div>
        </div>
	</div>
</div>
<!-- ============================================================== -->
<!-- End Container fluid  -->
<!-- ============================================================== -->
<script type="text/javascript">
$(function(){
	// 상세보기
	$(".playbtn").on("click", function() {
		location.href="${viewURL }";
	});	
});
</script>