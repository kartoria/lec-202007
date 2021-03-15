/**
 * @author 조예슬
 * @since 2021. 2. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 20.     조예슬       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
package kr.or.ddit.myclassroom.video.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.video.service.VideoService;
import kr.or.ddit.myclassroom.video.vo.LecPlanVO;
import kr.or.ddit.myclassroom.video.vo.VideoAttendanceVO;
import kr.or.ddit.myclassroom.video.vo.VideoVO;
import kr.or.ddit.myclassroom.video.vo.ViewRecordVO;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;

/**
 * 마이 클래스 강의 목록
 */
@Controller
public class VideoController extends BaseController{
	@Inject
	private VideoService service;
	
	/** 강의 목록 리스트 조회 **/
	@RequestMapping("/myclass/{lecCode}/videoList.do")
	public String myClassRoomIndex(@AuthenticationPrincipal(expression="realMember") MemberVO authMember
								 , @PathVariable(value="lecCode", required=true) String lecCode
								 , Model model ) {
		List<VideoVO> videoList = service.retrieveVideoList(VideoVO.builder().lecCode(lecCode).build());
		model.addAttribute("videoList", videoList);
		model.addAttribute("pageTitle", "강의 목록");
		return "myClassRoom/video/videoList";
	}
	
	/** 강의 상세 보기 (영상 재생) **/
	@RequestMapping("/myclass/{lecCode}/{videoNo}/videoView.do")
	public String videoView(@PathVariable(value="lecCode", required=true) String lecCode, Model model,
						@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
						@PathVariable(value="videoNo", required=true) int videoNo) {
		try {
			VideoVO video= service.retrieveVideo(VideoVO.builder().videoNo(videoNo).lecCode(lecCode).build());
			model.addAttribute("video",video);
		} catch (Exception e) {
			model.addAttribute("message", NotyMessageVO.builder("서버 오류").build());
			return "redirect:/myclass/{lecCode}/videoList.do";
		}
 		return "myClassRoom/video/videoView";
		
	}
	
	@ModelAttribute("video")
	public VideoVO video() {
		return new VideoVO();
	}
	
	/** 강의 등록 폼 **/
	@RequestMapping("/myclass/{lecCode}/videoForm.do")
	public String videoForm(@PathVariable(value="lecCode", required=true) String lecCode, 
							@RequestAttribute("professorId") String professorId,Model model,
							@AuthenticationPrincipal(expression="realMember") MemberVO authMember) {
		
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
			getWeekList(lecCode,model);
		return "myClassRoom/video/videoForm";
	}
	
	/**강의주차 얻는 함수**/
	private void getWeekList(String lecCode,Model model) {
		List<LecPlanVO> weekList = service.retrieveLecplanList(lecCode);
		model.addAttribute("weekList", weekList);
	}

	/** 강의 등록하기  **/
	@RequestMapping("/myclass/{lecCode}/videoInsert.do")
	public String videoInsert(@PathVariable(value="lecCode", required=true) String lecCode, Model model,RedirectAttributes redirectModel,
					@Validated(InsertGroup.class) @ModelAttribute("video") VideoVO video, BindingResult errors
					,@AuthenticationPrincipal(expression="realMember") MemberVO authMember) {
		video.setMemId(authMember.getMemId());
		if(errors.hasErrors()) {
			List<LecPlanVO> weekList = service.retrieveLecplanList(lecCode);
			model.addAttribute("weekList", weekList);
			model.addAttribute("message", NotyMessageVO.builder("입력 오류").build());
			return "myClassRoom/video/videoForm";
		}
		try {
			service.videoInsert(video);
			redirectModel.addFlashAttribute("message", NotyMessageVO.builder("등록완료").layout(NotyLayout.topCenter).type(NotyType.success).timeout(3000).build());
			return "redirect:/myclass/{lecCode}/"+video.getVideoNo()+"/videoView.do";
		} catch (Exception e) {
			getWeekList(lecCode,model);
			model.addAttribute("message", NotyMessageVO.builder("서버 오류").build());
			return "myClassRoom/video/videoForm";
		}
	}
	
	// validation추가하기
	/**영상시청기록**/
	@RequestMapping(value="/myclass/insertViewRecord.do",method=RequestMethod.POST)
	public String viewRecord(@Validated(InsertGroup.class) @ModelAttribute("viewRecordVO")ViewRecordVO viewRecordVO,BindingResult errors,
										RedirectAttributes redirectModel,Model model,
										@AuthenticationPrincipal(expression="realMember") MemberVO authMember) {
		viewRecordVO.setMemId(authMember.getMemId());
		try {
			if(!errors.hasErrors()) {
				int cnt = service.insertViewRecord(viewRecordVO);
				if(cnt>0) {
					insertAttendance(viewRecordVO,redirectModel);
				}
				redirectModel.addFlashAttribute("message", NotyMessageVO.builder("종료확인").layout(NotyLayout.topCenter).type(NotyType.info).build());
				return "redirect:/myclass/"+viewRecordVO.getLecCode()+"/videoList.do";
			}else {
				model.addAttribute("message", NotyMessageVO.builder("입력값 오류").build());
				return "/myclass/"+viewRecordVO.getLecCode()+"/"+viewRecordVO.getVideoNo()+"/videoView.do";
			}
		} catch (DataAccessException e) {
			model.addAttribute("message", NotyMessageVO.builder("서버 오류").build());
			return "/myclass/"+viewRecordVO.getLecCode()+"/"+viewRecordVO.getVideoNo()+"/videoView.do";
		}
		
	}
	/**영상시청기록후 출석체크하기**/
	private void insertAttendance(ViewRecordVO viewRecordVO,RedirectAttributes redirectModel) {
		ViewRecordVO findRecordVO = service.retrieveViewRocord(viewRecordVO);
		if(!ObjectUtils.isEmpty(viewRecordVO)) {
			String bf_viewTime = findRecordVO.getViewTime();
			String bf_videoLen = findRecordVO.getVideoLen();
			String viewTime = bf_viewTime.substring(0,bf_viewTime.indexOf("."));
			String videoLen = bf_videoLen.substring(0,bf_videoLen.indexOf("."));
			LOGGER.info("{}시청시간", viewTime);
			LOGGER.info("{}영상길이", videoLen);
			// 수강시간이 영상길이 80% 이상일 경우 출석처리
			if(Integer.parseInt(viewTime)>=Integer.parseInt(videoLen)-Integer.parseInt(videoLen)*0.2) {
				VideoAttendanceVO attendVO = new VideoAttendanceVO();
				attendVO = service.retrieveCheckAttendant(viewRecordVO);
				// 출석 현황에 아직 기록이 되어있지 않은 경우
				if(attendVO==null||ObjectUtils.isEmpty(attendVO)) {
					attendVO = VideoAttendanceVO.builder().
							lecCode(viewRecordVO.getLecCode()).
							memId(viewRecordVO.getMemId()).
							attenCode("ATTEND").
							week(viewRecordVO.getWeek()).build();
					try {
						int cnt = service.insertVideoAttendance(attendVO);
					} catch (DataAccessException e) {
						redirectModel.addFlashAttribute("message", NotyMessageVO.builder("서버 오류").build());
					}
				}
			}
		}
	}
	
	/**강의영상수정폼**/
	@RequestMapping("/myclass/{lecCode}/{videoNo}/updateVideoForm.do")
	public String updateForm(@PathVariable(value="videoNo", required=true) int videoNo,
								@PathVariable(value="lecCode", required=true) String lecCode,Model model,
								@RequestAttribute("professorId") String professorId,
								@AuthenticationPrincipal(expression="realMember") MemberVO authMember){
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
		VideoVO video= service.retrieveVideo(VideoVO.builder().videoNo(videoNo).lecCode(lecCode).build());
		model.addAttribute("video",video);
		getWeekList(lecCode,model);
		return "myClassRoom/video/videoUpdateForm";
	}
	
	/**강의영상수정**/
	@RequestMapping(value="/myclass/{lecCode}/updateVideo.do",method=RequestMethod.POST)
	public String updateForm(@PathVariable(value="lecCode", required=true) String lecCode,@Validated(UpdateGroup.class) @ModelAttribute("video") VideoVO video, BindingResult errors,RedirectAttributes redirectModel,
								Model model){
		if(errors.hasErrors()) {
			LOGGER.error("{}", errors);
			getWeekList(video.getLecCode(),model);
			model.addAttribute("message", NotyMessageVO.builder("입력 오류").build());
			return "myClassRoom/video/videoUpdateForm";
		}
		try {
			int cnt = service.modifyVideo(video);
			redirectModel.addFlashAttribute("message", NotyMessageVO.builder("수정완료").layout(NotyLayout.topCenter).type(NotyType.success).build());
		} catch (Exception e) {
			model.addAttribute("message", NotyMessageVO.builder("서버 오류").build());
			return "myClassRoom/video/videoUpdateForm";
		}
		
		return "redirect:/myclass/"+video.getLecCode()+"/"+video.getVideoNo()+"/videoView.do";
	}
	
	/**강의영상삭제**/
	@RequestMapping(value="/myclass/{lecCode}/deleteVideo.do",method=RequestMethod.POST)
	public String deleteVideo(@PathVariable(value="lecCode", required=true) String lecCode,@ModelAttribute("viewRecordVO")ViewRecordVO viewRecordVO,
								RedirectAttributes redirectModel,Model model,
								@RequestAttribute("professorId") String professorId,
								@AuthenticationPrincipal(expression="realMember") MemberVO authMember) {
		if(!authMember.getMemId().equals(professorId)) {
			throw new AccessDeniedException("해당 강의의 교수가 아님");
		}
		String goPage = null;
		if(viewRecordVO.getVideoNo()!=null||viewRecordVO.getVideoNo()<0) {
			try {
				int cnt = service.removeVideo(viewRecordVO);
				redirectModel.addFlashAttribute("message", NotyMessageVO.builder("삭제완료").layout(NotyLayout.topCenter).type(NotyType.success).build());
				goPage = "redirect:/myclass/"+viewRecordVO.getLecCode()+"/videoList.do";
			} catch (Exception e) {
				model.addAttribute("message", NotyMessageVO.builder("서버 오류").build());
				goPage = "myClassRoom/video/videoView";
			}
			
		}
		return goPage;
	}
}
