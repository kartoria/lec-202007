<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/DataTables/datatables.min.css"></link>
<script src="<%=request.getContextPath() %>/js/DataTables/datatables.min.js"></script>


<jsp:useBean id="commend" type="java.lang.String" scope="request" />
<jsp:useBean id="member" class="kr.or.ddit.vo.MemberVO" scope="request" />
<jsp:useBean id="errors" class="java.util.LinkedHashMap" scope="request" />


<form method="post" id="memberForm">
	<table class="table">
		<tr>
			<th>아이디</th>
			<td>
				<div class="input-group">
				<input type="text" class="form-control" required name="mem_id" placeholder="아이디를 입력해주세요"/>
				<button id="idCheckBtn" type="button" class="btn btn-primary">아이디중복확인</button>
				</div>	
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="text" value="Abc001" class="form-control" name="mem_pass"/>
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<input type="text" value="포치타" class="form-control" required name="mem_name" />
			</td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td>
				<input type="text" value="961223" class="form-control" required name="mem_regno1" size="6" />
			</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>
				<input type="text" value="1400000" class="form-control" required name="mem_regno2" size="7"/>
			</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>
				<input type="date" value="1996-12-23" class="form-control" name="mem_bir"/>
			</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>
				<div class="input-group">
				<input type="text" class="form-control" required readonly name="mem_zip" value="" id="mem_zip"/>
				<button id="zipBtn" type="button" class="btn btn-primary" data-bs-toggle="modal" 
							data-bs-target="#zipModal">우편번호 검색</button>
				</div>			
			</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>
				<input type="text" class="form-control" required readonly name="mem_add1" id="mem_add1"/>
			</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>
				<input type="text" class="form-control" required readonly name="mem_add2" id="mem_add2"/>
			</td>
		</tr>
		<tr>
			<th>집전번</th>
			<td>
				<input type="text" value="042-369-4871" class="form-control" required name="mem_hometel" />
			</td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td>
				<input type="text" value="022-142-1022" class="form-control" required name="mem_comtel" />
			</td>
		</tr>
		<tr>
			<th>메일</th>
			<td>
				<input type="text" value="gavethesun@gmail.com" class="form-control" required name="mem_mail"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" class="btn btn-primary" value="저장" />
				<input type="reset" class="btn btn-secondary" value="취소" />
			</td>
		</tr>
	</table>
</form>
<div class="modal fade" id="zipModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="staticBackdropLabel">우편번호 검색</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<table id="zipTable">
					<thead>
						<tr>
							<th>우편번호</th>
							<th>주소</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="input-group input-group-sm mb-3">
				  <span class="input-group-text" id="inputGroup-sizing-sm">우편번호</span>
				  <input readonly id="modalZipCode" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" />
				</div>
				<div class="input-group input-group-sm mb-3">
				  <span class="input-group-text" id="inputGroup-sizing-sm">주소1</span>
				  <input readonly id="address1" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" />
				</div>
				<div class="input-group input-group-sm mb-3">
				  <span class="input-group-text" id="inputGroup-sizing-sm">주소2</span>
				  <input id="address2" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" />
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" id="modalCloseBtn">주소입력</button>
			</div>
		</div>
	</div>	
</div>

<script type="text/javascript">
	$(function(){
		const IDCHECKED = "idChecked";
		let commend = "<%=commend%>";
		if(commend == "MODIFY"){
			$("input[name='mem_id']").val("<%=member.getMem_id()%>").attr("readonly", true);	
		}else{
			let mem_idTag = $("[name='mem_id']").on("blur", function(){
				idCheckBtn.trigger("click");
			}).on("keydown", function(){
				$(this).tooltip("hide");
			}).tooltip({
				title: "이미 사용중인 아이디입니다. 다른 아이디로 바꿔주세요."
				, placement: "bottom"
				, trigger: "manual"
			}).focus();
			
			let idCheckBtn = $("#idCheckBtn").on("click", function(){
				if(!mem_idTag.val()){
					mem_idTag.focus();
					return false;
				}
				$.ajax({
					url: "<%=request.getContextPath() %>/member/idCheck.do",
					data:{
						inputId : mem_idTag.val()
					},
					method:"post",
					dataType:"text",
					success:function(resp){
						if("true"==resp.trim()){
							console.log("아이디 가능");
							mem_idTag.tooltip("hide");
							mem_idTag.data(IDCHECKED, true);
						}else{
							console.log("아이디 불가능");
							mem_idTag.data(IDCHECKED, false);
							mem_idTag.tooltip("show");
							mem_idTag.focus();
							mem_idTag.select();
						}
					},
					error:function(xhr){
						console.log(xhr);
					}
				});
			});
			
			$("#memberForm").on("submit", function(){
				if(mem_idTag.data(IDCHECKED)===true){
					return true;	
				}else{
					alert("<%=errors.toString() %>");
					mem_idTag.select();
					mem_idTag.focus();
					return false;
				}
			});
		}
		
		
	
		
		let zipTable = $('#zipTable').DataTable({
		    ajax: {
		        url: '<%=request.getContextPath() %>/commons/searchZip.do',
		        dataSrc: 'zipList'
		    },
		    columns: [ 
		    	{ data: 'zipcode' }
		        , { data: 'address' }
			],
			select: {
				style: "single"
			},
			lengthChange: false
		}).on( 'select', function ( e, dt, type, indexes ) {
			let zipVO = dt.data();
			console.log(zipVO);
			modalZipCode.value = zipVO.zipcode;
			address1.value = zipVO.address;
		});
		
		$("#modalCloseBtn").on("click", function(){
			if(modalZipCode.value && address1.value && address2.value){
				mem_zip.value  = modalZipCode.value;
				mem_add1.value = address1.value;
				mem_add2.value = address2.value;
				$("#zipModal").modal("hide");
			}
			else $(address2).focus();
		});
		
	});
</script>