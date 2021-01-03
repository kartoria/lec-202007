<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<nav class="navbar navbar-expand-md navbar-dark sticky-top bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath }">INDEX</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav me-auto mb-2 mb-md-0">
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath }/member/memberList.do" tabindex="-1" aria-disabled="true">회원관리</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath }/prod/prodList.do" tabindex="-1" aria-disabled="true">상품관리</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath }/buyer/buyerList.do" tabindex="-1" aria-disabled="true">거래처관리</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath }/board/boardList.do"" tabindex="-1" aria-disabled="true">게시판</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#" tabindex="-1" aria-disabled="true">방명록</a>
        </li>
        <li class="nav-item">
          <a class="nav-link timeArea" href="#" tabindex="-1" aria-disabled="true"></a>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/sessionTimer.js"></script>
<script type="text/javascript">
	$(function(){
		$(".timeArea").sessionTimer({
			timeout:<%=session.getMaxInactiveInterval() %>
			, sessionURL:"${pageContext.request.contextPath }/02/getMessage.do"
		});
	});
</script>