<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<table class="table">
	<tr>
		<th class="text-center">제목</th>
		<td class="pb-1">${board.bo_title }</td>
	</tr>
	<tr>
		<th class="text-center">작성자</th>
		<td class="pb-1">${board.bo_writer }[${board.bo_mail }][${board.bo_ip }]</td>
	</tr>
	<tr>
		<th class="text-center">작성일</th>
		<td class="pb-1">${board.bo_date }</td>
	</tr>
	<tr>
		<th class="text-center">조회수</th>
		<td class="pb-1">${board.bo_hit }</td>
	</tr>
	<tr>
		<th class="text-center">추천수</th>		
		<td class="pb-1">
			<span id="recArea">${board.bo_rec }</span>
			<c:if test="${not fn:contains(cookie.boardCookie.value,board.bo_no) }">
				<span class="btn btn-secondary ml-2" id="recBtn" data-bo-no="${board.bo_no }">추천</span>
			</c:if>
		</td>
	</tr>
	<tr>
		<th class="text-center">첨부파일</th>
		<td class="pb-1">
			<c:if test="${not empty board.attatchList }">
				<c:forEach items="${board.attatchList }" var="attatch" varStatus="vs">
					<c:url value="/board/download.do" var="downloadURL">
						<c:param name="what" value="${attatch.att_no }" />
					</c:url>
					<a href="${downloadURL }">
						<span title="다운로드:${attatch.att_downcount }">${attatch.att_filename }</span>
						${not vs.last?"|":"" }
					</a>
				</c:forEach>		
			</c:if>
		</td>
	</tr>
	<tr>
		<th class="text-center">내용</th>
		<td class="pb-1">${board.bo_content }</td>
	</tr>
	<tr>
		<td colspan="2" class="text-center">
			<c:url value="/board/boardUpdate.do" var="updateURL">
				<c:param name="what" value="${board.bo_no }" />
			</c:url>
			<a class="btn btn-primary" href="${updateURL }">수정</a>
			<input type="button" value="삭제" class="btn btn-warning" id="removeBtn" />
			<input type="button" value="답글쓰기" class="btn btn-danger" id="answerBtn" data-bo-no="${board.bo_no }"/>
			<a class="btn btn-primary" href="${cPath }/board/boardList.do">목록으로</a>
		</td>
	</tr>
</table>
<form id="boardDeleteForm" method="post" action="${cPath }/board/boardDelete.do">
	<input type="hidden" name="what" value="${board.bo_no }" />
	<input type="hidden" name="bo_pass" />
</form>
<form method="post" class="form-inline" id="replyInsertForm"
	action="${pageContext.request.contextPath }/reply">
	<input type="hidden" name="rep_no" />
	<input type="hidden" name="bo_no" value="${board.bo_no }"/>
	<table class="col-md-10">
		<tr>
			<td>
				<input type="text" class="form-control mb-2" name="rep_writer" placeholder="작성자" maxlength="15"/>
			</td>
			<td>
				<input type="password" class="form-control mb-2" name="rep_pass" placeholder="비밀번호"/>
			</td>
			<td>
				<input type="text" class="form-control mb-2" readonly name="rep_ip" value="${pageContext.request.remoteAddr }" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="input-group">
				<textarea class="form-control mb-2 mr-2" rows="2" placeholder="내용 200자 이내"maxlength="200" name="rep_content"></textarea>
				</div>
			</td>
			<td colspan="3">
				<input type="submit" value="전송" class="btn btn-primary" />
			</td>
		</tr>
	</table>
</form>
<h4>댓글리스트 (비동기)</h4>
<table id="replyTable" class="table table-bordered">
	<thead class="table-dark">
		<tr>	
			<th class="text-center">내용</th>
			<th class="text-center">작성자</th>
			<th class="text-center">작성일</th>
			<th class="text-center">&nbsp;</th>
		</tr>
	</thead>
	<tbody id="listBody">
	
	</tbody>
</table>
<div id="pagingArea"></div>
<form id="searchForm" action="${pageContext.request.contextPath }/reply">
	<input type="hidden" name="what" value="${board.bo_no }" />
	<input type="hidden" name="page"  />
</form>
<div class="modal fade" id="replyModal" tabindex="-1" aria-labelledby="replyModalLabel" aria-hidden="true">
 <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="replyModalLabel">댓글 수정</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="${pageContext.request.contextPath }/reply" method="post">
      	<input type="hidden" name="_method" value="put">
      	<input type="hidden" name="rep_no" required/>
      	<input type="hidden" name="bo_no"  required value="${board.bo_no }"/>
	      <div class="modal-body">
	      	<table class="table form-inline">
	      		<tr>
	      			<td>
	      				<input type="text" required name="rep_writer" class="form-control" placeholder="작성자" />
	      			</td>
	      			<td>
	      				<input type="password" required name="rep_pass" class="form-control" placeholder="비밀번호"/>
	      			</td>
	      		</tr>
	      		<tr>
	      			<td colspan="2">
						<div class="input-group">
						<textarea class="form-control mb-2 mr-2" rows="2" placeholder="내용 200자 이내"maxlength="200" name="rep_content"></textarea>
						</div>
					</td>
	      		</tr>
	      	</table>
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-primary">수정</button>
	        <button type="reset" class="btn btn-warning" data-bs-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	      </div>
      </form>
    </div>
  </div>
</div>

<form id="replyDeleteForm" action="${pageContext.request.contextPath }/reply" method="post">
	<input type="hidden" name="_method" value="delete">
	<input type="hidden" name="rep_no" required/>
   	<input type="hidden" name="bo_no"  required value="${board.bo_no }"/>
   	<input type="hidden" name="rep_pass"  required/>
</form>	
<script type="text/javascript" src="${cPath }/js/board/boardView.js"></script>
<script type="text/javascript" src="${cPath }/js/board/reply.js"></script>
















		
