package kr.or.ddit.myclassroom.video.controller;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.myclassroom.video.service.VideoService;
import kr.or.ddit.vo.LectureVO;

/**
 * 강의 소개
 */
@Controller
public class LecIntroController extends BaseController{
	@Inject
	VideoService service;
	
	/** 강의 소개 조회 **/
	@RequestMapping("/myclass/{lecCode}/intro.do")
	public String introGet(@AuthenticationPrincipal(expression="realMember") MemberVO authMember
						,  @PathVariable(value="lecCode", required=true) String lecCode
						,  @RequestAttribute("subName") String subName
						,  Model model) {
		LectureVO lec = service.retrieveLecIntro(lecCode);
		// 강의 삭제가 있는지?
		model.addAttribute("lec",lec);
		model.addAttribute("pageTitle", pageTitleWithSubName(subName, "강의소개"));
		model.addAttribute("pageLink","/myclass/"+lecCode+"/intro.do");
		return "myClassRoom/intro/introView";
	}
}
