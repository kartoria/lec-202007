package kr.or.ddit.buyer.controller;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
public class BuyerUpdateController {
	@Inject
	IBuyerService service;
	
	public void addAttribute(HttpServletRequest req) {
		req.setAttribute("currentAction", "/buyer/buyerUpdate.do");
	}
    
	@RequestMapping("/buyer/buyerUpdate.do")
	public String form(@RequestParam("what") String buyer_id, HttpServletRequest req){
		addAttribute(req);
		
		BuyerVO buyer = service.retrieveBuyer(buyer_id);
		req.setAttribute("buyer", buyer);
		return "buyer/buyerForm";
	}
	
	@RequestMapping(value="/buyer/buyerUpdate.do", method=RequestMethod.POST)
	public String update(
		@ModelAttribute("buyer") BuyerVO buyer
		, HttpServletRequest req
			) throws IOException{
		addAttribute(req);
		
		String goPage = null;
		String saveFolderUrl = "/buyerImages";
		File saveFolder = new File(req.getServletContext().getRealPath(saveFolderUrl));
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		
		
		if (true) {
//		2. 로직 선택 service.modifybuyer(buyer)
			ServiceResult result = service.modifyBuyer(buyer);
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