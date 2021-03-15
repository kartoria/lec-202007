///**
// * 과제 제출 리스트 js
// */
//let lecCode = $("#lecCodeHidden").val();
//
//$(function() {
//	// paging
//	let pagingArea = $("#pagingArea");
//	let pagingA = pagingArea.on('click', "a" ,function(){
//		let page = $(this).data("page");
//		searchForm.find("[name='page']").val(page);
//		searchForm.submit();
//		return false;
//	});
//	
//	let listTable = $("#trListBody");
//	
//	// =============== 과제 게시글 댓글 =======================	
//	let searchForm =$("#searchForm").ajaxForm({
//		dataType: "json"
//		,success : function(resp) {
//			listTable.find("tbody").empty();
//			pagingArea.empty();
//			let taskSbList = resp.pagingVO.dataList;
//			let authMemId = resp.memId;
//			let trTags = [];
//			if(taskSbList){
//				// 하나씩 꺼내기
//				$(taskSbList).each(function(idx, taskSb) {
//					let tr = $("<tr>");
//					tr.append(
//						$("<td>").html("<h3>"+taskSb.memId+"</h3>").attr("data-memId", taskSb.memId),
//						$("<td>").html("<h3>"+taskSb.memberVO.memName+"</h3>"),
//						$("<td>").text(taskSb.taskReply).attr("data-attNo", taskSb.attNo),
//						$("<td>").text(taskSb.taskSubmitDate.substring(0,10)),
//						$("<td>").html("<i class='fa fa-link'><a href='/board/download.do?what='"+taskSb.attNo+">"+taskSb.attFilename+"</a></i>")
//					);
//					// 본인이 작성한 글 일때
//					if(taskSb.memId == authMemId){  
//						// 1. 자기가 쓴 댓글이면 삭제버튼 넣어주기
//						tr.append(
//							$("<td>").append( 
//								$("<button>").attr({type:"button"}).addClass("btn waves-effect waves-light btn-rounded btn-danger").text("삭제"))
//						);
//						// 2. 이미 과제를 등록했다고 등록 폼 막기
//					} else {
//						tr.append($("<td>"));
//					}
//					console.log(${authMember.memType });
//					// 교수님일 때 
//					if("${authMember.memType }"=="ROLE_PROFESSOR"){
//						if(taskSb.taskScr ==null){
//							// 1. 배점을 안등록했을 떄
//							let trFor = "";
//							for(var i=1; i<11; i++){
//								trFor += "<option value="+i+">"+i+"</option>";
//							}
//							tr.append(
//								$("<td>").html($("<select>").html(trFor).addClass("form-control selectBox")),
//								$("<td>").append(
//									$("<button>").attr("type","button").addClass("btn waves-effect waves-light btn-rounded btn-light").text("등록")
//								)
//							);
//						} else {
//							// 2. 배점을 등록했을 때 
//							tr.append(
//								$("<td>").text(taskSb.taskScr+"점"),	
//								$("<td>").append(
//									$("<button>").attr("type","button").addClass("btn waves-effect waves-light btn-rounded btn-secondary").text("완료")
//								)	
//							)
//						}
//						// 배점 등록
//					}
//					tr.data("taskSb", taskSb);
//					trTags.push(tr);
//				});
//			}else {
//				trTags.push($("<tr>").html($("<td>").text("현재 제출된 과제가 존재하지 않습니다.")));
//			}
//			listTable.html(trTags);
//			if(taskSbList.length>0){
//				pagingArea.html(resp.pagingVO.pagingHTML);
//			}
//			// =============== 과제 배점 등록 ===================
//			$("#trListBody").on("click", "tr",function() {
//				// 1. 제출된 과제 attNo
//				let attNo = $(this).find("td:eq(2)").data("attno");
//				// 2. 등록할 배점 selected option value
//				let selected = $(this).find(".selectBox option:selected").val();
//				
//				$(".btn-light").on("click", function() {
//				})
////				option:selected").text()
//			});
//		}
//		,error : function(xhr) {
//			console.log(xhr);
//		}
//	}).submit();
//});