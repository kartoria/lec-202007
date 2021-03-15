<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<style>
#selectBox{
	width: 80px;
}
#studentTable tr:hover{background-color: silver;}
#inputName{width: 120px;}
.mainTable input{border: 0px;}
.mainTable textArea{border: 0px;}
.fixedHeader {
	position: sticky;
	top: 0;
}
.stuListDiv{width:400px;height:600px;overflow: auto}
.applypaper{width: 100%;height:680px;overflow: auto;font-size: 12pt;border: 1px solid black;padding: 10px;}
.applyform{color:black;}
.mainTable{color: black;}
.mainTable, .mainTable td, .mainTable th{border: 1px solid darkgray;}
.bottomText{font-size:25pt; text-align:center;}
.bottomBody{height: 200px;}
</style>  

<div class="container-fluid">
<div class="row">
	<div class="col-12">
		<h5 class="ml-2">
			<i class="fas fa-home"></i> 장학 관리 > 교내 장학생 추천 > 추천서 작성
		</h5>
	</div>
</div>
	<div class="row">
		<div class="col-4">
           
		 <!-- 서버로 전송 -->
         <form id="searchForm">
             <input type="hidden" name="searchType"  value="이름"/>
          <input type="hidden" name="searchWord" />
         </form>
	    <div class="card">
<!-- 	       <h3 class="pl-5">학과 학생 조회</h3> -->
		  <!-- 클라이언트 입력 -->
		  <div class="row mb-3 mt-3" id="inputUI">
		    <div class="col-12">
		       <div class="float-left">
		           <select name="searchType" id="selectBox" class="form-control font-weight-normal">
		              <option value>전체</option> <!-- whitespace가 value로 넘어갈 것 -->
		              <option value="name"
		              ${'name' eq param.searchType?"selected":"" }>이름</option>
		              <option value="stuCode"
		              ${'stuCode' eq param.searchType?"selected":"" }>학번</option>
		           </select>
	           </div>
	           <div class="float-left">
	           <input type="text" id="inputName"  onkeypress="enterkey();"  class="form-control font-weight-normal" name="searchWord" > 
			   </div>
			   <div class="float-left">
			   <button type="button" id="searchBtn" class="btn waves-effect waves-light btn-primary px-3 h-100">
									<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search form-control-icon">
									<circle cx="11" cy="11" r="8"></circle>
									<line x1="21" y1="21" x2="16.65" y2="16.65"></line>
									</svg>
								</button>
	     	   </div>
	     	</div>
	     </div>
	     <p class="text-primary ml-2">*대상 학생을 두번 클릭시 학번이 자동 입력됩니다.</p>      	 
	     <div class="row mt-3">
	       	<div class="stuListDiv col-12">
	          	<table id="studentTable" class="table table-bordered table-striped text-center mb-0">
	          		<thead class="thead-light">
	            	<tr>
	            		<th class="fixedHeader font-weight-bold">학번</th>
	            		<th class="fixedHeader font-weight-bold">이름</th>
	            	</tr>	
		           	</thead>
		           	<tbody id="listBody">
		            </tbody>	
	          	</table>
	       	</div>
	    </div>  
	    </div> 	
	</div> 
       <div class="col-8">
        <security:authorize access="isAuthenticated()">
			<security:authentication property="principal" var="principal" />
			<c:set var="authMember" value="${principal.realMember }" />
		</security:authorize>
       	 <div class="card ">
	       	<form:form method="post" commandName="schVO" id="schForm" action="${cPath}/lms/professor/scholarship/recommend.do">
            	<input type="hidden" name="codeResult" value="APPLY">
            	<input type="hidden" name="sfundProfessor" value="${authMember.memId }">
				<div class="applyform container" >
				   <div class="applypaper card">
				     <div class="card-img-top">
				           <div class="container">
				              <div class="mt-5 ml-15">
				                 <div class="align-middle">
				                  <div class="text-center">            
				                        <p class="ml-3 text-center display-4">교내 장학생 추천서</p>  
				                  </div>
				                 </div>
				              </div>
				         </div>     
				     </div>
				     <hr>
				     <div class="card-body">
				          <table class="mainTable table table-bordered text-center">
				              <tr>
				                <td><span class="text-danger">* </span>학번</td>
				                <td><input type="text" name="sfundStudent" class="form-control" value="${schVO.sfundStudent}" required></td>
				                <td>이름</td>
				                <td><input type="text" name="memName" class="form-control" value="${schVO.memName}" readonly></td>
				              </tr>
				              <tr>
				                <td><span class="text-danger">* </span>장학유형</td>
				                <td colspan="3">
          								<form:select path="scharCode" id="scharCode" class="form-control w-25" required="required">
										<option value="">선택</option>
									</form:select>
									<form:errors path="scharCode" element="span" cssClass="error"/> 
				                </td>
				              </tr>
				              <tr>
				                 <td colspan='6'><span class="text-danger">*</span>신청사유(구체적으로 작성)</td>
				              </tr>
				              <tr>
				                 <td colspan='6'>
				                 <textarea rows="10" cols="90" name="sfundReason" required>위 학생은 본 학과에 재학중인 학생으로서 평소 학과 활동에 대한 열의가 대단히 커 여러 학과 홍보대사 활동 및 봉사활동에 적극적으로 임합니다. 또한 품행이 단정하고 학과의 궂은일도 마다하지 않아 솔선수범 하는 모습이 항상 선,후배 및 동기들의 귀감이 되기에 본인은 참된 인성과 우수한 학업능력을 가진 강문정 학생을 적극 추천하는 바입니다.       
				                 </textarea>
<%-- 				                 <form:textarea class="form-control" path="sfundReason" cols="155" rows="10" required="required"></form:textarea> --%>
				                 </td>
				              </tr>
				         </table>
				           <div>
				              <div class="bottomBody mt-2 text-center">
				                 <p class="mt-3 mb-3">위와 같이 장학금을 신청합니다.</p>
				                 <p class="today mt-3 mb-3"></p>
				                 <p class="text-right mt-3 mb-3">신청자 : ${authMember.memName }</p>
				              </div>
				           </div>
				           <div class="text-center">
				              <img id="logo1" src="${cPath}/images/logo/smartLMS_logo1.png" width="50px" alt="Logo">
				           	  <img id="logo2" src="${cPath}/images/logo/smartLMS_logo2.png" width="350px" alt="Logo">
				           </div>
				     </div>
				   </div>
				</div>	
				</form:form>	
			</div>			
		</div>
	</div>      	
	<div class="text-right">
		<button type="button" class="btn waves-effect waves-light btn-primary px-4 py-2 mx-1" id="insertBtn">등록</button>
		<button type="button" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1" id="resetBtn">취소</button>
	</div>
</div>
<script>
const validateOptions = {
        onsubmit:true,
        onfocusout:function(element, event){
           return this.element(element);
        },
        errorPlacement: function(error, element) {
           element.tooltip({
              title: error.text()
              , placement: "right"
              , trigger: "manual"
              , delay: { show: 500, hid: 100 }
           }).on("shown.bs.tooltip", function() {
              let tag = $(this);
              setTimeout(() => {
                 tag.tooltip("hide");
              }, 2000)
           }).tooltip('show');
          }
     }

$("#schForm").validate(validateOptions);

function enterkey(){
	 if ( event.which == 13 ) {
	        $('#searchBtn').click();
	        return false;
	 }
}

let schoar_Tag = $("[name='scharCode']");
let listBody = $("#listBody");
let searchForm = $("#searchForm");
$(function(){
	$.ajax({
		url:"${pageContext.request.contextPath }/lms/professor/scholarship/getScholarship.do",
		dataType:"json",
		success:function(resp){  
			console.log(resp);
			let scholarOpts = [];
			$(resp.schlist).each(function(idx, sch){
				scholarOpts.push(
					$("<option>").text(sch.schName)
								.attr("value", sch.schCode)
								.attr("value2",sch.schAmount)
								.addClass(sch.schCode)
								
				);
			});								
			schoar_Tag.append(scholarOpts);
		},
		error:function(xhr){
			console.log(xhr);
		}
	});

		searchForm.ajaxForm({
		url : '${cPath}/lms/professor/scholarship/studentList.do'
		, method : 'post'
		, data : searchForm.serialize()
		, dataType : 'json'
		, success : function(resp){
			let stuList = resp.memList;
			let trTags = [];
		  	if(stuList.length>0){
		  		$(stuList).each(function(idx, stu){
		  			trTags.push(
		  				$("<tr ondblclick='selectStudent(this)'>").append(
		  					 $("<td>").text(stu.memId)		
		  					, $("<td>").text(stu.memName)		
		  				).data("stu",stu)		
		  			)
		  		});
		  	}else{
		  		trTags.push(
			  		$("<tr>").html(
			  			$("<td colspan='2'>").text("검색 결과 없음.")
			  		)
		  		);
			}
		  	listBody.html(trTags);
		  	searchForm.find(":input").val("");
		}
		, error : function (request, error, textStatus){
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			alert(textStatus);
		}
	});
	searchForm.submit();
	
	//오늘날짜 구하기
	let date = new Date(); 
	let year = date.getFullYear(); 
	let month = new String(date.getMonth()+1); 
	let day = new String(date.getDate()); 

	// 한자리수일 경우 0을 채워준다. 
	if(month.length == 1){ 
	  month = "0" + month; 
	} 
	if(day.length == 1){ 
	  day = "0" + day; 
	} 

	$(".today").text(year + "년 " + month + "월 " + day+"일");
	
});

let searchBtn= $("#searchBtn").on("click", function() {
   let inputs = $(this).parents("div#inputUI").find(":input[name]");
   $(inputs).each(function(index, input) {
      let name = $(this).attr("name");
      let value = $(this).val();
      let hidden = searchForm.find("[name='"+name+"']");
      hidden.val(value);
   });
   searchForm.submit();
});	

function selectStudent(data){
	console.log(data);
	let td = $(this).text();
	let tdvalue = $(data).children().first().text();
	let memName = $(data).children().last().text();
	$("input[name='sfundStudent']").val(tdvalue);
	$("input[name='memName']").val(memName);
	
};

// 학생 추천 버튼 클릭시
$("#insertBtn").on("click",function(e){
	e.preventDefault();
	let count = $("input[name=scharCode]:checked").val();
	if(count==""){
	    alert("항목을 하나이상 체크하세요");
	    return;
	}
	let sfundValue = $("#scharCode option:selected").attr('value2');
	console.log(sfundValue);
	let input1 = document.createElement('input');
	input1.setAttribute("type", "hidden"); 
	input1.setAttribute("name", "sfundValue"); 
	input1.setAttribute("value",sfundValue);
	$("#schForm").append(input1);	
	
	$("#schForm").submit();
	
});


	

</script>
    