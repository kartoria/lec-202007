<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12">
			<h5 class="ml-2">
				<i class="fas fa-home"></i> 학사일정관리
			</h5>
		</div>
	</div>
	<div class="row my-3 border-bottom border-admin p-0 my-2">
		<div class="col-12">
			<h2 class="font-weight-bold">학사일정</h2>
		</div>
	</div>
	<div class="row my-3">
		<div class="col-11">
			<h5 class="pb-3 ml-2">캘린더로 학사일정을 관리할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="card">
				<div class="calendar">
						<div id="colorView" >
                              <div id="calendar-events" >
                                  <div class="calendar-events pt-3 mb-3 ui-draggable ui-draggable-handle" style="position: relative;">
                                  		<i class="fa fa-circle TLEC mr-2"></i>수강신청
                                  </div>
                                  <div class="calendar-events mb-3 ui-draggable ui-draggable-handle" style="position: relative;">
                                  		<i class="fa fa-circle TEST mr-2"></i>시험
                                  </div>
                                  <div class="calendar-events mb-3 ui-draggable ui-draggable-handle" style="position: relative;">
                                  		<i class="fa fa-circle GRADE mr-2"></i>성적
                                  </div>
                                  <div class="calendar-events mb-3 ui-draggable ui-draggable-handle" style="position: relative;">
                                  		<i class="fa fa-circle PAY mr-2"></i>등록금
                                  </div>
                                  <div class="calendar-events mb-3 ui-draggable ui-draggable-handle" style="position: relative;">
                                  		<i class="fa fa-circle ETC mr-2"></i>기타
                                  </div>
                              </div>
						</div>
						<div id="calenderBody" class="col-lg-9">
							<div class="card-body b-l calender-sidebar">
								<div id="calendar"></div>
							</div>
						</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- ============================================================== -->
<!-- End Container fluid  -->
<!-- ============================================================== -->

<!-- 일정 추가 modal-->
<div class="modal fade" id="scheduleInsertModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="scheduleInsertModalLabel">
   <div class="modal-dialog modal-md" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title h4" id="scheduleInsertModalLabel">일정 추가</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body"></div>
      </div>
   </div>
</div>

<!-- 일정상세보기 modal-->
<div class="modal fade" id="scheduleViewModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="scheduleInsertModalLabel">
   <div class="modal-dialog modal-md" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h4 class="modal-title" id="scheduleInsertModalLabel">일정 상세보기</h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body"></div>
          <div class="modal-footer">
		      <input type="button" id="updateBtn" value="수정" class="btn btn-outline-primary" />
		      <input type="button" id="delBtn" value="삭제" class="btn btn-outline-primary" />
		      <button type="button" id="closeBtn" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
		  </div>
      </div>
   </div>
</div>


<script>
// json 데이터로 받아온 일정 배열에 저장
let eventsArr = [];
let scheduleList = JSON.parse('${scheduleList}');
$(scheduleList).each(function(idx,schedulVO){
   eventsArr.push(
        {
          title: schedulVO.scheTitle,
          start: schedulVO.scheStart,
          end : schedulVO.scheEnd,
          id : schedulVO.scheNo,
          classNames : schedulVO.scheSort
        }
    )
});
	   
$(function(){
	 let calendarEl = document.getElementById('calendar');
	   let calendar = new FullCalendar.Calendar(calendarEl, {
	      locale : 'ko',
	      initialView : 'dayGridMonth',  //달력화면   timeGridWeek:시간표 화면나옴
	      themeSystem: 'bootstrap',
	      selectable : true,
	      dayMaxEvents: true, // allow "more" link when too many events
	      headerToolbar : {
	         left : 'prevYear,prev,next,nextYear today scheduleInsert',
	         center : 'title',
	         right : 'dayGridMonth,dayGridWeek,dayGridDay'
	      },
         select : function(schedule) {
        	 let scheduleInsertModal = $("#scheduleInsertModal");
             console.log(schedule)
             scheduleInsertModal.find(".modal-body").load("${cPath}/admin/insertscheduleForm.do", function(){
                scheduleInsertModal.find("input[name='scheStart']").val(schedule.startStr);
                scheduleInsertModal.find("input[name='scheEnd']").val(schedule.endStr);
           	  	scheduleInsertModal.modal();
             });
//              scheduleInsertModal.find(".modal-body").load($.getContextPath() + "/schedule/scheduleForm.do", function() {
//              });
          },
	      customButtons : {
	            scheduleInsert : {
	               text : '일정추가'
	            },
	            
	      },
	       events: eventsArr,
	       eventClick : function(info){
	            let scheNo = info.event.id;
	            $("#scheduleViewModal").find(".modal-body").load("${cPath}/admin/"+scheNo+"/scheduleView.do", function(){
	               $("#scheduleViewModal").modal();
	            });
	       },
	       eventColor: '#C6AFD8',
	       eventTextColor: 'black'

	   });
	   calendar.render();
	
	
});

let scheduleInsertModal = $("#scheduleInsertModal").on("hidden.bs.modal",
        function() {
           $(this).find(".modal-body").empty();
        });



$(document).on("click",".fc-scheduleInsert-button",function() {

    scheduleInsertModal.find(".modal-body").load(
          "${cPath}/admin/insertscheduleForm.do",
          function() {
             scheduleInsertModal.modal();
          });
 });
 
$("#delBtn").on("click",function(){
	$("#delModal").modal();
	
});

$("#goDelBtn").on("click", function() {
	$("#viewForm").attr("action","${cPath}/admin/deleteSchedule.do");
	$("#viewForm").submit();
}); 


$("#upFormBtn").on("click",function(){
	$(":input").attr("readonly",false);
	$(":input").attr("disabled",false);
});

$("#updateBtn").on("click",function(){
	$("#viewForm").submit();
});


$(':input').focus(function() {
    $(this).addClass('focusbox');
}).blur(function() {
    $(this).removeClass('focusbox');
});


</script>		