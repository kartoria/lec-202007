<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<c:set var="qnaList" value="${pagingVO.dataList }"/>

<c:choose>
	<c:when test="${not empty qnaList}">
		<c:forEach items="${qnaList }" var="qna">
				<c:when test="${qna.boDelete eq 'N'}">
				<tr>
					<td>${qna.rnum }</td>
					<c:url var="viewURL" value="/cyber/qna/${qna.boNo}/view.do"/>		
					<td>
					<c:choose>
                       <c:when test="${qna.boSecret eq 'N' }">
                          <c:choose>
                             <i class="fas fa-lock">
                              <a onclick="checkPassword(${qna.boNo}, ${qna.memId })">
                                 <span>비밀글
                                 </span>
                          </c:choose>
                       </c:when>
                    </c:choose>
                    </td>
                     <td>${qna.memName }</td>
                     <td>${qna.boDate }</td>
                     <td>${qna.boHit }</td>
				</tr>		
				</c:when>
		</c:forEach>
	</c:when>
	<c:when test="${not empty qnaList && ${qna.boDelete eq 'Y'}">
		<c:forEach items="${qnaList }" var="qna">
				<tr>
					<td>${qna.rnum }</td>
					<c:url var="viewURL" value="/cyber/qna/${qna.boNo}/view.do"/>		
					<td>
					<c:choose>
                       <c:when test="${qna.boSecret eq 'N' }">
                          <c:choose>
                             <c:when test="${qna.boDelete eq 'N'}">
								<a href="${cPath}/cyber/qna/${qna.boNo}/view.do">${qna.boTitle }</a>
							 </c:when>
                          </c:choose>
                       </c:when>
                    </c:choose>
                    </td>
                     <td>${qna.memName }</td>
                     <td>${qna.boDate }</td>
                     <td>${qna.boHit }</td>
				</tr>		
		</c:forEach>
	</c:when>
</c:choose>

















</body>
</html>