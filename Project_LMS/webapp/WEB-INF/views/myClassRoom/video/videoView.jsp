<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<style>
.iframeDiv{width:760px;height:515px;}
iframe{width:560px; height:315px; }
</style>
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 내강의실 > 학습목차 > 강의목록 </h5>
		</div>
	</div>
	<div class="row">
		<div class="col-12 mt-4">
			<!-- Card -->
			<div class="card">
				<div class="card-header">
					<i class="fas fa-book"> 현재 시청 강의</i>
				</div>
				<div class="card-body">
					<h4 class="card-title"></h4>
					<p class="card-text">
					<div class="d-flex no-block align-items-center">
						<div class="mr-3">
							<button type="button" class="btn btn-primary btn-circle">
								<i class="fa fa-list"></i>
							</button>
						</div>
						<div class="col-lg-4">
							<div class="grid-container">
								<h3>${video.week}주차 -${video.videoTitle }</h3>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="grid-container" id="attendDate" data-attstart="${video.videoAttstart }" data-attend="${video.videoAttend }">
								<h3>
									<i class="fas fa-calendar-check"></i> ${fn:substring(video.videoAttstart, 0, 10)} - ${fn:substring(video.videoAttend, 0, 10)}
								</h3>
							</div>
						</div>
					</div>
				</div>
				<!-- Card -->
			</div>
			<div id="attendDay" class="alert alert-primary" role="alert">
				<strong><i class="fas fa-clock"></i> 출석 -</strong> <span id="spanText">현재 출석 인정 기간입니다.</span>
			</div>
			<div class="iframeDiv embed-responsive embed-responsive-4by3">
				 <iframe id="player" src="${video.videoUrl }" frameborder="0"></iframe>
			</div>
			<!-- ===================== 출석관련 학생만 보이게 ======================= -->
			  <security:authorize access="hasRole('ROLE_STUDENT')">
				<security:authentication property="principal" var="principal" />
				<c:set var="authMember" value="${principal.realMember }" />
					<div class="alert alert-secondary" role="alert">
			<!-- 			<h4> 학습 인정 현황  -  <b>11분 15초(100%)</b>  -->
						<h4> 
							<button type="button" id="timeCheck" class="btn btn-primary btn-rounded timeCheck"><i class="fas fa-check"></i>학습종료</button>
							학습 인정을위해 '학습종료'버튼을 눌러야 인정이 됩니다.
						</h4>
					</div>
			 </security:authorize>
			<!-- =============================================================== -->
			<div class="col-12 mt-4">
				<div class="card">
					<div class="card-header">
						<i class="fas fa-paper-plane"> 강의 내용 </i>
					</div>
					<div class="card-body">
						${video.videoContent }
					</div>
				</div>
			</div>
			<div class="grid-structure">
				<div class="row">
					<div class="col-12 text-right">
						<button type="button" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1" id="resetBtn">목록</button>
							<!-- ===================== 교수만 보이게 ======================= -->
					        <security:authorize access="hasRole('ROLE_PROFESSOR')">
								<security:authentication property="principal" var="principal" />
								<c:set var="authMember" value="${principal.realMember }" />
									<button id="updateBtn" type="button" class="btn waves-effect waves-light btn-warning px-4 py-2 mx-1">수정</button>
									<button type="button" class="btn waves-effect waves-light btn-danger px-4 py-2 mx-1" id="delBtn">삭제</button>
							</security:authorize>
					</div>
				</div>
			</div>
			<!-- 전체 틀 -->
		</div>
	</div>
</div>

<form id="viewForm" method="post" action="${cPath}/myclass/insertViewRecord.do">
<input type="hidden" name="viewTime">
<input type="hidden" name="viewSession">
<input type="hidden" name="videoLen">
<input type="hidden" name="week" value="${video.week }">
<input type="hidden" name="videoNo" value="${video.videoNo }">
<%-- <input type="hidden" name="memId" value="${principal.realMember.memId }"> --%>
<input type="hidden" name="lecCode" value="${video.lecCode }" >
</form>

<!-- ============= 삭제 확인 모달 ================ -->
<!-- ========================================= -->
<div id="delModal" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="danger-header-modalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header modal-colored-header bg-danger">
				<h4 class="modal-title font-weight-bold" id="danger-header-modalLabel">삭제 여부 확인</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<h4 class="font-weight-bold">해당 강의영상을 삭제하시겠습니까?</h4>
			</div>
			<div class="modal-footer" style="margin: auto;">
				<button type="button" class="btn btn-light" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-danger" id="goDelBtn">삭제</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->



<!-- ============= 학습 종료모달 ================ -->
<!-- ========================================= -->
<div id="endStudy" class="modal fade" tabindex="-1"
	role="dialog" aria-labelledby="primary-header-modalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header modal-colored-header bg-danger">
				<h4 class="modal-title font-weight-bold" id="danger-header-modalLabel">학습 종료</h4>
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
			</div>
			<div class="modal-body">
				<h4 class="font-weight-bold">학습을 종료하시겠습니까?</h4>
			</div>
			<div class="modal-footer" style="margin: auto;">
				<button type="button" class="btn btn-light" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-danger" id="endBtn">종료</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script type="text/javascript">
let sStart;
let eEnd;
let totalTime;
let today;

//출석시작날짜
let startDate = $("#attendDate").data("attstart");
//출석종료날짜
let endDate = $("#attendDate").data("attend");


$(function(){
	
	// 오늘날짜 구하기
	 nStart = new Date().getTime();      //시작시간 체크(단위 ms)
	 let date = new Date();
	 let year = date.getFullYear(); //년도
	 let month = date.getMonth()+1; //월
	 let day = date.getDate(); //일
     if ((day+"").length < 2) {       
        day = "0" + day;
     }
     today = year+"-0"+month+"-"+day; // 오늘 날짜 (2017-02-07)
   	
     if(!(startDate<=today && today<=endDate)){
 		$("#attendDay").attr("class","alert alert-danger");
 		$("#spanText").text("출석 인정 기간이 아닙니다.");
 	 }
     
});

/**
 * Youtube API 로드
 */
let tag = document.createElement('script');
tag.src = "https://www.youtube.com/iframe_api";
let firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

/**
 * onYouTubeIframeAPIReady 함수는 필수로 구현해야 한다.
 * 플레이어 API에 대한 JavaScript 다운로드 완료 시 API가 이 함수 호출한다.
 * 페이지 로드 시 표시할 플레이어 개체를 만들어야 한다.
 */
let player;
function onYouTubeIframeAPIReady() {
    player = new YT.Player('player', {
        events: {
            'onReady': onPlayerReady,               // 플레이어 로드가 완료되고 API 호출을 받을 준비가 될 때마다 실행
            'onStateChange': onPlayerStateChange    // 플레이어의 상태가 변경될 때마다 실행
        }
    });
    // 추가한소스
    window.tmp_obj = player;
}
function onPlayerReady(event) {
    console.log('onPlayerReady 실행');

    // 플레이어 자동실행 (주의: 모바일에서는 자동실행되지 않음)
    event.target.playVideo();
    // 추가한 소스..
    console.log((tmp_obj.getCurrentTime()).toFixed(2));
    console.log((tmp_obj.getDuration()).toFixed(2));
}
let playerState;
function onPlayerStateChange(event) {
    playerState = event.data == YT.PlayerState.ENDED ? '종료됨' :
            event.data == YT.PlayerState.PLAYING ? '재생 중' :
            event.data == YT.PlayerState.PAUSED ? '일시중지 됨' :
            event.data == YT.PlayerState.BUFFERING ? '버퍼링 중' :
            event.data == YT.PlayerState.CUED ? '재생준비 완료됨' :
            event.data == -1 ? '시작되지 않음' : '예외';
	console.log((tmp_obj.getCurrentTime()).toFixed(2));
    console.log('onPlayerStateChange 실행: ' + playerState);
}

// 학습종료버튼 누를시 viewrecord input 태그 생성
$("#timeCheck").on("click",function(){

	console.log("오늘"+today);
	console.log("시작"+startDate);
	console.log("종료"+endDate);
	console.log(startDate<today);
	console.log(today<endDate);
	//출석기간이 아닐경우 출석불가
	if(!(startDate<=today && today<=endDate)){
		alert("출석기간이 아님");
		return false;
	}
	
	nEnd =  new Date().getTime();      //종료시간 체크(단위 ms)
	let nDiff = nEnd - nStart;      //두 시간차 계산(단위 ms)
	console.log(nEnd+"세션종료");
	if(totalTime!=null){
		nDiff =  totalTime +nDiff;
	}
	console.log((nDiff/1000).toFixed(2) + "ms세션시간들음");
	
	console.log((tmp_obj.getCurrentTime()).toFixed(2)+"재생시간");
	console.log(tmp_obj.getDuration()+"총영상시간");
	$("input[name=viewTime]").val((tmp_obj.getCurrentTime()).toFixed(2));
	$("input[name=viewSession]").val((nDiff/1000).toFixed(2));
	$("input[name=videoLen]").val((tmp_obj.getDuration()).toFixed(2));
	$("#endStudy").modal();
});

$("#resetBtn").on("click", function(){
	let lecCode = $("input[name=lecCode]").val();
	location.href="${cPath}/myclass/"+lecCode+"/videoList.do";
});

// 종료시 학습시간 insert
$("#endBtn").on("click",function(){
	$("#viewForm").submit();
	
});

$("#updateBtn").on("click",function(){
	let videoNo = $("input[name=videoNo]").val();
	let lecCode = $("input[name=lecCode]").val();
	location.href = "${cPath}/myclass/"+lecCode+"/"+videoNo+"/updateVideoForm.do";
});

$("#delBtn").on("click",function(){
	$("#delModal").modal();
});

$("#goDelBtn").on("click", function() {
	let lecCode = $("input[name='lecCode']").val();
	$("#viewForm").attr("action","${cPath}/myclass/"+lecCode+"/deleteVideo.do");
	$("#viewForm").submit();
}); 
</script>