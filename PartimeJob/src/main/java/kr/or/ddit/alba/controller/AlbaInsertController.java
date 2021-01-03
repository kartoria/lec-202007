package kr.or.ddit.alba.controller;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.stereotype.Controller;
import kr.or.ddit.mvc.stereotype.RequestMapping;

@Controller
public class AlbaInsertController {

	@RequestMapping("/alba/albaInsert.do")
	public String doGet() {
		return "alba/albaForm";
	}
}
