package kr.or.ddit.prod.controller;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;

@Controller
public class ProdUpdateController {
	
	@RequestMapping(value="/prod/prodUpdate.do", method=RequestMethod.POST)
	public String update() {
		
		
		return null;
	}
}
