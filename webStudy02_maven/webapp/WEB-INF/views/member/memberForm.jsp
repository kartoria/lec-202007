<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/DataTables/datatables.min.css"> 
<script src="<%=request.getContextPath() %>/js/DataTables/datatables.min.js"></script>
<script src="<%=request.getContextPath() %>/js/commons/searchZip.js"></script>

<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request"/>
<jsp:useBean id="errors" class="java.util.LinkedHashMap" scope="request"/>
<form method="post" id="memberForm">
	<table class="table table-responsive">
		<tr class="mb-1">
			<th class="text-center">아이디</th>
			<td class="pb-1">
				<div class="input-group">
					<input type="text" class="form-control" required name="mem_id" 
						value="<%=Objects.toString(member.getMem_id(), "") %>" placeholder="아이디 기록하기 전에는 빠져나갈수 없숴~"/>
					<button id="idCheckBtn" type="button" class="btn btn-primary insertOnly">아이디중복확인</button>
					<span class="error"><%=errors.get("mem_id") %></span>
				</div>
			</td>
		</tr>
		<tr>
			<th class="text-center">비밀번호</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" name="mem_pass" required
					pattern="^(?=.*[0-9]+)(?=.*[a-z]+)(?=.*[A-Z]+).{5,12}$"
				/>
				<span class="error"><%=errors.get("mem_pass") %></span>		 
				</td>
		</tr>
		<tr>
			<th class="text-center">이름</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" required name="mem_name" value="<%=Objects.toString(member.getMem_name(), "") %>" />
				<span class="error"><%=errors.get("mem_name") %></span>
				</td>
		</tr>
		<tr>
			<th class="text-center">주민번호1</th>
			<td class="pb-1">
				<input type="text" class="form-control" required name="mem_regno1" value="<%=Objects.toString(member.getMem_regno1(), "") %>" pattern="\d{6}" maxlength="6"/>
				<span class="error"><%=errors.get("mem_regno1") %></span>
				</td>
		</tr>
		<tr>
			<th class="text-center">주민번호2</th>
			<td class="pb-1">
				<input type="text" class="form-control" required name="mem_regno2" value="<%=Objects.toString(member.getMem_regno2(), "") %>" size="7"/>
				<span class="error"><%=errors.get("mem_regno2") %></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">생일</th>
			<td class="pb-1">
				<input type="date" class="form-control" name="mem_bir" value="<%=Objects.toString(member.getMem_bir(), "") %>" />
				<span class="error"><%=errors.get("mem_bir") %></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">우편번호</th>
			<td class="pb-1">
				<div class="input-group">
				<input type="text" class="form-control editable" required readonly name="mem_zip" value="<%=Objects.toString(member.getMem_zip(), "") %>" id="mem_zip" tabindex="-1"/>
				<button id="zipBtn" type="button" class="btn btn-primary" data-bs-toggle="modal" 
							data-bs-target="#zipModal">우편번호 검색</button>
				<span class="error"><%=errors.get("mem_zip") %></span>
				</div>			
			</td>
		</tr>
		<tr>
			<th class="text-center">주소1</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" required readonly name="mem_add1" value="<%=Objects.toString(member.getMem_add1(), "") %>" id="mem_add1" tabindex="-1"/>
				<span class="error"><%=errors.get("mem_add1") %></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">주소2</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" required readonly name="mem_add2" value="<%=Objects.toString(member.getMem_add2(), "") %>" id="mem_add2" tabindex="-1"/>
				<span class="error"><%=errors.get("mem_add2") %></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">집전번</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" required name="mem_hometel" value="<%=Objects.toString(member.getMem_hometel(), "") %>" />
				<span class="error"><%=errors.get("mem_hometel") %></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">회사전번</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" required name="mem_comtel" value="<%=Objects.toString(member.getMem_comtel(), "") %>" />
				<span class="error"><%=errors.get("mem_comtel") %></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">휴대폰</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" name="mem_hp" value="<%=Objects.toString(member.getMem_hp(), "") %>" />
				<span class="error"><%=errors.get("mem_hp") %></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">메일</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" required name="mem_mail" value="<%=Objects.toString(member.getMem_mail(), "") %>" />
				<span class="error"><%=errors.get("mem_mail") %></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">직업</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" name="mem_job" value="<%=Objects.toString(member.getMem_job(), "") %>" />
				<span class="error"><%=errors.get("mem_job") %></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">취미</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" name="mem_like" value="<%=Objects.toString(member.getMem_like(), "") %>" />
				<span class="error"><%=errors.get("mem_like") %></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">기념일</th>
			<td class="pb-1">
				<input type="text" class="form-control editable" name="mem_memorial" value="<%=Objects.toString(member.getMem_memorial(), "") %>" />
				<span class="error"><%=errors.get("mem_memorial") %></span>
			</td>
		</tr>
		<tr>
			<th class="text-center">기념일자</th>
			<td class="pb-1">
				<input type="date" class="form-control editable" name="mem_memorialday" value="<%=Objects.toString(member.getMem_memorialday(), "") %>" />
			<span class="error"><%=errors.get("mem_memorialday") %></span>
				</td>
		</tr>
		<tr>
			<th class="text-center">마일리지</th>
			<td class="pb-1">3000</td>
		</tr>
		<tr>
			<td colspan="2" class="text-center pt-2">
				<input type="submit" class="btn btn-primary ml-5" value="저장" />
				<input type="reset" class="btn btn-secondary" value="취소" />
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript">
	const language = "de";
	$.getScript($.getContextPath()+`/js/jquery-validation-1.19.2/localization/messages_\${language}.min.js`);
	
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
		const EDITABLE = <%="MODIFY".equals(request.getAttribute("command")) %>;
		if(EDITABLE){
//========입력제한 UI 설정==============================================================
			$("#memberForm input:not(.editable)").prop("readonly", true);
			$("button.insertOnly").hide().prop("disable", true);
//==================================================================================
		}else{
//========아이디 중복 체크===============================================================
			validateOptions.rules={
				mem_id : {
					remote : '<%=request.getContextPath() %>/member/idCheck.do'
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
	
	
	
	