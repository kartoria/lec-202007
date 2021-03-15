package kr.or.ddit.enrolment.controller;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.lms.student.profile.VO.ProfileVO;
import kr.or.ddit.lms.student.profile.service.ProfileService;
import kr.or.ddit.member.vo.MemberVO;

@Controller
public class EnrolmentProfileController {
	
	@Inject
	private ProfileService service;
	@Inject
	PasswordEncoder passwordEncoder;
	
	
	/**  마이페이지 조회 **/
	@RequestMapping("/enrolment/profileList.do")
	public String enrolmentProfile(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			Model model
			) {
		ProfileVO profile = service.profileView(authMember);
		
		model.addAttribute("profileVO",profile);
		
		String memType = authMember.getMemType();
		
		return "enrolment/enrolmentProfile";
		
	}
	
}
