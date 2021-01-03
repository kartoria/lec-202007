package kr.or.ddit.buyer.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.streotype.Controller;
import kr.or.ddit.mvc.streotype.RequestMapping;
import kr.or.ddit.validate.CommonValidator;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.NotyMessageVO;

@Controller
public class BuyerUpdateController {
	
	IBuyerService service = BuyerServiceImpl.getInstance();
	
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
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		CommonValidator<BuyerVO> validator = new CommonValidator<>();
		boolean valid = validator.validate(buyer, errors, UpdateGroup.class);
		
		String goPage = null;
		String saveFolderUrl = "/buyerImages";
		File saveFolder = new File(req.getServletContext().getRealPath(saveFolderUrl));
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
		
		
		if (valid) {
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