<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${empty pageTitle ? "SmartLMS" : pageTitle}</title>
<tiles:insertAttribute name="preScript"/>
</head>
<body>
	<!-- ============================================================== -->
    <!-- Preloader - style you can find in spinners.css -->
    <!-- ============================================================== -->
    <div class="preloader">
        <div class="lds-ripple">
       	     <div class="lds-pos"></div>
            <div class="lds-pos"></div>
        </div>
    </div>
    <!-- ============================================================== -->
    <!-- Main wrapper - style you can find in pages.scss -->
    <!-- ============================================================== -->
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
        data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<tiles:insertAttribute name="topMenu" />
		<tiles:insertAttribute name="leftMenu" />
		<div class="page-wrapper">
			<tiles:insertAttribute name="contents" />
			<tiles:insertAttribute name="footer" />
			 
			<!-- 템플릿 부분
			<div class="container-fluid border border-danger px-3" style="min-height:300px">
				<div class="row my-3">
					<div class="col-12 d-flex justify-content-start align-items-center">
						<h5 class="ml-2"><i class="fas fa-home"></i> 메뉴구조 > 표시하기</h5>
					</div>
				</div>
				
				<div class="row my-3 border-bottom border-cyber">
					<div class="col-12 d-flex justify-content-start align-items-center">
						<h2 class="font-weight-bold">디자인 템플릿</h2>
					</div>
				</div>
				
				<div class="row mt-3 mb-5">
					<div class="col-12 d-flex justify-content-start align-items-center">
						<h5 class="ml-2">여기부분은 이 페이지에대한 기능설명부분이고 ml-2로 왼쪽간격을 조금준다. 모든 row끼리 my-3씩 줘서 수직 간격 넓히고 col에는 align-items-center나 bottom으로 div의 수직 가운데 정렬 혹은 아래 정렬하기</h5>
					</div>
				</div>
				
				<div id="tmpSearchDiv" class="row my-3">
					<div class="col-6 d-flex justify-content-start align-items-center">
						<span>공지사항같은 게시판종류는 검색창이 밑에있는게 좋고 학생조회같은 관리쪽은 검색이 위에있는게 보기좋을것 같다.</span>
					</div>
					<div class="col-6 d-flex justify-content-end align-items-stretch">
						<form id="tmpSearchForm" class="form-inline flex-fill justify-content-end align-items-stretch"> 
							<div class="form-group">
								<select class="form-control w-100 h-100">
									<option>셀렉트박스</option>
									<option>ㅇㅇㅇㅇ</option>
								</select>
							</div>
							<div class="form-group flex-grow-1">
								<input type="text" class="form-control w-100 h-100" value="align-items-stretch는 내 안의 div들의 높이를 100퍼센트로 채우고 flex-grow-1은 div의 너비를 꽉채움" />
							</div>
							<div class="form-group">
								<button type="button" class="btn waves-effect waves-light btn-primary px-4 h-100">
									<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search form-control-icon"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
								</button>
							</div>
						</form>
					</div>
				</div>
				
				<div id="tmpTableDiv" class="row my-3">
					<div class="col-12">
						<table class="table">
							<thead class="thead-light">
								<tr class="text-center text-dark">
									<th class="font-weight-bold">1 가나다라마</th>
									<th class="font-weight-bold">2 ABCDE</th>
									<th class="font-weight-bold">3 abcde</th>
									<th>thead>th 에 font-weight-bold 안주면 글씨가 너무 얇음</th>
								</tr>
							</thead>
							<tbody class="bg-white">
								<tr class="text-center text-dark">
									<td style="width:25%">테이블은</td>
									<td style="width:25%">너비를</td>
									<td style="width:25%">인라인 width에</td>
									<td style="width:25%">퍼센트로 줍시다</td>
								</tr>
								<tr class="text-center text-dark">
									<td class="w-25">table-sm</td>
									<td class="w-25">속성을 주면</td>
									<td class="w-25">세로 너비가 좁아져서</td>
									<td class="w-25">공간확보할때는 좋음</td>
								</tr>
							</tbody>
						</table>				
					</div>
				</div>
				
				<div class="row my-3">
					<div class="col-12 d-flex justify-content-end align-items-center">
						<div class="form-inline">
							<div class="form-group">
								<label id="tmpinsertBtn" class="text-dark">버튼은 form-inline과 form-group을 사용해 일렬로 배치 이 버튼 디자인 그대로 사용</label>
								<button id="tmpinsertBtn" type="button" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-2">등록</button>
							</div>
							<div class="form-group">
								<button type="button" class="btn waves-effect waves-light btn-light px-4 py-2 mx-2">목록</button>
							</div>
							<div class="form-group">
								<button type="button" class="btn waves-effect waves-light btn-warning px-4 py-2 mx-2">수정</button>
							</div>
							<div class="form-group">
								<button type="button" class="btn waves-effect waves-light btn-danger px-4 py-2 mx-2">삭제</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 템플릿 끝 -->
		</div>
		<!-- ============================================================== -->
	    <!-- End Page wrapper  -->
	    <!-- ============================================================== -->
	</div>
    <!-- ============================================================== -->
    <!-- End Wrapper -->
    <!-- ============================================================== -->
    
</body>
</html>









