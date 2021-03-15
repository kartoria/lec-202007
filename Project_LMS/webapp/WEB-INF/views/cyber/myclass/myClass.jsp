<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 사이버 캠퍼스 > 내 강의실
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">내 강의실</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">강의실 제목부분을 눌러 해당 강의실로 이동합니다</h5>
		</div>
	</div>
	<c:forEach items="${lecList}" var="lecture" varStatus="status">
		<c:if test="${status.index mod 2 eq 0 }">
		<div class="row d-flex align-items-center">
		</c:if>
			<div class="col-md-6 flex-fill">
				<div class="card">
					<a href="${cPath}/myclass/${lecture.lecCode}/dashList.do">
						<div class="card-header bg-light d-flex align-items-center">
							<h3 class="font-weight-bold mb-0 text-dark">${lecture.subjectVo.subName }</h3>
						</div>
					</a>
					<div class="card-body">
						<div class="row">
							<div class="col-6 d-flex justify-content-start align-items-center">
								<h5>
									강의코드 : ${lecture.lecCode}
								</h5>
							</div>
							<div class="col-6 d-flex justify-content-start align-items-center">
								<h5>
									<c:forEach items="${lecture.ltimeDayStartEndList}" var="ltimeDayStartEnd" varStatus="status2">
		                    			강의 일자 : ${ltimeDayStartEnd}
		                    			<c:if test="${not status2.last}"> / </c:if>
									</c:forEach>
								</h5>
							</div>
						</div>
						<div class="row">
							<div class="col-6 justify-content-start align-items-center">
								<h5>
									개설학년 : ${lecture.lecGrd}
								</h5>
							</div>
							<div class="col-6 justify-content-start align-items-center">
								<h5>
									수강인원 : ${lecture.lecNmt}
								</h5>
							</div>
						</div>
						<c:if test="${not empty lecture.memberVo.memName }">
							<div class="row d-flex">
								<div class="col-12 justify-content-start align-items-center">
									<h5>강의자 : ${lecture.memberVo.memName } 교수</h5>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
	<c:if test="${status.index mod 2 eq 1 || status.last}">
		</div>
	</c:if>
	</c:forEach>
</div>

