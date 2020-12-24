package kr.or.ddit.prod.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdInsertController {
	private IProdService service = ProdServiceImpl.getInstance();
	
	@RequestMapping("/prod/prodInsert.do")
	public String prodInsertGet() {
		return "prod/prodForm";
	}
	
	@RequestMapping(value="/prod/prodInsert.do", method=RequestMethod.POST)
	public String prodInsertPost(@ModelAttribute("prod") ProdVO prod, HttpServletRequest req){
		CommonValidator<ProdVO> validator = new CommonValidator<>();
		Map<String, List<String>> errors = new LinkedHashMap<>();
		boolean valid = validator.validate(prod, errors);
		req.setAttribute("errors", errors);
		if(valid) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case OK:
				return "redirect:/prod/prodList.do";
			default:
				//메세지 전달
			}
		}
		return "prod/prodForm";
	}
}
