package kr.or.ddit.buyer.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class BuyerRetrieveController {
	@Inject
    IBuyerService service;   
	
    @RequestMapping("/buyer/buyerList.do")
	public String list(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("searchVO") SearchVO searchVO 
		, HttpServletRequest req 
	){
		PagingVO<BuyerVO> pagingVO = new PagingVO<>(5, 3);
		pagingVO.setSearchVO(searchVO);
		
		pagingVO.setTotalRecord(service.retrieveBuyerCount(pagingVO));
		
		pagingVO.setCurrentPage(currentPage);
		List<BuyerVO> buyerList = service.retrieveBuyerList(pagingVO);
		pagingVO.setDataList(buyerList);
		
		req.setAttribute("pagingVO", pagingVO);
		return "buyer/buyerList";
	}
    
    @RequestMapping("/buyer/buyerView.do")
	public String view(@RequestParam("what") String buyer_id, HttpServletRequest req){
		BuyerVO buyer = service.retrieveBuyer(buyer_id);
		req.setAttribute("buyer", buyer);
		return "buyer/buyerView";
	}
}
