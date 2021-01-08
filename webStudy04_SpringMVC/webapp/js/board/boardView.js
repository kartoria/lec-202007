/**
 * 
 */
// 	게시글 추천 
$.fn.recommend = function(){
	let bo_no = this.data("boNo");
	this.on("click", function(){
		$.ajax({
			url:$.getContextPath()+"/board/recommend.do",
			data:{
				what:bo_no
			},
			dataType:"text",
			success:function(resp){
				if("OK" == resp.trim()){
					let recCnt = $("#recArea").text();
					$("#recArea").text(parseInt(recCnt)+1);
					recBtn.remove();
				}
			},
			error:function(xhr){
				console.log(xhr);
			}
		});
	});
	return this;
}
	let recBtn = $("#recBtn").recommend();
// 게시글 삭제
	let boardDeleteForm = $("#boardDeleteForm");
	let removeBtn = $("#removeBtn").on("click", function(){
		let password = prompt("비밀번호 입력");
		if(password && confirm("정말 삭제할텨?")){
			boardDeleteForm.get(0).bo_pass.value = password;
			boardDeleteForm.submit();
		}
	});
// 답글쓰기
	let answerBtn = $("#answerBtn").on("click", function(){
		let bo_no = $(this).data("boNo");
		location.href=$.getContextPath()+"/board/boardInsert.do?parent="+bo_no;
	});

