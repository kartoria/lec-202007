package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.NotyMessageVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController{
	@Inject
	private IProdService service;
	
	@GetMapping
	public String doGet(){
		return "prod/prodForm";
	}
	
	@PostMapping
	public String doPost(@ModelAttribute("prod") ProdVO prod, Model model) throws IOException{
		
//		Map<String, List<String>> errors = new LinkedHashMap<>();
//		req.setAttribute("errors", errors);
//		CommonValidator<ProdVO> validator = new CommonValidator<>();
//		boolean valid = validator.validate(prod, errors, InsertGroup.class);
		
		String goPage = null;
		
		if(true) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
				case OK:
					
					goPage =  "redirect:/prod/prodView.do?full=y&what="+prod.getProd_id();
					break;
				default:	
					model.addAttribute("message", NotyMessageVO.builder("서버 오류").build());
					goPage = "prod/prodForm";
				break;
			}
		}else {
			goPage = "prod/prodForm";
		}
		return goPage;
	}
}













