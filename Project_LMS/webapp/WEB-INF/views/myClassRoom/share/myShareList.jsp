<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container-fluid">
<!-- *************************************************************** -->
<!-- End Top Leader Table -->
<!-- *************************************************************** -->
<div class="card-body">
	    <div class="d-flex align-items-center mb-4">
	        <h3 class="card-title">자료 공유</h3>
	    </div>
	    	<!-- ============== 제목 & 검색 필드 ============= -->
			<!-- ====================================== -->
		<div id="zero_config_wrapper"
			class="dataTables_wrapper container-fluid dt-bootstrap4">
			<div class="row">
				<div class="col-sm-12 col-md-12">
					<div id="zero_config_filter" class="dataTables_filter">
						<div id="searchArea" class="card-body">
							<div class="input-group">
								<select class="form-control" id="selectSearch">
									<option>전체</option>
									<option>제목</option>
									<option>작성자</option>
								</select> <input type="text" class="form-control" id="inputSearch"
									placeholder="검색">
								<button id="excelBtn" type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#exampleModal">
									<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
										viewBox="0 0 24 24" fill="none" stroke="currentColor"
										stroke-width="2" stroke-linecap="round"
										stroke-linejoin="round"
										class="feather feather-search form-control-icon">
										<circle cx="11" cy="11" r="8"></circle>
										<line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- ============== END 검색 필드 ============= -->
		<!-- ====================================== -->
	    <div class="col-12">
             <div class="card">
                 <div class="table-responsive">
                     <table class="table">
                         <thead class="thead-light">
                             <tr>
                                 <th scope="col">글번호</th>
                                 <th scope="col">제목</th>
                                 <th scope="col">작성자</th>
                                 <th scope="col">등록 날짜</th>
                                 <th scope="col">조회수</th>
                             </tr>
                         </thead>
                         <tbody id="listBody">
                             <tr>
                             	<td>1</td>
                                <td>자료 공유합니다</td>
                                 <td>강문정</td>
                                 <td>2020.01.20</td>
                                 <td>123</td>
                             </tr>
                         </tbody>
                     </table>
                 </div>
             </div>
									<!-- ============================================================== -->
			<!--페이징 -->
			<!-- ============================================================== -->
			<div class="col-sm-12 col-md-12">
				<div class="dataTables_paginate paging_simple_numbers"
					id="zero_config_paginate">
					<ul id="pagingArea" class="pagination">
						<li class="paginate_button page-item previous disabled"
							id="zero_config_previous"><a href="#"
							aria-controls="zero_config" data-dt-idx="0" tabindex="0"
							class="page-link">Previous</a></li>
						<li class="paginate_button page-item active"><a href="#"
							aria-controls="zero_config" data-dt-idx="1" tabindex="0"
							class="page-link">1</a></li>
						<li class="paginate_button page-item "><a href="#"
							aria-controls="zero_config" data-dt-idx="2" tabindex="0"
							class="page-link">2</a></li>
						<li class="paginate_button page-item "><a href="#"
							aria-controls="zero_config" data-dt-idx="3" tabindex="0"
							class="page-link">3</a></li>
						<li class="paginate_button page-item "><a href="#"
							aria-controls="zero_config" data-dt-idx="4" tabindex="0"
							class="page-link">4</a></li>
						<li class="paginate_button page-item "><a href="#"
							aria-controls="zero_config" data-dt-idx="5" tabindex="0"
							class="page-link">5</a></li>
						<li class="paginate_button page-item "><a href="#"
							aria-controls="zero_config" data-dt-idx="6" tabindex="0"
							class="page-link">6</a></li>
						<li class="paginate_button page-item next" id="zero_config_next"><a
							href="#" aria-controls="zero_config" data-dt-idx="7" tabindex="0"
							class="page-link">Next</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- =========== 버튼 입력 부분 ============= -->
		<!-- =========== 버튼 입력 부분 ============= -->
		<div id="buttomDiv">
			<a class="btn waves-effect waves-light btn-rounded btn-info"
				href="${cPath}/myclass/shareInsert.do" role="button"
				id="insertBtn">등록</a>
		</div>
		<!-- ==============END 버튼 ============== -->
		<!-- ================================== -->
	</div>
</div>
