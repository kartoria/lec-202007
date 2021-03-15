<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<aside class="left-sidebar" data-sidebarbg="skin6">
	<div class="scroll-sidebar" data-sidebarbg="skin6">
		<c:if test="${not empty lecCode}">
			<div class="col-12 mt-5">
				<select id="lectureSelectInLeftMenu" class="form-control">
					<c:forEach items="${myLectureList}" var="lecture">
						<option value="${lecture.lecCode}" ${lecCode eq lecture.lecCode ? "selected" : ""}>${lecture.subName} ${not empty lecture.memName ? " - ".concat(lecture.memName) : ""}</option>
					</c:forEach>
				</select>
			</div>
		</c:if>
		<nav class="sidebar-nav">
			<ul id="sidebarnav">
				<li class="nav-small-cap"><span class="hide-menu">MENU</span></li> 
				<c:forEach items="${perentMenuList}" var="perentMenu">
					<c:choose>
						<c:when test="${perentMenu.childCount eq 0}">
							<li class="sidebar-item">
								<a class="sidebar-link hover-border-radius" href="<c:url value='${perentMenu.menuPath}'/>" aria-expanded="false">
									<i data-feather="${perentMenu.menuIcon}" class="feather-icon"></i>
									<span class="hide-menu">${perentMenu.menuText}</span>
								</a>
							</li>	
						</c:when>
						<c:otherwise>
							<li class="sidebar-item">
								<a class="sidebar-link has-arrow active" href="javascript:void(0)" aria-expanded="false">
									<i data-feather="${perentMenu.menuIcon}" class="feather-icon"></i>
									<span class="hide-menu">${perentMenu.menuText}</span>
								</a>
								<ul aria-expanded="false" class="collapse first-level base-level-line in">
									<c:forEach items="${childMenuList}" var="childMenu">
										<c:if test="${childMenu.menuPerent eq perentMenu.menuNo}">
											<li class="sidebar-item"><a href="<c:url value='${childMenu.menuPath}'/>" class="sidebar-link hover-border"><span class="hide-menu">${childMenu.menuText}</span></a></li>
										</c:if>
									</c:forEach>
								</ul>
							</li>	
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${not empty lecCode}">
				<li class="list-divider"></li>
				<li class="sidebar-item"> <a class="sidebar-link sidebar-link hover-border" href="${cPath}/cyber/myclass/list.do" aria-expanded="false"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-log-out feather-icon"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path><polyline points="16 17 21 12 16 7"></polyline><line x1="21" y1="12" x2="9" y2="12"></line></svg><span class="hide-menu">강의실 나가기</span></a></li>
				</c:if>
			</ul>
		</nav>
	</div>
</aside>
