<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container-fluid">
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-12 text-left">
			<h5>마이클래스 > 실시간 강의 > 방송 개설완료</h5>
			<h3 class="card-title font-weight-bold">실시간 강의가 등록되었습니다.</h3>
		</div>
	</div>
	<div class="row mt-4">
		<div class="form-group col-12">
			<p>스트림 키를 외부 프로그램에 등록해서 방송을 시작하세요.</p>
			<label for="streamKey">스트림 키 : </label> 
			<input id="streamKey" type="text" value="${sessionScope.streamKey}" readonly="readonly" class="form-control"/>
		</div>
	</div>
</div>
			
			
			


