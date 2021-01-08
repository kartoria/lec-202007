package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.prod.dao.IOthersDAO;
import kr.or.ddit.vo.BuyerVO;
@Controller
public class OthersController{
	@Inject
	private IOthersDAO dao;
	
	@RequestMapping("/prod/getOthers.do")
	public String doGet(Model model){
		// json 데이터로 비동기 요청에 대한 응답의 형태로 buyerList/lprodList 제공
		List<BuyerVO> buyerList = dao.selectBuyerList();
		List<Map<String, Object>> lprodList = dao.selectLprodList();
		model.addAttribute("lprodList", lprodList);
		model.addAttribute("buyerList", buyerList);
		
		return "jsonView";
	}
}
















