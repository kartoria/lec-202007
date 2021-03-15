package kr.or.ddit.commons.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.PushMessageType;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.vo.PushMessageVO;
import kr.or.ddit.websocket.event.PushMessageEvent;

@Controller
public class indexController extends BaseController {
	@Inject
	IMemberDAO memberDAO;

	
	@RequestMapping("/index.do")
	public String goToIndex(@AuthenticationPrincipal(expression="realMember") MemberVO authMember
			,Model model
	) {
		String role = authMember.getMemType();
		String goPage = null;
		MemberVO memberVO = memberDAO.selectMember(authMember);
		
		if(!memberVO.getMemType().equals("ROLE_ADMIN") && (memberVO.getMemTel()==null  ||memberVO.getMemAddr1()==null ||memberVO.getMemAddr2()==null)) {
			model.addAttribute("first", "first");
		}
		
		if(role.equals("ROLE_ADMIN")) goPage = "redirect:/admin/dashboard.do";
		else if(role.equals("ROLE_STUDENT")) goPage = "index/studentIndex";
		else if(role.equals("ROLE_PROFESSOR")) goPage = "index/professorIndex";
		else goPage = "login/loginForm";
		
		return goPage;
	}
}
