package kr.or.ddit.commons.controller;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginFormController{
	
	@RequestMapping("/login/loginForm.do")
	public String doGet(@CookieValue(value="saveId",  required = false) Cookie saveIdCookie, Model model){
		if(saveIdCookie != null) {
			String saveId = saveIdCookie.getValue();
			model.addAttribute("saveId", saveId);
		}
		return "login/loginForm";
	}
}






















