let lecCode = $("#lecCodeHidden").val();

$("#updateBtn").on("click", function(event){
	event.preventDefault();
	$("#updateAndDeleteForm").attr("action", $.getContextPath()+"/myclass/"+lecCode+"/disUpdateForm.do").submit();
	return false;
});
$("#deleteBtn").on("click", function(event){
	event.preventDefault();
	$("#updateAndDeleteForm").attr("action", $.getContextPath()+"/myclass/"+lecCode+"/disDelete.do").submit();
	return false;
});

//====================댓글 CRUD==========================
	// 삭제
	function deleteReply(event){
		if(!confirm("정말 삭제하시겠습니까?")) return false;
		let reply = $(this).parents("tr:first").data("reply");
		$(replyDeleteForm).find("[name='repNo']").val(reply.repNo);
		$(replyDeleteForm).find("[name='memId']").val(reply.memId);
		$(replyDeleteForm).submit();
	}
	
	let listTable = $("#replyTable").on("click", ".delBtn", deleteReply).find("#listBody");
	
	
	let replyInsertForm = $("#replyInsertForm").ajaxForm({
		dataType : "json",
		success:function(resp){
			if(resp.result == "OK"){
				replyInsertForm.get(0).reset();
				searchForm.find("[name='page']").val(1);
				searchForm.submit();
			}else if(resp.msg){
				alert(resp.msg);
			}
		}
	});
	
	let replyDeleteForm = $("#replyDeleteForm").ajaxForm({
		dataType : "json",
		success:function(resp){
			if(resp.result == "OK"){
				replyInsertForm.get(0).reset();
				searchForm.submit();
			}else if(resp.msg){
				alert(resp.msg);
			}
		}
	});
	
	
//========================================================	
	
//====================덧글 페이징=======================
	let pagingArea = $("#pagingArea");
	let pagingA = pagingArea.on('click', "a" ,function(){
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;
	});
	
	let searchForm = $("#searchForm").ajaxForm({ // 댓글 불러오기
		dataType : "json",
		success : function(resp) {
			listTable.find("tbody").empty(); // 테이블 한번 비워주기
			pagingArea.empty(); // 페이징버튼도 비우기
			let replyList = resp.dataList;
			let authMemId = resp.memId;
			let trTags = [];
			if(replyList){
				$(replyList).each(function(idx, reply){ // 댓글하나씩 꺼내오기
					let tr = $("<tr>");
					tr.append(
							$("<td>").text(reply.member.memName).attr("data-memId", reply.memId),
							$("<td>").html(reply.repContent),
							$("<td>").text(reply.repDate)
					);
					if(reply.memId == authMemId){
						tr.append($("<td>").append( // 자기가 쓴 댓글이면 삭제버튼 넣어주기
								  	$("<input>").attr({type:"button", value:"삭제"}).addClass("btn btn-light delBtn"))
						);
					}else{
						tr.append($("<td>"));
					}
					tr.data("reply", reply);
					trTags.push(tr);
				});
			}else{
				trTags.push($("<tr>").html($("<td>").text("댓글이 없음."))); // 댓글없는거 표시해주기
			}
			$(".replyCountSpan").text(resp.totalRecord); // 전체 댓글 갯수 표시해주기
			let remainRowCnt = resp.screenSize - trTags.length;
	  		for(let i=0; i<remainRowCnt; i++){
	  			trTags.push($("<tr>").html($("<td colspan='4'>").html("&nbsp;")));
	  		}
			listTable.html(trTags);
			if(replyList.length>0)
				pagingArea.html(resp.pagingHTML);
			
			$("#replyTable tr>td:nth-child(1)").css("width", "10%");
			$("#replyTable tr>td:nth-child(2)").css("width", "70%");
			$("#replyTable tr>td:nth-child(3)").css("width", "10%").addClass("text-right");
			$("#replyTable tr>td:nth-child(4)").css("width", "10%").addClass("text-left");
		},
		error : function(errResp) {
			console.log(errResp);
		}
	}).submit(); // 페이지 로드 후 1페이지의 댓글 요청.
	
	
//========================================================