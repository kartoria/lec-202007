package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod")
public class ProdRetrieveController{
	private static final Logger LOGGER = LoggerFactory.getLogger(ProdRetrieveController.class);
	
//	@Resource(type=ProdServiceImpl.class)
	private IProdService service;
	
	@RequestMapping("prodList.do")
	public String prodList(
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchDetail") ProdVO searchDetail,
			Model model) {

		PagingVO<ProdVO> pagingVO = new PagingVO<>(7, 3);
		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
//		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchDetail(searchDetail);

		int totalRecord = service.retrieveProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);

		model.addAttribute("pagingVO", pagingVO);

		return "prod/prodList";
	}
	
	@RequestMapping(value="prodList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String prodListForAjax(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,    
			@ModelAttribute("searchDetail") ProdVO searchDetail,
			Model model){
		prodList(currentPage, searchDetail, model);
		return "jsonView";
	}
	
	@RequestMapping("prodView.do")
	public String prodView( 
			@RequestParam(value="full", required=false) String full
			, @RequestParam("what") String prod_id
			, Model model){
		ProdVO prod = service.retrieveProd(prod_id);
		model.addAttribute("prod", prod);
		if("y".equals(full)) {
			return "prod/prodView";
		}else {
			return "prod/ajax/prodView";
		}
	}
}




























