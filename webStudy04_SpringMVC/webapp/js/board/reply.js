/**
 * 
 */
//====================댓글 CRUD==========================
	function commonSuccess(resp){
		if(resp.result == "OK"){
			replyInsertForm.get(0).reset();
			replyModal.modal("hide");
			searchForm.submit();
		}else if(resp.message){
			alert(resp.message);
		}
	}
	function updateReply(event){
		let reply = $(this).parents("tr:first").data("reply");
		for(let prop in reply){
			$(replyUpdateForm).find("[name='"+prop+"']").val(reply[prop]);
		}
		replyModal.modal("show");
	}
	function deleteReply(event){
		if(!confirm("정말 지울텐가?")) return false;
		let password = prompt("비밀번호");
		if(!password){
			alert("비밀번호 입력!");
			return false;
		}
		let reply = $(this).parents("tr:first").data("reply");
		for(let prop in reply){
			$(replyUpdateForm).find("[name='"+prop+"']").val(reply[prop]);
		}
		$(replyDeleteForm).find("[name='rep_no']").val(reply.rep_no);
		$(replyDeleteForm).find("[name='rep_pass']").val(password);
		$(replyDeleteForm).submit();
	}
	
	let listTable = $("#replyTable").on("click", ".updateBtn", updateReply)
									.on("click", ".delBtn", deleteReply)
									.find("#listBody");
	
	let replyModal = $("#replyModal").on("hidden.bs.modal", function(){
		$(this).find("form").get(0).reset();
	});
	
	let options ={
		dataType : "json",
		success :commonSuccess
	}
	
	let replyInsertForm = $("#replyInsertForm").ajaxForm(options);
	let replyUpdateForm = replyModal.find("form").ajaxForm(options);
	let replyDeleteForm = $("#replyDeleteForm").ajaxForm(options);
//========================================================	
	
//====================덧글 페이징=======================
	let pagingArea = $("#pagingArea");
	let pagingA = pagingArea.on('click', "a" ,function(){
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		searchForm.find("[name='page']").val(1);
		return false;
	});
	
	let searchForm = $("#searchForm").ajaxForm({
		dataType : "json",
		success : function(resp) {
			listTable.find("tbody").empty();
			pagingArea.empty();
			let replyList = resp.dataList;
			let trTags = [];
			if(replyList){
				$(replyList).each(function(idx, reply){
					let tr = $("<tr>");
					tr.append(
							$("<td>").text(reply.rep_content)
									.addClass("text-center"),
							$("<td>").text(reply.rep_writer+"("+reply.rep_ip+")")
									.addClass("text-center"),
							$("<td>").text(reply.rep_date)
									.addClass("text-center"),	
							$("<td>").append(
								$("<input>").attr({
									type:"button",
									value:"수정"
								}).addClass("btn btn-info mr-2 updateBtn"),		
								$("<input>").attr({
									type:"button",
									value:"삭제"
								}).addClass("btn btn-danger mr-2 delBtn")		
							).addClass("text-center")	
					).data("reply", reply);
					trTags.push(tr);
				});
			}else{
				trTags.push(
					$("<tr>").html(
						$("<td>").text("댓글이 없음.")									
					)
				);
			}
			let remainRowCnt = resp.screenSize - trTags.length;
	  		for(let i=0; i<remainRowCnt; i++){
	  			trTags.push($("<tr>").html($("<td colspan='4'>").html("&nbsp;")));
	  		}
			listTable.html(trTags);
			if(replyList.length>0)
				pagingArea.html(resp.pagingHTML);
		},
		error : function(errResp) {
			console.log(errResp);
		}
	}).submit(); // 페이지 로드 후 1페이지의 댓글 요청.
//========================================================