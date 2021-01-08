package kr.or.ddit;

import kr.or.ddit.mvc.stereotype.Controller;
import kr.or.ddit.mvc.stereotype.RequestMapping;


@Controller
public class IndexServlet{
	
	@RequestMapping("/")
	public String doGet(){
		return "index";
	}
}












