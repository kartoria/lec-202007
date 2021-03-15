<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- ============================================================== -->
<!-- Bread crumb and right sidebar toggle -->
<!-- ============================================================== -->
<!-- <div class="page-breadcrumb"> -->
<!-- 	<div class="row"> -->
<!-- 		<div class="col-7 align-self-center"> -->
<!-- 			<h4 -->
<!-- 				class="page-title text-truncate text-dark font-weight-medium mb-1">학사일정</h4> -->
<!-- 			<div class="d-flex align-items-center"> -->
<!-- 				<nav aria-label="breadcrumb"> -->
<!-- 					<ol class="breadcrumb m-0 p-0"> -->
<!-- 						<li class="breadcrumb-item text-muted active" -->
<!-- 							aria-current="page">SmartLMS</li> -->
<!-- 						<li class="breadcrumb-item text-muted" aria-current="page">Calendar</li> -->
<!-- 					</ol> -->
<!-- 				</nav> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
<!-- ============================================================== -->
<!-- End Bread crumb and right sidebar toggle -->
<!-- ============================================================== -->
<!-- ============================================================== -->
<!-- Container fluid  -->
<!-- ============================================================== -->
<!-- fullcanlendar  -->
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 학사관리 > 학사일정</h5>
		</div>
	</div>
	
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">학사일정</h2>
		</div>
	</div>
	
<!-- 	<div class="row mt-3 mb-5"> -->
<!-- 		<div class="col-12 d-flex justify-content-start align-items-center"> -->
<!-- 			<h5 class="ml-2"></h5> -->
<!-- 		</div> -->
<!-- 	</div> -->
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
<!-- 일정상세보기 modal-->
<div class="modal fade" id="scheduleViewModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="scheduleInsertModalLabel">
   <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title h4" id="scheduleInsertModalLabel">일정 상세보기</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body"></div>
          <div class="modal-footer">
		      <button type="button" id="closeBtn" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
		  </div>
      </div>
   </div>
</div>
<script>
// json 데이터로 받아온 일정 배열에 저장
var eventsArr = [];
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
	 var calendarEl = document.getElementById('calendar');
	   var calendar = new FullCalendar.Calendar(calendarEl, {
	      locale : 'ko',
	      initialView : 'dayGridMonth',  //달력화면   timeGridWeek:시간표 화면나옴
	      themeSystem: 'bootstrap',
	      dayMaxEvents: true, // allow "more" link when too many events
	      headerToolbar : {
	         left : 'prevYear,prev,next,nextYear today',
	         center : 'title',
	         right : 'dayGridMonth,dayGridWeek,dayGridDay'
	      },
	       events: eventsArr,
	       eventClick : function(info){
	            let scheNo = info.event.id;
	            $("#scheduleViewModal").find(".modal-body").load("${cPath}/common/"+scheNo+"/scheduleView.do", function(){
	               $("#scheduleViewModal").modal();
	            });
	       },
	       eventColor: '#C6AFD8',
	       eventTextColor: 'black'

	   });
	   calendar.render();
	
	
});

</script>
