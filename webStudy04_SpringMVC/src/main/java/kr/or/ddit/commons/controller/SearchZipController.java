package kr.or.ddit.commons.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.commons.service.ISearchZipService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.ZipVO;

@Controller
public class SearchZipController{
	@Inject
	private ISearchZipService service;
	
	@RequestMapping("/commons/searchZip.do")
	public String doGet(
			@RequestParam(value="draw", required=false, defaultValue="1") String draw
			, @RequestParam(value="length", required=false, defaultValue="7") int screenSize
			, @RequestParam(value="start", required=false, defaultValue="0") String start
			, @RequestParam(value="search[value]", required=false) String searchWord
			, Model model){
		
		int currentPage = StringUtils.isNumeric(start)?Integer.parseInt(start)/screenSize + 1 : 1;
			
		PagingVO<ZipVO> pagingVO = new PagingVO<>(screenSize, 5);
		// 검색 전 레코드 수
		int recordsTotal = service.retrieveZipCount(pagingVO);
		
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(new SearchVO(null, searchWord));
		// 검색 후 레코드 수
		int recordsFiltered = service.retrieveZipCount(pagingVO);
		
		List<ZipVO> zipList = service.retrieveZipList(pagingVO);
		
		model.addAttribute("draw", draw);
		model.addAttribute("recordsTotal", recordsTotal);
		model.addAttribute("recordsFiltered", recordsFiltered);
		model.addAttribute("data", zipList);
		
		
		
		return "jsonView";	
	}
}













