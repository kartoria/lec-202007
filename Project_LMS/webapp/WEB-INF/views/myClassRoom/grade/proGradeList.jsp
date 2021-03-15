<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />
<div class="container-fluid">
	<div class="row border-bottom border-cyber p-0 my-2">
		<div class="col-9 text-left">
			<h2 class="card-title font-weight-bold">MyClass > 성적</h2>
		</div>
	</div>
	<h5>수강생의 성적을 조회할 수 있습니다.</h5>
	<form:form id="searchForm">
		<div class="row">
			<div class="col-3 m-auto justify-content-center form-inline">
				<div class="btn-group mt-3" data-toggle="buttons" role="group">
					<label class="btn btn-outline btn-light active">
						<div class="custom-control custom-radio">
							<input type="radio" id="customRadio1" name="options" value="male" class="custom-control-input" checked="checked"> <label class="custom-control-label" for="customRadio1"> 전체 </label>
						</div>
					</label> 
					<label class="btn btn-outline btn-light">
						<div class="custom-control custom-radio">
							<input type="radio" id="customRadio2" name="options" value="female" class="custom-control-input"> <label class="custom-control-label" for="customRadio2"> 과제</label>
						</div>
					</label> 
					<label class="btn btn-outline btn-light">
						<div class="custom-control custom-radio">
							<input type="radio" id="customRadio3" name="options" value="n/a" class="custom-control-input"> 
							<label class="custom-control-label" for="customRadio3">시험</label>
						</div>
					</label>
				</div>
				<div class="col-4 m-auto justify-content-center form-inline form-inline">
					<div class="btn-group mt-3" role="group">
						<select class="form-control">
							<option>과제/시험명</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-5 m-auto">
				<!-- ============== 제목 & 검색 필드 ============= -->
				<div id="searchArea" class="row p-5 justify-content-right">
					<div class="col-3 p-0 text-right">
						<select name="searchType" class="form-control h-auto" id="selectSearch">
							<option value="">전체</option>
							<option value="name" ${'title' eq param.searchType?"selected":"" }>학번</option>
							<option value="name" ${'title' eq param.searchType?"selected":"" }>이름</option>
						</select>
					</div>
					<div class="col-6 p-0 text-center">
						<input type="text" name="searchWord" value="${pagingVO.searchVO.searchWord }" class="form-control" id="inputSearch" placeholder="검색">
					</div>
					<div class="col-2 p-0 text-left">
						<button id="searchBtn" type="button" class="btn btn-primary w-100" data-toggle="modal" data-target="#exampleModal">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search form-control-icon">
							<circle cx="11" cy="11" r="8"></circle>
							<line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
						</button>
					</div>
				</div>
			</div>
		</div>
	</form:form>
	<div class="row">
		<div class="col-12">
			<table class="table">
				<thead class="thead-light">
					<tr>
						<th class="font-weight-bold">학번</th>
						<th class="font-weight-bold">이름</th>
						<th class="font-weight-bold">과제1</th>
						<th class="font-weight-bold">과제2</th>
						<th class="font-weight-bold">시험1</th>
						<th class="font-weight-bold">시험2</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>501222</td>
						<td>김선준</td>
						<td>5점</td>
						<td>5점</td>
						<td>100점</td>
						<td>85점</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	
</script>