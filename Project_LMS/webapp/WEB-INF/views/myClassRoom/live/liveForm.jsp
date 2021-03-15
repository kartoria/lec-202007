<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i>마이클래스 > 실시간 강의</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">실시간 강의 등록</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">Youtube 방송을 등록하고 외부 프로그램을 이용하여 강의를 시작할 수 있습니다.</h5>
		</div>
	</div>
	<div id="tmpTableDiv" class="row my-3">
		<div class="col-12">
			<form:form commandName="liveVO" id="createLiveForm" action="${cPath}/myclass/${lecCode}/broadcastInsert.do">
				<form:hidden path="lecCode"/>
				<form:hidden path="lecDays"/>
				<div class="form-group">
					<form:label path="liveWeek" cssClass="required">주차</form:label>
					<form:select path="liveWeek" cssClass="form-control">
						<form:option value="">주차를 선택하세요</form:option>
						<c:forEach begin="1" step="1" end="${liveVO.lecDays}" var="index">
							<form:option value="${index}">${index}주차</form:option>
						</c:forEach>
					</form:select>
					<form:errors path="liveWeek" element="span" cssClass="text-danger"/>
				</div>
				
				<div class="form-group">
					<form:label path="broadcastTitle" cssClass="required">제목</form:label>
					<form:input path="broadcastTitle" type="text" cssClass="form-control"/>
					<form:errors path="broadcastTitle" element="span" cssClass="text-danger"/>
				</div>
				
				<div class="form-group">
					<form:label path="broadcastDescription">설명</form:label>
					<form:input path="broadcastDescription" cssClass="form-control"/>
				</div>
				
				<div class="form-group">
					<form:label path="privacyStatus" cssClass="required">공개 설정</form:label>
					<form:select path="privacyStatus" cssClass="form-control">
						<form:option value="public">공개</form:option>
						<form:option value="unlisted">일부공개</form:option>
						<form:option value="private">비공개</form:option>
					</form:select>
					<form:errors path="privacyStatus" element="span" cssClass="text-danger"/>
				</div>
				
				<div class="form-group">
					<form:label path="frameRate" cssClass="required">프레임</form:label>
					<form:select path="frameRate" cssClass="form-control">
						<form:option value="30fps"/>
						<form:option value="60fps"/>
					</form:select>
					<form:errors path="frameRate" element="span" cssClass="text-danger"/>
				</div>
				
				<div class="form-group">
					<form:label path="resolution" cssClass="required">화질</form:label>
					<form:select path="resolution" cssClass="form-control">
						<form:option value="480p"/>
						<form:option value="720p"/>
						<form:option value="1080p"/>
					</form:select>
					<form:errors path="resolution" element="span" cssClass="text-danger"/>
				</div>
				<div class="row">
					<div class="col-12 d-flex justify-content-end align-items-start pr-0">
						<h5 class="m-0"><span class='text-danger mr-2'>*</span>는 필수 입력사항입니다.</h5>
					</div>
				</div>
				<div class="row">
					<div class="col-12 d-flex justify-content-center align-items-center">
						<input type="submit" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2" value="등록"/>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>


