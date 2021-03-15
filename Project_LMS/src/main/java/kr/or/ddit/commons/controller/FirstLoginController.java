package kr.or.ddit.commons.controller;

import java.util.Collections;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.lms.student.profile.VO.ProfileVO;
import kr.or.ddit.lms.student.profile.service.ProfileService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.validate.groups.UpdateGroup;

@Controller
public class FirstLoginController extends BaseController{
	@Inject
	private ProfileService service;
	@Inject
	PasswordEncoder passwordEncoder;
	//학생 최초 로그인
	@RequestMapping(value="/student/firstlogin.do")
	public String stuFirstLogin(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			,Model model) {
		ProfileVO profile = service.profileView(authMember);
		model.addAttribute("profileVO",profile);
		
		return "first/stuFirstLogin";
	}
	
	//교수 최초 로그인
	@RequestMapping(value="/pro/firstlogin.do")
	public String proFirstLogin(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			,Model model
			) {
		ProfileVO profile = service.profileView(authMember);
		model.addAttribute("profileVO",profile);
		return "first/proFirstLogin";
	}
	
	/**  최초로그인  개인정보 등록**/
	@RequestMapping(value="/first/firstprofileUpdate.do" ,method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	@Transactional
	@ResponseBody
	public Map<String, Object> stuFirstProfileUpdate(
			HttpSession session
			,@Validated(UpdateGroup.class) ProfileVO profileVO
			,BindingResult errors
			,@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			,HttpSession httpSession
			,HttpServletRequest request
			,Model model
			) {
		boolean resultUpdate =false;
		if(!errors.hasErrors()) {
			resultUpdate = service.profileUpdate(profileVO);
			if(!StringUtils.isBlank(profileVO.getMemPass()) ) {
				profileVO.setMemPass(passwordEncoder.encode(profileVO.getMemPass()));
			}
			authMember.setMemName(profileVO.getMemName());
			authMember.setMemAddr1(profileVO.getMemAddr1());
			authMember.setMemTel(profileVO.getMemTel());
			authMember.setMemMail(profileVO.getMemMail());
			authMember.setMemAddr1(profileVO.getMemAddr1());
			authMember.setMemAddr2(profileVO.getMemAddr2());
			authMember.setMemBank(profileVO.getMemBank());
			authMember.setMemAcn(profileVO.getMemAcn());
			if(profileVO.getMemImg()!=null) {
				authMember.setMemImg(profileVO.getMemImg());
			}
			if(!StringUtils.isBlank(profileVO.getMemPass())) {
				authMember.setMemPass(profileVO.getMemPass());
			}
			httpSession.setAttribute("imagePath", request.getContextPath()+"/memberImages/"+authMember.getMemImg());
			
			
			Map<String, Object> resultMap = Collections.singletonMap("Result", "OK");
			return resultMap;
			
			
		}else {
			model.addAttribute("first", "first");
			model.addAttribute("profileVO", profileVO);
			Map<String, Object> resultMap = Collections.singletonMap("Result", "NO");
			return resultMap;
		}
	}
	
}
