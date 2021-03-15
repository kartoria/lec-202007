/**
 * @author 김성준
 * @since 2021. 1. 29.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자       수정내용
 * --------    	   --------    ----------------------
 * 2021. 1. 29.      김성준       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */ 
	$("#modifyBtn").hide();
	$("#deleteBtn").hide();
	
	$('input[type="checkbox"][name="checkBox"]').click(function(){
		if($(this).prop('checked')){
			$('input[type="checkbox"][name="checkBox"]').prop('checked', false);
			$(this).prop('checked', true);
		}
	});
	
	var result = ""; 
	function getCheckboxValue(event) {
		if(event.target.checked){
			$("#modalBtn").hide();
			$("#modifyBtn").show();
			$("#deleteBtn").show();
			result = event.target.value;
		} else {
			result ="";
			$("#modalBtn").show();
		}
		alert(result);
	}
	
	

	$("#modifyBtn").on("click", function(){
// 		var aaa= $("#checkBox").val();
// 		alert(aaa);
		function getCheckboxValue(event){
			if(event.target.checked){
				result = event.target.value;
			}
		}
		alert(result);

	});
	
	var searchForm = $("#searchForm");
	function pageLinkNumber(event){
		event.preventDefault();
		let page = $(event.target).data("page");
		searchForm.find("[name='page']").val(page);
		console.log(page);
		searchForm.submit();
		return false;
	}
	
	
	// 상담 등록	
	function ApplyCounseling(){
		var proId = $("#inlineFormCustomSelectupdate option:selected").val();
		var cstApptm = $("#cstApptm").val();
		var cstDate = $("#cstDate").val();
		
		var reg = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
		var regcstDate = null
		if(reg.test(cstDate)) {
			regcstDate = cstDate.replace(reg, "");
		}
		
		var realcstApptm = cstApptm.substr(0,2);
		var cstAccpt = $("#cstAccpt").val();
		console.log(proId);
		console.log(regcstDate);
		console.log(realcstApptm)
		console.log(cstAccpt)
		    $.ajax({
		    	url : '${cPath}/lms/student/counseling/applyInsert.do'
		    	, type : 'post'
		    	, data : 
		    		{
		    			"proId" : proId
		    			, "cstDate" : regcstDate
		    			, "cstApptm" : realcstApptm
		    			, "cstAccpt" : cstAccpt
		    		}
		    , dataType : "text"
		    , success : function(data){
		    	$("#scrollable-modal").modal('hide');
		    }
		    , error : function(request, error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
		    }
	    });
	}
	
// 	$("#scrollable-modalUpdate").on("click",function(){
// 		var proId1 = $("#inlineFormCustomSelectupdate option:selected").val();
// 		var cstApptm = $("#cstApptmupdate").val();
// 		var cstDate = $("#cstDateupdate").val();
		
// 		var reg = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
// 		var regcstDate1 = null
// 		if(reg.test(cstDate)) {
// 			regcstDate1 = cstDate.replace(reg, "");
// 		}
		
// 		var realcstApptm1 = cstApptm.substr(0,2);
// 		var cstAccpt1 = $("#cstAccpt").val();
		
// 		console.log(proId1);
// 		console.log(regcstDate1);
// 		console.log(realcstApptm1)
// 		console.log(cstAccpt1)
// 	});
	
	// 상담 수정
	function ApplyCounselingUpdate(){
		var proId1 = $("#inlineFormCustomSelectupdate option:selected").val();
		var cstApptm = $("#cstApptmupdate").val();
		var cstDate = $("#cstDateupdate").val();
		
		var reg = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
		var regcstDate1 = null
		if(reg.test(cstDate)) {
			regcstDate1 = cstDate.replace(reg, "");
		}
		
		var realcstApptm1 = cstApptm.substr(0,2);
		var cstAccpt1 = $("#cstAccptupdate").val();
		
// 		var cstNo = $('input[type="checkbox"][name="checkBox"]').val();
// 		var cstNo = evnet.target.val;
		function getCheckboxValue(event){
			if(event.target.checked){
				result = event.target.value;
			}
		}
		console.log(result);
		console.log(proId1);
		console.log(regcstDate1);
		console.log(realcstApptm1)
		console.log(cstAccpt1)
		    $.ajax({
		    	url : '${cPath}/lms/student/counseling/applyupdate.do'
		    	, type : 'post'
		    	, data : 
		    		{
		    			"proId" : proId1
		    			, "cstDate" : regcstDate1
		    			, "cstApptm" : realcstApptm1
		    			, "cstAccpt" : cstAccpt1
		    			, "cstNo" : result
		    		}
		    , dataType : "text"
		    , success : function(data){
		    	$("#scrollable-modalUpdate").modal('hide');
		    	location.href="${cPath}/lms/student/counseling/apply.do";
// 		    	$('input[type="checkbox"][name="checkBox"]').each(function(){
// 					$(this).attr("checked", false);
// 		    	});
		    }
		    , error : function(request, error){
		    	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
		    }
	    });
	}