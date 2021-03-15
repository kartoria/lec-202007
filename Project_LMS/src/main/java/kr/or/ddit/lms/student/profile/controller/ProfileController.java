/**
 * @author PC-NEW13
 * @since 2021. 1. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 22.     PC-NEW13      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
package kr.or.ddit.lms.student.profile.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.lms.student.profile.VO.ProfileVO;
import kr.or.ddit.lms.student.profile.service.ProfileService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.vo.NotyMessageVO.NotyType;

/**
 * 교수/학생 마이페이지 
 */
@Controller
public class ProfileController extends BaseController {

	@Inject
	private ProfileService service;
	@Inject
	PasswordEncoder passwordEncoder;
	
	
	/**  마이페이지 조회 **/
	@RequestMapping("/profile/profileList.do")
	public String stuProfile(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@RequestParam(value="memId", required=false) String memId,
			Model model
			) {
		ProfileVO profile = new ProfileVO();
		if("admin".equals(authMember.getMemId())){
			MemberVO member = new MemberVO();
			member.setMemId(memId);
			profile = service.profileView(member);
			
		}else {
			 profile = service.profileView(authMember);
			
		}
		
		
		model.addAttribute("profileVO",profile);
		
		String memType = authMember.getMemType();
		
		if("ROLE_STUDENT".equals(memType)) {
			return "profile/stuProfile";
		}else if("ROLE_PROFESSOR".equals(memType)){
			return "profile/proProfile";
		}else {
			return "admin/adminTuition/adminstuProfile/adminStuProfile";
		}
		
	}
	
	/**  비밀번호 재확인 **/
	@RequestMapping(value="/profile/profilePass.do" ,method=RequestMethod.POST)
	public String stuProfilePasscheck(
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			@RequestParam("memPass") String pass
			,Model model) {
		ProfileVO profile = service.profileUpdateView(authMember);
		model.addAttribute("profileVO",profile);
		String realPass = authMember.getMemPass();
		boolean matched = passwordEncoder.matches(pass, realPass);
		String memType = authMember.getMemType();
		
		if("ROLE_STUDENT".equals(memType)) {
			if(matched) {
				model.addAttribute("updateBtn", "OK");
				return "profile/stuProfileForm";
			}else {
				model.addAttribute("msg", NotyMessageVO.builder("비번오류").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
				return "forward:/profile/profileList.do";
			}
			
		}else {
			if(matched) {
				model.addAttribute("updateBtn", "OK");
				return "profile/proProfileForm";
			}else {
				model.addAttribute("msg", NotyMessageVO.builder("비번오류").layout(NotyLayout.topCenter).type(NotyType.error).timeout(3000).build());
				return "forward:/profile/profileList.do";
			}
		}
	}
	/**  마이페이지 개인정보 수정**/
	@RequestMapping(value="/profile/profileUpdate.do" ,method=RequestMethod.POST )
	@Transactional
	public String stuProfileUpdate(
			@Validated(UpdateGroup.class) ProfileVO profileVO,
			BindingResult errors,
			@AuthenticationPrincipal(expression="realMember") MemberVO authMember,
			Model model
			,HttpSession httpSession
			,HttpServletRequest request
			) {
		boolean resultUpdate =false;
		if(!errors.hasErrors()) {
			if(!StringUtils.isBlank(profileVO.getMemPass()) ) {
				profileVO.setMemPass(passwordEncoder.encode(profileVO.getMemPass()));
			}
			
			resultUpdate = service.profileUpdate(profileVO);
			
			String memType = authMember.getMemType();
				if(resultUpdate) {
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
					return "redirect:/profile/profileList.do";
				}else {
					model.addAttribute("profileVO", profileVO);
					if("ROLE_STUDENT".equals(memType)) {
						return "profile/stuProfile";
					}else if("ROLE_PROFESSOR".equals(memType)){
						return "profile/proProfile";
					}else {
						return "admin/adminTuition/adminStuProfile";
					}
				}
		}else {
			model.addAttribute("profileVO", profileVO);
			return "profile/stuProfileForm";
		}
	}
	
	
	
	
	
	
	
	
}
