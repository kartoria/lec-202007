package kr.or.ddit.commons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@RequestMapping("/login/loginform.do")
	public String loginform(@RequestParam(value="msg", required=false, defaultValue="") String msg, Model model) {
		if(msg.equals("duplicated")) model.addAttribute("msg","다른 곳에서 로그인하였기 때문에 로그아웃 되었습니다.");
		return "login/loginForm";
	}
}
