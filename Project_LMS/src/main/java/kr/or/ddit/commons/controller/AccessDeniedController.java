package kr.or.ddit.commons.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessDeniedController extends BaseController{
	
	@RequestMapping("/access-denied.do")
	public String accessControl(HttpServletRequest request) {
		String referer = request.getHeader("referer");
		printInfo("referer", referer);
		return "common/403error";
	}
}
