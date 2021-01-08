package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateController {
	
	@Inject
	private IProdService service;
	
	@GetMapping
	public String updateForm(@RequestParam("what")String prod_id, HttpServletRequest req) {
 		ProdVO prod = service.retrieveProd(prod_id);
 		req.setAttribute("prod", prod);
 		return "prod/prodForm";
	}
	
	
	@PostMapping
	public String update(@ModelAttribute("prod") ProdVO prod, HttpServletRequest req) throws IOException {
		
//		Map<String, List<String>> errors = new LinkedHashMap<>();
//		req.setAttribute("errors", errors);
//		CommonValidator<ProdVO> validator = new CommonValidator<>();
//		boolean valid = validator.validate(prod, errors, UpdateGroup.class);
		
		String goPage = null;
		
		if(true) {
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
				case OK:
					goPage =  "redirect:/prod/prodView.do?full=y&what="+prod.getProd_id();
					
					break;
				default:	
					req.setAttribute("message", NotyMessageVO.builder("서버 오류").build());
					goPage = "prod/prodForm";
				break;
			}
		}else {
			goPage = "prod/prodForm";
		}
		return goPage;
	}
}
