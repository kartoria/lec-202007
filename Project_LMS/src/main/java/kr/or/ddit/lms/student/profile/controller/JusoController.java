package kr.or.ddit.lms.student.profile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JusoController {

	@RequestMapping("/profile/jusoPopup.do")
	public String proProfileJusoPopup() {
		return "profile/juso/jusoPopup";
	}
}
