package kr.or.ddit.commons.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController{
	
	@RequestMapping(value="/login/logout.do", method=RequestMethod.POST)
	public String doPost(HttpSession session) throws IOException{
		session.invalidate();
		String encoded= URLEncoder.encode("로그아웃성공", "UTF-8");
		return "redirect:/?message="+encoded;		
	}
}










