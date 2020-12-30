<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
 
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/DataTables/datatables.min.css"> 
<script src="${pageContext.request.contextPath }/js/DataTables/datatables.min.js"></script>


<form method="post" id="memberForm" enctype="multipart/form-data">
	<table class="table table-responsive">
		<tr class="mb-1">
			<th class="text-center">아이디</th>
			<td class="pb-1">
				<div class="input-group">
					<input type="text" class="form-control" required name="mem_id" 
						value="${member.mem_id }" placeholder="아이디 입력하시오"/>
					<button id="idCheckBtn" type="button" class="btn btn-primary insertOnly">아이디중복확인</button>
					<span class="error">${errors.mem_id } </span>
				</div>
			</td>
		</tr>
		<tr>
			<th class="text-center">비밀번호</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" name="mem_pass" required
					pattern="^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+).{5,12}$"
				/>
				<span class="error">${errors.mem_pass}</span>		 
				</td>
		</tr>
		<tr>
			<th class="text-center">이름</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" required name="mem_name" value="${member.mem_name }" />
				<span class="error">${errors.mem_name}</span>
				</td>
		</tr>
		<tr>
			<th class="text-center">주민번호1</th>
			<td class="pb-1">
				<input type="text" class="form-control" required name="mem_regno1" value="${member.mem_regno1 }" pattern="\d{6}" maxlength="6"/>
				<span class="error">${errors.mem_regno1 }</span>
				</td>
		</tr>
		<tr>
			<th class="text-center">주민번호2</th>
			<td class="pb-1">
				<input type="text" class="form-control" required name="mem_regno2" value="${member.mem_regno2 }" size="7"/>
				<span class="error">${errors.mem_regno2 }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">생일</th>
			<td class="pb-1">
				<input type="date" class="form-control" name="mem_bir" value="${member.mem_bir }" />
				<span class="error">${errors.mem_bir}</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">우편번호</th>
			<td class="pb-1">
				<div class="input-group">
				<input type="text" class="form-control editable" required readonly name="mem_zip" value="${member.mem_zip }" id="mem_zip" tabindex="-1"/>
				<button id="zipBtn" type="button" class="btn btn-primary" data-bs-toggle="modal" 
							data-bs-target="#zipModal">우편번호 검색</button>
				<span class="error">${errors.mem_zip}</span>
				</div>			
			</td>
		</tr>
		<tr>
			<th class="text-center">주소1</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" required readonly name="mem_add1" value="${member.mem_add1 }" id="mem_add1" tabindex="-1"/>
				<span class="error">${errors.mem_add1 }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">주소2</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" required readonly name="mem_add2" value="${member.mem_add2 }" id="mem_add2" tabindex="-1"/>
				<span class="error">${errors.mem_add2 }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">집전번</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" required name="mem_hometel" value="${member.mem_hometel }" />
				<span class="error">${errors.mem_hometel }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">회사전번</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" required name="mem_comtel" value="${member.mem_comtel }" />
				<span class="error">${errors.mem_comtel }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">휴대폰</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" name="mem_hp" value="${member.mem_hp }" />
				<span class="error">${errors.mem_hp }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">메일</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" required name="mem_mail" value="${member.mem_mail }" />
				<span class="error">${errors.mem_mail }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">직업</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" name="mem_job" value="${member.mem_job }" />
				<span class="error">${errors.mem_job }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">취미</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" name="mem_like" value="${member.mem_like }" />
				<span class="error">${errors.mem_like }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">기념일</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" name="mem_memorial" value="${member.mem_memorial }" />
				<span class="error">${errors.mem_memorial }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">기념일자</th>
			<td class="pb-1">
				<input type="date" class="form-control editable" name="mem_memorialday" value="${member.mem_memorialday }" />
				<span class="error">${errors.mem_memorialday }</span>
			</td>
		</tr>
		<tr>
			<th class="text-center">마일리지</th>
			<td class="pb-1">3000</td>
		</tr>
		<tr>
			<th class="text-center">프로필사진</th>
			<td class="pb-1">
				<input type="file" class="form-control editable" name="mem_image" />
				<span class="error">${errors.mem_img }</span>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="text-center pt-2">
				<input type="submit" class="btn btn-primary ml-5" value="저장" />
				<input type="reset" class="btn btn-secondary" value="취소" />
			</td>
		</tr>
	</table>
</form>
<script src="${pageContext.request.contextPath }/js/commons/searchZip.js"></script>
<script type="text/javascript">
	$(function(){
		const validateOptions = {
			onsubmit:true,
			onfocusout:function(element, event){
				return this.element(element);
			},
			errorPlacement: function(error, element) {
				error.appendTo( $(element).parents("td:first") );
		  	}
		}
		const EDITABLE = ${"MODIFY" eq command};
		if(EDITABLE){
//========입력제한 UI 설정==============================================================
			$("#memberForm input:not(.editable)").prop("readonly", true);
			$("button.insertOnly").hide().prop("disable", true);
//==================================================================================
		}else{
//========아이디 중복 체크===============================================================
			validateOptions.rules={
				mem_id : {
					remote : '${pageContext.request.contextPath }/member/idCheck.do'
				}
			}
			validateOptions.messages={
				mem_id :{
					remote : '아이디 중복'
				}				
			}
			$("#idCheckBtn").click("on", function(){
				$("[name='mem_id']").trigger("blur");
			});
//==================================================================================
		}	
		let validator = $("#memberForm").validate(validateOptions);
		
//========우편번호 검색=================================================================
		$.searchZip({
			zipCodeTag:$("#mem_zip").get(0),
			add1Tag:$("#mem_add1").get(0),
			add2Tag:$("#mem_add2").get(0)
		});
//==================================================================================
	});
	
</script>
	
	
	
	