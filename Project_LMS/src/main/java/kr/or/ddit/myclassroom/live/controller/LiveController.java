package kr.or.ddit.myclassroom.live.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.api.client.auth.oauth2.Credential;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.enumpkg.PushMessageType;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.live.service.CredentialService;
import kr.or.ddit.myclassroom.live.service.LiveService;
import kr.or.ddit.myclassroom.live.vo.LiveVO;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;
import kr.or.ddit.vo.PushMessageVO;
import kr.or.ddit.websocket.event.PushMessageEvent;

/**
 * 실시간 강의 컨트롤러
 * @author 김선준
 */
@Controller
public class LiveController extends BaseController {

	@Inject
	private LiveService liveService;
	
	@Inject
	private ApplicationEventPublisher publisher;
	
	@Inject
	private CredentialService credentialService;
	
	/** 실시간 강의 리스트  **/
	@RequestMapping("/myclass/{lecCode}/liveList.do")
	public String liveList(@PathVariable(value = "lecCode", required = true) String lecCode
						 , @AuthenticationPrincipal(expression="realMember") MemberVO authMember
						 , Model model){
		List<LiveVO> liveList = liveService.selectLiveList(LiveVO.builder().lecCode(lecCode).build());
		model.addAttribute("authMember", authMember);
		model.addAttribute("liveList", liveList);
		return "myClassRoom/live/liveList";
	}

	/** 실시간 강의 등록 폼 **/
	@RequestMapping("/myclass/{lecCode}/liveForm.do")
	public String liveForm(@PathVariable(value = "lecCode", required = true) String lecCode
						 , @AuthenticationPrincipal(expression="realMember") MemberVO authMember
						 , @RequestAttribute("professorId") String professorId
						 , LiveVO liveVO) throws IOException {
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
		liveVO.setLecCode(lecCode);
		int lecDays = liveService.selectTotalWeek(liveVO).getLecDays();
		liveVO.setLecDays(lecDays);
		return "myClassRoom/live/liveForm";
	}
	
	/** 실시간 강의 조회 **/
	@RequestMapping("/myclass/{lecCode}/{liveNo}/liveView.do")
	public String liveView(@PathVariable(value = "lecCode", required = true) String lecCode
			             , @PathVariable(value = "liveNo" , required = true) Integer liveNo
			             , @AuthenticationPrincipal(expression="realMember") MemberVO authMember
			             , @RequestAttribute("professorId") String professorId
			             , Model model
			             , HttpSession httpSession
			             , RedirectAttributes redirectAttributes
			             , LiveVO formVO) throws IOException {
		
	
		// 1. 저장된 Credential 로딩 
//		Credential credential =  credentialService.loadCredential(authMember.getMemId());
		// 2. 저장된 credential이 없는 경우. access_token/refresh_token 발급 절차 진행
//		if(credential == null) {
//			httpSession.setAttribute("redirectUrl", "/myclass/"+lecCode+"/"+liveNo+"/liveView.do");
//			return "redirect:" + credentialService.sendAuthorizationCodeRequest();
//		}
		
		// 3. 저장된 credential이 있는 경우.
//		printInfo("저장된 access token", credential.getAccessToken());
//		printInfo("access token 잔여시한", credential.getExpiresInSeconds());
//		printInfo("저장된 refresh token", credential.getRefreshToken());
		
		// 4. Credential 이 만료된 경우, token refresh 진행 
//		credentialService.validateCredential(credential);
		
		/*
		LiveVO liveVO = liveService.selectLive(LiveVO.builder().liveNo(liveNo).credential(credential).build());
		String lifeCycleStatus = liveVO.getLifeCycleStatus();
		
		// 방송중이면 채팅창과 출석부가 보이고 나머지 경우는 안보임
		switch(lifeCycleStatus) {
		case "ready" :
		case "liveStarting" :
		case "live" :
			model.addAttribute("chat", true);
			
			// 접속자가 학생이면 출석처리
			if("ROLE_STUDENT".equals(authMember.getMemType())){
				LiveVO myLiveAttendanceVO = LiveVO.builder().memId(authMember.getMemId()).lecCode(lecCode).liveWeek(liveVO.getLiveWeek()).build();
				liveService.updateStudentAttendance(myLiveAttendanceVO);
			}
			
			// 출석부 리스트 불러옴
			List<LiveVO> attendanceList = liveService.selectStudentAttendanceList(liveVO);
			
			// 출석 코드 리스트
			List<LiveVO> attendanceCodeList = liveService.selectAttendanceCodeList();
			
			model.addAttribute("attendanceCodeList", attendanceCodeList);
			model.addAttribute("attendanceList", attendanceList);
			break;
		case "complete" :
		case "removed" :
			model.addAttribute("chat", false);
			break;
		default:
			redirectAttributes.addFlashAttribute("msg", notyErrorMessage());
			return "redirect:/myclass/{lecCode}/liveList.do";
		}
		*/
		
		LiveVO liveVO = liveService.selectLive(LiveVO.builder().liveNo(liveNo).build());
		
		model.addAttribute("chat", true);
		
		// 접속자가 학생이면 출석처리
		if("ROLE_STUDENT".equals(authMember.getMemType())){
			LiveVO myLiveAttendanceVO = LiveVO.builder().memId(authMember.getMemId()).lecCode(lecCode).liveWeek(liveVO.getLiveWeek()).build();
			liveService.updateStudentAttendance(myLiveAttendanceVO);
			
			PushMessageVO source = new PushMessageVO(PushMessageType.LIVEATTENDANCE, Arrays.asList(professorId), authMember.getMemName() + "학생이 출석했습니다.");
			PushMessageEvent event = new PushMessageEvent(source);
			printInfo("event", event);
			publisher.publishEvent(event);
		}
		
		// 출석부 리스트 불러옴
		List<LiveVO> attendanceList = liveService.selectStudentAttendanceList(liveVO);
		
		// 출석 코드 리스트
		List<LiveVO> attendanceCodeList = liveService.selectAttendanceCodeList();
		
		model.addAttribute("attendanceCodeList", attendanceCodeList);
		model.addAttribute("attendanceList", attendanceList);
		
		model.addAttribute("liveVO", liveVO);
		model.addAttribute("authMember", authMember);
		model.addAttribute("streamKey", httpSession.getAttribute("streamKey"));
		
		
		return "myClassRoom/live/liveView";
	}
	
	/** 구글 인증 **/
	@RequestMapping("/authorize.do")
	public String authorize(@RequestParam("code") String code
						  , @AuthenticationPrincipal(expression="realMember") MemberVO authMember
						  , HttpSession httpSession) throws IOException {
		String redirectUrl = (String) httpSession.getAttribute("redirectUrl");
		credentialService.createAndStoreCredential(code, authMember.getMemId());
		return "redirect:"+redirectUrl;
	}
	
	/** 실시간 강의 등록 **/
	@RequestMapping("/myclass/{lecCode}/broadcastInsert.do")
	public String broadcast(@Validated LiveVO liveVO
			, BindingResult errors
			, @PathVariable(value="lecCode", required=true) String lecCode
			, Model model){
		if(errors.hasErrors()) {
			return "myClassRoom/live/liveForm";
		}
		return "forward:/myclass/broadcastInsert.do";
	}
	
	@RequestMapping("/myclass/broadcastInsert.do")
	public String broadcast(LiveVO liveVO
						  , HttpSession httpSession
						  , @AuthenticationPrincipal(expression="realMember") MemberVO authMember) throws IOException {
		// 1. 저장된 Credential 로딩 
		Credential credential =  credentialService.loadCredential(authMember.getMemId());
		
		// 2. 저장된 credential이 없는 경우. access_token/refresh_token 발급 절차 진행
		if(credential == null) {
			httpSession.setAttribute("liveVO", liveVO);
			httpSession.setAttribute("redirectUrl", "/myclass/broadcastInsert.do");
			return "redirect:" + credentialService.sendAuthorizationCodeRequest();
		}
		
		// 3. 저장된 credential이 있는 경우.
		printInfo("저장된 access token", credential.getAccessToken());
		printInfo("access token 잔여시한", credential.getExpiresInSeconds());
		printInfo("저장된 refresh token", credential.getRefreshToken());
		
		// 4. Credential 이 만료된 경우, token refresh 진행 
		credentialService.validateCredential(credential);
		
		LiveVO sessionLiveVO = (LiveVO) httpSession.getAttribute("liveVO");
		if(sessionLiveVO != null) {
			liveVO = sessionLiveVO;
			httpSession.removeAttribute("liveVO");
			httpSession.removeAttribute("redirectUrl");
		} 
		liveVO.setCredential(credential);
		
		liveService.liveStreams(liveVO);
		liveService.liveBroadcast(liveVO);
		liveService.broadcastBind(liveVO);
		
		liveVO.setMemId(authMember.getMemId());
		liveService.insertBroadcast(liveVO);
		httpSession.setAttribute("streamKey", liveVO.getStreamKey());
		
		return "redirect:/myclass/"+liveVO.getLecCode()+"/liveList.do";
	}
	
	/** 실시간강의 글 삭제 (유튜브의 영상은 삭제되지 않음) **/
	@PostMapping("/myclass/deleteLive.do")
	public String deleteLive(LiveVO liveVO
						   , RedirectAttributes redirectAttributes) {
		try {
			liveService.deleteLive(liveVO);
			redirectAttributes.addFlashAttribute("msg", notyDeleteSuccessMessage());
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", notyErrorMessage());
			return "redirect:/myclass/"+liveVO.getLecCode()+"/"+liveVO.getLiveNo()+"/liveView.do";
		}
		return "redirect:/myclass/"+liveVO.getLecCode()+"/liveList.do";
	}
	
	/** 출결 변경 **/
	@ResponseBody
	@PostMapping(value="/myclass/updateAttendance.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public NotyMessageVO updateAttendance(LiveVO liveVO
									    , @AuthenticationPrincipal(expression="realMember") MemberVO authMember){
		if(authMember.getMemId().equals(liveVO.getProfessorId())) {
			try {
				liveService.updateAttendance(liveVO);
				PushMessageVO source = new PushMessageVO(PushMessageType.LIVEATTENDANCE, Arrays.asList(liveVO.getMemId()), "출결이 변경되었습니다");
				PushMessageEvent event = new PushMessageEvent(source);
				printInfo("event", event);
				publisher.publishEvent(event);
				return NotyMessageVO.builder(liveVO.getMemName() + " 학생의 출결정보가 정상적으로 변경되었습니다").layout(NotyLayout.topCenter).type(NotyType.success).build();
			}catch(Exception e) {
				return notyErrorMessage();
			}
		}
		return notyAccessDeniedMessage();
	}
	
	@ResponseBody
	@PostMapping(value="/myclass/getAttendanceList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public LiveVO getAttenTimeList(LiveVO liveVO){
		liveVO.setAttenTimeList(liveService.selectStudentAttendanceList(liveVO));
		return liveVO;
	}
	
	
	
}
