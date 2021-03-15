<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<style type="text/css">
#youtubeSrc{width: 400px;}
.text-nowrap{width: 15%;}
</style>	
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<form:form method="post" id="videoForm" commandName="video" action="${cPath }/myclass/${lecCode}/videoInsert.do">
<input type="hidden" name="lecCode" value="${lecCode }" >
<input type="hidden" name="videoUrl" id="videoUrl" required/>
<input type="hidden" name="videoLen" required />
<div class="container-fluid">
	<div class="row my-3">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h5 class="ml-2"><i class="fas fa-home"></i> 내강의실 > 학습목차 > 강의목록 </h5>
		</div>
	</div>
	
	<div class="row my-3 border-bottom border-cyber">
		<div class="col-12 d-flex justify-content-start align-items-center">
			<h2 class="font-weight-bold">강의등록</h2>
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
								<th class="text-nowrap" scope="row"><span class="text-danger">* </span>주차<span class="text-danger" style="font-size: 0.75em;">(한 주에 한 강의씩 업로드 가능합니다.)</span></th>
								<td colspan="3">
									<form:select path="week" cssClass="form-control" id="selectSearch" cssStyle="display:inline-block width:50%" required="required" >
										<c:set var="weekList" value="${weekList }"/>
											<c:if test="${not empty weekList }">
												<c:forEach items="${weekList }" var="week">
													<form:option value="${week.lecWeek }">${week.lecWeek }주차</form:option>
												</c:forEach>
											</c:if>
									</form:select>
								</td>
								<th class="text-nowrap" scope="row"><span class="text-danger">* </span>학습 시간</th>
								<td colspan="3">
									<input type="text" id="startDate" name="videoAttstart" value="${video.videoAttstart }" class="form-control" autocomplete="off" required="required" style="display:inline-block;width:150px;"/><label for="startDate"><i class='icon-calender'></i></label>
									- <input type="text" id="endDate" name="videoAttend" value="${video.videoAttend }" class="form-control" autocomplete="off"  required="required" style="display:inline-block;width:150px;" /><label for="endDate"><i class='icon-calender'></i></label>
								</td>
							</tr>
							<tr>
								<th class="text-nowrap" onClick="putData()" scope="row"><span class="text-danger">* </span>강의 명</th>
								<td colspan="5">
								<form:input path="videoTitle" cssClass="form-control" required="required"  maxlength="30"
									id="placeholder" placeholder="강의 명을 입력하세요."/></td>
							</tr>
							<tr>
								<th class="text-nowrap" scope="row"><span class="text-danger">* </span>내용</th>
								<td colspan="5"><textarea class="form-control" id="lec_content" rows="3" required="required"
									style="margin-top: 0px; margin-bottom: 0px; height: 161px;" name="videoContent">기간내에 수강하시길 바랍니다.</textarea></td>
							</tr>
							<tr>
								<th class="text-nowrap" scope="row"><span class="text-danger">* </span>올바른 형식의 YouTube 주소를 입력해주세요.</th>
								<td colspan="5">
								<input type="text" id="youtubeSrc" required /><button type="button" id="srcBtn" class="btn waves-effect waves-light btn-light" ><i class="fab fa-youtube"></i>영상업로드</button>
	<!-- 							<input type="hidden" id="videoUrl" name="videoUrl"> -->
	<%-- 							<form:input path="videoUrl" cssClass="form-control"/> --%>
								
	<!-- 							<iframe id="player" width="200" height="150" src="" frameborder="0" ></iframe> -->
								</td>
							</tr>
							<tr>
								<th class="text-nowrap" scope="row"></th>
								<td colspan="5">
								<div id="playerDiv">
									<iframe id="player" width="560" height="315" src="https://www.youtube.com/embed/qSYnn5TMhtg?enablejsapi=1" frameborder="0" allow="fullscreen" allowfullscreen></iframe>
								</div>
								</td>
							</tr>
							<tr>
								<th class="text-nowrap text-right" scope="row" colspan="6">
									<button type="button" class="btn waves-effect waves-light btn-primary px-4 mx-1" id="insertVideo">등록</button>
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
// 	        height: '315',           // 이미 player 가 로드되어있는경우 설정필요 없음
// 	        width: '560',   
// 	        videoId: '',   
// 	        playerVars: { 'autoplay': 0, 'controls': 2 },
// 	        playerVars: { autoplay : '0' , controls: '2'},
// 	        playerVars: {             
// 	        	controls: '2'
// 	        },
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

// 화면 로드시
$(function (){
	
	$("#player").hide();
	
});

let videoId;
// url 업로드시 
$("#srcBtn").on("click", function(){
	let src = $("#youtubeSrc").val();
	
	//유튜브주소 valid 체크
	let regExp = /^(?:https?:\/\/)?(?:www\.)?(?:youtu\.be\/|youtube\.com\/(?:embed\/|v\/|watch\?v=|watch\?.+&v=))((\w|-){11})(?:\S+)?$/;
	let match = src.match(regExp);
	if(!match){
		alert("유효한 주소를 입력해주세요.");
		return false;
	}
	
	let srcArr = src.split("/");
	videoId = srcArr[3];
	player.loadVideoById(videoId);
	// 숨긴 iframe 보여줌
	$("#player").show();
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


$("#insertVideo").on("click",function(e){
	e.preventDefault();
	$("#videoForm").submit();
	return false;	
});

$("#resetBtn").on("click", function(){
	let lecCode = $("input[name=lecCode]").val();
	location.href="${cPath}/myclass/"+lecCode+"/videoList.do";
});


function putData(){
	$("input[name='videoTitle']").val("15주차 알기쉬운 SW 공학 배우기");
	$("#youtubeSrc").val("https://youtu.be/OLHPRJnHn-A");
}

</script>