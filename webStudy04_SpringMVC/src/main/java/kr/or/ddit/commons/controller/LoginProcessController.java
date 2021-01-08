package kr.or.ddit.commons.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IAuthenticateService;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
public class LoginProcessController{
	@Inject
	IAuthenticateService service;
	@Inject
	WebApplicationContext container;
	
	private boolean validate(String mem_id, String mem_pass) {
		return true;
	}
	
	@RequestMapping(value="/login/loginProcess.do", method=RequestMethod.POST)
	public String doPost(
			@RequestParam("mem_id") String mem_id
			, @RequestParam("mem_pass") String mem_pass
			, @RequestParam(value="saveId", required=false) String saveId
			, HttpSession session
			, HttpServletRequest req
			, HttpServletResponse resp
			, RedirectAttributes redirectAttributes
			, @CookieValue(value="saveId",  required = false) Cookie saveIdCookie) throws IOException{
		
		Object result  = service.authenticate(MemberVO.builder()
												.mem_id(mem_id)
												.mem_pass(mem_pass)
												.build());
		String goPage = null;
		if("saveId".equals(saveId)) {
			if(saveIdCookie == null) saveIdCookie = CookieUtils.createCookie("saveId", mem_id, container.getServletContext()+"/login/loginForm.do" , 60*60*24);
			else saveIdCookie.setValue(mem_id);
			resp.addCookie(saveIdCookie);
		}else {
			saveIdCookie.setMaxAge(0);
		}
		if(result instanceof MemberVO) {
			MemberVO authMember = (MemberVO) result;
			session.setAttribute("authMember", authMember);
			goPage = "redirect:/";
		}else {
			String message = null;
			if(ServiceResult.NOTEXIST.equals(result)) {
				message = "아이디 오류, 그런 사람 없음.";
			}else if(ServiceResult.INVALIDPASSWORD.equals(result)){
				message = "비번 오류, 다시 입력하셈.";
				redirectAttributes.addFlashAttribute("mem_id", mem_id);
			}else{
				message = "사용자가 유효하지 않슴돠.";
				redirectAttributes.addFlashAttribute("mem_id", mem_id);
			}
			redirectAttributes.addFlashAttribute("message", NotyMessageVO.builder(message).build());
			goPage = "redirect:/login/loginForm.do";	
		}
		return goPage;
	}
}











