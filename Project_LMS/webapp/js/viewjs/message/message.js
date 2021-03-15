let flag =0;
$(function(){
	$.ajax({
		url : $.getContextPath()+"/message/messageCount.do",
		method : "post",
		dataType : "json",
		success : function(resp) {
			if (resp.result != "NO") {
				$("#msgCount").text(resp.result);
			}
		},
		error : function(xhr) {
			console.log(xhr);
		}
	});
	
	$("#bell").on("click",function(){
		if(flag==1){
			flag = 0;
		}else {
			flag = 1;
		}
		let text="";
		if(flag==1){
			$.ajax({
				url : $.getContextPath()+"/message/selectMessage.do",
				method : "post",
				dataType : "json",
				success : function(resp) {
					if (resp.messageList != null) {
						$("#messageArea").empty();
						let msgMax =resp.messageList.length;
						for(let i=0;i<msgMax;i++){
							let msg="msg";
							text+=("<div id='msg"+resp.messageList[i].msgNum+"' class=' d-flex align-items-center border-bottom px-3 py-2'>");
//							text+=("<a  href='#' onclick='deleteMessage("+resp.messageList[i].msgNum+");return false;' >");
							text+=("<span class='btn  rounded-circle btn-circle'>");
							text+=("<img src='"+$.getContextPath()+"/images/message.png' style='width: 25px;'>");
							text+=("</span>");
							text+=("<div class='w-75 d-inline-block v-middle pl-2'>");
							text+=("<h6 class='message-title mb-0 mt-1'>"+resp.messageList[i].msgTime+"</h6> ");
							text+=("<span class='font-12 text-nowrap d-block text-muted'>");
							text+=(""+resp.messageList[i].msgContent+"  ");
							text+=("<button class='btn ml-2 btn-link text-info' onclick='deleteMessage("+resp.messageList[i].msgNum+");return false;'>");
							text+=("X");
							text+=("</button>");
							text+=("</span>");
							text+=("</span>");
							text+=("</div>");
//							text+=("</a>");
							text+=("</div>");
						}
					
						if(msgMax==0){
							text+=("<div class='d-flex align-items-center border-bottom px-3 py-2'>");
//							text+=("<a  href='#'  class='message-item d-flex align-items-center border-bottom px-3 py-2'>");
							text+=("<span class='btn  rounded-circle btn-circle'>");
							text+=("<img src='"+$.getContextPath()+"/images/message.png' style='width: 25px;'>");
							text+=("</span>");
							text+=("<div  class='w-75 d-inline-block v-middle pl-2'>");
							text+=("<h6 class='message-title mb-0 mt-1'>알림</h6> ");
							text+=("<span class='font-12 text-nowrap d-block text-muted'>");
							text+=("메세지가 없습니다.");
							text+=("</span>");
							text+=("</div>");
//							text+=("</a>");
							text+=("</div>");
						}
						$("#messageArea").append(text);
					}
				},
				error : function(xhr) {
					console.log(xhr);
				}
			});
		}
		
	});
});

function deleteMessage(msgNum){
	
	let msgCount = $("#msgCount").text();
	$.ajax({
		url : $.getContextPath()+"/message/deleteMessage.do",
		data:{"msgNum":msgNum},
		method : "post",
		dataType : "json",
		success : function(resp) {
			if (resp.result >0) {
				$("#msgCount").text(msgCount-1);
				$("#msg"+msgNum).remove();
			}
		},
		error : function(xhr) {
			console.log(xhr);
		}
	});
}	
	