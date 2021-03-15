package kr.or.ddit.cyber.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.ddit.commons.controller.BaseController;
import kr.or.ddit.cyber.service.CyberService;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.LectureVO;

/**
 * @author PC-17
 * @since 2021. 1. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 22.     PC-17      최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 */
@Controller
public class MyClassController extends BaseController {
	@Inject
	CyberService cyberService;
	
	@GetMapping("/cyber/myclass/list.do")
	public String doGet(Model model, @AuthenticationPrincipal(expression="realMember") MemberVO authMember) {
		List<LectureVO> lecList =  cyberService.getMyLectureList(authMember);
		
		printInfo("lecList", lecList);
		model.addAttribute("pageTitle", "내 강의실 목록");
		model.addAttribute("lecList", lecList);
		return "cyber/myclass/myClass";
	}
}
