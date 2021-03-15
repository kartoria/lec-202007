<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zTree_v3-master/js/jquery.ztree.core.js"></script>
<link rel="stylesheet" href="${cPath}/css/admin/student.css">
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 학생관리 > 학부정보
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-admin p-0 my-2">
		<div class="col-12">
			<h2 class="font-weight-bold">학부정보</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-5 ml-2">학부/학과 정보를 확인할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row pl-5">
		<div class="treeTable col-4">
			<!-- 트리구조 학부/학과/교수 -->
	   		 <ul id="ztree" class="ztree pt-5" ></ul>
		</div>
		<!-- 임시알림창 화면 -->
		<div class="tmpDiv col-8 pt-5" >
            <img alt="" id="tmpImage" src="${cPath}/images/collegeInfomation.png">
		</div>
		<div class="col-6" id="departmentDiv">
			<!-- 학과정보 테이블 -->
			<h3 class="text-center">&lt학과정보&gt</h3>
			<div id="departmentTable">
			</div>
		</div>
	</div>	
</div>	

<form id="departInfoForm" action="${cPath}/admin/selectDepartmentInfo.do">
<input type="hidden" name="depNo" >
</form>
<form id="professorForm" action="${cPath}/admin/selectProfessorDetail.do">
<input type="hidden" name="memId">
</form>
<!-- /.modal -->
<!-- 교수 상세정보 -->
<div class="modal fade" id="professorView" tabindex="-1"
	role="dialog" aria-labelledby="scrollableModalTitle"  aria-hidden="true">
	<div class="modal-dialog modal-xl" >
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="scrollableModalTitle">교수 상세 정보</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
						<table class="table" id="viewTable">
							<tbody id="modalBody">
							</tbody>
						</table>
			</div>
			<div class="modal-footer">
				<button type="button"
					class="btn waves-effect waves-light btn-rounded btn-secondary"
					data-dismiss="modal">닫기</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript">
        
$(function(){
	
	$("#departmentDiv").hide();
	
    $.ajax({
        type : "POST",
        url : "${cPath}/admin/collegeTreeStructureAjax.do",
        dataType : 'JSON',
        data : "",
        success : function(result){
                var setting = {
                                data: {
                                        simpleData: {
                                                enable: true
                                        }
                                },
                                callback: {
                    				onClick: onClick
                    			}
                        };
                var list = result;
                var jsonTree = [];
                jsonTree = "[";
                 $.each(list,function(key){
                              jsonTree += '{"id":'+list[key].id+',"pId":'+list[key].pid+',"name":"'+list[key].name+'","icon":"'+'${cPath}'+list[key].icon+'"},';
                 });
                 
                 jsonTree = jsonTree.slice(0,jsonTree.length-1);
                 
                 jsonTree += "]";


                 var zNodes = $.parseJSON(jsonTree);
                       $.fn.zTree.init($("#ztree"), setting, zNodes);
        }
    });
    
//     $(".level2").removeClass("curSelectedNode");
    
});




function onClick(event, treeId, treeNode, clickFlag) {
	// 교수 클릭 이벤트 막기
// 	$(".level2").on('click', function(e){
// 		$(".level2").removeClass("curSelectedNode");
// 		e.stopPropagation();
// 		e.preventDefault();
// 	});

	let str = treeNode.id;
	console.log(treeNode);
	// pId ==null ==> 학부면 return false
	if(treeNode.pId!=null){
		// id가 1000이상이면 교수 => 교수가 아닌
		if(treeNode.id<1000){
			$(".tmpDiv").hide();
			
			$("input[name='depNo']").val(treeNode.id);
			// 학과 선택시
			$.ajax({
				url : "${cPath}/admin/getCollegeAndState.do"
				,method : "post"
				,data : $("#departInfoForm").serialize()
				,dataType : "json"
				,success : function(data){
					makeDepartmentInfoTable(data);
				}
			});
		// 교수일경우 모달창	
		}else if(treeNode.id>1000){
			$("#professorView").find("#modalBody").load("${cPath}/admin/selectProfessorDetail.do?memId="+treeNode.id,function(){
				$("#professorView").modal("show");
			});
		}
	}else{
		return false;
	}
		
}	
	
	
function makeDepartmentInfoTable(data){
	let adminDepVO = data.adminDepVO;
	let roomList = data.roomList;
	let tmpFee = adminDepVO.depFee;
	let depFee = tmpFee.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')
	console.log(data);
	let html = '';
	html += '<table class="departmentTable table text-center">';
	html += '<tr>';
	html += '<th class="bg-primary">학부코드</th>';
	html += '<td>'+adminDepVO.colCode+'</td>';
	html += '<th class="bg-primary">학부명</th>';
	html += '<td>'+adminDepVO.colName+'</td>';
	html += '</tr>';
	html += '<tr>';
	html += '<th class="bg-primary">학과코드</th>';
	html += '<td>'+adminDepVO.depNo+'</td>';
	html += '<th class="bg-primary">학과명</th>';
	html += '<td class="text-primary">'+adminDepVO.depName+'</td>';
	html += '</tr>';
	html += '<tr>';
	html += '<th class="bg-primary">학과 전화번호</th>';
	html += '<td>'+adminDepVO.depTel+'</td>';
	html += '<th class="bg-primary">학과 등록금</th>';
	html += '<td>'+depFee+' 원</td>';
	html += '</tr>';
	html += '<tr>';
	html += '<th class="bg-primary">학과생</th>';
	html += '<td>'+(adminDepVO.stuCnt==null ? 0 : adminDepVO.stuCnt)+' 명</td>';
	html += '<th class="bg-primary">학과 교수</th>';
	html += '<td>'+(adminDepVO.proCnt==null? 0 :adminDepVO.proCnt )+' 명</td>';
	html += '</tr>';
	html += '<tr>';
	html += '<th class="bg-primary">개설년도</th>';
	html += '<td colspan="3">'+adminDepVO.depBorn+' 년</td>';
	html += '</tr>';
	html += '<tr>';
	html += '<th colspan="4" class="bg-primary">강의실정보</th>';
	html += '</tr>';
	if(roomList.length>0){
	html += '<tr>';
	html += '<td colspan="4">';
	html += '<div class="lectureRoomTable">';
	html += '<table class="table text-center">';
	html += '<tr>';
	html += '<th class="bg-dark">학부건물코드</th>';
	html += '<td colspan="3">'+roomList[0].code.slice(0,1)+' 관</td>';
	html += '</tr>';
		
		for(let i=0 ; i<roomList.length ; i++){
			html += '<tr>';
			html += '<th class="bg-dark">강의실</th>';
			html += '<td colspan="3">'+roomList[i].code+' ['+roomList[i].codeName+']'+'</td>';
			html += '</tr>';
		}
		html += '</table>';
	}
	html += '</div>';
	html += '</td>';
	html += '</tr>';
	
	html += '</table>';
	
	
	$("#departmentDiv").show();
	$("#departmentTable").html(html);
	
}	
	
</script>