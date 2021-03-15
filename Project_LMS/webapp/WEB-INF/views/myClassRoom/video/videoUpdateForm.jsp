<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<style type="text/css">
#youtubeSrc{width: 400px;}
input[type=date] {
  width: 30%;
}
</style>	
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<form:form method="post" id="videoForm" commandName="video" action="${cPath}/myclass/${video.lecCode}/updateVideo.do">
<input type="hidden" name="videoNo" id="videoNo" value="${video.videoNo }"/>
<input type="hidden" name="memId" id="memId" value="${video.memId }"/>
<input type="hidden" name="lecCode" id="lecCode" value="${video.lecCode }"/>
<input type="hidden" name="videoUrl" id="videoUrl" value="${video.videoUrl }"/>
<input type="hidden" name="videoLen" />
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 내강의실 > 학습목차 > 강의목록 </h5>
		</div>
	</div>
	
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">강의수정</h2>
		</div>
	</div>
	<div class="row mt-3 mb-5">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2">강의 주차를 선택하여 영상을 업로드 할 수 있습니다.</h5>
		</div>
	</div>
	<div class="row">
		<div class="card col-12">
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered table-striped mb-0">
						<tbody>
							<tr>
								<th class="text-nowrap" scope="row">주차</th>
								<td class="w-25">
									<select name="week" cssClass="form-control" id="selectSearch" cssStyle="display:inline-block width:50%" required disabled>
										<option value="${video.week }" <c:out value="${video.week eq i ? 'selected' :'' } "/>>${video.week }주차</option>
									</select>
								</td>
								<th class="text-nowrap" scope="row">학습 시간</th>
								<td colspan="5">
									<input type="text" placeholder="년-월-일" id="startDate" name="videoAttstart" value="${video.videoAttstart }" class="form-control" required="required" style="display:inline-block;width:150px;"/><i class='icon-calender'></i>
									- <input type="text" placeholder="년-월-일" id="endDate" name="videoAttend" value="${video.videoAttend }"  class="form-control" required="required" style="display:inline-block;width:150px;" /><i class='icon-calender'></i>
								</td>
							</tr>
							<tr>
								<th class="text-nowrap" scope="row">강의 명</th>
								<td colspan="5">
								<form:input path="videoTitle" cssClass="form-control" required="required"
									id="placeholder" placeholder="강의 명을 입력하세요."/></td>
							</tr>
							<tr>
								<th class="text-nowrap" scope="row">내용</th>
								<td colspan="5"><textarea class="form-control" id="lec_content" rows="3" placeholder="내용을 입력하세요." required="required"
									style="margin-top: 0px; margin-bottom: 0px; height: 161px;" name="videoContent">${video.videoContent }</textarea>
								</td>
							</tr>
							<tr>
								<th class="text-nowrap" scope="row">올바른 형식의 YouTube 주소를 입력해주세요.</th>
								<td>
									<input type="text" id="youtubeSrc" value="${video.videoUrl }" /><button type="button" id="srcBtn"><i class="fab fa-youtube"></i>영상업로드</button>
	 								<iframe id="player" width="560" height="315" src="${video.videoUrl }" frameborder="0" allow="fullscreen" allowfullscreen></iframe>
								</td>
							</tr>
							<tr>
								<th class="text-nowrap text-right" scope="row" colspan="6">
									<button type="button" id="updateVideo" class="btn waves-effect waves-light btn-warning px-4 py-2 mx-2">수정</button>
									<button type="button" class="btn waves-effect waves-light btn-light px-4 py-2 mx-1" id="resetBtn">취소</button>
								</th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</form:form>
<script type="text/javascript">
//======== validator ========
const validateOptions = {
        onsubmit:true,
        onfocusout:function(element, event){
           return this.element(element);
        },
        errorPlacement: function(error, element) {
           element.tooltip({
              title: error.text()
              , placement: "right"
              , trigger: "manual"
              , delay: { show: 500, hid: 100 }
           }).on("shown.bs.tooltip", function() {
              let tag = $(this);
              setTimeout(() => {
                 tag.tooltip("hide");
              }, 3000)
           }).tooltip('show');
          }
     }
     
$("#videoForm").validate(validateOptions);
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
    window.tmp_obj = player;
}
function onPlayerReady(event) {
    console.log('onPlayerReady 실행');
    event.target.playVideo();

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
    console.log('onPlayerStateChange 실행: ' + playerState);
    console.log((tmp_obj.getDuration()).toFixed(2));
    let duration = tmp_obj.getDuration().toFixed(2);
    $("input[name='videoLen']").val(duration);
}

	

let videoId;
// url 업로드시 
$("#srcBtn").on("click", function(){
	let src = $("#youtubeSrc").val();
	let srcArr = src.split("/");
	videoId = srcArr[3];
	player.loadVideoById(videoId);
	// 숨긴 iframe 보여줌
	let url = "https://www.youtube.com/embed/"+srcArr[3]+"?enablejsapi=1";
	console.log(url);
	$("input[name='videoUrl']").val(url);
	
	
});


//================ 날짜 ====================
$("#startDate").datepicker({
	dateFormat: "yy-mm-dd", // 날짜의 형식
	minDate: 0,
	nextText: ">",
	prevText: "<",
	onSelect: function (date) {
		var endDate = $('#endDate');
		var startDate = $(this).datepicker('getDate');
		var minDate = $(this).datepicker('getDate');
		endDate.datepicker('setDate', minDate);
		startDate.setDate(startDate.getDate() + 30);
		endDate.datepicker('option', 'maxDate', startDate);
		endDate.datepicker('option', 'minDate', minDate);
	}
});
$('#endDate').datepicker({
	dateFormat: "yy-mm-dd", // 날짜의 형식
	nextText: ">",
	prevText: "<"
});



$("#updateVideo").on("click",function(e){
	e.preventDefault();
	$("#videoForm").submit();
	return false;	
});

$("#resetBtn").on("click", function(){
	let lecCode = $("input[name=lecCode]").val();
	let videoNo = $("input[name=videoNo]").val();
	location.href="${cPath}/myclass/"+lecCode+"/"+videoNo+"/videoView.do";
});

</script>