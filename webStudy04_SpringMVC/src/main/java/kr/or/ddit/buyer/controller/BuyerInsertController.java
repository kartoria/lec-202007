package kr.or.ddit.buyer.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
public class BuyerInsertController{
	@Inject
    IBuyerService service;
	
	public void addAttribute(HttpServletRequest req) {
		req.setAttribute("currentAction", "/buyer/buyerInsert.do");
	}
    
	@RequestMapping("/buyer/buyerInsert.do")
	public String form(HttpServletRequest req){
		addAttribute(req);
		return "buyer/buyerForm";
	}
	
	@RequestMapping(value="/buyer/buyerInsert.do", method=RequestMethod.POST)
	public String insert(
			@ModelAttribute("buyer") BuyerVO buyer, HttpServletRequest req
			) throws IOException{
		addAttribute(req);
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
//		CommonValidator<BuyerVO> validator = new CommonValidator<>();
//		boolean valid = validator.validate(buyer, errors, InsertGroup.class);
		
		String goPage = null;
		String saveFolderUrl = "/buyerImages";
		File saveFolder = new File(req.getServletContext().getRealPath(saveFolderUrl));
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		
		if (true) {
//		2. 로직 선택 service.createbuyer(buyer)
			ServiceResult result = service.createBuyer(buyer);
//		3. 로직의 실행 결과에 따른 분기 페이지 선택
			switch (result) {
			case OK:
				buyer.saveTo(saveFolder);
				goPage = "redirect:/buyer/buyerView.do?what="+buyer.getBuyer_id();
				break;
			default: // FAIL
				req.setAttribute("message", NotyMessageVO.builder("서버 오류").build());
				goPage = "buyer/buyerForm";
				break;
			}
		} else {
			goPage = "buyer/buyerForm";
		}
//		4. 모델 데이터 공유
		return goPage;
	}
}